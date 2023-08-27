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
@RequestMapping("/newseventslist")
public class SagiaNewsEventsPageController extends SagiaSearchPageController
{
    private static final Logger LOG = Logger.getLogger(SagiaNewsEventsPageController.class);
    private static final String NEWS_EVENTS_LIST_PAGE = "newsevents-list-page";
	private static final int NUM_OF_RECORD_PER_PAGE = 9;
	private static final String DEFAULT_NEWS_SORT_TYPE = ":creationTime-desc";
	private static final String DEFAULT_SORT_TYPE = ":creationTime-asc";

    @Override
	@RequestMapping(method = RequestMethod.GET, params = "!q")
	public String textSearch(@RequestParam(value = "text", defaultValue = "") String searchText,
			final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{
		ProductSearchPageData<SearchStateData, ProductData> newsSearchPageData = null;
		ProductSearchPageData<SearchStateData, ProductData> eventsSearchPageData = null;

		newsSearchPageData = performSearch(searchText + DEFAULT_NEWS_SORT_TYPE + ":resource:News", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);
		eventsSearchPageData = performSearch(searchText + DEFAULT_SORT_TYPE + ":resource:Event", 0, ShowMode.Page, "", NUM_OF_RECORD_PER_PAGE);

		storeContinueUrl(request);
		populateModel(model, newsSearchPageData, ShowMode.Page);

		model.addAttribute("searchPageData", newsSearchPageData);
		model.addAttribute("eventSearchPageData", eventsSearchPageData);

		model.addAttribute("pageType", PageType.PRODUCTSEARCH.name());
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_FOLLOW);
		ContentPageModel contentPageModel = getContentPageForLabelOrId(getPageId());
		storeCmsPageInModel(model, contentPageModel);
		storeContentPageTitleInModel(model, contentPageModel.getTitle());

		return getViewForPage(model);
	}
    
    @Override
    protected String getPageId() {
        return NEWS_EVENTS_LIST_PAGE;
    }
    
    @Override
    protected String getFilterParam() {
        return ":resource:News:resource:Event";
    }
    
    @Override
	protected String getDefaultSort() {
		return DEFAULT_SORT_TYPE;
	}
}
