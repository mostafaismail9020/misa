package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpRegionData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SPRegionPopulator extends ODataPopulator<SpRegionData> {
    @Override
    public void populate(ODataModel model, SpRegionData spRegion) throws ConversionException {
        super.populate(model, spRegion, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
    }
}
