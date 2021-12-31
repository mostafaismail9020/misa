package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.NationalInvestorAtachment;
import com.sap.ibso.eservices.sagiaservices.data.nip.NationalInvestorAtachmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipAttachmentReversePopulator implements Populator<NationalInvestorAtachment, NationalInvestorAtachmentData> {
    @Override
    public void populate(NationalInvestorAtachment nationalInvestorAtachment, NationalInvestorAtachmentData nationalInvestorAtachmentData) throws ConversionException {
        nationalInvestorAtachmentData.setDOC_ID(nationalInvestorAtachment.getId());
        nationalInvestorAtachmentData.setFILENAME(nationalInvestorAtachment.getFilename());
        nationalInvestorAtachmentData.setMimetype(nationalInvestorAtachment.getMimeType());
        nationalInvestorAtachmentData.setContent(nationalInvestorAtachment.getContent());
    }
}
