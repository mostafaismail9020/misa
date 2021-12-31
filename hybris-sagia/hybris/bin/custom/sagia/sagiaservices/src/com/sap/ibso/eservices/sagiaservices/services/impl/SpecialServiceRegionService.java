package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpRegionData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * SpecialServiceRegionService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SpecialServiceRegionService extends AbstractSagiaService<SpRegionData> {
    /**
     * getRegionCollection
     * @param language language
     * @return Collection of SpRegionData
     */
    public final Collection<SpRegionData> getRegionCollection(String language) {
        return super.getCollection(
                SpRegionData.class,
                "LANGUAGE",
                "'" + language + "'");
    }
}
