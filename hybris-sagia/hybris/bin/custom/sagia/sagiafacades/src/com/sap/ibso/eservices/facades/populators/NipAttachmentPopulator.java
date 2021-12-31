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
public class NipAttachmentPopulator implements Populator<NationalInvestorAtachmentData, NationalInvestorAtachment> {
    @Override
    public void populate(NationalInvestorAtachmentData nationalInvestorAtachmentData, NationalInvestorAtachment nationalInvestorAtachment) throws ConversionException {
        nationalInvestorAtachment.setId(nationalInvestorAtachmentData.getDOC_ID());
        nationalInvestorAtachment.setFilename(nationalInvestorAtachmentData.getFILENAME());
        nationalInvestorAtachment.setMimeType(nationalInvestorAtachmentData.getMimetype());
        nationalInvestorAtachment.setContent(nationalInvestorAtachmentData.getContent());
    }
}
