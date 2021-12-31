package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;

public interface SagiaLicenseTypeDAO {

    List<SagiaLicenseTypeModel> getAllLicenseTypes();

    SagiaLicenseTypeModel getLicenseTypeForCode(String code);
    
}
