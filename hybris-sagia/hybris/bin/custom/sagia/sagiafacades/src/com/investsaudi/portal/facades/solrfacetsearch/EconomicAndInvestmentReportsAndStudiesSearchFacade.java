/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.facades.solrfacetsearch;

import com.sap.ibso.eservices.facades.data.InvestSaudiResourceComponentData;
import de.hybris.platform.commercefacades.search.data.AutocompleteSuggestionData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.enums.SearchQueryContext;
import de.hybris.platform.commerceservices.search.facetdata.EconomicAndInvestmentReportsAndStudiesSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import java.util.List;


/**
 * InvestSaudiResourceComponent search facade interface.
 * Used to retrieve InvestSaudiResourceComponents of type {@link InvestSaudiResourceComponentData} (or subclasses of).
 *
 * @param <ITEM> The type of the product result items
 */
public interface EconomicAndInvestmentReportsAndStudiesSearchFacade<ITEM extends InvestSaudiResourceComponentData>
{
	/**
	 * Initiate a new search using simple free text query.
	 *
	 * @param text the search text
	 * @return the search results
	 */
	EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM> textSearch(String text);

	/**
	 * Initiate a new search using simple free text query in a search query context.
	 *
	 * @param text
	 *           the search text
	 * @param searchQueryContext
	 *           search query context
	 * @return the search results
	 */
	EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM> textSearch(String text, SearchQueryContext searchQueryContext);

	/**
	 * Refine an exiting search. The query object allows more complex queries using facet selection. The SearchStateData
	 * must have been obtained from the results of a call to {@link #textSearch(String)}.
	 *
	 * @param searchState  the search query object
	 * @param pageableData the page to return
	 * @return the search results
	 */
	EconomicAndInvestmentReportsAndStudiesSearchPageData<SearchStateData, ITEM> textSearch(SearchStateData searchState, PageableData pageableData);

	/**
	 * Get the auto complete suggestions for the provided input.
	 *
	 * @param input	the user's input
	 * @return a list of suggested search terms
	 */
	List<AutocompleteSuggestionData> getAutocompleteSuggestions(String input);
}
