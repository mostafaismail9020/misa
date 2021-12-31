package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.SagiaSectorData;

import java.util.List;

/**
 * SagiaSectorFacade
 */
public interface SagiaSectorFacade {
    /**
     * Retrieves Sectors List
     * @return List of SagiaSectorData
     */
    List<SagiaSectorData> getSectorsList();
}
