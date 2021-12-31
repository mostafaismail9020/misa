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
public class AttachmentReversePopulator implements Populator<Attachment, AttachmentData> {
    @Override
    public void populate(Attachment attachment, AttachmentData attachmentData) throws ConversionException {
        attachmentData.setAttachcode(attachment.getAttachmentCode());
        attachmentData.setContent(attachment.getContent());
        attachmentData.setFilename(attachment.getFileName());
        attachmentData.setMimetype(attachment.getMimeType());
    }

}
