/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiFeaturedOpportunitiesComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiSuccessStoriesComponentModel;
import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.OpportunityData;
import de.hybris.platform.commercefacades.product.data.SuccessStoryData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UrlPathHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller for InvestSaudiFeaturedOpportunitiesComponent. This controller is used for displaying the container own page
 */

@Controller("InvestSaudiSuccessStoriesComponentController")
@RequestMapping("/view/InvestSaudiSuccessStoriesComponentController")
public class InvestSaudiSuccessStoriesComponentController extends AbstractAcceleratorCMSComponentController<InvestSaudiSuccessStoriesComponentModel> {

    @Resource
    private InvestSaudiProductFacade investSaudiProductFacade;

    @Resource
    private InvestSaudiCategoryFacade investSaudiCategoryFacade;

    private static final String CHAR = "/";

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiSuccessStoriesComponentModel component) {

        String path = new UrlPathHelper().getOriginatingServletPath(request);
        String categoryCode = path.substring(path.lastIndexOf(CHAR) + 1);
        List<SuccessStoryData> successStories;

        final CategoryData categoryForCode = investSaudiCategoryFacade.getCategoryForCode(categoryCode);

        if(categoryForCode !=  null)
        {
            successStories = investSaudiProductFacade.getSuccessStoriesByCategory(component.getNumberItensDisplayed(), categoryCode);
            model.addAttribute("isCategoryPage", true);
        }
        else
        {
            successStories = investSaudiProductFacade.getSuccessStories(component.getNumberItensDisplayed());
        }
        model.addAttribute("successStories", successStories);
    }

}
