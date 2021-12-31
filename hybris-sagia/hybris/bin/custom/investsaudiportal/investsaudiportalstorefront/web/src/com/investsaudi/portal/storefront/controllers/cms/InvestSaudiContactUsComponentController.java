/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.cms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.investsaudi.portal.core.model.InvestSaudiContactUsComponentModel;
import com.investsaudi.portal.facades.contact.ContactTicketFacade;
import de.hybris.platform.servicelayer.exceptions.AttributeNotSupportedException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for InvestSaudiWhyContainer. This controller is used for displaying the container own page
 */

@Controller("InvestSaudiContactUsComponentController")
@RequestMapping("/view/InvestSaudiContactUsComponentController")
public class InvestSaudiContactUsComponentController
    extends AbstractAcceleratorCMSComponentController<InvestSaudiContactUsComponentModel> {

    @Resource
    private ContactTicketFacade contactTicketFacade;

    @Resource(name = "modelService")
    private ModelService modelService;

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model,
                             final InvestSaudiContactUsComponentModel component) {

        // See documentation for CMSComponentService.getReadableEditorProperties, but this will return all frontend
        // properties which we just inject into the model.

        model.addAttribute("contactSubjects", contactTicketFacade.getContactTicketSubjects());

        String categoryName = (String) request.getAttribute("categoryName");
        String productName = (String) request.getAttribute("productName");
        String sectorOrProduct = StringUtils.defaultString(categoryName, productName);

        model.addAttribute("sectorOrProduct", sectorOrProduct);
        model.addAttribute("componentTitle", StringUtils.replace(component.getTitle(), "{sector-opportunity}", StringUtils.defaultString(sectorOrProduct)));

        for (final String property : getCmsComponentService().getReadableEditorProperties(component)) {
            try {
                final Object value = modelService.getAttributeValue(component, property);
                model.addAttribute(property, value);
            } catch (final AttributeNotSupportedException ignore) {
                // ignore
            }
        }
    }

    /**
     * @param modelService the modelService to set
     */
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }
}
