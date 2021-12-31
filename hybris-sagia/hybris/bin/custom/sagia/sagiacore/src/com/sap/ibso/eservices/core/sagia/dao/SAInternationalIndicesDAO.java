package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.EaseOfProtectingMinorityInvestorsModel;
import com.sap.ibso.eservices.core.model.LogisticsPerformanceIndexModel;
import com.sap.ibso.eservices.core.model.EconomicFreedomIndexModel;
import com.sap.ibso.eservices.core.model.GlobalInnovationIndexModel;
import com.sap.ibso.eservices.core.model.EGovernmentDevelopmentIndexModel;
import com.sap.ibso.eservices.core.model.GlobalCompetitivenessReportModel;
import com.sap.ibso.eservices.core.model.LegatumProsperityIndexModel;
import com.sap.ibso.eservices.core.model.EaseOfStartingBusinessModel;
import java.util.List;

public interface SAInternationalIndicesDAO {
	List<EaseOfProtectingMinorityInvestorsModel> getEaseOfProtectingMinorityInvestorsModelBySearch(final String indicator, final Integer startYear,final Integer endYear);
	List<LogisticsPerformanceIndexModel> getLogisticsPerformanceIndexModelBySearch(final String indicator, final Integer startYear,final Integer endYear);
	List<EconomicFreedomIndexModel> getEconomicFreedomIndexModelBySearch(final String indicator, final Integer startYear,final Integer endYear);
	List<GlobalInnovationIndexModel> getGlobalInnovationIndexModelBySearch(final String indicator, final Integer startYear,final Integer endYear);
	List<EGovernmentDevelopmentIndexModel> getEGovernmentDevelopmentIndexModelBySearch(final String indicator, final Integer startYear,final Integer endYear);
	List<GlobalCompetitivenessReportModel> getGlobalCompetitivenessReportModelBySearch(final String indicator, final Integer startYear,final Integer endYear);
	List<LegatumProsperityIndexModel> getLegatumProsperityIndexModelBySearch(final String indicator, final Integer startYear,final Integer endYear);
	List<EaseOfStartingBusinessModel> getEaseOfStartingBusinessModelBySearch(final String indicator, final Integer startYear,final Integer endYear);
}
