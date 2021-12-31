package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.StatusModel;

import java.util.Map;

/**
 * Provides access to status codes and names.
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaStatusService
{
    /**
     * Collects all statuses and maps each status code to its corresponding instance.
     *
     * @return the map of status codes to statuses
     */
    Map<String, StatusModel> getStatusesByCodes();
}
