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

		/*if(!source.getListOfRhqCountries().isEmpty())
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
		}*/
			
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
			for (String managementActivities : source.getListOfManagementActivties())
			{
				listOfManagementActivities.add(licenseApplyDAO.getActivityDetailsCodeForName(managementActivities).getId());
			}
			target.setListOfManagementActivities(listOfManagementActivities);
		}

		if(!source.getListOfEntitiesManagedByRhq().isEmpty())
		{
			final List<EntitiesManagedByRhq> entitiesManagedByRhqList = new ArrayList<EntitiesManagedByRhq>();
			for (EntitiesManagedByRhqModel entityModel : source.getListOfEntitiesManagedByRhq())
			{
				EntitiesManagedByRhq entityData = new EntitiesManagedByRhq();
				entityData.setCompanyName(entityModel.getCompanyName());
				entityData.setCountry(entityModel.getCountry());
				entityData.setBusinessRelationshipType(entityModel.getBusinessRelationshipType());
				entityData.setIndustry(entityModel.getIndustry());
				entityData.setOperations(entityModel.getOperations());
				entityData.setRhqActivityProvided(entityModel.getRhqActivityProvided());
				entitiesManagedByRhqList.add(entityData);
			}
			target.setListOfEntitiesManagedByRhq(entitiesManagedByRhqList);
		}

		if(!source.getListOfBrandPresenceInMENARegion().isEmpty())
		{
			final List<BrandPresenceInMENARegion> brandPresenceInMENARegionList = new ArrayList<BrandPresenceInMENARegion>();
			for (BrandPresenceModel brandModel : source.getListOfBrandPresenceInMENARegion())
			{
				BrandPresenceInMENARegion brandData = new BrandPresenceInMENARegion();
				brandData.setBrandName(brandModel.getBrandName());
				brandData.setCountry(brandModel.getCountry());
				brandData.setIndustry(brandModel.getIndustry());
				brandData.setCompanyOwningBrandInMENA(brandModel.getCompanyOwningBrandInMENA());
				brandData.setRhqActivityProvided(brandModel.getRhqActivityProvided());
				brandPresenceInMENARegionList.add(brandData);
			}
			target.setListOfBrandPresenceInMENARegion(brandPresenceInMENARegionList);
		}

		if(!source.getListOfEstimatedOperatingCostForRhq().isEmpty())
		{
			final List<EstimatedOperatingCostForRhq> operatingCostList = new ArrayList<EstimatedOperatingCostForRhq>();
			for (OperatingCostForRhqModel operatingCostModel : source.getListOfEstimatedOperatingCostForRhq())
			{
				EstimatedOperatingCostForRhq operatingCostData = new EstimatedOperatingCostForRhq();
				operatingCostData.setItem(operatingCostModel.getItem());
				operatingCostData.setUnitCost(operatingCostModel.getUnitCost());
				operatingCostData.setNoOfUnits(operatingCostModel.getNoOfUnits());
				operatingCostData.setCostFrequency(operatingCostModel.getCostFrequency());
				operatingCostData.setYear2022(operatingCostModel.getYear2022());
				operatingCostData.setYear2023(operatingCostModel.getYear2023());
				operatingCostData.setYear2024(operatingCostModel.getYear2024());
				operatingCostList.add(operatingCostData);
			}
			target.setListOfEstimatedOperatingCostForRhq(operatingCostList);
		}
		if(!source.getRhqCenterAdmin().isEmpty())
		{
			final List<String> rhqCenterAdmin = new ArrayList<String>();
			for (String center : source.getRhqCenterAdmin())
			{
				if(center.equals("Middle East (ME)"))
				{
					rhqCenterAdmin.add("Middle_East_ME");
				}
				else
				{
					rhqCenterAdmin.add(center);
				}
			}
			target.setRhqCenterAdmin(rhqCenterAdmin);
		}

			target.setRhqSubsidiaryPresence(source.getRhqSubsidiaryPresence());
			target.setRhqCurrentMarketValue(source.getRhqCurrentMarketValue());
			target.setRhqLastYearAsset(source.getRhqLastYearAsset());
			target.setRhqAverage3YearRevenue(source.getRhqAverage3YearRevenue());
			target.setRhqNumberOfEmployees(source.getRhqNumberOfEmployees());
			target.setRhqCompanyRankedInFortune(source.getRhqCompanyRankedInFortuneList());
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

		if (source.getCurrentMarketValueFile() != null) {
			target.setCurrentMarketValueFile(getMediaConverter().convert(source.getCurrentMarketValueFile()));
		}
		if (source.getAverage3YearRevenueFile() != null) {
			target.setAverage3YearRevenueFile(getMediaConverter().convert(source.getAverage3YearRevenueFile()));
		}
		if (source.getLastYearAssetFile() != null) {
			target.setLastYearAssetFile(getMediaConverter().convert(source.getLastYearAssetFile()));
		}
		if (source.getNumberOfEmployeesFile() != null) {
			target.setNumberOfEmployeesFile(getMediaConverter().convert(source.getNumberOfEmployeesFile()));
		}
		if (source.getCompanyRankedInFortuneFile() != null) {
			target.setCompanyRankedInFortuneFile(getMediaConverter().convert(source.getCompanyRankedInFortuneFile()));
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
