package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceInfoDataCRM;
import com.sap.ibso.eservices.sagiaservices.data.SagiaIgniteServiceInfoDataCRM;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * SagiaGovtInfoDataService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaIgniteInfoDataService extends AbstractSagiaService<SagiaIgniteServiceInfoDataCRM> {

    /**
     * Gets the info data for the government categories from CRM.
     * @return info data object from CRM.
     */
    public SagiaIgniteServiceInfoDataCRM getInfoData() {
        return super.get(SagiaIgniteServiceInfoDataCRM.class, (Object) "");
    }
}
