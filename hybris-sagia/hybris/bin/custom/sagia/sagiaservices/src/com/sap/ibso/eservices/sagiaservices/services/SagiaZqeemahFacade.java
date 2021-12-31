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
package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.facades.data.BasicCompanyData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoDictionaryData;
import com.sap.ibso.eservices.sagiaservices.services.impl.OrgInfoDictionaryService;
import com.sap.ibso.eservices.sagiaservices.services.impl.OrgInfoService;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.Collection;

/**
 * SagiaZqeemahFacade
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaZqeemahFacade {
    private OrgInfoService orgInfoService;
    private OrgInfoDictionaryService orgInfoDictionaryService;
    private I18NService i18NService;


    /**
     * retrieves OrgInfoData
     * @param code code
     * @return OrgInfoData
     */
    public OrgInfoData getOrgInfoData(String code) {
        return orgInfoService.get();
    }

    /**
     * retrieves LegalStatusList
     * @return Collection of OrgInfoDictionaryData
     */
    public Collection<OrgInfoDictionaryData> getLegalStatusList() {
        return orgInfoDictionaryService.getLegalStatus(i18NService.getCurrentLocale().getLanguage());
    }

    /**
     * retrieves CitiesList
     * @param region region
     * @return Collection of OrgInfoDictionaryData
     */
    public Collection<OrgInfoDictionaryData> getCitiesList(final String region) {
        return orgInfoDictionaryService.getCities(i18NService.getCurrentLocale().getLanguage(), region);
    }

    /**
     * retrieves RegionsList
     * @return Collection of OrgInfoDictionaryData
     */
    public Collection<OrgInfoDictionaryData> getRegionsList() {
        return orgInfoDictionaryService.getRegions(i18NService.getCurrentLocale().getLanguage());
    }

    /**
     * updates CompanyBasicData
     * @param basicCompanyFormData basicCompanyFormData
     */
    public void updateCompanyBasicData(final BasicCompanyData basicCompanyFormData) {
        orgInfoService.updateOrgInfoData(basicCompanyFormData);
    }

    /**
     * @return OrgInfoService
     */
    public OrgInfoService getOrgInfoService() {
        return orgInfoService;
    }

    /**
     * @param orgInfoService orgInfoService
     */
    public void setOrgInfoService(final OrgInfoService orgInfoService) {
        this.orgInfoService = orgInfoService;
    }

    /**
     * @return OrgInfoDictionaryService
     */
    public OrgInfoDictionaryService getOrgInfoDictionaryService() {
        return orgInfoDictionaryService;
    }

    /**
     * @param orgInfoDictionaryService orgInfoDictionaryService
     */
    public void setOrgInfoDictionaryService(final OrgInfoDictionaryService orgInfoDictionaryService) {
        this.orgInfoDictionaryService = orgInfoDictionaryService;
    }

    /**
     * @return I18NService
     */
    public I18NService getI18NService() {
        return i18NService;
    }

    /**
     * @param i18NService i18NService
     */
    public void setI18NService(final I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
