package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.SagiaClassificationData;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZListData;

import java.util.Collection;
import java.util.List;

/**
 * SagiaClassificationFacade
 */
public interface SagiaClassificationFacade {

    /**
     * Loads the classification list needed to populate the classification upgrade form
     * @return the entire list of classifications
     */

    List<SagiaClassificationData> getClassificationsList();
    
    /**
     * get current classification level 
     * @return String
     */
    String getCurrentClassification();

    /**
     * retrieves LatestClassificationUpgradeRequest
     * @param classificationUpgradeList classificationUpgradeList
     * @return ZListData
     */
	ZListData getLatestClassificationUpgradeRequest(Collection<ZListData> classificationUpgradeList);
}
