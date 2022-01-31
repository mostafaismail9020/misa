package com.sap.ibso.eservices.facades.populators;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.LicenseApplyDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaRegionDAO;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicActivity;
import com.sap.ibso.eservices.facades.data.zqeemah.LicenseType;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseTypeFacade;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;


public class LicenseEntityInformationPopulator implements Populator<EntityInformationModel, EntityInformationData> {

	@Resource
	private Converter<MediaModel, MediaData> mediaConverter;
	
	@Resource
	private Converter<SagiaCountryModel, SagiaCountryData> sagiaCountryConverter;
	
	@Resource
	private Converter<SagiaLicenseModel, SagiaLicenseData> sagiaLicenseConverter;

	@Resource
	private Converter<IsicMasterModel, IsicActivity> isicActivityConverter;
	
    @Resource(name = "sagiaLicenseTypeFacade")
    private SagiaLicenseTypeFacade sagiaLicenseTypeFacade;

	@Resource
	private SagiaCountryDAO sagiaCountryDAO;

	@Resource
	private SagiaRegionDAO sagiaRegionDAO;

	@Resource
	private LicenseApplyDAO licenseApplyDAO;

	@Override
	public void populate(EntityInformationModel source, EntityInformationData target) throws ConversionException {

		if (source.getLicense() != null) {
//			target.setLicense(getSagiaLicenseConverter().convert(source.getLicense()));
		}

		target.setLicenseType(source.getLicenseType().getCode());
//		target.setLegalStatus(source.getLegalStatus().getName());
		target.setHasAdvanceLicenseNr(source.isHasAdvanceLicenseNr());
		target.setAdvanceLicenseNr(source.getAdvanceLicenseNr());
		target.setIsEntrepreneur(source.isIsEntrepreneur());
		
		//RHQ Requirement START
		
		target.setIsMoreThan2Branch(source.isIsMoreThan2Branch());
		target.setIsMoreThan6Branch(source.isIsMoreThan6Branch());
		target.setIsEntityAssetMoreThanThreshold(source.isIsEntityAssetMoreThanThreshold());
		target.setIsEntityListedInStockMarket(source.isIsEntityListedInStockMarket());
		target.setIsEntityRevenueMoreThanThreshold(source.isIsEntityRevenueMoreThanThreshold());
		if (source.getMainBranchCR() != null) {
			target.setMainBranchCR(getMediaConverter().convert(source.getMainBranchCR()));
		}
		if (source.getOtherBranchCR1() != null) {
			target.setOtherBranchCR1(getMediaConverter().convert(source.getOtherBranchCR1()));
		}
		if (source.getOtherBranchCR2() != null) {
			target.setOtherBranchCR2(getMediaConverter().convert(source.getOtherBranchCR2()));
		}
		if (source.getRhqStockMarketAttachment() != null) {
			target.setRhqStockMarketAttachment(getMediaConverter().convert(source.getRhqStockMarketAttachment()));
		}
		if (source.getRhqEntityAssetAttachment() != null) {
			target.setRhqEntityAssetAttachment(getMediaConverter().convert(source.getRhqEntityAssetAttachment()));
		}
		if (source.getRhqEntityRevenueAttachment() != null) {
			target.setRhqEntityRevenueAttachment(getMediaConverter().convert(source.getRhqEntityRevenueAttachment()));
		}
		if (source.getRhqCR1() != null) {
			target.setRhqCR1(getMediaConverter().convert(source.getRhqCR1()));
		}
		if (source.getRhqCR2() != null) {
			target.setRhqCR2(getMediaConverter().convert(source.getRhqCR2()));
		}
		if (source.getRhqCR3() != null) {
			target.setRhqCR3(getMediaConverter().convert(source.getRhqCR3()));
		}
		if (source.getRhqCR4() != null) {
			target.setRhqCR4(getMediaConverter().convert(source.getRhqCR4()));
		}

		if(!source.getListOfRhqCountries().isEmpty())
		{
			final List<String> listOfRhqCountries = new ArrayList<String>();
			for (final String country : source.getListOfRhqCountries())
			{
				listOfRhqCountries.add(sagiaCountryDAO.getCountryCodeForName(country).getCode());
			}
		
		target.setListOfRhqCountries(listOfRhqCountries);
		}
		if(!source.getListOfRhqRegions().isEmpty())
		{
			final List<String> listOfRhqRegions = new ArrayList<String>();
			for (final String region : source.getListOfRhqRegions())
			{
				listOfRhqRegions.add(sagiaRegionDAO.getRhqRegionCodeForName(region).getCode());
			}
		target.setListOfRhqRegions(listOfRhqRegions);
		}
			
		//RHQ Requirement END

//New RHQ Start
		if(!source.getListOfCorporateActivties().isEmpty())
		{
			final List<String> listOfCorporateActivities = new ArrayList<String>();
			for (String corporateActivities : source.getListOfCorporateActivties())
			{
				listOfCorporateActivities.add(licenseApplyDAO.getActivityDetailsCodeForName(corporateActivities).getId());
			}
			target.setListOfCorporateActivities(listOfCorporateActivities);
		}
		if(!source.getListOfStrategicActivties().isEmpty())
		{
			final List<String> listOfStrategicActivities = new ArrayList<String>();
			for (String strategicActivities : source.getListOfStrategicActivties())
			{
				listOfStrategicActivities.add(licenseApplyDAO.getActivityDetailsCodeForName(strategicActivities).getId());
			}
			target.setListOfStrategicActivities(listOfStrategicActivities);
		}
		if(!source.getListOfManagementActivties().isEmpty())
		{
			final List<String> listOfManagementActivities = new ArrayList<String>();
			for (String managementActivities : source.getListOfCorporateActivties())
			{
				listOfManagementActivities.add(licenseApplyDAO.getActivityDetailsCodeForName(managementActivities).getId());
			}
			target.setListOfManagementActivities(listOfManagementActivities);
		}

		if(!source.getListOfEntitiesManagedByRhq().isEmpty())
		{
			final List<EntitiesManagedByRhq> entitiesManagedByRhqList = new ArrayList<EntitiesManagedByRhq>();
			EntitiesManagedByRhq entityData = new EntitiesManagedByRhq();
			for (EntitiesManagedByRhqModel entityModel : source.getListOfEntitiesManagedByRhq())
			{
				entityData.setCompanyName(entityModel.getCompanyName());
				entityData.setCountry(entityModel.getCountry());
				entityData.setBusinessRelationshipType(entityModel.getBusinessRelationshipType());
				entityData.setIndustry(entityModel.getIndustry());
				entityData.setOperations(entityModel.getOperations());
				entityData.setRhqActivityProvided(entityModel.getRhqActivityProvided());
			}
			target.setListOfEntitiesManagedByRhq(entitiesManagedByRhqList);
		}

		if(!source.getListOfBrandPresenceInMENARegion().isEmpty())
		{
			final List<BrandPresenceInMENARegion> brandPresenceInMENARegionList = new ArrayList<BrandPresenceInMENARegion>();
			BrandPresenceInMENARegion brandData = new BrandPresenceInMENARegion();
			for (BrandPresenceModel brandModel : source.getListOfBrandPresenceInMENARegion())
			{
				brandData.setBrandName(brandModel.getBrandName());
				brandData.setCountry(brandModel.getCountry());
				brandData.setIndustry(brandModel.getIndustry());
				brandData.setCompanyOwningBrandInMENA(brandModel.getCompanyOwningBrandInMENA());
				brandData.setRhqActivityProvided(brandModel.getRhqActivityProvided());
			}
			target.setListOfBrandPresenceInMENARegion(brandPresenceInMENARegionList);
		}

		if(!source.getListOfEstimatedOperatingCostForRhq().isEmpty())
		{
			final List<EstimatedOperatingCostForRhq> operatingCostList = new ArrayList<EstimatedOperatingCostForRhq>();
			EstimatedOperatingCostForRhq operatingCostData = new EstimatedOperatingCostForRhq();
			for (OperatingCostForRhqModel operatingCostModel : source.getListOfEstimatedOperatingCostForRhq())
			{
				operatingCostModel.setItem(operatingCostData.getItem());
				operatingCostModel.setUnitCost(operatingCostData.getUnitCost());
				operatingCostModel.setNoOfUnits(operatingCostData.getNoOfUnits());
				operatingCostModel.setCostFrequency(operatingCostData.getCostFrequency());
				operatingCostModel.setYear2022(operatingCostData.getYear2022());
				operatingCostModel.setYear2023(operatingCostData.getYear2023());
				operatingCostModel.setYear2024(operatingCostData.getYear2024());
			}
			target.setListOfEstimatedOperatingCostForRhq(operatingCostList);
		}
		if(!source.getRhqCenterAdmin().isEmpty())
		{
			final List<String> rhqCenterAdmin = new ArrayList<String>();
			for (String center : source.getRhqCenterAdmin())
			{
				rhqCenterAdmin.add(center);
			}
			target.setRhqCenterAdmin(rhqCenterAdmin);
		}
		if(!source.getRhqSubsidiaryPresence().isEmpty())
		{
			final List<String> rhqSubsidiaryPresence = new ArrayList<String>();
			for (String subsidiary : source.getRhqSubsidiaryPresence())
			{
				rhqSubsidiaryPresence.add(subsidiary);
			}
			target.setRhqSubsidiaryPresence(rhqSubsidiaryPresence);
		}

		if (source.getEntityFinancialStatementFile() != null) {
			target.setEntityFinancialStatementFile(getMediaConverter().convert(source.getEntityFinancialStatementFile()));
		}

		if (source.getCommercialRegMainEntryFile() != null) {
			target.setCommercialRegMainEntryFile(getMediaConverter().convert(source.getCommercialRegMainEntryFile()));
		}

		if (source.getCommercialRegBranch1File() != null) {
			target.setCommercialRegBranch1File(getMediaConverter().convert(source.getCommercialRegBranch1File()));
		}
		if (source.getCommercialRegBranch2File() != null) {
			target.setCommercialRegBranch2File(getMediaConverter().convert(source.getCommercialRegBranch2File()));
		}
//New RHQ End
		if (source.getBoardResolutionFile() != null) {
			target.setBoardResolutionFile(getMediaConverter().convert(source.getBoardResolutionFile()));
		}
		if (source.getLetterOfSupportFile() != null) {
			target.setLetterOfSupportFile(getMediaConverter().convert(source.getLetterOfSupportFile()));
		}
		target.setLicenseDuration(source.getLicenseDuration());
		target.setEntityName(source.getEntityName());
		target.setEntityNameArabic(source.getEntityNameArabic());
		target.setLegalStatus(source.getLegalStatus().getCode());
		target.setLegalStatusText(source.getLegalStatus().getName());
		target.setBasicInfoExtendedMultinationalCompany(source.getBasicInfoExtendedMultinationalCompany());
		target.setCapital(source.getCapital());
		target.setEmail(source.getEmail());
		target.setTelephone(source.getTelephone());
		target.setMobilePhone(source.getMobilePhone());
		target.setCountryCodeForMobilePhone(source.getCountryCodeForMobilePhone());
		target.setCountryCodeForTelephone(source.getCountryCodeForTelephone());
		target.setWebsite(source.getWebsite());
		target.setCountry(source.getCountry().getCode());
		target.setCountryText(source.getCountry().getName());
		target.setRegion(source.getRegion().getCode());
		target.setRegionText(source.getRegion().getName());
		target.setCity(source.getCity().getCode());
		target.setCityText(source.getCity().getName());
		target.setAddress(source.getAddress());
		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setInvestment(source.getInvestment());
		target.setTemporaryLicenseText(source.getTemporaryLicenseText());

		LicenseType licenseTypeForCode = sagiaLicenseTypeFacade.getLicenseTypeForCode(source.getLicenseType().getCode());
		target.setLicenseTypeText(licenseTypeForCode.getLicenseTypeText());

		List<IsicMasterModel> isicActivityList = source.getIsicActivities();
		if(isicActivityList != null)
		{
			List<IsicActivity> isicActivities = isicActivityConverter.convertAll(isicActivityList);
			target.setIsicActivities(isicActivities);
		}
		
		target.setProfessionalLicenseCr(source.getProfessionalLicenseCr());
		target.setHasProfessionalLicenseCr(source.isHasProfessionalLicenseCr());
		target.setProfessionalLicenseCrVerified(source.isProfessionalLicenseCrVerified());
		
		target.setIsPreApprovalNumber(source.isIsPreApprovalNumber());
		target.setPreApprovalNumber(source.getPreApprovalNumber());
		if (source.getFinancialStatementFile() != null) {
			target.setFinancialStatementFile(getMediaConverter().convert(source.getFinancialStatementFile()));
		}
		if (source.getIqamaFile() != null) {
			target.setIqamaFile(getMediaConverter().convert(source.getIqamaFile()));
		}
		if (source.getCrCertificateFile() != null) {
			target.setCrCertificateFile(getMediaConverter().convert(source.getCrCertificateFile()));
		}
		if (source.getGosiCertificateFile() != null) {
			target.setGosiCertificateFile(getMediaConverter().convert(source.getGosiCertificateFile()));
		}
		if (source.getNoObjectionCertificateFile() != null) {
			target.setNoObjectionCertificateFile(getMediaConverter().convert(source.getNoObjectionCertificateFile()));
		}
	}

	public Converter<MediaModel, MediaData> getMediaConverter() {
		return mediaConverter;
	}

	public void setMediaConverter(Converter<MediaModel, MediaData> mediaConverter) {
		this.mediaConverter = mediaConverter;
	}

	public Converter<SagiaCountryModel, SagiaCountryData> getSagiaCountryConverter() {
		return sagiaCountryConverter;
	}

	public void setSagiaCountryConverter(Converter<SagiaCountryModel, SagiaCountryData> sagiaCountryConverter) {
		this.sagiaCountryConverter = sagiaCountryConverter;
	}

	public Converter<SagiaLicenseModel, SagiaLicenseData> getSagiaLicenseConverter() {
		return sagiaLicenseConverter;
	}

	public void setSagiaLicenseConverter(Converter<SagiaLicenseModel, SagiaLicenseData> sagiaLicenseConverter) {
		this.sagiaLicenseConverter = sagiaLicenseConverter;
	}
}
