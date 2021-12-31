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
package com.sap.ibso.eservices.storefront.interceptors;

import com.sap.ibso.eservices.facades.sagia.SagiaAccountFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeViewHandler;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

/**
 * A postHandle HandlerInterceptor that runs a number of BeforeViewHandlers before the view is rendered.
 */
public class BeforeViewHandlerInterceptor extends HandlerInterceptorAdapter {
    private List<BeforeViewHandler> beforeViewHandlers;

    @Resource(name = "sagiaDashboardWithoutLicenseFacade")
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

    @Resource(name = "sagiaAccountFacade")
    private SagiaAccountFacade sagiaAccountFacade;

    @Value("${default.session.timeout}")
    protected String sessionTimeout;

    protected List<BeforeViewHandler> getBeforeViewHandlers() {
        return beforeViewHandlers;
    }

    @Required
    public void setBeforeViewHandlers(final List<BeforeViewHandler> beforeViewHandlers) {
        this.beforeViewHandlers = beforeViewHandlers;
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && !isIncludeRequest(request) && isSupportedView(modelAndView)) {
            for (final BeforeViewHandler beforeViewHandler : getBeforeViewHandlers()) {
                beforeViewHandler.beforeView(request, response, modelAndView);
            }
            Principal loggedInUser = request.getUserPrincipal();
            //on register page, multiple requests are made, which do not contain login word
            if (Objects.nonNull(loggedInUser) && isURLAllowed(request)) {
                modelAndView.addObject("hasLicense", sagiaDashboardWithoutLicenseFacade.hasLicense());
                modelAndView.addObject("hasAwaitingPayment", sagiaDashboardWithoutLicenseFacade.hasAwaitingPayment());
                //No using reference found on the storefront for below primaryContact
                //modelAndView.addObject("primaryContact", sagiaAccountFacade.getPrimaryContact());
            }
            modelAndView.addObject("sagiaSessionTimeout", sessionTimeout);
		}
	}

	private boolean isURLAllowed(final HttpServletRequest request) {
		String header = request.getHeader("x-requested-with");
		boolean isAjaxRequest = header != null && header.equalsIgnoreCase("XMLHttpRequest");
		return !request.getRequestURI().contains("/login") && !request.getRequestURI().contains("/cms/") && !isAjaxRequest;
	}

    private boolean isIncludeRequest(final HttpServletRequest request) {
        return request.getAttribute("javax.servlet.include.request_uri") != null;
    }

    private boolean isSupportedView(final ModelAndView modelAndView) {
        return modelAndView.getViewName() != null && !isRedirectView(modelAndView);
    }

    private boolean isRedirectView(final ModelAndView modelAndView) {
        final String viewName = modelAndView.getViewName();
        return viewName != null && viewName.startsWith("redirect:");
    }
}
