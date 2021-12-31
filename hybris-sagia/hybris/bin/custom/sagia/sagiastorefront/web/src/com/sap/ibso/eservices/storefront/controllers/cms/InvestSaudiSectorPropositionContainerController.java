/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiSectorPropositionContainerModel;
import com.investsaudi.portal.core.model.InvestSaudiWhyDetailContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for InvestSaudiWhyDetailContainer. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiSectorPropositionContainerController")
@RequestMapping("/view/InvestSaudiSectorPropositionContainerController")
public class InvestSaudiSectorPropositionContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiSectorPropositionContainerModel> {

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiSectorPropositionContainerModel component) {
        model.addAttribute("components", component.getSimpleCMSComponents());
    }
}
