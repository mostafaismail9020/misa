/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.controllers.pages;

import de.hybris.platform.acceleratorservices.config.SiteConfigService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * This controller responsible to render captcha widget
 */
@Controller
@RequestMapping(value = "/register/captcha")
public class CaptchaPageController
{
	private static final String RECAPTCHA_SITE_KEY_PROPERTY = "recaptcha.publickey";

	@Autowired
	private SiteConfigService siteConfigService;

	@Autowired
	private BaseStoreService baseStoreService;

	@RequestMapping(value = "/widget/{widgetName:.*}", method = RequestMethod.GET)
	public String getWidget(@PathVariable("widgetName") final String widgetName, final Model model,
			final HttpServletRequest request)
	{
		request.setAttribute("captchaEnabledForCurrentStore", isCaptchaEnabledForCurrentStore());
		request.setAttribute("recaptchaPublicKey", siteConfigService.getProperty(RECAPTCHA_SITE_KEY_PROPERTY));
		return "addon:/investsaudicaptchaddon/pages/widget/" + widgetName;
	}

	protected boolean isCaptchaEnabledForCurrentStore()
	{
		final BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();
		return currentBaseStore != null && Boolean.TRUE.equals(currentBaseStore.getCaptchaCheckEnabled());
	}
}
