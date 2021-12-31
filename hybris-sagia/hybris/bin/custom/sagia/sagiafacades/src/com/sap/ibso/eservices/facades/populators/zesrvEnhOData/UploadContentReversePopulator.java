package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.UploadContent;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.UploadContentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class UploadContentReversePopulator implements Populator<UploadContent, UploadContentData> {
    /**
     * Populate from UploadContent to UploadContentData
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(UploadContent source, UploadContentData target) throws ConversionException {
        target.setObjectId(source.getObjectId());
        target.setObjectGuid(source.getObjectGuid());
        target.setDocumentId(source.getDocumentId());
        target.setFileCont(source.getFileCont());
        target.setFilename(source.getFilename());
        target.setMimeType(source.getMimeType());
        target.setFileConts(source.getFileConts());
        target.setStage(source.getStage());
        target.setDocId(source.getDocId());
        target.setShDocID(source.getShDocID());

    }
}

