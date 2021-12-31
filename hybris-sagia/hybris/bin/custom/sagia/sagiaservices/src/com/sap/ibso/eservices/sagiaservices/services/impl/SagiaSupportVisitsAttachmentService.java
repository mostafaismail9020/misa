package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SupportVisitData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.InputStream;
import java.util.Arrays;

/**
 * SagiaSupportVisitsAttachmentService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaSupportVisitsAttachmentService extends AbstractSagiaService<SupportVisitData> {
    /**
     * reads SupportVisitPdf
     * @param objectId objectId
     * @param fileGuid fileGuid
     * @return InputStream
     */
    public InputStream readSupportVisitPdf(String objectId, String fileGuid) {
        String id = "ObjectId=" + "'" + objectId + "'";
        String docId = "FileGuid='" + fileGuid.toUpperCase() + "'";
        return getMediaEntity(getEntitySetName(), Arrays.asList(id, docId));
    }
}
