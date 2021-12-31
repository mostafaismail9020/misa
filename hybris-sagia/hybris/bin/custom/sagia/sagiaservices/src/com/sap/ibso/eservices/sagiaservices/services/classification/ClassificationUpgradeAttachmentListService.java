package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZATT_LISTData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.Map;

/**
 * ClassificationUpgradeAttachmentListService
 */
public class ClassificationUpgradeAttachmentListService extends AbstractSagiaService<ZATT_LISTData> {


    /**
     * get the list of attachments for classification upgrade
     * @return Collection of ZATT_LISTData
     */
    public Collection<ZATT_LISTData> getClassificationUpgradeAttachmentList() {
        AttachmentListFilterExpression filterExpression = new AttachmentListFilterExpression()
                .type("C")
                .subtype("C");
        Map<String, String> queryOptions = new QueryOptionsBuilder()
                .filter(filterExpression.build())
                .build();
        return getCollection(ZATT_LISTData.class, queryOptions);
    }
}
