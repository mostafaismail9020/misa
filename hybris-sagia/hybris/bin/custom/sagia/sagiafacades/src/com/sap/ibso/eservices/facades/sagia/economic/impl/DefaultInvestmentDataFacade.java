package com.sap.ibso.eservices.facades.sagia.economic.impl;

import java.util.ArrayList;
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
import com.sap.ibso.eservices.core.sagia.services.InvestmentDataService;
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
import com.sap.ibso.eservices.facades.sagia.economic.InvestmentDataFacade;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

public class DefaultInvestmentDataFacade implements InvestmentDataFacade {

	private InvestmentDataService investmentDataService;
	private Converter<AnnualValueModel, AnnualValueData> annualValueConverter;
	private Converter<AnnualGrowthModel, AnnualGrowthData> annualGrowthConverter;
	private Converter<QuarterlyValueModel, QuarterlyValueData> quarterlyValueConverter;
	private Converter<QuarterlyGrowthModel, QuarterlyGrowthData> quarterlyGrowthConverter;
	private Converter<ForeignInvestmentValueModel, ForeignInvestmentValueData> foreignInvestmentValueConverter;
	private Converter<ForeignInvestmentGrowthModel, ForeignInvestmentGrowthData> foreignInvestmentGrowthConverter;
	private Converter<AnnualFundAssetsModel, AnnualFundAssetsData> annualFundAssetsConverter;
	private Converter<NumberOfCommercialRegisterModel, NumberOfCommercialRegisterData> numberOfCommercialRegisterConverter;
	private Converter<CapitalOfCommercialRegisterModel, CapitalOfCommercialRegisterData> capitalOfCommercialRegisterConverter;
	private Converter<CapitalInformationModel, CapitalInformationData> capitalInformationConverter;
	private Converter<QuarterlyFundAssetsModel, QuarterlyFundAssetsData> quarterlyFundAssetsConverter;

	
	/**
	 * @return the investmentDataService
	 */
	public InvestmentDataService getInvestmentDataService() {
		return investmentDataService;
	}

	/**
	 * @param investmentDataService the investmentDataService to set
	 */
	public void setInvestmentDataService(InvestmentDataService investmentDataService) {
		this.investmentDataService = investmentDataService;
	}

	/**
	 * @return the annualValueConverter
	 */
	public Converter<AnnualValueModel, AnnualValueData> getAnnualValueConverter() {
		return annualValueConverter;
	}

	/**
	 * @param annualValueConverter the annualValueConverter to set
	 */
	public void setAnnualValueConverter(Converter<AnnualValueModel, AnnualValueData> annualValueConverter) {
		this.annualValueConverter = annualValueConverter;
	}

	/**
	 * @return the annualGrowthConverter
	 */
	public Converter<AnnualGrowthModel, AnnualGrowthData> getAnnualGrowthConverter() {
		return annualGrowthConverter;
	}

	/**
	 * @param annualGrowthConverter the annualGrowthConverter to set
	 */
	public void setAnnualGrowthConverter(Converter<AnnualGrowthModel, AnnualGrowthData> annualGrowthConverter) {
		this.annualGrowthConverter = annualGrowthConverter;
	}

	/**
	 * @return the quarterlyValueConverter
	 */
	public Converter<QuarterlyValueModel, QuarterlyValueData> getQuarterlyValueConverter() {
		return quarterlyValueConverter;
	}

	/**
	 * @param quarterlyValueConverter the quarterlyValueConverter to set
	 */
	public void setQuarterlyValueConverter(Converter<QuarterlyValueModel, QuarterlyValueData> quarterlyValueConverter) {
		this.quarterlyValueConverter = quarterlyValueConverter;
	}

	/**
	 * @return the quarterlyGrowthConverter
	 */
	public Converter<QuarterlyGrowthModel, QuarterlyGrowthData> getQuarterlyGrowthConverter() {
		return quarterlyGrowthConverter;
	}

	/**
	 * @param quarterlyGrowthConverter the quarterlyGrowthConverter to set
	 */
	public void setQuarterlyGrowthConverter(Converter<QuarterlyGrowthModel, QuarterlyGrowthData> quarterlyGrowthConverter) {
		this.quarterlyGrowthConverter = quarterlyGrowthConverter;
	}

	/**
	 * @return the foreignInvestmentValueConverter
	 */
	public Converter<ForeignInvestmentValueModel, ForeignInvestmentValueData> getForeignInvestmentValueConverter() {
		return foreignInvestmentValueConverter;
	}

	/**
	 * @param foreignInvestmentValueConverter the foreignInvestmentValueConverter to set
	 */
	public void setForeignInvestmentValueConverter(
			Converter<ForeignInvestmentValueModel, ForeignInvestmentValueData> foreignInvestmentValueConverter) {
		this.foreignInvestmentValueConverter = foreignInvestmentValueConverter;
	}

	/**
	 * @return the foreignInvestmentGrowthConverter
	 */
	public Converter<ForeignInvestmentGrowthModel, ForeignInvestmentGrowthData> getForeignInvestmentGrowthConverter() {
		return foreignInvestmentGrowthConverter;
	}

	/**
	 * @param foreignInvestmentGrowthConverter the foreignInvestmentGrowthConverter to set
	 */
	public void setForeignInvestmentGrowthConverter(
			Converter<ForeignInvestmentGrowthModel, ForeignInvestmentGrowthData> foreignInvestmentGrowthConverter) {
		this.foreignInvestmentGrowthConverter = foreignInvestmentGrowthConverter;
	}

