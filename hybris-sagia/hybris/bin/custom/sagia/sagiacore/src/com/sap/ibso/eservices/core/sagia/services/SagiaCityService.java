package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaCityModel;
import com.sap.ibso.eservices.core.model.SagiaRegionModel;

import java.util.List;

/**
 * Provides access to the City Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaCityService {

    /**
     * Get all the cities
     * @return - A list containing all the cities.
     */
    List<SagiaCityModel> getAllCities();

    /**
     * Get a city by its code.
     * @param code - The code for which the city is retrieved.
     * @return - The city with that specific code.
     */
    SagiaCityModel getCityForCode(String code);
    
    /**
     * Get all the cities by a region code
     * @return - A list containing all cities found.
     */
    List<SagiaCityModel> getAllCitiesForRegionCode(String code);
    
}
