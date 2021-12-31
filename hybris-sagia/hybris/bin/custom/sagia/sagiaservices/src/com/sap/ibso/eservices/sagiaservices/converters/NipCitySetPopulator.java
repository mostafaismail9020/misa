package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPCitySetData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipCitySetPopulator extends ODataPopulator<NIPCitySetData> {
    @Override
    public void populate(ODataModel model, NIPCitySetData nipCitySetData) throws ConversionException {
        super.populate(model, nipCitySetData, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
    }
}
