package com.sap.ibso.eservices.facades.populators.license;

import com.sap.ibso.eservices.core.sagia.services.SagiaCurrencyService;
import com.sap.ibso.eservices.facades.data.ProfileCompanyData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ClassificData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class ClassificToCompanyPopulator implements Populator<ClassificData,ProfileCompanyData> {

    private SagiaCurrencyService sagiaCurrencyService;

    @Override
    public void populate(ClassificData classific, ProfileCompanyData companyData) {
        companyData.setYearlyFees(classific.getFees().toString());
        companyData.setYearlyFeesFormatted(sagiaCurrencyService.getValueWithCurrency(classific.getFees()));
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
