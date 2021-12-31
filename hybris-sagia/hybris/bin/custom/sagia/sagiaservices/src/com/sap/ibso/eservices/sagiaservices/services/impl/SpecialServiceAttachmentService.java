package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpAttachmentData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

/**
 * SpecialServiceAttachmentService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SpecialServiceAttachmentService extends AbstractSagiaService<SpAttachmentData> {

    /**
     * retrieves a Collection of SpAttachmentData
     * @param language language
     * @param processType processType
     * @param categoryCode1 categoryCode1
     * @param categoryCode2 categoryCode2
     * @return Collection of SpAttachmentData
     * @throws SagiaODataException exception
     */
    public Collection<SpAttachmentData> getCollection(String language, String processType, String categoryCode1, String categoryCode2) throws SagiaODataException {
        return super.getCollection(
                SpAttachmentData.class,
                "LANGUAGE",
                quote(language),
                "PROCESS_TYPE",
                quote(processType),
                "CAT_CODE1",
                quote(categoryCode1),
                "CAT_CODE2",
                quote(categoryCode2)
        );
    }

    /**
     * reads Attachment By objectId and documentGuid
     * @param objectId objectId
     * @param documentGuid documentGuid
     * @return InputStream
     */
    public InputStream readAttachmentBy(String objectId, String documentGuid) {
        String objectIdParam = "OBJECT_ID=" + "'" + objectId + "'";
        String documentGuidParam = "DOC_GUID=" + "'" + documentGuid + "'";
        return getMediaEntity("ATTACHMENTDETAILSSET", Arrays.asList(objectIdParam, documentGuidParam));
    }

    /**
     * Appends quotes
     * @param param param
     * @return String
     */
    private String quote(String param){
        return new StringBuilder().append("'").append(param).append("'").toString();
    }
}
