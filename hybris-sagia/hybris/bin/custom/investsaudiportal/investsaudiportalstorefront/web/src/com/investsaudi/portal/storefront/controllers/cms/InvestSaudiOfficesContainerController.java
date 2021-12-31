/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiOfficesContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for InvestSaudiOfficesContainerController. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiOfficesContainerController")
@RequestMapping("/view/InvestSaudiOfficesContainerController")
public class InvestSaudiOfficesContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiOfficesContainerModel> {
    
	@Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiOfficesContainerModel component) {
        model.addAttribute("components", component.getSimpleCMSComponents());
    }
}
