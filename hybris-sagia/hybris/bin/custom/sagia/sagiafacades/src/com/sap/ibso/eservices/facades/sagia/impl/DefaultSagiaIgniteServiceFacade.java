package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.populators.SagiaIgniteServiceUploadPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaIgniteServiceFacade;
import com.sap.ibso.eservices.sagiaservices.data.SagiaIgniteServiceFileUpload;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SagiaIgniteServiceFileUploadData;
import com.sap.ibso.eservices.sagiaservices.services.impl.GlobalValsService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaIgniteCreationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DefaultSagiaGovtServiceFacade
 */
public class DefaultSagiaIgniteServiceFacade implements SagiaIgniteServiceFacade {
    private SagiaIgniteServiceUploadPopulator sagiaIgniteServiceUploadPopulator;
    private SagiaIgniteCreationService sagiaIgniteCreationService;
    @Autowired
    private GlobalValsService globalValsService;

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

    @Override
    public void checkCreateIgniteCRMReply() {
        globalValsService.checkIgniteServicesAvailability();
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
