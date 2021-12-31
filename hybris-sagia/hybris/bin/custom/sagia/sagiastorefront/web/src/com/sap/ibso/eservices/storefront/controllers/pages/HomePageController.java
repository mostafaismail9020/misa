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

import com.sap.ibso.eservices.facades.data.PersonShareholderData;
import com.sap.ibso.eservices.facades.data.ProfileCompanyRepresentativeData;
import com.sap.ibso.eservices.facades.sagia.SagiaAccountFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseFacade;
import com.sap.ibso.eservices.facades.sagia.impl.DefautSagiaLicenseApplyFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.feedback.FeedbackService;

import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.assistedservicefacades.AssistedServiceFacade;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.util.Config;

import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import com.sap.ibso.eservices.storefront.dto.FeedBackPayloadData;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Controller for home page
 */
@Controller
@RequestMapping("/")
public class HomePageController extends AbstractPageController {
    private static final Logger LOG = LoggerFactory.getLogger(HomePageController.class);

    private static final String LOGOUT = "logout";
    private static final String ACCOUNT_CONFIRMATION_SIGNOUT_TITLE = "account.confirmation.signout.title";
    private static final String ACCOUNT_CONFIRMATION_CLOSE_TITLE = "account.confirmation.close.title";

    private static final String CHAT_FEEDBACK = "CHAT_FEEDBACK";
    
    private static final String HOME_PAGE = "/home";
    private static final String INVESTSAUDI_LOGIN_PAGE = "/investsaudi-login";
    private static final String ERROR_CMS_PAGE = "notFound";
    

    @Autowired
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

    @Resource(name = "sagiaLicenseApplyFacade")
    private DefautSagiaLicenseApplyFacade sagiaLicenseApplyFacade;
        
    @Resource
    private FeedbackService feedbackService;

    @Resource(name = "sagiaAccountFacade")
    private SagiaAccountFacade sagiaAccountFacade;

    @Resource(name = "customerFacade")
    private CustomerFacade customerFacade;

	@Resource(name = "assistedServiceFacade")
	private AssistedServiceFacade assistedServiceFacade;


