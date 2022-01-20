package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.FinancialSurveyBranchModel;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyBranchDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultFinancialSurveyBranchDAO extends DefaultGenericDao<FinancialSurveyBranchModel> implements FinancialSurveyBranchDAO {

    private FlexibleSearchService flexibleSearchService;


    @Override
    public List<FinancialSurveyBranchModel> findFinancialSurveyBranchForFinancialSurvey(String financialSurveyPk) {

        validateParameterNotNull(financialSurveyPk, "financialSurveyPk cannot be null");
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(FinancialSurveyBranchModel.FINANCIALSURVEY,financialSurveyPk);
        List<FinancialSurveyBranchModel> financialSurveyBranchlList = find(parameters);

        return financialSurveyBranchlList;


    }







    public DefaultFinancialSurveyBranchDAO(String typecode) {
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
