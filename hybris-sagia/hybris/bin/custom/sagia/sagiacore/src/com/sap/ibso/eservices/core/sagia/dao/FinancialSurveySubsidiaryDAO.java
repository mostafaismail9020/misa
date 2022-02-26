package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaSubsidiaryModel;

import java.util.List;

public interface FinancialSurveySubsidiaryDAO {


    List<SagiaSubsidiaryModel> findFinancialSurveySubsidiaryForFinancialSurvey(String pk);
}
