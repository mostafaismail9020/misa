package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiry;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class LegalInquiryPopulator implements Populator<LegalInquiryData, LegalInquiry> {
    /**
     * Populate from LegalInquiryData to LegalInquiry
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */

    @Override
    public void populate(LegalInquiryData source, LegalInquiry target) throws ConversionException {
        target.setSrId(source.getSrId());
        target.setSrGuid(source.getSrGuid());
        target.setBpId(source.getBpId());
        target.setBpGuid(source.getBpGuid());
        target.setLegEnqTitle(source.getLegEnqTitle());
        target.setLegEnqDesc(source.getLegEnqDesc());
        target.setTextMsg(source.getTextMsg());
        target.setTdid(source.getTdid());
    }
}


