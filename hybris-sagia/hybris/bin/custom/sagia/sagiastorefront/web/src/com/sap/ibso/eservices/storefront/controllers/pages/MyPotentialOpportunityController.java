package com.sap.ibso.eservices.storefront.controllers.pages;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sap.ibso.eservices.facades.potentialopportunity.MyPotentialOpportunityFacade;
import com.sap.ibso.eservices.facades.sagia.impl.SagiaVerificationFacade;
import com.sap.ibso.eservices.facades.user.SagiaUserFacade;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.sagiaservices.services.authentication.AuthenticationCodeGeneratorService;
import com.sap.ibso.eservices.sagiaservices.services.impl.EmailVerificationSetService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SmsService;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;

import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commerceservices.security.SecureTokenService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

@Controller
@RequestMapping(value = "/mypotentialopportunity")
public class MyPotentialOpportunityController extends SagiaAbstractPageController {

	private static final Logger LOG = Logger.getLogger(MyPotentialOpportunityController.class);
	
	private static final String MYPOTENTIAL_OPPORTUNITY_DASHBOARD_PAGE = "/myPotentialOpportunities/dashboard";
	
	private static final String MYPOTENTIAL_OPPORTUNITY_DETAILS_PAGE = "/myPotentialOpportunities/details";
	
	/*
	 * @Resource private UserService userService;
	 * 
	 * @Resource private AutoLoginStrategy autoLoginStrategy;
	 * 
	 * @Resource private SmsService smsService;
	 * 
	 * @Resource private ModelService modelService;
	 * 
	 * @Resource private SecureTokenService secureTokenService;
	 * 
	 * @Resource(name = "sagiaAuthenticateCodeFormValidator") private Validator
	 * sagiaAuthenticateCodeFormValidator;
	 * 
	 * @Resource(name = "defaultAuthenticationCodeGeneratorService") private
	 * AuthenticationCodeGeneratorService authenticationCodeGeneratorService;
	 * 
	 * @Resource(name = "sagiaConfigurationFacade") private SagiaConfigurationFacade
	 * sagiaConfigurationFacade;
	 * 
	 * @Resource(name = "emailVerificationSetService") private
	 * EmailVerificationSetService emailVerificationSetService;
	 * 
	 * @Resource private SagiaVerificationFacade sagiaVerificationFacade;
	 * 
	 * @Resource(name = "sagiaUserFacade") private SagiaUserFacade sagiaUserFacade;
	 * 
	 * 
	 * @Resource(name = "myPotentialOpportunityFacade") private
	 * MyPotentialOpportunityFacade myPotentialOpportunityFacade;
	 * 
	 */

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getMyPotentialOpportunityDashboard(final Model model) throws CMSItemNotFoundException {
		
		final ContentPageModel myPotentialOpportunityCMSPage = getContentPageForLabelOrId(MYPOTENTIAL_OPPORTUNITY_DASHBOARD_PAGE);
		storeCmsPageInModel(model, myPotentialOpportunityCMSPage);
		setUpMetaDataForContentPage(model, myPotentialOpportunityCMSPage);

		return getViewForPage(model);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String getMyPotentialOpportunityDetails(final Model model) throws CMSItemNotFoundException {
		
		final ContentPageModel myPotentialOpportunityCMSPage = getContentPageForLabelOrId(MYPOTENTIAL_OPPORTUNITY_DETAILS_PAGE);
		storeCmsPageInModel(model, myPotentialOpportunityCMSPage);
		setUpMetaDataForContentPage(model, myPotentialOpportunityCMSPage);

		return getViewForPage(model);
	}




}
