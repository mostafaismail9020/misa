/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiSiteMapComponentModel;
import com.investsaudi.portal.facades.sitemap.InvestSaudiSiteMapFacade;
import de.hybris.platform.commercefacades.product.data.SiteMapItemData;
import de.hybris.platform.servicelayer.exceptions.AttributeNotSupportedException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Controller for InvestSaudiSiteMapComponentController. This controller is used for displaying the container own page
 */

@Controller("InvestSaudiSiteMapComponentController")
@RequestMapping("/view/InvestSaudiSiteMapComponentController")
public class InvestSaudiSiteMapComponentController extends AbstractAcceleratorCMSComponentController<InvestSaudiSiteMapComponentModel> {

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource
    private InvestSaudiSiteMapFacade investSaudiSiteMapFacade;

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiSiteMapComponentModel component) {

        for (final String property : getCmsComponentService().getReadableEditorProperties(component)) {
            try {
                final Object value = modelService.getAttributeValue(component, property);
                model.addAttribute(property, value);
            } catch (final AttributeNotSupportedException ignore) {
                // ignore
            }
        }
        model.addAttribute("mapSiteMapItemData", investSaudiSiteMapFacade.buildSiteMapHierarchy(concatAllPages()));
    }

    /**
     * @param modelService the modelService to set
     */
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    private List<SiteMapItemData> concatAllPages() {

        List<SiteMapItemData> pages = investSaudiSiteMapFacade.getAllActiveContentPagesForCatalogVersion();
        pages.addAll(investSaudiSiteMapFacade.getAllActiveCategoryPagesForCatalogVersion());
        pages.addAll(investSaudiSiteMapFacade.getAllActiveProductPagesForCatalogVersion());

        return pages;
    }
}
