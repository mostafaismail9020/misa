
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.AnnualValueModel;
import com.sap.ibso.eservices.core.model.EaseOfStartingBusinessModel;
import com.sap.ibso.eservices.facades.data.AnnualValueData;
import com.sap.ibso.eservices.facades.data.SAInternationalIndicesData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class AnnualValuePopulator
		implements Populator<AnnualValueModel, AnnualValueData> {

	@Override
	public void populate(final AnnualValueModel source, final AnnualValueData target)
			throws ConversionException {
		target.setYear(source.getYear());
		target.setSaudiInvestmentsAbroad(source.getSaudiInvestmentsAbroad());
		target.setIncomingForeignInvestment(source.getIncomingForeignInvestment());
		target.setTotalNumberOfInvestmentLicenses(source.getTotalNumberOfInvestmentLicenses());
		target.setManufacturingLicenses(source.getManufacturingLicenses());
		target.setConstructionLicenses(source.getConstructionLicenses());
		target.setWholeSaleRetailTradeMoLicenses(source.getWholeSaleRetailTradeMoLicenses());
		target.setInformationAndCommunicationLicenses(source.getInformationAndCommunicationLicenses());
		target.setProfessionalScientificAndTechnicalLicenses(source.getProfessionalScientificAndTechnicalLicenses());
		target.setAccommodationFoodServiceActivityLicenses(source.getAccommodationFoodServiceActivityLicenses());
		target.setAdministrativeSupportServiceActivityLicenses(
				source.getAdministrativeSupportServiceActivityLicenses());
		target.setTransportationStorageLicenses(source.getTransportationStorageLicenses());
		target.setHumanHealthSocialworkActivityLicenses(source.getHumanHealthSocialworkActivityLicenses());
		target.setFinancialInsuranceActivityLicenses(source.getFinancialInsuranceActivityLicenses());
		target.setOtherServiceActivityLicenses(source.getOtherServiceActivityLicenses());
		target.setMiningQuarryingLicenses(source.getMiningQuarryingLicenses());
		target.setWaterSupplySewerageWasteManagement(source.getWaterSupplySewerageWasteManagement());
		target.setEducationLicenses(source.getEducationLicenses());
		target.setRealEstateActivityLicenses(source.getRealEstateActivityLicenses());
		target.setArtsEntertainmentRecreationLicenses(source.getArtsEntertainmentRecreationLicenses());
		target.setElectricityGasSteamAircondition(source.getElectricityGasSteamAircondition());
		target.setAgricultureForestryFishingLicenses(source.getAgricultureForestryFishingLicenses());
		target.setNotAssignedLicenses(source.getNotAssignedLicenses());
		target.setPublicAdministrationDefenceCompuLicenses(source.getPublicAdministrationDefenceCompuLicenses());
		target.setActivitiesOfExtraterritorialOrganizationLicenses(
				source.getActivitiesOfExtraterritorialOrganizationLicenses());
	}

}
