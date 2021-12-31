package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.ContentHDRImage;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AttachmentHDRData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServicesRequestImageReversePopulator implements Populator<ContentHDRImage, AttachmentHDRData> {
    @Override
    public void populate(ContentHDRImage contentHDRImage, AttachmentHDRData attachmentHDRData) throws ConversionException {
        attachmentHDRData.setDocumentID(contentHDRImage.getDocumentID());
        attachmentHDRData.setObjectGuid(contentHDRImage.getObjectGuid());
        attachmentHDRData.setObjectID(contentHDRImage.getObjectID());
        attachmentHDRData.setContentType(contentHDRImage.getContentType());
        attachmentHDRData.setCrtdby(contentHDRImage.getCreatedBy());
        attachmentHDRData.setCrton(contentHDRImage.getCreatedDate());
        attachmentHDRData.setFilename(contentHDRImage.getFileName());
        attachmentHDRData.setFilesize(contentHDRImage.getFileSize());
        attachmentHDRData.setMimetype(contentHDRImage.getMimetype());
        attachmentHDRData.setFileContString(contentHDRImage.getFileContentString());
    }
}
