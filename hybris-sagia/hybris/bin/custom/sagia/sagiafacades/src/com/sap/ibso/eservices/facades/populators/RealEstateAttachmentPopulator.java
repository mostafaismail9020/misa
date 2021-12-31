package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.sagiaservices.converters.attachment.ContentHDRUtils;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class RealEstateAttachmentPopulator implements Populator<RealEstateAttachmentData, RealEstateAttachment> {
    /**
     * Populate from RealEstateAttachmentData to RealEstateAttachment.
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(RealEstateAttachmentData source, RealEstateAttachment target) throws ConversionException {
        target.setObjectId(source.getObjectId());
        target.setDocGuid(source.getDocGuid());
        target.setFilename(source.getFilename());
        target.setMimeType(source.getMimeType());
        target.setFileContString(source.getFileContString());
        target.setObjectGUID(source.getObjectGUID());
        
        String fullFileName = ContentHDRUtils.createFullFileNameFrom(source.getFilename(), source.getMimeType());
        target.setFullFileName(fullFileName);
    }
}
