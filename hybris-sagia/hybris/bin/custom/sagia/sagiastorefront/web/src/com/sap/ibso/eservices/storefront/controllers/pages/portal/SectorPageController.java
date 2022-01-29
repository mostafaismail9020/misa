/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;
import com.sap.ibso.eservices.storefront.breadcrumb.SectorBreadcrumbBuilder;
import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCategoryPageController;
import de.hybris.platform.acceleratorstorefrontcommons.util.MetaSanitizerUtil;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.OpportunityData;
import de.hybris.platform.commercefacades.product.data.SuccessStoryData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.util.Config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Controller for a sector page
 */
@Controller
@RequestMapping(value = "/sectors-opportunities")
public class SectorPageController extends AbstractCategoryPageController {
	
	private static final Logger LOG = Logger.getLogger(SectorPageController.class);

    private static final String MULTIPLE_PARAMS_PAGE = "&page";

    private static final String SINGLE_PAGE = "page";
    
    private static final String ALL_SECTORS_DATA = "AllSectors";
    
    
	private static final String OPPORTUNITY_CODE_PATH_VARIABLE_PATTERN = "/{sectorCode}";

	private static final String SECTOR_PAGE = "layout/portalSector";

    private static final String SEARCH_OPPORTUNITY_PAGE = "/sectors-opportunities/opportunities";
  
    private static final String SECTORS_OPPORTUNITY_PAGE = "/sectors-opportunities";
    
    private static final String SECTOR_DETAILS_PAGE = "/sector-details";
    
    private static final String REGIONAL_HQ_PAGE = "/sectors-opportunities/regionalHQ";
    
    private static final String SEARCH_SUCCESS_STORIES_PAGE = "/sectors-opportunities/success-stories";

    
    @Autowired
    private SectorBreadcrumbBuilder sectorBreadcrumbBuilder;

    @Resource(name = "contentPageBreadcrumbBuilder")
    private ContentPageBreadcrumbBuilder contentPageBreadcrumbBuilder;

    @Resource
    private InvestSaudiProductFacade investSaudiProductFacade;

    @Resource
    private InvestSaudiCategoryFacade investSaudiCategoryFacade;

    @Resource
    private SessionService sessionService;
    
    /**
     * 
     * @param model
     * @param request
     * @param response
     * @return
     * @throws CMSItemNotFoundException
     */
	@RequestMapping(method = RequestMethod.GET)
    public String opportunitiesSearch(final Model model, final HttpServletRequest request, final HttpServletResponse response) 
    		throws CMSItemNotFoundException {
		
		final String parentSector = Config.getString("parent.sector", "sector-opportunities");
		
		Collection<CategoryData> mainCategories = investSaudiCategoryFacade.getAllMainCategories(parentSector);
		model.addAttribute("mainCategories", mainCategories);
						
        ContentPageModel contentPageModel = getContentPageForLabelOrId(SECTORS_OPPORTUNITY_PAGE);
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
       
        storeCmsPageInModel(model, contentPageModel);
        storeContentPageTitleInModel(model, contentPageModel.getTitle());
        return getViewForPage(model);
    }

