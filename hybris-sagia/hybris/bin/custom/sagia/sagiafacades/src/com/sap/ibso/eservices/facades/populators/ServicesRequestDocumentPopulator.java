package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.ContentHDRDocument;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServicesRequestDocumentPopulator implements Populator<ContentHDRData, ContentHDRDocument> {
    @Override
    public void populate(ContentHDRData contentHDRData, ContentHDRDocument contentHDRDocument) throws ConversionException {
        contentHDRDocument.setDocumentID(contentHDRData.getDocumentID());
        contentHDRDocument.setObjectId(contentHDRData.getObjectId());
        contentHDRDocument.setContentType(contentHDRData.getContentType());
        contentHDRDocument.setCreatedBy(contentHDRData.getCrtdby());
        contentHDRDocument.setCreatedDate(contentHDRData.getCrtdon());
        contentHDRDocument.setKeyID(contentHDRData.getDocKeyID());
        contentHDRDocument.setFileName(contentHDRData.getFilename());
        contentHDRDocument.setFileSize(contentHDRData.getFilesize());
        contentHDRDocument.setMimetype(contentHDRData.getMimetype());
        contentHDRDocument.setObjectGuid(contentHDRData.getObjectGuid());
        contentHDRDocument.setShDocID(contentHDRData.getShDocID());
        contentHDRDocument.setStage(contentHDRData.getStage());
        contentHDRDocument.setFullFileName(contentHDRData.getFullFileName());
        contentHDRDocument.setFileContentString(contentHDRData.getFileContString());
    }
}
