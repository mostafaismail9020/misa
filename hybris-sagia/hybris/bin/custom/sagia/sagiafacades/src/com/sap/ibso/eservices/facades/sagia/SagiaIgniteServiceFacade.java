package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.sagiaservices.data.SagiaIgniteServiceFileUpload;

/**
 * SagiaIgniteServiceFacade
 */
public interface SagiaIgniteServiceFacade {
    /**
     * creates IgniteService
     * @param sagiaIgniteServiceFileUpload sagiaIgniteServiceFileUpload
     */
    void createIgniteService(SagiaIgniteServiceFileUpload sagiaIgniteServiceFileUpload);
}
