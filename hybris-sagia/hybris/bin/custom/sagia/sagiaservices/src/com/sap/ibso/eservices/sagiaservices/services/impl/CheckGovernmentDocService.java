package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.GovtDocCheck;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * CheckGovernmentDocService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class CheckGovernmentDocService extends AbstractSagiaService<GovtDocCheck> {

    /**
     * Check government documents for License Renewal
     * @return - Government Documents Check response object
     */
    public GovtDocCheck checkGovernmentDocuments(){
        return super.get(GovtDocCheck.class,(Object) "");
    }
}
