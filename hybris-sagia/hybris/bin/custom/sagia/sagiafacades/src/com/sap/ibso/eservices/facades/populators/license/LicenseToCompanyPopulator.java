package com.sap.ibso.eservices.facades.populators.license;

import com.sap.ibso.eservices.core.sagia.services.SagiaCurrencyService;
import com.sap.ibso.eservices.facades.data.ProfileCompanyData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseInfoData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class LicenseToCompanyPopulator implements Populator<LicenseInfoData,ProfileCompanyData> {

    private SagiaCurrencyService sagiaCurrencyService;

    @Override
    public void populate(LicenseInfoData licenseInfo, ProfileCompanyData companyData) {
        companyData.setEntityId(licenseInfo.getBpID());
        companyData.setClassLevel(licenseInfo.getLicClass());
        companyData.setISICSection(licenseInfo.getLicIsicSection());
        companyData.setEntityStatus(licenseInfo.getLicStDesc());

        companyData.setCapital(licenseInfo.getLicCapital());
        companyData.setCapitalFormatted(sagiaCurrencyService.getValueWithCurrency(licenseInfo.getLicCapital()));
    }

    /**
     * @return
     */
    public SagiaCurrencyService getSagiaCurrencyService()
    {
        return sagiaCurrencyService;
    }

    /**
     * @param sagiaCurrencyService
     */
    public void setSagiaCurrencyService(final SagiaCurrencyService sagiaCurrencyService)
    {
        this.sagiaCurrencyService = sagiaCurrencyService;
    }
}
