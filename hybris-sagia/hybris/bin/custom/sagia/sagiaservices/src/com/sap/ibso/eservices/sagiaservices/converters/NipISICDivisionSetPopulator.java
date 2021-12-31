package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPISICDivisionSetData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipISICDivisionSetPopulator extends ODataPopulator<NIPISICDivisionSetData> {
    @Override
    public void populate(ODataModel model, NIPISICDivisionSetData nipisicDivisionSetData) throws ConversionException {
        super.populate(model, nipisicDivisionSetData, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
    }
}
