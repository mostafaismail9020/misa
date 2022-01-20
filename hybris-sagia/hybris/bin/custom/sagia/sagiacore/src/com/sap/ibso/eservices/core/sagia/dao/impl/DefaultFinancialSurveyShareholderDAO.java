package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.FinancialSurveyShareholderModel;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyShareholderDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultFinancialSurveyShareholderDAO extends DefaultGenericDao<FinancialSurveyShareholderModel> implements FinancialSurveyShareholderDAO {

    private FlexibleSearchService flexibleSearchService;



    @Override
    public List<FinancialSurveyShareholderModel> findFinancialSurveyShareholderForFinancialSurvey(String financialSurveyPk) {
        validateParameterNotNull(financialSurveyPk, "financialSurveyPk cannot be null");
     ///   final Map<String, String> parameters = new HashMap<>();
    //    parameters.put(FinancialSurveyShareholderModel.FINANCIALSURVEY,financialSurveyPk);
     //   List<FinancialSurveyShareholderModel> financialSurveyShareholderlList = find(parameters);
        return null;
    }








    public DefaultFinancialSurveyShareholderDAO(String typecode) {
        super(typecode);
    }


    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * @param flexibleSearchService the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }


}
