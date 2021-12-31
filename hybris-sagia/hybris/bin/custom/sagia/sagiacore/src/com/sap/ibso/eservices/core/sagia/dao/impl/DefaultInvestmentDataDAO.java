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
				+ " WHERE {" + AnnualValueModel.YEAR + "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{"
				+ AnnualValueModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<AnnualValueModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<AnnualGrowthModel> getAnnualGrowthModelBySearch(String sector, String period, String startYear,
			String endYear) {
		final String queryString = "SELECT {" + AnnualGrowthModel.PK + "} FROM {" + AnnualGrowthModel._TYPECODE + "}"
				+ " WHERE {" + AnnualGrowthModel.YEAR + "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{"
				+ AnnualGrowthModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<AnnualGrowthModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<QuarterlyValueModel> getQuarterlyValueModelBySearch(String sector, String period, String startYear,
			String endYear) {

		final String queryString = "SELECT {" + QuarterlyValueModel.PK + "} FROM {" + QuarterlyValueModel._TYPECODE
				+ "}" + " WHERE ({" + QuarterlyValueModel.YEAR + "}BETWEEN 'Qtr1-" + startYear + "' AND 'Qtr1-"
				+ endYear + "')OR({" + QuarterlyValueModel.YEAR + "}BETWEEN 'Qtr2-" + startYear + "' AND 'Qtr2-"
				+ endYear + "')OR({" + QuarterlyValueModel.YEAR + "}BETWEEN 'Qtr3-" + startYear + "' AND 'Qtr3-"
				+ endYear + "')OR({" + QuarterlyValueModel.YEAR + "}BETWEEN 'Qtr4-" + startYear + "' AND 'Qtr4-"
				+ endYear + "')" + "ORDER BY" + "{" + QuarterlyValueModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<QuarterlyValueModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<QuarterlyGrowthModel> getQuarterlyGrowthModelBySearch(String sector, String period, String startYear,
			String endYear) {
		final String queryString = "SELECT {" + QuarterlyGrowthModel.PK + "} FROM {" + QuarterlyGrowthModel._TYPECODE
				+ "}" + " WHERE ({" + QuarterlyGrowthModel.YEAR + "}BETWEEN 'Qtr1-" + startYear + "' AND 'Qtr1-"
				+ endYear + "')OR({" + QuarterlyGrowthModel.YEAR + "}BETWEEN 'Qtr2-" + startYear + "' AND 'Qtr2-"
				+ endYear + "')OR({" + QuarterlyGrowthModel.YEAR + "}BETWEEN 'Qtr3-" + startYear + "' AND 'Qtr3-"
				+ endYear + "')OR({" + QuarterlyGrowthModel.YEAR + "}BETWEEN 'Qtr4-" + startYear + "' AND 'Qtr4-"
				+ endYear + "')" + "ORDER BY" + "{" + QuarterlyGrowthModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
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
				+ startYear + " AND " + endYear + " ORDER BY{" + CapitalInformationModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<CapitalInformationModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<AnnualFundAssetsModel> getAnnualFundAssetsModelBySearch(String indicator, String period,
			String startYear, String endYear) {
		final String queryString = "SELECT {" + AnnualFundAssetsModel.PK + "} FROM {" + AnnualFundAssetsModel._TYPECODE
				+ "}" + " WHERE {" + AnnualFundAssetsModel.YEAR + "} BETWEEN " + startYear + " AND " + endYear
				+ " ORDER BY{" + AnnualFundAssetsModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<AnnualFundAssetsModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<NumberOfCommercialRegisterModel> getNumberOfCommercialRegisterModelBySearch(String indicator,
			String startYear, String endYear) {
		final String queryString = "SELECT {" + NumberOfCommercialRegisterModel.PK + "} FROM {"
				+ NumberOfCommercialRegisterModel._TYPECODE + "}" + " WHERE {" + NumberOfCommercialRegisterModel.YEAR
				+ "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{" + NumberOfCommercialRegisterModel.YEAR
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<NumberOfCommercialRegisterModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<CapitalOfCommercialRegisterModel> getCapitalOfCommercialRegisterModelBySearch(String indicator,
			String startYear, String endYear) {
		final String queryString = "SELECT {" + CapitalOfCommercialRegisterModel.PK + "} FROM {"
				+ CapitalOfCommercialRegisterModel._TYPECODE + "}" + " WHERE {" + CapitalOfCommercialRegisterModel.YEAR
				+ "} BETWEEN " + startYear + " AND " + endYear + " ORDER BY{" + CapitalOfCommercialRegisterModel.YEAR
				+ "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<CapitalOfCommercialRegisterModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

	@Override
	public List<QuarterlyFundAssetsModel> getQuarterlyFundAssetsModelBySearch(String sector, String period,
			String startYear, String endYear) {
		final String queryString = "SELECT {" + QuarterlyFundAssetsModel.PK + "} FROM {"
				+ QuarterlyFundAssetsModel._TYPECODE + "}" + " WHERE ({" + QuarterlyFundAssetsModel.PERIOD
				+ "}BETWEEN 'Q1-" + startYear + "' AND 'Q1-" + endYear + "')OR({" + QuarterlyFundAssetsModel.PERIOD
				+ "}BETWEEN 'Q2-" + startYear + "' AND 'Q2-" + endYear + "')OR({" + QuarterlyFundAssetsModel.PERIOD
				+ "}BETWEEN 'Q3-" + startYear + "' AND 'Q3-" + endYear + "')OR({" + QuarterlyFundAssetsModel.PERIOD
				+ "}BETWEEN 'Q4-" + startYear + "' AND 'Q4-" + endYear + "')" + "ORDER BY" + "{"
				+ QuarterlyFundAssetsModel.PERIOD + "}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		
		final SearchResult<QuarterlyFundAssetsModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

}
