package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import java.util.Map;
import java.util.Set;

/**
 * Provides access to the Search Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaSearchService {

    /**
     * Search through Sagia Services.
     * @param text - The text for which the search is made.
     * @return - The collection of Sagia Services in which the text was found.
     */
    Set<SagiaServiceModel> searchSagiaServices(String text);

    /**
     * Get the Sagia Service by code.
     * @param code - The code for which the sagia service is retrieved.
     * @return - The sagia service model with that respective code.
     */
    SagiaServiceModel getSagiaServiceByCode(String code);

    /**
     * Get all the services.
     * @return - A collection with all services.
     */
    Set<SagiaServiceModel> getAllServices();

    /**
     * Collects all Sagia services and maps each service code to its corresponding instance.
     *
     * @return the map of service codes to Sagia services
     */
    Map<String, SagiaServiceModel> getAllServicesByCodes();

    Set<SagiaCategoryModel> getSagiaCategoriesByLabel(String categoryLabel);
}
