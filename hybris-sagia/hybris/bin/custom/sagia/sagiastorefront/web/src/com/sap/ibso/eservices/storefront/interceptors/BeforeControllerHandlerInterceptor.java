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

import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaNavigationMenuFacade;
import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeControllerHandler;
import de.hybris.platform.servicelayer.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A postHandle HandlerInterceptor that runs a number of BeforeViewHandlers before the view is rendered.
 */
public class BeforeControllerHandlerInterceptor extends HandlerInterceptorAdapter
{
	private static final String INTERCEPTOR_ONCE_KEY = BeforeControllerHandlerInterceptor.class.getName();

	private List<BeforeControllerHandler> beforeControllerHandlers;
	private static final String MENU_DATA = "sagiaMenuData";
	private static final String NAVCATEGORIES = "navcategories";
	private static final String NAVSERVICES = "navservices";

	@Autowired
	private SagiaNavigationMenuFacade sagiaNavigationMenuFacade;

	@Autowired
	private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

	@Autowired
	private SessionService sessionService;

	protected List<BeforeControllerHandler> getBeforeControllerHandlers()
	{
		return beforeControllerHandlers;
	}

	@Required
	public void setBeforeControllerHandlers(final List<BeforeControllerHandler> beforeControllerHandlers)
	{
		this.beforeControllerHandlers = beforeControllerHandlers;
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception
	{
		if (request.getAttribute(INTERCEPTOR_ONCE_KEY) == null)
		{
			// Set the flag so that we are not executed multiple times
			request.setAttribute(INTERCEPTOR_ONCE_KEY, Boolean.TRUE);

			final HandlerMethod handlerMethod = (HandlerMethod) handler;

			// Call the pre handler once for the request
			for (final BeforeControllerHandler beforeControllerHandler : getBeforeControllerHandlers())
			{
				if (!beforeControllerHandler.beforeController(request, response, handlerMethod))
				{
					// Return false immediately if a handler returns false
					return false;
				}
			}
		}
		//updateMenuData(); Avoid unnecessary ODATA calls

		return true;
	}
	private void updateMenuData() {
		Map<String, Object> menuData = new HashMap<>();
		boolean hasLicense = sagiaDashboardWithoutLicenseFacade.hasLicense();
		if(hasLicense) {
			menuData.put(NAVCATEGORIES, sagiaNavigationMenuFacade.getNavigationMenuCategories());
			menuData.put(NAVSERVICES, sagiaNavigationMenuFacade.getNavigationMenuServices());
		}
		sessionService.setAttribute(MENU_DATA, menuData);
	}
}
