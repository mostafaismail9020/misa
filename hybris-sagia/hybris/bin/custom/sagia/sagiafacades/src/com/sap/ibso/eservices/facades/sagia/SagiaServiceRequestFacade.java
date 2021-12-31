package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData;

import java.util.List;


/**
 * SagiaServiceRequestFacade
 */
public interface SagiaServiceRequestFacade {

    /**
     * Retrieves ServiceRequestsData
     * @return - Service Requests DTO for the  dashboard
     */
    List<ServiceRequestData> getServiceRequestsData();
}

