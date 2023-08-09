/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import de.hybris.platform.acceleratorcms.model.components.SearchBoxComponentModel;
import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorservices.customer.CustomerLocationService;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.SearchBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.util.MetaSanitizerUtil;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.commercefacades.product.data.OpportunityData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.AutocompleteResultData;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.enums.SearchQueryContext;
import de.hybris.platform.commerceservices.search.facetdata.FacetData;
import de.hybris.platform.commerceservices.search.facetdata.FacetRefinement;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/sectors-opportunities/opportunities")
public class SagiaSearchPageController extends AbstractSearchPageController
{
	private static final String SEARCH_META_DESCRIPTION_ON = "search.meta.description.on";
	private static final String SEARCH_META_DESCRIPTION_RESULTS = "search.meta.description.results";

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(SagiaSearchPageController.class);

	private static final String COMPONENT_UID_PATH_VARIABLE_PATTERN = "{componentUid:.*}";
	private static final String FACET_SEPARATOR = ":";

	private static final String SEARCH_CMS_PAGE_ID = "opportunity-search-page";
	private static final int NUM_OF_RECORD_PER_PAGE = 9;

	@Resource(name = "productSearchFacade")
	private ProductSearchFacade<ProductData> productSearchFacade;

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
		final ContentPageModel noResultPage = getContentPageForLabelOrId(getPageId());

		final PageableData pageableData = createPageableData(0,NUM_OF_RECORD_PER_PAGE, null, ShowMode.Page);

		final SearchStateData searchState = new SearchStateData();
		final SearchQueryData searchQueryData = new SearchQueryData();
		if (getPageId().equals(SEARCH_CMS_PAGE_ID)) {
			searchText = searchText + ":resource:Opportunity";
		}
		searchQueryData.setValue(searchText);
		searchState.setQuery(searchQueryData);

		ProductSearchPageData<SearchStateData, ProductData> searchPageData = null;
		ProductSearchPageData<SearchStateData, ProductData> solrSearchPageData = null;

		try
		{
			searchPageData = encodeSearchPageData(productSearchFacade.textSearch(searchState, pageableData));
			if (getPageId().equals(SEARCH_CMS_PAGE_ID) && CollectionUtils.isNotEmpty(searchPageData.getFacets())) {
				searchPageData.setFacets(searchPageData.getFacets().stream()
						.filter(facet -> !facet.getCode().equals("resource")).collect(Collectors.toList()));
			}
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
		ContentPageModel contentPageModel = getContentPageForLabelOrId(getPageId());
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

	protected String getPageId() {
		return SEARCH_CMS_PAGE_ID;
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
		final ProductSearchPageData<SearchStateData, ProductData> searchPageData = performSearch(searchQuery, page, showMode,
				sortCode, NUM_OF_RECORD_PER_PAGE);
		if (getPageId().equals(SEARCH_CMS_PAGE_ID) && CollectionUtils.isNotEmpty(searchPageData.getFacets())) {
			searchPageData.setFacets(searchPageData.getFacets().stream()
					.filter(facet -> !facet.getCode().equals("resource")).collect(Collectors.toList()));
		}
		ProductSearchPageData<SearchStateData, ProductData> solrSearchPageData = null;

		populateModel(model, searchPageData, showMode);
		model.addAttribute("userLocation", customerLocationService.getUserLocation());

		if (searchPageData.getPagination().getTotalNumberOfResults() == 0)
		{
			updatePageTitle(searchPageData.getFreeTextSearch(), model);
			storeCmsPageInModel(model, getContentPageForLabelOrId(getPageId()));
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
			storeCmsPageInModel(model, getContentPageForLabelOrId(getPageId()));
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

	protected ProductSearchPageData<SearchStateData, ProductData> performSearch(String searchQuery, final int page,
			final ShowMode showMode, final String sortCode, final int pageSize)
	{
		final PageableData pageableData = createPageableData(page, pageSize, sortCode, showMode);

		final SearchStateData searchState = new SearchStateData();
		final SearchQueryData searchQueryData = new SearchQueryData();
		if (getPageId().equals(SEARCH_CMS_PAGE_ID)) {
			if (searchQuery.contains(":")) {
				searchQuery = searchQuery + ":resource:Opportunity";
			}
			else {
				searchQuery = searchQuery + ":relevance:resource:Opportunity";
			}
		}
		searchQueryData.setValue(searchQuery);
		searchState.setQuery(searchQueryData);

		return encodeSearchPageData(productSearchFacade.textSearch(searchState, pageableData));
	}

	@ResponseBody
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public SearchResultsData<ProductData> jsonSearchResults(@RequestParam("q") final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode) throws CMSItemNotFoundException
	{
		final ProductSearchPageData<SearchStateData, ProductData> searchPageData = performSearch(searchQuery, page, showMode,
				sortCode, getSearchPageSize());
		if (getPageId().equals(SEARCH_CMS_PAGE_ID) && CollectionUtils.isNotEmpty(searchPageData.getFacets())) {
			searchPageData.setFacets(searchPageData.getFacets().stream()
					.filter(facet -> !facet.getCode().equals("resource")).collect(Collectors.toList()));
		}
		final SearchResultsData<ProductData> searchResultsData = new SearchResultsData<>();
		searchResultsData.setResults(searchPageData.getResults());
		searchResultsData.setPagination(searchPageData.getPagination());
		return searchResultsData;
	}

	@ResponseBody
	@RequestMapping(value = "/facets", method = RequestMethod.GET)
	public FacetRefinement<SearchStateData> getFacets(@RequestParam("q") String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode) throws CMSItemNotFoundException
	{
		final SearchStateData searchState = new SearchStateData();
		final SearchQueryData searchQueryData = new SearchQueryData();
		if (getPageId().equals(SEARCH_CMS_PAGE_ID)) {
			searchQuery = searchQuery + ":resource:Opportunity";
		}
		searchQueryData.setValue(searchQuery);
		searchState.setQuery(searchQueryData);

		final ProductSearchPageData<SearchStateData, ProductData> searchPageData = productSearchFacade.textSearch(searchState,
				createPageableData(page, getSearchPageSize(), sortCode, showMode));
		if (getPageId().equals(SEARCH_CMS_PAGE_ID) && CollectionUtils.isNotEmpty(searchPageData.getFacets())) {
			searchPageData.setFacets(searchPageData.getFacets().stream()
					.filter(facet -> !facet.getCode().equals("resource")).collect(Collectors.toList()));
		}
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
			resultData.setSuggestions(subList(productSearchFacade.getAutocompleteSuggestions(term), component.getMaxSuggestions()));
		}

		if (component.isDisplayProducts())
		{
			resultData.setProducts(subList(productSearchFacade.textSearch(term, SearchQueryContext.SUGGESTIONS).getResults(),
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
	}
}
