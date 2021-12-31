package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.sagia.dao.SAInternationalIndicesDAO;
import com.sap.ibso.eservices.core.sagia.services.SAInternationalIndicesService;
import com.sap.ibso.eservices.core.sagia.services.SAInternationalIndicesService;
import com.sap.ibso.eservices.core.model.EaseOfProtectingMinorityInvestorsModel;
import com.sap.ibso.eservices.core.model.LogisticsPerformanceIndexModel;
import com.sap.ibso.eservices.core.model.EconomicFreedomIndexModel;
import com.sap.ibso.eservices.core.model.GlobalInnovationIndexModel;
import com.sap.ibso.eservices.core.model.EGovernmentDevelopmentIndexModel;
import com.sap.ibso.eservices.core.model.GlobalCompetitivenessReportModel;
import com.sap.ibso.eservices.core.model.LegatumProsperityIndexModel;
import com.sap.ibso.eservices.core.model.EaseOfStartingBusinessModel;
import java.util.List;

public class DefaultSAInternationalIndicesService implements SAInternationalIndicesService {

	private SAInternationalIndicesDAO saInternationalIndicesDAO;

	@Override
	public List<EaseOfProtectingMinorityInvestorsModel> getEaseOfProtectingMinorityInvestorsModel(final String indicator, final Integer startYear,
			final Integer endYear) {
		final List<EaseOfProtectingMinorityInvestorsModel> easeOfProtectingMinorityInvestors = getSaInternationalIndicesDAO()
				.getEaseOfProtectingMinorityInvestorsModelBySearch(indicator,startYear,endYear);
		return easeOfProtectingMinorityInvestors;
	}
	
	@Override
	public List<LogisticsPerformanceIndexModel> getLogisticsPerformanceIndexModel(final String indicator, final Integer startYear,
			final Integer endYear) {
		final List<LogisticsPerformanceIndexModel> logisticsPerformanceIndex = getSaInternationalIndicesDAO()
				.getLogisticsPerformanceIndexModelBySearch(indicator,startYear,endYear);
		return logisticsPerformanceIndex;
	}
	
	@Override
	public List<EconomicFreedomIndexModel> getEconomicFreedomIndexModel(final String indicator, final Integer startYear,
			final Integer endYear) {
		final List<EconomicFreedomIndexModel> economicFreedomIndex = getSaInternationalIndicesDAO()
				.getEconomicFreedomIndexModelBySearch(indicator,startYear,endYear);
		return economicFreedomIndex;
	}
	
	@Override
	public List<GlobalInnovationIndexModel> getGlobalInnovationIndexModel(final String indicator, final Integer startYear,
			final Integer endYear) {
		final List<GlobalInnovationIndexModel> globalInnovationIndex = getSaInternationalIndicesDAO()
				.getGlobalInnovationIndexModelBySearch(indicator,startYear,endYear);
		return globalInnovationIndex;
	}
	
	@Override
	public List<EGovernmentDevelopmentIndexModel> getEGovernmentDevelopmentIndexModel(final String indicator, final Integer startYear,
			final Integer endYear) {
		final List<EGovernmentDevelopmentIndexModel> egovernmentDevelopmentIndex = getSaInternationalIndicesDAO()
				.getEGovernmentDevelopmentIndexModelBySearch(indicator,startYear,endYear);
		return egovernmentDevelopmentIndex;
	}
	
	@Override
	public List<GlobalCompetitivenessReportModel> getGlobalCompetitivenessReportModel(final String indicator, final Integer startYear,
			final Integer endYear) {
		final List<GlobalCompetitivenessReportModel> globalCompetitivenessReport = getSaInternationalIndicesDAO()
				.getGlobalCompetitivenessReportModelBySearch(indicator,startYear,endYear);
		return globalCompetitivenessReport;
	}
	
	@Override
	public List<LegatumProsperityIndexModel> getLegatumProsperityIndexModel(final String indicator, final Integer startYear,
			final Integer endYear) {
		final List<LegatumProsperityIndexModel> legatumProsperityIndex = getSaInternationalIndicesDAO()
				.getLegatumProsperityIndexModelBySearch(indicator,startYear,endYear);
		return legatumProsperityIndex;
	}
	
	@Override
	public List<EaseOfStartingBusinessModel> getEaseOfStartingBusinessModel(final String indicator, final Integer startYear,
			final Integer endYear) {
		final List<EaseOfStartingBusinessModel> easeOfStartingBusiness = getSaInternationalIndicesDAO()
				.getEaseOfStartingBusinessModelBySearch(indicator,startYear,endYear);
		return easeOfStartingBusiness;
	}
	

	/**
	 * @return the saInternationalIndicesDAO
	 */
	public SAInternationalIndicesDAO getSaInternationalIndicesDAO() {
		return saInternationalIndicesDAO;
	}

	/**
	 * @param saInternationalIndicesDAO the saInternationalIndicesDAO to set
	 */
	public void setSaInternationalIndicesDAO(SAInternationalIndicesDAO saInternationalIndicesDAO) {
		this.saInternationalIndicesDAO = saInternationalIndicesDAO;
	}

}
