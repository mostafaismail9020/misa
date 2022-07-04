package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.*;


import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import com.sap.ibso.eservices.core.sagia.dao.EconomicDAO;

import java.util.List;

public class DefaultEconomicDAO implements EconomicDAO{
	
	private FlexibleSearchService flexibleSearchService;


	@Override
	public List<DashboardModel> getAllDashboardModelBySearch() {
		final String queryString = "SELECT {" + DashboardModel.PK + "} FROM {" + DashboardModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<DashboardModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}
	
	@Override
	public CreditRatingModel getCreditRatingModelBySearch()
	{
		final String queryString = "SELECT {" + CreditRatingModel.PK + "} FROM {" + CreditRatingModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<CreditRatingModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult().get(0);
	}
	
	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}


	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
