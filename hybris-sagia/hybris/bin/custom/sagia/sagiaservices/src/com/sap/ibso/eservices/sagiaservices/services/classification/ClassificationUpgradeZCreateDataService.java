package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCREATEData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * ClassificationUpgradeZCreateDataService
 */
public class ClassificationUpgradeZCreateDataService extends AbstractSagiaService<ZCREATEData> {
    private static final String ENTITY_VALUE = "A";


    /**
     * get entity for classification upgrade
     *
     * @return ZCREATEData
     */
    public final ZCREATEData getZCreateData() {
        return get(ZCREATEData.class, ENTITY_VALUE);
    }
}
