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

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sap.ibso.eservices.core.enums.NafathStatus;
import com.sap.ibso.eservices.core.jalo.SagiaLicense;
import com.sap.ibso.eservices.facades.data.NafathLoginData;
import com.sap.ibso.eservices.facades.sagia.NafathFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.cmsfacades.data.UserData;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetailsSource;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sap.ibso.eservices.facades.data.appintments.NationalInvestorAppointment;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractLoginPageController;
import com.sap.ibso.eservices.storefront.forms.NationalInvestorForm;
import com.sap.ibso.eservices.storefront.forms.SagiaRegisterForm;
import com.sap.ibso.eservices.storefront.security.OAuthUserAutoLoginStrategy;
import com.sap.ibso.eservices.storefront.security.SagiaAuthenticationException;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Login Controller. Handles login and register for the account flow.
 */
@Controller
@RequestMapping(value = "/login")
public class SagiaLoginPageController extends SagiaAbstractLoginPageController {
    private static final Logger LOGGER = LogManager.getLogger(SagiaLoginPageController.class);

    private static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";
    private static final String VERIFY_NAFATH_RANDOM_TEXT_CMS_PAGE = "verifyNafathRandomText";


    private HttpSessionRequestCache httpSessionRequestCache;
    private static final String ENTITY_NAME = "NIPHeaderSet";

    @Resource
    private AutoLoginStrategy autoLoginStrategy;

    @Resource
    private NafathFacade nafathFacade;

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource
    SagiaConfigurationFacade sagiaConfigurationFacade;

    @Resource
    private UserService userService;
    
	@Resource(name="sagiaOauth2AuthenticationManager")
	private AuthenticationManager authenticationManager;

	private TokenExtractor tokenExtractor = new BearerTokenExtractor();
	
	@Resource(name = "oauthUserAutoLoginStrategy")
	private OAuthUserAutoLoginStrategy oauthUserAutoLoginStrategy;
	
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new OAuth2AuthenticationDetailsSource();
	
	private boolean stateless = true;

    @Override
    protected String getView() {
        return ControllerConstants.Views.Pages.Account.AccountLoginPage;
    }

