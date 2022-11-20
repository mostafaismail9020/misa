package com.sap.ibso.eservices.facades.populators.odata;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.sagia.dao.LicenseApplyDAO;
import org.apache.commons.lang3.StringUtils;

import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaRegionDAO;
import com.sap.ibso.eservices.sagiaservices.data.odata.EntityInformationData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class EntitytInformationODataPopulator implements Populator<EntityInformationModel, EntityInformationData> {

	@Resource
	private SagiaFormatProvider  sagiaFormatProvider;
	
	@Resource
	private SagiaCountryDAO sagiaCountryDAO;

	@Resource
	private SagiaRegionDAO sagiaRegionDAO;

	@Resource
	private LicenseApplyDAO licenseApplyDAO;
	
	@Override
	public void populate(EntityInformationModel source, EntityInformationData target) throws ConversionException {
		
		    target.setRefid(source.getLicense().getApplicantReferenceID());
		    target.setObjectid(source.getLicense().getApplicationServiceRequestID());
		    target.setGuid(source.getLicense().getGuid());
		    target.setHasadvlicno(sagiaFormatProvider.formatBooleanForODATA(source.isHasAdvanceLicenseNr()));
		    if(source.isHasAdvanceLicenseNr())
		    {
		    	target.setAdvlicno(source.getAdvanceLicenseNr());
		    }
		    target.setLicenseType(source.getLicenseType().getCode());
		    /*target.setIsEntrepreneur(sagiaFormatProvider.formatBooleanForODATA(source.isIsEntrepreneur()));
		    target.setIsMoreThan2Branch(sagiaFormatProvider.formatBooleanForODATA(source.isIsMoreThan2Branch()));
		    target.setIsMoreThan6Branch(sagiaFormatProvider.formatBooleanForODATA(source.isIsMoreThan6Branch()));
		    target.setIsEntityAssetMoreThanThreshold(sagiaFormatProvider.formatBooleanForODATA(source.isIsEntityAssetMoreThanThreshold()));
		    target.setIsEntityRevenueMoreThanThreshold(sagiaFormatProvider.formatBooleanForODATA(source.isIsEntityRevenueMoreThanThreshold()));
		    target.setIsEntityListedInStockMarket(sagiaFormatProvider.formatBooleanForODATA(source.isIsEntityListedInStockMarket()));*/


// New RHQ Requirement Starts
		if(!source.getListOfCorporateActivties().isEmpty()) {
			final StringBuilder listOfCorporateActivities = new StringBuilder();
			for (final String corporateActivities : source.getListOfCorporateActivties()) {
				listOfCorporateActivities.append(licenseApplyDAO.getActivityDetailsCodeForName(corporateActivities).getId());
				listOfCorporateActivities.append(",");
			}
			target.setListOfCorporateActivities(listOfCorporateActivities.deleteCharAt(listOfCorporateActivities.length() - 1).toString());
		}
		if(!source.getListOfStrategicActivties().isEmpty()) {
			final StringBuilder listOfStrategicActivities = new StringBuilder();
			for (final String strategicActivities : source.getListOfStrategicActivties()) {
				listOfStrategicActivities.append(licenseApplyDAO.getActivityDetailsCodeForName(strategicActivities).getId());
				listOfStrategicActivities.append(",");
			}
			target.setListOfStrategicActivities(listOfStrategicActivities.deleteCharAt(listOfStrategicActivities.length() - 1).toString());
		}
		if(!source.getListOfManagementActivties().isEmpty()) {
			final StringBuilder listOfManagementActivities = new StringBuilder();
			for (final String managementActivities : source.getListOfManagementActivties()) {
				listOfManagementActivities.append(licenseApplyDAO.getActivityDetailsCodeForName(managementActivities).getId());
				listOfManagementActivities.append(",");
			}
			target.setListOfManagementActivities(listOfManagementActivities.deleteCharAt(listOfManagementActivities.length() - 1).toString());
		}
		if(!source.getRhqCenterAdmin().isEmpty()) {
			final StringBuilder listOfAdminCenter = new StringBuilder();
			for (final String centerOfAdminList : source.getRhqCenterAdmin()) {
				listOfAdminCenter.append(centerOfAdminList);
				listOfAdminCenter.append(",");
			}
			target.setRhqCenterAdmin(listOfAdminCenter.deleteCharAt(listOfAdminCenter.length() - 1).toString());
		}
/*		if(!source.getListOfRhqRegions().isEmpty()) {
			final StringBuilder listOfRhqRegions = new StringBuilder();
			for (final String region : source.getListOfRhqRegions()) {
				listOfRhqRegions.append(sagiaRegionDAO.getRhqRegionCodeForName(region).getCode());
				listOfRhqRegions.append(",");
			}
			target.setListOfRhqRegions(listOfRhqRegions.deleteCharAt(listOfRhqRegions.length() - 1).toString());
		}
		if(!source.getListOfRhqCountries().isEmpty()) {
			final StringBuilder listOfRhqCountries = new StringBuilder();
			for (final String country : source.getListOfRhqCountries()) {
				listOfRhqCountries.append(sagiaCountryDAO.getCountryCodeForName(country).getCode());
				listOfRhqCountries.append(",");
			}
			target.setListOfRhqCountries(listOfRhqCountries.deleteCharAt(listOfRhqCountries.length() - 1).toString());

		}*/
			target.setRhqSubsidiaryPresence(returnCodeForSubsidiaryPresenceInCRM(source.getRhqSubsidiaryPresence()));
		    target.setLicenseDuration(source.getLicenseDuration());
		    target.setEntityName(source.getEntityName());
		    target.setEntityNameArabic(source.getEntityNameArabic());
		    target.setLegalStatus(source.getLegalStatus().getCode());
		    target.setMnccomp(isMNCComp(source));
		    target.setCapital(source.getCapital());
		    target.setEmail(source.getEmail());
		    target.setTelephone(source.getTelephone());
		    target.setMobile(source.getMobilePhone());
		    target.setCcode_Mobile(source.getCountryCodeForMobilePhone());
		    target.setCcode_Tele(source.getCountryCodeForTelephone());
		    target.setCountry(source.getCountry().getCode());
		    target.setRegion(source.getRegion().getCode());
		    target.setCity(source.getCity().getCode());
		    target.setStreet(source.getAddress());
		    target.setPoBox(source.getPoBox());
		    target.setPostalCode(source.getPostalCode());
		    target.setInvestment(source.getInvestment());
		    target.setWebsite(source.getWebsite());
		    target.setRhqMarketValue(returnCodeForCRM(source.getRhqCurrentMarketValue()));
		    target.setRhqFinStmntAsset(returnCodeForCRM(source.getRhqLastYearAsset()));
		    target.setRhq3YrsRev(returnCodeForCRM(source.getRhqAverage3YearRevenue()));
		    target.setRhqTotalEmployees(returnCodeForCRM(source.getRhqNumberOfEmployees()));
		    target.setRhqFortuneListedEnt(returnCodeForCRM(source.getRhqCompanyRankedInFortuneList()));
		    
		    target.setHasProfessionalLicenseCr(sagiaFormatProvider.formatBooleanForODATA(source.isHasProfessionalLicenseCr()));
		    if(source.isHasProfessionalLicenseCr()) {
		    	target.setProfessionalLicenseCr(source.getProfessionalLicenseCr());
		    	target.setProfessionalLicenseCrVerified(sagiaFormatProvider.formatBooleanForODATA(source.isProfessionalLicenseCrVerified()));
		    }
		    
		    target.setIsPreApprovalNumber(sagiaFormatProvider.formatBooleanForODATA(source.isIsPreApprovalNumber()));
		    if(source.isIsPreApprovalNumber()) {
		    	target.setPreApprovalNumber(source.getPreApprovalNumber());
		    }
	}

	private String returnCodeForCRM(String entityData) {
		if(entityData.startsWith("less"))
		{
			return "1";
		}
		else if(entityData.startsWith("between"))
		{
			return "2";
		}
		else
		{
			return "3";
		}
	}

	private String returnCodeForSubsidiaryPresenceInCRM(String entityData) {
		if(entityData.startsWith("2"))
		{
			return "1";
		}
		else if(entityData.startsWith("6"))
		{
			return "2";
		}
		else
		{
			return "3";
		}
	}

	private String isMNCComp(EntityInformationModel source) {
		if(StringUtils.containsIgnoreCase(source.getBasicInfoExtendedMultinationalCompany(), "1"))
		{
			return sagiaFormatProvider.formatBooleanForODATA(true);
		}
		else
		{
			return sagiaFormatProvider.formatBooleanForODATA(false);
		}
	}
}
