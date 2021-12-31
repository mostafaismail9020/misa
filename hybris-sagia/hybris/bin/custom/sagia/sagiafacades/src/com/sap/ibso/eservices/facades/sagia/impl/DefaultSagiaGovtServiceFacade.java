package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.populators.SagiaGovtServiceUploadPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaGovtServiceFacade;
import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceFileUpload;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SagiaGovtServiceFileUploadData;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaGovtCreationService;

/**
 * DefaultSagiaGovtServiceFacade
 */
public class DefaultSagiaGovtServiceFacade implements SagiaGovtServiceFacade {
    private SagiaGovtServiceUploadPopulator sagiaGovtServiceUploadPopulator;
    private SagiaGovtCreationService sagiaGovtCreationService;

    /**
     * Create government service method.
     * @param sagiaGovtServiceFileUpload
     */
    @Override
    public void createGovtService(SagiaGovtServiceFileUpload sagiaGovtServiceFileUpload) {
        SagiaGovtServiceFileUploadData sagiaGovtServiceFileUploadData = new SagiaGovtServiceFileUploadData();
        sagiaGovtServiceUploadPopulator.populate(sagiaGovtServiceFileUpload, sagiaGovtServiceFileUploadData);
        sagiaGovtCreationService.createGovtService(sagiaGovtServiceFileUploadData);
    }

    /**
     * @param sagiaGovtServiceUploadPopulator
     */
    public void setSagiaGovtServiceUploadPopulator(SagiaGovtServiceUploadPopulator sagiaGovtServiceUploadPopulator) {
        this.sagiaGovtServiceUploadPopulator = sagiaGovtServiceUploadPopulator;
    }

    /**
     * @param sagiaGovtCreationService
     */
    public void setSagiaGovtCreationService(SagiaGovtCreationService sagiaGovtCreationService) {
        this.sagiaGovtCreationService = sagiaGovtCreationService;
    }
}
