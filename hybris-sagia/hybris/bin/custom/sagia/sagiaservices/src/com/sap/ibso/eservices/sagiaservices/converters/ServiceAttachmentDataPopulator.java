package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceAttachmentData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceAttachmentDataPopulator extends ODataPopulator<ServiceAttachmentData> {
    @Override
    public void populate(ODataModel model, ServiceAttachmentData serviceAttachment) throws ConversionException {
        super.populate(model, serviceAttachment, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
        serviceAttachment.setFilename(model.get("Filename").toString());
        serviceAttachment.setMimeType(ObjectUtils.toString(model.get("MimeType")));
    }
}
