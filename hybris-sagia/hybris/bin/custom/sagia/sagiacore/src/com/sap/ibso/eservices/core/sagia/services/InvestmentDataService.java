package com.sap.ibso.eservices.core.sagia.services;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;

public interface InvestmentDataService {

	List<AQValueGrowthModel> getAnnualValueModel(final String sector, final String period,final String startYear,final String endYear);

	List<AQValueGrowthModel> getAnnualGrowthModel(final String sector, final String period,final String startYear,final String endYear);

	List<AQValueGrowthModel> getQuarterlyValueModel(final String sector, final String period, final String startYear, final String endYear);

	List<AQValueGrowthModel> getQuarterlyGrowthModel(final String sector, final String period,final String startYear,final String endYear);

	List<ForeignInvestmentModel> getForeignInvestmentValueModel();

	List<ForeignInvestmentModel> getForeignInvestmentGrowthModel();

	List<FundAssetsModel> getAnnualFundAssetsModel(final String indicator, final String period, final String startYear, final String endYear);

	List<FundAssetsModel> getQuarterlyFundAssetsModel(final String sector, final String period,final String startYear,final String endYear);

	List<CommercialRegisterModel> getNumberOfCommercialRegisterModel(final String indicator, final String startYear,final String endYear);
	
	List<CommercialRegisterModel> getCapitalOfCommercialRegisterModel(final String indicator, final String startYear,final String endYear);

	List<CommercialRegisterModel> getAnnualCapitalInformationModel(final String indicator, final String startYear, final String endYear);

	List<CommercialRegisterModel> getQuarterlyCapitalInformationModel(final String indicator, final String startYear, final String endYear);

}
