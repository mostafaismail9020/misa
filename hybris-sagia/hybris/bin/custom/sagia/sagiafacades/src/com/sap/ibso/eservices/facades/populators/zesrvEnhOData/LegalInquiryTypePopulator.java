package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryType;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryDropdownSetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class LegalInquiryTypePopulator implements Populator<LegalInquiryDropdownSetData, LegalInquiryType> {
    /**
     * Populate from LegalInquiryDropdownSetData to LegalInquiryType
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */

    @Override
    public void populate(LegalInquiryDropdownSetData source, LegalInquiryType target) throws ConversionException {
        target.setId(source.getLegenqId());
        target.setDescription(source.getDescription());
    }
}


