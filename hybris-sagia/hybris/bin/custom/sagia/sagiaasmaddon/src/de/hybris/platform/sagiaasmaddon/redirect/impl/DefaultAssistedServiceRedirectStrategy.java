/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.sagiaasmaddon.redirect.impl;

import de.hybris.platform.assistedservicefacades.constants.AssistedservicefacadesConstants;
import de.hybris.platform.assistedserviceservices.AssistedServiceService;
import de.hybris.platform.commerceservices.util.ResponsiveUtils;
import de.hybris.platform.order.CartService;
import de.hybris.platform.sagiaasmaddon.constants.SagiaasmaddonConstants;
import de.hybris.platform.sagiaasmaddon.redirect.AssistedServiceRedirectStrategy;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Default property based implementation for {@link AssistedServiceRedirectStrategy}
 */
public class DefaultAssistedServiceRedirectStrategy implements AssistedServiceRedirectStrategy {
    private CartService cartService;
    private UserService userService;
    private SessionService sessionService;
    private AssistedServiceService assistedServiceService;

    private static final String DEFAULT_ERROR_REDIRECT = "/";
    private static final String DEFAULT_CART_REDIRECT = "/cart";
    private static final String DEFAULT_CUSTOMER_REDIRECT = "/my-account";
    private static final String DEFAULT_ORDER_REDIRECT = "/my-account/order/%s";
    private static final String REDIRECT_PARAMETER = "fwd";

    @Override
    public String getRedirectPath() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        final String asmRedirectParameter = getAssistedServiceService().getAsmSession().getForwardUrl();
        if (request != null && StringUtils.isNotBlank(request.getParameter(REDIRECT_PARAMETER))) {
            final String fwd = request.getParameter(REDIRECT_PARAMETER);
            return fwd.startsWith("/") ? fwd : ("/" + fwd);
        } else if (StringUtils.isNotBlank(asmRedirectParameter)) {
            //remove the fwd url after its being redirected.
            getAssistedServiceService().getAsmSession().setForwardUrl(null);
            return asmRedirectParameter.startsWith("/") ? asmRedirectParameter : ("/" + asmRedirectParameter);
        }

        final String redirectOrderId = sessionService.getCurrentSession().getAttribute(AssistedservicefacadesConstants.ASM_ORDER_ID_TO_REDIRECT);
        if (redirectOrderId == null) {
            return getPathWithoutRedirectOrder();
        } else {
            sessionService.getCurrentSession().removeAttribute(AssistedservicefacadesConstants.ASM_ORDER_ID_TO_REDIRECT);
            return getPathWithOrder(redirectOrderId);
        }
    }

    private String getPathWithoutRedirectOrder() {
        if (getCartService().getSessionCart() != null && CollectionUtils.isNotEmpty(getCartService().getSessionCart().getEntries())) {
            return getPathWithCart();
        } else if (!ResponsiveUtils.isResponsive() && !getUserService().isAnonymousUser(getUserService().getCurrentUser())) {
            return getPathCustomerOnly();
        }
        return "/";
    }

    @Override
    public String getErrorRedirectPath() {
        return Config.getString(SagiaasmaddonConstants.REDIRECT_ERROR, DEFAULT_ERROR_REDIRECT);
    }

    protected String getPathWithCart() {
        return Config.getString(SagiaasmaddonConstants.REDIRECT_WITH_CART, DEFAULT_CART_REDIRECT);
    }

    protected String getPathCustomerOnly() {
        return Config.getString(SagiaasmaddonConstants.REDIRECT_CUSTOMER_ONLY, DEFAULT_CUSTOMER_REDIRECT);
    }

    protected String getPathWithOrder(final String redirectOrderId) {
        return String.format(Config.getString(SagiaasmaddonConstants.REDIRECT_WITH_ORDER, DEFAULT_ORDER_REDIRECT),
                redirectOrderId);
    }

    /**
     * @return the cartService
     */
    protected CartService getCartService() {
        return cartService;
    }

    /**
     * @param cartService the cartService to set
     */
    @Required
    public void setCartService(final CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * @return the userService
     */
    protected UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    @Required
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * @return the sessionService
     */
    protected SessionService getSessionService() {
        return sessionService;
    }

    /**
     * @param sessionService the sessionService to set
     */
    @Required
    public void setSessionService(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    protected AssistedServiceService getAssistedServiceService() {
        return assistedServiceService;
    }

    @Required
    public void setAssistedServiceService(final AssistedServiceService assistedServiceService) {
        this.assistedServiceService = assistedServiceService;
    }


}