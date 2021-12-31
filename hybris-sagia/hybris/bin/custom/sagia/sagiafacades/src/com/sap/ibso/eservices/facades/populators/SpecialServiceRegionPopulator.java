package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.specialservices.Region;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpRegionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SpecialServiceRegionPopulator implements Populator<SpRegionData, Region> {
    @Override
    public void populate(SpRegionData spRegionData, Region region) throws ConversionException {
        region.setLanguage(spRegionData.getLANGUAGE());
		region.setCode(spRegionData.getREGIONCODE());
		region.setName(spRegionData.getREGION());
    }
}
