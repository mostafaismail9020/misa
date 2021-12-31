package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaLicenseTypeService;
import com.sap.ibso.eservices.facades.data.zqeemah.LicenseType;
import com.sap.ibso.eservices.facades.populators.SagiaLicenseTypePopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseTypeFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultSagiaLicenseTypeFacade
 */
public class DefaultSagiaLicenseTypeFacade implements SagiaLicenseTypeFacade {

    private SagiaLicenseTypeService sagiaLicenseTypeService;
    private SagiaLicenseTypePopulator sagiaLicenseTypePopulator;


    @Override
    public Collection<LicenseType> getAllLicenseTypes() {
        final List<SagiaLicenseTypeModel> licenseTypes = getSagiaLicenseTypeService().getAllLicenseTypes();

        Collection<LicenseType> licenseTypesList = new ArrayList<>();
        for (SagiaLicenseTypeModel sagiaLicenseTypeModel : licenseTypes) {
            LicenseType data = new LicenseType();
            getSagiaLicenseTypePopulator().populate(sagiaLicenseTypeModel, data);
            licenseTypesList.add(data);
        }

        return licenseTypesList;
    }

    @Override
    public LicenseType getLicenseTypeForCode(String code) {
        SagiaLicenseTypeModel sagiaLicenseTypeModel = getSagiaLicenseTypeService().getLicenseTypeForCode(code);
        if (sagiaLicenseTypeModel != null) {
            LicenseType data = new LicenseType();
            getSagiaLicenseTypePopulator().populate(sagiaLicenseTypeModel, data);
            return data;
        }
        return null;
    }

    public SagiaLicenseTypeService getSagiaLicenseTypeService() {
        return sagiaLicenseTypeService;
    }



    public void setSagiaLicenseTypeService(SagiaLicenseTypeService sagiaLicenseTypeService) {
        this.sagiaLicenseTypeService = sagiaLicenseTypeService;
    }

    public SagiaLicenseTypePopulator getSagiaLicenseTypePopulator() {
        return sagiaLicenseTypePopulator;
    }

    public void setSagiaLicenseTypePopulator(SagiaLicenseTypePopulator sagiaLicenseTypePopulator) {
        this.sagiaLicenseTypePopulator = sagiaLicenseTypePopulator;
    }
}
