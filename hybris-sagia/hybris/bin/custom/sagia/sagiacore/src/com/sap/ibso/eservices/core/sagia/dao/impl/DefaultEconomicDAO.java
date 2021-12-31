package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.RealGDPModel;
import com.sap.ibso.eservices.core.model.UnemploymentModel;
import com.sap.ibso.eservices.core.model.TradeBalanceModel;
import com.sap.ibso.eservices.core.model.TotalReserveAssetsModel;
import com.sap.ibso.eservices.core.model.SaudiStockMarketModel;
import com.sap.ibso.eservices.core.model.PopulationModel;
import com.sap.ibso.eservices.core.model.MoneySupplyModel;
import com.sap.ibso.eservices.core.model.InterestRateModel;
import com.sap.ibso.eservices.core.model.InflationModel;
import com.sap.ibso.eservices.core.model.GovernmentReserveModel;
import com.sap.ibso.eservices.core.model.GovernmentDebtModel;
import com.sap.ibso.eservices.core.model.GDPPerCapitaModel;
import com.sap.ibso.eservices.core.model.FDIinflowModel;
import com.sap.ibso.eservices.core.model.ExchangeRateModel;
import com.sap.ibso.eservices.core.model.CreditRatingModel;
import com.sap.ibso.eservices.core.model.BudgetDeficitModel;


import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import com.sap.ibso.eservices.core.sagia.dao.EconomicDAO;

public class DefaultEconomicDAO implements EconomicDAO{
	
	private FlexibleSearchService flexibleSearchService;


	@Override
	public RealGDPModel getRealGDPModelBySearch()
	{
		final String queryString = "SELECT {" + RealGDPModel.PK + "} FROM {" + RealGDPModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<RealGDPModel> searchResult = getFlexibleSearchService().search(query);
		final RealGDPModel realGDPModel = searchResult.getResult().get(0);
		return realGDPModel;
	}

	@Override
	public UnemploymentModel getUnemploymentModelBySearch()
	{
		final String queryString = "SELECT {" + UnemploymentModel.PK + "} FROM {" + UnemploymentModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<UnemploymentModel> searchResult = getFlexibleSearchService().search(query);
		final UnemploymentModel unemploymentModel = searchResult.getResult().get(0);
		return unemploymentModel;
	}
	
	@Override
	public TradeBalanceModel getTradeBalanceModelBySearch()
	{
		final String queryString = "SELECT {" + TradeBalanceModel.PK + "} FROM {" + TradeBalanceModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<TradeBalanceModel> searchResult = getFlexibleSearchService().search(query);
		final TradeBalanceModel tradeBalanceModel = searchResult.getResult().get(0);
		return tradeBalanceModel;
	}
	
	@Override
	public TotalReserveAssetsModel getTotalReserveAssetsModelBySearch()
	{
		final String queryString = "SELECT {" + TotalReserveAssetsModel.PK + "} FROM {" + TotalReserveAssetsModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<TotalReserveAssetsModel> searchResult = getFlexibleSearchService().search(query);
		final TotalReserveAssetsModel totalReserveAssetsModel = searchResult.getResult().get(0);
		return totalReserveAssetsModel;
	}
	
	@Override
	public SaudiStockMarketModel getSaudiStockMarketModelBySearch()
	{
		final String queryString = "SELECT {" + SaudiStockMarketModel.PK + "} FROM {" + SaudiStockMarketModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<SaudiStockMarketModel> searchResult = getFlexibleSearchService().search(query);
		final SaudiStockMarketModel saudiStockMarketModel = searchResult.getResult().get(0);
		return saudiStockMarketModel;
	}
	
	@Override
	public PopulationModel getPopulationModelBySearch()
	{
		final String queryString = "SELECT {" + PopulationModel.PK + "} FROM {" + PopulationModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<PopulationModel> searchResult = getFlexibleSearchService().search(query);
		final PopulationModel populationModel = searchResult.getResult().get(0);
		return populationModel;
	}
	
