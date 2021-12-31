/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import com.investsaudi.portal.core.model.InvestSaudiNumberTalkContainerModel;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller for InvestSaudiNumberTalkContainer. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiNumberTalkContainerController")
@RequestMapping("/view/InvestSaudiNumberTalkContainerController")
public class InvestSaudiNumberTalkContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiNumberTalkContainerModel> {
    
	@Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiNumberTalkContainerModel component) {

        List<SimpleCMSComponentModel> simpleCMSComponents = new ArrayList<>();
        simpleCMSComponents.addAll(component.getSimpleCMSComponents());
        Collections.shuffle(simpleCMSComponents);
        model.addAttribute("components", simpleCMSComponents.subList(0, Math.min(simpleCMSComponents.size(), 5)));
    }
}
