package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.SagiaMediaData;

/**
 * SagiaMediaFacade
 */
public interface SagiaMediaFacade {

    /**
     * Retrieves SagiaMediaForPageName
     * @param pageName pageName
     * @return SagiaMediaData
     */
    SagiaMediaData getSagiaMediaForPageName(String pageName);

    /**
     * Retrieves the download URL for the contact update template
     * @return Contact Update Template URL
     */
    String getContactUpdateTemplateUrl();
}
