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
package com.sap.ibso.eservices.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.ForgottenPasswordProcessModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Velocity context for a forgotten password email.
 */
public class ForgottenPasswordEmailContext extends CustomerEmailContext {
    private static final String LOGIN_PW_CHANGE = "/login/pw/change";
    private static final String LOGIN_PW_REQUEST_EXTERNAL = "/login/pw/request/external";
    private int expiresInMinutes = 30;
    private String token;

    /**
     * @return
     */
    public int getExpiresInMinutes() {
        return expiresInMinutes;
    }

    /**
     * @param expiresInMinutes
     */
    public void setExpiresInMinutes(final int expiresInMinutes) {
        this.expiresInMinutes = expiresInMinutes;
    }

    /**
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(final String token) {
        this.token = token;
    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getURLEncodedToken() throws UnsupportedEncodingException {
        return URLEncoder.encode(token, "UTF-8");
    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getRequestResetPasswordUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), false, LOGIN_PW_REQUEST_EXTERNAL);
    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getSecureRequestResetPasswordUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), true, LOGIN_PW_REQUEST_EXTERNAL);
    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getResetPasswordUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), false, LOGIN_PW_CHANGE,
                "token=" + getURLEncodedToken());
    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getSecureResetPasswordUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), true, LOGIN_PW_CHANGE,
                "token=" + getURLEncodedToken() + (getBaseSite() != null && StringUtils.isNotEmpty(getBaseSite().getUid()) ? "&site=" + getBaseSite().getUid(): ""));
    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getDisplayResetPasswordUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), false, LOGIN_PW_CHANGE,
                "token=" + getURLEncodedToken());
    }

    /**
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getDisplaySecureResetPasswordUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), true, LOGIN_PW_CHANGE,
                "token=" + getURLEncodedToken()  + (getBaseSite() != null && StringUtils.isNotEmpty(getBaseSite().getUid()) ? "&site=" + getBaseSite().getUid(): ""));
    }

    @Override
    public void init(final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel, final EmailPageModel emailPageModel) {
        super.init(storeFrontCustomerProcessModel, emailPageModel);

        put("textAlign", StringUtils.equalsIgnoreCase(getEmailLanguage(storeFrontCustomerProcessModel).getIsocode(), "en") ? "left" : "right");
        put("textDirection", StringUtils.equalsIgnoreCase(getEmailLanguage(storeFrontCustomerProcessModel).getIsocode(), "en") ? "ltr" : "rtl");

        if (storeFrontCustomerProcessModel instanceof ForgottenPasswordProcessModel) {
            setToken(((ForgottenPasswordProcessModel) storeFrontCustomerProcessModel).getToken());
        }
    }
}
