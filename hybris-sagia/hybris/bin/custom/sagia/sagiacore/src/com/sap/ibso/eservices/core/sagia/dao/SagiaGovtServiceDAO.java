package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import java.util.List;
import java.util.Map;

public interface SagiaGovtServiceDAO {
    Map<SagiaCategoryModel, List<SagiaServiceModel>> getGovtCategoriesServices();
}
