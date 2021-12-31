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
public class ServicesRequestImagePopulator implements Populator<AttachmentHDRData, ContentHDRImage> {
    @Override
    public void populate(AttachmentHDRData attachmentHDRData, ContentHDRImage contentHDRImage) throws ConversionException {
        contentHDRImage.setDocumentID(attachmentHDRData.getDocumentID());
        contentHDRImage.setObjectGuid(attachmentHDRData.getObjectGuid());
        contentHDRImage.setObjectID(attachmentHDRData.getObjectID());
        contentHDRImage.setContentType(attachmentHDRData.getContentType());
        contentHDRImage.setCreatedBy(attachmentHDRData.getCrtdby());
        contentHDRImage.setCreatedDate(attachmentHDRData.getCrton());
        contentHDRImage.setFileName(attachmentHDRData.getFilename());
        contentHDRImage.setFileSize(attachmentHDRData.getFilesize());
        contentHDRImage.setMimetype(attachmentHDRData.getMimetype());
        contentHDRImage.setFileContentString(attachmentHDRData.getFileContString());
    }
}
