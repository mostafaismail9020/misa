package com.sap.ibso.eservices.facades.populators.license;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.LicenseData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class LicensePopulator implements Populator<HomeHDRData,LicenseData> {

    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(HomeHDRData homeHDRData, LicenseData licenseData) {
        if(homeHDRData != null) {
            licenseData.setDuration(homeHDRData.getClassificData().getDuration());
            licenseData.setExpiryDate(sagiaFormatProvider.getLocalizedDateData(homeHDRData.getLicenseInfoData().getLicExDate()));
            licenseData.setType(homeHDRData.getLicenseInfoData().getLicTypeDesc());
        }
    }

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
