package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaCountryModel;

import java.util.List;

public interface SagiaCountryDAO {

    List<SagiaCountryModel> getAllCountries();

    SagiaCountryModel getCountryForCode(String code);
    SagiaCountryModel getCountryForPhonePrefix(String phonePrefix);
    SagiaCountryModel getCountryCodeForName(String code);
    List<SagiaCountryModel> getShareHolderCheckCountries();
}
