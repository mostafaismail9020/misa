package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaTutorialModel;

/**
 * Provides access to the Tutorial Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaTutorialService {

    /**
     * Gets sagia tutorial
     * @param url - String url
     * @return - SagiaTutorialModel
     */
    SagiaTutorialModel getSagiaTutorial(String url);
}
