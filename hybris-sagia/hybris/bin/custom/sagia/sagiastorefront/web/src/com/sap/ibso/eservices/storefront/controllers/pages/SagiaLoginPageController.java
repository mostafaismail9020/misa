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
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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


/**
 * Login Controller. Handles login and register for the account flow.
 */
@Controller
@RequestMapping(value = "/login")
public class SagiaLoginPageController extends SagiaAbstractLoginPageController {
    private static final Logger LOGGER = LogManager.getLogger(SagiaLoginPageController.class);

    private static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

    private HttpSessionRequestCache httpSessionRequestCache;
    private static final String ENTITY_NAME = "NIPHeaderSet";

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
}
