package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaClassificationModel;

import java.util.List;

/**
 * Provides access to the Classification Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaClassificationService {

    List<SagiaClassificationModel> getClassifications();

    /**
     * Get a classification by its code.
     * @param code - The code for which the classification is retrieved.
     * @return - Classification model having the respective code.
     */
    SagiaClassificationModel getClassificationForCode(Integer code);
}
