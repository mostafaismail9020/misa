package com.sap.ibso.eservices.core.sagia.dao.password;

import com.sap.ibso.eservices.core.model.SagiaPasswordRuleConfigModel;

import java.util.List;

public interface SagiaPasswordRuleConfigDAO {

    List<SagiaPasswordRuleConfigModel> get();

    SagiaPasswordRuleConfigModel getRule();
}
