package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;

import java.util.List;

public interface SagiaLicenseTypeService {

    List<SagiaLicenseTypeModel> getAllLicenseTypes();

    SagiaLicenseTypeModel getLicenseTypeForCode(String code);

}