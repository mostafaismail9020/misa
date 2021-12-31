package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpAttachmentData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SPAttachmentPopulator extends ODataPopulator<SpAttachmentData> {
    @Override
    public void populate(ODataModel model, SpAttachmentData spAttachment) throws ConversionException {
        super.populate(model, spAttachment, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
    }
}
