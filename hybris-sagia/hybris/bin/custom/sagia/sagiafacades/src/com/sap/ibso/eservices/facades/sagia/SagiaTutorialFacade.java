package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.tutorial.SagiaTutorialData;

/**
 * Provides access to SagiaTutorialFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaTutorialFacade {
    /**
     * Gets sagia tutorial
     * @param pageUrl - String pageUrl
     * @return - SagiaTutorialData
     */
    SagiaTutorialData getTutorial(String pageUrl);
}
