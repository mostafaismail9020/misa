package com.sap.ibso.eservices.facades.sagia.impl;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.LicenseStatus;
import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicActivity;
import com.sap.ibso.eservices.facades.populators.*;
import com.sap.ibso.eservices.sagiaservices.services.isic.IsicMasterDataService;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.fest.util.Collections;

import com.sap.ibso.eservices.core.sagia.services.LicenseApplyService;
import com.sap.ibso.eservices.facades.sagia.SagiaIsicFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseApplyFacade;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sap.ibso.eservices.core.model.SagiaCMSParagraphMediaComponentModel;

public class DefautSagiaLicenseApplyFacade implements SagiaLicenseApplyFacade {

	private static final String PDF = "pdf";
	private static final String ENTITY_NOT_ADDED = "validation.information.required";
	private static final String ENTITY_LICENSE_TYPE_MANDATORY = "validation.licenseTypes";
	private static final String SHAREHOLDER_NOT_ADDED = "validation.information.required";
	private static final String SHAREHOLDER_TOTAL_PERCENTAGE_NOT_100 = "validation.shareholder.totalPercentage";
	private static final String CONTACT_NOT_ADDED = "validation.information.required";
	private static final String OK_TO_CONTINUE_FLOW = "";
	private static final String NO_SHAREHOLDER_FOR_PROFESSIONAL_LICENSE = "validation.shareholder.professionalLicense";

	@Resource
	private LicenseApplyService licenseApplyService;

	@Resource
	private Converter<EntityInformationModel, EntityInformationData> licenseEntityInformationConverter;

	@Resource
	private Converter<ShareHolderModel, ShareHoldersData> licenseShareholderConverter;

	@Resource
	private Converter<ContactPersonModel, ContactPersonsData> licenseContactPersonConverter;

	@Resource
	private LicenseEntityInformationReversePopulator licenseEntityInformationReversePopulator;

	@Resource
	private LicenseShareholderPopulator licenseShareholderPopulator;

	@Resource
	private LicenseShareholderReversePopulator licenseShareholderReversePopulator;

	@Resource
	private LicenseContactPersonReversePopulator licenseContactPersonReversePopulator;

	@Resource
	private LicenseExistingShareholderPopulator licenseExistingShareholderPopulator;

	@Resource
	private LicensePersonShareholderPopulator licensePersonShareholderPopulator;

	@Resource
	private LicenseOrganizationShareholderPopulator licenseOrganizationShareholderPopulator;

	@Resource
	private IsicMasterDataService isicMasterDataService;

	@Resource
	private ModelService modelService;

	@Resource
	private MediaContainerService mediaContainerService;

	@Resource
	private Converter<DelegateInfoModel, DelegateInformationData> licenseDelegateInformationConverter;

	@Resource
	private Converter<ExistingShareholderModel, ExistingShareholderData> licenseExistingShareholderConverter;

	@Resource
	private Converter<PersonShareholderModel, PersonShareholderData> licensePersonShareholderConverter;

	@Resource
	private Converter<OrganizationShareholderModel, OrganizationShareholderData> licenseOrganizationShareholderConverter;

    @Resource(name = "defaultSagiaIsicFacade")
    private SagiaIsicFacade sagiaIsicFacade;

	/**
	 * GET Entity Information
	 */
	public EntityInformationData getEntityInformationData() {
		EntityInformationModel entityInformation = licenseApplyService.getEntityInformation();
		if (entityInformation != null) {
			return licenseEntityInformationConverter.convert(entityInformation);
		}
		return null;
	}
	
	/**
	 * GET Entity Information For a specific draft
	 */
	public EntityInformationData getEntityInformationData( SagiaLicenseModel draft) {
		EntityInformationModel entityInformation = draft.getEntityInformation();
		if (entityInformation != null) {
			return licenseEntityInformationConverter.convert(entityInformation);
		}
		return null;
	}
	

	/**
	 * SAVE Entity Information
	 */
	public void saveEntityInformationData(EntityInformationData entityInformationData, HttpServletRequest request) {

		EntityInformationModel entityInformationModel = licenseApplyService.getEntityInformation();

		if (entityInformationModel == null) {
			entityInformationModel = modelService.create(EntityInformationModel.class);
			licenseEntityInformationReversePopulator.populate(entityInformationData, entityInformationModel);

			if (entityInformationData.isIsEntrepreneur()) {
				saveBoardResolutionFileOnEntityInfo(request, entityInformationModel);
				saveLetterOfSupportFileOnEntityInfo(request, entityInformationModel);
			}
			
			if (entityInformationData.isIsPreApprovalNumber()) {
				saveFinancialStatementFileOnEntityInfo(request, entityInformationModel);
				saveIqamaFileOnEntityInfo(request, entityInformationModel);
				saveCrCertificateFileOnEntityInfo(request, entityInformationModel);
				saveGosiCertificateFileOnEntityInfo(request, entityInformationModel);
				saveNoObjectionCertificateFileOnEntityInfo(request, entityInformationModel);
			}
			if(entityInformationData.isIsMoreThan2Branch())
			{
				saveMainBranchCRFileOnEntityInfo(request, entityInformationModel);
				saveOtherBranchCR1FileOnEntityInfo(request, entityInformationModel);
				saveOtherBranchCR2FileOnEntityInfo(request, entityInformationModel);
			}
			if(entityInformationData.isIsMoreThan6Branch())
			{
				saveEntityBranchCR1FileOnEntityInfo(request, entityInformationModel);
				saveEntityBranchCR2FileOnEntityInfo(request, entityInformationModel);
				saveEntityBranchCR3FileOnEntityInfo(request, entityInformationModel);
				saveEntityBranchCR4FileOnEntityInfo(request, entityInformationModel);
			}
			if(entityInformationData.isIsEntityListedInStockMarket()) 
			{
				saveEntityListedInStockMarketFileOnEntityInfo(request, entityInformationModel);
			}
			if(entityInformationData.isIsEntityAssetMoreThanThreshold())
			{
				saveEntityAssetFileOnEntityInfo(request, entityInformationModel);
			}
			if(entityInformationData.isIsEntityRevenueMoreThanThreshold())
			{
				saveEntityRevenueFileOnEntityInfo(request, entityInformationModel);
			}

			licenseApplyService.saveEntityInformation(entityInformationModel);
		} else {
			licenseEntityInformationReversePopulator.populate(entityInformationData, entityInformationModel);

			if (entityInformationData.isIsEntrepreneur()) {
				saveBoardResolutionFileOnEntityInfo(request, entityInformationModel);
				saveLetterOfSupportFileOnEntityInfo(request, entityInformationModel);
			}else{
				//remove medias if Entrepreneur is no
				entityInformationModel.setBoardResolutionFile(null);
				entityInformationModel.setLetterOfSupportFile(null);
			}
			
			if (entityInformationData.isIsPreApprovalNumber()) {
				saveFinancialStatementFileOnEntityInfo(request, entityInformationModel);
				saveIqamaFileOnEntityInfo(request, entityInformationModel);
				saveCrCertificateFileOnEntityInfo(request, entityInformationModel);
				saveGosiCertificateFileOnEntityInfo(request, entityInformationModel);
				saveNoObjectionCertificateFileOnEntityInfo(request, entityInformationModel);
			}else {
				entityInformationModel.setFinancialStatementFile(null);
				entityInformationModel.setIqamaFile(null);
				entityInformationModel.setCrCertificateFile(null);
				entityInformationModel.setGosiCertificateFile(null);
				entityInformationModel.setNoObjectionCertificateFile(null);
			}
			
			if(entityInformationData.isIsMoreThan2Branch())
			{
				saveMainBranchCRFileOnEntityInfo(request, entityInformationModel);
				saveOtherBranchCR1FileOnEntityInfo(request, entityInformationModel);
				saveOtherBranchCR2FileOnEntityInfo(request, entityInformationModel);
			}
			else
			{
				entityInformationModel.setMainBranchCR(null);
				entityInformationModel.setOtherBranchCR1(null);
				entityInformationModel.setOtherBranchCR2(null);
			}
			if(entityInformationData.isIsMoreThan6Branch())
			{
				saveEntityBranchCR1FileOnEntityInfo(request, entityInformationModel);
				saveEntityBranchCR2FileOnEntityInfo(request, entityInformationModel);
				saveEntityBranchCR3FileOnEntityInfo(request, entityInformationModel);
				saveEntityBranchCR4FileOnEntityInfo(request, entityInformationModel);
			}
			else
			{
				entityInformationModel.setRhqCR1(null);
				entityInformationModel.setRhqCR2(null);
				entityInformationModel.setRhqCR3(null);
				entityInformationModel.setRhqCR4(null);
			}
			if(entityInformationData.isIsEntityListedInStockMarket()) 
			{
				saveEntityListedInStockMarketFileOnEntityInfo(request, entityInformationModel);
			}
			else
			{
				entityInformationModel.setRhqStockMarketAttachment(null);
			}
			if(entityInformationData.isIsEntityAssetMoreThanThreshold())
			{
				saveEntityAssetFileOnEntityInfo(request, entityInformationModel);
			}
			else
			{
				entityInformationModel.setRhqEntityAssetAttachment(null);
			}
			if(entityInformationData.isIsEntityRevenueMoreThanThreshold())
			{
				saveEntityRevenueFileOnEntityInfo(request, entityInformationModel);
			}
			else
			{
				entityInformationModel.setRhqEntityRevenueAttachment(null);
			}
			if(entityInformationModel.getLicenseType().getCode().equals("11"))
			{
				List<ShareHolderModel> shareHolders=licenseApplyService.getShareHolders();
				modelService.removeAll(shareHolders);
			}
			modelService.save(entityInformationModel);
		}

	}

