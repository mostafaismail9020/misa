package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.ServiceTypeCategoryModel;

import java.util.Map;

/**
 * Provides access to service type categories.
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaServiceTypeCategoryService
{
    /**
     * Collects all service type categories and maps each service type category code to its corresponding instance.
     *
     * @return the map of service type category codes to service type categories
     */
    Map<String, ServiceTypeCategoryModel> getServiceTypeCategoriesByCodes();

    /**
     * Collects all service type categories for overview relevant service types and maps each service type category
     * code to its corresponding instance.
     *
     * @return the map of overview relevant service type category codes to service type categories
     */
    Map<String, ServiceTypeCategoryModel> getOverviewRelevantServiceTypeCategoriesByCodes();
}
