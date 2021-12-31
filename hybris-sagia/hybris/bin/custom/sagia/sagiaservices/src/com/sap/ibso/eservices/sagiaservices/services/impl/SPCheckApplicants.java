package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceApplicantData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.*;

/**
 * SPCheckApplicants
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SPCheckApplicants extends AbstractSagiaService<ServiceApplicantData> {
    /**
     * retrieves a Collection of ServiceApplicantData
     * @param processType processType
     * @param categoryCode1 categoryCode1
     * @param categoryCode2 categoryCode2
     * @return Collection of ServiceApplicantData
     */
    public final Collection<ServiceApplicantData> getCollection(String processType, String categoryCode1, String categoryCode2){

        return super.getCollection(ServiceApplicantData.class,
                PROCESS_TYPE,
                quote(processType),
                CAT_CODE1,
                quote(categoryCode1),
                CAT_CODE2,
                quote(categoryCode2));

    }

    /**
     * Appends quotes
     * @param param param
     * @return String
     */
    private String quote(String param){
        return new StringBuilder().append("'").append(param).append("'").toString();
    }
}
