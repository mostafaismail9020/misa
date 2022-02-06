package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.enums.LicenseStatus;
import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import de.hybris.platform.core.model.media.MediaModel;
import com.sap.ibso.eservices.core.model.SagiaCMSParagraphMediaComponentModel;

import java.io.InputStream;
import java.util.List;

public interface LicenseApplyService {


	List<ShareHolderModel> getShareHolders();

	ContactPersonModel getContactPerson();

	void saveShareHolderAndLicense(ShareHolderModel  shareHolderModel, SagiaLicenseModel sagiaLicenseModel);

	void saveContactPerson(ContactPersonModel contactPersonModel);

	void saveShareHolderData(ShareHolderModel  shareHolderModel);

	EntityInformationModel getEntityInformation();

	SagiaLicenseModel getDraftLicense();
	
	SagiaLicenseModel getCurrentProcessedLicense();

	void saveEntityInformation(EntityInformationModel entityInformationModel);

	MediaModel uploadFile(final InputStream in, final String fileName, String realFileName);

	ShareHolderModel getShareHolder(String code);


	List<SagiaLicenseModel> getPendingLicenses();

    void removeShareholder(String code);


	SagiaLicenseModel getLicense(LicenseStatus licenseStatus);
	
	public SagiaLicenseModel cloneLicense(SagiaLicenseModel originalLicense) ;
	
	
	SagiaCMSParagraphMediaComponentModel getParagraphLicenseMedia(String uid);



	
}
