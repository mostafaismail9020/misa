package com.sap.ibso.eservices.facades.populators.license.replace;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementAttachments;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class LicenseReplacementAttachmentReversePopulator implements Populator<LicenseReplacementAttachments, ContentHDRData > {
    private SagiaFormatProvider sagiaFormatProvider;
    /**
     * Populate from LicenseReplacementAttachments  to LicenseReplaceMentToContentNavData .
     * @param source the source object
     * @param target the target to fill
     */
    @Override
    public void populate(LicenseReplacementAttachments source, ContentHDRData target) {
        target.setObjectId(source.getObjectID());
        target.setObjectGuid(source.getObjectGuid());
        target.setDocumentID(source.getDocumentID());
        target.setFilename(source.getFilename());
        target.setFilesize(source.getFilesize());
        target.setMimetype(source.getMimetype());
        target.setContentType(source.getContentType());
        target.setCrtdby(source.getCrtdby());
        target.setCrtdon(target.getCrtdon());
        target.setDocKeyID(source.getDocKeyID());
        target.setShDocID(source.getShDocID());
        target.setStage(source.getStage());
    }
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

}
