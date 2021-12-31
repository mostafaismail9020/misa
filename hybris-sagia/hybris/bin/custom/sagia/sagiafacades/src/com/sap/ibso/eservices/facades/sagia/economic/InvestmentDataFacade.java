package com.sap.ibso.eservices.facades.sagia.economic;

import java.util.List;

import com.sap.ibso.eservices.facades.data.AnnualFundAssetsData;
import com.sap.ibso.eservices.facades.data.AnnualGrowthData;
import com.sap.ibso.eservices.facades.data.AnnualValueData;
import com.sap.ibso.eservices.facades.data.CapitalInformationData;
import com.sap.ibso.eservices.facades.data.NumberOfCommercialRegisterData;
import com.sap.ibso.eservices.facades.data.CapitalOfCommercialRegisterData;
import com.sap.ibso.eservices.facades.data.ForeignInvestmentGrowthData;
import com.sap.ibso.eservices.facades.data.ForeignInvestmentValueData;
import com.sap.ibso.eservices.facades.data.QuarterlyFundAssetsData;
import com.sap.ibso.eservices.facades.data.QuarterlyGrowthData;
import com.sap.ibso.eservices.facades.data.QuarterlyValueData;

public interface InvestmentDataFacade {

	List<AnnualValueData> getAnnualValueData(final String sector, final String period,final String startYear,final String endYear);

	List<AnnualGrowthData> getAnnualGrowthData(final String sector, final String period,final String startYear,final String endYear);

	List<QuarterlyValueData> getQuarterlyValueData(final String sector, final String period,final String startYear,final String endYear);

	List<QuarterlyGrowthData> getQuarterlyGrowthData(final String sector, final String period,final String startYear,final String endYear);

	List<ForeignInvestmentValueData> getForeignInvestmentValueData();

	List<ForeignInvestmentGrowthData> getForeignInvestmentGrowthData();

	List<AnnualFundAssetsData> getAnnualFundAssetsData(final String sector, final String period,final String startYear,final String endYear);

	List<NumberOfCommercialRegisterData> getNumberOfCommercialRegisterData(final String indicator, final String startYear,final String endYear);
	
	List<CapitalOfCommercialRegisterData> getCapitalOfCommercialRegisterData(final String indicator, final String startYear,final String endYear);

	List<CapitalInformationData> getCapitalInformationData(final String indicator, final String startYear,final String endYear);

	List<QuarterlyFundAssetsData> getQuarterlyFundAssetsData(final String sector, final String period,final String startYear,final String endYear);

}
