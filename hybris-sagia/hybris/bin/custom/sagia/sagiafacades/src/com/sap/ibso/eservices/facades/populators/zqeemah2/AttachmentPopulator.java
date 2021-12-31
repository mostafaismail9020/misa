package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.Attachment;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.AttachmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class AttachmentPopulator implements Populator<AttachmentData, Attachment> {
    @Override
    public void populate(AttachmentData attachmentData, Attachment attachment) throws ConversionException {
        attachment.setAttachmentCode(attachmentData.getAttachcode());
		attachment.setContent(attachmentData.getContent());
		attachment.setFileName(attachmentData.getFilename());
		attachment.setMimeType(attachmentData.getMimetype());
    }
}
