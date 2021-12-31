package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.replace.LicenseReplaceMents;
import com.sap.ibso.eservices.facades.populators.license.replace.LicenseReplacementPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseReplacementFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentData;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.sagiaservices.services.licensereplacement.LicenseReplacementFormData;
import com.sap.ibso.eservices.sagiaservices.services.licensereplacement.LicenseReplacementResubmitFormData;
import com.sap.ibso.eservices.sagiaservices.services.licensereplacement.LicenseReplacementService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultSagiaLicenseReplacementFacade
 */
public class DefaultSagiaLicenseReplacementFacade implements SagiaLicenseReplacementFacade {
	private LicenseReplacementService licenseReplacementService;
	private CustomizationListService customizationListService;
	private ContentDetailsService contentDetailsService;
	private LicenseReplacementPopulator licenseReplacementPopulator;

	@Override
	public Collection<LicenseReplaceMentData> getLicenseReplacementDataList() {
		return licenseReplacementService.getCollection();
	}

	@Override
	public Collection<LicenseReplaceMents> getLicenseReplacementList() {
		List<LicenseReplaceMents> licenseReplaceMents = new ArrayList<>();
		Collection<LicenseReplaceMentData> licenseReplaceMentsData = licenseReplacementService.getCollection();
		if (licenseReplaceMentsData != null && !licenseReplaceMentsData.isEmpty()) {
			for (LicenseReplaceMentData licenseReplaceMentData : licenseReplaceMentsData) {
				LicenseReplaceMents licenseReplaceMent = new LicenseReplaceMents();
				licenseReplacementPopulator.populate(licenseReplaceMentData, licenseReplaceMent);
				licenseReplaceMents.add(licenseReplaceMent);
			}
		}
		return licenseReplaceMents;
	}

	@Override
	public LicenseReplaceMentData getLicenseReplacementData(final String id) {
		return licenseReplacementService.get(id);
	}
	
	@Override
	public LicenseReplaceMentData getLatestLicenseReplacement() {
		return licenseReplacementService.getLatestEntityCreated();
	}

	@Override
	public LicenseReplaceMents getLicenseReplacement(final String id) {
		LicenseReplaceMents licenseReplaceMent = new LicenseReplaceMents();
		LicenseReplaceMentData licenseReplaceMentData = licenseReplacementService.get(id);
		licenseReplacementPopulator.populate(licenseReplaceMentData, licenseReplaceMent);
		return licenseReplaceMent;
	}

	/**
	 * creates LicenseReplacement
	 * @param licenseReplacementFormData licenseReplacementFormData
	 * @param supportedAttachments supportedAttachments
	 */
	public void createLicenseReplacement(LicenseReplacementFormData licenseReplacementFormData, Collection<CustomizingGetData> supportedAttachments){
		licenseReplacementService.createLicenseReplacement(licenseReplacementFormData, supportedAttachments);
	}
	
	@Override
	public void update(LicenseReplacementResubmitFormData licenseReplacementFormData,
			List<ContentHDRData> previouslyAttachedFiles) {
		licenseReplacementService.updateLicenseReplacement(licenseReplacementFormData, previouslyAttachedFiles);
	}

	/**
	 * @return
	 */
	public Collection<CustomizingGetData> getLicenseReplacementInfo(){
		return customizationListService.readLicenseReplacementInfo();
	}

	/**
	 * @return
	 */
	public LicenseReplacementService getLicenseReplacementService() {
		return licenseReplacementService;
	}

	public void setLicenseReplacementService(LicenseReplacementService licenseReplacementService) {
		this.licenseReplacementService = licenseReplacementService;
	}

	/**
	 * @return
	 */
	public CustomizationListService getCustomizationListService() {
		return customizationListService;
	}

	/**
	 * @param customizationListService
	 */
	public void setCustomizationListService(CustomizationListService customizationListService) {
		this.customizationListService = customizationListService;
	}

	/**
	 * @return
	 */
	public ContentDetailsService getContentDetailsService() {
		return contentDetailsService;
	}

	/**
	 * @param contentDetailsService
	 */
	public void setContentDetailsService(ContentDetailsService contentDetailsService) {
		this.contentDetailsService = contentDetailsService;
	}

	/**
	 * @return
	 */
	public LicenseReplacementPopulator getLicenseReplacementPopulator() {
		return licenseReplacementPopulator;
	}

	/**
	 * @param licenseReplacementPopulator
	 */
	public void setLicenseReplacementPopulator(LicenseReplacementPopulator licenseReplacementPopulator) {
		this.licenseReplacementPopulator = licenseReplacementPopulator;
	}
}
