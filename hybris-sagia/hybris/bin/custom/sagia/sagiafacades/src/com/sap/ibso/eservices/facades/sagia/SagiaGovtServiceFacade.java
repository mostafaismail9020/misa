package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceFileUpload;

/**
 * SagiaGovtServiceFacade
 */
public interface SagiaGovtServiceFacade {
    /**
     * creates GovtService
     * @param sagiaGovtServiceFileUpload sagiaGovtServiceFileUpload
     */
    void createGovtService(SagiaGovtServiceFileUpload sagiaGovtServiceFileUpload);
}
