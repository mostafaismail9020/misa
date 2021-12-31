package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpCountryData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * SpecialServiceCountryService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SpecialServiceCountryService extends AbstractSagiaService<SpCountryData> {
    /**
     * retrieves CountryCollection
     * @param language language
     * @return Collection of SpCountryData
     */
    public final Collection<SpCountryData> getCountryCollection(String language) {
        return super.getCollection(
                SpCountryData.class,
                "LANGUAGE",
                "'" + language + "'");
    }
}
