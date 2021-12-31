package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZATT_LISTData;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCLASS_DETSETData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.classification.dto.ClassificationUpgradeFormData;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.Map;

/**
 * ClassificationUpgradeDetailService
 */
public class ClassificationUpgradeDetailService extends AbstractSagiaService<ZCLASS_DETSETData> {
    /**
     * get a classification upgrade by objectID, with the navigation property expanded
     * @param objectID id of the classification upgrade
     * @return ZCLASS_DETSETData
     */
    public final ZCLASS_DETSETData getClassificationUpgradeBy(String objectID) {
        Map<String, String> queryOptions = new QueryOptionsBuilder()
                .expand("ZCLASSATT")
                .build();
        return get(ZCLASS_DETSETData.class, objectID, queryOptions);
    }

    /**
     * create new Classification upgrade
     * @param classificationUpgradeFormData classificationUpgradeFormData
     * @param supportedAttachments supportedAttachments
     */
    public void createClassificationUpgrade(ClassificationUpgradeFormData classificationUpgradeFormData,
                                                                  Collection<ZATT_LISTData> supportedAttachments) {
        ZCLASS_DETSETData newClassificationUpgrade = ClassificationUpgradeConverter.fromFormData(classificationUpgradeFormData,
                supportedAttachments);
        save(newClassificationUpgrade);
    }
}