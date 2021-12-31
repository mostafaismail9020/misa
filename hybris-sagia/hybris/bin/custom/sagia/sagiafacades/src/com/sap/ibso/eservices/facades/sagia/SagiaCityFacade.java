package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.SagiaCityData;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;

import java.util.List;

/**
 * SagiaCityFacade
 */
public interface SagiaCityFacade {

    List<DropdownValue> getAllCities();

    DropdownValue getCityForCode(String code);

    List<DropdownValue> getAllCitiesForRegionCode(String code);
}



