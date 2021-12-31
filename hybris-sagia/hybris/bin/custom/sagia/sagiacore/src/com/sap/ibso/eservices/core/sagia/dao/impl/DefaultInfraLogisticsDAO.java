package com.sap.ibso.eservices.core.sagia.dao.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.EmploymentModel;
import com.sap.ibso.eservices.core.model.HousingFacilitiesModel;
import com.sap.ibso.eservices.core.model.IndustrialCitiesModel;
import com.sap.ibso.eservices.core.model.InfraLogisticsLandingModel;
import com.sap.ibso.eservices.core.model.InfrastructureModel;
import com.sap.ibso.eservices.core.model.LengthOfNetworkModel;
import com.sap.ibso.eservices.core.model.PrivateCitiesModel;
import com.sap.ibso.eservices.core.model.TotalAreaModel;
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
	public InfrastructureModel getInfrastructureModelBySearch() {
		final String queryString = "SELECT {" + InfrastructureModel.PK + "} FROM {"
				+ InfrastructureModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<InfrastructureModel> searchResult = getFlexibleSearchService().search(query);
		final InfrastructureModel infrastructureModel = searchResult.getResult().get(0);
		return infrastructureModel;
	}

	@Override
	public List<LengthOfNetworkModel> getLengthOfNetworkModelBySearch() {
		final String queryString = "SELECT {" + LengthOfNetworkModel.PK + "} FROM {" + LengthOfNetworkModel._TYPECODE
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<LengthOfNetworkModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public EmploymentModel getEmploymentModelBySearch() {
		final String queryString = "SELECT {" + EmploymentModel.PK + "} FROM {" + EmploymentModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<EmploymentModel> searchResult = getFlexibleSearchService().search(query);
		final EmploymentModel employmentModel = searchResult.getResult().get(0);
		return employmentModel;
	}

	@Override
	public TotalAreaModel getTotalAreaModelBySearch() {
		final String queryString = "SELECT {" + TotalAreaModel.PK + "} FROM {" + TotalAreaModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<TotalAreaModel> searchResult = getFlexibleSearchService().search(query);
		final TotalAreaModel totalAreaModel = searchResult.getResult().get(0);
		return totalAreaModel;
	}

	@Override
	public HousingFacilitiesModel getHousingFacilitiesModelBySearch() {
		final String queryString = "SELECT {" + HousingFacilitiesModel.PK + "} FROM {"
				+ HousingFacilitiesModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<HousingFacilitiesModel> searchResult = getFlexibleSearchService().search(query);
		final HousingFacilitiesModel housingFacilitiesModel = searchResult.getResult().get(0);
		return housingFacilitiesModel;
	}

	@Override
	public IndustrialCitiesModel getIndustrialCitiesModelBySearch() {
		final String queryString = "SELECT {" + IndustrialCitiesModel.PK + "} FROM {" + IndustrialCitiesModel._TYPECODE
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<IndustrialCitiesModel> searchResult = getFlexibleSearchService().search(query);
		final IndustrialCitiesModel industrialCitiesModel = searchResult.getResult().get(0);
		return industrialCitiesModel;
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


}
