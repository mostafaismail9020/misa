package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SagiaGovtServiceFileUploadData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * SagiaGovtCreationService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaGovtCreationService extends AbstractSagiaService<SagiaGovtServiceFileUploadData> {

    /**
     * Create government service method.
     * @param sagiaGovtServiceFileUploadData sagiaGovtServiceFileUploadData
     */
    public void createGovtService(SagiaGovtServiceFileUploadData sagiaGovtServiceFileUploadData){
        super.save(sagiaGovtServiceFileUploadData);
    }
}
