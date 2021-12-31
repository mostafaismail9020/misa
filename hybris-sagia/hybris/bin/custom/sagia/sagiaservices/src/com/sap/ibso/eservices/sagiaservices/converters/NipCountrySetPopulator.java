package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPCountrySetData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipCountrySetPopulator extends ODataPopulator<NIPCountrySetData> {
    @Override
    public void populate(ODataModel model, NIPCountrySetData nipCountrySetData) throws ConversionException {
        super.populate(model, nipCountrySetData, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
    }
}
