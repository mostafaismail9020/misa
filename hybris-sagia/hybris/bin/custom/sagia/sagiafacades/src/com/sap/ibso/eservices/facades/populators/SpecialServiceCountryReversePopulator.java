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
public class SpecialServiceCountryReversePopulator implements Populator<Country, SpCountryData> {
    @Override
    public void populate(Country country, SpCountryData spCountryData) throws ConversionException {
        spCountryData.setLANGUAGE(country.getLanguage());
		spCountryData.setCOUNTRYCODE(country.getCode());
		spCountryData.setCOUNTRY(country.getName());
    }
}