	public String validateEntityTab()
	{
		SagiaLicenseModel license = licenseApplyService.getDraftLicense();

		if(license == null || license.getEntityInformation() == null)
		{
			return ENTITY_NOT_ADDED;
		}

		if(license.getEntityInformation().getLicenseType() == null){
			return ENTITY_LICENSE_TYPE_MANDATORY;
		}

		return OK_TO_CONTINUE_FLOW;
	}

	public String validateShareHoldersTab()
	{
		boolean hasProfLicenseType = false;
		SagiaLicenseModel license = licenseApplyService.getDraftLicense();
		if(license == null || license.getShareHolders() == null || license.getShareHolders().isEmpty())
		{
			return SHAREHOLDER_NOT_ADDED;
		}

		if(license.getShareHolders().size() > 0 && !sumOfSharesIsNot100()){
			return SHAREHOLDER_TOTAL_PERCENTAGE_NOT_100;
		}
		
		if(license.getEntityInformation() != null && "9".equals(license.getEntityInformation().getLicenseType().getCode())){
			hasProfLicenseType = true;
		}
		if(hasProfLicenseType && license.getShareHolders().size() > 0 && sumOfSharesIsNot100() && !hasProfLicenseShareholder()) {
			return NO_SHAREHOLDER_FOR_PROFESSIONAL_LICENSE;
		}

		return OK_TO_CONTINUE_FLOW;
	}

	public String validateContactPersonTab()
	{
		SagiaLicenseModel license = licenseApplyService.getDraftLicense();

		if(license.getContactPerson() == null)
		{
			return CONTACT_NOT_ADDED;
		}
		return OK_TO_CONTINUE_FLOW;
	}

	private boolean sumOfSharesIsNot100(){

		SagiaLicenseModel license = licenseApplyService.getDraftLicense();

		List<ShareHolderModel> shareHolderModels = license.getShareHolders();

		double totalRequired = 100d;
		double sumOfShares = 0d;
		for(ShareHolderModel shareHolderFound : shareHolderModels){
			sumOfShares += Double.parseDouble(shareHolderFound.getSharesPercentage());
		}

		if(sumOfShares != totalRequired){
			return false;
		}

		return true;
	}
	
	private boolean hasProfLicenseShareholder(){

		SagiaLicenseModel license = licenseApplyService.getDraftLicense();

		List<ShareHolderModel> shareHolderModels = license.getShareHolders();

		for(ShareHolderModel shareHolderFound : shareHolderModels){
			
			if (shareHolderFound instanceof ExistingShareholderModel) {
				
				ExistingShareholderModel existingShareHolderModel = (ExistingShareholderModel) shareHolderFound;
				
				if(existingShareHolderModel.isProfessionalLicense()) {
					return true;
				}
			}
			else if (shareHolderFound instanceof PersonShareholderModel) {
				
				PersonShareholderModel personShareHolderModel = (PersonShareholderModel) shareHolderFound;
				
				if(personShareHolderModel.isProfessionalLicense()) {
					return true;
				}
			}
			else if (shareHolderFound instanceof OrganizationShareholderModel) {
				
				OrganizationShareholderModel organizationShareHolderModel = (OrganizationShareholderModel) shareHolderFound;
				
				if(organizationShareHolderModel.isProfessionalLicense()) {
					return true;
				}
			}
		}
		
		return false;
	}



