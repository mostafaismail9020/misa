package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.SagiaGovDocWasselCheck;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * GovDocWasselCheckService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class GovDocWasselCheckService extends AbstractSagiaService<SagiaGovDocWasselCheck> {

    public static final String CR_NUMBER = "CRNUMBER";

    /**
     * requests WasselCheck
     * @return SagiaGovDocWasselCheck
     */
    public SagiaGovDocWasselCheck requestWasselCheck(){
        return super.getByProperty(SagiaGovDocWasselCheck.class,CR_NUMBER,"");
    }
}