	/**
	 * @return the annualFundAssetsConverter
	 */
	public Converter<AnnualFundAssetsModel, AnnualFundAssetsData> getAnnualFundAssetsConverter() {
		return annualFundAssetsConverter;
	}

	/**
	 * @param annualFundAssetsConverter the annualFundAssetsConverter to set
	 */
	public void setAnnualFundAssetsConverter(
			Converter<AnnualFundAssetsModel, AnnualFundAssetsData> annualFundAssetsConverter) {
		this.annualFundAssetsConverter = annualFundAssetsConverter;
	}

	/**
	 * @return the numberOfCommercialRegisterConverter
	 */
	public Converter<NumberOfCommercialRegisterModel, NumberOfCommercialRegisterData> getNumberOfCommercialRegisterConverter() {
		return numberOfCommercialRegisterConverter;
	}

	/**
	 * @param numberOfCommercialRegisterConverter the numberOfCommercialRegisterConverter to set
	 */
	public void setNumberOfCommercialRegisterConverter(
			Converter<NumberOfCommercialRegisterModel, NumberOfCommercialRegisterData> numberOfCommercialRegisterConverter) {
		this.numberOfCommercialRegisterConverter = numberOfCommercialRegisterConverter;
	}

	/**
	 * @return the capitalOfCommercialRegisterConverter
	 */
	public Converter<CapitalOfCommercialRegisterModel, CapitalOfCommercialRegisterData> getCapitalOfCommercialRegisterConverter() {
		return capitalOfCommercialRegisterConverter;
	}

	/**
	 * @param capitalOfCommercialRegisterConverter the capitalOfCommercialRegisterConverter to set
	 */
	public void setCapitalOfCommercialRegisterConverter(
			Converter<CapitalOfCommercialRegisterModel, CapitalOfCommercialRegisterData> capitalOfCommercialRegisterConverter) {
		this.capitalOfCommercialRegisterConverter = capitalOfCommercialRegisterConverter;
	}

	/**
	 * @return the capitalInformationConverter
	 */
	public Converter<CapitalInformationModel, CapitalInformationData> getCapitalInformationConverter() {
		return capitalInformationConverter;
	}

	/**
	 * @param capitalInformationConverter the capitalInformationConverter to set
	 */
	public void setCapitalInformationConverter(
			Converter<CapitalInformationModel, CapitalInformationData> capitalInformationConverter) {
		this.capitalInformationConverter = capitalInformationConverter;
	}

	/**
	 * @return the quarterlyFundAssetsConverter
	 */
	public Converter<QuarterlyFundAssetsModel, QuarterlyFundAssetsData> getQuarterlyFundAssetsConverter() {
		return quarterlyFundAssetsConverter;
	}

	/**
	 * @param quarterlyFundAssetsConverter the quarterlyFundAssetsConverter to set
	 */
	public void setQuarterlyFundAssetsConverter(
			Converter<QuarterlyFundAssetsModel, QuarterlyFundAssetsData> quarterlyFundAssetsConverter) {
		this.quarterlyFundAssetsConverter = quarterlyFundAssetsConverter;
	}

