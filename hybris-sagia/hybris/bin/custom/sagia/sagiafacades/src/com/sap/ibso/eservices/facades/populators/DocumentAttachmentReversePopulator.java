package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.DocumentAttachment;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class DocumentAttachmentReversePopulator implements Populator<DocumentAttachment, DocAttachSetData> {
    @Override
    public void populate(DocumentAttachment  source, DocAttachSetData target) throws ConversionException {
        target.setObjectId(source.getObjectId());
        target.setFileGuid(source.getFileGuid());
        target.setFilename(source.getFileName());
        target.setMimetype(source.getMimeType());
        target.setFileContent(source.getFileContent());
        target.setObjectType(source.getObjectType());
    }
}
