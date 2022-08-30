/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/investment-highlights/resources")
public class InvestmentHighlightsReportSearchPageController extends AbstractSearchPageController
{
	/*private static final String SEARCH_META_DESCRIPTION_ON = "search.meta.description.on";
	private static final String SEARCH_META_DESCRIPTION_RESULTS = "search.meta.description.results";

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(InvestmentHighlightsReportSearchPageController.class);

	private static final String COMPONENT_UID_PATH_VARIABLE_PATTERN = "{componentUid:.*}";
	private static final String FACET_SEPARATOR = ":";

	private static final String SEARCH_CMS_PAGE_ID = "opportunity-search-page";
	private static final int NUM_OF_RECORD_PER_PAGE = 9;

	@Resource(name = "investmentHighlightsReportSearchFacade")
	private InvestmentHighlightsReportSearchFacade<InvestSaudiResourceComponentData> investmentHighlightsReportSearchFacade;

	@Resource(name = "searchBreadcrumbBuilder")
	private SearchBreadcrumbBuilder searchBreadcrumbBuilder;

	@Resource(name = "customerLocationService")
	private CustomerLocationService customerLocationService;

	@Resource(name = "cmsComponentService")
	private CMSComponentService cmsComponentService;

	@Resource (name="investSaudiCategoryFacade")
	private InvestSaudiCategoryFacade investSaudiCategoryFacade;

	@RequestMapping(method = RequestMethod.GET, params = "!q")
	public String textSearch(@RequestParam(value = "text", defaultValue = "") String searchText,
			final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{
		final ContentPageModel noResultPage = getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID);

		final PageableData pageableData = createPageableData(0,NUM_OF_RECORD_PER_PAGE, null, ShowMode.Page);

		final SearchStateData searchState = new SearchStateData();
		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(searchText);
		searchState.setQuery(searchQueryData);


		InvestSaudiResourceComponentSearchPageData<SearchStateData,
				InvestSaudiResourceComponentSearchPageData> searchPageData = null;

		InvestSaudiResourceComponentSearchPageData<SearchStateData,
				InvestSaudiResourceComponentSearchPageData> solrSearchPageData = null;

		try
		{
			searchPageData = encodeSearchCustomePageData(investmentHighlightsReportSearchFacade.textSearch(searchState, pageableData));
		}
		catch (final ConversionException e) // NOSONAR
		{
			// nothing to do - the exception is logged in SearchSolrQueryPopulator
		}

		if (searchPageData == null)
		{
			storeCmsPageInModel(model, noResultPage);
		}
		else if (searchPageData.getKeywordRedirectUrl() != null)
		{
			// if the search engine returns a redirect, just
			return "redirect:" + searchPageData.getKeywordRedirectUrl();
		}
		else if (searchPageData.getPagination().getTotalNumberOfResults() == 0)
		{
			model.addAttribute("searchPageData", searchPageData);
			storeCmsPageInModel(model, noResultPage);
			updatePageTitle(searchText, model);
		}
		else
		{
			storeContinueUrl(request);
			populateModel(model, searchPageData, ShowMode.Page);
			solrSearchPageData=searchPageData;
			List<OpportunityData> opportunityDataList = new ArrayList<>();
			for (InvestSaudiResourceComponentSearchPageData productData : searchPageData.getResults()) {
				opportunityDataList.add(createOpportunityData(productData));
			}
			SearchPageData<OpportunityData> productDataSearchPageData = new SearchPageData<>();
			productDataSearchPageData.setResults(opportunityDataList);
			PaginationData sagiaPaginationData = new PaginationData ();
			sagiaPaginationData.setPageSize(searchPageData.getPagination().getPageSize());
			sagiaPaginationData.setNumberOfPages(searchPageData.getPagination().getNumberOfPages());
			sagiaPaginationData.setTotalNumberOfResults(searchPageData.getPagination().getTotalNumberOfResults());
			sagiaPaginationData.setCurrentPage(searchPageData.getPagination().getCurrentPage());
			productDataSearchPageData.setPagination(sagiaPaginationData);
			model.addAttribute("solrSearchPageData", solrSearchPageData);
			model.addAttribute("searchPageData", productDataSearchPageData);
		}
		model.addAttribute("userLocation", customerLocationService.getUserLocation());
		getRequestContextData(request).setSearch(solrSearchPageData);
		if (solrSearchPageData != null)
		{
			model.addAttribute(WebConstants.BREADCRUMBS_KEY, searchBreadcrumbBuilder.getBreadcrumbs(null, searchText,
					CollectionUtils.isEmpty(solrSearchPageData.getBreadcrumbs())));
		}

		model.addAttribute("pageType", PageType.PRODUCTSEARCH.name());
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_FOLLOW);
		ContentPageModel contentPageModel = getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID);
		storeCmsPageInModel(model, contentPageModel);
		storeContentPageTitleInModel(model, contentPageModel.getTitle());

		final String metaDescription = MetaSanitizerUtil
				.sanitizeDescription(getMessageSource().getMessage(SEARCH_META_DESCRIPTION_RESULTS, null,
						SEARCH_META_DESCRIPTION_RESULTS, getI18nService().getCurrentLocale()) + " " + searchText + " "
						+ getMessageSource().getMessage(SEARCH_META_DESCRIPTION_ON, null, SEARCH_META_DESCRIPTION_ON,
								getI18nService().getCurrentLocale())
						+ " " + getSiteName());
		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(searchText);
		setUpMetaData(model, metaKeywords, metaDescription);

		return getViewForPage(model);
	}

	protected
	InvestSaudiResourceComponentSearchPageData<SearchStateData,
			InvestSaudiResourceComponentData> encodeSearchCustomePageData(
			final
			InvestSaudiResourceComponentSearchPageData<SearchStateData,
					InvestSaudiResourceComponentData> searchPageData)
	{
		final SearchStateData currentQuery = searchPageData.getCurrentQuery();

		if (currentQuery != null)
		{
			try
			{
				final SearchQueryData query = currentQuery.getQuery();
				final String encodedQueryValue = XSSEncoder.encodeHTML(query.getValue());
				query.setValue(encodedQueryValue);
				currentQuery.setQuery(query);
				searchPageData.setCurrentQuery(currentQuery);
				searchPageData.setFreeTextSearch(XSSEncoder.encodeHTML(searchPageData.getFreeTextSearch()));

				final List<FacetData<SearchStateData>> facets = searchPageData.getFacets();
				if (CollectionUtils.isNotEmpty(facets))
				{
					processFacetData(facets);
				}
			}
			catch (final UnsupportedEncodingException e)
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Error occured during Encoding the Search Page data values", e);
				}
			}
		}
		return searchPageData;
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

	@RequestMapping(method = RequestMethod.GET, params = "q")
	public String refineSearch(@RequestParam("q") final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "text", required = false) final String searchText, final HttpServletRequest request,
			final Model model) throws CMSItemNotFoundException
	{
		final InvestSaudiResourceComponentSearchPageData<SearchStateData, ProductData> searchPageData = performSearch(searchQuery, page, showMode,
				sortCode, NUM_OF_RECORD_PER_PAGE);
		InvestSaudiResourceComponentSearchPageData<SearchStateData, ProductData> solrSearchPageData = null;

		populateModel(model, searchPageData, showMode);
		model.addAttribute("userLocation", customerLocationService.getUserLocation());

		if (searchPageData.getPagination().getTotalNumberOfResults() == 0)
		{
			updatePageTitle(searchPageData.getFreeTextSearch(), model);
			storeCmsPageInModel(model, getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID));
		}
		else
		{
			storeContinueUrl(request);
			updatePageTitle(searchPageData.getFreeTextSearch(), model);
			solrSearchPageData=searchPageData;
			List<OpportunityData> opportunityDataList = new ArrayList<>();
			for (ProductData productData : searchPageData.getResults()) {
				opportunityDataList.add(createOpportunityData(productData));
			}
			SearchPageData<OpportunityData> productDataSearchPageData = new SearchPageData<>();
			productDataSearchPageData.setResults(opportunityDataList);
			PaginationData sagiaPaginationData = new PaginationData ();
			sagiaPaginationData.setPageSize(searchPageData.getPagination().getPageSize());
			sagiaPaginationData.setNumberOfPages(searchPageData.getPagination().getNumberOfPages());
			sagiaPaginationData.setTotalNumberOfResults(searchPageData.getPagination().getTotalNumberOfResults());
			sagiaPaginationData.setCurrentPage(searchPageData.getPagination().getCurrentPage());
			productDataSearchPageData.setPagination(sagiaPaginationData);
			model.addAttribute("solrSearchPageData", solrSearchPageData);
			model.addAttribute("searchPageData", productDataSearchPageData);
			storeCmsPageInModel(model, getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID));
		}
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, searchBreadcrumbBuilder.getBreadcrumbs(null, searchPageData));
		model.addAttribute("pageType", PageType.PRODUCTSEARCH.name());

		final String metaDescription = MetaSanitizerUtil
				.sanitizeDescription(getMessageSource().getMessage(SEARCH_META_DESCRIPTION_RESULTS, null,
						SEARCH_META_DESCRIPTION_RESULTS, getI18nService().getCurrentLocale()) + " " + searchText + " "
						+ getMessageSource().getMessage(SEARCH_META_DESCRIPTION_ON, null, SEARCH_META_DESCRIPTION_ON,
								getI18nService().getCurrentLocale())
						+ " " + getSiteName());

		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(searchText);
		setUpMetaData(model, metaKeywords, metaDescription);

		return getViewForPage(model);
	}



	protected InvestSaudiResourceComponentSearchPageData<SearchStateData, InvestSaudiResourceComponentData> performSearch(final String searchQuery, final int page,
			final ShowMode showMode, final String sortCode, final int pageSize)
	{
		final PageableData pageableData = createPageableData(page, pageSize, sortCode, showMode);

		final SearchStateData searchState = new SearchStateData();
		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(searchQuery);
		searchState.setQuery(searchQueryData);
		InvestSaudiResourceComponentSearchPageData<SearchStateData, InvestSaudiResourceComponentData> searchText = investmentHighlightsReportSearchFacade.textSearch(searchState, pageableData);
		return encodeSearchCustomePageData(searchText);
	}


	@ResponseBody
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public SearchResultsData<InvestSaudiResourceComponentData> jsonSearchResults(@RequestParam("q") final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode) throws CMSItemNotFoundException
	{
		final InvestSaudiResourceComponentSearchPageData<SearchStateData, InvestSaudiResourceComponentData> searchPageData = performSearch(searchQuery, page, showMode,
				sortCode, getSearchPageSize());
		final SearchResultsData<InvestSaudiResourceComponentData> searchResultsData = new SearchResultsData<>();
		searchResultsData.setResults(searchPageData.getResults());
		searchResultsData.setPagination(searchPageData.getPagination());
		return searchResultsData;
	}

	@ResponseBody
	@RequestMapping(value = "/facets", method = RequestMethod.GET)
	public FacetRefinement<SearchStateData> getFacets(@RequestParam("q") final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode) throws CMSItemNotFoundException
	{
		final SearchStateData searchState = new SearchStateData();
		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(searchQuery);
		searchState.setQuery(searchQueryData);




		final InvestSaudiResourceComponentSearchPageData<SearchStateData, InvestSaudiResourceComponentData> searchPageData = investmentHighlightsReportSearchFacade.textSearch(searchState,
				createPageableData(page, getSearchPageSize(), sortCode, showMode));
		final List<FacetData<SearchStateData>> facets = refineFacets(searchPageData.getFacets(),
				convertBreadcrumbsToFacets(searchPageData.getBreadcrumbs()));
		final FacetRefinement<SearchStateData> refinement = new FacetRefinement<>();
		refinement.setFacets(facets);
		refinement.setCount(searchPageData.getPagination().getTotalNumberOfResults());
		refinement.setBreadcrumbs(searchPageData.getBreadcrumbs());
		return refinement;
	}

	@ResponseBody
	@RequestMapping(value = "/autocomplete/" + COMPONENT_UID_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public AutocompleteResultData getAutocompleteSuggestions(@PathVariable final String componentUid,
			@RequestParam("term") final String term) throws CMSItemNotFoundException
	{
		final AutocompleteResultData resultData = new AutocompleteResultData();

		final SearchBoxComponentModel component = (SearchBoxComponentModel) cmsComponentService.getSimpleCMSComponent(componentUid);

		if (component.isDisplaySuggestions())
		{
			resultData.setSuggestions(subList(investmentHighlightsReportSearchFacade.getAutocompleteSuggestions(term), component.getMaxSuggestions()));
		}

		if (component.isDisplayProducts())
		{
			resultData.setProducts(subList(investmentHighlightsReportSearchFacade.textSearch(term, SearchQueryContext.SUGGESTIONS).getResults(),
					component.getMaxProducts()));
		}

		return resultData;
	}

	protected <E> List<E> subList(final List<E> list, final int maxElements)
	{
		if (CollectionUtils.isEmpty(list))
		{
			return Collections.emptyList();
		}

		if (list.size() > maxElements)
		{
			return list.subList(0, maxElements);
		}

		return list;
	}

	protected void updatePageTitle(final String searchText, final Model model)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveContentPageTitle(
				getMessageSource().getMessage("search.meta.title", null, "search.meta.title", getI18nService().getCurrentLocale())
						+ " " + searchText));
	}*/
}
