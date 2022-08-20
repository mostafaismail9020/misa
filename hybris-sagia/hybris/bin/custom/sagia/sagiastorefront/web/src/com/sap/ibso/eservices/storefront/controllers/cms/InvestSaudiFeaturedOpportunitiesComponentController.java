/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiFeaturedOpportunitiesComponentModel;
import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.OpportunityData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
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

    @Resource(name = "productSearchFacade")
    private ProductSearchFacade<ProductData> productSearchFacade;

    private static final String CHAR = "/";
    private static final String SOLR_PRFIX_QUERY = "all:name-asc:allCategories:";

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiFeaturedOpportunitiesComponentModel component) 
    {
    	List<OpportunityData> featuredOpportunities = null;
        List<OpportunityData> opportunityDataList = null;
        ProductSearchPageData<SearchStateData, ProductData> searchPageData = null;
    	
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
            /*Removed Old DB Query Logic */
//            featuredOpportunities = investSaudiProductFacade.getFeaturedOpportunitiesByCategory(component.getNumberItensDisplayed(), categoryCode);
            model.addAttribute("isCategoryPage", true);
            searchPageData = performSearch(null,0,null,null,0,categoryCode);

            opportunityDataList = new ArrayList<>();
            for (ProductData productData : searchPageData.getResults()) {
                opportunityDataList.add(createOpportunityData(productData));
            }
        }

        model.addAttribute("featuredOpportunities", opportunityDataList);
        model.addAttribute("searchPageData", searchPageData);
        model.addAttribute("category", categoryForCode);
    }


    protected ProductSearchPageData<SearchStateData, ProductData> performSearch(final String searchQuery, final int page,
                                                                                final AbstractSearchPageController.ShowMode showMode, final String sortCode, final int pageSize, String categoryCode)
    {
        final PageableData pageableData = new PageableData();
        pageableData.setCurrentPage(0);
        pageableData.setSort(null);
        pageableData.setPageSize(3);

        final SearchStateData searchState = new SearchStateData();
        final SearchQueryData searchQueryData = new SearchQueryData();
        searchQueryData.setValue(SOLR_PRFIX_QUERY+categoryCode);
        searchState.setQuery(searchQueryData);

        return productSearchFacade.textSearch(searchState, pageableData);
    }

    private OpportunityData createOpportunityData(ProductData productData) {
        OpportunityData opportunityData = new OpportunityData();
        opportunityData.setOpportunity(productData);
        if(productData.getParentCategory() != null)
        {
            opportunityData.setParentCategory(investSaudiCategoryFacade.getCategoryForCode(productData.getParentCategory()));
        }
        return opportunityData;
    }
}
