/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.investsaudi.portal.core.model.InvestSaudiContactUsComponentModel;
import com.investsaudi.portal.facades.contact.ContactTicketFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaSectorFacade;
import de.hybris.platform.servicelayer.exceptions.AttributeNotSupportedException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import de.hybris.platform.commercefacades.product.data.ProductData; 

/**
 * Controller for InvestSaudiWhyContainer. This controller is used for displaying the container own page
 */

@Controller("InvestSaudiPartnerContactUsComponentController")
@RequestMapping("/view/InvestSaudiPartnerContactUsComponentController")
public class InvestSaudiPartnerContactUsComponentController extends InvestSaudiContactUsComponentController {

    @Resource(name = "sagiaSectorFacade")
    private SagiaSectorFacade sagiaSectorFacade;


    @Override
    protected void fillModel(final HttpServletRequest request, final Model model,
                             final InvestSaudiContactUsComponentModel component) {
        super.fillModel(request, model, component);
    	 model.addAttribute("sectors", sagiaSectorFacade.getSectorsList());
    }
}
