/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.investsaudi.controllers;

import com.investsaudi.constants.InvestsaudisecureportalWebConstants;
import com.investsaudi.data.B2BRegistrationData;
import com.investsaudi.data.SagiaB2BUnitData;
import com.investsaudi.enums.InvestSaudiLob;
import com.investsaudi.exceptions.CustomerAlreadyExistsException;
import com.investsaudi.exceptions.PhoneNumberUsedException;
import com.investsaudi.facades.B2BRegistrationFacade;
import com.investsaudi.forms.RegistrationForm;
import com.investsaudi.services.SagiaB2BUnitService;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.localization.Localization;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
/**
 * Registration page controller: Handles Get and Post request and dispatches relevant wortkflow facades and necessary
 * services
 */
@RequestMapping(value = InvestsaudisecureportalWebConstants.RequestMappings.ACCOUNT_REGISTRATION)
public class B2BRegistrationController extends AbstractB2BRegistrationController
{

	private static final Logger LOG = Logger.getLogger(B2BRegistrationController.class);
	private static final String NUMBER_REGEX = "[0-9]+";
    private static final String FALSE = "false";

	private final static class MessageKeys
	{
		public static final String REGISTER_SUBMIT_CONFIRMATION = "text.secureportal.register.submit.confirmation";
		public static final String REGISTER_ACCOUNT_EXISTING = "text.secureportal.register.account.existing";
		public static final String REGISTER_PHONENUMBER_EXISTING = "text.secureportal.register.phone.number.existing";
		public static final String SCP_LINK_CREATE_ACCOUNT = "text.secureportal.link.createAccount";
	}

	private static final String HOME_REDIRECT = REDIRECT_PREFIX + ROOT;

	@Resource(name = "checkoutFacade")
	private CheckoutFacade checkoutFacade;

	@Resource(name = "b2bRegistrationFacade")
	private B2BRegistrationFacade b2bRegistrationFacade;

	@Resource(name = "modelService")
	private ModelService modelService;

	@Resource(name = "securePortalRegistrationValidator")
	private Validator registrationValidator;

	@Resource
	private SagiaB2BUnitService sagiaB2BUnitService;


