package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.SagiaCRMGovtServiceData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.InputStream;
import java.util.Arrays;

/**
 * GovtServicesAttachmentService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class GovtServicesAttachmentService extends AbstractSagiaService<SagiaCRMGovtServiceData> {
    /**
     * reads GovtServicesPdf
     * @param objectId objectId
     * @param documentId documentId
     * @return InputStream
     */
    public InputStream readGovtServicesPdf(String objectId, String documentId) {
        String id = "ObjectId='" + objectId + "'";
        String docId = "DocumentID='" + documentId.toUpperCase() + "'";
        return getMediaEntity(getEntitySetName(), Arrays.asList(id, docId));
    }
}
