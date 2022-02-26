package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.FinancialSurveyModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

public interface FinancialSurveyDAO {

	SearchPageData<FinancialSurveyModel> findFinancialSurveysByB2BUnit(PageableData pageableData, String b2bUnit);

    FinancialSurveyModel getFinancialSurveyForQuarter(String userPk, String quarter);
}
