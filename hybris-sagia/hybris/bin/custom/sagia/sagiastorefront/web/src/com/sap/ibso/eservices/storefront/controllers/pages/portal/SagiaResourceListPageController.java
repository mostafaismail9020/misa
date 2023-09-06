package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;

import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController.ShowMode;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.servicelayer.i18n.I18NService;


@Controller
@RequestMapping("/resourcesList")
public class SagiaResourceListPageController extends SagiaSearchPageController
{
    private static final Logger LOG = Logger.getLogger(SagiaResourceListPageController.class);
    private static final String RESOURCES_LIST_PAGE = "resources-list-page";
    private static final int NUM_OF_RECORD_PER_PAGE = 60;
    private static final String DEFAULT_SORT = ":creationTime";

	@Resource (name="investSaudiCategoryFacade")
	private InvestSaudiCategoryFacade investSaudiCategoryFacade;
 
    @Override
    @RequestMapping(method = RequestMethod.GET, params = "!q")
    public String textSearch(@RequestParam(value = "text", defaultValue = "") String searchText,
                             final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
    {
        ProductSearchPageData<SearchStateData, ProductData> newsSearchPageData = null;
        ProductSearchPageData<SearchStateData, ProductData> eventsSearchPageData = null;
        ProductSearchPageData<SearchStateData, ProductData> articleSearchPageData = null;
        ProductSearchPageData<SearchStateData, ProductData> opportunitySearchPageData = null;

        newsSearchPageData = performSearch(searchText + DEFAULT_SORT + "-desc:resource:News", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);
        eventsSearchPageData = performSearch(searchText + DEFAULT_SORT + "-asc:resource:Event", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);
        articleSearchPageData = performSearch(searchText + DEFAULT_SORT + "-desc:resource:Article", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);
        opportunitySearchPageData = performSearch(searchText + DEFAULT_SORT + "-desc:resource:Opportunity", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);

        storeContinueUrl(request);
//        populateModel(model, newsSearchPageData, ShowMode.Page);

        model.addAttribute("newsSearchPageData", newsSearchPageData);
        model.addAttribute("eventSearchPageData", eventsSearchPageData);
        model.addAttribute("articleSearchPageData", articleSearchPageData);
        model.addAttribute("opportunitySearchPageData", opportunitySearchPageData);

        opportunitySearchPageData.getResults().forEach(r -> r.setParentCategory(investSaudiCategoryFacade.getCategoryForCode(r.getParentCategory()).getName()));
        
        model.addAttribute("pageType", PageType.PRODUCTSEARCH.name());
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_FOLLOW);
        ContentPageModel contentPageModel = getContentPageForLabelOrId(getPageId());
        storeCmsPageInModel(model, contentPageModel);
        storeContentPageTitleInModel(model, contentPageModel.getTitle());

        return getViewForPage(model);
    }

    @Override
	@RequestMapping(method = RequestMethod.GET, params = "q")
	public String refineSearch(@RequestParam("q") final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "text", required = false) final String searchText, final HttpServletRequest request,
			final Model model) throws CMSItemNotFoundException    {
        ProductSearchPageData<SearchStateData, ProductData> newsSearchPageData = null;
        ProductSearchPageData<SearchStateData, ProductData> eventsSearchPageData = null;
        ProductSearchPageData<SearchStateData, ProductData> articleSearchPageData = null;
        ProductSearchPageData<SearchStateData, ProductData> opportunitySearchPageData = null;

        newsSearchPageData = performSearch(searchQuery + DEFAULT_SORT + "-desc:resource:News", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);
        eventsSearchPageData = performSearch(searchQuery + DEFAULT_SORT + "-asc:resource:Event", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);
        articleSearchPageData = performSearch(searchQuery + DEFAULT_SORT + "-desc:resource:Article", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);
        opportunitySearchPageData = performSearch(searchQuery + DEFAULT_SORT + "-desc:resource:Opportunity", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);

        storeContinueUrl(request);

        model.addAttribute("newsSearchPageData", newsSearchPageData);
        model.addAttribute("eventSearchPageData", eventsSearchPageData);
        model.addAttribute("articleSearchPageData", articleSearchPageData);
        model.addAttribute("opportunitySearchPageData", opportunitySearchPageData);
        LOG.debug("Added search results to model");

        opportunitySearchPageData.getResults().forEach(r -> r.setParentCategory(investSaudiCategoryFacade.getCategoryForCode(r.getParentCategory()).getName()));
        
        model.addAttribute("pageType", PageType.PRODUCTSEARCH.name());
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_FOLLOW);
        ContentPageModel contentPageModel = getContentPageForLabelOrId(getPageId());
        storeCmsPageInModel(model, contentPageModel);
        storeContentPageTitleInModel(model, contentPageModel.getTitle());

        return getViewForPage(model);
    }
    
    @Override
    protected String getPageId() {
        return RESOURCES_LIST_PAGE;
    }

    @Override
    protected String getFilterParam() {
        return ":resource:News:resource:Event:Article:Opportunity";
    }

    @Override
    protected String getDefaultSort() {
        return DEFAULT_SORT;
    }
}

