package com.sap.ibso.eservices.core.sagia.dao.impl;

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

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultInvestmentDataDAO implements InvestmentDataDAO {

	private FlexibleSearchService flexibleSearchService;
	private static final String START_YEAR = "startYear";
	private static final String END_YEAR = "endYear";
	private static final String SELECTED_CHART = "selectedChart";
	private static final String SELECTED_INDICATOR = "indicator";

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public List<AnnualValueModel> getAnnualValueModelBySearch(String sector, String period, String startYear,
			String endYear) {
		final String queryString = "SELECT {" + AnnualValueModel.PK + "} FROM {" + AnnualValueModel._TYPECODE + "}"
				+ " WHERE {" + AnnualValueModel.YEAR + "} BETWEEN ?startYear AND ?endYear ORDER BY{"
				+ AnnualValueModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);
		
		
		final SearchResult<AnnualValueModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<AnnualGrowthModel> getAnnualGrowthModelBySearch(String sector, String period, String startYear,
			String endYear) {
		final String queryString = "SELECT {" + AnnualGrowthModel.PK + "} FROM {" + AnnualGrowthModel._TYPECODE + "}"
				+ " WHERE {" + AnnualGrowthModel.YEAR + "} BETWEEN  ?startYear AND  ?endYear ORDER BY{"
				+ AnnualGrowthModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);
		
		final SearchResult<AnnualGrowthModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<QuarterlyValueModel> getQuarterlyValueModelBySearch(String sector, String period, String startYear,
			String endYear) {

		final String queryString = "SELECT {" + QuarterlyValueModel.PK + "} FROM {" + QuarterlyValueModel._TYPECODE
				+ "}" + " WHERE ({" + QuarterlyValueModel.YEAR + "}BETWEEN ?qrt1startYear AND "
				+ "?qrt1endYear)OR({" + QuarterlyValueModel.YEAR + "}BETWEEN ?qrt2startYear AND "
				+ "?qrt2endYear)OR({" + QuarterlyValueModel.YEAR + "}BETWEEN ?qrt3startYear AND "
				+ "?qrt3endYear)OR({" + QuarterlyValueModel.YEAR + "}BETWEEN ?qrt4startYear AND "
				+ "?qrt4endYear)" + "ORDER BY" + "{" + QuarterlyValueModel.YEAR + "}";
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		
		query.addQueryParameter("qrt1startYear","Qtr1-"+startYear);
		query.addQueryParameter("qrt1endYear", "Qtr1-"+endYear);
		
		query.addQueryParameter("qrt2startYear", "Qtr2-"+startYear);
		query.addQueryParameter("qrt2endYear", "Qtr2-"+endYear);
		
		query.addQueryParameter("qrt3startYear", "Qtr3-"+startYear);
		query.addQueryParameter("qrt3endYear", "Qtr3-"+endYear);
		
		query.addQueryParameter("qrt4startYear", "Qtr4-"+startYear);
		query.addQueryParameter("qrt4endYear", "Qtr4-"+endYear);
		
		
		final SearchResult<QuarterlyValueModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}
	
	@Override
	public List<QuarterlyGrowthModel> getQuarterlyGrowthModelBySearch(String sector, String period, String startYear,
			String endYear) {
		final String queryString = "SELECT {" + QuarterlyGrowthModel.PK + "} FROM {" + QuarterlyGrowthModel._TYPECODE
				+ "}" + " WHERE ({" + QuarterlyGrowthModel.YEAR + "}BETWEEN ?qrt1startYear AND "
				+ "?qrt1endYear)OR({" + QuarterlyGrowthModel.YEAR + "}BETWEEN ?qrt2startYear AND "
				+ "?qrt2endYear)OR({" + QuarterlyGrowthModel.YEAR + "}BETWEEN ?qrt3startYear AND "
				+ "?qrt3endYear)OR({" + QuarterlyGrowthModel.YEAR + "}BETWEEN ?qrt4startYear AND "
				+ "?qrt4endYear)" + "ORDER BY" + "{" + QuarterlyGrowthModel.YEAR + "}";
		
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		
		query.addQueryParameter("qrt1startYear","Qtr1-"+startYear);
		query.addQueryParameter("qrt1endYear", "Qtr1-"+endYear);
		
		query.addQueryParameter("qrt2startYear", "Qtr2-"+startYear);
		query.addQueryParameter("qrt2endYear", "Qtr2-"+endYear);
		
		query.addQueryParameter("qrt3startYear", "Qtr3-"+startYear);
		query.addQueryParameter("qrt3endYear", "Qtr3-"+endYear);
		
		query.addQueryParameter("qrt4startYear", "Qtr4-"+startYear);
		query.addQueryParameter("qrt4endYear", "Qtr4-"+endYear);
		
		final SearchResult<QuarterlyGrowthModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<ForeignInvestmentValueModel> getForeignInvestmentValueModelBySearch() {
		final String queryString = "SELECT {" + ForeignInvestmentValueModel.PK + "} FROM {"
				+ ForeignInvestmentValueModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<ForeignInvestmentValueModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<ForeignInvestmentGrowthModel> getForeignInvestmentGrowthModelBySearch() {
		final String queryString = "SELECT {" + ForeignInvestmentGrowthModel.PK + "} FROM {"
				+ ForeignInvestmentGrowthModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<ForeignInvestmentGrowthModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<CapitalInformationModel> getCapitalInformationModelBySearch(String indicator, String startYear,
			String endYear) {
		final String queryString = "SELECT {" + CapitalInformationModel.PK + "} FROM {"
				+ CapitalInformationModel._TYPECODE + "}" + " WHERE {" + CapitalInformationModel.YEAR + "} BETWEEN "
				+ "?startYear AND ?endYear ORDER BY{" + CapitalInformationModel.YEAR + "}";
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);
		
		final SearchResult<CapitalInformationModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<AnnualFundAssetsModel> getAnnualFundAssetsModelBySearch(String indicator, String period,
			String startYear, String endYear) {
		final String queryString = "SELECT {" + AnnualFundAssetsModel.PK + "} FROM {" + AnnualFundAssetsModel._TYPECODE
				+ "}" + " WHERE {" + AnnualFundAssetsModel.YEAR + "} BETWEEN ?startYear AND ?endYear "
						+ "ORDER BY{" + AnnualFundAssetsModel.YEAR + "}";
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);
		
		final SearchResult<AnnualFundAssetsModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<NumberOfCommercialRegisterModel> getNumberOfCommercialRegisterModelBySearch(String indicator,
			String startYear, String endYear) {
		final String queryString = "SELECT {" + NumberOfCommercialRegisterModel.PK + "} FROM {"
				+ NumberOfCommercialRegisterModel._TYPECODE + "}" + " WHERE {" + NumberOfCommercialRegisterModel.YEAR
				+ "} BETWEEN ?startYear AND ?endYear ORDER BY{" + NumberOfCommercialRegisterModel.YEAR
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
				
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);		
		
		final SearchResult<NumberOfCommercialRegisterModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<CapitalOfCommercialRegisterModel> getCapitalOfCommercialRegisterModelBySearch(String indicator,
			String startYear, String endYear) {
		final String queryString = "SELECT {" + CapitalOfCommercialRegisterModel.PK + "} FROM {"
				+ CapitalOfCommercialRegisterModel._TYPECODE + "}" + " WHERE {" + CapitalOfCommercialRegisterModel.YEAR
				+ "} BETWEEN ?startYear AND ?endYear ORDER BY{" + CapitalOfCommercialRegisterModel.YEAR
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);	
		
		final SearchResult<CapitalOfCommercialRegisterModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<QuarterlyFundAssetsModel> getQuarterlyFundAssetsModelBySearch(String sector, String period,
			String startYear, String endYear) {
		final String queryString = "SELECT {" + QuarterlyFundAssetsModel.PK + "} FROM {"
				+ QuarterlyFundAssetsModel._TYPECODE + "}" + " WHERE ({" + QuarterlyFundAssetsModel.PERIOD
				+ "}BETWEEN ?q1startYear AND ?q1endYear)OR({" + QuarterlyFundAssetsModel.PERIOD
				+ "}BETWEEN ?q2startYear AND ?q2endYear)OR({" + QuarterlyFundAssetsModel.PERIOD
				+ "}BETWEEN ?q3startYear AND ?q3endYear)OR({" + QuarterlyFundAssetsModel.PERIOD
				+ "}BETWEEN ?q4startYear AND ?q4endYear)" + "ORDER BY" + "{"
				+ QuarterlyFundAssetsModel.PERIOD + "}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		
		query.addQueryParameter("q1startYear", "Q1-"+startYear);
		query.addQueryParameter("q1endYear", "Q1-"+endYear);
		
		query.addQueryParameter("q2startYear", "Q2-"+startYear);
		query.addQueryParameter("q2endYear", "Q2-"+endYear);
		
		query.addQueryParameter("q3startYear", "Q3-"+startYear);
		query.addQueryParameter("q3endYear", "Q3-"+endYear);
		
		query.addQueryParameter("q4startYear", "Q4-"+startYear);
		query.addQueryParameter("q4endYear", "Q4-"+endYear);
		
		final SearchResult<QuarterlyFundAssetsModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}
		
}
