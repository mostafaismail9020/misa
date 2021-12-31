package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaMediaModel;

/**
 * Provides access to the Media Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaMediaService {

    /**
     * Get the media with by its name.
     * @param name - The name for which the media is retrieved.
     * @return - Media Model with that specific name.
     */
    SagiaMediaModel getSagiaMediaForName(String name);
}
