/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.sagiaservices.solrfacetsearch.impl;

import com.sap.ibso.eservices.sagiaservices.solrfacetsearch.InvestmentHighlightsReportSearchService;
import de.hybris.platform.commerceservices.enums.SearchQueryContext;
import de.hybris.platform.commerceservices.search.facetdata.InvestmentHighlightsReportSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryTermData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;


/**
 * Default implementation of the {@link ProductSearchService}
 *
 * @param <ITEM>
 *           the type of items returned as part of the search results
 */
public class DefaultSolrInvestmentHighlightsReportSearchService<ITEM> implements
		InvestmentHighlightsReportSearchService<SolrSearchQueryData, ITEM, InvestmentHighlightsReportSearchPageData<SolrSearchQueryData, ITEM>>
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DefaultSolrInvestmentHighlightsReportSearchService.class);

	private Converter<SearchQueryPageableData<SolrSearchQueryData>, SolrSearchRequest> searchQueryPageableConverter;
	private Converter<SolrSearchRequest, SolrSearchResponse> searchRequestConverter;
	private Converter<SolrSearchResponse, InvestmentHighlightsReportSearchPageData<SolrSearchQueryData, ITEM>> searchResponseConverter;

	protected Converter<SearchQueryPageableData<SolrSearchQueryData>, SolrSearchRequest> getSearchQueryPageableConverter()
	{
		return searchQueryPageableConverter;
	}

	@Required
	public void setSearchQueryPageableConverter(
			final Converter<SearchQueryPageableData<SolrSearchQueryData>, SolrSearchRequest> searchQueryPageableConverter)
	{
		this.searchQueryPageableConverter = searchQueryPageableConverter;
	}

	protected Converter<SolrSearchRequest, SolrSearchResponse> getSearchRequestConverter()
	{
		return searchRequestConverter;
	}

	@Required
	public void setSearchRequestConverter(final Converter<SolrSearchRequest, SolrSearchResponse> searchRequestConverter)
	{
		this.searchRequestConverter = searchRequestConverter;
	}

	protected Converter<SolrSearchResponse, InvestmentHighlightsReportSearchPageData<SolrSearchQueryData, ITEM>> getSearchResponseConverter()
	{
		return searchResponseConverter;
	}

	@Required
	public void setSearchResponseConverter(
			final Converter<SolrSearchResponse, InvestmentHighlightsReportSearchPageData<SolrSearchQueryData, ITEM>> searchResponseConverter)
	{
		this.searchResponseConverter = searchResponseConverter;
	}

	// End spring inject methods

	@Override
	public InvestmentHighlightsReportSearchPageData<SolrSearchQueryData, ITEM> textSearch(final String text,
			final PageableData pageableData)
	{
		final SolrSearchQueryData searchQueryData = createSearchQueryData();
		searchQueryData.setFreeTextSearch(text);
		searchQueryData.setFilterTerms(Collections.<SolrSearchQueryTermData> emptyList());

		return doSearch(searchQueryData, pageableData);
	}

	@Override
	public InvestmentHighlightsReportSearchPageData<SolrSearchQueryData, ITEM> textSearch(final String text,
			final SearchQueryContext searchQueryContext, final PageableData pageableData)
	{
		final SolrSearchQueryData searchQueryData = createSearchQueryData();
		searchQueryData.setFreeTextSearch(text);
		searchQueryData.setFilterTerms(Collections.<SolrSearchQueryTermData> emptyList());
		searchQueryData.setSearchQueryContext(searchQueryContext);

		return doSearch(searchQueryData, pageableData);
	}


	@Override
	public InvestmentHighlightsReportSearchPageData<SolrSearchQueryData, ITEM> searchAgain(
			final SolrSearchQueryData searchQueryData, final PageableData pageableData)
	{
		return doSearch(searchQueryData, pageableData);
	}

	protected InvestmentHighlightsReportSearchPageData<SolrSearchQueryData, ITEM> doSearch(
			final SolrSearchQueryData searchQueryData, final PageableData pageableData)
	{
		validateParameterNotNull(searchQueryData, "SearchQueryData cannot be null");

		// Create the SearchQueryPageableData that contains our parameters
		final SearchQueryPageableData<SolrSearchQueryData> searchQueryPageableData = buildSearchQueryPageableData(searchQueryData,
				pageableData);

		// Build up the search request
		final SolrSearchRequest solrSearchRequest = getSearchQueryPageableConverter().convert(searchQueryPageableData);

		// Execute the search
		final SolrSearchResponse solrSearchResponse = getSearchRequestConverter().convert(solrSearchRequest);

		// Convert the response
		return getSearchResponseConverter().convert(solrSearchResponse);
	}

	protected SearchQueryPageableData<SolrSearchQueryData> buildSearchQueryPageableData(final SolrSearchQueryData searchQueryData,
			final PageableData pageableData)
	{
		final SearchQueryPageableData<SolrSearchQueryData> searchQueryPageableData = createSearchQueryPageableData();
		searchQueryPageableData.setSearchQueryData(searchQueryData);
		searchQueryPageableData.setPageableData(pageableData);
		return searchQueryPageableData;
	}

	// Create methods for data object - can be overridden in spring config

	protected SearchQueryPageableData<SolrSearchQueryData> createSearchQueryPageableData()
	{
		return new SearchQueryPageableData<SolrSearchQueryData>();
	}

	protected SolrSearchQueryData createSearchQueryData()
	{
		return new SolrSearchQueryData();
	}

}
