/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.facades.solrfacetsearch.impl;

import com.investsaudi.portal.facades.solrfacetsearch.EconomicAndInvestmentMonitorSearchFacade;
import com.sap.ibso.eservices.facades.data.EconomicAndInvestmentMonitorData;
import com.sap.ibso.eservices.sagiaservices.solrfacetsearch.EconomicAndInvestmentMonitorSearchService;
import de.hybris.platform.commercefacades.search.data.AutocompleteSuggestionData;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.enums.SearchQueryContext;
import de.hybris.platform.commerceservices.search.ProductSearchAutocompleteService;
import de.hybris.platform.commerceservices.search.facetdata.EconomicAndInvestmentMonitorSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.AutocompleteSuggestion;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.threadcontext.ThreadContextService;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.util.List;


public class DefaultSolrEconomicAndInvestmentMonitorSearchFacade<ITEM extends EconomicAndInvestmentMonitorData> implements EconomicAndInvestmentMonitorSearchFacade<ITEM>
{
	private EconomicAndInvestmentMonitorSearchService<SolrSearchQueryData, SearchResultValueData, EconomicAndInvestmentMonitorSearchPageData<SolrSearchQueryData, SearchResultValueData>> EconomicAndInvestmentMonitorSearchService;


	private Converter<EconomicAndInvestmentMonitorSearchPageData<SolrSearchQueryData, SearchResultValueData>, EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM>> EconomicAndInvestmentMonitorSearchPageConverter;
	private Converter<SearchQueryData, SolrSearchQueryData> searchQueryDecoder;
	private Converter<AutocompleteSuggestion, AutocompleteSuggestionData> autocompleteSuggestionConverter;
	private ProductSearchAutocompleteService<AutocompleteSuggestion> autocompleteService;
	private ThreadContextService threadContextService;




	protected Converter<SearchQueryData, SolrSearchQueryData> getSearchQueryDecoder()
	{
		return searchQueryDecoder;
	}

	@Required
	public void setSearchQueryDecoder(final Converter<SearchQueryData, SolrSearchQueryData> searchQueryDecoder)
	{
		this.searchQueryDecoder = searchQueryDecoder;
	}

	protected Converter<AutocompleteSuggestion, AutocompleteSuggestionData> getAutocompleteSuggestionConverter()
	{
		return autocompleteSuggestionConverter;
	}

	@Required
	public void setAutocompleteSuggestionConverter(
			final Converter<AutocompleteSuggestion, AutocompleteSuggestionData> autocompleteSuggestionConverter)
	{
		this.autocompleteSuggestionConverter = autocompleteSuggestionConverter;
	}

	protected ProductSearchAutocompleteService<AutocompleteSuggestion> getAutocompleteService()
	{
		return autocompleteService;
	}

	@Required
	public void setAutocompleteService(final ProductSearchAutocompleteService<AutocompleteSuggestion> autocompleteService)
	{
		this.autocompleteService = autocompleteService;
	}

	protected ThreadContextService getThreadContextService()
	{
		return threadContextService;
	}

	@Required
	public void setThreadContextService(final ThreadContextService threadContextService)
	{
		this.threadContextService = threadContextService;
	}


	@Override
	public EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM> textSearch(final String text)
	{
		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM> execute()
					{
						return getEconomicAndInvestmentMonitorSearchPageConverter().convert(getEconomicAndInvestmentMonitorSearchService().textSearch(text, null,
								null));
					}
				});
	}

	@Override
	public EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM> textSearch(final String text, final SearchQueryContext searchQueryContext)
	{
		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM> execute()
					{
						return getEconomicAndInvestmentMonitorSearchPageConverter()
								.convert(getEconomicAndInvestmentMonitorSearchService().textSearch(text, searchQueryContext, null));
					}
				});
	}

	@Override
	public EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM> textSearch(final SearchStateData searchState,
			final PageableData pageableData)
	{
		Assert.notNull(searchState, "SearchStateData must not be null.");

		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM> execute()
					{
						return getEconomicAndInvestmentMonitorSearchPageConverter()
								.convert(getEconomicAndInvestmentMonitorSearchService().searchAgain(decodeState(searchState, null), pageableData));
					}
				});
	}

	@Override
	public List<AutocompleteSuggestionData> getAutocompleteSuggestions(final String input)
	{
		return getThreadContextService()
				.executeInContext(new ThreadContextService.Executor<List<AutocompleteSuggestionData>, ThreadContextService.Nothing>()
				{
					@Override
					public List<AutocompleteSuggestionData> execute()
					{
						return Converters.convertAll(getAutocompleteService().getAutocompleteSuggestions(input),
								getAutocompleteSuggestionConverter());
					}
				});

	}

	protected SolrSearchQueryData decodeState(final SearchStateData searchState, final String categoryCode)
	{
		final SolrSearchQueryData searchQueryData = getSearchQueryDecoder().convert(searchState.getQuery());
		if (categoryCode != null)
		{
			searchQueryData.setCategoryCode(categoryCode);
		}

		return searchQueryData;
	}


	public Converter<EconomicAndInvestmentMonitorSearchPageData<SolrSearchQueryData, SearchResultValueData>, EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM>> getEconomicAndInvestmentMonitorSearchPageConverter() {
		return EconomicAndInvestmentMonitorSearchPageConverter;
	}

	public void setEconomicAndInvestmentMonitorSearchPageConverter(Converter<EconomicAndInvestmentMonitorSearchPageData<SolrSearchQueryData, SearchResultValueData>, EconomicAndInvestmentMonitorSearchPageData<SearchStateData, ITEM>> EconomicAndInvestmentMonitorSearchPageConverter) {
		this.EconomicAndInvestmentMonitorSearchPageConverter = EconomicAndInvestmentMonitorSearchPageConverter;
	}


	protected EconomicAndInvestmentMonitorSearchService<SolrSearchQueryData, SearchResultValueData, EconomicAndInvestmentMonitorSearchPageData<SolrSearchQueryData, SearchResultValueData>> getEconomicAndInvestmentMonitorSearchService()
	{
		return EconomicAndInvestmentMonitorSearchService;
	}

	@Required
	public void setEconomicAndInvestmentMonitorSearchService(
			final EconomicAndInvestmentMonitorSearchService<SolrSearchQueryData, SearchResultValueData, EconomicAndInvestmentMonitorSearchPageData<SolrSearchQueryData, SearchResultValueData>> EconomicAndInvestmentMonitorSearchService)
	{
		this.EconomicAndInvestmentMonitorSearchService = EconomicAndInvestmentMonitorSearchService;
	}
}
