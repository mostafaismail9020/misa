package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;

import java.util.List;

public interface SagiaCategoryDAO {
    List<SagiaCategoryModel> getAllCategories();
}
