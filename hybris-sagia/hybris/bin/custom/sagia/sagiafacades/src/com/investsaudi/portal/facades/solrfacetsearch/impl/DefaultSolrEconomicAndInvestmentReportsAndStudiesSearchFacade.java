/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.facades.solrfacetsearch.impl;

import com.investsaudi.portal.facades.solrfacetsearch.EconomicAndInvestmentReportsAndStudiesSearchFacade;
import com.sap.ibso.eservices.facades.data.EconomicAndInvestmentReportsAndStudiesData;
import com.sap.ibso.eservices.sagiaservices.solrfacetsearch.EconomicAndInvestmentReportsAndStudiesSearchService;
import de.hybris.platform.commercefacades.search.data.AutocompleteSuggestionData;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.enums.SearchQueryContext;
import de.hybris.platform.commerceservices.search.ProductSearchAutocompleteService;
import de.hybris.platform.commerceservices.search.facetdata.EconomicAndInvestmentReportsAndStudiesSearchPageData;
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


public class DefaultSolrEconomicAndInvestmentReportsAndStudiesSearchFacade<ITEM extends EconomicAndInvestmentReportsAndStudiesData> implements EconomicAndInvestmentReportsAndStudiesSearchFacade<ITEM>
{
	private EconomicAndInvestmentReportsAndStudiesSearchService<SolrSearchQueryData, SearchResultValueData, EconomicAndInvestmentReportsAndStudiesSearchPageData<SolrSearchQueryData, SearchResultValueData>> EconomicAndInvestmentReportsAndStudiesSearchService;


	private Converter<EconomicAndInvestmentReportsAndStudiesSearchPageData<SolrSearchQueryData, SearchResultValueData>, EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM>> EconomicAndInvestmentReportsAndStudiesSearchPageConverter;
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
	public EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM> textSearch(final String text)
	{
		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM> execute()
					{
						return getEconomicAndInvestmentReportsAndStudiesSearchPageConverter().convert(getEconomicAndInvestmentReportsAndStudiesSearchService().textSearch(text, null,
								null));
					}
				});
	}

	@Override
	public EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM> textSearch(final String text, final SearchQueryContext searchQueryContext)
	{
		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM> execute()
					{
						return getEconomicAndInvestmentReportsAndStudiesSearchPageConverter()
								.convert(getEconomicAndInvestmentReportsAndStudiesSearchService().textSearch(text, searchQueryContext, null));
					}
				});
	}

	@Override
	public EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM> textSearch(final SearchStateData searchState,
			final PageableData pageableData)
	{
		Assert.notNull(searchState, "SearchStateData must not be null.");

		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM> execute()
					{
						return getEconomicAndInvestmentReportsAndStudiesSearchPageConverter()
								.convert(getEconomicAndInvestmentReportsAndStudiesSearchService().searchAgain(decodeState(searchState, null), pageableData));
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


	public Converter<EconomicAndInvestmentReportsAndStudiesSearchPageData<SolrSearchQueryData, SearchResultValueData>, EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM>> getEconomicAndInvestmentReportsAndStudiesSearchPageConverter() {
		return EconomicAndInvestmentReportsAndStudiesSearchPageConverter;
	}

	public void setEconomicAndInvestmentReportsAndStudiesSearchPageConverter(Converter<EconomicAndInvestmentReportsAndStudiesSearchPageData<SolrSearchQueryData, SearchResultValueData>, EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM>> EconomicAndInvestmentReportsAndStudiesSearchPageConverter) {
		this.EconomicAndInvestmentReportsAndStudiesSearchPageConverter = EconomicAndInvestmentReportsAndStudiesSearchPageConverter;
	}


	protected EconomicAndInvestmentReportsAndStudiesSearchService<SolrSearchQueryData, SearchResultValueData, EconomicAndInvestmentReportsAndStudiesSearchPageData<SolrSearchQueryData, SearchResultValueData>> getEconomicAndInvestmentReportsAndStudiesSearchService()
	{
		return EconomicAndInvestmentReportsAndStudiesSearchService;
	}

	@Required
	public void setEconomicAndInvestmentReportsAndStudiesSearchService(
			final EconomicAndInvestmentReportsAndStudiesSearchService<SolrSearchQueryData, SearchResultValueData, EconomicAndInvestmentReportsAndStudiesSearchPageData<SolrSearchQueryData, SearchResultValueData>> EconomicAndInvestmentReportsAndStudiesSearchService)
	{
		this.EconomicAndInvestmentReportsAndStudiesSearchService = EconomicAndInvestmentReportsAndStudiesSearchService;
	}
}
