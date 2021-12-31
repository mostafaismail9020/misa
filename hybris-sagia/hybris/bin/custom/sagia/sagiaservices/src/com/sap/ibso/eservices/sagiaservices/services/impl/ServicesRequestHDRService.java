package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceRequestHDRsData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * ServicesRequestHDRService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ServicesRequestHDRService extends AbstractSagiaService<ServiceRequestHDRsData>
{
    /**
     * retrieves ServiceRequestHDRsData by id
     * @param id id
     * @return ServiceRequestHDRsData
     */
    public final ServiceRequestHDRsData get(String id) {
        return super.get(ServiceRequestHDRsData.class,(Object) id,
                REQUEST_PARAMETER_EXPAND,"SrvReqToAddressNav,SrvReqToTextNav,SrvReqToAttachNav,SrvReqToContentNav");
    }

    /**
     * retrieves Collection of ServiceRequestHDRsData
     * @return Collection of ServiceRequestHDRsData
     */
    public final Collection<ServiceRequestHDRsData> getCollection(){
        return super.getCollection(ServiceRequestHDRsData.class
        );
    }

    /**
     * creates a ServiceRequestHDRsData
     * @param serviceRequestHDRsData serviceRequestHDRsData
     */
    public final void create(ServiceRequestHDRsData serviceRequestHDRsData){
        super.save(serviceRequestHDRsData);
    }


}