    @Override
    protected String getDefaultLoginPage(boolean loginError, HttpSession session, Model model) throws CMSItemNotFoundException {
        final LoginForm loginForm = new LoginForm();
        String backendRegex = sagiaConfigurationFacade.getPasswordRegex().replace("\\", "\\\\");
        String currentLanguage = getI18nService().getCurrentLocale().getLanguage();
        String backendRegexErrorMessage = sagiaConfigurationFacade.getPasswordErrorMessage(currentLanguage);
        model.addAttribute(loginForm);
        model.addAttribute(new GuestForm());
        model.addAttribute(new SagiaRegisterForm());
        model.addAttribute("nationalInvestorForm", new NationalInvestorForm());
        model.addAttribute("nationalInvestorAppointment", new NationalInvestorAppointment());
        model.addAttribute("backendRegexErrorMessage", backendRegexErrorMessage);
        model.addAttribute("backendRegex", backendRegex);
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));

        String username = (String) session.getAttribute(SPRING_SECURITY_LAST_USERNAME);
        if (username != null) {
            session.removeAttribute(SPRING_SECURITY_LAST_USERNAME);
            checkIfUserDisabled(model, username);
        }

        loginForm.setJ_username(username);
        storeCmsPageInModel(model, getCmsPage());
        setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.INDEX_NOFOLLOW);

        addRegistrationConsentDataToModel(model);

        final Breadcrumb loginBreadcrumbEntry = new Breadcrumb("#",
                getMessageSource().getMessage("header.link.login", null, "header.link.login", getI18nService().getCurrentLocale()),
                null);
        model.addAttribute("breadcrumbs", Collections.singletonList(loginBreadcrumbEntry));

        if (loginError) {
            model.addAttribute("loginError", loginError);

            Exception e = (Exception) session.getAttribute(SPRING_SECURITY_LAST_EXCEPTION);
            if (e instanceof SagiaAuthenticationException)
            {
                // Note: the exception message is already picked up from message resource bundle (see SagiaAuthenticationProvider)
                model.addAttribute("loginFormError", e.getMessage());
            }
            else
            {
                model.addAttribute("loginFormError", "login.error.account.not.found.title");
            }
        }
        return getView();
    }

    private void checkIfUserDisabled(Model model, String username) {
        try {
            if (username == null) {
                // Nothing to check
                return;
            }

            UserModel user = userService.getUserForUID(username.toLowerCase(Locale.ENGLISH));
            if (user != null && user.isLoginDisabled()) {
                model.addAttribute("disabledUser", true);
            }
        } catch (UnknownIdentifierException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Override
    protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response) {
        if (httpSessionRequestCache.getRequest(request, response) != null) {
            return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
        }
        return "/";
    }

    @Override
    protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException {
        return getContentPageForLabelOrId("login");
    }

    @Resource(name = "httpSessionRequestCache")
    public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache) {
        this.httpSessionRequestCache = accHttpSessionRequestCache;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doLogin(@RequestHeader(value = "referer", required = false) final String referer,
                          @RequestParam(value = "error", defaultValue = "false") final boolean loginError, final Model model,
                          final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
            throws CMSItemNotFoundException {
        if (!loginError) {
            storeReferer(referer, request, response);
        }
        return getDefaultLoginPage(loginError, session, model);
    }

    @RequestMapping(value = "/sso", method = RequestMethod.GET)
	public String doAutoLogin(@RequestParam(value = "access_token", required = true) final String oauthtoken,
			@RequestParam(value = "redirect", defaultValue = "/") final String url, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException
	{
	
		final boolean debug = LOGGER.isDebugEnabled();
		try {

			Authentication authentication = tokenExtractor.extract(request);
			
			if (authentication == null) {
				if (stateless && isAuthenticated()) {
					if (debug) {
						LOGGER.debug("Clearing security context.");
					}
					SecurityContextHolder.clearContext();
				}
				if (debug) {
					LOGGER.debug("No token in request, will continue chain.");
				}
			}
			else {
				request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE, authentication.getPrincipal());
				if (authentication instanceof AbstractAuthenticationToken) {
					AbstractAuthenticationToken needsDetails = (AbstractAuthenticationToken) authentication;
					needsDetails.setDetails(authenticationDetailsSource.buildDetails(request));
				}
				Authentication authResult = authenticationManager.authenticate(authentication);

				if (debug) {
					LOGGER.debug("Authentication success: " + authResult);
				}
				oauthUserAutoLoginStrategy.login(authResult.getPrincipal().toString(), request, response);
			}
		}
		catch (OAuth2Exception failed) {
			SecurityContextHolder.clearContext();
		}
		return "redirect:"+url;
	}
    
	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return false;
		}
		return true;
	}
	
    private void storeReferer(final String referer, final HttpServletRequest request, final HttpServletResponse response) {
        if (StringUtils.isNotBlank(referer) && !StringUtils.endsWith(referer, "/login")
                && StringUtils.contains(referer, request.getServerName())) {
            httpSessionRequestCache.saveRequest(request, response);
        }
    }

    //TODO: need to change this to POST
    @RequestMapping(value = "/nafathLogin", method = RequestMethod.GET)
    public String loginWithNafath(@RequestParam(value = "nationalID") final String nationalID, final Model model,
                                  final HttpServletRequest request, final HttpServletResponse response, final HttpSession session, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
        NafathLoginData loginData = nafathFacade.login(nationalID);

        //TODO: check the success response and set below value in the session if success
        getSessionService().setAttribute("nationalID", loginData.getNationalId());
        getSessionService().setAttribute("transactionID", loginData.getTransactionId());
        getSessionService().setAttribute("randomNafathText", loginData.getRandom());

        //TODO: on error response, return to the login page with custom message
        if (loginData.getStatus().equals(NafathStatus.REJECTED) || loginData.getStatus().equals(NafathStatus.EXPIRED)) {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,"nafath.login.error");
            return REDIRECT_PREFIX + "/login";
        }

        //TODO: return to success page
        return REDIRECT_PREFIX + "/login/verify";
    }


    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public String random(final Model model,
                         final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) throws CMSItemNotFoundException {
        String randomNafathText = getSessionService().getAttribute("randomNafathText");
        model.addAttribute("randomNafathText",randomNafathText);
        storeCmsPageInModel(model, getContentPageForLabelOrId(VERIFY_NAFATH_RANDOM_TEXT_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(VERIFY_NAFATH_RANDOM_TEXT_CMS_PAGE));
        return ControllerConstants.Views.Pages.Account.NafathVerifyRandomText;
    }

    //TODO: change JSON response
    @RequestMapping(value = "/checkNafathStatus", method = RequestMethod.GET)
    public String checkNafathStatus(final Model model,
                                    final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) {
        String transactionID = getSessionService().getAttribute("transactionID");
        String nationalID = getSessionService().getAttribute("nationalID");
        String randomNafathText = getSessionService().getAttribute("randomNafathText");
        NafathLoginData loginStatus = nafathFacade.checkStatus(transactionID,nationalID,randomNafathText);
        return loginStatus.getStatus().toString();
    }

    @RequestMapping(value = "/nafathLicenses", method = RequestMethod.GET)
    public String displayLicenses(final Model mode, final HttpSession session, final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel) {
        String nationalID = getSessionService().getAttribute("nationalID");
        String transactionId = getSessionService().getAttribute("transactionID");
        String randomNafathText = getSessionService().getAttribute("randomNafathText");
        NafathLoginData loginStatus = nafathFacade.checkStatus(transactionId,nationalID,randomNafathText);

        if (loginStatus.getStatus().equals(NafathStatus.COMPLETED)) {
            //TODO: call CRM API here
            List<SagiaLicense> licenseList = null;
            if (licenseList.size() == 1) {
                getSessionService().setAttribute("licenseList", licenseList);
                return doNafathLogin(licenseList.get(0).getCode(), request, response, redirectModel);
            } else {
                getSessionService().setAttribute("licenseList", licenseList);
                //TODO : redirect to license selection page here
                return "/loginSelectionUI";
            }
        }
        return REDIRECT_PREFIX + "/login/verify";
    }

    //TODO: change method to POST

    /**
     * When there are list of License, user will select one license and submit it
     */
    @RequestMapping(value = "/loginNafathUser", method = RequestMethod.GET)
    public String loginNafathUser(@RequestParam(value = "selectedLicense") final String selectedLicense, final Model mode, final HttpSession session, final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel) {
        return doNafathLogin(selectedLicense, request, response, redirectModel);
    }

    private String doNafathLogin(String selectedLicense, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectModel) {
        List<String> licenseList = getSessionService().getAttribute("licenseList");
        if(CollectionUtils.isNotEmpty(licenseList) && licenseList.contains(selectedLicense))
        {
            try {
                UserData user = nafathFacade.getUserForLicense(selectedLicense);
                oauthUserAutoLoginStrategy.login(user.getUid(), request, response);
            } catch (RuntimeException e) {
                return REDIRECT_PREFIX + "/login";
            }
            return REDIRECT_PREFIX + "/";
        }

        GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,"nafath.login.generic.error");
        return REDIRECT_PREFIX + "/login";
    }
}
