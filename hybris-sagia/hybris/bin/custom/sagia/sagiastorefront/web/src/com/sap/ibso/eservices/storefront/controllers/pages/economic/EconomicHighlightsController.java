package com.sap.ibso.eservices.storefront.controllers.pages.economic;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.data.AnnualFundAssetsData;
import com.sap.ibso.eservices.facades.data.AnnualGrowthData;
import com.sap.ibso.eservices.facades.data.AnnualValueData;
import com.sap.ibso.eservices.facades.data.CapitalInformationData;
import com.sap.ibso.eservices.facades.data.NumberOfCommercialRegisterData;
import com.sap.ibso.eservices.facades.data.CapitalOfCommercialRegisterData;
import com.sap.ibso.eservices.facades.data.ForeignInvestmentGrowthData;
import com.sap.ibso.eservices.facades.data.ForeignInvestmentValueData;
import com.sap.ibso.eservices.facades.data.QuarterlyFundAssetsData;
import com.sap.ibso.eservices.facades.data.QuarterlyGrowthData;
import com.sap.ibso.eservices.facades.data.QuarterlyValueData;
import com.sap.ibso.eservices.facades.data.SAInternationalIndicesData;
import com.sap.ibso.eservices.facades.sagia.economic.EconomicFacade;
import com.sap.ibso.eservices.facades.sagia.economic.InfraLogisticsFacade;
import com.sap.ibso.eservices.facades.sagia.economic.InvestmentDataFacade;
import com.sap.ibso.eservices.facades.sagia.economic.SAInternationalIndicesFacade;
import com.sap.ibso.eservices.facades.sagia.economic.SaudiEconomicSectorsFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

@Controller
@RequestMapping(value = "/economicHighlights")
public class EconomicHighlightsController extends SagiaAbstractPageController {

	private static final String ECONOMIC_DASHBOARD_PAGE = "/economicHighlights/dashboard";

	private static final String SA_INTERNATIONAL_INDICES_PAGE = "/economicHighlights/saInternationalIndices";

	private static final String INFRASTRUCTURE_AND_LOGISTICS_PAGE = "/economicHighlights/infraLogistics";

	private static final String SAUDI_ECONOMIC_SECTORS_PAGE = "/economicHighlights/saudiEconomicSectors";

	private static final String INVESTMENT_DATA_PAGE = "/economicHighlights/investmentData";

	@Resource(name = "economicFacade")
	private EconomicFacade economicFacade;

	@Resource(name = "saInternationalIndicesFacade")
	private SAInternationalIndicesFacade saInternationalIndicesFacade;

	@Resource(name = "infraLogisticsFacade")
	private InfraLogisticsFacade infraLogisticsFacade;

	@Resource(name = "investmentDataFacade")
	private InvestmentDataFacade investmentDataFacade;

	@Resource(name = "saudiEconomicSectorsFacade")
	private SaudiEconomicSectorsFacade saudiEconomicSectorsFacade;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getEconomicDashboard(final Model model) throws CMSItemNotFoundException {

		model.addAttribute("realGDP", economicFacade.getRealGDPData());
		model.addAttribute("unemployment", economicFacade.getUnemploymentData());
		model.addAttribute("tradeBalance", economicFacade.getTradeBalanceData());
		model.addAttribute("totalReserveAssets", economicFacade.getTotalReserveAssetsData());
		model.addAttribute("saudiStockMarket", economicFacade.getSaudiStockMarketData());
		model.addAttribute("population", economicFacade.getPopulationData());
		model.addAttribute("moneySupply", economicFacade.getMoneySupplyData());
		model.addAttribute("interestRate", economicFacade.getInterestRateData());
		model.addAttribute("inflation", economicFacade.getInflationData());
		model.addAttribute("governmentReserve", economicFacade.getGovernmentReserveData());
		model.addAttribute("governmentDebt", economicFacade.getGovernmentDebtData());
		model.addAttribute("gDPPerCapita", economicFacade.getGDPPerCapitaData());
		model.addAttribute("fDIinflow", economicFacade.getFDIinflowData());
		model.addAttribute("exchangeRate", economicFacade.getExchangeRateData());
		model.addAttribute("creditRating", economicFacade.getCreditRatingData());
		model.addAttribute("budgetDeficit", economicFacade.getBudgetDeficitData());

		final ContentPageModel economicCMSPage = getContentPageForLabelOrId(ECONOMIC_DASHBOARD_PAGE);
		storeCmsPageInModel(model, economicCMSPage);
		setUpMetaDataForContentPage(model, economicCMSPage);

		return getViewForPage(model);
	}

