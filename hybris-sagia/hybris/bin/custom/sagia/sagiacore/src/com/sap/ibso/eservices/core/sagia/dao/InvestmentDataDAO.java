package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;

public interface InvestmentDataDAO {
	List<AQValueGrowthModel> getAnnualValueModelBySearch(final String sector, final String period, final String startYear, final String endYear);

	List<AQValueGrowthModel> getAnnualGrowthModelBySearch(final String sector, final String period, final String startYear, final String endYear);

	List<AQValueGrowthModel> getQuarterlyValueModelBySearch(final String sector, final String period, final String startYear, final String endYear);

	List<AQValueGrowthModel> getQuarterlyGrowthModelBySearch(final String sector, final String period, final String startYear, final String endYear);

	List<ForeignInvestmentModel> getForeignInvestmentValueModelBySearch();

	List<ForeignInvestmentModel> getForeignInvestmentGrowthModelBySearch();

	List<FundAssetsModel> getAnnualFundAssetsModelBySearch(final String indicator, final String period, final String startYear, final String endYear);

	List<FundAssetsModel> getQuarterlyFundAssetsModelBySearch(final String sector, final String period,final String startYear,final String endYear);

	List<CommercialRegisterModel> getNumberOfCommercialRegisterModelBySearch(final String indicator, final String startYear, final String endYear);
	List<CommercialRegisterModel> getCapitalOfCommercialRegisterModelBySearch(final String indicator, final String startYear, final String endYear);

	List<CommercialRegisterModel> getAnnualCapitalInformationModelBySearch(final String indicator, final String startYear, final String endYear);
	List<CommercialRegisterModel> getQuarterlyCapitalInformationModelBySearch(final String indicator, final String startYear, final String endYear);

}
