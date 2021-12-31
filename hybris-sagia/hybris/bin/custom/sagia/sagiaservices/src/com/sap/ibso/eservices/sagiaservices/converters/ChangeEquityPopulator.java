package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ChangeEquity;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ChangeEquityPopulator extends ODataPopulator<ChangeEquity>{
    @Override
    public void populate(ODataModel model, ChangeEquity changeEquity) throws ConversionException {
        super.populate(model, changeEquity,SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
    }
}
