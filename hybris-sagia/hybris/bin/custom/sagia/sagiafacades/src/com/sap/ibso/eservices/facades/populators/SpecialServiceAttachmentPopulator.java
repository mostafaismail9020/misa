package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.specialservices.Attachment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpAttachmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SpecialServiceAttachmentPopulator implements Populator<SpAttachmentData, Attachment> {
    @Override
    public void populate(SpAttachmentData spAttachmentData, Attachment attachment) throws ConversionException {
        attachment.setLanguage(spAttachmentData.getLANGUAGE());
		attachment.setSequenceNumber(spAttachmentData.getSEQUENCE_NUMBER());
		attachment.setDescriptionPass(spAttachmentData.getDESCRIPTION_PASS());
		attachment.setMandatory(spAttachmentData.getMANDATORY());
		attachment.setZzapplyCategoryFlag(spAttachmentData.getZZAPPLY_CAT_FLAG());
		attachment.setDescription(spAttachmentData.getDESCRIPTION());
    }
}
