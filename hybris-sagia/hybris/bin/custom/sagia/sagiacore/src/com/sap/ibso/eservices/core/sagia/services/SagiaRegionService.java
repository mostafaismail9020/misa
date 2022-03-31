package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.core.model.SagiaRhqRegionModel;

import java.util.List;

/**
 * Provides access to the Region Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaRegionService {

    /**
     * Get all the regions
     * @return - A list containing all the regions.
     */
    List<SagiaRegionModel> getAllRegions();

    /**
     * Get a region by its code.
     * @param code - The code for which the region is retrieved.
     * @return - The region with that specific code.
     */
    SagiaRegionModel getRegionForCode(String code);
    
    /**
     * Get all the regions by a country code
     * @return - A list containing all regions found.
     */
    List<SagiaRegionModel> getAllRegionsForCountryCode(String code);
    
    /**
     * Get all the regions
     * @return - A list containing all the regions.
     */
    List<SagiaRhqRegionModel> getRhqRegionsList();

    String getRhqRegionForCode(String code);

}
