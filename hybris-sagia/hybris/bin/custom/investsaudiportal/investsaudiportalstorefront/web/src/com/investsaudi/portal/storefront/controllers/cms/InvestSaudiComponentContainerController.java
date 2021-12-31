/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import com.investsaudi.portal.core.model.InvestSaudiComponentContainerModel;
import com.investsaudi.portal.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorcms.model.components.CMSTabParagraphContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for InvestSaudiComponentContainer. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiComponentContainer")
@RequestMapping("/view/InvestSaudiComponentContainerController")
public class InvestSaudiComponentContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiComponentContainerModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiComponentContainerModel component)
	{
		model.addAttribute("components", component.getSimpleCMSComponents());
	}
}
