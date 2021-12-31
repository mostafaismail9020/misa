/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sagia.investsaudi.portal.core.controller;

import static com.sagia.investsaudi.portal.core.constants.SagiainvestsaudistoreConstants.PLATFORM_LOGO_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sagia.investsaudi.portal.core.service.SagiainvestsaudistoreService;


@Controller
public class SagiainvestsaudistoreHelloController
{
	@Autowired
	private SagiainvestsaudistoreService sagiainvestsaudistoreService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model)
	{
		model.addAttribute("logoUrl", sagiainvestsaudistoreService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "welcome";
	}
}
