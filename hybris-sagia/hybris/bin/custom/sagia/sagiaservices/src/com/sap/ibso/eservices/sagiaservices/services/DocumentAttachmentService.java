package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

/**
 * DocumentAttachmentService
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DocumentAttachmentService extends AbstractSagiaService<DocAttachSetData> {
    /**
     * getCollection
     * @param srId srId
     * @return collection
     */
    public Collection<DocAttachSetData> getCollection(String srId){
        QueryOptionsBuilder query = new QueryOptionsBuilder()
                .filter("ObjectId eq '" + srId + "' and FileGuid eq ' '");
        return super.getCollection(DocAttachSetData.class, query.build());
    }


    /**
     * read MediaEntity from service by documents IDs
     *
     * @param objectIDValue objectIDValue
     * @param documentIdValue documentIdValue
     * @return InputStream
     */
    public InputStream readAttachmentBy(String objectIDValue, String documentIdValue) {
        String objectId = "ObjectId=" + "'" + objectIDValue + "'";
        String documentId = "DocumentID=" + "'" + documentIdValue + "'";
        return getMediaEntity(getEntitySetName(), Arrays.asList(objectId, documentId));
    }
}
