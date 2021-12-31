/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import com.investsaudi.portal.core.model.InvestSaudiAboutSaudiOurValuesContainerModel;
import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.acceleratorcms.model.components.CMSTabParagraphContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Controller for InvestSaudiComponentContainer. 
 * This controller is used for displaying the container own page
 */
@Controller("InvestSaudiAboutSaudiOurValuesContainerController")
@RequestMapping("/view/InvestSaudiAboutSaudiOurValuesContainerController")
public class InvestSaudiAboutSaudiOurValuesContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiAboutSaudiOurValuesContainerModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiAboutSaudiOurValuesContainerModel component)
	{
		List<SimpleCMSComponentModel> simpleCMSComponents = new ArrayList<>();
        simpleCMSComponents.addAll(component.getSimpleCMSComponents());
		model.addAttribute("components", simpleCMSComponents);
		model.addAttribute("title", component.getTitle());
	}
}
