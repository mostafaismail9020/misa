package com.sap.ibso.eservices.core.sagia.dao;

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


public interface EconomicDAO {

	
	RealGDPModel getRealGDPModelBySearch();
	UnemploymentModel getUnemploymentModelBySearch();
	TradeBalanceModel getTradeBalanceModelBySearch();
	TotalReserveAssetsModel getTotalReserveAssetsModelBySearch();
	SaudiStockMarketModel getSaudiStockMarketModelBySearch();
	PopulationModel getPopulationModelBySearch();
	MoneySupplyModel getMoneySupplyModelBySearch();
	InterestRateModel getInterestRateModelBySearch();
	InflationModel getInflationModelBySearch();
	GovernmentReserveModel getGovernmentReserveModelBySearch();
	GovernmentDebtModel getGovernmentDebtModelBySearch();
	GDPPerCapitaModel getGDPPerCapitaModelBySearch();
	FDIinflowModel getFDIinflowModelBySearch();
	ExchangeRateModel getExchangeRateModelBySearch();
	CreditRatingModel getCreditRatingModelBySearch();
	BudgetDeficitModel getBudgetDeficitModelBySearch();
}
