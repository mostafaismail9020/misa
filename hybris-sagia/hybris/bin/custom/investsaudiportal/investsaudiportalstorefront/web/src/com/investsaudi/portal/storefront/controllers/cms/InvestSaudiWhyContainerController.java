/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiWhyContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for InvestSaudiWhyContainer. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiWhyContainerController")
@RequestMapping("/view/InvestSaudiWhyContainerController")
public class InvestSaudiWhyContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiWhyContainerModel> {

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiWhyContainerModel component) {
        model.addAttribute("components", component.getSimpleCMSComponents());
    }
}
