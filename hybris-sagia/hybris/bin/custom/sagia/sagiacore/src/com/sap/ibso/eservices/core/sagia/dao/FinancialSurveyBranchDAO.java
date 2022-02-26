package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.FinancialSurveyBranchModel;

import java.util.List;

public interface FinancialSurveyBranchDAO {


    List<FinancialSurveyBranchModel> findFinancialSurveyBranchForFinancialSurvey(String pk);
}
