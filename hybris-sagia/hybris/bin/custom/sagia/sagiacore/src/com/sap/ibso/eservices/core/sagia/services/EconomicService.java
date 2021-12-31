package com.sap.ibso.eservices.core.sagia.services;

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

public interface EconomicService {
	
	RealGDPModel getRealGDPModel();
	UnemploymentModel getUnemploymentModel();
	TradeBalanceModel getTradeBalanceModel();
	TotalReserveAssetsModel getTotalReserveAssetsModel();
	SaudiStockMarketModel getSaudiStockMarketModel();
	PopulationModel getPopulationModel();
	MoneySupplyModel getMoneySupplyModel();
	InterestRateModel getInterestRateModel();
	InflationModel getInflationModel();
	GovernmentReserveModel getGovernmentReserveModel();
	GovernmentDebtModel getGovernmentDebtModel();
	GDPPerCapitaModel getGDPPerCapitaModel();
	FDIinflowModel getFDIinflowModel();
	ExchangeRateModel getExchangeRateModel();
	CreditRatingModel getCreditRatingModel();
	BudgetDeficitModel getBudgetDeficitModel();
	

}
