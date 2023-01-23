package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceInfoDataCRM;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * SagiaGovtInfoDataService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaIgniteInfoDataService extends AbstractSagiaService<SagiaGovtServiceInfoDataCRM> {

    /**
     * Gets the info data for the government categories from CRM.
     * @return info data object from CRM.
     */
    public SagiaGovtServiceInfoDataCRM getInfoData() {
        return super.get(SagiaGovtServiceInfoDataCRM.class, (Object) "");
    }
}
