package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.FinancialSurveyQuarterModel;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyQuarterDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultFinancialSurveyQuarterDAO extends DefaultGenericDao<FinancialSurveyQuarterModel> implements FinancialSurveyQuarterDAO {

    private FlexibleSearchService flexibleSearchService;


    public DefaultFinancialSurveyQuarterDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<FinancialSurveyQuarterModel> findFinancialSurveyQuarters() {
        //final Map<String, String> parameters = new HashMap<>();
        List<FinancialSurveyQuarterModel> financialSurveyQuarterlList = find();
        return financialSurveyQuarterlList;
    }

    @Override
    public FinancialSurveyQuarterModel findFinancialSurveyQuarterByCode(String quarterCode) {

        validateParameterNotNull(quarterCode, "quarter code cannot be null");
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(FinancialSurveyQuarterModel.CODE, quarterCode);
        List<FinancialSurveyQuarterModel> financialSurveyQuarterlList = find(parameters);

        if(financialSurveyQuarterlList.size()< 1 ){
            return null;
        }
        return financialSurveyQuarterlList.get(0);
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