	@RequestMapping(value = "/saInternationalIndices", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = "Accept=application/xml,application/json", produces = APPLICATION_JSON_VALUE)
	public String getSAInternationalIndices(final Model model,
			@RequestParam(defaultValue = "EaseOfProtectingMinorityInvestors") String indicator,
			@RequestParam(defaultValue = "2010") Integer startYear,
			@RequestParam(defaultValue = "2020") Integer endYear) throws CMSItemNotFoundException {

		List<SAInternationalIndicesData> saInternationalIndicesData = saInternationalIndicesFacade
				.getSAInternationalIndicesListData(indicator, startYear, endYear);
		String gsonJson = new Gson().toJson(saInternationalIndicesData).toString();
		model.addAttribute("saInternationalIndices", saInternationalIndicesData);
		model.addAttribute("saInternationalIndicesJsonData", new Gson().toJson(saInternationalIndicesData));
		model.addAttribute("gsonJson", gsonJson);
		final ContentPageModel economicCMSPage = getContentPageForLabelOrId(SA_INTERNATIONAL_INDICES_PAGE);
		storeCmsPageInModel(model, economicCMSPage);
		setUpMetaDataForContentPage(model, economicCMSPage);

		return getViewForPage(model);
	}

	@RequestMapping(value = "/infraLogistics", method = RequestMethod.GET)
	public String getInfraLogistics(final Model model) throws CMSItemNotFoundException {
		model.addAttribute("infraLogisticsLanding", infraLogisticsFacade.getInfraLogisticsLandingData());
		model.addAttribute("lengthOfNetwork", infraLogisticsFacade.getLengthOfNetworkData());
		model.addAttribute("infrastructure", infraLogisticsFacade.getInfrastructureData());
		model.addAttribute("employment", infraLogisticsFacade.getEmploymentData());
		model.addAttribute("totalArea", infraLogisticsFacade.getTotalAreaData());
		model.addAttribute("housingFacilities", infraLogisticsFacade.getHousingFacilitiesData());
		model.addAttribute("industrialCities", infraLogisticsFacade.getIndustrialCitiesData());
		model.addAttribute("lengthOfNetworkjson", new Gson().toJson(infraLogisticsFacade.getLengthOfNetworkData()));
		final ContentPageModel economicCMSPage = getContentPageForLabelOrId(INFRASTRUCTURE_AND_LOGISTICS_PAGE);
		storeCmsPageInModel(model, economicCMSPage);
		setUpMetaDataForContentPage(model, economicCMSPage);

		return getViewForPage(model);
	}

