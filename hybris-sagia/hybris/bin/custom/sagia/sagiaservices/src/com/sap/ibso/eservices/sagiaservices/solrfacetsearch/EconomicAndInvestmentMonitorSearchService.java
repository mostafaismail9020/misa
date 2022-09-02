/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.sagiaservices.solrfacetsearch;

import de.hybris.platform.commerceservices.enums.SearchQueryContext;
import de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData;
import de.hybris.platform.commerceservices.search.facetdata.EconomicAndInvestmentMonitorSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;


/**
 * ProductSearchService interface. Its main purpose is to retrieve product search results. The search service
 * implementation is stateless, i.e. it does not maintain any state for each search, instead it externalizes the search
 * state in the search page data returned. The search must be initiated by calling either of the 2 search methods
 * {@link #textSearch(String,PageableData)} or {@link #categorySearch(String,PageableData)}. From these you get the
 * search page data result {@link FacetSearchPageData}. The search page data includes state instances for each search
 * refinement option, and to perform the refinement the
 * {@link #searchAgain(Object, PageableData)} method must be called
 * passing back in the appropriate state instance. The state instance is a memento representing the search state, it is
 * an opaque instance that only has meaning to the search service implementation. Callers should not create their own
 * instances or modify the instances returned.
 *
 * @param <STATE>
 *           The type of the search query state. This is implementation specific. For example
 *           {@link de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData}
 * @param <ITEM>
 *           The type of items returned as part of the search results. For example
 *           {@link de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData}
 * @param <RESULT>
 *           The type of the search page data returned. Must be (or extend) {@link FacetSearchPageData}.
 */
public interface EconomicAndInvestmentMonitorSearchService<STATE, ITEM, RESULT extends EconomicAndInvestmentMonitorSearchPageData<STATE, ITEM>>
{
	/**
	 * Initiate a new search using simple free text query.
	 *
	 * @param text
	 *           the search text
	 * @param pageableData
	 *           the page to return, can be null to use defaults
	 * @return the search results
	 */
	RESULT textSearch(String text, PageableData pageableData);

	/**
	 * Initiate a new search using simple free text query in a search query context.
	 *
	 * @param text
	 *           the search text
	 * @param searchQueryContext
	 *           search query context
	 * @param pageableData
	 *           the page to return, can be null to use defaults
	 * @return the search results
	 */
	RESULT textSearch(String text, SearchQueryContext searchQueryContext, PageableData pageableData);

	/**
	 * Refine an exiting search. The query object allows more complex queries using facet selection. The SearchQueryData
	 * must have been obtained from the results of a call to either {@link #textSearch(String,PageableData)} or
	 *
	 *
	 * @param searchQueryData
	 *           the search query object
	 * @param pageableData
	 *           the page to return
	 * @return the search results
	 */
	RESULT searchAgain(STATE searchQueryData, PageableData pageableData);

}
