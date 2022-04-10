package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.*;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicActivity;
import com.sap.ibso.eservices.sagiaservices.services.isic.IsicMasterDataService;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.tools.ant.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LicenseEntityInformationReversePopulator
		implements Populator<EntityInformationData, EntityInformationModel> {

	@Resource
	private SagiaMediaReversePopulator sagiaMediaReversePopulator;

	@Resource
	private SagiaLicenseReversePopulator sagiaLicenseReversePopulator;

	@Resource
	private ModelService modelService;
	
	@Resource
    private SagiaLicenseTypeDAO sagiaLicenseTypeDAO;
	
	@Resource
    private SagiaLegalStatusDAO sagiaLegalStatusDAO;
	
	@Resource
    private SagiaCountryDAO sagiaCountryDAO;
	
	@Resource
    private SagiaRegionDAO sagiaRegionDAO;
	
	@Resource
    private SagiaCityDAO sagiaCityDAO;

	@Resource
	private LicenseApplyDAO licenseApplyDAO;

	@Resource
    private IsicMasterDataService isicMasterDataService;


	@Override
	public void populate(EntityInformationData source, EntityInformationModel target) throws ConversionException {

		boolean isNewRecord = false;
		//If new record, generate licenseModel
		if (target.getLicense() == null) {
			isNewRecord = true;
		}
		
		final SagiaLicenseTypeModel licenseTypeModel = sagiaLicenseTypeDAO.getLicenseTypeForCode(source.getLicenseType());
		target.setLicenseType(licenseTypeModel);
		final SagiaLegalStatusModel sagiaLegalStatusModel = sagiaLegalStatusDAO.getLegalStatusForCode(source.getLegalStatus());
		target.setLegalStatus(sagiaLegalStatusModel);

		target.setHasAdvanceLicenseNr(source.isHasAdvanceLicenseNr());
		target.setAdvanceLicenseNr(source.getAdvanceLicenseNr());
		target.setIsEntrepreneur(source.isIsEntrepreneur());
		//target.setIsMoreThan2Branch(source.isIsMoreThan2Branch());
		//target.setIsMoreThan6Branch(source.isIsMoreThan6Branch());
		//target.setIsEntityAssetMoreThanThreshold(source.isIsEntityAssetMoreThanThreshold());
		//target.setIsEntityListedInStockMarket(source.isIsEntityListedInStockMarket());
		//target.setIsEntityRevenueMoreThanThreshold(source.isIsEntityRevenueMoreThanThreshold());
/*		if(null!=source.getListOfRhqCountries())
		{
			final List<String> listOfRhqCountries = new ArrayList<String>();
			for (final String country : source.getListOfRhqCountries())
			{
				listOfRhqCountries.add(sagiaCountryDAO.getCountryForCode(country).getName());
			}
			target.setListOfRhqCountries(listOfRhqCountries);
		}
		else
		{
			target.setListOfRhqCountries(null);
		}*/
		target.setListOfRhqCountries(null);
//New RHQ Start
/*		if(null!=source.getListOfRhqRegions())
		{
			final List<String> listOfRhqRegions = new ArrayList<String>();
			for (final String region : source.getListOfRhqRegions())
			{
				listOfRhqRegions.add(sagiaRegionDAO.getRhqRegionForCode(region).getName());
			}
			target.setListOfRhqRegions(listOfRhqRegions);
		}
		else
		{
			target.setListOfRhqRegions(null);
		}*/
		target.setListOfRhqRegions(null);
		if(null!=source.getListOfCorporateActivities())
		{
			final List<String> listOfCorporateActivities = new ArrayList<String>();
			for (String corporateActivity : source.getListOfCorporateActivities())
			{
				listOfCorporateActivities.add(licenseApplyDAO.getActivityDetailsForCode(corporateActivity).getDetails());
			}
			target.setListOfCorporateActivties(listOfCorporateActivities);
		}
		else
		{
			target.setListOfCorporateActivties(null);
		}
		if(null!=source.getListOfStrategicActivities())
		{
			final List<String> listOfStrategicActivities = new ArrayList<String>();
			for (String strategicActivity : source.getListOfStrategicActivities())
			{
				listOfStrategicActivities.add(licenseApplyDAO.getActivityDetailsForCode(strategicActivity).getDetails());
			}
			target.setListOfStrategicActivties(listOfStrategicActivities);
		}
		else
		{
			target.setListOfStrategicActivties(null);
		}
		if(null!=source.getListOfManagementActivities())
		{
			final List<String> listOfManagementActivities = new ArrayList<String>();
			for (String managementActivity : source.getListOfManagementActivities())
			{
				listOfManagementActivities.add(licenseApplyDAO.getActivityDetailsForCode(managementActivity).getDetails());
			}
			target.setListOfManagementActivties(listOfManagementActivities);
		}
		else
		{
			target.setListOfManagementActivties(null);
		}
//Section for Table Data
		if(null!=source.getListOfEntitiesManagedByRhq())
		{
			final List<EntitiesManagedByRhqModel> entitiesManagedByRhqList = new ArrayList<EntitiesManagedByRhqModel>();
			target.setListOfEntitiesManagedByRhq(null);
			for (EntitiesManagedByRhq entityData : source.getListOfEntitiesManagedByRhq())
			{
				final EntitiesManagedByRhqModel entityModel=modelService.create(EntitiesManagedByRhqModel.class);
				entityModel.setCompanyName(entityData.getCompanyName());
				entityModel.setCountry(entityData.getCountry());
				entityModel.setBusinessRelationshipType(entityData.getBusinessRelationshipType());
				entityModel.setIndustry(entityData.getIndustry());
				entityModel.setOperations(entityData.getOperations());
				entityModel.setRhqActivityProvided(entityData.getRhqActivityProvided());
				entitiesManagedByRhqList.add(entityModel);
			}
			target.setListOfEntitiesManagedByRhq(entitiesManagedByRhqList);
		}
		else
		{
			target.setListOfEntitiesManagedByRhq(null);
		}
		if(null!=source.getListOfBrandPresenceInMENARegion())
		{
			final List<BrandPresenceModel> brandPresenceInMENARegionList = new ArrayList<BrandPresenceModel>();
			target.setListOfBrandPresenceInMENARegion(null);
			for (BrandPresenceInMENARegion brandData : source.getListOfBrandPresenceInMENARegion())
			{
				final BrandPresenceModel brandModel = modelService.create(BrandPresenceModel.class);
				brandModel.setBrandName(brandData.getBrandName());
				brandModel.setCountry(brandData.getCountry());
				brandModel.setIndustry(brandData.getIndustry());
				brandModel.setCompanyOwningBrandInMENA(brandData.getCompanyOwningBrandInMENA());
				brandModel.setRhqActivityProvided(brandData.getRhqActivityProvided());
				brandPresenceInMENARegionList.add(brandModel);
			}
			target.setListOfBrandPresenceInMENARegion(brandPresenceInMENARegionList);
		}
		else
		{
			target.setListOfBrandPresenceInMENARegion(null);
		}
		if(null!=source.getListOfEstimatedOperatingCostForRhq())
		{
			final List<OperatingCostForRhqModel> operatingCostList = new ArrayList<OperatingCostForRhqModel>();
			target.setListOfEstimatedOperatingCostForRhq(null);
			for (EstimatedOperatingCostForRhq operatingCostData : source.getListOfEstimatedOperatingCostForRhq())
			{
				final OperatingCostForRhqModel operatingCostModel = modelService.create(OperatingCostForRhqModel.class);
				operatingCostModel.setItem(operatingCostData.getItem());
				operatingCostModel.setUnitCost(operatingCostData.getUnitCost());
				operatingCostModel.setNoOfUnits(operatingCostData.getNoOfUnits());
				operatingCostModel.setCostFrequency(operatingCostData.getCostFrequency());
				operatingCostModel.setYear2022(operatingCostData.getYear2022());
				operatingCostModel.setYear2023(operatingCostData.getYear2023());
				operatingCostModel.setYear2024(operatingCostData.getYear2024());
				operatingCostList.add(operatingCostModel);
			}
			target.setListOfEstimatedOperatingCostForRhq(operatingCostList);
		}
		else
		{
			target.setListOfEstimatedOperatingCostForRhq(null);
		}
		if(null!=source.getRhqCenterAdmin())
		{
			final List<String> rhqCenterAdmin = new ArrayList<String>();
			for (String center : source.getRhqCenterAdmin())
			{
				if(center.equals("Middle_East_ME"))
				{
					rhqCenterAdmin.add("Middle East (ME)");
				}
				else
				{
					rhqCenterAdmin.add(center);
				}
			}
			target.setRhqCenterAdmin(rhqCenterAdmin);
		}
		else
		{
			target.setRhqCenterAdmin(null);
		}
			target.setRhqSubsidiaryPresence(source.getRhqSubsidiaryPresence());

//New RHQ end
		
		//TODO: need to create more file attribute to get file stream, file name etc. also change below logic in a way so if it's new file then only update it.
		if (source.getBoardResolutionFile() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getBoardResolutionFile();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getLetterOfSupportFile() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getLetterOfSupportFile();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setLetterOfSupportFile(mediaModel);
		}

		if (source.getEntityFinancialStatementFile() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getEntityFinancialStatementFile();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setEntityFinancialStatementFile(mediaModel);
		}
		if (source.getCommercialRegMainEntryFile() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getCommercialRegMainEntryFile();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setCommercialRegMainEntryFile(mediaModel);
		}
		if (source.getCommercialRegBranch1File() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getCommercialRegBranch1File();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setCommercialRegBranch1File(mediaModel);
		}
		if (source.getCommercialRegBranch2File() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getCommercialRegBranch2File();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setCommercialRegBranch2File(mediaModel);
		}
		
		target.setLicenseDuration(source.getLicenseDuration());
		target.setEntityName(source.getEntityName());
		target.setEntityNameArabic(source.getEntityNameArabic());

		target.setBasicInfoExtendedMultinationalCompany(source.getBasicInfoExtendedMultinationalCompany());
		target.setCapital(source.getCapital());
		target.setEmail(source.getEmail());
		target.setTelephone(source.getTelephone());
		target.setMobilePhone(source.getMobilePhone());
		target.setCountryCodeForMobilePhone(source.getCountryCodeForMobilePhone());
		target.setCountryCodeForTelephone(source.getCountryCodeForTelephone());

		target.setWebsite(source.getWebsite());
				
		SagiaCountryModel sagiaCountryModel = sagiaCountryDAO.getCountryForCode(source.getCountry());
		target.setCountry(sagiaCountryModel);

		SagiaRegionModel sagiaRegionModel = sagiaRegionDAO.getRegionForCode(source.getRegion());
		target.setRegion(sagiaRegionModel);

		SagiaCityModel sagiaCityModel = sagiaCityDAO.getCityForCode(source.getCity());
		target.setCity(sagiaCityModel);
		
		target.setAddress(source.getAddress());
		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setInvestment(source.getInvestment());
		
		if(source.getLicenseType() != null && source.getLicenseType().equals("6")) {
			target.setTemporaryLicenseText(source.getTemporaryLicenseText());
			target.setIsicActivities(Collections.<IsicMasterModel>emptyList());
			
        }
		else if(source.getIsicActivities() != null)
		{
			//TODO: an attribute from the storefront, which tells this selected IsicActivity has been modified, if yes then only we need to store new references
			List<IsicActivity> isicActivities = source.getIsicActivities();
			List<String> isicActivityCodes = isicActivities.stream().map(IsicActivity::getActivityId).collect(Collectors.toList());
			List<IsicMasterModel> isicMasterModels = (List<IsicMasterModel>)isicMasterDataService.getAllIsicMaster(isicActivityCodes);
			target.setIsicActivities(isicMasterModels);	
			target.setTemporaryLicenseText("");
		}
		
		if(source.getLicenseType() != null && source.getLicenseType().equals("9")) {
			target.setHasProfessionalLicenseCr(source.isHasProfessionalLicenseCr());
			if(source.isHasProfessionalLicenseCr()) {
				target.setProfessionalLicenseCr(source.getProfessionalLicenseCr());
				target.setProfessionalLicenseCrVerified(source.isProfessionalLicenseCrVerified());
			}else {
				target.setProfessionalLicenseCr("");
				target.setProfessionalLicenseCrVerified(false);
			}
		}else {
			target.setHasProfessionalLicenseCr(false);
			target.setProfessionalLicenseCrVerified(false);
			target.setProfessionalLicenseCr("");
		}
		
		target.setIsPreApprovalNumber(source.isIsPreApprovalNumber());
		if(source.isIsPreApprovalNumber()) {
			target.setPreApprovalNumber(source.getPreApprovalNumber());
		}else {
			target.setPreApprovalNumber("");
		}
		if (source.getFinancialStatementFile() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getFinancialStatementFile();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setFinancialStatementFile(mediaModel);
		}
		if (source.getIqamaFile() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getIqamaFile();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setIqamaFile(mediaModel);
		}
		if (source.getCrCertificateFile() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getCrCertificateFile();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setCrCertificateFile(mediaModel);
		}
		if (source.getGosiCertificateFile() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getGosiCertificateFile();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setGosiCertificateFile(mediaModel);
		}
		if (source.getNoObjectionCertificateFile() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getNoObjectionCertificateFile();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setNoObjectionCertificateFile(mediaModel);
		}
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}


	public SagiaLicenseReversePopulator getSagiaLicenseReversePopulator() {
		return sagiaLicenseReversePopulator;
	}

	public void setSagiaLicenseReversePopulator(SagiaLicenseReversePopulator sagiaLicenseReversePopulator) {
		this.sagiaLicenseReversePopulator = sagiaLicenseReversePopulator;
	}

	public SagiaMediaReversePopulator getSagiaMediaReversePopulator() {
		return sagiaMediaReversePopulator;
	}

	public void setSagiaMediaReversePopulator(SagiaMediaReversePopulator sagiaMediaReversePopulator) {
		this.sagiaMediaReversePopulator = sagiaMediaReversePopulator;
	}


}
