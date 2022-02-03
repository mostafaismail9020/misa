package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaSubsidiaryModel;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveySubsidiaryDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultFinancialSurveySubsidiaryDAO extends DefaultGenericDao<SagiaSubsidiaryModel> implements FinancialSurveySubsidiaryDAO {

    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<SagiaSubsidiaryModel> findFinancialSurveySubsidiaryForFinancialSurvey(String subsidiaryPk) {
        validateParameterNotNull(subsidiaryPk, "SubsidiaryPk cannot be null");
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(SagiaSubsidiaryModel.FINANCIALSURVEY,subsidiaryPk);
        List<SagiaSubsidiaryModel> financialSurveySubsidiarylList = find(parameters);

        return financialSurveySubsidiarylList;
    }



    public DefaultFinancialSurveySubsidiaryDAO(String typecode) {
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
