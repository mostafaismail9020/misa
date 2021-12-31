package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaBEServiceTypeModel;

import java.util.List;

/**
 * Provides access to the BE Trans Type Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaBETransTypeService {

    /**
     * Get all the Sagia BE Service Type Entities
     * @return - all entities
     */
    List<SagiaBEServiceTypeModel> getAllSagiaBEServiceTypes();

    /**
     * Get the Trans Type by its code
     * @param code - The code for which the entity is retrieved.
     * @return - Trans Type for that specific code.
     */
    String getTransTypeForCode(String code);
}
