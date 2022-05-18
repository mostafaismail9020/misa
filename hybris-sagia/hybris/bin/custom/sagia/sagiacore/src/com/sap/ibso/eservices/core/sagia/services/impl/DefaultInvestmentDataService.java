package com.sap.ibso.eservices.core.sagia.services.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;
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
	public List<AQValueGrowthModel> getAnnualValueModel(String sector, String period, String startYear, String endYear) {
		return getInvestmentDataDAO().getAnnualValueModelBySearch(sector, period,
				startYear, endYear);
	}

	@Override
	public List<AQValueGrowthModel> getAnnualGrowthModel(String sector, String period, String startYear,
														 String endYear) {
		return getInvestmentDataDAO().getAnnualGrowthModelBySearch(sector, period,
				startYear, endYear);
	}

	@Override
	public List<AQValueGrowthModel> getQuarterlyValueModel(String sector, String period, String startYear,
														   String endYear) {
		return getInvestmentDataDAO().getQuarterlyValueModelBySearch(sector,
				period, startYear, endYear);
	}

	@Override
	public List<AQValueGrowthModel> getQuarterlyGrowthModel(String sector, String period, String startYear,
															String endYear) {
		return getInvestmentDataDAO()
				.getQuarterlyGrowthModelBySearch(sector, period, startYear, endYear);
	}

	@Override
	public List<ForeignInvestmentModel> getForeignInvestmentValueModel() {
		return getInvestmentDataDAO()
				.getForeignInvestmentValueModelBySearch();
	}

	@Override
	public List<ForeignInvestmentModel> getForeignInvestmentGrowthModel() {
		return getInvestmentDataDAO()
				.getForeignInvestmentGrowthModelBySearch();
	}

	@Override
	public List<FundAssetsModel> getAnnualFundAssetsModel(String indicator, String period, String startYear,
														  String endYear) {
		return getInvestmentDataDAO()
				.getAnnualFundAssetsModelBySearch(indicator, period, startYear, endYear);
	}

	@Override
	public List<FundAssetsModel> getQuarterlyFundAssetsModel(String sector, String period, String startYear,
															 String endYear) {
		return getInvestmentDataDAO()
				.getQuarterlyFundAssetsModelBySearch(sector, period, startYear, endYear);
	}

	@Override
	public List<CommercialRegisterModel> getNumberOfCommercialRegisterModel(String indicator, String startYear,
																			String endYear) {
		return getInvestmentDataDAO()
				.getNumberOfCommercialRegisterModelBySearch(indicator, startYear, endYear);
	}
	
	@Override
	public List<CommercialRegisterModel> getCapitalOfCommercialRegisterModel(String indicator, String startYear,
																			 String endYear) {
		return getInvestmentDataDAO()
				.getCapitalOfCommercialRegisterModelBySearch(indicator, startYear, endYear);
	}

	@Override
	public List<CommercialRegisterModel> getAnnualCapitalInformationModel(String indicator, String startYear,
																		  String endYear) {
		return getInvestmentDataDAO()
				.getAnnualCapitalInformationModelBySearch(indicator, startYear, endYear);
	}

	@Override
	public List<CommercialRegisterModel> getQuarterlyCapitalInformationModel(String indicator, String startYear, String endYear) {
		return getInvestmentDataDAO()
				.getQuarterlyCapitalInformationModelBySearch(indicator, startYear, endYear);
	}
}
