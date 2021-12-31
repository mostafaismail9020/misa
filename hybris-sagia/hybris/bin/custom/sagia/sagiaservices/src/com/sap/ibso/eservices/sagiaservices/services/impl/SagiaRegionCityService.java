package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.facades.data.RegionCityData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * SagiaRegionCityService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaRegionCityService extends AbstractSagiaService<RegionCityData> {
    private static final String FILTER_BY_LANGUAGE = "Language eq '";
    private static final String FILTER_BY_REGION_CODE = " and RegionCode eq'";
    private I18NService i18NService;

    /**
     * Get all regions.
     * @return all regions.
     */
    public final Collection<RegionCityData> getCollection() {
        String upperCaseLanguage = i18NService.getCurrentLocale().getLanguage().toUpperCase();
        return super.getCollection(RegionCityData.class, REQUEST_PARAMETER_FILTER, FILTER_BY_LANGUAGE + upperCaseLanguage + "'");
    }

    /**
     * Get all cities for a given region id.
     * @param regionId regionId
     * @return all cities for a given region id.
     */
    public Collection<RegionCityData> getCities(String regionId) {
        String upperCaseLanguage = i18NService.getCurrentLocale().getLanguage().toUpperCase();
        return super.getCollection(RegionCityData.class, REQUEST_PARAMETER_FILTER, FILTER_BY_LANGUAGE + upperCaseLanguage + "'" + FILTER_BY_REGION_CODE + regionId + "'");
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
