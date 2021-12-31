package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.IncomeStat;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class IncomeStatPopulator extends ODataPopulator<IncomeStat>{

    @Override
    public void populate(ODataModel model, IncomeStat incomeStat, PropertyNamingStrategy propertyNamingStrategy) throws ConversionException {
        super.populate(model, incomeStat, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
    }
}
