package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah.ShareholderAttachment;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ShareholderAttachmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ShareholderAttachmentReversePopulator implements Populator<ShareholderAttachment, ShareholderAttachmentData> {
    @Override
    public void populate(ShareholderAttachment shareholderAttachment, ShareholderAttachmentData shareholderAttachmentData) throws ConversionException {
        shareholderAttachmentData.setRefId(shareholderAttachment.getRefId());
        shareholderAttachmentData.setEntityNumber(shareholderAttachment.getEntityNumber());
        shareholderAttachmentData.setFileType(shareholderAttachment.getFileType());
        shareholderAttachmentData.setFileName(shareholderAttachment.getFileName());
        shareholderAttachmentData.setFileContent(shareholderAttachment.getFileContent());
        shareholderAttachmentData.setFileMtype(shareholderAttachment.getFileMimeType());
    }
}
