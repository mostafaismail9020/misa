package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import com.sap.ibso.eservices.sagiaservices.services.license.info.LicenseInformationService;
import org.springframework.beans.factory.annotation.Required;

/**
 * DefaultSagiaDashboardWithoutLicenseFacade
 */
public class DefaultSagiaDashboardWithoutLicenseFacade implements SagiaDashboardWithoutLicenseFacade {
    private LicenseInformationService licenseInformationService;

    @Override
    public boolean hasLicense() {
        return licenseInformationService.hasLicense();
    }

    @Override
    public boolean hasAwaitingPayment() {
        return licenseInformationService.hasAwaitingPayment();
    }

    /**
     * @param licenseInformationService
     */
    @Required
    public void setLicenseInformationService(LicenseInformationService licenseInformationService) {
        this.licenseInformationService = licenseInformationService;
    }
    
    @Override
    public void evictApplicationStatus()
    {
    	licenseInformationService.evictApplicationStatus();
    }
}