	/**
	 * Check if BoardResolutionFile PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveBoardResolutionFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customBoardResolutionFile");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "boardResolutionFile_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setBoardResolutionFile(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Check if LetterOfSupportFile PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveLetterOfSupportFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customLetterOfSupportFile");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "letterOfSupportFile_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setLetterOfSupportFile(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Check if FinancialStatementFile PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveFinancialStatementFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customFinancialStatementFile");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "financialStatementFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setFinancialStatementFile(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Check if IqamaFile PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveIqamaFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customIqamaFile");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "iqamaFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setIqamaFile(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Check if CrCertificateFile PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveCrCertificateFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customCrCertificateFile");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "crCertificateFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setCrCertificateFile(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Check if GosiCertificateFile PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveGosiCertificateFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customGosiCertificateFile");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "gosiCertificateFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setGosiCertificateFile(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Check if NoObjectionCertificateFile PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveNoObjectionCertificateFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customNoObjectionCertificateFile");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "noObjectionCertificateFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setNoObjectionCertificateFile(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Check if Main Branch CR PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveMainBranchCRFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customMainBranchCRFile");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "mainBranchCRFile_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setMainBranchCR(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Check if Other Branch CR 1 PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveOtherBranchCR1FileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customOtherBranchCR1File");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "otherBranchCR1File_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setOtherBranchCR1(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Check if Other Branch CR 2 PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveOtherBranchCR2FileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customOtherBranchCR2File");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "otherBranchCR2File_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setOtherBranchCR2(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Check if Entity Listed in Stock Market PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveEntityListedInStockMarketFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customEntityListedInStockMarketFile");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "entityListedInStockMarketFile_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setRhqStockMarketAttachment(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Check if Entity Asset PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveEntityAssetFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customEntityAssetFileName");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "entityAssetFile_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setRhqEntityAssetAttachment(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Check if Entity Revenue PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveEntityRevenueFileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customEntityRevenueFileName");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "entityRevenueFileName_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setRhqEntityRevenueAttachment(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Check if Branch CR 1 PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveEntityBranchCR1FileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customBranchCR1FileName");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "branchCR1FileName_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setRhqCR1(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Check if Branch CR 2 PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveEntityBranchCR2FileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customBranchCR2FileName");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "branchCR2FileName_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setRhqCR2(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Check if Branch CR 3 PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveEntityBranchCR3FileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customBranchCR3FileName");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "branchCR3FileName_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setRhqCR3(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Check if Branch CR 4 PDF file is present in request and if so, set in EntityInformationModel
	 * @param request
	 * @param entityInformationModel
	 */
	public void saveEntityBranchCR4FileOnEntityInfo(HttpServletRequest request, EntityInformationModel entityInformationModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("customBranchCR4FileName");

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());

			try {
				if (file.isEmpty() || file.getContentType() == null) {
					return;
				}
				final Date nowTime = new Date();
				if (extension.equals(PDF)) {
					String fileName = "branchCR4FileName_" + nowTime.getTime() + "_" + file.getOriginalFilename();
					MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
					entityInformationModel.setRhqCR4(media);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Get Share Holders from Current User License
	 */
	public List<ShareHoldersData> getShareHoldersDataByLicense() {
		List<ShareHolderModel> shareHolderModelList = licenseApplyService.getShareHolders();
		List<ShareHoldersData> shareHolderDataList;
		if (!Collections.isEmpty(shareHolderModelList)) {
			shareHolderDataList = new ArrayList<>();

			for (ShareHolderModel shareHolderModelFound : shareHolderModelList){

				ShareHoldersData shareHoldersData = null;

				if (shareHolderModelFound instanceof ExistingShareholderModel) {
					shareHoldersData = new ExistingShareholderData();
					shareHoldersData = licenseExistingShareholderConverter.convert((ExistingShareholderModel) shareHolderModelFound);
				}
				else if (shareHolderModelFound instanceof PersonShareholderModel) {
					shareHoldersData = new PersonShareholderData();
					shareHoldersData = licensePersonShareholderConverter.convert((PersonShareholderModel) shareHolderModelFound);
				}
				else if (shareHolderModelFound instanceof OrganizationShareholderModel) {
					shareHoldersData = new OrganizationShareholderData();
					shareHoldersData = licenseOrganizationShareholderConverter.convert((OrganizationShareholderModel) shareHolderModelFound);
				}

				if (shareHolderModelFound.getDelegateInfo() != null) {
					shareHoldersData.setDelegateInfo(licenseDelegateInformationConverter.convert(shareHolderModelFound.getDelegateInfo()));
				}

				licenseShareholderPopulator.populate(shareHolderModelFound, shareHoldersData);

				shareHolderDataList.add(shareHoldersData);
			}

		} else {
			shareHolderDataList = new ArrayList<ShareHoldersData>();
		}
		return shareHolderDataList;
	}

	/**
	 * Save Share Holders (Existing, Person and Organization)
	 */
	public void saveShareHolderData(ShareHoldersData shareHoldersData, HttpServletRequest request) throws Exception {


		SagiaLicenseModel license = licenseApplyService.getDraftLicense();
		if (license != null) {

			ShareHolderModel shareHolderModel = null;

			if(StringUtils.isNotBlank(shareHoldersData.getCode()))
			{
				//check if Shareholder already exists in db, if so updated it.
				List<ShareHolderModel> shareHolderModels = licenseApplyService.getShareHolders();

				for (ShareHolderModel holderFound : shareHolderModels) {
					if(holderFound.getCode().equals(shareHoldersData.getCode())){
						shareHolderModel = holderFound;
					}
				}
			}


			if (shareHoldersData instanceof ExistingShareholderData) {

				ExistingShareholderData existingShareholderData = (ExistingShareholderData) shareHoldersData;

				if (shareHolderModel == null) {
					shareHolderModel = modelService.create(ExistingShareholderModel.class);
				}

				//add media files to ExistingShareholder
				saveLastBudgetFile(request, shareHolderModel);
				saveCommercialRegistrationFile(request, shareHolderModel);
				saveExistingProfessionalLicenseFile(request, shareHolderModel);
				
				licenseShareholderReversePopulator.populate(existingShareholderData, shareHolderModel);
				
				checkProfessionalLicenseFile(request,shareHolderModel);
			}

			if (shareHoldersData instanceof PersonShareholderData) {

				PersonShareholderData personShareholderData = (PersonShareholderData) shareHoldersData;

				if (shareHolderModel == null) {
					shareHolderModel = modelService.create(PersonShareholderModel.class);
				}

				//add media files to PersonShareholder and its Delegate Info
				savePassportFile(request, shareHolderModel);
				saveOtherFile(request, shareHolderModel);
				saveProfessionalLicenseFile(request,shareHolderModel);
				
				saveAuthorizationLetterFile(request, shareHolderModel);
				saveIdCopyFile(request, shareHolderModel);
				
				licenseShareholderReversePopulator.populate(personShareholderData, shareHolderModel);
				
				checkProfessionalLicenseFile(request,shareHolderModel);
			}

			if (shareHoldersData instanceof OrganizationShareholderData) {

				OrganizationShareholderData organizationShareholderData = (OrganizationShareholderData) shareHoldersData;

				if (shareHolderModel == null) {
					shareHolderModel = modelService.create(OrganizationShareholderModel.class);
				}

				//add media files to OrganizationShareholder and its Delegate Info
				saveCompanyRegistrationFile(request,shareHolderModel);
				saveCompanyFinancialStatementFile(request,shareHolderModel);
				saveCompanyMemoAssociationFile(request,shareHolderModel);
				saveCompanyProfessionalLicenseFile(request,shareHolderModel);

				saveAuthorizationLetterFile(request, shareHolderModel);
				saveIdCopyFile(request, shareHolderModel);

				licenseShareholderReversePopulator.populate(organizationShareholderData, shareHolderModel);

				checkMemoAssociationFile(request,shareHolderModel);				
				checkProfessionalLicenseFile(request,shareHolderModel);
			}

			shareHolderModel.setLicense(license);
			modelService.save(shareHolderModel);
			modelService.refresh(license);

		} else {
			throw new Exception("no license found for current user, cannot save Share Holder");
		}

	}
	
	private void checkProfessionalLicenseFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if(shareHolderModel instanceof ExistingShareholderModel){
			ExistingShareholderModel existingShareholderModel = (ExistingShareholderModel) shareHolderModel;

			if(!existingShareholderModel.isProfessionalLicense()){
				existingShareholderModel.setProfessionalLicenseCertificate(null);
			}
		}
		if(shareHolderModel instanceof PersonShareholderModel){
			PersonShareholderModel personShareholderModel = (PersonShareholderModel) shareHolderModel;
			
			if(!personShareholderModel.isProfessionalLicense()){
				personShareholderModel.setProfessionalLicenseCertificate(null);
			}
		}
		if(shareHolderModel instanceof OrganizationShareholderModel){
			OrganizationShareholderModel organizationShareholderModel = (OrganizationShareholderModel) shareHolderModel;

			if(!organizationShareholderModel.isProfessionalLicense()){
				organizationShareholderModel.setProfessionalLicenseCertificate(null);
			}
		}

	}

	private void checkMemoAssociationFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		//remove file from Org Entity if isShareHolderCheck was setted to false
		if(shareHolderModel.getPk() != null){
			OrganizationShareholderModel organizationShareholderModel = (OrganizationShareholderModel) shareHolderModel;

			if(organizationShareholderModel.getCompanyCountry() != null){

				Boolean isShareHolderCheck = organizationShareholderModel.getCompanyCountry().getIsShareHolderCheck();

				if(isShareHolderCheck != null && !isShareHolderCheck){
					organizationShareholderModel.setCompanyMemoAssociation(null);
				}
			}
		}
	}

