package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.FinancialSurveyModel;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyDAO;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultFinancialSurveyDAO extends DefaultGenericDao<FinancialSurveyModel>  implements FinancialSurveyDAO {


    private FlexibleSearchService flexibleSearchService;

    public DefaultFinancialSurveyDAO(String typecode) {
        super(typecode);
    }

    @Override
    public SearchPageData<FinancialSurveyModel> findFinancialSurveysByB2BUnit(PageableData pageableData, String b2bUnit) {

        final Map<String, Object> queryParams = new HashMap<String, Object>(1);
        queryParams.put("b2bUnit", b2bUnit);

        final List<SortQueryData> sortQueries = new ArrayList<>();
        return  null;
    }

    @Override
    public FinancialSurveyModel getFinancialSurveyForQuarter(String userPk, String quarterPk) {

        validateParameterNotNull(userPk, "User PK cannot be null");
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(FinancialSurveyModel.USER, userPk);
        parameters.put(FinancialSurveyModel.QUARTER, quarterPk);
        List<FinancialSurveyModel> financialSurveyModelList = find(parameters);
        if(financialSurveyModelList.size()< 1 ){
            return null;
        }
        return financialSurveyModelList.get(0);
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
