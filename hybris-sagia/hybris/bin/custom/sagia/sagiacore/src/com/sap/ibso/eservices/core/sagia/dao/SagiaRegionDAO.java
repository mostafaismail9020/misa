package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.core.model.SagiaRhqRegionModel;

import java.util.List;

public interface SagiaRegionDAO {

    List<SagiaRegionModel> getAllRegions();

    SagiaRegionModel getRegionForCode(String code);

    List<SagiaRegionModel> getAllRegionsForCountryCode(String code);
    SagiaRhqRegionModel getRhqRegionForCode(String code);
    SagiaRhqRegionModel getRhqRegionCodeForName(String name);
    /**
     * Get all the regions
     * @return - A list containing all the regions.
     */
    List<SagiaRhqRegionModel> getRhqRegionsList();

}