	/**
	 * Return ExistingShareholder found if exist to front end
	 * @param code
	 * @return
	 */
	public ExistingShareholderData getExistingShareholderByCode(String code) {
		ExistingShareholderData existingShareholderData = new ExistingShareholderData();

		ShareHolderModel shareHolderModel = licenseApplyService.getShareHolder(code);

		if(shareHolderModel != null){
			licenseExistingShareholderPopulator.populate((ExistingShareholderModel) shareHolderModel , existingShareholderData);
			return existingShareholderData;
		}

		return null;
	}

	public PersonShareholderData getPersonShareholderByCode(String code) {
		PersonShareholderData shareholderData = new PersonShareholderData();

		ShareHolderModel shareHolderModel = licenseApplyService.getShareHolder(code);
		
		if(shareHolderModel != null){
			licensePersonShareholderPopulator.populate((PersonShareholderModel) shareHolderModel, shareholderData);
			shareholderData.setDelegateInfo(licenseDelegateInformationConverter.convert(shareHolderModel.getDelegateInfo()));
			return shareholderData;
		}

		return null;
	}

	public OrganizationShareholderData getOrganizationShareholderByCode(String code) {
		OrganizationShareholderData shareholderData = new OrganizationShareholderData();

		ShareHolderModel shareHolderModel = licenseApplyService.getShareHolder(code);

		if(shareHolderModel != null){
			licenseOrganizationShareholderPopulator.populate((OrganizationShareholderModel) shareHolderModel , shareholderData);
			shareholderData.setDelegateInfo(licenseDelegateInformationConverter.convert(shareHolderModel.getDelegateInfo()));
			return shareholderData;
		}

		return null;
	}

	public void removeShareholder(String code) {
		licenseApplyService.removeShareholder(code);
	}



