package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaLegalStatusModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLegalStatusDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLicenseTypeDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaLegalStatusService;

import java.util.List;

public class DefaultSagiaLegalStatusService implements SagiaLegalStatusService {

    private transient SagiaLegalStatusDAO sagiaLegalStatusDAO;

    @Override
    public List<SagiaLegalStatusModel> getAllLegalStatus() {
        return getSagiaLegalStatusDAO().getAllLegalStatus();
    }

    @Override
    public  SagiaLegalStatusModel getLegalStatusForCode(String code) {
        return getSagiaLegalStatusDAO().getLegalStatusForCode(code);
    }


    public void setSagiaLegalStatusDAO(SagiaLegalStatusDAO sagiaLegalStatusDAO) {
        this.sagiaLegalStatusDAO = sagiaLegalStatusDAO;
    }

    public SagiaLegalStatusDAO getSagiaLegalStatusDAO() {
        return sagiaLegalStatusDAO;
    }
}

