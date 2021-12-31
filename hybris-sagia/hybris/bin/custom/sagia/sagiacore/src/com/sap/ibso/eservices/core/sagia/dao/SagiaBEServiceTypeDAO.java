package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaBEServiceTypeModel;

import java.util.List;

public interface SagiaBEServiceTypeDAO {

    List<SagiaBEServiceTypeModel> getAllSagiaBEServiceTypes();

    String getTransTypeForCode(String code);
}
