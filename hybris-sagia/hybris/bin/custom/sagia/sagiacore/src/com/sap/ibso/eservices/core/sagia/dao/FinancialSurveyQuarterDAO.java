package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.FinancialSurveyQuarterModel;

import java.util.List;

public interface FinancialSurveyQuarterDAO {

	List<FinancialSurveyQuarterModel> findFinancialSurveyQuarters();
	FinancialSurveyQuarterModel findFinancialSurveyQuarterByCode(String quarterCode);

}
