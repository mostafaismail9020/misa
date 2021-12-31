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
package com.sap.ibso.eservices.storefront.filters;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.platform.acceleratorstorefrontcommons.history.BrowseHistory;
import de.hybris.platform.acceleratorstorefrontcommons.history.BrowseHistoryEntry;
import de.hybris.platform.cms2.misc.CMSFilter;
import de.hybris.platform.commercefacades.storesession.StoreSessionFacade;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;


/**
 * Filter that initializes the session for the sagiastorefront. This is a spring configured filter that is
 * executed by the PlatformFilterChain.
 */
public class StorefrontFilter extends OncePerRequestFilter {
    private static final String AJAX_REQUEST_HEADER_NAME = "X-Requested-With";
    private static final String ORIGINAL_REFERER = "originalReferer";
    private static final String HYBRISUSERNAME = "hybrisusername";
    private static final String HYBRISPASS = "hybrispassword";
    private static final String SECOND_PAGE_AUTHENTICATION_URL = "/login-second-step";
    private static final String SECOND_PAGE_AUTHETICATION_RETRIES = "SECOND_PAGE_AUTHETICATION_RETRIES";
    private static final String VERIFICATION_URL = "/verification";

    private StoreSessionFacade storeSessionFacade;
    private BrowseHistory browseHistory;
    private Set<String> refererExcludeUrlSet;
    private PathMatcher pathMatcher;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private static final Logger LOG = Logger.getLogger(StorefrontFilter.class);

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sagiaConfigurationFacade")
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager;

    private boolean isReferrerValid(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String referrer = request.getHeader("referer");
        if (referrer != null && referrer.trim().isEmpty()) {
            referrer = referrer.toLowerCase();
            if(!referrer.contains("sagia.local") && !referrer.contains("sagia.gov.sa")) {
                redirectStrategy.sendRedirect(request, response, "/logout");
                return false;
            }
        }
        return true;
    }

    @Override
    public void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        if(!isReferrerValid(request, response)) {
            return;
        }

        final HttpSession session = request.getSession();
        final String queryString = request.getQueryString();

        if (isSessionNotInitialized(session, queryString)) {
            initDefaults(request);
            markSessionInitialized(session);
        }

        if (isGetMethod(request)) {
            if (StringUtils.isBlank(request.getHeader(AJAX_REQUEST_HEADER_NAME)) && !isRequestPathExcluded(request)) {
                final String requestURL = request.getRequestURL().toString();
                session.setAttribute(ORIGINAL_REFERER, StringUtils.isNotBlank(queryString) ? (requestURL + "?" + queryString) : requestURL);
            }

            getBrowseHistory().addBrowseHistoryEntry(new BrowseHistoryEntry(request.getRequestURI(), null));
        }
        
         LOG.info("########## isAnonymousUser: "+userService.isAnonymousUser(userService.getCurrentUser()) + " ******** "+sagiaConfigurationFacade.isVerificationEnabled()+" &&&&& "+request.getRequestURI().contains("j_spring_security_check"));
        
        if (sagiaConfigurationFacade.isVerificationEnabled()) {
	        if (userService.isAnonymousUser(userService.getCurrentUser())) {
	            String requestUrl = request.getRequestURI();
	            if (requestUrl.contains("j_spring_security_check")) {
	                try {
	                    String username = request.getParameter("j_username");
	                    String password = request.getParameter("j_password");
	                    //validate user exists && try to authenticate
	                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	                    //set back anonymous user
	                    userService.setCurrentUser(userService.getAnonymousUser());
	                    session.setAttribute(HYBRISUSERNAME, username);
	                    session.setAttribute(HYBRISPASS, password);
	                    //session.setAttribute(SECOND_PAGE_AUTHETICATION_RETRIES, 0);
	                    redirectStrategy.sendRedirect(request, response, VERIFICATION_URL);
	                    return;
	                } catch (Exception e) {
	                    LOG.info(e.getMessage(), e);
	                }
	            }
	        }
        }

//        if (!sagiaConfigurationFacade.isEnableSmsService()) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        if (userService.isAnonymousUser(userService.getCurrentUser())) {
//            String requestUrl = request.getRequestURI();
//            if (requestUrl.contains("j_spring_security_check")) {
//                try {
//                    String username = request.getParameter("j_username");
//                    String password = request.getParameter("j_password");
//                    //validate user exists && try to authenticate
//                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//                    //set back anonymous user
//                    userService.setCurrentUser(userService.getAnonymousUser());
//                    session.setAttribute(HYBRISUSERNAME, username);
//                    session.setAttribute(HYBRISPASS, password);
//                    session.setAttribute(SECOND_PAGE_AUTHETICATION_RETRIES, 0);
//                    redirectStrategy.sendRedirect(request, response, SECOND_PAGE_AUTHENTICATION_URL);
//                    return;
//                } catch (Exception e) {
//                    LOG.info(e.getMessage(), e);
//                }
//            }
//        }

        filterChain.doFilter(request, response);
    }

    private boolean isGetMethod(final HttpServletRequest httpRequest) {
        return "GET".equalsIgnoreCase(httpRequest.getMethod());
    }

    protected boolean isRequestSecure(final HttpServletRequest httpRequest) {
        return httpRequest.isSecure();
    }

    private boolean isSessionNotInitialized(final HttpSession session, final String queryString) {
        return session.isNew() || StringUtils.contains(queryString, CMSFilter.CLEAR_CMSSITE_PARAM)
                || !isSessionInitialized(session);
    }

    private void initDefaults(final HttpServletRequest request) {
        getStoreSessionFacade().initializeSession(Collections.list(request.getLocales()));
    }

    protected StoreSessionFacade getStoreSessionFacade() {
        return storeSessionFacade;
    }

    @Required
    public void setStoreSessionFacade(final StoreSessionFacade storeSessionFacade) {
        this.storeSessionFacade = storeSessionFacade;
    }

    protected BrowseHistory getBrowseHistory() {
        return browseHistory;
    }

    @Required
    public void setBrowseHistory(final BrowseHistory browseHistory) {
        this.browseHistory = browseHistory;
    }

    private boolean isSessionInitialized(final HttpSession session) {
        return session.getAttribute(this.getClass().getName()) != null;
    }

    private void markSessionInitialized(final HttpSession session) {
        session.setAttribute(this.getClass().getName(), "initialized");
    }

    protected boolean isRequestPathExcluded(final HttpServletRequest request) {
        final Set<String> inputSet = getRefererExcludeUrlSet();
        final String servletPath = request.getServletPath();

        for (final String input : inputSet) {
            if (getPathMatcher().match(input, servletPath)) {
                return true;
            }
        }
        return false;
    }

    protected Set<String> getRefererExcludeUrlSet() {
        return refererExcludeUrlSet;
    }

    @Required
    public void setRefererExcludeUrlSet(final Set<String> refererExcludeUrlSet) {
        this.refererExcludeUrlSet = refererExcludeUrlSet;
    }

    protected PathMatcher getPathMatcher() {
        return pathMatcher;
    }

    @Required
    public void setPathMatcher(final PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }
}
