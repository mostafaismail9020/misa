package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.EaseOfProtectingMinorityInvestorsModel;
import com.sap.ibso.eservices.core.model.LogisticsPerformanceIndexModel;
import com.sap.ibso.eservices.core.model.EconomicFreedomIndexModel;
import com.sap.ibso.eservices.core.model.GlobalInnovationIndexModel;
import com.sap.ibso.eservices.core.model.EGovernmentDevelopmentIndexModel;
import com.sap.ibso.eservices.core.model.GlobalCompetitivenessReportModel;
import com.sap.ibso.eservices.core.model.LegatumProsperityIndexModel;
import com.sap.ibso.eservices.core.model.EaseOfStartingBusinessModel;
import java.util.List;

public interface SAInternationalIndicesService {
	  List<EaseOfProtectingMinorityInvestorsModel> getEaseOfProtectingMinorityInvestorsModel(final String indicator, final Integer startYear,final Integer endYear);
	  List<LogisticsPerformanceIndexModel> getLogisticsPerformanceIndexModel(final String indicator, final Integer startYear,final Integer endYear);
	  List<EconomicFreedomIndexModel> getEconomicFreedomIndexModel(final String indicator, final Integer startYear,final Integer endYear);
	  List<GlobalInnovationIndexModel> getGlobalInnovationIndexModel(final String indicator, final Integer startYear,final Integer endYear);
	  List<EGovernmentDevelopmentIndexModel> getEGovernmentDevelopmentIndexModel(final String indicator, final Integer startYear,final Integer endYear);
	  List<GlobalCompetitivenessReportModel> getGlobalCompetitivenessReportModel(final String indicator, final Integer startYear,final Integer endYear);
	  List<LegatumProsperityIndexModel> getLegatumProsperityIndexModel(final String indicator, final Integer startYear,final Integer endYear);
	  List<EaseOfStartingBusinessModel> getEaseOfStartingBusinessModel(final String indicator, final Integer startYear,final Integer endYear);
}
