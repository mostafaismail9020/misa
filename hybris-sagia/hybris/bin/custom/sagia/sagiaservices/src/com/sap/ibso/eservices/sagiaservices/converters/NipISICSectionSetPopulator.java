package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPISICSectionSetData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipISICSectionSetPopulator extends ODataPopulator<NIPISICSectionSetData> {
    @Override
    public void populate(ODataModel model, NIPISICSectionSetData nipisicSectionSetData) throws ConversionException {
        super.populate(model, nipisicSectionSetData, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
    }
}
