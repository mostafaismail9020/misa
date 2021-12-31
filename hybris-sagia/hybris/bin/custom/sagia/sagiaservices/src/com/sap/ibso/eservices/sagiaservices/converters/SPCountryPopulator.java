package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpCountryData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SPCountryPopulator extends ODataPopulator<SpCountryData> {
    @Override
    public void populate(ODataModel model, SpCountryData spCountry) throws ConversionException {
        super.populate(model, spCountry, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);

    }
}
