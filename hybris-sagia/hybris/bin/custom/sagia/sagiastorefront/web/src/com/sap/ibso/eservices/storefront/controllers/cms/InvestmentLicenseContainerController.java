/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import com.investsaudi.portal.core.model.InvestmentLicenseContainerModel;

import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.acceleratorcms.model.components.CMSTabParagraphContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for InvestSaudiComponentContainer. 
 * This controller is used for displaying the container own page
 */
@Controller("InvestmentLicenseContainerController")
@RequestMapping("/view/InvestmentLicenseContainerController")
public class InvestmentLicenseContainerController extends AbstractAcceleratorCMSComponentController<InvestmentLicenseContainerModel>
{
	private static final Logger LOG = LoggerFactory.getLogger(InvestmentLicenseContainerController.class);
	
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final InvestmentLicenseContainerModel component)
	{
		LOG.debug("---------InvestmentLicenseContainerController---------------");
		
		List<SimpleCMSComponentModel> simpleCMSComponents = new ArrayList<>();
        simpleCMSComponents.addAll(component.getSimpleCMSComponents());
		model.addAttribute("components", simpleCMSComponents);
		model.addAttribute("title", component.getTitle());
		model.addAttribute("longDescription", component.getLongDescription());
	}
}
