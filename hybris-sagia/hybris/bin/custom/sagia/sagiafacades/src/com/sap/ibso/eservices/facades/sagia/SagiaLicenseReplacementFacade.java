package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.replace.LicenseReplaceMents;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentData;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.sagiaservices.services.licensereplacement.LicenseReplacementFormData;
import com.sap.ibso.eservices.sagiaservices.services.licensereplacement.LicenseReplacementResubmitFormData;

import java.util.Collection;
import java.util.List;

/**
 * SagiaLicenseReplacementFacade
 */
public interface SagiaLicenseReplacementFacade {
     /** 
      * retrieves LicenseReplacementDataList
      * @return Collection of LicenseReplaceMentData
      */
     Collection<LicenseReplaceMentData> getLicenseReplacementDataList();

     /**
      * retrieves LicenseReplacementData
      * @param id id
      * @return LicenseReplaceMentData
      */
     LicenseReplaceMentData getLicenseReplacementData(String id);

     /**
      * retrieves LicenseReplacementList
      * @return Collection of LicenseReplaceMents
      */
     Collection<LicenseReplaceMents> getLicenseReplacementList();

     /**
      * retrieves LicenseReplacement
      * @param id id
      * @return LicenseReplaceMents
      */
     LicenseReplaceMents getLicenseReplacement(String id);

     /**
      * retrieves LatestLicenseReplacement
      * @return LicenseReplaceMentData
      */
     LicenseReplaceMentData getLatestLicenseReplacement();

     /**
      * retrieves LicenseReplacementInfo
      * @return Collection of CustomizingGetData
      */
     Collection<CustomizingGetData> getLicenseReplacementInfo();

     /**
      * creates LicenseReplacement
      * @param licenseReplacementFormData licenseReplacementFormData
      * @param supportedAttachments supportedAttachments
      */
     void createLicenseReplacement(LicenseReplacementFormData licenseReplacementFormData, Collection<CustomizingGetData> supportedAttachments);

     /**
      * retrieves ContentDetailsService
      * @return ContentDetailsService
      */
     ContentDetailsService getContentDetailsService();

     /**
      * retrieves CustomizationListService
      * @return CustomizationListService
      */
     CustomizationListService getCustomizationListService();

     /**
      * updates
      * @param licenseReplacementFormData licenseReplacementFormData
      * @param previouslyAttachedFiles previouslyAttachedFiles
      */
	void update(LicenseReplacementResubmitFormData licenseReplacementFormData,
			List<ContentHDRData> previouslyAttachedFiles);
}
