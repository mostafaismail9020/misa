package com.sap.ibso.eservices.sagiaservices.services.financialsurvey;

import com.sap.ibso.eservices.core.model.SagiaCompanyProfileModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyModel;
import de.hybris.platform.commercefacades.user.data.FinancialSurvey;

import java.util.List;

/**
 * The interface SagiaFinancialService.
 */
public interface SagiaFinancialSurveyService {

    void saveMessage(String messageContent, String quarterCode);

    void saveFinancialSurvey(FinancialSurvey financialSurveyData);

    FinancialSurveyModel getFinancialSurvey(String quarter);

    List<FinancialSurveyModel> getFinancialSurveys();

    SagiaCompanyProfileModel getCompanyProfile();
}
