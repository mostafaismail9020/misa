package com.sap.ibso.eservices.sagiaservices.converters.zqeemah;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.LicenseTypeData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class LicenseTypeDataPopulator extends ODataPopulator<LicenseTypeData> {
    @Override
    public void populate(ODataModel model, LicenseTypeData licenseTypeData) throws ConversionException {
        super.populate(model, licenseTypeData);
    }
}
