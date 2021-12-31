package com.sap.ibso.eservices.bol.overview;

import com.sap.ibso.eservices.bol.overview.data.ServiceRequestsBackendData;
import com.sap.ibso.eservices.core.model.ServiceTypeModel;

import java.util.Map;
import java.util.Set;

/**
 * Provides synchronous access to an overview of e-service requests in SAP backend system.
 */
public interface ServiceRequestsOverviewBackendService
{
    /**
     * Retrieves overview data of (submitted) service requests (including status information) of an entity (i.e. an investor company).
     *
     * @param entityId                 the entity identifier
     * @param serviceTypesByCodes      the overview relevant service types
     * @param serviceTypeCategoryCodes the service type category codes for overview relevant service types
     * @return the service requests (overview) backend data
     */
    ServiceRequestsBackendData getServiceRequests(String entityId,
                                                  Map<String, ServiceTypeModel> serviceTypesByCodes,
                                                  Set<String> serviceTypeCategoryCodes);
}
