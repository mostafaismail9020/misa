package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.LicenseTypeData;
import de.hybris.platform.converters.Populator;
import com.sap.ibso.eservices.facades.data.zqeemah.LicenseType;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class LicenseTypePopulator implements Populator<LicenseTypeData, LicenseType> {
    @Override
    public void populate(LicenseTypeData licenseTypeData, LicenseType licenseType) throws ConversionException {
        licenseType.setBpId(licenseTypeData.getBpid());
        licenseType.setLicenseType(licenseTypeData.getLictype());
        licenseType.setLicenseTypeText(licenseTypeData.getLictypetext());
    }
}
