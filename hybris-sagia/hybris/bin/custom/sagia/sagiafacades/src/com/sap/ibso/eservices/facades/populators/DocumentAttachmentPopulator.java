package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.DocumentAttachment;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.ContentHDRUtils;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class DocumentAttachmentPopulator implements Populator<DocAttachSetData, DocumentAttachment> {
    @Override
    public void populate(DocAttachSetData docAttachSetData, DocumentAttachment documentAttachment) throws ConversionException {
        documentAttachment.setObjectId(docAttachSetData.getObjectId());
        documentAttachment.setFileGuid(docAttachSetData.getFileGuid());
        documentAttachment.setFileName(docAttachSetData.getFilename());
        documentAttachment.setMimeType(docAttachSetData.getMimetype());
        documentAttachment.setFileContent(docAttachSetData.getFileContent());
        documentAttachment.setObjectType(docAttachSetData.getObjectType());
        
        String fullFileName = ContentHDRUtils.createFullFileNameFrom(documentAttachment.getFileName(), documentAttachment.getMimeType());
        documentAttachment.setFullFileName(fullFileName);
    }
}