	/**
	 * Check if LastBudgetFile PDF is present in request and if so, set in ExistingShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveLastBudgetFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("existingShareholderLastBudgetFile");

			if(file != null) {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					if (extension.equals(PDF) && shareHolderModel instanceof ExistingShareholderModel) {

						final Date nowTime = new Date();

						ExistingShareholderModel existingShareholderModel = (ExistingShareholderModel) shareHolderModel;

						String fileName = "existingShareholderLastBudgetFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						existingShareholderModel.setLastBudget(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}


	/**
	 * Check if CommercialRegistrationFile PDF is present in request and if so, set in ExistingShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveCommercialRegistrationFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("existingShareholderCommercialRegistrationFile");

			if(file != null) {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) && shareHolderModel instanceof ExistingShareholderModel) {

						ExistingShareholderModel existingShareholderModel = (ExistingShareholderModel) shareHolderModel;

						String fileName = "existingShareholderCommercialRegistrationFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						existingShareholderModel.setCommercialRegistration(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Check if ExistingProfessionalLicenseFile PDF is present in request and if so, set in ExistingShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveExistingProfessionalLicenseFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("existingProfessionalLicenseFile");

			if(file != null) {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) && shareHolderModel instanceof ExistingShareholderModel) {

						ExistingShareholderModel existingShareholderModel = (ExistingShareholderModel) shareHolderModel;

						String fileName = "existingProfessionalLicenseFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						existingShareholderModel.setProfessionalLicenseCertificate(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * Check if PassportFile PDF is present in request and if so, set in PersonShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void savePassportFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("passportFile");

			if(file != null) {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) && shareHolderModel instanceof PersonShareholderModel) {

						PersonShareholderModel personShareholderModel = (PersonShareholderModel) shareHolderModel;

						String fileName = "passportFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						personShareholderModel.setPassportIdCopy(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Check if otherFile PDF is present in request and if so, set in PersonShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveOtherFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("otherFile");

			if(file != null) {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) && shareHolderModel instanceof PersonShareholderModel) {

						PersonShareholderModel personShareholderModel = (PersonShareholderModel) shareHolderModel;

						String fileName = "otherFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						personShareholderModel.setOther(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Check if ProfessionalLicenseFile PDF is present in request and if so, set in PersonShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveProfessionalLicenseFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("professionalLicenseFile");

			if(file != null) {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) && shareHolderModel instanceof PersonShareholderModel) {

						PersonShareholderModel personShareholderModel = (PersonShareholderModel) shareHolderModel;

						String fileName = "professionalLicenseFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						personShareholderModel.setProfessionalLicenseCertificate(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Check if authorizationLetterFile PDF (from Delegate info on Person Shareholder) is present in request and if so,
	 * set in Delegate info
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveAuthorizationLetterFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("authorizationLetterFile");

			if(file != null) {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF)) {

						String fileName = "authorizationLetterFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());

						if(shareHolderModel instanceof PersonShareholderModel){
							PersonShareholderModel personShareholderModel = (PersonShareholderModel) shareHolderModel;

							if(personShareholderModel.getDelegateInfo() != null){
								personShareholderModel.getDelegateInfo().setAuthorisationLetter(media);
							} else {
								DelegateInfoModel delegateInfoModel = new DelegateInfoModel();
								personShareholderModel.setDelegateInfo(delegateInfoModel);
								personShareholderModel.getDelegateInfo().setAuthorisationLetter(media);
							}
						}

						if(shareHolderModel instanceof OrganizationShareholderModel){
							OrganizationShareholderModel organizationShareholderModel = (OrganizationShareholderModel) shareHolderModel;

							if(organizationShareholderModel.getDelegateInfo() != null){
								organizationShareholderModel.getDelegateInfo().setAuthorisationLetter(media);
							} else {
								DelegateInfoModel delegateInfoModel = new DelegateInfoModel();
								organizationShareholderModel.setDelegateInfo(delegateInfoModel);
								organizationShareholderModel.getDelegateInfo().setAuthorisationLetter(media);
							}
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Check if idCopyFile PDF (from Delegate info on Person Shareholder) is present in request and if so,
	 * set in Delegate info
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveIdCopyFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("saudiIdCopy");

			if(file != null) {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) ) {

						String fileName = "saudiIdCopy" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());

						if(shareHolderModel instanceof PersonShareholderModel){

							PersonShareholderModel personShareholderModel = (PersonShareholderModel) shareHolderModel;

							if(personShareholderModel.getDelegateInfo() != null){
								personShareholderModel.getDelegateInfo().setSaudiIdCopy(media);
							} else {
								DelegateInfoModel delegateInfoModel = new DelegateInfoModel();
								personShareholderModel.setDelegateInfo(delegateInfoModel);
								personShareholderModel.getDelegateInfo().setSaudiIdCopy(media);
							}
						}

						if(shareHolderModel instanceof OrganizationShareholderModel){
							OrganizationShareholderModel organizationShareholderModel = (OrganizationShareholderModel) shareHolderModel;

							if(organizationShareholderModel.getDelegateInfo() != null){
								organizationShareholderModel.getDelegateInfo().setSaudiIdCopy(media);
							} else {
								DelegateInfoModel delegateInfoModel = new DelegateInfoModel();
								organizationShareholderModel.setDelegateInfo(delegateInfoModel);
								organizationShareholderModel.getDelegateInfo().setSaudiIdCopy(media);
							}
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}



	/**
	 * Check if companyRegistrationFile PDF is present in request and if so, set in OrganizationShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveCompanyRegistrationFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("companyRegistrationFile");

			if(file != null){
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) && shareHolderModel instanceof OrganizationShareholderModel) {

						OrganizationShareholderModel organizationShareholderModel = (OrganizationShareholderModel) shareHolderModel;

						String fileName = "companyRegistrationFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						organizationShareholderModel.setCommercialRegCopy(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}


	/**
	 * Check if companyFinancialStatementFile PDF is present in request and if so, set in OrganizationShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveCompanyFinancialStatementFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("companyFinancialStatementFile");

			if(file != null){

				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) && shareHolderModel instanceof OrganizationShareholderModel) {

						OrganizationShareholderModel organizationShareholderModel = (OrganizationShareholderModel) shareHolderModel;

						String fileName = "companyFinancialStatementFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						organizationShareholderModel.setLastYearFinStatement(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Check if companyFinancialStatementFile PDF is present in request and if so, set in OrganizationShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveCompanyMemoAssociationFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("companyMemoAssociationFile");

			if(file != null){

				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) && shareHolderModel instanceof OrganizationShareholderModel) {

						OrganizationShareholderModel organizationShareholderModel = (OrganizationShareholderModel) shareHolderModel;

						String fileName = "companyMemoAssociationFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						organizationShareholderModel.setCompanyMemoAssociation(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Check if companyFinancialStatementFile PDF is present in request and if so, set in OrganizationShareholderModel
	 * @param request
	 * @param shareHolderModel
	 */
	public void saveCompanyProfessionalLicenseFile(HttpServletRequest request, ShareHolderModel shareHolderModel) {

		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("companyProfessionalLicenseFile");

			if(file != null){

				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				try {
					if (file.isEmpty() || file.getContentType() == null) {
						return;
					}
					final Date nowTime = new Date();
					if (extension.equals(PDF) && shareHolderModel instanceof OrganizationShareholderModel) {

						OrganizationShareholderModel organizationShareholderModel = (OrganizationShareholderModel) shareHolderModel;

						String fileName = "companyProfessionalLicenseFile" + nowTime.getTime() + "_" + file.getOriginalFilename();
						MediaModel media = licenseApplyService.uploadFile(file.getInputStream(), fileName, file.getOriginalFilename());
						organizationShareholderModel.setProfessionalLicenseCertificate(media);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * GET Contact Person
	 */
	public ContactPersonsData getContactPersonData() {
		ContactPersonModel contactPersonModel = licenseApplyService.getContactPerson();
		if (contactPersonModel != null) {
			return licenseContactPersonConverter.convert(contactPersonModel);
		}
		return null;
	}

	/**
	 * SAVE Contact Person
	 */
	public void saveContactPersonData(ContactPersonsData contactPersonData) {
		ContactPersonModel contactPersonModel = licenseApplyService.getContactPerson();
		if (contactPersonModel == null) {
			contactPersonModel = modelService.create(ContactPersonModel.class);
			licenseContactPersonReversePopulator.populate(contactPersonData, contactPersonModel);
			licenseApplyService.saveContactPerson(contactPersonModel);
		} else {
			licenseContactPersonReversePopulator.populate(contactPersonData, contactPersonModel);
			modelService.save(contactPersonModel);
		}
	}

	public void validateMediasFromEntityInfo(EntityInformationData sagiaApplyEntityInfoForm, HttpServletRequest request) {

		if (request instanceof MultipartHttpServletRequest) {

			EntityInformationData editingForm = getEntityInformationData();

			boolean validationNotRequired  =  editingForm != null && editingForm.getBoardResolutionFile() != null;
			boolean validationNotRequired1 = editingForm != null && editingForm.getLetterOfSupportFile() != null;
			
			boolean checkFinancialStatementFile = editingForm != null && editingForm.getFinancialStatementFile() != null;
			boolean checkIqamaFile = editingForm != null && editingForm.getIqamaFile() != null;
			boolean checkCrCertificateFile = editingForm != null && editingForm.getCrCertificateFile() != null;
			boolean checkGosiCertificateFile = editingForm != null && editingForm.getGosiCertificateFile() != null;
			boolean checkNoObjectionCertificateFile = editingForm != null && editingForm.getNoObjectionCertificateFile() != null;

			boolean mainBranchCR = editingForm != null && editingForm.getMainBranchCR() != null;
			boolean otherBranchCR1 = editingForm != null && editingForm.getOtherBranchCR1() != null;
			boolean otherBranchCR2 = editingForm != null && editingForm.getOtherBranchCR2() != null;
			boolean rhqCR1 = editingForm != null && editingForm.getRhqCR1() != null;
			boolean rhqCR2 = editingForm != null && editingForm.getRhqCR2() != null;
			boolean rhqCR3 = editingForm != null && editingForm.getRhqCR3() != null;
			boolean rhqCR4 = editingForm != null && editingForm.getRhqCR4() != null;
			boolean rhqEntityAsset = editingForm != null && editingForm.getRhqEntityAssetAttachment() != null;
			boolean rhqEnityRevenue = editingForm != null && editingForm.getRhqEntityRevenueAttachment() != null;
			boolean rhqStockMarket = editingForm != null && editingForm.getRhqStockMarketAttachment() != null;

			if(sagiaApplyEntityInfoForm.isIsEntrepreneur()){

					MultipartFile file1 = ((MultipartHttpServletRequest) request).getFile("customBoardResolutionFile");

					if ((file1 != null && !file1.isEmpty()) || validationNotRequired) {
						sagiaApplyEntityInfoForm.setBoardResolutionFileAdded(true);
					}


					MultipartFile file2 = ((MultipartHttpServletRequest) request).getFile("customLetterOfSupportFile");

					if ((file2 != null && !file2.isEmpty()) || validationNotRequired1) {
						sagiaApplyEntityInfoForm.setLetterOfSupportFileAdded(true);
					}
				}
			if(sagiaApplyEntityInfoForm.isIsPreApprovalNumber()){

				MultipartFile financialStatementFile = ((MultipartHttpServletRequest) request).getFile("customFinancialStatementFile");
				if ((financialStatementFile != null && !financialStatementFile.isEmpty()) || checkFinancialStatementFile) {
					sagiaApplyEntityInfoForm.setFinancialStatementFileAdded(true);
				}

				MultipartFile iqamaFile = ((MultipartHttpServletRequest) request).getFile("customIqamaFile");
				if ((iqamaFile != null && !iqamaFile.isEmpty()) || checkIqamaFile) {
					sagiaApplyEntityInfoForm.setIqamaFileAdded(true);
				}
				
				MultipartFile crCertificateFile = ((MultipartHttpServletRequest) request).getFile("customCrCertificateFile");
				if ((crCertificateFile != null && !crCertificateFile.isEmpty()) || checkCrCertificateFile) {
					sagiaApplyEntityInfoForm.setCrCertificateFileAdded(true);
				}
				
				MultipartFile gosiCertificateFile = ((MultipartHttpServletRequest) request).getFile("customGosiCertificateFile");
				if ((gosiCertificateFile != null && !gosiCertificateFile.isEmpty()) || checkGosiCertificateFile) {
					sagiaApplyEntityInfoForm.setGosiCertificateFileAdded(true);
				}
				
				MultipartFile noObjectionCertificateFile = ((MultipartHttpServletRequest) request).getFile("customNoObjectionCertificateFile");
				if ((noObjectionCertificateFile != null && !noObjectionCertificateFile.isEmpty()) || checkNoObjectionCertificateFile) {
					sagiaApplyEntityInfoForm.setNoObjectionCertificateFileAdded(true);
				}
			}
			
			if(sagiaApplyEntityInfoForm.isIsMoreThan2Branch()){

				MultipartFile mainBranchCRFile = ((MultipartHttpServletRequest) request).getFile("customMainBranchCRFile");

				if ((mainBranchCRFile != null && !mainBranchCRFile.isEmpty()) || mainBranchCR) {
					sagiaApplyEntityInfoForm.setMainBranchCRFileAdded(true);
				}
				
				MultipartFile otherBranchCR1File = ((MultipartHttpServletRequest) request).getFile("customOtherBranchCR1File");

				if ((otherBranchCR1File != null && !otherBranchCR1File.isEmpty()) || otherBranchCR1) {
					sagiaApplyEntityInfoForm.setOtherBranchCR1FileAdded(true);
				}

				MultipartFile otherBranchCR2File = ((MultipartHttpServletRequest) request).getFile("customOtherBranchCR2File");

				if ((otherBranchCR2File != null && !otherBranchCR2File.isEmpty()) || otherBranchCR2) {
					sagiaApplyEntityInfoForm.setOtherBranchCR2FileAdded(true);
				}
			}
			
			if(sagiaApplyEntityInfoForm.isIsEntityAssetMoreThanThreshold()){

				MultipartFile entityAssetFileName = ((MultipartHttpServletRequest) request).getFile("customEntityAssetFileName");

				if ((entityAssetFileName != null && !entityAssetFileName.isEmpty()) || rhqEntityAsset) {
					sagiaApplyEntityInfoForm.setIsEntityAssetMoreThanThreshold(true);
				}
			}
			
			if(sagiaApplyEntityInfoForm.isIsEntityListedInStockMarket()){

				MultipartFile entityListedInStockMarketFile = ((MultipartHttpServletRequest) request).getFile("customEntityListedInStockMarketFile");

				if ((entityListedInStockMarketFile != null && !entityListedInStockMarketFile.isEmpty()) || rhqStockMarket) {
					sagiaApplyEntityInfoForm.setRhqStockMarketAttachmentFileAdded(true);
				}
			}
			
			if(sagiaApplyEntityInfoForm.isIsEntityRevenueMoreThanThreshold()){

				MultipartFile entityRevenueFileName = ((MultipartHttpServletRequest) request).getFile("customEntityRevenueFileName");

				if ((entityRevenueFileName != null && !entityRevenueFileName.isEmpty()) || rhqEnityRevenue) {
					sagiaApplyEntityInfoForm.setRhqEntityRevenueAttachmentFileAdded(true);
				}
			}
			
			if(sagiaApplyEntityInfoForm.isIsMoreThan6Branch()){

				MultipartFile branchCR1FileName = ((MultipartHttpServletRequest) request).getFile("customBranchCR1FileName");

				if ((branchCR1FileName != null && !branchCR1FileName.isEmpty()) || rhqCR1) {
					sagiaApplyEntityInfoForm.setRhqCR1FileAdded(true);
				}
				
				MultipartFile branchCR2FileName = ((MultipartHttpServletRequest) request).getFile("customBranchCR2FileName");

				if ((branchCR2FileName != null && !branchCR2FileName.isEmpty()) || rhqCR2) {
					sagiaApplyEntityInfoForm.setRhqCR2FileAdded(true);
				}
				
				MultipartFile branchCR3FileName = ((MultipartHttpServletRequest) request).getFile("customBranchCR3FileName");

				if ((branchCR3FileName != null && !branchCR3FileName.isEmpty()) || rhqCR3) {
					sagiaApplyEntityInfoForm.setRhqCR3FileAdded(true);
				}
				
				MultipartFile branchCR4FileName = ((MultipartHttpServletRequest) request).getFile("customBranchCR4FileName");

				if ((branchCR4FileName != null && !branchCR4FileName.isEmpty()) || rhqCR4) {
					sagiaApplyEntityInfoForm.setRhqCR4FileAdded(true);
				}
			}
			
			}
	}

	public void validateMediasFromExistingShareHolder(ExistingShareholderData existingShareholderForm, HttpServletRequest request) {

		if (request instanceof MultipartHttpServletRequest) {

			MultipartFile existingProfessionalLicenseFile = ((MultipartHttpServletRequest) request).getFile("existingProfessionalLicenseFile");

			
			
			if ((existingProfessionalLicenseFile != null && !existingProfessionalLicenseFile.isEmpty()) || StringUtils.isNotEmpty(existingShareholderForm.getCode())) {
				existingShareholderForm.setProfessionalLicenseFileAdded(true);
			}
		}
	}


	public void validateMediasFromPersonShareHolder(PersonShareholderData form, HttpServletRequest request) {

		if (request instanceof MultipartHttpServletRequest) {

			MultipartFile passportFile = ((MultipartHttpServletRequest) request).getFile("passportFile");
			MultipartFile otherFile = ((MultipartHttpServletRequest) request).getFile("otherFile");
			MultipartFile professionalLicenseFile = ((MultipartHttpServletRequest) request).getFile("professionalLicenseFile");

			MultipartFile authorizationLetterFile = ((MultipartHttpServletRequest) request).getFile("authorizationLetterFile");
			MultipartFile idCopyFile = ((MultipartHttpServletRequest) request).getFile("idCopyFile");

			boolean isShareHolderEdition = StringUtils.isNotEmpty(form.getCode());

			if ((passportFile != null && !passportFile.isEmpty()) || isShareHolderEdition) {
				form.setPassportIdCopyFileAdded(true);
			}

			//if ((otherFile != null && !otherFile.isEmpty()) || isShareHolderEdition) {
				//form.setOtherFileAdded(true);
			//}
			
			if ((professionalLicenseFile != null && !professionalLicenseFile.isEmpty()) || isShareHolderEdition) {
				form.setProfessionalLicenseFileAdded(true);
			}

			if (form.getDelegateInfo() != null) {
				DelegateInformationData delegate = form.getDelegateInfo();
				if (!delegate.isDelegateYourself()) {

					if ((authorizationLetterFile != null && !authorizationLetterFile.isEmpty()) || isShareHolderEdition) {
						form.getDelegateInfo().setAuthorisationLetterFileAdded(true);
					}

					if ((idCopyFile != null && !idCopyFile.isEmpty()) || isShareHolderEdition) {
						form.getDelegateInfo().setSaudiIdCopyFileAdded(true);
					}
				}
			}

		}
	}

	public void validateMediasFromOrganizationShareHolder(OrganizationShareholderData form, HttpServletRequest request) {

		if (request instanceof MultipartHttpServletRequest) {

			MultipartFile companyRegistrationFile = ((MultipartHttpServletRequest) request).getFile("companyRegistrationFile");
			MultipartFile companyFinancialStatementFile = ((MultipartHttpServletRequest) request).getFile("companyFinancialStatementFile");
			MultipartFile companyMemoAssociationFile = ((MultipartHttpServletRequest) request).getFile("companyMemoAssociationFile");
			MultipartFile companyProfessionalLicenseFile = ((MultipartHttpServletRequest) request).getFile("companyProfessionalLicenseFile");

			MultipartFile authorizationLetterFile = ((MultipartHttpServletRequest) request).getFile("companyFinancialStatementFile");
			MultipartFile idCopyFile = ((MultipartHttpServletRequest) request).getFile("saudiIdCopy");

			boolean isShareHolderEdition = StringUtils.isNotEmpty(form.getCode());

			if ((companyRegistrationFile != null && !companyRegistrationFile.isEmpty()) || isShareHolderEdition) {
				form.setCommercialRegCopyFileAdded(true);
			}

			if ((companyFinancialStatementFile != null && !companyFinancialStatementFile.isEmpty()) || isShareHolderEdition) {
				form.setLastYearFinStatementFileAdded(true);
			}

			if ((companyMemoAssociationFile != null && !companyMemoAssociationFile.isEmpty()) || isShareHolderEdition) {
				form.setCompanyMemoAssociationFileAdded(true);
			}
			
			if ((companyProfessionalLicenseFile != null && !companyProfessionalLicenseFile.isEmpty()) || isShareHolderEdition) {
				form.setProfessionalLicenseFileAdded(true);
			}

			if (form.getDelegateInfo() != null) {
				DelegateInformationData delegate = form.getDelegateInfo();
				if (delegate.isDelegate()) {

					if ((authorizationLetterFile != null && !authorizationLetterFile.isEmpty()) || isShareHolderEdition) {
						form.getDelegateInfo().setAuthorisationLetterFileAdded(true);
					}

					if ((idCopyFile != null && !idCopyFile.isEmpty()) || isShareHolderEdition) {
						form.getDelegateInfo().setSaudiIdCopyFileAdded(true);
					}
				}
			}
		}
	}
	/**
	 * Format json hashmap business activities in a list of IsicActivities object.
	 */
	public List<IsicActivity> populateBusinessActivities (List<HashMap<String, String>> businessActivitiesList) {
		List<IsicActivity> isicActivityList = new ArrayList<>();

		for (HashMap<String, String> businessActivity : businessActivitiesList) {
			IsicActivity isicActivity = new IsicActivity();

			isicActivity.setActivityId(businessActivity.get("activityId"));
			isicActivity.setBranchId(businessActivity.get("branchId"));
			isicActivity.setDescription(businessActivity.get("description"));
			isicActivity.setQeemahChannel(businessActivity.get("qeemahChannel"));
			isicActivity.setSplRequirementId(businessActivity.get("splrequirementId"));
			isicActivity.setGroupId(businessActivity.get("groupId"));
			isicActivity.setSectionId(businessActivity.get("sectionId"));
			isicActivity.setClassId(businessActivity.get("classId"));
			isicActivity.setDivisionId(businessActivity.get("divisionId"));

			isicActivityList.add(isicActivity);
		}

		return isicActivityList;
	}

	/**
	 * Format json hashmap business activities in a list of IsicActivities object.
	 */
	public List<String> prepareBusinessActivitiesHashMap (List<IsicActivity> businessActivitiesList) {
		List<String> isicActivityList = new ArrayList<>();
		Gson gson = new Gson();

		for (IsicActivity businessActivity : businessActivitiesList) {
			HashMap<String, String> isicActivity = new HashMap<>();

			List<String> activitiesId = new ArrayList<>();
			activitiesId.add(businessActivity.getActivityId());
			List<IsicTextsModel> isicTextsList = isicMasterDataService.getTextsDataFromType("ACTIVITY", activitiesId);
			String activityDescription = isicTextsList.get(0).getDescription();

			String descriptionName = businessActivity.getActivityId() + " - " + activityDescription;

			isicActivity.put("activityId", businessActivity.getActivityId());
			isicActivity.put("branchId", businessActivity.getBranchId());
			isicActivity.put("description", activityDescription);
			isicActivity.put("qeemahChannel", businessActivity.getQeemahChannel());
			isicActivity.put("splrequirementId", businessActivity.getSplRequirementId());
			isicActivity.put("groupId", businessActivity.getGroupId());
			isicActivity.put("sectionId", businessActivity.getSectionId());
			isicActivity.put("classId", businessActivity.getClassId());
			isicActivity.put("divisionId", businessActivity.getDivisionId());
			isicActivity.put("branchIdName", descriptionName);
			isicActivity.put("descriptionName", descriptionName);
			isicActivity.put("sectionIdName", descriptionName);
			isicActivity.put("activityIdName", descriptionName);
			isicActivity.put("splrequirementIdName", descriptionName);
			isicActivity.put("divisionIdName", descriptionName);
			isicActivity.put("classIdName", descriptionName);
			isicActivity.put("groupIdName", descriptionName);
			isicActivity.put("qeemahChannelName", descriptionName);

			isicActivityList.add(gson.toJson(isicActivity));
		}

		return isicActivityList;
	}

	public Map<String, List> getSelectedISICDetails() {

		EntityInformationModel entityInformation = licenseApplyService.getEntityInformation();
		if(entityInformation != null && !Collections.isEmpty(entityInformation.getIsicActivities()))
		{
			List<IsicMasterModel> isicActivities = entityInformation.getIsicActivities();
			return sagiaIsicFacade.getIsicDetails(isicActivities);
		}
		return null;
	}

    public void populateExistingShareholderMediaOnFormError (ExistingShareholderData existingShareholderForm) {
        if (StringUtils.isNotEmpty(existingShareholderForm.getCode())) {
            ExistingShareholderData existingShareholderData = getExistingShareholderByCode(existingShareholderForm.getCode());

            if (existingShareholderData.getCommercialRegistration() != null) {
                existingShareholderForm.setCommercialRegistration(existingShareholderData.getCommercialRegistration());
            }

            if (existingShareholderData.getLastBudget() != null) {
                existingShareholderForm.setLastBudget(existingShareholderData.getLastBudget());
            }
            
            if (existingShareholderData.getProfessionalLicenseCertificate() != null) {
            	existingShareholderForm.setProfessionalLicenseCertificate(existingShareholderData.getProfessionalLicenseCertificate());
            }
        }
    }

    public void populatePersonShareholderMediaOnFormError (PersonShareholderData personShareholderForm) {
        if (StringUtils.isNotEmpty(personShareholderForm.getCode())) {
            PersonShareholderData personShareholderData = getPersonShareholderByCode(personShareholderForm.getCode());

            if (personShareholderData.getPassportIdCopy() != null) {
                personShareholderForm.setPassportIdCopy(personShareholderData.getPassportIdCopy());
            }

            if (personShareholderData.getOther() != null) {
                personShareholderForm.setOther(personShareholderData.getOther());
            }
            
            if (personShareholderData.getProfessionalLicenseCertificate() != null) {
            	personShareholderForm.setProfessionalLicenseCertificate(personShareholderData.getProfessionalLicenseCertificate());
            }

            if (personShareholderData.getDelegateInfo() != null) {
                if (personShareholderData.getDelegateInfo().getAuthorisationLetter() != null) {
                    personShareholderForm.getDelegateInfo().setAuthorisationLetter(personShareholderData.getDelegateInfo().getAuthorisationLetter());
                }

                if (personShareholderData.getDelegateInfo().getSaudiIdCopy() != null) {
                    personShareholderForm.getDelegateInfo().setSaudiIdCopy(personShareholderData.getDelegateInfo().getSaudiIdCopy());
                }
            }
        }
    }

    public void populateOrganizationShareholderMediaOnFormError (OrganizationShareholderData organizationShareholderForm) {
        if (StringUtils.isNotEmpty(organizationShareholderForm.getCode())) {
            OrganizationShareholderData organizationShareholderData = getOrganizationShareholderByCode(organizationShareholderForm.getCode());

            if (organizationShareholderData.getCommercialRegCopy() != null) {
                organizationShareholderForm.setCommercialRegCopy(organizationShareholderData.getCommercialRegCopy());
            }

            if (organizationShareholderData.getLastYearFinStatement() != null) {
                organizationShareholderForm.setLastYearFinStatement(organizationShareholderData.getLastYearFinStatement());
            }

            if (organizationShareholderData.getCompanyMemoAssociation() != null) {
                organizationShareholderForm.setCompanyMemoAssociation(organizationShareholderData.getCompanyMemoAssociation());
            }
            
            if (organizationShareholderData.getProfessionalLicenseCertificate() != null) {
                organizationShareholderForm.setProfessionalLicenseCertificate(organizationShareholderData.getProfessionalLicenseCertificate());
            }

            if (organizationShareholderData.getDelegateInfo() != null) {
                if (organizationShareholderData.getDelegateInfo().getAuthorisationLetter() != null) {
                    organizationShareholderForm.getDelegateInfo().setAuthorisationLetter(organizationShareholderData.getDelegateInfo().getAuthorisationLetter());
                }

                if (organizationShareholderData.getDelegateInfo().getSaudiIdCopy() != null) {
                    organizationShareholderForm.getDelegateInfo().setSaudiIdCopy(organizationShareholderData.getDelegateInfo().getSaudiIdCopy());
                }
            }
        }
    }

    public void repopulateEntityInfoMediaOnFormError (EntityInformationData entityInformationForm) {
		EntityInformationData entity = getEntityInformationData();

		if (entity != null) {
			if (entity.getLetterOfSupportFile() != null) {
				entityInformationForm.setLetterOfSupportFile(entity.getLetterOfSupportFile());
			}

			if (entity.getBoardResolutionFile() != null) {
				entityInformationForm.setBoardResolutionFile(entity.getBoardResolutionFile());
			}
			
			if (entity.getMainBranchCR() != null) {
				entityInformationForm.setMainBranchCR(entity.getMainBranchCR());
			}
			if (entity.getOtherBranchCR1() != null) {
				entityInformationForm.setOtherBranchCR1(entity.getOtherBranchCR1());
			}
			if (entity.getOtherBranchCR2() != null) {
				entityInformationForm.setOtherBranchCR2(entity.getOtherBranchCR2());
			}
			if (entity.getRhqEntityAssetAttachment() != null) {
				entityInformationForm.setRhqEntityAssetAttachment(entity.getRhqEntityAssetAttachment());
			}
			if (entity.getRhqEntityRevenueAttachment() != null) {
				entityInformationForm.setRhqEntityRevenueAttachment(entity.getRhqEntityRevenueAttachment());
			}
			if (entity.getRhqStockMarketAttachment() != null) {
				entityInformationForm.setRhqStockMarketAttachment(entity.getRhqStockMarketAttachment());
			}
			if (entity.getRhqCR1() != null) {
				entityInformationForm.setRhqCR1(entity.getRhqCR1());
			}
			if (entity.getRhqCR2() != null) {
				entityInformationForm.setRhqCR2(entity.getRhqCR2());
			}
			if (entity.getRhqCR3() != null) {
				entityInformationForm.setRhqCR3(entity.getRhqCR3());
			}
			if (entity.getRhqCR4() != null) {
				entityInformationForm.setRhqCR4(entity.getRhqCR4());
			}
			
		}
	}

	public SagiaLicenseModel changeLicneseStatusTODraft() {
		
		SagiaLicenseModel originalLicense = licenseApplyService.getCurrentProcessedLicense();
		
		//save a version of the original license		
		return licenseApplyService.cloneLicense(originalLicense);		
		
				 
	}
	
	public SagiaCMSParagraphMediaComponentModel getParagraphLicenseMedia(String uid)
	{
		return licenseApplyService.getParagraphLicenseMedia(uid);
	}
	
}
