package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.DocumentAttachment;

import java.util.Collection;

/**
 * SagiaDocumentAttachmentFacade
 */
public interface SagiaDocumentAttachmentFacade {
    /**
     * gets AttachedDocument List
     * @param objectId objectId
     * @return Collection of DocumentAttachment
     */
    Collection<DocumentAttachment> getAttchedDocumentList(String objectId);

}
