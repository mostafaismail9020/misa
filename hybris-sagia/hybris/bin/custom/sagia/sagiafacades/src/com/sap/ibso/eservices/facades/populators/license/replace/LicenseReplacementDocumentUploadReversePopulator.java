package com.sap.ibso.eservices.facades.populators.license.replace;

import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementAttachmentToBeUploaded;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadContentData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class LicenseReplacementDocumentUploadReversePopulator implements Populator<LicenseReplacementAttachmentToBeUploaded, UploadContentData> {
    @Override
    public void populate(LicenseReplacementAttachmentToBeUploaded source,UploadContentData target) {
        target.setFilename(source.getFilename());
        target.setMimeType(source.getMimeType());
        target.setFileCont(source.getFileCont());
        target.setFileContString(source.getFileContString());
        target.setDockey_ID(source.getDockeyID());
    }
}
