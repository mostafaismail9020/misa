package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaClassificationModel;

import java.util.List;

public interface SagiaClassificationDAO  {

    List<SagiaClassificationModel> getAllClassifications();

    SagiaClassificationModel getClassificationForCode(Integer code);
}
