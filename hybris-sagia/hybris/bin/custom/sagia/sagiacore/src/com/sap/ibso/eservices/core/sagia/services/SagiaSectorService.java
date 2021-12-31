package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaSectorModel;

import java.util.List;

/**
 * Provides access to the Sector Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaSectorService {

    /**
     * Get all the sectors.
     * @return - A collection containing all the sectors.
     */
    List<SagiaSectorModel> getSectors();

    /**
     * Get a sector by its code.
     * @param code - the code for which the sector is retrieved.
     * @return - Sagia Sector Model having that specific code.
     */
    SagiaSectorModel getSectorForCode(String code);
    /**
     * Get a sector by its Sector Code.
     * @param sectorCode - the code for which the sector is retrieved.
     * @return - Sagia Sector Model having that specific sectorCode.
     */
    SagiaSectorModel getSectorForSectorCode(String sectorCode);
}
