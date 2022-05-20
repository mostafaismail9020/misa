package com.sap.ibso.eservices.facades.sagia.economic;

import java.util.List;

import com.sap.ibso.eservices.facades.data.*;

public interface InvestmentDataFacade {

	List<AQValueGrowthData> getAnnualValueData(final String sector, final String period, final String startYear, final String endYear);

	List<AQValueGrowthData> getAnnualGrowthData(final String sector, final String period,final String startYear,final String endYear);

	List<AQValueGrowthData> getQuarterlyValueData(final String sector, final String period,final String startYear,final String endYear);

	List<AQValueGrowthData> getQuarterlyGrowthData(final String sector, final String period,final String startYear,final String endYear);

	List<ForeignInvestmentData> getForeignInvestmentValueData();

	List<ForeignInvestmentData> getForeignInvestmentGrowthData();

	List<FundAssetsData> getAnnualFundAssetsData(final String sector, final String period, final String startYear, final String endYear);

	List<FundAssetsData> getQuarterlyFundAssetsData(final String sector, final String period,final String startYear,final String endYear);

	List<CommercialRegisterData> getNumberOfCommercialRegisterData(final String indicator, final String startYear,final String endYear);
	
	List<CommercialRegisterData> getCapitalOfCommercialRegisterData(final String indicator, final String startYear,final String endYear);

	List<CommercialRegisterData> getAnnualCapitalInformationData(final String indicator, final String startYear,final String endYear);

	List<CommercialRegisterData> getQuarterlyCapitalInformationData(final String indicator, final String startYear, final String endYear);


}
