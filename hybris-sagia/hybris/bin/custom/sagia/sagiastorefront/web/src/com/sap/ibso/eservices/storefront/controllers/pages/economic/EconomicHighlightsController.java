package com.sap.ibso.eservices.storefront.controllers.pages.economic;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.annotation.Resource;

import com.sap.ibso.eservices.facades.data.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.sagia.economic.EconomicFacade;
import com.sap.ibso.eservices.facades.sagia.economic.InfraLogisticsFacade;
import com.sap.ibso.eservices.facades.sagia.economic.InvestmentDataFacade;
import com.sap.ibso.eservices.facades.sagia.economic.SAInternationalIndicesFacade;
import com.sap.ibso.eservices.facades.sagia.economic.SaudiEconomicSectorsFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;

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

		model.addAttribute("dashboardCards",economicFacade.getAllDashboardData());

		model.addAttribute("creditRating", economicFacade.getCreditRatingData());

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
		String gsonJson = new Gson().toJson(saInternationalIndicesData);
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
		model.addAttribute("lengthOfNetworkjson", new Gson().toJson(infraLogisticsFacade.getLengthOfNetworkData()));
		model.addAttribute("privateIndustrialCities", new Gson().toJson(infraLogisticsFacade.getPrivateCitiesListData()));
		model.addAttribute("privateIndustrialCitiesData", infraLogisticsFacade.getPrivateCitiesListData());
		model.addAttribute("infrastructureLogisticsData", infraLogisticsFacade.getInfrastructureLogisticsData());
		final ContentPageModel economicCMSPage = getContentPageForLabelOrId(INFRASTRUCTURE_AND_LOGISTICS_PAGE);
		storeCmsPageInModel(model, economicCMSPage);
		setUpMetaDataForContentPage(model, economicCMSPage);

		return getViewForPage(model);
	}

	@RequestMapping(value = "/saudiEconomicSectors", method = { RequestMethod.GET,
			RequestMethod.POST }, headers = "Accept=application/xml,application/json", produces = APPLICATION_JSON_VALUE)
	public String getSaudiEconomicSectors(final Model model, @RequestParam(defaultValue = "2010") Integer year)
			throws CMSItemNotFoundException {
		model.addAttribute("economicSector", saudiEconomicSectorsFacade.getEconomicSectorData());
		model.addAttribute("saudiPopulationJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getPopulationDistributionData("SaudiPopulation", year)));
		model.addAttribute("labourPrivateSectorJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getPopulationDistributionData("LabourPrivateSector", year)));
		model.addAttribute("avgMonthlyWagesJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGenderDistributionData("AvgMonthlyWages", year)));
		model.addAttribute("growthrateWagesJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGenderDistributionData("GrowthRateWages", year)));
		model.addAttribute("saudiEmploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGenderDistributionData("SaudiEmployment", year)));
		model.addAttribute("nonSaudiEmploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGenderDistributionData("NonSaudiEmployment", year)));
		model.addAttribute("saudiUnemploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGenderDistributionData("SaudiUnemployment", year)));
		model.addAttribute("nonSaudiUnemploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGenderDistributionData("NonSaudiUnemployment", year)));
		model.addAttribute("overallUnemploymentJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGenderDistributionData("OverallUnemployment", year)));
		model.addAttribute("populationByRegionJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getRegionDistributionData("PopulationByRegion", year)));
		model.addAttribute("saudiEmploymentByRegionJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getRegionDistributionData("SaudiEmploymentByRegion", year)));
		model.addAttribute("nonSaudiEmploymentByRegionJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getRegionDistributionData("NonSaudiEmploymentByRegion", year)));
		model.addAttribute("graduatesByDegreeJson",
				new Gson().toJson(saudiEconomicSectorsFacade.getGraduatesByDegreeData(year)));
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
			@RequestParam(defaultValue = "2017") String startYear, @RequestParam(defaultValue = "2022") String endYear)
			throws CMSItemNotFoundException {
		if (sector.contains("%2C")) {
			sector = sector.replaceAll("%2C", ",");
		}

		if ("Annually".equalsIgnoreCase(period)) {
			List<AQValueGrowthData> annualValueListData = investmentDataFacade.getAnnualValueData(XSSFilterUtil.filter(sector), XSSFilterUtil.filter(period),
					XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
			model.addAttribute("annualValueJson", new Gson().toJson(annualValueListData));

			List<AQValueGrowthData> annualGrowthListData = investmentDataFacade.getAnnualGrowthData(XSSFilterUtil.filter(sector), XSSFilterUtil.filter(period),
					XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
			model.addAttribute("annualGrowthJson", new Gson().toJson(annualGrowthListData));
		}

		if ("Quarterly".equalsIgnoreCase(period)) {
			List<AQValueGrowthData> quarterlyValueListData = investmentDataFacade.getQuarterlyValueData(XSSFilterUtil.filter(sector), XSSFilterUtil.filter(period),
					XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
			model.addAttribute("quarterlyValueJson", new Gson().toJson(quarterlyValueListData));

			List<AQValueGrowthData> quarterlyGrowthListData = investmentDataFacade.getQuarterlyGrowthData(XSSFilterUtil.filter(sector),
					XSSFilterUtil.filter(period), XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
			model.addAttribute("quarterlyGrowthJson", new Gson().toJson(quarterlyGrowthListData));
		}

		List<ForeignInvestmentData> foreignInvestmentValueListData = investmentDataFacade
				.getForeignInvestmentValueData();
		List<ForeignInvestmentData> foreignInvestmentValueData = investmentDataFacade.getForeignInvestmentValueData();
		
		model.addAttribute("foreignInvestmentValueData",foreignInvestmentValueData);
		model.addAttribute("foreignInvestmentValueJson", new Gson().toJson(foreignInvestmentValueListData));

		List<ForeignInvestmentData> foreignInvestmentGrowthListData = investmentDataFacade
				.getForeignInvestmentGrowthData();
		List<ForeignInvestmentData> foreignInvestmentGrowthData = investmentDataFacade.getForeignInvestmentGrowthData();
		model.addAttribute("foreignInvestmentGrowthData", foreignInvestmentGrowthData);
		model.addAttribute("foreignInvestmentGrowthJson", new Gson().toJson(foreignInvestmentGrowthListData));

		if ("Annually".equalsIgnoreCase(period)) {
			List<FundAssetsData> annualFundAssetsListData = investmentDataFacade.getAnnualFundAssetsData(XSSFilterUtil.filter(sector),
					XSSFilterUtil.filter(period), XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
			model.addAttribute("annualFundAssetsJson", new Gson().toJson(annualFundAssetsListData));
		}

		if ("Quarterly".equalsIgnoreCase(period)) {
			List<FundAssetsData> quarterlyFundAssetsListData = investmentDataFacade
					.getQuarterlyFundAssetsData(XSSFilterUtil.filter(sector), XSSFilterUtil.filter(period), XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
			model.addAttribute("quarterlyFundAssetsJson", new Gson().toJson(quarterlyFundAssetsListData));
		}

		List<CommercialRegisterData> numberOfCommercialRegisterListData = investmentDataFacade
				.getNumberOfCommercialRegisterData(XSSFilterUtil.filter(sector), XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
		model.addAttribute("numberOfCommercialRegisterJson", new Gson().toJson(numberOfCommercialRegisterListData));

		List<CommercialRegisterData> capitalOfCommercialRegisterListData = investmentDataFacade
				.getCapitalOfCommercialRegisterData(XSSFilterUtil.filter(sector), XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
		model.addAttribute("capitalOfCommercialRegisterJson", new Gson().toJson(capitalOfCommercialRegisterListData));

		if("Annually".equalsIgnoreCase(period)){
			List<CommercialRegisterData> capitalInformationListData = investmentDataFacade.getAnnualCapitalInformationData(XSSFilterUtil.filter(sector),
					XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
			model.addAttribute("annualCapitalInformationJson", new Gson().toJson(capitalInformationListData));

		}
		if("Quarterly".equalsIgnoreCase(period)){
			List<CommercialRegisterData> quarterlyCapitalInformationListData = investmentDataFacade.getQuarterlyCapitalInformationData(XSSFilterUtil.filter(sector),
					XSSFilterUtil.filter(startYear), XSSFilterUtil.filter(endYear));
			model.addAttribute("quarterlyCapitalInformationJson", new Gson().toJson(quarterlyCapitalInformationListData));

		}
		final ContentPageModel economicCMSPage = getContentPageForLabelOrId(INVESTMENT_DATA_PAGE);
		storeCmsPageInModel(model, economicCMSPage);
		setUpMetaDataForContentPage(model, economicCMSPage);

		return getViewForPage(model);
	}

}
