package com.sap.ibso.eservices.facades.sagia.economic;

import com.sap.ibso.eservices.facades.data.RealGDPData;
import com.sap.ibso.eservices.facades.data.UnemploymentData;
import com.sap.ibso.eservices.facades.data.TradeBalanceData;
import com.sap.ibso.eservices.facades.data.TotalReserveAssetsData;
import com.sap.ibso.eservices.facades.data.SaudiStockMarketData;
import com.sap.ibso.eservices.facades.data.PopulationData;
import com.sap.ibso.eservices.facades.data.MoneySupplyData;
import com.sap.ibso.eservices.facades.data.InterestRateData;
import com.sap.ibso.eservices.facades.data.InflationData;
import com.sap.ibso.eservices.facades.data.GovernmentReserveData;
import com.sap.ibso.eservices.facades.data.GovernmentDebtData;
import com.sap.ibso.eservices.facades.data.GDPPerCapitaData;
import com.sap.ibso.eservices.facades.data.FDIinflowData;
import com.sap.ibso.eservices.facades.data.ExchangeRateData;
import com.sap.ibso.eservices.facades.data.CreditRatingData;
import com.sap.ibso.eservices.facades.data.BudgetDeficitData;

public interface EconomicFacade {

	RealGDPData getRealGDPData();

	UnemploymentData getUnemploymentData();

	TradeBalanceData getTradeBalanceData();

	TotalReserveAssetsData getTotalReserveAssetsData();

	SaudiStockMarketData getSaudiStockMarketData();

	PopulationData getPopulationData();

	MoneySupplyData getMoneySupplyData();

	InterestRateData getInterestRateData();

	InflationData getInflationData();

	GovernmentReserveData getGovernmentReserveData();

	GovernmentDebtData getGovernmentDebtData();

	GDPPerCapitaData getGDPPerCapitaData();

	FDIinflowData getFDIinflowData();

	ExchangeRateData getExchangeRateData();

	CreditRatingData getCreditRatingData();

	BudgetDeficitData getBudgetDeficitData();

}
