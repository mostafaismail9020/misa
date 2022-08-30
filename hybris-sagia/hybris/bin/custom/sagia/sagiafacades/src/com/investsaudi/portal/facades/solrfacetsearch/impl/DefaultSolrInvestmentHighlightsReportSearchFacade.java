/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.facades.solrfacetsearch.impl;

import com.investsaudi.portal.facades.solrfacetsearch.InvestmentHighlightsReportSearchFacade;
import com.sap.ibso.eservices.facades.data.InvestSaudiResourceComponentData;
import com.sap.ibso.eservices.sagiaservices.solrfacetsearch.InvestmentHighlightsReportSearchService;
import de.hybris.platform.commercefacades.search.data.AutocompleteSuggestionData;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.enums.SearchQueryContext;
import de.hybris.platform.commerceservices.search.ProductSearchAutocompleteService;
import de.hybris.platform.commerceservices.search.facetdata.InvestSaudiResourceComponentSearchPageData;
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


public class DefaultSolrInvestmentHighlightsReportSearchFacade<ITEM extends InvestSaudiResourceComponentData> implements InvestmentHighlightsReportSearchFacade<ITEM>
{
	private InvestmentHighlightsReportSearchService<SolrSearchQueryData, SearchResultValueData, InvestSaudiResourceComponentSearchPageData<SolrSearchQueryData, SearchResultValueData>> investmentHighlightsReportSearchService;


	private Converter<InvestSaudiResourceComponentSearchPageData<SolrSearchQueryData, SearchResultValueData>, InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM>> investSaudiResourceComponentSearchPageConverter;
	private Converter<SearchQueryData, SolrSearchQueryData> searchQueryDecoder;
	private Converter<AutocompleteSuggestion, AutocompleteSuggestionData> autocompleteSuggestionConverter;
	private ProductSearchAutocompleteService<AutocompleteSuggestion> autocompleteService;
	private ThreadContextService threadContextService;

	protected InvestmentHighlightsReportSearchService<SolrSearchQueryData, SearchResultValueData, InvestSaudiResourceComponentSearchPageData<SolrSearchQueryData, SearchResultValueData>> getInvestSaudiResourceComponentSearchService()
	{
		return investmentHighlightsReportSearchService;
	}

	@Required
	public void setInvestmentHighlightsReportSearchService(
			final InvestmentHighlightsReportSearchService<SolrSearchQueryData, SearchResultValueData, InvestSaudiResourceComponentSearchPageData<SolrSearchQueryData, SearchResultValueData>> investmentHighlightsReportSearchService)
	{
		this.investmentHighlightsReportSearchService = investmentHighlightsReportSearchService;
	}



	public Converter<InvestSaudiResourceComponentSearchPageData<SolrSearchQueryData, SearchResultValueData>, InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM>> getInvestSaudiResourceComponentSearchPageConverter() {
		return investSaudiResourceComponentSearchPageConverter;
	}

	public void setInvestSaudiResourceComponentSearchPageConverter(Converter<InvestSaudiResourceComponentSearchPageData<SolrSearchQueryData, SearchResultValueData>, InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM>> investSaudiResourceComponentSearchPageConverter) {
		this.investSaudiResourceComponentSearchPageConverter = investSaudiResourceComponentSearchPageConverter;
	}
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
	public InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM> textSearch(final String text)
	{
		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM> execute()
					{
						return getInvestSaudiResourceComponentSearchPageConverter().convert(getInvestSaudiResourceComponentSearchService().textSearch(text, null,
								null));
					}
				});
	}

	@Override
	public InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM> textSearch(final String text, final SearchQueryContext searchQueryContext)
	{
		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM> execute()
					{
						return getInvestSaudiResourceComponentSearchPageConverter()
								.convert(getInvestSaudiResourceComponentSearchService().textSearch(text, searchQueryContext, null));
					}
				});
	}

	@Override
	public InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM> textSearch(final SearchStateData searchState,
			final PageableData pageableData)
	{
		Assert.notNull(searchState, "SearchStateData must not be null.");

		return getThreadContextService().executeInContext(
				new ThreadContextService.Executor<InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM>, ThreadContextService.Nothing>()
				{
					@Override
					public InvestSaudiResourceComponentSearchPageData<SearchStateData, ITEM> execute()
					{
						return getInvestSaudiResourceComponentSearchPageConverter()
								.convert(getInvestSaudiResourceComponentSearchService().searchAgain(decodeState(searchState, null), pageableData));
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

	public InvestmentHighlightsReportSearchService<SolrSearchQueryData, SearchResultValueData, InvestSaudiResourceComponentSearchPageData<SolrSearchQueryData, SearchResultValueData>> getInvestmentHighlightsReportSearchService() {
		return investmentHighlightsReportSearchService;
	}

}
