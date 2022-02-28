package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import java.util.List;
import java.util.Set;

public interface SagiaServiceDAO {

    List<SagiaServiceModel> getAllServices();

    SagiaServiceModel getServiceForCode(String code);

    Set<SagiaServiceModel> searchSagiaServices(String text);

    List<SagiaCategoryModel> getSagiaCategoriesByLabel(String categoryLabel);

}
