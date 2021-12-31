/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoDictionaryData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * OrgInfoDictionaryService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class OrgInfoDictionaryService extends AbstractSagiaService<OrgInfoDictionaryData> {
    private static final String LEGAL_STATUS_CODE = "'LS'";
    private static final String REGION_CODE = "'RG'";
    private static final String CITY_CODE = "'CT'";
    private static final String COUNTRY_CODE = "'CY'";

    private static final String LANGUAGE_KEY = "lvkey";
    private static final String LEVEL_FLAG = "lv_flag";
    private static final String LEVEL_REGION = "lv_region";


    /**
     * retrieves LegalStatus
     * @param language language
     * @return Collection of OrgInfoDictionaryData
     */
    public Collection<OrgInfoDictionaryData> getLegalStatus(final String language) {
        return getCollection(OrgInfoDictionaryData.class, LANGUAGE_KEY, "'" + language.toUpperCase() + "'", LEVEL_FLAG, LEGAL_STATUS_CODE, LEVEL_REGION, "''");
    }

    /**
     * retrieves Cities
     * @param language language
     * @param region region
     * @return Collection of OrgInfoDictionaryData
     */
    public Collection<OrgInfoDictionaryData> getCities(final String language, final String region) {
        return getCollection(OrgInfoDictionaryData.class, LANGUAGE_KEY, "'" + language.toUpperCase() + "'", LEVEL_FLAG, CITY_CODE, LEVEL_REGION, "'" + region + "'");
    }

    /**
     * retrieves Regions
     * @param language language
     * @return Collection of OrgInfoDictionaryData
     */
    public Collection<OrgInfoDictionaryData> getRegions(final String language) {
        return getCollection(OrgInfoDictionaryData.class, LANGUAGE_KEY, "'" + language.toUpperCase() + "'", LEVEL_FLAG, REGION_CODE, LEVEL_REGION, "''");
    }

    /**
     * retrieves Countries
     * @param language language
     * @return Collection of OrgInfoDictionaryData
     */
    public Collection<OrgInfoDictionaryData> getCountries(final String language) {
        return getCollection(OrgInfoDictionaryData.class, LANGUAGE_KEY, "'" + language.toUpperCase() + "'", LEVEL_FLAG, COUNTRY_CODE, LEVEL_REGION, "''");
    }
}
