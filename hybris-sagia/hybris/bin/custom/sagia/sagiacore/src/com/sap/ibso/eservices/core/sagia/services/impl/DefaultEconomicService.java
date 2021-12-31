package com.sap.ibso.eservices.core.sagia.services.impl;

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

import com.sap.ibso.eservices.core.sagia.dao.EconomicDAO;
import com.sap.ibso.eservices.core.sagia.services.EconomicService;

public class DefaultEconomicService implements EconomicService{
	
	private EconomicDAO economicDAO;

	@Override
	public RealGDPModel getRealGDPModel()
	{
		final RealGDPModel realGDP = getEconomicDAO().getRealGDPModelBySearch();
		return realGDP;
	}
	
	@Override
	public UnemploymentModel getUnemploymentModel()
	{
		final UnemploymentModel unemployment = getEconomicDAO().getUnemploymentModelBySearch();
		return unemployment;
	}

	@Override
	public TradeBalanceModel getTradeBalanceModel()
	{
		final TradeBalanceModel tradeBalance = getEconomicDAO().getTradeBalanceModelBySearch();
		return tradeBalance;
	}

	@Override
	public TotalReserveAssetsModel getTotalReserveAssetsModel()
	{
		final TotalReserveAssetsModel totalReserveAssets = getEconomicDAO().getTotalReserveAssetsModelBySearch();
		return totalReserveAssets;
	}

	@Override
	public SaudiStockMarketModel getSaudiStockMarketModel()
	{
		final SaudiStockMarketModel saudiStockMarket = getEconomicDAO().getSaudiStockMarketModelBySearch();
		return saudiStockMarket;
	}

	@Override
	public PopulationModel getPopulationModel()
	{
		final PopulationModel populationModel = getEconomicDAO().getPopulationModelBySearch();
		return populationModel;
	}

	@Override
	public MoneySupplyModel getMoneySupplyModel()
	{
		final MoneySupplyModel moneySupply = getEconomicDAO().getMoneySupplyModelBySearch();
		return moneySupply;
	}

	@Override
	public InterestRateModel getInterestRateModel()
	{
		final InterestRateModel interestRate = getEconomicDAO().getInterestRateModelBySearch();
		return interestRate;
	}

	@Override
	public InflationModel getInflationModel()
	{
		final InflationModel inflation = getEconomicDAO().getInflationModelBySearch();
		return inflation;
	}

	@Override
	public GovernmentReserveModel getGovernmentReserveModel()
	{
		final GovernmentReserveModel governmentReserve = getEconomicDAO().getGovernmentReserveModelBySearch();
		return governmentReserve;
	}

	@Override
	public GovernmentDebtModel getGovernmentDebtModel()
	{
		final GovernmentDebtModel governmentDebt = getEconomicDAO().getGovernmentDebtModelBySearch();
		return governmentDebt;
	}

	@Override
	public GDPPerCapitaModel getGDPPerCapitaModel()
	{
		final GDPPerCapitaModel gDPPerCapita = getEconomicDAO().getGDPPerCapitaModelBySearch();
		return gDPPerCapita;
	}

	@Override
	public FDIinflowModel getFDIinflowModel()
	{
		final FDIinflowModel fDIinflow = getEconomicDAO().getFDIinflowModelBySearch();
		return fDIinflow;
	}

	@Override
	public ExchangeRateModel getExchangeRateModel()
	{
		final ExchangeRateModel exchangeRate = getEconomicDAO().getExchangeRateModelBySearch();
		return exchangeRate;
	}

	@Override
	public CreditRatingModel getCreditRatingModel()
	{
		final CreditRatingModel creditRating = getEconomicDAO().getCreditRatingModelBySearch();
		return creditRating;
	}

	@Override
	public BudgetDeficitModel getBudgetDeficitModel()
	{
		final BudgetDeficitModel budgetDeficit = getEconomicDAO().getBudgetDeficitModelBySearch();
		return budgetDeficit;
	}

	

	public EconomicDAO getEconomicDAO() {
		return economicDAO;
	}

	public void setEconomicDAO(EconomicDAO economicDAO) {
		this.economicDAO = economicDAO;
	}

	
}
