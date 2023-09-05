package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;


@Controller
@RequestMapping("/resourcesList")
public class SagiaResourceListPageController extends SagiaSearchPageController
{
    private static final Logger LOG = Logger.getLogger(SagiaResourceListPageController.class);
    private static final String RESOURCES_LIST_PAGE = "resources-list-page";
    private static final int NUM_OF_RECORD_PER_PAGE = 60;
    private static final String DEFAULT_SORT = ":creationTime";

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
        populateModel(model, newsSearchPageData, ShowMode.Page);

        model.addAttribute("newsSearchPageData", newsSearchPageData);
        model.addAttribute("eventSearchPageData", eventsSearchPageData);
        model.addAttribute("articleSearchPageData", articleSearchPageData);
        model.addAttribute("opportunitySearchPageData", opportunitySearchPageData);

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

