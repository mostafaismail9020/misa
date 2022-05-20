package com.sap.ibso.eservices.core.sagia.dao.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;
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
	public List<AQValueGrowthModel> getAnnualValueModelBySearch(String sector, String period, String startYear,
																String endYear) {
		final String queryString = "SELECT {" + AQValueGrowthModel.PK + "} FROM {" + AQValueGrowthModel._TYPECODE + "}"
				+ " WHERE { " + AQValueGrowthModel.UID +" }= 'AnnualValue' AND {" + AQValueGrowthModel.YEAR + "} BETWEEN "
				+ "?startYear AND ?endYear ORDER BY{"
				+ AQValueGrowthModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);
		
		
		final SearchResult<AQValueGrowthModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<AQValueGrowthModel> getAnnualGrowthModelBySearch(String sector, String period, String startYear,
																 String endYear) {
		final String queryString = "SELECT {" + AQValueGrowthModel.PK + "} FROM {" + AQValueGrowthModel._TYPECODE + "}"
				+ " WHERE { " + AQValueGrowthModel.UID +" }= 'AnnualGrowth' AND {" + AQValueGrowthModel.YEAR + "} BETWEEN  "
				+ "?startYear AND  ?endYear ORDER BY{"
				+ AQValueGrowthModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);
		
		final SearchResult<AQValueGrowthModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<AQValueGrowthModel> getQuarterlyValueModelBySearch(String sector, String period, String startYear,
			String endYear) {

		final String queryString = "SELECT {" + AQValueGrowthModel.PK + "} FROM {" + AQValueGrowthModel._TYPECODE
				+ "}" + " WHERE { " + AQValueGrowthModel.UID + " } = 'QuarterlyValue' AND " +
				"({" + AQValueGrowthModel.YEAR + "}BETWEEN ?qrt1startYear AND "
				+ "?qrt1endYear)OR({" + AQValueGrowthModel.YEAR + "}BETWEEN ?qrt2startYear AND "
				+ "?qrt2endYear)OR({" + AQValueGrowthModel.YEAR + "}BETWEEN ?qrt3startYear AND "
				+ "?qrt3endYear)OR({" + AQValueGrowthModel.YEAR + "}BETWEEN ?qrt4startYear AND "
				+ "?qrt4endYear)" + "ORDER BY" + "{" + AQValueGrowthModel.YEAR + "}";
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		
		query.addQueryParameter("qrt1startYear","Qtr1-"+startYear);
		query.addQueryParameter("qrt1endYear", "Qtr1-"+endYear);
		
		query.addQueryParameter("qrt2startYear", "Qtr2-"+startYear);
		query.addQueryParameter("qrt2endYear", "Qtr2-"+endYear);
		
		query.addQueryParameter("qrt3startYear", "Qtr3-"+startYear);
		query.addQueryParameter("qrt3endYear", "Qtr3-"+endYear);
		
		query.addQueryParameter("qrt4startYear", "Qtr4-"+startYear);
		query.addQueryParameter("qrt4endYear", "Qtr4-"+endYear);
		
		
		final SearchResult<AQValueGrowthModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}
	
	@Override
	public List<AQValueGrowthModel> getQuarterlyGrowthModelBySearch(String sector, String period, String startYear,
																	String endYear) {
		final String queryString = "SELECT {" + AQValueGrowthModel.PK + "} FROM {" + AQValueGrowthModel._TYPECODE
				+ "}" + " WHERE { " + AQValueGrowthModel.UID + " } = 'QuarterlyGrowth' AND " +
				"({" + AQValueGrowthModel.YEAR + "}BETWEEN ?qrt1startYear AND "
				+ "?qrt1endYear)OR({" + AQValueGrowthModel.YEAR + "}BETWEEN ?qrt2startYear AND "
				+ "?qrt2endYear)OR({" + AQValueGrowthModel.YEAR + "}BETWEEN ?qrt3startYear AND "
				+ "?qrt3endYear)OR({" + AQValueGrowthModel.YEAR + "}BETWEEN ?qrt4startYear AND "
				+ "?qrt4endYear)" + "ORDER BY" + "{" + AQValueGrowthModel.YEAR + "}";
		
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		
		query.addQueryParameter("qrt1startYear","Qtr1-"+startYear);
		query.addQueryParameter("qrt1endYear", "Qtr1-"+endYear);
		
		query.addQueryParameter("qrt2startYear", "Qtr2-"+startYear);
		query.addQueryParameter("qrt2endYear", "Qtr2-"+endYear);
		
		query.addQueryParameter("qrt3startYear", "Qtr3-"+startYear);
		query.addQueryParameter("qrt3endYear", "Qtr3-"+endYear);
		
		query.addQueryParameter("qrt4startYear", "Qtr4-"+startYear);
		query.addQueryParameter("qrt4endYear", "Qtr4-"+endYear);
		
		final SearchResult<AQValueGrowthModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<ForeignInvestmentModel> getForeignInvestmentValueModelBySearch() {
		final String queryString = "SELECT {" + ForeignInvestmentModel.PK + "} FROM {"
				+ ForeignInvestmentModel._TYPECODE + "} WHERE {" + ForeignInvestmentModel.UID + "} = 'ForeignInvestmentValue'" +
				"ORDER BY{" + ForeignInvestmentModel.INDEX + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<ForeignInvestmentModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<ForeignInvestmentModel> getForeignInvestmentGrowthModelBySearch() {
		final String queryString = "SELECT {" + ForeignInvestmentModel.PK + "} FROM {"
				+ ForeignInvestmentModel._TYPECODE + "} WHERE {" + ForeignInvestmentModel.UID + "} = 'ForeignInvestmentGrowth'" +
				"ORDER BY{" + ForeignInvestmentModel.INDEX + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<ForeignInvestmentModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<FundAssetsModel> getAnnualFundAssetsModelBySearch(String indicator, String period,
																  String startYear, String endYear) {
		final String queryString = "SELECT {" + FundAssetsModel.PK + "} FROM {" + FundAssetsModel._TYPECODE
				+ "}" + " WHERE {"+FundAssetsModel.UID+"} = 'AnnualFundAssets' AND {" + FundAssetsModel.YEAR + "} BETWEEN ?startYear AND ?endYear "
						+ "ORDER BY{" + FundAssetsModel.YEAR + "}";
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);
		
		final SearchResult<FundAssetsModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<FundAssetsModel> getQuarterlyFundAssetsModelBySearch(String sector, String period,
																	 String startYear, String endYear) {
		final String queryString = "SELECT {" + FundAssetsModel.PK + "} FROM {"
				+ FundAssetsModel._TYPECODE + "}" + " WHERE { " + FundAssetsModel.UID + " } = 'QuarterlyFundAssets'"
				+ " AND ({" + FundAssetsModel.YEAR
				+ "}BETWEEN ?q1startYear AND ?q1endYear)OR({" + FundAssetsModel.YEAR
				+ "}BETWEEN ?q2startYear AND ?q2endYear)OR({" + FundAssetsModel.YEAR
				+ "}BETWEEN ?q3startYear AND ?q3endYear)OR({" + FundAssetsModel.YEAR
				+ "}BETWEEN ?q4startYear AND ?q4endYear)" + "ORDER BY" + "{"
				+ FundAssetsModel.YEAR + "}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

		query.addQueryParameter("q1startYear", "Q1-"+startYear);
		query.addQueryParameter("q1endYear", "Q1-"+endYear);

		query.addQueryParameter("q2startYear", "Q2-"+startYear);
		query.addQueryParameter("q2endYear", "Q2-"+endYear);

		query.addQueryParameter("q3startYear", "Q3-"+startYear);
		query.addQueryParameter("q3endYear", "Q3-"+endYear);

		query.addQueryParameter("q4startYear", "Q4-"+startYear);
		query.addQueryParameter("q4endYear", "Q4-"+endYear);

		final SearchResult<FundAssetsModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<CommercialRegisterModel> getNumberOfCommercialRegisterModelBySearch(String indicator,
																					String startYear, String endYear) {
		final String queryString = "SELECT {" + CommercialRegisterModel.PK + "} FROM {"
				+ CommercialRegisterModel._TYPECODE + "}" + " WHERE { " + CommercialRegisterModel.UID + "} = 'NumberOfCommercialRegister' "
				+ "AND {" + CommercialRegisterModel.YEAR
				+ "} BETWEEN ?startYear AND ?endYear ORDER BY{" + CommercialRegisterModel.YEAR
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
				
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);		
		
		final SearchResult<CommercialRegisterModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<CommercialRegisterModel> getCapitalOfCommercialRegisterModelBySearch(String indicator,
																					 String startYear, String endYear) {
		final String queryString = "SELECT {" + CommercialRegisterModel.PK + "} FROM {"
				+ CommercialRegisterModel._TYPECODE + "}" + " WHERE { " + CommercialRegisterModel.UID + "} = 'CapitalOfCommercialRegister' "
				+ "AND {" + CommercialRegisterModel.YEAR
				+ "} BETWEEN ?startYear AND ?endYear ORDER BY{" + CommercialRegisterModel.YEAR
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		
		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);	
		
		final SearchResult<CommercialRegisterModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<CommercialRegisterModel> getAnnualCapitalInformationModelBySearch(String indicator, String startYear,
																				  String endYear) {
		final String queryString = "SELECT {" + CommercialRegisterModel.PK + "} FROM {"
				+ CommercialRegisterModel._TYPECODE + "}" + " WHERE {" + CommercialRegisterModel.UID + "} = 'AnnualCapitalInformation'" +
				" AND {" + CommercialRegisterModel.YEAR + "} BETWEEN "
				+ "?startYear AND ?endYear ORDER BY{" + CommercialRegisterModel.YEAR + "}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

		query.addQueryParameter("startYear", startYear);
		query.addQueryParameter("endYear", endYear);

		final SearchResult<CommercialRegisterModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<CommercialRegisterModel> getQuarterlyCapitalInformationModelBySearch(String indicator, String startYear, String endYear) {

		final String queryString = "SELECT {" + CommercialRegisterModel.PK + "} FROM {"
				+ CommercialRegisterModel._TYPECODE + "}" + " WHERE {"+CommercialRegisterModel.UID+"} = 'QuarterlyCapitalInformation' "
				+ " AND (({" + CommercialRegisterModel.YEAR
				+ "}BETWEEN ?q1startYear AND ?q1endYear)OR({" + CommercialRegisterModel.YEAR
				+ "}BETWEEN ?q2startYear AND ?q2endYear)OR({" + CommercialRegisterModel.YEAR
				+ "}BETWEEN ?q3startYear AND ?q3endYear)OR({" + CommercialRegisterModel.YEAR
				+ "}BETWEEN ?q4startYear AND ?q4endYear))" + "ORDER BY" + "{"
				+ CommercialRegisterModel.YEAR + "}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

		query.addQueryParameter("q1startYear", startYear+"-Q1");
		query.addQueryParameter("q1endYear", endYear+"-Q1");

		query.addQueryParameter("q2startYear", startYear+"-Q2");
		query.addQueryParameter("q2endYear", endYear+"-Q2");

		query.addQueryParameter("q3startYear", startYear+"-Q3");
		query.addQueryParameter("q3endYear", endYear+"-Q3");

		query.addQueryParameter("q4startYear", startYear+"-Q4");
		query.addQueryParameter("q4endYear", endYear+"-Q4");

		final SearchResult<CommercialRegisterModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

}
