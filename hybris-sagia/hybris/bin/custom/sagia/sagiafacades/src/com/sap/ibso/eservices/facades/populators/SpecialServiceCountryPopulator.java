package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.specialservices.Country;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpCountryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SpecialServiceCountryPopulator implements Populator<SpCountryData, Country> {
    @Override
    public void populate(SpCountryData spCountryData, Country country) throws ConversionException {
        country.setLanguage(spCountryData.getLANGUAGE());
		country.setCode(spCountryData.getCOUNTRYCODE());
		country.setName(spCountryData.getCOUNTRY());
    }
}
