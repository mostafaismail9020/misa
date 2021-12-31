/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiNumberTalkCarouselContainerModel;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller for InvestSaudiNumberTalkCarouselContainer. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiNumberTalkCarouselContainerController")
@RequestMapping("/view/InvestSaudiNumberTalkCarouselContainerController")
public class InvestSaudiNumberTalkCarouselContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiNumberTalkCarouselContainerModel> {
    
	@Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiNumberTalkCarouselContainerModel component) {

        List<SimpleCMSComponentModel> simpleCMSComponents = new ArrayList<>();
        simpleCMSComponents.addAll(component.getSimpleCMSComponents());
        Collections.shuffle(simpleCMSComponents);
        model.addAttribute("components", simpleCMSComponents);
    }
}
