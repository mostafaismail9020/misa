package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah.BusinessType;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.IsicDetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class BusinessTypePopulator implements Populator<IsicDetData, BusinessType> {

    @Override
    public void populate(IsicDetData source, BusinessType target) throws ConversionException {
        target.setBusinessType(source.getFlag());
        target.setBusinessTypeText(source.getIsicDescription());
    }
}
