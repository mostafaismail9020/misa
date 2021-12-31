package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaLegalStatusModel;

import java.util.List;

public interface SagiaLegalStatusService {

    List<SagiaLegalStatusModel> getAllLegalStatus();

    SagiaLegalStatusModel getLegalStatusForCode(String code);

}