package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.SagiaCountryData;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;

import java.util.List;

/**
 * SagiaCountryFacade
 */
public interface SagiaCountryFacade {
    /**
     * @return List of SagiaCountryData
     */
    List<SagiaCountryData> getCountriesList();

    /**
     * @return List of SagiaCountryData
     */
    List<DropdownValue> getAllCountries();

    /**
     * @return List of SagiaCountryData
     */
    List<SagiaCountryData> getShareHolderCheckCountries();
}
