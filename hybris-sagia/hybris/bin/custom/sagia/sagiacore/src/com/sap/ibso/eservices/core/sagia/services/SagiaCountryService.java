package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaCountryModel;

import java.util.List;

/**
 * Provides access to the Country Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaCountryService {

    /**
     * Get all the countries
     * @return - A list containing all the countries.
     */
    List<SagiaCountryModel> getCountries();

    /**
     * Get a country by its code.
     * @param code - The code for which the country is retrieved.
     * @return - The country with that specific code.
     */
    SagiaCountryModel getCountryForCode(String code);
    
    /**
     * Get all the Share Holder Check Countries
     * @return - A list containing all the Share Holder Check countries.
     */
    List<SagiaCountryModel> getShareHolderCheckCountries();
    
    /**
     * Get a country by its Phone Prefix.
     * @param code - The Phone Prefix for which the country is retrieved.
     * @return - The country with that specific Phone Prefix.
     */
    SagiaCountryModel getCountryForPhonePrefix(String phonePrefix);
    
}