	/**
     * 
     * @param closeAcc
     * @param logout
     * @param model
     * @param redirectModel
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET)
	public String home(@RequestParam(value = WebConstants.CLOSE_ACCOUNT, defaultValue = "false") final boolean closeAcc,
			@RequestParam(value = LOGOUT, defaultValue = "false") final boolean logout, final Model model,
			final RedirectAttributes redirectModel, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		if (logout)
		{
			String message = ACCOUNT_CONFIRMATION_SIGNOUT_TITLE;
			if (closeAcc)
			{
				message = ACCOUNT_CONFIRMATION_CLOSE_TITLE;
			}
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER, message);
			//return REDIRECT_PREFIX + ROOT;
			return REDIRECT_PREFIX + HOME_PAGE;
		}				
		
		CustomerData customer = customerFacade.getCurrentCustomer();
		LOG.info("== HomePageController == CustomerData Email=" + customer.getEmail());
		if (null != customer && null != customer.getEmail())
		{
			storeCmsPageInModel(model, getContentPageForLabelOrId(null));
	        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
	        updatePageTitle(model, getContentPageForLabelOrId(null));
	        
			//Clear the cache
	        sagiaDashboardWithoutLicenseFacade.evictApplicationStatus();
	        
	        try {
	            if (sagiaDashboardWithoutLicenseFacade.hasLicense()) {
	                return "redirect:/dashboard"+getOtherRequestParameters(request);
	            }
	        } catch(Exception e) {
	            LOG.error(e.getMessage(), e);
	        }
	        return "redirect:/dashboard-without-license"+getOtherRequestParameters(request);
		}
		if(assistedServiceFacade.isAssistedServiceAgentLoggedIn())
		{
			return "redirect:/login";
		}
		
		final ContentPageModel contentPage = getContentPageForLabelOrId(HOME_PAGE);
		storeCmsPageInModel(model, contentPage);
		setUpMetaDataForContentPage(model, contentPage);
		//updatePageTitle(model, contentPage);
		storeContentPageTitleInModel(model, contentPage.getTitle());

		return getViewForPage(model);
	}
	
    @RequestMapping(value = "/home", method = RequestMethod.GET)
   	public String homePage(final Model model, final HttpServletRequest request, final HttpServletResponse response) 
   			throws CMSItemNotFoundException
   	{
		final ContentPageModel contentPage = getContentPageForLabelOrId(HOME_PAGE);
		storeCmsPageInModel(model, contentPage);
		setUpMetaDataForContentPage(model, contentPage);
		//updatePageTitle(model, contentPage);
		storeContentPageTitleInModel(model, contentPage.getTitle());

		return getViewForPage(model);
   	}
    
    @RequestMapping(value = "/investsaudi-login", method = RequestMethod.GET)
   	public String investSaudiLogin(final Model model, final HttpServletRequest request, final HttpServletResponse response) 
   			throws CMSItemNotFoundException
   	{
		final ContentPageModel contentPage = getContentPageForLabelOrId(INVESTSAUDI_LOGIN_PAGE);
		storeCmsPageInModel(model, contentPage);
		setUpMetaDataForContentPage(model, contentPage);
		//updatePageTitle(model, contentPage);
		storeContentPageTitleInModel(model, contentPage.getTitle());

		return getViewForPage(model);
   	}
    
    
	public String getOtherRequestParameters(final HttpServletRequest request)
	{
		String asmRequestParameter = "asm";
		String assistedServiceModeRequested = request.getParameter(asmRequestParameter);
		if (request.getRequestURI().endsWith(asmRequestParameter))
		{
			assistedServiceModeRequested = "false";
		}
		return assistedServiceModeRequested == null ? "" : ("?"+asmRequestParameter + "=" + assistedServiceModeRequested);
	}
	
    @RequestMapping(value = "/sendFeedback", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
    @RequireHardLogIn
    public void sendFeedback(@ModelAttribute("feedBackPayloadData") final FeedBackPayloadData feedBackPayloadData) {

        String investorEmail = null;
        if (CHAT_FEEDBACK.equals(feedBackPayloadData.getServiceId())) {
            ProfileCompanyRepresentativeData primaryContact = sagiaAccountFacade.getPrimaryContact();
            if (primaryContact != null && primaryContact.getEmail() != null) {
                investorEmail = primaryContact.getEmail();
            } else {
                investorEmail = customerFacade.getCurrentCustomer().getEmail();
            }
        }

        feedbackService.submitUserExperienceFeedback(
        			feedBackPayloadData.getServiceId(),
        			FeedbackService.Rating.getValue(feedBackPayloadData.getNumberOfStars()),
        			feedBackPayloadData.getMessage(),
                    investorEmail);
    }

    protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage) {
        storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
    }
    
    @RequestMapping(value = "/my-sagia/license/person-shareholder-form1", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getPersonShareholderForm(final Model model, final RedirectAttributes redirectModel,
    			@RequestParam(value = "code", required = false, defaultValue = "") String code) 
    					throws CMSItemNotFoundException {
        PersonShareholderData shareholderData;
        boolean edit = false;

        if (!StringUtils.isEmpty(code)) {
            shareholderData = sagiaLicenseApplyFacade.getPersonShareholderByCode(code);
            edit = true;
        } else {
            shareholderData = new PersonShareholderData();
        }

        if (shareholderData != null) {
            model.addAttribute("sagiaApplyPersonShareholderForm", shareholderData);
            if (edit) {
                model.addAttribute("editOrError", edit);
            }
        } else {
            model.addAttribute("sagiaApplyPersonShareholderForm", new PersonShareholderData());
        }

        return ControllerConstants.Views.Fragments.License.LicenseApplyPersonShareholderForm;
    }
}
