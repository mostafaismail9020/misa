package com.sap.ibso.eservices.core.sagia.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import com.sap.ibso.eservices.core.sagia.dao.SAInternationalIndicesDAO;
import com.sap.ibso.eservices.core.model.EaseOfProtectingMinorityInvestorsModel;
import com.sap.ibso.eservices.core.model.LogisticsPerformanceIndexModel;
import com.sap.ibso.eservices.core.model.EconomicFreedomIndexModel;
import com.sap.ibso.eservices.core.model.GlobalInnovationIndexModel;
import com.sap.ibso.eservices.core.model.EGovernmentDevelopmentIndexModel;
import com.sap.ibso.eservices.core.model.GlobalCompetitivenessReportModel;
import com.sap.ibso.eservices.core.model.LegatumProsperityIndexModel;
import com.sap.ibso.eservices.core.model.EaseOfStartingBusinessModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import com.sap.ibso.eservices.core.sagia.dao.SAInternationalIndicesDAO;
import java.util.List;

public class DefaultSAInternationalIndicesDAO implements SAInternationalIndicesDAO {

	private FlexibleSearchService flexibleSearchService;
	private static final String START_YEAR = "startYear";
	private static final String END_YEAR = "endYear";
	private static final String SELECTED_CHART = "selectedChart";
	private static final String SELECTED_INDICATOR = "indicator";

	@Override
	public List<EaseOfProtectingMinorityInvestorsModel> getEaseOfProtectingMinorityInvestorsModelBySearch(
			final String indicator, final Integer startYear, final Integer endYear) {
		final String queryString = "SELECT {" + EaseOfProtectingMinorityInvestorsModel.PK + "} FROM {"
				+ EaseOfProtectingMinorityInvestorsModel._TYPECODE + "}" + " WHERE {"
				+ EaseOfProtectingMinorityInvestorsModel.YEAR + "} BETWEEN " + startYear + " AND " + endYear
				+ " ORDER BY{" + EaseOfProtectingMinorityInvestorsModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<EaseOfProtectingMinorityInvestorsModel> searchResult = getFlexibleSearchService()
				.search(query);

		return searchResult.getResult();
	}

	@Override
	public List<LogisticsPerformanceIndexModel> getLogisticsPerformanceIndexModelBySearch(final String indicator,
			final Integer startYear, final Integer endYear) {

		final String queryString = "SELECT {" + LogisticsPerformanceIndexModel.PK + "} FROM {"
				+ LogisticsPerformanceIndexModel._TYPECODE + "}" + " WHERE {" + LogisticsPerformanceIndexModel.YEAR
				+ "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{" +LogisticsPerformanceIndexModel.YEAR +"}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<LogisticsPerformanceIndexModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<EconomicFreedomIndexModel> getEconomicFreedomIndexModelBySearch(final String indicator,
			final Integer startYear, final Integer endYear) {
		final String queryString = "SELECT {" + EconomicFreedomIndexModel.PK + "} FROM {"
				+ EconomicFreedomIndexModel._TYPECODE + "}" + " WHERE {" + EconomicFreedomIndexModel.YEAR + "} BETWEEN "
				+ startYear + " AND " + endYear + " ORDER BY{" +EconomicFreedomIndexModel.YEAR +"}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<EconomicFreedomIndexModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<GlobalInnovationIndexModel> getGlobalInnovationIndexModelBySearch(final String indicator,
			final Integer startYear, final Integer endYear) {
		final String queryString = "SELECT {" + GlobalInnovationIndexModel.PK + "} FROM {"
				+ GlobalInnovationIndexModel._TYPECODE + "}" + " WHERE {" + GlobalInnovationIndexModel.YEAR
				+ "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{" +GlobalInnovationIndexModel.YEAR +"}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<GlobalInnovationIndexModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<EGovernmentDevelopmentIndexModel> getEGovernmentDevelopmentIndexModelBySearch(final String indicator,
			final Integer startYear, final Integer endYear) {
		final String queryString = "SELECT {" + EGovernmentDevelopmentIndexModel.PK + "} FROM {"
				+ EGovernmentDevelopmentIndexModel._TYPECODE + "}" + " WHERE {" + EGovernmentDevelopmentIndexModel.YEAR
				+ "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{" +EGovernmentDevelopmentIndexModel.YEAR +"}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<EGovernmentDevelopmentIndexModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<GlobalCompetitivenessReportModel> getGlobalCompetitivenessReportModelBySearch(final String indicator,
			final Integer startYear, final Integer endYear) {
		final String queryString = "SELECT {" + GlobalCompetitivenessReportModel.PK + "} FROM {"
				+ GlobalCompetitivenessReportModel._TYPECODE + "}" + " WHERE {" + GlobalCompetitivenessReportModel.YEAR
				+ "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{" +GlobalCompetitivenessReportModel.YEAR +"}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<GlobalCompetitivenessReportModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<LegatumProsperityIndexModel> getLegatumProsperityIndexModelBySearch(final String indicator,
			final Integer startYear, final Integer endYear) {
		final String queryString = "SELECT {" + LegatumProsperityIndexModel.PK + "} FROM {"
				+ LegatumProsperityIndexModel._TYPECODE + "}" + " WHERE {" + LegatumProsperityIndexModel.YEAR
				+ "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{" +LegatumProsperityIndexModel.YEAR +"}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<LegatumProsperityIndexModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<EaseOfStartingBusinessModel> getEaseOfStartingBusinessModelBySearch(final String indicator,
			final Integer startYear, final Integer endYear) {
		final String queryString = "SELECT {" + EaseOfStartingBusinessModel.PK + "} FROM {"
				+ EaseOfStartingBusinessModel._TYPECODE + "}" + " WHERE {" + EaseOfStartingBusinessModel.YEAR
				+ "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{" +EaseOfStartingBusinessModel.YEAR +"}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<EaseOfStartingBusinessModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

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
}
