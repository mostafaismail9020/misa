/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiWebinarVideoContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for InvestSaudiWebinarVideoContainer. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiWebinarVideoContainerController")
@RequestMapping("/view/InvestSaudiWebinarVideoContainerController")
public class InvestSaudiWebinarVideoContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiWebinarVideoContainerModel> {

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiWebinarVideoContainerModel component) {
        model.addAttribute("components", component.getSimpleCMSComponents());
    }
}