	/**
	 * 
	 * @param sectorCode
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws CMSItemNotFoundException
	 */
    @RequestMapping(value = OPPORTUNITY_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
    public String sector(@PathVariable("sectorCode") final String sectorCode, final Model model,
    		final HttpServletRequest request, final HttpServletResponse response)
    				throws UnsupportedEncodingException, CMSItemNotFoundException {

        final CategoryModel category = getCommerceCategoryService().getCategoryForCode(sectorCode);

        final String redirection = checkRequestUrl(request, response, getCategoryModelUrlResolver().resolve(category));
        if (StringUtils.isNotEmpty(redirection)) {
            return redirection;
        }
               
        ContentPageModel contentPageModel = getContentPageForLabelOrId(SECTOR_DETAILS_PAGE);
        storeCmsPageInModel(model, contentPageModel);
        storeContentPageTitleInModel(model, contentPageModel.getTitle());
        
        storeContinueUrl(request);
        model.addAttribute(
                WebConstants.BREADCRUMBS_KEY, sectorBreadcrumbBuilder.getBreadcrumbs(category));

        final Optional<MediaModel> banner = category.getOthers().stream().findFirst();
        if (banner.isPresent()) {
            model.addAttribute("categoryBanner", banner.get());
        }
    
        model.addAttribute("categoryLogo", category.getPicture());
        model.addAttribute("categoryCode", category.getCode());
        model.addAttribute("categoryName", category.getName());
        model.addAttribute("category", category);
        model.addAttribute("pageType", PageType.CATEGORY.name());
        
        final String parentSector = Config.getString("parent.sector", "sector-opportunities");
        Collection<CategoryData> mainCategories = investSaudiCategoryFacade.getAllMainCategories(parentSector);		
        model.addAttribute("mainCategories", mainCategories);
        
        updatePageTitle(category, model);

        final RequestContextData requestContextData = getRequestContextData(request);
        requestContextData.setCategory(category);

        final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(
                category.getKeywords().stream().map(keywordModel -> keywordModel.getKeyword()).collect(Collectors.toSet()));
        final String metaDescription = MetaSanitizerUtil.sanitizeDescription(category.getDescription());
        final String metaKeywords2 = StringUtils.isBlank(metaKeywords)?category.getSeoKeywords():metaKeywords+","+category.getSeoKeywords();

        setUpMetaData(model, metaKeywords2, metaDescription);

        return getViewForPage(model);
    }
    
    /**
     * 
     * @param model
     * @param request
     * @param response
     * @param q
     * @param sectorIds
     * @param page
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/opportunities", method = {RequestMethod.GET})
    public String opportunitiesSearch(final Model model, final HttpServletRequest request, final HttpServletResponse response, 
    		@RequestParam(required = false, defaultValue = "") String q, @RequestParam(required = false) List<String> sectorIds, 
    		@RequestParam(required = false, defaultValue = "0") Integer page) 
    				throws CMSItemNotFoundException {

        final String parentSector = Config.getString("parent.sector", "sector-opportunities");
        final int opportunityResultSize = Config.getInt("opportunity.search.result.size", 6);
        
        ArrayList<String> sectoridlist = new ArrayList<String>();
        
        if (sectorIds == null) {
            sectorIds = new ArrayList<>();
        }
      else {
        	
        	  for(String sectorId:sectorIds)	{
        	sectoridlist.add(XSSFilterUtil.filter(sectorId));
        		  
        	    }
        	    LOG.info("encoding sectorids" +sectoridlist);
			
        }

        SearchPageData<OpportunityData> searchPageData = investSaudiProductFacade.
        		searchOpportunityByNameAndSectors(XSSFilterUtil.filter(q), sectoridlist, page, opportunityResultSize);
        ContentPageModel contentPageModel = getContentPageForLabelOrId(SEARCH_OPPORTUNITY_PAGE);

        int[] paginationLimits = getPaginationLimits(page, searchPageData.getPagination().getNumberOfPages());

        model.addAttribute("categoryList", investSaudiCategoryFacade.getAllMainCategories(parentSector));
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
        model.addAttribute("searchPageData", searchPageData);
        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("startPagination", paginationLimits[0]);
        model.addAttribute("endPagination", paginationLimits[1]);

        preserveQueryParamsForPagination(request, model);

        storeCmsPageInModel(model, contentPageModel);
        storeContentPageTitleInModel(model, contentPageModel.getTitle());
        return getViewForPage(model);
    }
    
    @RequestMapping(value = "/regionalHQ", method = {RequestMethod.GET})
    public String livingInSaudiPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException {

    	ContentPageModel contentPageModel = getContentPageForLabelOrId(REGIONAL_HQ_PAGE);
    	storeCmsPageInModel(model, contentPageModel);
    	storeContentPageTitleInModel(model, contentPageModel.getTitle());
    	model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
        return getViewForPage(model);        
    }

    /**
     * 
     * @param model
     * @param request
     * @param response
     * @param q
     * @param sectorIds
     * @param page
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/success-stories", method = {RequestMethod.GET})
    public String successStoriesSearch(final Model model, final HttpServletRequest request, final HttpServletResponse response, 
    		@RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(required = false) List<String> sectorIds, 
            @RequestParam(required = false, defaultValue = "0") Integer page) 
            		throws CMSItemNotFoundException {

        final String parentSector = Config.getString("parent.sector", "sector-opportunities");
        final int successStoryResultSize = Config.getInt("success.story.search.result.size", 6);
        
        if (sectorIds == null) {
            sectorIds = new ArrayList<>();
        }

        SearchPageData<SuccessStoryData> searchPageData = investSaudiProductFacade.
        		searchSuccessStoriesByNameAndSectors(XSSFilterUtil.filter(q), sectorIds, page, successStoryResultSize);
        ContentPageModel contentPageModel = getContentPageForLabelOrId(SEARCH_SUCCESS_STORIES_PAGE);

        int[] paginationLimits = getPaginationLimits(page, searchPageData.getPagination().getNumberOfPages());

        model.addAttribute("categoryList", investSaudiCategoryFacade.getAllSubCategoriesDataForCategoryCode(parentSector));
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
        model.addAttribute("searchPageData", searchPageData);
        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("startPagination", paginationLimits[0]);
        model.addAttribute("endPagination", paginationLimits[1]);

        preserveQueryParamsForPagination(request, model);

        storeCmsPageInModel(model, contentPageModel);
        storeContentPageTitleInModel(model, contentPageModel.getTitle());        
        return getViewForPage(model);
    }

    private int[] getPaginationLimits(int pageNumber, int numberOfPages){
        if(numberOfPages < 11)
            return new int[]{1, numberOfPages};
        else if(pageNumber > 5){
            if(pageNumber + 4 < numberOfPages)
                return new int[]{pageNumber - 4, pageNumber + 5};
            else return new int[]{numberOfPages - 9, numberOfPages};
        }
        return new int[]{1, 10};
    }

    protected String getViewPage(final CategoryPageModel categoryPage) {
        if (categoryPage != null) {
            final String targetPage = getViewForPage(categoryPage);
            if (targetPage != null && !targetPage.isEmpty()) {
                return targetPage;
            }
        }
        return PAGE_ROOT + SECTOR_PAGE;
    }

    private void preserveQueryParamsForPagination(HttpServletRequest request, Model model) {
        String queryString = "queryString";
        if (request.getQueryString() != null && request.getQueryString().contains(MULTIPLE_PARAMS_PAGE)) {
            String params = request.getQueryString().split(MULTIPLE_PARAMS_PAGE)[0];
            model.addAttribute(queryString, params);
        } else if (request.getQueryString() != null && request.getQueryString().contains(SINGLE_PAGE)) {
            String params = request.getQueryString().split(SINGLE_PAGE)[0];
            model.addAttribute(queryString, params);
        } else {
            model.addAttribute(queryString, request.getQueryString());
        }
    }
}
