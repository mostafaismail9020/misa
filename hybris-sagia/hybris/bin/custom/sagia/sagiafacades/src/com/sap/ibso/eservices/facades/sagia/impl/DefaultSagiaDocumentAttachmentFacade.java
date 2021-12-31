package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.populators.DocumentAttachmentPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaDocumentAttachmentFacade;
import com.sap.ibso.eservices.facades.data.DocumentAttachment;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.services.DocumentAttachmentService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * DefaultSagiaDocumentAttachmentFacade
 */
public class DefaultSagiaDocumentAttachmentFacade implements SagiaDocumentAttachmentFacade {
    DocumentAttachmentService documentAttachmentService;
    DocumentAttachmentPopulator documentAttachmentPopulator;

    @Override
    public Collection<DocumentAttachment> getAttchedDocumentList(String objectId) {
        Collection<DocumentAttachment> documentAttachmentCollection = new ArrayList<>();
        Collection<DocAttachSetData> documentAttachmentCollectionData = documentAttachmentService.getCollection(objectId);
        if(CollectionUtils.isNotEmpty(documentAttachmentCollectionData)){
            for(DocAttachSetData item : documentAttachmentCollectionData){
                DocumentAttachment documentAttachment = new DocumentAttachment();
                documentAttachmentPopulator.populate(item, documentAttachment);
                documentAttachmentCollection.add(documentAttachment);
            }
        }
        return documentAttachmentCollection;
    }


    /**
     * @param documentAttachmentService
     */
    public void setDocumentAttachmentService(DocumentAttachmentService documentAttachmentService) {
        this.documentAttachmentService = documentAttachmentService;
    }

    /**
     * @param documentAttachmentPopulator
     */
    public void setDocumentAttachmentPopulator(DocumentAttachmentPopulator documentAttachmentPopulator) {
        this.documentAttachmentPopulator = documentAttachmentPopulator;
    }
}
