package com.sap.ibso.eservices.facades.populators.license.replace;

import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementAttachmentToBeUploaded;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadContentData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class LicenseReplacementDocumentUploadPopulator implements Populator<UploadContentData, LicenseReplacementAttachmentToBeUploaded> {
    @Override
    public void populate(UploadContentData uploadContentData, LicenseReplacementAttachmentToBeUploaded licenseReplacementAttachmentToBeUploaded) {
        licenseReplacementAttachmentToBeUploaded.setFilename(uploadContentData.getFilename());
        licenseReplacementAttachmentToBeUploaded.setMimeType(uploadContentData.getMimeType());
        licenseReplacementAttachmentToBeUploaded.setFileCont(uploadContentData.getFileCont());
        licenseReplacementAttachmentToBeUploaded.setFileContString(uploadContentData.getFileContString());
        licenseReplacementAttachmentToBeUploaded.setDockeyID(uploadContentData.getDockey_ID());

    }
}
