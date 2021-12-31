package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLicenseTypeDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaLicenseTypeService;

import java.util.List;

public class DefaultSagiaLicenseTypeService implements SagiaLicenseTypeService {

    private SagiaLicenseTypeDAO sagiaLicenseTypeDAO;

    @Override
    public List<SagiaLicenseTypeModel> getAllLicenseTypes() {
        return getSagiaLicenseTypeDAO().getAllLicenseTypes();
    }

    @Override
    public SagiaLicenseTypeModel getLicenseTypeForCode(String code) {
        return getSagiaLicenseTypeDAO().getLicenseTypeForCode(code);
    }


    public void setSagiaLicenseTypeDAO(SagiaLicenseTypeDAO sagiaLicenseTypeDAO) {
        this.sagiaLicenseTypeDAO = sagiaLicenseTypeDAO;
    }

    public SagiaLicenseTypeDAO getSagiaLicenseTypeDAO() {
        return sagiaLicenseTypeDAO;
    }
}

