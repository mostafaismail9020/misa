/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiSectorsComponentModel;
import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.servicelayer.exceptions.AttributeNotSupportedException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

/**
 * Controller for InvestSaudiSectorsComponent. This controller is used for displaying the container own page
 */

@Controller("InvestSaudiSectorsComponentController")
@RequestMapping("/view/InvestSaudiSectorsComponentController")
public class InvestSaudiSectorsComponentController extends AbstractAcceleratorCMSComponentController<InvestSaudiSectorsComponentModel> {

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource
    private InvestSaudiCategoryFacade investSaudiCategoryFacade;

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiSectorsComponentModel component) {

        // See documentation for CMSComponentService.getReadableEditorProperties, but this will return all frontend
        // properties which we just inject into the model.


        populateCategory(component);

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

    private void populateCategory(InvestSaudiSectorsComponentModel component) {

        String categoryCode = component.getCategory().getCode();
        emptyIfNull(investSaudiCategoryFacade.getAllCategoriesForCategoryCode(categoryCode)).stream().findFirst().ifPresent(component::setCategory);
    }
}
