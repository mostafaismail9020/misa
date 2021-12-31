package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.AnnualFundAssetsModel;
import com.sap.ibso.eservices.core.model.AnnualGrowthModel;
import com.sap.ibso.eservices.core.model.AnnualValueModel;
import com.sap.ibso.eservices.core.model.CapitalInformationModel;
import com.sap.ibso.eservices.core.model.NumberOfCommercialRegisterModel;
import com.sap.ibso.eservices.core.model.CapitalOfCommercialRegisterModel;
import com.sap.ibso.eservices.core.model.ForeignInvestmentGrowthModel;
import com.sap.ibso.eservices.core.model.ForeignInvestmentValueModel;
import com.sap.ibso.eservices.core.model.QuarterlyFundAssetsModel;
import com.sap.ibso.eservices.core.model.QuarterlyGrowthModel;
import com.sap.ibso.eservices.core.model.QuarterlyValueModel;

public interface InvestmentDataDAO {
	List<AnnualValueModel> getAnnualValueModelBySearch(final String sector, final String period, final String startYear, final String endYear);

	List<AnnualGrowthModel> getAnnualGrowthModelBySearch(final String sector, final String period, final String startYear, final String endYear);

	List<QuarterlyValueModel> getQuarterlyValueModelBySearch(final String sector, final String period, final String startYear, final String endYear);

	List<QuarterlyGrowthModel> getQuarterlyGrowthModelBySearch(final String sector, final String period, final String startYear, final String endYear);

	List<ForeignInvestmentValueModel> getForeignInvestmentValueModelBySearch();

	List<ForeignInvestmentGrowthModel> getForeignInvestmentGrowthModelBySearch();

	List<CapitalInformationModel> getCapitalInformationModelBySearch(final String indicator, final String startYear, final String endYear);

	List<AnnualFundAssetsModel> getAnnualFundAssetsModelBySearch(final String indicator, final String period, final String startYear, final String endYear);

	List<NumberOfCommercialRegisterModel> getNumberOfCommercialRegisterModelBySearch(final String indicator, final String startYear, final String endYear);
	
	List<CapitalOfCommercialRegisterModel> getCapitalOfCommercialRegisterModelBySearch(final String indicator, final String startYear, final String endYear);

	List<QuarterlyFundAssetsModel> getQuarterlyFundAssetsModelBySearch(final String sector, final String period,final String startYear,final String endYear);

}