	@Resource(name = "defaultSagiaB2BUnitConverter")
	private Converter<B2BUnitModel, SagiaB2BUnitData> sagiaB2BUnitConverter;

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "i18NService")
    private I18NService i18NService;

	@Resource(name = "enumerationService")
	private EnumerationService enumerationService;


    @ModelAttribute("investSaudiLob")
	public List<SelectOption> getInvestSaudiLobs()
	{
    	List<InvestSaudiLob> investSaudiLobList = enumerationService.getEnumerationValues(InvestSaudiLob.class);
		final List<String> lobs = new ArrayList<String>();
		for (final InvestSaudiLob lob : investSaudiLobList)
		{
			lobs.add(lob.getCode());
		}
		return populateSelectBoxForLob(lobs);
	}

	protected List<SelectOption> populateSelectBoxForLob(final List<String> listOfLobs)
	{
		Locale locale = i18NService.getCurrentLocale();
		final List<SelectOption> selectOptions = new ArrayList<SelectOption>();
		for (final String lob : listOfLobs)
		{
			selectOptions.add(new SelectOption(lob, messageSource.getMessage("investsaudi.registration.lob."+lob, null, locale)));
		}

		return selectOptions;
	}

	/**
	 * Data class used to hold a drop down select option value. Holds the code identifier as well as the display name.
	 */
	public static class SelectOption
	{
		private final String code;
		private final String name;

		public SelectOption(final String code, final String name)
		{
			this.code = code;
			this.name = name;
		}

		public String getCode()
		{
			return code;
		}

		public String getName()
		{
			return name;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showRegistrationPage(final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{
		if (getCmsSiteService().getCurrentSite().isEnableRegistration())
		{
			return getDefaultRegistrationPage(model, getContentPageForLabelOrId(getRegistrationCmsPage()));
		}
		return HOME_REDIRECT;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitRegistration(final RegistrationForm form, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final HttpSession session, final RedirectAttributes redirectModel)
					throws CMSItemNotFoundException
	{

		populateModelCmsContent(model, getContentPageForLabelOrId(getRegistrationCmsPage()));
		model.addAttribute(form);

		getRegistrationValidator().validate(form, bindingResult);
		if (bindingResult.hasErrors())
		{
			if(bindingResult.getGlobalError() != null && bindingResult.getGlobalError().getCodes() != null && bindingResult.getGlobalError().getCodes().length > 0  &&  Arrays.asList(bindingResult.getGlobalError().getCodes()).contains("recaptcha.challenge.field.invalid")) {
				GlobalMessages.addFlashMessage(
				          redirectModel,
				          GlobalMessages.ERROR_MESSAGES_HOLDER,
				          Localization.getLocalizedString("captcha.error.message.redirect.page"));


				return "redirect:/register";



			}
			return getRegistrationView();
		}

		try
		{
			b2bRegistrationFacade.register(convertFormToData(form));
		}
		catch (final CustomerAlreadyExistsException e) //NOSONAR
		{
			LOG.error("Failed to register account. Account already exists.");
			GlobalMessages.addErrorMessage(model, Localization.getLocalizedString(MessageKeys.REGISTER_ACCOUNT_EXISTING));
			return getRegistrationView();
		}
		catch (final PhoneNumberUsedException e) //NOSONAR
		{
			LOG.error("Failed to register phone number already exists.");
			GlobalMessages.addErrorMessage(model, Localization.getLocalizedString(MessageKeys.REGISTER_PHONENUMBER_EXISTING));
			return getRegistrationView();
		}



		GlobalMessages.addInfoMessage(model, Localization.getLocalizedString(MessageKeys.REGISTER_SUBMIT_CONFIRMATION));

		return getDefaultLoginPage(false, session, model);
	}

	/**
    *
    * @return Validation Response that contains code
    */
   @RequestMapping(value = "/regvalidation", method = RequestMethod.GET, produces = "text/plain")
   @ResponseBody
   public String validate(
           @RequestParam(required = false) String email,
           @RequestParam(required = false) String userName,
           @RequestParam(required = false) String mobileNumber,
           @RequestParam(required = false) String mobileCountryCode) {
       if (Strings.isNotEmpty(email) && (StringUtils.length(email) > 255 || !validateEmailAddress(email))) {
           return FALSE;
       }

       if (Strings.isNotEmpty(mobileNumber) && (StringUtils.length(mobileNumber) > 255 || !mobileNumber.matches(NUMBER_REGEX))) {
           return FALSE;
       }

       boolean validationResult = b2bRegistrationFacade.validateUniqueValue(userName, email);
       if (!validationResult) {
           return FALSE;
       }
       return "true";
   }

   private boolean validateEmailAddress(final String email) {
       return Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b").matcher(email).matches();
   }

	/**
	 * @param form
	 *           Form data as submitted by user
	 * @return A DTO object built from the form instance
	 */
	protected B2BRegistrationData convertFormToData(final RegistrationForm form)
	{
		final B2BRegistrationData registrationData = new B2BRegistrationData();
		BeanUtils.copyProperties(form, registrationData);
		return registrationData;
	}

	protected Validator getRegistrationValidator()
	{
		return registrationValidator;
	}

	@Override
	protected String getRegistrationView()
	{
		return InvestsaudisecureportalWebConstants.Views.REGISTRATION_PAGE;
	}

	@Override
	protected String getRegistrationCmsPage()
	{
		return InvestsaudisecureportalWebConstants.CMS_REGISTER_PAGE_NAME;
	}

	@Override
	protected void populateModelCmsContent(final Model model, final ContentPageModel contentPageModel)
	{

		storeCmsPageInModel(model, contentPageModel);
		setUpMetaDataForContentPage(model, contentPageModel);

		final Breadcrumb registrationBreadcrumbEntry = new Breadcrumb("#",
				getMessageSource().getMessage(MessageKeys.SCP_LINK_CREATE_ACCOUNT, null, getI18nService().getCurrentLocale()), null);
		model.addAttribute("breadcrumbs", Collections.singletonList(registrationBreadcrumbEntry));
	}

	@Override
	protected String getView()
	{
		return InvestsaudisecureportalWebConstants.Views.LOGIN_PAGE;
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("login");
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
	{
		return HOME_REDIRECT;
	}

	/**
	 * Spring MVC Model attribute that holds the list of B2B units used to populate the "Entity" dropdown.
	 */
	@ModelAttribute("b2bUnit")
	public List<SagiaB2BUnitData> getB2BUnit()
	{
		final List<SagiaB2BUnitData> b2bUnitData = Converters.convertAll(sagiaB2BUnitService.getDisplayedB2BUnit(), sagiaB2BUnitConverter);

		return b2bUnitData;
	}


}