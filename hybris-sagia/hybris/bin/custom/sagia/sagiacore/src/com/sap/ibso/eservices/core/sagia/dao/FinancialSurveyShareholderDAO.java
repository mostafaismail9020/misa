package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.FinancialSurveyShareholderModel;

import java.util.List;

public interface FinancialSurveyShareholderDAO {


    List<FinancialSurveyShareholderModel> findFinancialSurveyShareholderForFinancialSurvey(String pk);
}
