package com.sap.ibso.eservices.core.sagia.dao.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.InfraLogisticsDAO;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultInfraLogisticsDAO implements InfraLogisticsDAO {

	private FlexibleSearchService flexibleSearchService;

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public InfraLogisticsLandingModel getInfraLogisticsLandingModelBySearch() {
		final String queryString = "SELECT {" + InfraLogisticsLandingModel.PK + "} FROM {"
				+ InfraLogisticsLandingModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<InfraLogisticsLandingModel> searchResult = getFlexibleSearchService().search(query);
		final InfraLogisticsLandingModel infraLogisticsLandingModel = searchResult.getResult().get(0);
		return infraLogisticsLandingModel;
	}

	@Override
	public List<LengthOfNetworkModel> getLengthOfNetworkModelBySearch() {
		final String queryString = "SELECT {" + LengthOfNetworkModel.PK + "} FROM {" + LengthOfNetworkModel._TYPECODE
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<LengthOfNetworkModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PrivateCitiesModel> getPrivateCitiesModelBySearch() {
		final String queryString = "SELECT {" + PrivateCitiesModel.PK + "} FROM {" + PrivateCitiesModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<PrivateCitiesModel> searchResult = getFlexibleSearchService().search(query);
		final List<PrivateCitiesModel> privateCitiesModel = (List<PrivateCitiesModel>) searchResult.getResult();
		return privateCitiesModel;
	}

	@Override
	public List<InfrastructureLogisticsModel> getAllInfrastructureLogisticsModel() {
		final String queryString = "SELECT {" + InfrastructureLogisticsModel.PK + "} FROM {" + InfrastructureLogisticsModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<InfrastructureLogisticsModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}


}
