package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaCityModel;
import com.sap.ibso.eservices.core.model.SagiaRegionModel;

import java.util.List;

public interface SagiaCityDAO {

    List<SagiaCityModel> getAllCities();

    SagiaCityModel getCityForCode(String code);

    SagiaCityModel getCityForName(String name);

    List<SagiaCityModel> getAllCitiesForRegionCode(String code);

}
