package com.sap.ibso.eservices.facades.sagia.economic.impl;

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


import com.sap.ibso.eservices.core.sagia.services.EconomicService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import com.sap.ibso.eservices.facades.data.RealGDPData;
import com.sap.ibso.eservices.facades.data.UnemploymentData;
import com.sap.ibso.eservices.facades.sagia.economic.EconomicFacade;
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

public class DefaultEconomicFacade implements EconomicFacade{
	
	private EconomicService economicService;

	private Converter<RealGDPModel, RealGDPData> realGDPConverter;
	private Converter<UnemploymentModel, UnemploymentData> unemploymentConverter;
	private Converter<TradeBalanceModel, TradeBalanceData> tradeBalanceConverter;
	private Converter<TotalReserveAssetsModel, TotalReserveAssetsData> totalReserveAssetsConverter;
	private Converter<SaudiStockMarketModel, SaudiStockMarketData> saudiStockMarketConverter;
	private Converter<PopulationModel, PopulationData> populationConverter;
	private Converter<MoneySupplyModel, MoneySupplyData> moneySupplyConverter;
	private Converter<InterestRateModel, InterestRateData> interestRateConverter;
	private Converter<InflationModel, InflationData> inflationConverter;
	private Converter<GovernmentReserveModel, GovernmentReserveData> governmentReserveConverter;
	private Converter<GovernmentDebtModel, GovernmentDebtData> governmentDebtConverter;
	private Converter<GDPPerCapitaModel, GDPPerCapitaData> gDPPerCapitaConverter;
	private Converter<FDIinflowModel, FDIinflowData> fDIinflowConverter;
	private Converter<ExchangeRateModel, ExchangeRateData> exchangeRateConverter;
	private Converter<CreditRatingModel, CreditRatingData> creditRatingConverter;
	private Converter<BudgetDeficitModel, BudgetDeficitData> budgetDeficitConverter;


	@Override
	public RealGDPData getRealGDPData()
	{
		final RealGDPModel realGDPModel = getEconomicService().getRealGDPModel();

		final RealGDPData realGDPData = getRealGDPConverter().convert(realGDPModel);

		return realGDPData;
	}

	@Override
	public UnemploymentData getUnemploymentData()
	{
		final UnemploymentModel unemploymentModel = getEconomicService().getUnemploymentModel();

		final UnemploymentData unemploymentData = getUnemploymentConverter().convert(unemploymentModel);

		return unemploymentData;
	}
	
	@Override
	public TradeBalanceData getTradeBalanceData()
	{
		final TradeBalanceModel tradeBalanceModel = getEconomicService().getTradeBalanceModel();

		final TradeBalanceData tradeBalanceData = getTradeBalanceConverter().convert(tradeBalanceModel);

		return tradeBalanceData;
	}
	
	@Override
	public TotalReserveAssetsData getTotalReserveAssetsData()
	{
		final TotalReserveAssetsModel totalReserveAssetsModel = getEconomicService().getTotalReserveAssetsModel();

		final TotalReserveAssetsData totalReserveAssetsData = getTotalReserveAssetsConverter().convert(totalReserveAssetsModel);

		return totalReserveAssetsData;
	}
	
	@Override
	public SaudiStockMarketData getSaudiStockMarketData()
	{
		final SaudiStockMarketModel saudiStockMarketModel = getEconomicService().getSaudiStockMarketModel();

		final SaudiStockMarketData saudiStockMarketData = getSaudiStockMarketConverter().convert(saudiStockMarketModel);

		return saudiStockMarketData;
	}
	
	@Override
	public PopulationData getPopulationData()
	{
		final PopulationModel populationModel = getEconomicService().getPopulationModel();

		final PopulationData populationData = getPopulationConverter().convert(populationModel);

		return populationData;
	}
	
	@Override
	public MoneySupplyData getMoneySupplyData()
	{
		final MoneySupplyModel moneySupplyModel = getEconomicService().getMoneySupplyModel();

		final MoneySupplyData moneySupplyData = getMoneySupplyConverter().convert(moneySupplyModel);

		return moneySupplyData;
	}
	
	@Override
	public InterestRateData getInterestRateData()
	{
		final InterestRateModel interestRateModel = getEconomicService().getInterestRateModel();

		final InterestRateData interestRateData = getInterestRateConverter().convert(interestRateModel);

		return interestRateData;
	}
	
	@Override
	public InflationData getInflationData()
	{
		final InflationModel inflationModel = getEconomicService().getInflationModel();

		final InflationData inflationData = getInflationConverter().convert(inflationModel);

		return inflationData;
	}
	
	@Override
	public GovernmentReserveData getGovernmentReserveData()
	{
		final GovernmentReserveModel governmentReserveModel = getEconomicService().getGovernmentReserveModel();

		final GovernmentReserveData governmentReserveData = getGovernmentReserveConverter().convert(governmentReserveModel);

		return governmentReserveData;
	}
	
