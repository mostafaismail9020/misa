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
package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import com.sap.ibso.eservices.storefront.forms.SagiaResetPwdForm;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ForgottenPwdForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commerceservices.customer.TokenInvalidatedException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * Controller for the forgotten password pages. Supports requesting a password reset email as well as changing the
 * password once you have got the token that was sent via email.
 */
@Controller
@RequestMapping(value = "/login/pw")
public class PasswordResetPageController extends AbstractPageController {
    private static final String FORGOTTEN_PASS_TITLE = "forgottenPwd.title";

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(PasswordResetPageController.class);

    private static final String REDIRECT_PASS_REQ_CONF = "redirect:/login/pw/request/external/conf";
    private static final String REDIRECT_LOGIN = "redirect:/login";
    private static final String REDIRECT_HOME = "redirect:/";
    private static final String UPDATE_PASS_CMS_PAGE = "updatePassword";

    @Resource(name = "customerFacade")
    private CustomerFacade customerFacade;

    @Resource(name = "simpleBreadcrumbBuilder")
    private ResourceBreadcrumbBuilder resourceBreadcrumbBuilder;

    @Resource(name = "sagiaResetPswValidator")
    private Validator sagiaResetPswValidator;

    @Resource(name = "sagiaCustomerFacade")
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Resource(name = "sagiaConfigurationFacade")
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String getPasswordRequest(final Model model) throws CMSItemNotFoundException {
        return getExternalPasswordRequest(model);
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public String passwordRequest(@Valid final ForgottenPwdForm form, final BindingResult bindingResult, final Model model)
            throws CMSItemNotFoundException {
        if (bindingResult.hasErrors()) {
            return ControllerConstants.Views.Fragments.Password.PasswordResetRequestPopup;
        } else {
            try {
                customerFacade.forgottenPassword(form.getEmail());
            } catch (final UnknownIdentifierException unknownIdentifierException) {
                LOG.warn("Email: " + form.getEmail() + " does not exist in the database.");
            }
            return ControllerConstants.Views.Fragments.Password.ForgotPasswordValidationMessage;
        }
    }

    @RequestMapping(value = "/request/external", method = RequestMethod.GET)
    public String getExternalPasswordRequest(final Model model) throws CMSItemNotFoundException {
        model.addAttribute(new ForgottenPwdForm());
        storeCmsPageInModel(model, getContentPageForLabelOrId(null));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, resourceBreadcrumbBuilder.getBreadcrumbs(FORGOTTEN_PASS_TITLE));
        return ControllerConstants.Views.Pages.Password.PasswordResetRequest;
    }

    @RequestMapping(value = "/request/external/conf", method = RequestMethod.GET)
    public String getExternalPasswordRequestConf(final Model model) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(null));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, resourceBreadcrumbBuilder.getBreadcrumbs(FORGOTTEN_PASS_TITLE));
        return ControllerConstants.Views.Pages.Password.PasswordResetRequestConfirmation;
    }

    @RequestMapping(value = "/request/external", method = RequestMethod.POST)
    public String externalPasswordRequest(@Valid final ForgottenPwdForm form, final BindingResult bindingResult, final Model model, final RedirectAttributes redirectModel)
            throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(null));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, resourceBreadcrumbBuilder.getBreadcrumbs(FORGOTTEN_PASS_TITLE));

        if (bindingResult.hasErrors()) {
            return ControllerConstants.Views.Pages.Password.PasswordResetRequest;
        } else {
            try {
                customerFacade.forgottenPassword(form.getEmail());
            } catch (final UnknownIdentifierException unknownIdentifierException) {
                LOG.warn("Email: " + form.getEmail() + " does not exist in the database.");
            }
            return REDIRECT_PASS_REQ_CONF;
        }
    }

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String getChangePassword(@ModelAttribute SagiaResetPwdForm sagiaResetPwdForm, @RequestParam(required = false) final String token, final Model model)
            throws CMSItemNotFoundException {
        if (StringUtils.isBlank(token)) {
            return REDIRECT_HOME;
        }
        sagiaResetPwdForm.setToken(token);
        model.addAttribute(sagiaResetPwdForm);

        String currentLanguage = getI18nService().getCurrentLocale().getLanguage();
        String backendRegex = sagiaCustomerFacade.getSagiaConfigurationFacade().getPasswordRegex().replace("\\", "\\\\");
        String backendRegexErrorMessage = sagiaConfigurationFacade.getPasswordErrorMessage(currentLanguage);

        model.addAttribute("backendRegex", backendRegex);
        model.addAttribute("backendRegexErrorMessage", backendRegexErrorMessage);

        storeCmsPageInModel(model, getContentPageForLabelOrId(null));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, resourceBreadcrumbBuilder.getBreadcrumbs("updatePwd.title"));
        return ControllerConstants.Views.Pages.Password.PasswordResetChangePage;
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String changePassword(@Valid final SagiaResetPwdForm form, final BindingResult bindingResult, final Model model,
                                 final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
        getSagiaResetPswValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            prepareErrorMessage(model, UPDATE_PASS_CMS_PAGE);
            return ControllerConstants.Views.Pages.Password.PasswordResetChangePage;
        }
        if (!StringUtils.isBlank(form.getToken())) {
            try {
                customerFacade.updatePassword(form.getToken(), form.getPwd());
                GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
                        "account.confirmation.password.updated");
            } catch (final TokenInvalidatedException e) {
                GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "updatePwd.token.invalidated");
            } catch (final RuntimeException e) {
                LOG.error("A RuntimeException occurred while updating password",e);
                GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "updatePwd.token.invalid");
            }
        } else {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "updatePwd.token.invalid");
            return REDIRECT_PREFIX + "/login/pw/change?token=" + form.getToken();
        }
        return REDIRECT_LOGIN;
    }

    /**
     * Prepares the view to display an error message
     *
     * @throws CMSItemNotFoundException
     */
    protected void prepareErrorMessage(final Model model, final String page) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(page));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(page));
    }

    public Validator getSagiaResetPswValidator() {
        return sagiaResetPswValidator;
    }
}
