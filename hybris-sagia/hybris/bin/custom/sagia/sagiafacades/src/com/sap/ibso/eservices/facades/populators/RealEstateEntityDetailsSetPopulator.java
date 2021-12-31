package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateEntityDetailsSet;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateEntityDetailsSetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class RealEstateEntityDetailsSetPopulator implements Populator<RealEstateEntityDetailsSetData, RealEstateEntityDetailsSet> {
    @Override
    public void populate(RealEstateEntityDetailsSetData source, RealEstateEntityDetailsSet target) throws ConversionException {
        target.setUsername(source.getUsername());
        target.setAllowedToSell("X".equals(source.getSell()) ? true : false);
        target.setAllowedToBuy("X".equals(source.getBuy()) ? true : false);
    }
}
