/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiFeaturedOpportunitiesComponentModel;
import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;

import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.OpportunityData;
import de.hybris.platform.util.Config;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller for InvestSaudiFeaturedOpportunitiesComponent. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiFeaturedOpportunitiesComponentController")
@RequestMapping("/view/InvestSaudiFeaturedOpportunitiesComponentController")
public class InvestSaudiFeaturedOpportunitiesComponentController extends 
			AbstractAcceleratorCMSComponentController<InvestSaudiFeaturedOpportunitiesComponentModel> 
{
	private static final Logger LOG = LoggerFactory.getLogger(InvestSaudiFeaturedOpportunitiesComponentController.class);
	
    @Resource
    private InvestSaudiProductFacade investSaudiProductFacade;

    @Resource
    private InvestSaudiCategoryFacade investSaudiCategoryFacade;

    private static final String CHAR = "/";

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiFeaturedOpportunitiesComponentModel component) 
    {
    	List<OpportunityData> featuredOpportunities = null;
    	
        String path = new UrlPathHelper().getOriginatingServletPath(request);
        String categoryCode = path.substring(path.lastIndexOf(CHAR) + 1);
        
        LOG.debug("---InvestSaudiFeaturedOpportunitiesComponentController: categoryCode=" + categoryCode);
        
        final String parentSector = Config.getString("parent.sector", "sector-opportunities");
        
        final CategoryData categoryForCode = investSaudiCategoryFacade.getCategoryForCode(categoryCode);        
        if(categoryForCode !=  null)
        {
        	String parentCategoryCode = categoryForCode.getParentCategoryCode();
        	if (null != parentCategoryCode && !parentCategoryCode.equals(parentSector)) {
        		categoryCode = parentCategoryCode;
        	}
        	
            featuredOpportunities = investSaudiProductFacade.getFeaturedOpportunitiesByCategory(component.getNumberItensDisplayed(), categoryCode);
            model.addAttribute("isCategoryPage", true);
        }
               
        model.addAttribute("featuredOpportunities", featuredOpportunities);
        model.addAttribute("category", categoryForCode);
    }
}
