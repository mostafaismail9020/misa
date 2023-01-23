package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SagiaGovtServiceFileUploadData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SagiaIgniteServiceFileUploadData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * SagiaIgniteCreationService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2023 SAP
 */
public class SagiaIgniteCreationService extends AbstractSagiaService<SagiaIgniteServiceFileUploadData> {

    /**
     * Create Ignite service method.
     * @param sagiaIgniteServiceFileUploadData sagiaIgniteServiceFileUploadData
     */
    public void createIgniteService(SagiaIgniteServiceFileUploadData sagiaIgniteServiceFileUploadData){
        super.save(sagiaIgniteServiceFileUploadData);
    }
}
