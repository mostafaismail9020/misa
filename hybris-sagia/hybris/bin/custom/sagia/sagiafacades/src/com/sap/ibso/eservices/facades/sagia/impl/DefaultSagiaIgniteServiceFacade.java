package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.populators.SagiaIgniteServiceUploadPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaIgniteServiceFacade;
import com.sap.ibso.eservices.sagiaservices.data.SagiaIgniteServiceFileUpload;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SagiaIgniteServiceFileUploadData;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaIgniteCreationService;

/**
 * DefaultSagiaGovtServiceFacade
 */
public class DefaultSagiaIgniteServiceFacade implements SagiaIgniteServiceFacade {
    private SagiaIgniteServiceUploadPopulator sagiaIgniteServiceUploadPopulator;
    private SagiaIgniteCreationService sagiaIgniteCreationService;

    /**
     * Create Ignite service method.
     * @param sagiaIgniteServiceFileUpload
     */
    @Override
    public void createIgniteService(SagiaIgniteServiceFileUpload sagiaIgniteServiceFileUpload) {
        SagiaIgniteServiceFileUploadData sagiaIgniteServiceFileUploadData = new SagiaIgniteServiceFileUploadData();
        sagiaIgniteServiceUploadPopulator.populate(sagiaIgniteServiceFileUpload, sagiaIgniteServiceFileUploadData);
        sagiaIgniteCreationService.createIgniteService(sagiaIgniteServiceFileUploadData);
    }

    /**
     * @param sagiaIgniteServiceUploadPopulator
     */
    public void setSagiaIgniteServiceUploadPopulator(SagiaIgniteServiceUploadPopulator sagiaIgniteServiceUploadPopulator) {
        this.sagiaIgniteServiceUploadPopulator = sagiaIgniteServiceUploadPopulator;
    }

    /**
     * @param sagiaIgniteCreationService
     */
    public void setSagiaIgniteCreationService(SagiaIgniteCreationService sagiaIgniteCreationService) {
        this.sagiaIgniteCreationService = sagiaIgniteCreationService;
    }
}
