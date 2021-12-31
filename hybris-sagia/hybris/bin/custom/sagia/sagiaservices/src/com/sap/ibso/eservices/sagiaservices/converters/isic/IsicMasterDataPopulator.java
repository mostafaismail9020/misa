package com.sap.ibso.eservices.sagiaservices.converters.isic;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.data.IsicMasterSetData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class IsicMasterDataPopulator extends ODataPopulator<IsicMasterSetData> {
    @Override
    public void populate(ODataModel model, IsicMasterSetData isicMasterSetData) throws ConversionException {
        super.populate(model, isicMasterSetData, PropertyNamingStrategy.UPPER_CAMEL_CASE);
    }
}
