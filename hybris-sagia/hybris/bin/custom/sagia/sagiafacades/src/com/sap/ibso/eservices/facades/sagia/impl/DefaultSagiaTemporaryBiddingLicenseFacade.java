package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicense;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseCountry;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseGovernmentEntity;
import com.sap.ibso.eservices.facades.populators.TemporaryBiddingLicenseCountryPopulator;
import com.sap.ibso.eservices.facades.populators.TemporaryBiddingLicenseGovernmentEntityPopulator;
import com.sap.ibso.eservices.facades.populators.TemporaryBiddingLicenseReversePopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaTemporaryBiddingLicenseFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseCountryPrefixData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseListItemData;
import com.sap.ibso.eservices.sagiaservices.services.impl.TemporaryBiddingLicenseCountryPrefixService;
import com.sap.ibso.eservices.sagiaservices.services.impl.TemporaryBiddingLicenseListItemService;
import com.sap.ibso.eservices.sagiaservices.services.impl.TemporaryBiddingLicenseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by i335541 on 2/12/18.
 */
public class DefaultSagiaTemporaryBiddingLicenseFacade implements SagiaTemporaryBiddingLicenseFacade {
    @Autowired
    private TemporaryBiddingLicenseReversePopulator temporaryBiddingLicenseReversePopulator;

    @Autowired
    private TemporaryBiddingLicenseCountryPopulator temporaryBiddingLicenseCountryPopulator;

    @Autowired
    private TemporaryBiddingLicenseGovernmentEntityPopulator temporaryBiddingLicenseGovernmentEntityPopulator;

    @Autowired
    private TemporaryBiddingLicenseService temporaryBiddingLicenseService;

    @Autowired
    private TemporaryBiddingLicenseListItemService temporaryBiddingLicenseListItemService;

    @Autowired
    private TemporaryBiddingLicenseCountryPrefixService temporaryBiddingLicenseCountryPrefixService;

    @Override
    public TemporaryBiddingLicenseData createTemporaryBiddingLicense(TemporaryBiddingLicense temporaryBiddingLicense) {
        TemporaryBiddingLicenseData temporaryBiddingLicenseData = new TemporaryBiddingLicenseData();
        temporaryBiddingLicenseReversePopulator.populate(temporaryBiddingLicense, temporaryBiddingLicenseData);
        return temporaryBiddingLicenseService.create(temporaryBiddingLicenseData);
    }

    @Override
    public List<TemporaryBiddingLicenseCountry> getCountries(String language) {
        List<TemporaryBiddingLicenseCountry> countries = new ArrayList<>();
        Collection<TemporaryBiddingLicenseListItemData> listItems = temporaryBiddingLicenseListItemService.getListItems("C", language);
        for (TemporaryBiddingLicenseListItemData listItem : listItems) {
            TemporaryBiddingLicenseCountry country = new TemporaryBiddingLicenseCountry();
            temporaryBiddingLicenseCountryPopulator.populate(listItem, country);
            countries.add(country);
        }
        return countries;
    }

    @Override
    public List<TemporaryBiddingLicenseGovernmentEntity> getGovernmentEntities(String language) {
        List<TemporaryBiddingLicenseGovernmentEntity> governmentEntities = new ArrayList<>();
        Collection<TemporaryBiddingLicenseListItemData> listItems = temporaryBiddingLicenseListItemService.getListItems("G", language);
        for (TemporaryBiddingLicenseListItemData listItem : listItems) {
            TemporaryBiddingLicenseGovernmentEntity governmentEntity = new TemporaryBiddingLicenseGovernmentEntity();
            temporaryBiddingLicenseGovernmentEntityPopulator.populate(listItem, governmentEntity);
            governmentEntities.add(governmentEntity);
        }
        return governmentEntities;
    }

    @Override
    public String getCountryPrefix(String countryCode) {
        TemporaryBiddingLicenseCountryPrefixData countryPrefixData = temporaryBiddingLicenseCountryPrefixService.getCountryPrefix(countryCode);
        return countryPrefixData.getTelNo();
    }

    /**
     * @return
     */
    public TemporaryBiddingLicenseListItemService getTemporaryBiddingLicenseListItemService() {
        return temporaryBiddingLicenseListItemService;
    }

    /**
     * @param temporaryBiddingLicenseListItemService
     */
    public void setTemporaryBiddingLicenseListItemService(TemporaryBiddingLicenseListItemService temporaryBiddingLicenseListItemService) {
        this.temporaryBiddingLicenseListItemService = temporaryBiddingLicenseListItemService;
    }

    /**
     * @return
     */
    public TemporaryBiddingLicenseService getTemporaryBiddingLicenseService() {
        return temporaryBiddingLicenseService;
    }

    /**
     * @param temporaryBiddingLicenseService
     */
    public void setTemporaryBiddingLicenseService(TemporaryBiddingLicenseService temporaryBiddingLicenseService) {
        this.temporaryBiddingLicenseService = temporaryBiddingLicenseService;
    }

    /**
     * @return
     */
    public TemporaryBiddingLicenseGovernmentEntityPopulator getTemporaryBiddingLicenseGovernmentEntityPopulator() {

        return temporaryBiddingLicenseGovernmentEntityPopulator;
    }

    /**
     * @param temporaryBiddingLicenseGovernmentEntityPopulator
     */
    public void setTemporaryBiddingLicenseGovernmentEntityPopulator(TemporaryBiddingLicenseGovernmentEntityPopulator temporaryBiddingLicenseGovernmentEntityPopulator) {
        this.temporaryBiddingLicenseGovernmentEntityPopulator = temporaryBiddingLicenseGovernmentEntityPopulator;
    }

    /**
     * @return
     */
    public TemporaryBiddingLicenseCountryPopulator getTemporaryBiddingLicenseCountryPopulator() {
        return temporaryBiddingLicenseCountryPopulator;
    }

    /**
     * @param temporaryBiddingLicenseCountryPopulator
     */
    public void setTemporaryBiddingLicenseCountryPopulator(TemporaryBiddingLicenseCountryPopulator temporaryBiddingLicenseCountryPopulator) {
        this.temporaryBiddingLicenseCountryPopulator = temporaryBiddingLicenseCountryPopulator;
    }

    /**
     * @return
     */
    public TemporaryBiddingLicenseReversePopulator getTemporaryBiddingLicenseReversePopulator() {
        return temporaryBiddingLicenseReversePopulator;
    }

    /**
     * @param temporaryBiddingLicenseReversePopulator
     */
    public void setTemporaryBiddingLicenseReversePopulator(TemporaryBiddingLicenseReversePopulator temporaryBiddingLicenseReversePopulator) {
        this.temporaryBiddingLicenseReversePopulator = temporaryBiddingLicenseReversePopulator;
    }
}
