package com.sap.ibso.eservices.core.sagia.services;

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

public interface InvestmentDataService {

	List<AnnualValueModel> getAnnualValueModel(final String sector, final String period,final String startYear,final String endYear);

	List<AnnualGrowthModel> getAnnualGrowthModel(final String sector, final String period,final String startYear,final String endYear);

	List<QuarterlyValueModel> getQuarterlyValueModel(final String sector, final String period,final String startYear,final String endYear);

	List<QuarterlyGrowthModel> getQuarterlyGrowthModel(final String sector, final String period,final String startYear,final String endYear);

	List<ForeignInvestmentValueModel> getForeignInvestmentValueModel();

	List<ForeignInvestmentGrowthModel> getForeignInvestmentGrowthModel();

	List<AnnualFundAssetsModel> getAnnualFundAssetsModel(final String indicator, final String period, final String startYear, final String endYear);

	List<NumberOfCommercialRegisterModel> getNumberOfCommercialRegisterModel(final String indicator, final String startYear,final String endYear);
	
	List<CapitalOfCommercialRegisterModel> getCapitalOfCommercialRegisterModel(final String indicator, final String startYear,final String endYear);

	List<CapitalInformationModel> getCapitalInformationModel(final String indicator, final String startYear,final String endYear);

	List<QuarterlyFundAssetsModel> getQuarterlyFundAssetsModel(final String sector, final String period,final String startYear,final String endYear);
}