	@Override
	public GovernmentDebtData getGovernmentDebtData()
	{
		final GovernmentDebtModel governmentDebtModel = getEconomicService().getGovernmentDebtModel();

		final GovernmentDebtData governmentDebtData = getGovernmentDebtConverter().convert(governmentDebtModel);

		return governmentDebtData;
	}
	
	@Override
	public GDPPerCapitaData getGDPPerCapitaData()
	{
		final GDPPerCapitaModel gDPPerCapitaModel = getEconomicService().getGDPPerCapitaModel();

		final GDPPerCapitaData gDPPerCapitaData = getgDPPerCapitaConverter().convert(gDPPerCapitaModel);
		
		return gDPPerCapitaData;
	}
	
	@Override
	public FDIinflowData getFDIinflowData()
	{
		final FDIinflowModel fDIinflowModel = getEconomicService().getFDIinflowModel();

		final FDIinflowData fDIinflowData = getfDIinflowConverter().convert(fDIinflowModel);

		return fDIinflowData;
	}
	
	@Override
	public ExchangeRateData getExchangeRateData()
	{
		final ExchangeRateModel exchangeRateModel = getEconomicService().getExchangeRateModel();

		final ExchangeRateData exchangeRateData = getExchangeRateConverter().convert(exchangeRateModel);

		return exchangeRateData;
	}
	
	@Override
	public CreditRatingData getCreditRatingData()
	{
		final CreditRatingModel creditRatingModel = getEconomicService().getCreditRatingModel();

		final CreditRatingData creditRatingData = getCreditRatingConverter().convert(creditRatingModel);

		return creditRatingData;
	}
	
	@Override
	public BudgetDeficitData getBudgetDeficitData()
	{
		final BudgetDeficitModel budgetDeficitModel = getEconomicService().getBudgetDeficitModel();

		final BudgetDeficitData budgetDeficitData = getBudgetDeficitConverter().convert(budgetDeficitModel);

		return budgetDeficitData;
	}
	


	/**
	 * @return the unemploymentConverter
	 */
	public Converter<UnemploymentModel, UnemploymentData> getUnemploymentConverter() {
		return unemploymentConverter;
	}



	/**
	 * @param unemploymentConverter the unemploymentConverter to set
	 */
	public void setUnemploymentConverter(Converter<UnemploymentModel, UnemploymentData> unemploymentConverter) {
		this.unemploymentConverter = unemploymentConverter;
	}



	/**
	 * @return the tradeBalanceConverter
	 */
	public Converter<TradeBalanceModel, TradeBalanceData> getTradeBalanceConverter() {
		return tradeBalanceConverter;
	}



	/**
	 * @param tradeBalanceConverter the tradeBalanceConverter to set
	 */
	public void setTradeBalanceConverter(Converter<TradeBalanceModel, TradeBalanceData> tradeBalanceConverter) {
		this.tradeBalanceConverter = tradeBalanceConverter;
	}



	/**
	 * @return the totalReserveAssetsConverter
	 */
	public Converter<TotalReserveAssetsModel, TotalReserveAssetsData> getTotalReserveAssetsConverter() {
		return totalReserveAssetsConverter;
	}



	/**
	 * @param totalReserveAssetsConverter the totalReserveAssetsConverter to set
	 */
	public void setTotalReserveAssetsConverter(
			Converter<TotalReserveAssetsModel, TotalReserveAssetsData> totalReserveAssetsConverter) {
		this.totalReserveAssetsConverter = totalReserveAssetsConverter;
	}



	/**
	 * @return the saudiStockMarketConverter
	 */
	public Converter<SaudiStockMarketModel, SaudiStockMarketData> getSaudiStockMarketConverter() {
		return saudiStockMarketConverter;
	}



	/**
	 * @param saudiStockMarketConverter the saudiStockMarketConverter to set
	 */
	public void setSaudiStockMarketConverter(
			Converter<SaudiStockMarketModel, SaudiStockMarketData> saudiStockMarketConverter) {
		this.saudiStockMarketConverter = saudiStockMarketConverter;
	}



	/**
	 * @return the populationConverter
	 */
	public Converter<PopulationModel, PopulationData> getPopulationConverter() {
		return populationConverter;
	}



	/**
	 * @param populationConverter the populationConverter to set
	 */
	public void setPopulationConverter(Converter<PopulationModel, PopulationData> populationConverter) {
		this.populationConverter = populationConverter;
	}



	/**
	 * @return the moneySupplyConverter
	 */
	public Converter<MoneySupplyModel, MoneySupplyData> getMoneySupplyConverter() {
		return moneySupplyConverter;
	}



	/**
	 * @param moneySupplyConverter the moneySupplyConverter to set
	 */
	public void setMoneySupplyConverter(Converter<MoneySupplyModel, MoneySupplyData> moneySupplyConverter) {
		this.moneySupplyConverter = moneySupplyConverter;
	}