	@Override
	public MoneySupplyModel getMoneySupplyModelBySearch()
	{
		final String queryString = "SELECT {" + MoneySupplyModel.PK + "} FROM {" + MoneySupplyModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<MoneySupplyModel> searchResult = getFlexibleSearchService().search(query);
		final MoneySupplyModel moneySupplyModel = searchResult.getResult().get(0);
		return moneySupplyModel;
	}
	
	
	@Override
	public InterestRateModel getInterestRateModelBySearch()
	{
		final String queryString = "SELECT {" + InterestRateModel.PK + "} FROM {" + InterestRateModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<InterestRateModel> searchResult = getFlexibleSearchService().search(query);
		final InterestRateModel interestRateModel = searchResult.getResult().get(0);
		return interestRateModel;
	}
	
	
	@Override
	public InflationModel getInflationModelBySearch()
	{
		final String queryString = "SELECT {" + InflationModel.PK + "} FROM {" + InflationModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<InflationModel> searchResult = getFlexibleSearchService().search(query);
		final InflationModel inflationModel = searchResult.getResult().get(0);
		return inflationModel;
	}
	
	@Override
	public GovernmentReserveModel getGovernmentReserveModelBySearch()
	{
		final String queryString = "SELECT {" + GovernmentReserveModel.PK + "} FROM {" + GovernmentReserveModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<GovernmentReserveModel> searchResult = getFlexibleSearchService().search(query);
		final GovernmentReserveModel governmentReserveModel = searchResult.getResult().get(0);
		return governmentReserveModel;
	}
	
	
	@Override
	public GovernmentDebtModel getGovernmentDebtModelBySearch()
	{
		final String queryString = "SELECT {" + GovernmentDebtModel.PK + "} FROM {" + GovernmentDebtModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<GovernmentDebtModel> searchResult = getFlexibleSearchService().search(query);
		final GovernmentDebtModel governmentDebtModel = searchResult.getResult().get(0);
		return governmentDebtModel;
	}
	
	@Override
	public GDPPerCapitaModel getGDPPerCapitaModelBySearch()
	{
		final String queryString = "SELECT {" + GDPPerCapitaModel.PK + "} FROM {" + GDPPerCapitaModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<GDPPerCapitaModel> searchResult = getFlexibleSearchService().search(query);
		final GDPPerCapitaModel gDPPerCapitaModel = searchResult.getResult().get(0);
		return gDPPerCapitaModel;
	}
	
	
	@Override
	public FDIinflowModel getFDIinflowModelBySearch()
	{
		final String queryString = "SELECT {" + FDIinflowModel.PK + "} FROM {" + FDIinflowModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<FDIinflowModel> searchResult = getFlexibleSearchService().search(query);
		final FDIinflowModel fDIinflowModel = searchResult.getResult().get(0);
		return fDIinflowModel;
	}
	
	
	@Override
	public ExchangeRateModel getExchangeRateModelBySearch()
	{
		final String queryString = "SELECT {" + ExchangeRateModel.PK + "} FROM {" + ExchangeRateModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<ExchangeRateModel> searchResult = getFlexibleSearchService().search(query);
		final ExchangeRateModel exchangeRateModel = searchResult.getResult().get(0);
		return exchangeRateModel;
	}
	
	@Override
	public CreditRatingModel getCreditRatingModelBySearch()
	{
		final String queryString = "SELECT {" + CreditRatingModel.PK + "} FROM {" + CreditRatingModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<CreditRatingModel> searchResult = getFlexibleSearchService().search(query);
		final CreditRatingModel creditRatingModel = searchResult.getResult().get(0);
		return creditRatingModel;
	}
	
	@Override
	public BudgetDeficitModel getBudgetDeficitModelBySearch()
	{
		final String queryString = "SELECT {" + BudgetDeficitModel.PK + "} FROM {" + BudgetDeficitModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<BudgetDeficitModel> searchResult = getFlexibleSearchService().search(query);
		final BudgetDeficitModel budgetDeficitModel = searchResult.getResult().get(0);
		return budgetDeficitModel;
	}
	
	
	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}


	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
