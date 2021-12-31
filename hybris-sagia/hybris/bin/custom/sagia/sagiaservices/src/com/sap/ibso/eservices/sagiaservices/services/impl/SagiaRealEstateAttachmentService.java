package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.InputStream;
import java.util.Arrays;

/**
 * SagiaRealEstateAttachmentService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaRealEstateAttachmentService extends AbstractSagiaService<DocAttachSetData> {
    /**
     * reads RealEstatePdf
     * @param objectId objectId
     * @param documentId documentId
     * @return InputStream
     */
    public InputStream readRealEstatePdf(String objectId, String documentId) {
        String id = "ObjectID=" + "'" + objectId + "'";
        String docId = "DocGUID=" + "guid'" + documentId.toUpperCase() + "'";
        return getMediaEntity(getEntitySetName(), Arrays.asList(id, docId));
    }
}
