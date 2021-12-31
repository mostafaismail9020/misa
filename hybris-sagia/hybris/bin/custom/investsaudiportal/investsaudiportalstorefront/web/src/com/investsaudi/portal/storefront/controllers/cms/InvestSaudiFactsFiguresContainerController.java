/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiFactsFiguresContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for InvestSaudiFactsFiguresContainerController. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiFactsFiguresContainerController")
@RequestMapping("/view/InvestSaudiFactsFiguresContainerController")
public class InvestSaudiFactsFiguresContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiFactsFiguresContainerModel> {
    
	@Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiFactsFiguresContainerModel component) {
        model.addAttribute("components", component.getSimpleCMSComponents());
    }
}
