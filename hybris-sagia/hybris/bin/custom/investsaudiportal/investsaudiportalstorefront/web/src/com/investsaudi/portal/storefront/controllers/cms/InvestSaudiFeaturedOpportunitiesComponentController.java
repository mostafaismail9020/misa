/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiFeaturedOpportunitiesComponentModel;
import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;

import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.OpportunityData;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UrlPathHelper;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for InvestSaudiFeaturedOpportunitiesComponent. This controller is used for displaying the container own page
 */

@Controller("InvestSaudiFeaturedOpportunitiesComponentController")
@RequestMapping("/view/InvestSaudiFeaturedOpportunitiesComponentController")
public class InvestSaudiFeaturedOpportunitiesComponentController extends AbstractAcceleratorCMSComponentController<InvestSaudiFeaturedOpportunitiesComponentModel> {

    @Resource
    private InvestSaudiProductFacade investSaudiProductFacade;

    @Resource
    private InvestSaudiCategoryFacade investSaudiCategoryFacade;

    private static final String CHAR = "/";

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiFeaturedOpportunitiesComponentModel component) {

        String path = new UrlPathHelper().getOriginatingServletPath(request);
        String categoryCode = path.substring(path.lastIndexOf(CHAR) + 1);
        List<OpportunityData> featuredOpportunities;

        final CategoryData categoryForCode = investSaudiCategoryFacade.getCategoryForCode(categoryCode);

        if(categoryForCode !=  null)
        {
            featuredOpportunities = investSaudiProductFacade.getFeaturedOpportunitiesByCategory(component.getNumberItensDisplayed(), categoryCode);
            model.addAttribute("isCategoryPage", true);
        }
        else
        {
            featuredOpportunities = investSaudiProductFacade.getFeaturedOpportunities(component.getNumberItensDisplayed());
        }
        model.addAttribute("featuredOpportunities", featuredOpportunities);
    }

}
