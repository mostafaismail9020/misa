package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.RegionCity;
import com.sap.ibso.eservices.facades.data.RegionCityData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class RegionCityPopulator implements Populator<RegionCityData, RegionCity> {
    /**
     * Populate from RegionCityData to RegionCity.
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(RegionCityData source, RegionCity target) throws ConversionException {
        target.setCityCode(source.getCityCode());
        target.setCityDesc(source.getCityDesc());
        target.setLanguage(source.getLanguage());
        target.setRegionCode(source.getRegionCode());
        target.setRegionDesc(source.getRegionDesc());
    }
}
