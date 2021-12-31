package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCLASS_CONTData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.InputStream;
import java.util.Arrays;

/**
 * ClassificationUpgradeContentService
 */
public class ClassificationUpgradeContentService extends AbstractSagiaService<ZCLASS_CONTData> {
    /**
     * read MediaEntity from service by documents IDs
     * @param objectIDValue objectIDValue
     * @param docGUIDValue docGUIDValue
     * @return InputStream
     */
    public InputStream readContentAttachmentBy(String objectIDValue, String docGUIDValue) {
        String objectId = "ObjectID=" + "'" + objectIDValue + "'";
        String docGUID = "DocGUID=" + "'" + docGUIDValue + "'";
        return getMediaEntity(getEntitySetName(), Arrays.asList(objectId, docGUID));
    }
}