	@RequestMapping(value = "/saudiEconomicSectors", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = "Accept=application/xml,application/json", produces = APPLICATION_JSON_VALUE)
	public String getSaudiEconomicSectors(final Model model, @RequestParam(defaultValue = "2010") Integer year)
			throws CMSItemNotFoundException {
		model.addAttribute("realSector", saudiEconomicSectorsFacade.getRealSectorData());
		model.addAttribute("monetarySector", saudiEconomicSectorsFacade.getMonetarySectorData());
		model.addAttribute("fiscalSector", saudiEconomicSectorsFacade.getFiscalSectorData());
		model.addAttribute("externalSector", saudiEconomicSectorsFacade.getExternalSectorData());
		model.addAttribute("graduatesByDegreeJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGraduatesByDegreeData(year)));
		model.addAttribute("saudiPopulationJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getSaudiPopulationData(year)));
		model.addAttribute("populationByRegionJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getPopulationByRegionData(year)));
		model.addAttribute("saudiEmploymentByRegionJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getSaudiEmploymentByRegionData(year)));
		model.addAttribute("nonSaudiEmploymentByRegionJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getNonSaudiEmploymentByRegionData(year)));
		model.addAttribute("saudiEmploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getSaudiEmploymentData(year)));
		model.addAttribute("nonSaudiEmploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getNonsaudiEmploymentData(year)));
		model.addAttribute("avgMonthlyWagesJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getAvgMonthlyWagesData(year)));
		model.addAttribute("growthrateWagesJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGrowthrateWagesData(year)));
		model.addAttribute("labourPrivateSectorJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getLabourPrivateSectorData(year)));
		model.addAttribute("saudiUnemploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getSaudiUnemploymentData(year)));
		model.addAttribute("nonSaudiUnemploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getNonSaudiUnemploymentData(year)));
		model.addAttribute("overallUnemploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getOverallUnemploymentData(year)));
		final ContentPageModel economicCMSPage = getContentPageForLabelOrId(SAUDI_ECONOMIC_SECTORS_PAGE);
		storeCmsPageInModel(model, economicCMSPage);
		setUpMetaDataForContentPage(model, economicCMSPage);
		return getViewForPage(model);
	}

	@RequestMapping(value = "/investmentData", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = "Accept=application/xml,application/json", produces = APPLICATION_JSON_VALUE)
	public String getInvestmentData(final Model model,
			@RequestParam(defaultValue = "manufacturingLicenses") String sector,
			@RequestParam(defaultValue = "Annually") String period,
			@RequestParam(defaultValue = "2017") String startYear, @RequestParam(defaultValue = "2020") String endYear)
			throws CMSItemNotFoundException {
		if (sector.contains("%2C")) {
			sector = sector.replaceAll("%2C", ",");
		}

		if ("Annually".equalsIgnoreCase(period)) {
			List<AnnualValueData> annualValueListData = investmentDataFacade.getAnnualValueData(sector, period,
					startYear, endYear);
			model.addAttribute("annualValueJson", new Gson().toJson(annualValueListData));

			List<AnnualGrowthData> annualGrowthListData = investmentDataFacade.getAnnualGrowthData(sector, period,
					startYear, endYear);
			model.addAttribute("annualGrowthJson", new Gson().toJson(annualGrowthListData));
		}

		if ("Quarterly".equalsIgnoreCase(period)) {
			List<QuarterlyValueData> quarterlyValueListData = investmentDataFacade.getQuarterlyValueData(sector, period,
					startYear, endYear);
			model.addAttribute("quarterlyValueJson", new Gson().toJson(quarterlyValueListData));

			List<QuarterlyGrowthData> quarterlyGrowthListData = investmentDataFacade.getQuarterlyGrowthData(sector,
					period, startYear, endYear);
			model.addAttribute("quarterlyGrowthJson", new Gson().toJson(quarterlyGrowthListData));
		}

		List<ForeignInvestmentValueData> foreignInvestmentValueListData = investmentDataFacade
				.getForeignInvestmentValueData();
		List<ForeignInvestmentValueData> foreignInvestmentValueData = investmentDataFacade.getForeignInvestmentValueData();
		
		model.addAttribute("foreignInvestmentValueData",foreignInvestmentValueData);
		model.addAttribute("foreignInvestmentValueJson", new Gson().toJson(foreignInvestmentValueListData));

		List<ForeignInvestmentGrowthData> foreignInvestmentGrowthListData = investmentDataFacade
				.getForeignInvestmentGrowthData();
		List<ForeignInvestmentGrowthData> foreignInvestmentGrowthData = investmentDataFacade.getForeignInvestmentGrowthData();
		model.addAttribute("foreignInvestmentGrowthData", foreignInvestmentGrowthData);
		model.addAttribute("foreignInvestmentGrowthJson", new Gson().toJson(foreignInvestmentGrowthListData));

		if ("Annually".equalsIgnoreCase(period)) {
			List<AnnualFundAssetsData> annualFundAssetsListData = investmentDataFacade.getAnnualFundAssetsData(sector,
					period, startYear, endYear);
			model.addAttribute("annualFundAssetsJson", new Gson().toJson(annualFundAssetsListData));
		}

		if ("Quarterly".equalsIgnoreCase(period)) {
			List<QuarterlyFundAssetsData> quarterlyFundAssetsListData = investmentDataFacade
					.getQuarterlyFundAssetsData(sector, period, startYear, endYear);
			model.addAttribute("quarterlyFundAssetsJson", new Gson().toJson(quarterlyFundAssetsListData));
		}

		List<NumberOfCommercialRegisterData> numberOfCommercialRegisterListData = investmentDataFacade
				.getNumberOfCommercialRegisterData(sector, startYear, endYear);
		model.addAttribute("numberOfCommercialRegisterJson", new Gson().toJson(numberOfCommercialRegisterListData));

		List<CapitalOfCommercialRegisterData> capitalOfCommercialRegisterListData = investmentDataFacade
				.getCapitalOfCommercialRegisterData(sector, startYear, endYear);
		model.addAttribute("capitalOfCommercialRegisterJson", new Gson().toJson(capitalOfCommercialRegisterListData));

		List<CapitalInformationData> capitalInformationListData = investmentDataFacade.getCapitalInformationData(sector,
				startYear, endYear);
		model.addAttribute("capitalInformationJson", new Gson().toJson(capitalInformationListData));

		final ContentPageModel economicCMSPage = getContentPageForLabelOrId(INVESTMENT_DATA_PAGE);
		storeCmsPageInModel(model, economicCMSPage);
		setUpMetaDataForContentPage(model, economicCMSPage);

		return getViewForPage(model);
	}

}
