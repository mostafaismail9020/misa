package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaConfigurationModel;

import java.util.List;

public interface SagiaConfigurationDAO {
    
    List<SagiaConfigurationModel> get();

    SagiaConfigurationModel get(String key);
}
