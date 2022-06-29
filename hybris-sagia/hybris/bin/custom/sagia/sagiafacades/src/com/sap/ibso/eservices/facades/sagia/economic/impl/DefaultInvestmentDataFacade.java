package com.sap.ibso.eservices.facades.sagia.economic.impl;

import com.sap.ibso.eservices.core.model.AQValueGrowthModel;
import com.sap.ibso.eservices.core.model.CommercialRegisterModel;
import com.sap.ibso.eservices.core.model.ForeignInvestmentModel;
import com.sap.ibso.eservices.core.model.FundAssetsModel;
import com.sap.ibso.eservices.core.sagia.services.InvestmentDataService;
import com.sap.ibso.eservices.facades.data.AQValueGrowthData;
import com.sap.ibso.eservices.facades.data.CommercialRegisterData;
import com.sap.ibso.eservices.facades.data.ForeignInvestmentData;
import com.sap.ibso.eservices.facades.data.FundAssetsData;
import com.sap.ibso.eservices.facades.sagia.economic.InvestmentDataFacade;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DefaultInvestmentDataFacade implements InvestmentDataFacade {

	private InvestmentDataService investmentDataService;
	private Converter<AQValueGrowthModel, AQValueGrowthData> aqValueGrowthConverter;
	private Converter<ForeignInvestmentModel, ForeignInvestmentData> foreignInvestmentConverter;
	private Converter<FundAssetsModel, FundAssetsData> fundAssetsConverter;
	private Converter<CommercialRegisterModel, CommercialRegisterData> commercialRegisterConverter;

	
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

	public Converter<AQValueGrowthModel, AQValueGrowthData> getAqValueGrowthConverter() {
		return aqValueGrowthConverter;
	}

	public void setAqValueGrowthConverter(Converter<AQValueGrowthModel, AQValueGrowthData> aqValueGrowthConverter) {
		this.aqValueGrowthConverter = aqValueGrowthConverter;
	}

	public Converter<ForeignInvestmentModel, ForeignInvestmentData> getForeignInvestmentConverter() {
		return foreignInvestmentConverter;
	}

	public void setForeignInvestmentConverter(Converter<ForeignInvestmentModel, ForeignInvestmentData> foreignInvestmentConverter) {
		this.foreignInvestmentConverter = foreignInvestmentConverter;
	}

	public Converter<FundAssetsModel, FundAssetsData> getFundAssetsConverter() {
		return fundAssetsConverter;
	}

	public void setFundAssetsConverter(Converter<FundAssetsModel, FundAssetsData> fundAssetsConverter) {
		this.fundAssetsConverter = fundAssetsConverter;
	}

	public Converter<CommercialRegisterModel, CommercialRegisterData> getCommercialRegisterConverter() {
		return commercialRegisterConverter;
	}

	public void setCommercialRegisterConverter(Converter<CommercialRegisterModel, CommercialRegisterData> commercialRegisterConverter) {
		this.commercialRegisterConverter = commercialRegisterConverter;
	}

	@Override
	public List<AQValueGrowthData> getAnnualValueData(String sector, String period, String startYear, String endYear) {
		{
			final List<AQValueGrowthModel> annualValuemodelList = getInvestmentDataService().getAnnualValueModel(sector,
					period, startYear, endYear);

			final List<AQValueGrowthData> aqValueGrowthDataList = new ArrayList<>();

			final AQValueGrowthModel filteredModel = new AQValueGrowthModel();

			if (null != annualValuemodelList) {
				if (sector != null) {
					String[] sectorArray = sector.split(",");
					for (AQValueGrowthModel valueModel : annualValuemodelList) {
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
						final AQValueGrowthData aqValueGrowthData = getAqValueGrowthConverter().convert(filteredModel);
						aqValueGrowthDataList.add(aqValueGrowthData);
					}

				}
			}
			return aqValueGrowthDataList;
		}

	}

	@Override
	public List<AQValueGrowthData> getAnnualGrowthData(String sector, String period, String startYear, String endYear) {

		{
			final List<AQValueGrowthModel> aqGrowthmodelList = getInvestmentDataService()
					.getAnnualGrowthModel(sector, period, startYear, endYear);

			final List<AQValueGrowthData> aqValueGrowthDataList = new ArrayList<>();

			final AQValueGrowthModel filteredModel = new AQValueGrowthModel();

			if (null != aqGrowthmodelList) {
				if (sector != null) {
					String[] sectorArray = sector.split(",");
					for (AQValueGrowthModel valueModel : aqGrowthmodelList) {
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
						final AQValueGrowthData aqValueGrowth = getAqValueGrowthConverter().convert(filteredModel);
						aqValueGrowthDataList.add(aqValueGrowth);
					}

				}
			}
			return aqValueGrowthDataList;
		}

	}

	@Override
	public List<AQValueGrowthData> getQuarterlyValueData(String sector, String period, String startYear,
														 String endYear) {

		{
			final List<AQValueGrowthModel> quarterlyValuemodelList = getInvestmentDataService()
					.getQuarterlyValueModel(sector, period, startYear, endYear);

			final List<AQValueGrowthData> aqValueGrowthDataList = new ArrayList<>();

			final AQValueGrowthModel filteredModel = new AQValueGrowthModel();

			if (null != quarterlyValuemodelList) {
				if (sector != null) {
					String[] sectorArray = sector.split(",");
					for (AQValueGrowthModel valueModel : quarterlyValuemodelList) {
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
						final AQValueGrowthData aqValueGrowthData = getAqValueGrowthConverter()
								.convert(filteredModel);
						aqValueGrowthDataList.add(aqValueGrowthData);
					}

				}
			}
			return aqValueGrowthDataList;
		}

	}

	@Override
	public List<AQValueGrowthData> getQuarterlyGrowthData(String sector, String period, String startYear,
														  String endYear) {
		{
			final List<AQValueGrowthModel> quarterlyGrowthmodelList = getInvestmentDataService()
					.getQuarterlyGrowthModel(sector, period, startYear, endYear);

			final List<AQValueGrowthData> aqValueGrowthDataList = new ArrayList<>();

			final AQValueGrowthModel filteredModel = new AQValueGrowthModel();

			if (null != quarterlyGrowthmodelList) {
				if (sector != null) {
					String[] sectorArray = sector.split(",");
					for (AQValueGrowthModel valueModel : quarterlyGrowthmodelList) {
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
						final AQValueGrowthData aqValueGrowthData = getAqValueGrowthConverter().convert(filteredModel);
						aqValueGrowthDataList.add(aqValueGrowthData);
					}

				}
			}
			return aqValueGrowthDataList;
		}

	}

	@Override
	public List<ForeignInvestmentData> getForeignInvestmentValueData() {
		{
			final List<ForeignInvestmentModel> model = getInvestmentDataService().getForeignInvestmentValueModel();

			final List<ForeignInvestmentData> foreignInvestmentValueListData = new ArrayList<>();

			for (final ForeignInvestmentModel foreignInvestmentValueModel : model) {
				final ForeignInvestmentData eoData = getForeignInvestmentConverter()
						.convert(foreignInvestmentValueModel);
				foreignInvestmentValueListData.add(eoData);
			}
			Collections.sort(foreignInvestmentValueListData, Comparator.comparing(ForeignInvestmentData::getIndex));
			return foreignInvestmentValueListData;
		}
	}

	@Override
	public List<ForeignInvestmentData> getForeignInvestmentGrowthData() {
		{
			final List<ForeignInvestmentModel> model = getInvestmentDataService()
					.getForeignInvestmentGrowthModel();

			final List<ForeignInvestmentData> foreignInvestmentGrowthListData = new ArrayList<>();

			for (final ForeignInvestmentModel foreignInvestmentGrowthModel : model) {
				final ForeignInvestmentData eoData = getForeignInvestmentConverter()
						.convert(foreignInvestmentGrowthModel);
				foreignInvestmentGrowthListData.add(eoData);
			}
			Collections.sort(foreignInvestmentGrowthListData, Comparator.comparing(ForeignInvestmentData::getIndex));
			return foreignInvestmentGrowthListData;
		}
	}

	@Override
	public List<FundAssetsData> getAnnualFundAssetsData(String sector, String period, String startYear,
														String endYear) {
		{
			final List<FundAssetsModel> model = getInvestmentDataService().getAnnualFundAssetsModel(sector,
					period, startYear, endYear);

			final List<FundAssetsData> quarterlyFundAssetsListData = new ArrayList<>();

			final FundAssetsModel filteredModel = new FundAssetsModel();

			if (null != model) {
				if (sector != null) {
					for (FundAssetsModel valueModel : model) {
						if (null != sector) {
							switch (sector) {
							case "totalNoOfInvestmentFund":
								if (null != valueModel.getTotalNoOfInvestmentFund()) {
									filteredModel.setTotalNoOfInvestmentFund(valueModel.getTotalNoOfInvestmentFund());
									filteredModel.setTotalNoOfInvestmentFundGrowthRate(valueModel.getTotalNoOfInvestmentFundGrowthRate());
									filteredModel.setYear(valueModel.getYear());
								}
								break;
							case "totalInvestmentFundAssets":
								if (null != valueModel.getTotalInvestmentFundAssets()) {
									filteredModel.setTotalInvestmentFundAssets(valueModel.getTotalInvestmentFundAssets());
									filteredModel.setTotalInvestmentFundAssetsGrowthRate(valueModel.getTotalInvestmentFundAssetsGrowthRate());
									filteredModel.setYear(valueModel.getYear());
								}
								break;
							case "foreignInvestmentFund":
								if (null != valueModel.getForeignInvestmentFund()) {
									filteredModel.setForeignInvestmentFund(valueModel.getForeignInvestmentFund());
									filteredModel.setForeignInvestmentFundGrowthRate(valueModel.getForeignInvestmentFundGrowthRate());
									filteredModel.setYear(valueModel.getYear());
								}
								break;
							case "domesticInvestmentFund":
								if (null != valueModel.getDomesticInvestmentFund()) {
									filteredModel.setDomesticInvestmentFund(valueModel.getDomesticInvestmentFund());
									filteredModel.setDomesticInvestmentFundGrowthRate(valueModel.getDomesticInvestmentFundGrowthRate());
									filteredModel.setYear(valueModel.getYear());
								}

							}

						}
						final FundAssetsData quarterlyFundAssetsData = getFundAssetsConverter().convert(filteredModel);
						quarterlyFundAssetsListData.add(quarterlyFundAssetsData);
					}

				}
			}
			return quarterlyFundAssetsListData;
		}
	}

	@Override
	public List<CommercialRegisterData> getNumberOfCommercialRegisterData(String indicator, String startYear, String endYear) {
		{
			final List<CommercialRegisterModel> model = getInvestmentDataService().getNumberOfCommercialRegisterModel(indicator,
					startYear, endYear);

			final List<CommercialRegisterData> numberOfCommercialRegisterListData = new ArrayList<>();

			for (final CommercialRegisterModel numberOfCommercialRegisterModel : model) {
				final CommercialRegisterData eoData = getCommercialRegisterConverter().convert(numberOfCommercialRegisterModel);
				numberOfCommercialRegisterListData.add(eoData);
			}
			return numberOfCommercialRegisterListData;
		}
	}
	
	@Override
	public List<CommercialRegisterData> getCapitalOfCommercialRegisterData(String indicator, String startYear, String endYear) {
		{
			final List<CommercialRegisterModel> model = getInvestmentDataService().getCapitalOfCommercialRegisterModel(indicator,
					startYear, endYear);

			final List<CommercialRegisterData> capitalOfCommercialRegisterListData = new ArrayList<>();

			for (final CommercialRegisterModel capitalOfCommercialRegisterModel : model) {
				final CommercialRegisterData eoData = getCommercialRegisterConverter().convert(capitalOfCommercialRegisterModel);
				capitalOfCommercialRegisterListData.add(eoData);
			}
			return capitalOfCommercialRegisterListData;
		}
	}

	@Override
	public List<CommercialRegisterData> getAnnualCapitalInformationData(String indicator, String startYear, String endYear) {
		{
			final List<CommercialRegisterModel> model = getInvestmentDataService().getAnnualCapitalInformationModel(indicator,
					startYear, endYear);

			final List<CommercialRegisterData> capitalInformationListData = new ArrayList<>();

			for (final CommercialRegisterModel capitalInformationModel : model) {
				final CommercialRegisterData eoData = getCommercialRegisterConverter().convert(capitalInformationModel);
				capitalInformationListData.add(eoData);
			}
			return capitalInformationListData;
		}
	}


	@Override
	public List<CommercialRegisterData> getQuarterlyCapitalInformationData(String indicator, String startYear, String endYear) {
		{
			final List<CommercialRegisterModel> model = getInvestmentDataService().getQuarterlyCapitalInformationModel(indicator,
					startYear, endYear);

			final List<CommercialRegisterData> capitalInformationListData = new ArrayList<>();

			for (final CommercialRegisterModel capitalInformationModel : model) {
				final CommercialRegisterData eoData = getCommercialRegisterConverter().convert(capitalInformationModel);
				capitalInformationListData.add(eoData);
			}
			return capitalInformationListData;
		}
	}

	@Override
	public List<FundAssetsData> getQuarterlyFundAssetsData(String sector, String period, String startYear,
														   String endYear) {
		{
			final List<FundAssetsModel> model = getInvestmentDataService().getQuarterlyFundAssetsModel(sector,
					period, startYear, endYear);

			final List<FundAssetsData> quarterlyFundAssetsListData = new ArrayList<>();

			final FundAssetsModel filteredModel = new FundAssetsModel();

			if (null != model) {
				if (sector != null) {
					for (FundAssetsModel valueModel : model) {
						if (null != sector) {
							switch (sector) {
							case "totalNoOfInvestmentFund":
								if (null != valueModel.getTotalNoOfInvestmentFund()) {
									filteredModel.setTotalNoOfInvestmentFund(valueModel.getTotalNoOfInvestmentFund());
									filteredModel.setTotalNoOfInvestmentFundGrowthRate(valueModel.getTotalNoOfInvestmentFundGrowthRate());
									filteredModel.setYear(valueModel.getYear());
								}
								break;
							case "totalInvestmentFundAssets":
								if (null != valueModel.getTotalInvestmentFundAssets()) {
									filteredModel.setTotalInvestmentFundAssets(valueModel.getTotalInvestmentFundAssets());
									filteredModel.setTotalInvestmentFundAssetsGrowthRate(valueModel.getTotalInvestmentFundAssetsGrowthRate());
									filteredModel.setYear(valueModel.getYear());
								}
								break;
							case "foreignInvestmentFund":
								if (null != valueModel.getForeignInvestmentFund()) {
									filteredModel.setForeignInvestmentFund(valueModel.getForeignInvestmentFund());
									filteredModel.setForeignInvestmentFundGrowthRate(valueModel.getForeignInvestmentFundGrowthRate());
									filteredModel.setYear(valueModel.getYear());
								}
								break;
							case "domesticInvestmentFund":
								if (null != valueModel.getDomesticInvestmentFund()) {
									filteredModel.setDomesticInvestmentFund(valueModel.getDomesticInvestmentFund());
									filteredModel.setDomesticInvestmentFundGrowthRate(valueModel.getDomesticInvestmentFundGrowthRate());
									filteredModel.setYear(valueModel.getYear());
								}

							}
						}
						final FundAssetsData quarterlyFundAssetsData = getFundAssetsConverter()
								.convert(filteredModel);
						quarterlyFundAssetsListData.add(quarterlyFundAssetsData);
					}

				}
			}
			return quarterlyFundAssetsListData;
		}
	}

}
