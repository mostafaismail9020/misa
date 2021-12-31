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
public class SpecialServiceAttachmentReversePopulator implements Populator<Attachment, SpAttachmentData> {
    @Override
    public void populate(Attachment attachment, SpAttachmentData spAttachmentData) throws ConversionException {
        spAttachmentData.setLANGUAGE(attachment.getLanguage());
		spAttachmentData.setSEQUENCE_NUMBER(attachment.getSequenceNumber());
		spAttachmentData.setDESCRIPTION_PASS(attachment.getDescriptionPass());
		spAttachmentData.setMANDATORY(attachment.getMandatory());
		spAttachmentData.setZZAPPLY_CAT_FLAG(attachment.getZzapplyCategoryFlag());
		spAttachmentData.setDESCRIPTION(attachment.getDescription());
    }
}