	/**
	 * @return the interestRateConverter
	 */
	public Converter<InterestRateModel, InterestRateData> getInterestRateConverter() {
		return interestRateConverter;
	}



	/**
	 * @param interestRateConverter the interestRateConverter to set
	 */
	public void setInterestRateConverter(Converter<InterestRateModel, InterestRateData> interestRateConverter) {
		this.interestRateConverter = interestRateConverter;
	}



	/**
	 * @return the inflationConverter
	 */
	public Converter<InflationModel, InflationData> getInflationConverter() {
		return inflationConverter;
	}



	/**
	 * @param inflationConverter the inflationConverter to set
	 */
	public void setInflationConverter(Converter<InflationModel, InflationData> inflationConverter) {
		this.inflationConverter = inflationConverter;
	}



	/**
	 * @return the governmentReserveConverter
	 */
	public Converter<GovernmentReserveModel, GovernmentReserveData> getGovernmentReserveConverter() {
		return governmentReserveConverter;
	}



	/**
	 * @param governmentReserveConverter the governmentReserveConverter to set
	 */
	public void setGovernmentReserveConverter(
			Converter<GovernmentReserveModel, GovernmentReserveData> governmentReserveConverter) {
		this.governmentReserveConverter = governmentReserveConverter;
	}



	/**
	 * @return the governmentDebtConverter
	 */
	public Converter<GovernmentDebtModel, GovernmentDebtData> getGovernmentDebtConverter() {
		return governmentDebtConverter;
	}



	/**
	 * @param governmentDebtConverter the governmentDebtConverter to set
	 */
	public void setGovernmentDebtConverter(Converter<GovernmentDebtModel, GovernmentDebtData> governmentDebtConverter) {
		this.governmentDebtConverter = governmentDebtConverter;
	}



	/**
	 * @return the gDPPerCapitaConverter
	 */
	public Converter<GDPPerCapitaModel, GDPPerCapitaData> getgDPPerCapitaConverter() {
		return gDPPerCapitaConverter;
	}



	/**
	 * @param gDPPerCapitaConverter the gDPPerCapitaConverter to set
	 */
	public void setgDPPerCapitaConverter(Converter<GDPPerCapitaModel, GDPPerCapitaData> gDPPerCapitaConverter) {
		this.gDPPerCapitaConverter = gDPPerCapitaConverter;
	}



	/**
	 * @return the fDIinflowConverter
	 */
	public Converter<FDIinflowModel, FDIinflowData> getfDIinflowConverter() {
		return fDIinflowConverter;
	}



	/**
	 * @param fDIinflowConverter the fDIinflowConverter to set
	 */
	public void setfDIinflowConverter(Converter<FDIinflowModel, FDIinflowData> fDIinflowConverter) {
		this.fDIinflowConverter = fDIinflowConverter;
	}



	/**
	 * @return the exchangeRateConverter
	 */
	public Converter<ExchangeRateModel, ExchangeRateData> getExchangeRateConverter() {
		return exchangeRateConverter;
	}



	/**
	 * @param exchangeRateConverter the exchangeRateConverter to set
	 */
	public void setExchangeRateConverter(Converter<ExchangeRateModel, ExchangeRateData> exchangeRateConverter) {
		this.exchangeRateConverter = exchangeRateConverter;
	}



	/**
	 * @return the creditRatingConverter
	 */
	public Converter<CreditRatingModel, CreditRatingData> getCreditRatingConverter() {
		return creditRatingConverter;
	}



	/**
	 * @param creditRatingConverter the creditRatingConverter to set
	 */
	public void setCreditRatingConverter(Converter<CreditRatingModel, CreditRatingData> creditRatingConverter) {
		this.creditRatingConverter = creditRatingConverter;
	}



	/**
	 * @return the budgetDeficitConverter
	 */
	public Converter<BudgetDeficitModel, BudgetDeficitData> getBudgetDeficitConverter() {
		return budgetDeficitConverter;
	}



	/**
	 * @param budgetDeficitConverter the budgetDeficitConverter to set
	 */
	public void setBudgetDeficitConverter(Converter<BudgetDeficitModel, BudgetDeficitData> budgetDeficitConverter) {
		this.budgetDeficitConverter = budgetDeficitConverter;
	}



	public EconomicService getEconomicService() {
		return economicService;
	}



	public void setEconomicService(EconomicService economicService) {
		this.economicService = economicService;
	}



	/**
	 * @return the realGDPConverter
	 */
	public Converter<RealGDPModel, RealGDPData> getRealGDPConverter()
	{
		return realGDPConverter;
	}


	/**
	 * @param realGDPConverter
	 *           the realGDPConverter to set
	 */
	public void setRealGDPConverter(final Converter<RealGDPModel, RealGDPData> realGDPConverter)
	{
		this.realGDPConverter = realGDPConverter;
	}
  
  }
 