package com.sap.ibso.eservices.facades.populators;


import com.sap.ibso.eservices.facades.data.specialservices.ServiceAttachment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceAttachmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceAttachmentReversePopulator implements Populator<ServiceAttachment, ServiceAttachmentData> {

    /**
     * @param serviceAttachment
     * @param serviceAttachmentData
     * @throws ConversionException
     */
    @Override
    public void populate(ServiceAttachment serviceAttachment, ServiceAttachmentData serviceAttachmentData) throws ConversionException {
        serviceAttachmentData.setOBJECT_GUID(serviceAttachment.getObjectGuid());
        serviceAttachmentData.setOBJECT_ID(serviceAttachment.getObjectId());
        serviceAttachmentData.setERROR_MSG(serviceAttachment.getErrorMessage());
        serviceAttachmentData.setSUCCESS(serviceAttachment.getSuccess());
        serviceAttachmentData.setFilename(serviceAttachment.getFileName());
        serviceAttachmentData.setDOC_GUID(serviceAttachment.getDocumentGuid());
        serviceAttachmentData.setDOC_ID(serviceAttachment.getDocumentId());
        serviceAttachmentData.setMimeType(serviceAttachment.getMimeType());
        serviceAttachmentData.setFileContString(serviceAttachment.getFileContentString());
        serviceAttachmentData.setSize(serviceAttachment.getSize());
    }
}
