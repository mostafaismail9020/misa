package com.sap.ibso.eservices.facades.populators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.investsaudi.enums.InvestSaudiLob;
import org.apache.commons.lang.StringUtils;

import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.SagiaCityModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.model.SagiaLegalStatusModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;
import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCityDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLegalStatusDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLicenseTypeDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaRegionDAO;
import com.sap.ibso.eservices.facades.data.EntityInformationData;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicActivity;
import com.sap.ibso.eservices.sagiaservices.services.isic.IsicMasterDataService;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

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
		target.setIsMoreThan2Branch(source.isIsMoreThan2Branch());
		target.setIsMoreThan6Branch(source.isIsMoreThan6Branch());
		target.setIsEntityAssetMoreThanThreshold(source.isIsEntityAssetMoreThanThreshold());
		target.setIsEntityListedInStockMarket(source.isIsEntityListedInStockMarket());
		target.setIsEntityRevenueMoreThanThreshold(source.isIsEntityRevenueMoreThanThreshold());
		if(null!=source.getListOfRhqCountries())
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
		}
		if(null!=source.getListOfRhqRegions())
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
		}
		
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
		if (source.getMainBranchCR() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getMainBranchCR();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getOtherBranchCR1() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getOtherBranchCR1();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getOtherBranchCR2() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getOtherBranchCR2();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getRhqStockMarketAttachment() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getRhqStockMarketAttachment();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getRhqEntityAssetAttachment() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getRhqEntityAssetAttachment();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getRhqEntityRevenueAttachment() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getRhqEntityRevenueAttachment();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getRhqCR1() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getRhqCR1();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getRhqCR2() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getRhqCR2();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getRhqCR3() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getRhqCR3();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
		}
		if (source.getRhqCR4() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getRhqCR4();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setBoardResolutionFile(mediaModel);
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
