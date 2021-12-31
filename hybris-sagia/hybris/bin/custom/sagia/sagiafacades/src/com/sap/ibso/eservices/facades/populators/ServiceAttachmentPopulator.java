package com.sap.ibso.eservices.facades.populators;


import com.sap.ibso.eservices.facades.data.specialservices.ServiceAttachment;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.ContentHDRUtils;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceAttachmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceAttachmentPopulator implements Populator<ServiceAttachmentData, ServiceAttachment> {

    @Override
    public void populate(ServiceAttachmentData serviceAttachmentData, ServiceAttachment serviceAttachment) throws ConversionException {
        serviceAttachment.setObjectGuid(serviceAttachmentData.getOBJECT_GUID());
        serviceAttachment.setObjectId(serviceAttachmentData.getOBJECT_ID());
        serviceAttachment.setErrorMessage(serviceAttachmentData.getERROR_MSG());
        serviceAttachment.setSuccess(serviceAttachmentData.getSUCCESS());
        serviceAttachment.setFileName(serviceAttachmentData.getFilename());
        serviceAttachment.setDocumentGuid(serviceAttachmentData.getDOC_GUID());
        serviceAttachment.setDocumentId(serviceAttachmentData.getDOC_ID());
        serviceAttachment.setMimeType(serviceAttachmentData.getMimeType());
        serviceAttachment.setFileContentString(serviceAttachmentData.getFileContString());
        serviceAttachment.setSize(serviceAttachmentData.getSize());
        
        String fullFileName = ContentHDRUtils.createFullFileNameFrom(serviceAttachment.getFileName(), serviceAttachment.getMimeType());
        serviceAttachment.setFullFileName(fullFileName);
    }
}
