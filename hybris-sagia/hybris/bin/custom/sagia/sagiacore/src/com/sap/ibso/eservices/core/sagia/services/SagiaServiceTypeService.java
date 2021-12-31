package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.ServiceTypeModel;

import java.util.Map;

/**
 * Provides access to service types.
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaServiceTypeService
{
    /**
     * Collects all service types and maps each service type code to its corresponding instance.
     *
     * @return the map of service type codes to service types
     */
    Map<String, ServiceTypeModel> getServiceTypesByCodes();

    /**
     * Collects all overview relevant service types and maps each service type code to its corresponding instance.
     *
     * @return the map of overview relevant service type codes to service types
     */
    Map<String, ServiceTypeModel> getOverviewRelevantServiceTypesByCodes();
}
