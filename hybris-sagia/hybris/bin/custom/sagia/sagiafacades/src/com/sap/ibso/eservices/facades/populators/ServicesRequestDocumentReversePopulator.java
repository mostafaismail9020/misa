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
public class ServicesRequestDocumentReversePopulator implements Populator<ContentHDRDocument, ContentHDRData> {
    @Override
    public void populate(ContentHDRDocument contentHDRDocument, ContentHDRData contentHDRData) throws ConversionException {
        contentHDRData.setDocumentID(contentHDRDocument.getDocumentID());
        contentHDRData.setObjectId(contentHDRDocument.getObjectId());
        contentHDRData.setContentType(contentHDRDocument.getContentType());
        contentHDRData.setCrtdby(contentHDRDocument.getCreatedBy());
        contentHDRData.setCrtdon(contentHDRDocument.getCreatedDate());
        contentHDRData.setDocKeyID(contentHDRDocument.getKeyID());
        contentHDRData.setFilename(contentHDRDocument.getFileName());
        contentHDRData.setFilesize(contentHDRDocument.getFileSize());
        contentHDRData.setMimetype(contentHDRDocument.getMimetype());
        contentHDRData.setObjectGuid(contentHDRDocument.getObjectGuid());
        contentHDRData.setShDocID(contentHDRDocument.getShDocID());
        contentHDRData.setStage(contentHDRDocument.getStage());
        contentHDRData.setFileContString(contentHDRDocument.getFileContentString());
    }
}
