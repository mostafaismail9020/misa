/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiBannerContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for InvestSaudiBannerContainerController. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiBannerContainerController")
@RequestMapping("/view/InvestSaudiBannerContainerController")
public class InvestSaudiBannerContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiBannerContainerModel> {
    
	@Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiBannerContainerModel component) {
        model.addAttribute("components", component.getSimpleCMSComponents());
        model.addAttribute("title", component.getTitle());
    }
}