	@Override
	public List<AnnualValueData> getAnnualValueData(String sector, String period, String startYear, String endYear) {
		{
			final List<AnnualValueModel> annualValuemodelList = getInvestmentDataService().getAnnualValueModel(sector,
					period, startYear, endYear);

			final List<AnnualValueData> annualValueListData = new ArrayList<AnnualValueData>();

			final AnnualValueModel filteredModel = new AnnualValueModel();

			if (null != annualValuemodelList) {
				if (sector != null) {
					String[] sectorArray = sector.split(",");
					for (AnnualValueModel valueModel : annualValuemodelList) {
						for (String selectedSector : sectorArray) {
							if (null != selectedSector) {
								switch (selectedSector) {
								case "saudiInvestmentsAbroad":
									if (null != valueModel.getSaudiInvestmentsAbroad()) {
										filteredModel.setSaudiInvestmentsAbroad(valueModel.getSaudiInvestmentsAbroad());
										filteredModel.setYear(valueModel.getYear());
									}
									break;
								case "incomingForeignInvestment":
									if (null != valueModel.getIncomingForeignInvestment()) {
										filteredModel.setIncomingForeignInvestment(valueModel.getIncomingForeignInvestment());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "totalNumberOfInvestmentLicenses":
									if (null != valueModel.getTotalNumberOfInvestmentLicenses()) {
										filteredModel.setTotalNumberOfInvestmentLicenses(valueModel.getTotalNumberOfInvestmentLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "manufacturingLicenses":
									if (null != valueModel.getManufacturingLicenses()) {
										filteredModel.setManufacturingLicenses(valueModel.getManufacturingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "constructionLicenses":
									if (null != valueModel.getConstructionLicenses()) {
										filteredModel.setConstructionLicenses(valueModel.getConstructionLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "wholeSaleRetailTradeMoLicenses":
									if (null != valueModel.getWholeSaleRetailTradeMoLicenses()) {
										filteredModel.setWholeSaleRetailTradeMoLicenses(valueModel.getWholeSaleRetailTradeMoLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "informationAndCommunicationLicenses":
									if (null != valueModel.getInformationAndCommunicationLicenses()) {
										filteredModel.setInformationAndCommunicationLicenses(valueModel.getInformationAndCommunicationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "professionalScientificAndTechnicalLicenses":
									if (null != valueModel.getProfessionalScientificAndTechnicalLicenses()) {
										filteredModel.setProfessionalScientificAndTechnicalLicenses(valueModel.getProfessionalScientificAndTechnicalLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "accommodationFoodServiceActivityLicenses":
									if (null != valueModel.getAccommodationFoodServiceActivityLicenses()) {
										filteredModel.setAccommodationFoodServiceActivityLicenses(valueModel.getAccommodationFoodServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "administrativeSupportServiceActivityLicenses":
									if (null != valueModel.getAdministrativeSupportServiceActivityLicenses()) {
										filteredModel.setAdministrativeSupportServiceActivityLicenses(valueModel.getAdministrativeSupportServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "transportationStorageLicenses":
									if (null != valueModel.getTransportationStorageLicenses()) {
										filteredModel.setTransportationStorageLicenses(valueModel.getTransportationStorageLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "humanHealthSocialworkActivityLicenses":
									if (null != valueModel.getHumanHealthSocialworkActivityLicenses()) {
										filteredModel.setHumanHealthSocialworkActivityLicenses(valueModel.getHumanHealthSocialworkActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "financialInsuranceActivityLicenses":
									if (null != valueModel.getFinancialInsuranceActivityLicenses()) {
										filteredModel.setFinancialInsuranceActivityLicenses(valueModel.getFinancialInsuranceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "otherServiceActivityLicenses":
									if (null != valueModel.getOtherServiceActivityLicenses()) {
										filteredModel.setOtherServiceActivityLicenses(valueModel.getOtherServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "miningQuarryingLicenses":
									if (null != valueModel.getMiningQuarryingLicenses()) {
										filteredModel.setMiningQuarryingLicenses(valueModel.getMiningQuarryingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "waterSupplySewerageWasteManagement":
									if (null != valueModel.getWaterSupplySewerageWasteManagement()) {
										filteredModel.setWaterSupplySewerageWasteManagement(valueModel.getWaterSupplySewerageWasteManagement());
										filteredModel.setYear(valueModel.getWaterSupplySewerageWasteManagement());

									}
									break;
								case "educationLicenses":
									if (null != valueModel.getEducationLicenses()) {
										filteredModel.setEducationLicenses(valueModel.getEducationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "realEstateActivityLicenses":
									if (null != valueModel.getRealEstateActivityLicenses()) {
										filteredModel.setRealEstateActivityLicenses(valueModel.getRealEstateActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "artsEntertainmentRecreationLicenses":
									if (null != valueModel.getArtsEntertainmentRecreationLicenses()) {
										filteredModel.setArtsEntertainmentRecreationLicenses(valueModel.getArtsEntertainmentRecreationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "electricityGasSteamAircondition":
									if (null != valueModel.getElectricityGasSteamAircondition()) {
										filteredModel.setElectricityGasSteamAircondition(valueModel.getElectricityGasSteamAircondition());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "agricultureForestryFishingLicenses":
									if (null != valueModel.getAgricultureForestryFishingLicenses()) {
										filteredModel.setAgricultureForestryFishingLicenses(valueModel.getAgricultureForestryFishingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "notAssignedLicenses":
									if (null != valueModel.getNotAssignedLicenses()) {
										filteredModel.setNotAssignedLicenses(valueModel.getNotAssignedLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "publicAdministrationDefenceCompuLicenses":
									if (null != valueModel.getPublicAdministrationDefenceCompuLicenses()) {
										filteredModel.setPublicAdministrationDefenceCompuLicenses(valueModel.getPublicAdministrationDefenceCompuLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "activitiesOfExtraterritorialOrganizationLicenses":
									if (null != valueModel.getActivitiesOfExtraterritorialOrganizationLicenses()) {
										filteredModel.setActivitiesOfExtraterritorialOrganizationLicenses(valueModel.getActivitiesOfExtraterritorialOrganizationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "nonOilGdp":
									if (null != valueModel.getNonOilGdp()) {
										filteredModel.setNonOilGdp(valueModel.getNonOilGdp());
										filteredModel.setYear(valueModel.getYear());

									}
								}

							}
						}
						final AnnualValueData annualValueData = getAnnualValueConverter().convert(filteredModel);
						annualValueListData.add(annualValueData);
					}

				}
			}
			return annualValueListData;
		}

	}

	@Override
	public List<AnnualGrowthData> getAnnualGrowthData(String sector, String period, String startYear, String endYear) {

		{
			final List<AnnualGrowthModel> annualGrowthmodelList = getInvestmentDataService()
					.getAnnualGrowthModel(sector, period, startYear, endYear);

			final List<AnnualGrowthData> annualGrowthListData = new ArrayList<AnnualGrowthData>();

			final AnnualGrowthModel filteredModel = new AnnualGrowthModel();

			if (null != annualGrowthmodelList) {
				if (sector != null) {
					String[] sectorArray = sector.split(",");
					for (AnnualGrowthModel valueModel : annualGrowthmodelList) {
						for (String selectedSector : sectorArray) {
							if (null != selectedSector) {
								switch (selectedSector) {
								case "saudiInvestmentsAbroad":
									if (null != valueModel.getSaudiInvestmentsAbroad()) {
										filteredModel.setSaudiInvestmentsAbroad(valueModel.getSaudiInvestmentsAbroad());
										filteredModel.setYear(valueModel.getYear());
									}
									break;
								case "incomingForeignInvestment":
									if (null != valueModel.getIncomingForeignInvestment()) {
										filteredModel.setIncomingForeignInvestment(valueModel.getIncomingForeignInvestment());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "totalNumberOfInvestmentLicenses":
									if (null != valueModel.getTotalNumberOfInvestmentLicenses()) {
										filteredModel.setTotalNumberOfInvestmentLicenses(valueModel.getTotalNumberOfInvestmentLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "manufacturingLicenses":
									if (null != valueModel.getManufacturingLicenses()) {
										filteredModel.setManufacturingLicenses(valueModel.getManufacturingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "constructionLicenses":
									if (null != valueModel.getConstructionLicenses()) {
										filteredModel.setConstructionLicenses(valueModel.getConstructionLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "wholeSaleRetailTradeMoLicenses":
									if (null != valueModel.getWholeSaleRetailTradeMoLicenses()) {
										filteredModel.setWholeSaleRetailTradeMoLicenses(valueModel.getWholeSaleRetailTradeMoLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "informationAndCommunicationLicenses":
									if (null != valueModel.getInformationAndCommunicationLicenses()) {
										filteredModel.setInformationAndCommunicationLicenses(valueModel.getInformationAndCommunicationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "professionalScientificAndTechnicalLicenses":
									if (null != valueModel.getProfessionalScientificAndTechnicalLicenses()) {
										filteredModel.setProfessionalScientificAndTechnicalLicenses(valueModel.getProfessionalScientificAndTechnicalLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "accommodationFoodServiceActivityLicenses":
									if (null != valueModel.getAccommodationFoodServiceActivityLicenses()) {
										filteredModel.setAccommodationFoodServiceActivityLicenses(valueModel.getAccommodationFoodServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "administrativeSupportServiceActivityLicenses":
									if (null != valueModel.getAdministrativeSupportServiceActivityLicenses()) {
										filteredModel.setAdministrativeSupportServiceActivityLicenses(valueModel.getAdministrativeSupportServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "transportationStorageLicenses":
									if (null != valueModel.getTransportationStorageLicenses()) {
										filteredModel.setTransportationStorageLicenses(valueModel.getTransportationStorageLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "humanHealthSocialworkActivityLicenses":
									if (null != valueModel.getHumanHealthSocialworkActivityLicenses()) {
										filteredModel.setHumanHealthSocialworkActivityLicenses(valueModel.getHumanHealthSocialworkActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "financialInsuranceActivityLicenses":
									if (null != valueModel.getFinancialInsuranceActivityLicenses()) {
										filteredModel.setFinancialInsuranceActivityLicenses(valueModel.getFinancialInsuranceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "otherServiceActivityLicenses":
									if (null != valueModel.getOtherServiceActivityLicenses()) {
										filteredModel.setOtherServiceActivityLicenses(valueModel.getOtherServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "miningQuarryingLicenses":
									if (null != valueModel.getMiningQuarryingLicenses()) {
										filteredModel.setMiningQuarryingLicenses(valueModel.getMiningQuarryingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "waterSupplySewerageWasteManagement":
									if (null != valueModel.getWaterSupplySewerageWasteManagement()) {
										filteredModel.setWaterSupplySewerageWasteManagement(valueModel.getWaterSupplySewerageWasteManagement());
										filteredModel.setYear(valueModel.getYear());
									}
									break;
								case "educationLicenses":
									if (null != valueModel.getEducationLicenses()) {
										filteredModel.setEducationLicenses(valueModel.getEducationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "realEstateActivityLicenses":
									if (null != valueModel.getRealEstateActivityLicenses()) {
										filteredModel.setRealEstateActivityLicenses(valueModel.getRealEstateActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "artsEntertainmentRecreationLicenses":
									if (null != valueModel.getArtsEntertainmentRecreationLicenses()) {
										filteredModel.setArtsEntertainmentRecreationLicenses(valueModel.getArtsEntertainmentRecreationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "electricityGasSteamAircondition":
									if (null != valueModel.getElectricityGasSteamAircondition()) {
										filteredModel.setElectricityGasSteamAircondition(valueModel.getElectricityGasSteamAircondition());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "agricultureForestryFishingLicenses":
									if (null != valueModel.getAgricultureForestryFishingLicenses()) {
										filteredModel.setAgricultureForestryFishingLicenses(valueModel.getAgricultureForestryFishingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "notAssignedLicenses":
									if (null != valueModel.getNotAssignedLicenses()) {
										filteredModel.setNotAssignedLicenses(valueModel.getNotAssignedLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "publicAdministrationDefenceCompuLicenses":
									if (null != valueModel.getPublicAdministrationDefenceCompuLicenses()) {
										filteredModel.setPublicAdministrationDefenceCompuLicenses(valueModel.getPublicAdministrationDefenceCompuLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "activitiesOfExtraterritorialOrganizationLicenses":
									if (null != valueModel.getActivitiesOfExtraterritorialOrganizationLicenses()) {
										filteredModel.setActivitiesOfExtraterritorialOrganizationLicenses(valueModel.getActivitiesOfExtraterritorialOrganizationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
								}

							}
						}
						final AnnualGrowthData annualGrowthData = getAnnualGrowthConverter().convert(filteredModel);
						annualGrowthListData.add(annualGrowthData);
					}

				}
			}
			return annualGrowthListData;
		}

	}

	@Override
	public List<QuarterlyValueData> getQuarterlyValueData(String sector, String period, String startYear,
			String endYear) {

		{
			final List<QuarterlyValueModel> quarterlyValuemodelList = getInvestmentDataService()
					.getQuarterlyValueModel(sector, period, startYear, endYear);

			final List<QuarterlyValueData> quarterlyValueListData = new ArrayList<QuarterlyValueData>();

			final QuarterlyValueModel filteredModel = new QuarterlyValueModel();

			if (null != quarterlyValuemodelList) {
				if (sector != null) {
					String[] sectorArray = sector.split(",");
					for (QuarterlyValueModel valueModel : quarterlyValuemodelList) {
						for (String selectedSector : sectorArray) {
							if (null != selectedSector) {
								switch (selectedSector) {
								case "saudiInvestmentsAbroad":
									if (null != valueModel.getSaudiInvestmentsAbroad()) {
										filteredModel.setSaudiInvestmentsAbroad(valueModel.getSaudiInvestmentsAbroad());
										filteredModel.setYear(valueModel.getYear());
									}
									break;
								case "incomingForeignInvestment":
									if (null != valueModel.getIncomingForeignInvestment()) {
										filteredModel.setIncomingForeignInvestment(valueModel.getIncomingForeignInvestment());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "totalNumberOfInvestmentLicenses":
									if (null != valueModel.getTotalNumberOfInvestmentLicenses()) {
										filteredModel.setTotalNumberOfInvestmentLicenses(valueModel.getTotalNumberOfInvestmentLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "manufacturingLicenses":
									if (null != valueModel.getManufacturingLicenses()) {
										filteredModel.setManufacturingLicenses(valueModel.getManufacturingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "constructionLicenses":
									if (null != valueModel.getConstructionLicenses()) {
										filteredModel.setConstructionLicenses(valueModel.getConstructionLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "wholeSaleRetailTradeMoLicenses":
									if (null != valueModel.getWholeSaleRetailTradeMoLicenses()) {
										filteredModel.setWholeSaleRetailTradeMoLicenses(valueModel.getWholeSaleRetailTradeMoLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "informationAndCommunicationLicenses":
									if (null != valueModel.getInformationAndCommunicationLicenses()) {
										filteredModel.setInformationAndCommunicationLicenses(valueModel.getInformationAndCommunicationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "professionalScientificAndTechnicalLicenses":
									if (null != valueModel.getProfessionalScientificAndTechnicalLicenses()) {
										filteredModel.setProfessionalScientificAndTechnicalLicenses(valueModel.getProfessionalScientificAndTechnicalLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "accommodationFoodServiceActivityLicenses":
									if (null != valueModel.getAccommodationFoodServiceActivityLicenses()) {
										filteredModel.setAccommodationFoodServiceActivityLicenses(valueModel.getAccommodationFoodServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "administrativeSupportServiceActivityLicenses":
									if (null != valueModel.getAdministrativeSupportServiceActivityLicenses()) {
										filteredModel.setAdministrativeSupportServiceActivityLicenses(valueModel.getAdministrativeSupportServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "transportationStorageLicenses":
									if (null != valueModel.getTransportationStorageLicenses()) {
										filteredModel.setTransportationStorageLicenses(valueModel.getTransportationStorageLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "humanHealthSocialworkActivityLicenses":
									if (null != valueModel.getHumanHealthSocialworkActivityLicenses()) {
										filteredModel.setHumanHealthSocialworkActivityLicenses(valueModel.getHumanHealthSocialworkActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "financialInsuranceActivityLicenses":
									if (null != valueModel.getFinancialInsuranceActivityLicenses()) {
										filteredModel.setFinancialInsuranceActivityLicenses(valueModel.getFinancialInsuranceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "otherServiceActivityLicenses":
									if (null != valueModel.getOtherServiceActivityLicenses()) {
										filteredModel.setOtherServiceActivityLicenses(valueModel.getOtherServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "miningQuarryingLicenses":
									if (null != valueModel.getMiningQuarryingLicenses()) {
										filteredModel.setMiningQuarryingLicenses(valueModel.getMiningQuarryingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "waterSupplySewerageWasteManagement":
									if (null != valueModel.getWaterSupplySewerageWasteManagement()) {
										filteredModel.setWaterSupplySewerageWasteManagement(valueModel.getWaterSupplySewerageWasteManagement());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "educationLicenses":
									if (null != valueModel.getEducationLicenses()) {
										filteredModel.setEducationLicenses(valueModel.getEducationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "realEstateActivityLicenses":
									if (null != valueModel.getRealEstateActivityLicenses()) {
										filteredModel.setRealEstateActivityLicenses(valueModel.getRealEstateActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "artsEntertainmentRecreationLicenses":
									if (null != valueModel.getArtsEntertainmentRecreationLicenses()) {
										filteredModel.setArtsEntertainmentRecreationLicenses(valueModel.getArtsEntertainmentRecreationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "electricityGasSteamAircondition":
									if (null != valueModel.getElectricityGasSteamAircondition()) {
										filteredModel.setElectricityGasSteamAircondition(valueModel.getElectricityGasSteamAircondition());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "agricultureForestryFishingLicenses":
									if (null != valueModel.getAgricultureForestryFishingLicenses()) {
										filteredModel.setAgricultureForestryFishingLicenses(valueModel.getAgricultureForestryFishingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "notAssignedLicenses":
									if (null != valueModel.getNotAssignedLicenses()) {
										filteredModel.setNotAssignedLicenses(valueModel.getNotAssignedLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "publicAdministrationDefenceCompuLicenses":
									if (null != valueModel.getPublicAdministrationDefenceCompuLicenses()) {
										filteredModel.setPublicAdministrationDefenceCompuLicenses(valueModel.getPublicAdministrationDefenceCompuLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "activitiesOfExtraterritorialOrganizationLicenses":
									if (null != valueModel.getActivitiesOfExtraterritorialOrganizationLicenses()) {
										filteredModel.setActivitiesOfExtraterritorialOrganizationLicenses(valueModel.getActivitiesOfExtraterritorialOrganizationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "nonOilGdp":
									if (null != valueModel.getNonOilGdp()) {
										filteredModel.setNonOilGdp(valueModel.getNonOilGdp());
										filteredModel.setYear(valueModel.getYear());

									}
								}

							}
						}
						final QuarterlyValueData quarterlyValueData = getQuarterlyValueConverter()
								.convert(filteredModel);
						quarterlyValueListData.add(quarterlyValueData);
					}

				}
			}
			return quarterlyValueListData;
		}

	}

	@Override
	public List<QuarterlyGrowthData> getQuarterlyGrowthData(String sector, String period, String startYear,
			String endYear) {
		{
			final List<QuarterlyGrowthModel> quarterlyGrowthmodelList = getInvestmentDataService()
					.getQuarterlyGrowthModel(sector, period, startYear, endYear);

			final List<QuarterlyGrowthData> quarterlyGrowthListData = new ArrayList<QuarterlyGrowthData>();

			final QuarterlyGrowthModel filteredModel = new QuarterlyGrowthModel();

			if (null != quarterlyGrowthmodelList) {
				if (sector != null) {
					String[] sectorArray = sector.split(",");
					for (QuarterlyGrowthModel valueModel : quarterlyGrowthmodelList) {
						for (String selectedSector : sectorArray) {
							if (null != selectedSector) {
								switch (selectedSector) {
								case "saudiInvestmentsAbroad":
									if (null != valueModel.getSaudiInvestmentsAbroad()) {
										filteredModel.setSaudiInvestmentsAbroad(valueModel.getSaudiInvestmentsAbroad());
										filteredModel.setYear(valueModel.getYear());
									}
									break;
								case "incomingForeignInvestment":
									if (null != valueModel.getIncomingForeignInvestment()) {
										filteredModel.setIncomingForeignInvestment(valueModel.getIncomingForeignInvestment());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "totalNumberOfInvestmentLicenses":
									if (null != valueModel.getTotalNumberOfInvestmentLicenses()) {
										filteredModel.setTotalNumberOfInvestmentLicenses(valueModel.getTotalNumberOfInvestmentLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "manufacturingLicenses":
									if (null != valueModel.getManufacturingLicenses()) {
										filteredModel.setManufacturingLicenses(valueModel.getManufacturingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "constructionLicenses":
									if (null != valueModel.getConstructionLicenses()) {
										filteredModel.setConstructionLicenses(valueModel.getConstructionLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "wholeSaleRetailTradeMoLicenses":
									if (null != valueModel.getWholeSaleRetailTradeMoLicenses()) {
										filteredModel.setWholeSaleRetailTradeMoLicenses(valueModel.getWholeSaleRetailTradeMoLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "informationAndCommunicationLicenses":
									if (null != valueModel.getInformationAndCommunicationLicenses()) {
										filteredModel.setInformationAndCommunicationLicenses(valueModel.getInformationAndCommunicationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "professionalScientificAndTechnicalLicenses":
									if (null != valueModel.getProfessionalScientificAndTechnicalLicenses()) {
										filteredModel.setProfessionalScientificAndTechnicalLicenses(valueModel.getProfessionalScientificAndTechnicalLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "accommodationFoodServiceActivityLicenses":
									if (null != valueModel.getAccommodationFoodServiceActivityLicenses()) {
										filteredModel.setAccommodationFoodServiceActivityLicenses(valueModel.getAccommodationFoodServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "administrativeSupportServiceActivityLicenses":
									if (null != valueModel.getAdministrativeSupportServiceActivityLicenses()) {
										filteredModel.setAdministrativeSupportServiceActivityLicenses(valueModel.getAdministrativeSupportServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "transportationStorageLicenses":
									if (null != valueModel.getTransportationStorageLicenses()) {
										filteredModel.setTransportationStorageLicenses(valueModel.getTransportationStorageLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "humanHealthSocialworkActivityLicenses":
									if (null != valueModel.getHumanHealthSocialworkActivityLicenses()) {
										filteredModel.setHumanHealthSocialworkActivityLicenses(valueModel.getHumanHealthSocialworkActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "financialInsuranceActivityLicenses":
									if (null != valueModel.getFinancialInsuranceActivityLicenses()) {
										filteredModel.setFinancialInsuranceActivityLicenses(valueModel.getFinancialInsuranceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "otherServiceActivityLicenses":
									if (null != valueModel.getOtherServiceActivityLicenses()) {
										filteredModel.setOtherServiceActivityLicenses(valueModel.getOtherServiceActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "miningQuarryingLicenses":
									if (null != valueModel.getMiningQuarryingLicenses()) {
										filteredModel.setMiningQuarryingLicenses(valueModel.getMiningQuarryingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "waterSupplySewerageWasteManagement":
									if (null != valueModel.getWaterSupplySewerageWasteManagement()) {
										filteredModel.setWaterSupplySewerageWasteManagement(valueModel.getWaterSupplySewerageWasteManagement());
										filteredModel.setYear(valueModel.getYear());
									}
									break;
								case "educationLicenses":
									if (null != valueModel.getEducationLicenses()) {
										filteredModel.setEducationLicenses(valueModel.getEducationLicenses());
										filteredModel.setYear(valueModel.getYear());
									}
									break;
								case "realEstateActivityLicenses":
									if (null != valueModel.getRealEstateActivityLicenses()) {
										filteredModel.setRealEstateActivityLicenses(valueModel.getRealEstateActivityLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "artsEntertainmentRecreationLicenses":
									if (null != valueModel.getArtsEntertainmentRecreationLicenses()) {
										filteredModel.setArtsEntertainmentRecreationLicenses(valueModel.getArtsEntertainmentRecreationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "electricityGasSteamAircondition":
									if (null != valueModel.getElectricityGasSteamAircondition()) {
										filteredModel.setElectricityGasSteamAircondition(valueModel.getElectricityGasSteamAircondition());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "agricultureForestryFishingLicenses":
									if (null != valueModel.getAgricultureForestryFishingLicenses()) {
										filteredModel.setAgricultureForestryFishingLicenses(valueModel.getAgricultureForestryFishingLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "notAssignedLicenses":
									if (null != valueModel.getNotAssignedLicenses()) {
										filteredModel.setNotAssignedLicenses(valueModel.getNotAssignedLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "publicAdministrationDefenceCompuLicenses":
									if (null != valueModel.getPublicAdministrationDefenceCompuLicenses()) {
										filteredModel.setPublicAdministrationDefenceCompuLicenses(valueModel.getPublicAdministrationDefenceCompuLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "activitiesOfExtraterritorialOrganizationLicenses":
									if (null != valueModel.getActivitiesOfExtraterritorialOrganizationLicenses()) {
										filteredModel.setActivitiesOfExtraterritorialOrganizationLicenses(valueModel.getActivitiesOfExtraterritorialOrganizationLicenses());
										filteredModel.setYear(valueModel.getYear());

									}
									break;
								case "nonOilGdp":
									if (null != valueModel.getNonOilGdp()) {
										filteredModel.setNonOilGdp(valueModel.getNonOilGdp());
										filteredModel.setYear(valueModel.getYear());

									}
								}

							}
						}
						final QuarterlyGrowthData quarterlyGrowthData = getQuarterlyGrowthConverter().convert(filteredModel);
						quarterlyGrowthListData.add(quarterlyGrowthData);
					}

				}
			}
			return quarterlyGrowthListData;
		}

	}

	@Override
	public List<ForeignInvestmentValueData> getForeignInvestmentValueData() {
		{
			final List<ForeignInvestmentValueModel> model = getInvestmentDataService().getForeignInvestmentValueModel();

			final List<ForeignInvestmentValueData> foreignInvestmentValueListData = new ArrayList<ForeignInvestmentValueData>();

			for (final ForeignInvestmentValueModel foreignInvestmentValueModel : model) {
				final ForeignInvestmentValueData eoData = getForeignInvestmentValueConverter()
						.convert(foreignInvestmentValueModel);
				foreignInvestmentValueListData.add(eoData);
			}
			return foreignInvestmentValueListData;
		}
	}

	@Override
	public List<ForeignInvestmentGrowthData> getForeignInvestmentGrowthData() {
		{
			final List<ForeignInvestmentGrowthModel> model = getInvestmentDataService()
					.getForeignInvestmentGrowthModel();

			final List<ForeignInvestmentGrowthData> foreignInvestmentGrowthListData = new ArrayList<ForeignInvestmentGrowthData>();

			for (final ForeignInvestmentGrowthModel foreignInvestmentGrowthModel : model) {
				final ForeignInvestmentGrowthData eoData = getForeignInvestmentGrowthConverter()
						.convert(foreignInvestmentGrowthModel);
				foreignInvestmentGrowthListData.add(eoData);
			}
			return foreignInvestmentGrowthListData;
		}
	}

	@Override
	public List<AnnualFundAssetsData> getAnnualFundAssetsData(String sector, String period, String startYear,
			String endYear) {
		{
			final List<AnnualFundAssetsModel> model = getInvestmentDataService().getAnnualFundAssetsModel(sector,
					period, startYear, endYear);

			final List<AnnualFundAssetsData> quarterlyFundAssetsListData = new ArrayList<AnnualFundAssetsData>();

			final AnnualFundAssetsModel filteredModel = new AnnualFundAssetsModel();

			if (null != model) {
				if (sector != null) {
					for (AnnualFundAssetsModel valueModel : model) {
						if (null != sector) {
							switch (sector) {
							case "totalNoOfInvestmentFund":
								if (null != valueModel.getTotalNoOfInvestmentFund()) {
									filteredModel.setTotalNoOfInvestmentFund(valueModel.getTotalNoOfInvestmentFund());
									filteredModel.setYear(valueModel.getYear());
								}
								break;
							case "totalInvestmentFundAssets":
								if (null != valueModel.getTotalInvestmentFundAssets()) {
									filteredModel.setTotalInvestmentFundAssets(valueModel.getTotalInvestmentFundAssets());
									filteredModel.setYear(valueModel.getYear());
								}
								break;
							case "foreignInvestmentFund":
								if (null != valueModel.getForeignInvestmentFund()) {
									filteredModel.setForeignInvestmentFund(valueModel.getForeignInvestmentFund());
									filteredModel.setYear(valueModel.getYear());
								}
								break;
							case "domesticInvestmentFund":
								if (null != valueModel.getDomesticInvestmentFund()) {
									filteredModel.setDomesticInvestmentFund(valueModel.getDomesticInvestmentFund());
									filteredModel.setYear(valueModel.getYear());
								}

							}

						}
						final AnnualFundAssetsData quarterlyFundAssetsData = getAnnualFundAssetsConverter().convert(filteredModel);
						quarterlyFundAssetsListData.add(quarterlyFundAssetsData);
					}

				}
			}
			return quarterlyFundAssetsListData;
		}
	}

	@Override
	public List<NumberOfCommercialRegisterData> getNumberOfCommercialRegisterData(String indicator, String startYear, String endYear) {
		{
			final List<NumberOfCommercialRegisterModel> model = getInvestmentDataService().getNumberOfCommercialRegisterModel(indicator,
					startYear, endYear);

			final List<NumberOfCommercialRegisterData> numberOfCommercialRegisterListData = new ArrayList<NumberOfCommercialRegisterData>();

			for (final NumberOfCommercialRegisterModel numberOfCommercialRegisterModel : model) {
				final NumberOfCommercialRegisterData eoData = getNumberOfCommercialRegisterConverter().convert(numberOfCommercialRegisterModel);
				numberOfCommercialRegisterListData.add(eoData);
			}
			return numberOfCommercialRegisterListData;
		}
	}
	
	@Override
	public List<CapitalOfCommercialRegisterData> getCapitalOfCommercialRegisterData(String indicator, String startYear, String endYear) {
		{
			final List<CapitalOfCommercialRegisterModel> model = getInvestmentDataService().getCapitalOfCommercialRegisterModel(indicator,
					startYear, endYear);

			final List<CapitalOfCommercialRegisterData> capitalOfCommercialRegisterListData = new ArrayList<CapitalOfCommercialRegisterData>();

			for (final CapitalOfCommercialRegisterModel capitalOfCommercialRegisterModel : model) {
				final CapitalOfCommercialRegisterData eoData = getCapitalOfCommercialRegisterConverter().convert(capitalOfCommercialRegisterModel);
				capitalOfCommercialRegisterListData.add(eoData);
			}
			return capitalOfCommercialRegisterListData;
		}
	}


	@Override
	public List<CapitalInformationData> getCapitalInformationData(String indicator, String startYear, String endYear) {
		{
			final List<CapitalInformationModel> model = getInvestmentDataService().getCapitalInformationModel(indicator,
					startYear, endYear);

			final List<CapitalInformationData> capitalInformationListData = new ArrayList<CapitalInformationData>();

			for (final CapitalInformationModel capitalInformationModel : model) {
				final CapitalInformationData eoData = getCapitalInformationConverter().convert(capitalInformationModel);
				capitalInformationListData.add(eoData);
			}
			return capitalInformationListData;
		}
	}

	@Override
	public List<QuarterlyFundAssetsData> getQuarterlyFundAssetsData(String sector, String period, String startYear,
			String endYear) {
		{
			final List<QuarterlyFundAssetsModel> model = getInvestmentDataService().getQuarterlyFundAssetsModel(sector,
					period, startYear, endYear);

			final List<QuarterlyFundAssetsData> quarterlyFundAssetsListData = new ArrayList<QuarterlyFundAssetsData>();

			final QuarterlyFundAssetsModel filteredModel = new QuarterlyFundAssetsModel();

			if (null != model) {
				if (sector != null) {
					for (QuarterlyFundAssetsModel valueModel : model) {
						if (null != sector) {
							switch (sector) {
							case "totalNoOfInvestmentFund":
								if (null != valueModel.getTotalNoOfInvestmentFund()) {
									filteredModel.setTotalNoOfInvestmentFund(valueModel.getTotalNoOfInvestmentFund());
									filteredModel.setPeriod(valueModel.getPeriod());
								}
								break;
							case "totalInvestmentFundAssets":
								if (null != valueModel.getTotalInvestmentFundAssets()) {
									filteredModel.setTotalInvestmentFundAssets(valueModel.getTotalInvestmentFundAssets());
									filteredModel.setPeriod(valueModel.getPeriod());
								}
								break;
							case "foreignInvestmentFund":
								if (null != valueModel.getForeignInvestmentFund()) {
									filteredModel.setForeignInvestmentFund(valueModel.getForeignInvestmentFund());
									filteredModel.setPeriod(valueModel.getPeriod());
								}
								break;
							case "domesticInvestmentFund":
								if (null != valueModel.getDomesticInvestmentFund()) {
									filteredModel.setDomesticInvestmentFund(valueModel.getDomesticInvestmentFund());
									filteredModel.setPeriod(valueModel.getPeriod());
								}

							}

						}
						final QuarterlyFundAssetsData quarterlyFundAssetsData = getQuarterlyFundAssetsConverter()
								.convert(filteredModel);
						quarterlyFundAssetsListData.add(quarterlyFundAssetsData);
					}

				}
			}
			return quarterlyFundAssetsListData;
		}
	}

}
