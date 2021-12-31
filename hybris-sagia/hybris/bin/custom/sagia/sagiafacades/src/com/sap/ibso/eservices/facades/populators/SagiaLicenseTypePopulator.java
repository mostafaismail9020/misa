package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;
import com.sap.ibso.eservices.facades.data.zqeemah.LicenseType;
import de.hybris.platform.converters.Populator;


public class SagiaLicenseTypePopulator implements Populator<SagiaLicenseTypeModel, LicenseType> {

    @Override
    public void populate(SagiaLicenseTypeModel source, LicenseType target)  {

        target.setBpId("");
        target.setLicenseType(source.getCode());
        target.setLicenseTypeText(source.getName());
    }


}
