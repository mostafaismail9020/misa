package com.sap.ibso.eservices.facades.sagia;

import java.util.List;

import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;

/**
 * SagiaRegionFacade
 */
public interface SagiaRegionFacade {

    List<DropdownValue> getAllRegions();

    DropdownValue getRegionForCode(String code);

    List<DropdownValue> getAllRegionsForCountryCode(String code);

	List<DropdownValue> getRhqRegionsList();

}



