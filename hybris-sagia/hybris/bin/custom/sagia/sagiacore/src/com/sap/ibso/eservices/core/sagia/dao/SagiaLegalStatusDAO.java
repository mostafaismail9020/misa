package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaLegalStatusModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;

import java.util.List;

public interface SagiaLegalStatusDAO {

    List<SagiaLegalStatusModel> getAllLegalStatus();

    SagiaLegalStatusModel getLegalStatusForCode(String code);
    
}
