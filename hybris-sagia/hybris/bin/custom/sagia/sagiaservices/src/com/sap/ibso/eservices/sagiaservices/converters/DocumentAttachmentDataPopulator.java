package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class DocumentAttachmentDataPopulator extends ODataPopulator<DocAttachSetData> {
    @Override
    public void populate(ODataModel model, DocAttachSetData docAttachSetData) throws ConversionException {
        super.populate(model, docAttachSetData, PropertyNamingStrategy.UPPER_CAMEL_CASE);
    }
}
