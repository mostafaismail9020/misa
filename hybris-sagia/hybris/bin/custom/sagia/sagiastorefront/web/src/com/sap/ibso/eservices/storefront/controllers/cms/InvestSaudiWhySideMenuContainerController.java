/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiWhySideMenuContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for InvestSaudiWhySideMenuContainer. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiWhySideMenuContainerController")
@RequestMapping("/view/InvestSaudiWhySideMenuContainerController")
public class InvestSaudiWhySideMenuContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiWhySideMenuContainerModel> {

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiWhySideMenuContainerModel component) {
        model.addAttribute("components", component.getSimpleCMSComponents());
    }
}
