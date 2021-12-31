package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.zqeemah.LicenseType;

import java.util.Collection;

/**
 * SagiaLicenseTypeFacade
 */
public interface SagiaLicenseTypeFacade {

    Collection<LicenseType> getAllLicenseTypes();

    LicenseType getLicenseTypeForCode(String code);
}



