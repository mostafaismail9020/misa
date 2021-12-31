package com.sap.ibso.eservices.core.sagia.services.impl;

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
import com.sap.ibso.eservices.core.sagia.dao.InvestmentDataDAO;
import com.sap.ibso.eservices.core.sagia.services.InvestmentDataService;

public class DefaultInvestmentDataService implements InvestmentDataService {

	private InvestmentDataDAO investmentDataDAO;

	/**
	 * @return the investmentDataDAO
	 */
	public InvestmentDataDAO getInvestmentDataDAO() {
		return investmentDataDAO;
	}

	/**
	 * @param investmentDataDAO the investmentDataDAO to set
	 */
	public void setInvestmentDataDAO(InvestmentDataDAO investmentDataDAO) {
		this.investmentDataDAO = investmentDataDAO;
	}

	@Override
	public List<AnnualValueModel> getAnnualValueModel(String sector, String period, String startYear, String endYear) {
		final List<AnnualValueModel> annualValue = getInvestmentDataDAO().getAnnualValueModelBySearch(sector, period,
				startYear, endYear);
		return annualValue;
	}

	@Override
	public List<AnnualGrowthModel> getAnnualGrowthModel(String sector, String period, String startYear,
			String endYear) {
		final List<AnnualGrowthModel> annualGrowth = getInvestmentDataDAO().getAnnualGrowthModelBySearch(sector, period,
				startYear, endYear);
		return annualGrowth;
	}

	@Override
	public List<QuarterlyValueModel> getQuarterlyValueModel(String sector, String period, String startYear,
			String endYear) {
		final List<QuarterlyValueModel> quarterlyValue = getInvestmentDataDAO().getQuarterlyValueModelBySearch(sector,
				period, startYear, endYear);
		return quarterlyValue;
	}

	@Override
	public List<QuarterlyGrowthModel> getQuarterlyGrowthModel(String sector, String period, String startYear,
			String endYear) {
		final List<QuarterlyGrowthModel> quarterlyGrowth = getInvestmentDataDAO()
				.getQuarterlyGrowthModelBySearch(sector, period, startYear, endYear);
		return quarterlyGrowth;
	}

	@Override
	public List<ForeignInvestmentValueModel> getForeignInvestmentValueModel() {
		final List<ForeignInvestmentValueModel> foreignInvestmentValue = getInvestmentDataDAO()
				.getForeignInvestmentValueModelBySearch();
		return foreignInvestmentValue;
	}

	@Override
	public List<ForeignInvestmentGrowthModel> getForeignInvestmentGrowthModel() {
		final List<ForeignInvestmentGrowthModel> foreignInvestmentValue = getInvestmentDataDAO()
				.getForeignInvestmentGrowthModelBySearch();
		return foreignInvestmentValue;
	}

	@Override
	public List<AnnualFundAssetsModel> getAnnualFundAssetsModel(String indicator, String period, String startYear,
			String endYear) {
		final List<AnnualFundAssetsModel> annualFundAssets = getInvestmentDataDAO()
				.getAnnualFundAssetsModelBySearch(indicator, period, startYear, endYear);
		return annualFundAssets;
	}

	@Override
	public List<NumberOfCommercialRegisterModel> getNumberOfCommercialRegisterModel(String indicator, String startYear,
			String endYear) {
		final List<NumberOfCommercialRegisterModel> numberOfCommercialRegister = getInvestmentDataDAO()
				.getNumberOfCommercialRegisterModelBySearch(indicator, startYear, endYear);
		return numberOfCommercialRegister;
	}
	
	@Override
	public List<CapitalOfCommercialRegisterModel> getCapitalOfCommercialRegisterModel(String indicator, String startYear,
			String endYear) {
		final List<CapitalOfCommercialRegisterModel> capitalOfCommercialRegister = getInvestmentDataDAO()
				.getCapitalOfCommercialRegisterModelBySearch(indicator, startYear, endYear);
		return capitalOfCommercialRegister;
	}

	@Override
	public List<CapitalInformationModel> getCapitalInformationModel(String indicator, String startYear,
			String endYear) {
		final List<CapitalInformationModel> capitalInformation = getInvestmentDataDAO()
				.getCapitalInformationModelBySearch(indicator, startYear, endYear);
		return capitalInformation;
	}

	@Override
	public List<QuarterlyFundAssetsModel> getQuarterlyFundAssetsModel(String sector, String period, String startYear,
			String endYear) {
		final List<QuarterlyFundAssetsModel> quarterlyFundAssets = getInvestmentDataDAO()
				.getQuarterlyFundAssetsModelBySearch(sector, period, startYear, endYear);
		return quarterlyFundAssets;
	}

}
