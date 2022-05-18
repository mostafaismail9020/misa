package com.sap.ibso.eservices.core.sagia.dao.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.SaudiEconomicSectorsDAO;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultSaudiEconomicSectorsDAO implements SaudiEconomicSectorsDAO {

	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<GraduatesByDegreeModel> getGraduatesByDegreeModelBySearch(int year) {
		final String queryString = "SELECT {" + GraduatesByDegreeModel.PK + "} FROM {"
				+ GraduatesByDegreeModel._TYPECODE + "}" + " WHERE {" + GraduatesByDegreeModel.YEAR + "}='" + year
				+ "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<GraduatesByDegreeModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<EconomicsSectorModel> getAllEconomicSectors() {
		final String queryString = "SELECT {" + EconomicsSectorModel.PK + "} FROM {" + EconomicsSectorModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<EconomicsSectorModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<PopulationDistributionModel> getAllPopulationDistributions(String popDistribution, int year) {
		final String queryString = "SELECT {" + PopulationDistributionModel.PK + "} FROM {" + PopulationDistributionModel._TYPECODE
				+ "}" + " WHERE {" + PopulationDistributionModel.YEAR + "}='" + year + "' AND {" + PopulationDistributionModel.UID + "}='" + popDistribution +"'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<PopulationDistributionModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<GenderDistributionModel> getGenderDistributionModelsBySearch(String distribution, int year) {
		final String queryString = "SELECT {" + GenderDistributionModel.PK + "} FROM {" + GenderDistributionModel._TYPECODE
				+ "}" + " WHERE {" + GenderDistributionModel.YEAR + "}  LIKE '%" + year  + "%' AND {" + GenderDistributionModel.UID + "}='" + distribution  + "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<GenderDistributionModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<RegionDistributionModel> getRegionDistributionModelsBySearch(String distribution, int year) {
		final String queryString = "SELECT {" + RegionDistributionModel.PK + "} FROM {"
				+ RegionDistributionModel._TYPECODE + "}" + " WHERE {" + RegionDistributionModel.YEAR + "} LIKE '%" + year
				+ "%' AND {" + RegionDistributionModel.UID+ "}='" + distribution + "'";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<RegionDistributionModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
	

}
