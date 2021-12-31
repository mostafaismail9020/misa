package com.sap.ibso.eservices.core.sagia.dao.password;

import com.sap.ibso.eservices.core.model.SagiaPasswordErrorMessageConfigModel;

import java.util.List;

public interface SagiaPasswordErrorMessageConfigDAO {

    List<SagiaPasswordErrorMessageConfigModel> get();

    SagiaPasswordErrorMessageConfigModel getMessageByLang(String lang);
}
