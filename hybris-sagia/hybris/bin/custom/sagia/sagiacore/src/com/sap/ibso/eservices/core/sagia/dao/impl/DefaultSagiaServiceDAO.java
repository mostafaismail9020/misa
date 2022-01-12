package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaServiceDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Nonnull;
import java.util.*;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;


public class DefaultSagiaServiceDAO  extends DefaultGenericDao<SagiaServiceModel> implements SagiaServiceDAO {

    public DefaultSagiaServiceDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaServiceModel> getAllServices() {
        return find();
    }

    @Override
    public SagiaServiceModel getServiceForCode(String code) {
        validateParameterNotNull(code, "SagiaService code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaServiceModel.CODE, code);
        List parameterList = find(parameters);
        if (CollectionUtils.isNotEmpty(parameterList)) {
            return (SagiaServiceModel) parameterList.get(0);
        } else {
            return null;
        }
    }


    @Override
    public Set<SagiaServiceModel> searchSagiaServices(String text) {

        validateParameterNotNull(text, "search text must not be null!");
        String[] words = text.split("\\s+");
        FlexibleSearchQuery query = this.createMultipleStringSearch(words);
        return new HashSet(getFlexibleSearchService().search(query).getResult());
    }

    /**
     * return the flexible search query for multiple parameters/words
     * @param words
     * @return
     */
    public FlexibleSearchQuery createMultipleStringSearch(String[] words) {

        String querytext = "SELECT * FROM {" + SagiaServiceModel._TYPECODE + " as s}" +
                " where (UPPER({s.description}) LIKE UPPER(?description) OR " +
                " UPPER({s.name}) LIKE UPPER(?name))";
        Map<String, String> queryParameters = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                String parameter = "%" + words[i] + "%";
                queryParameters.put("name", parameter);
                queryParameters.put("description", parameter);
            } else {
                querytext += "AND (UPPER({s.description}) LIKE UPPER(?description" + i + ") OR" +
                        " UPPER({s.name}) LIKE UPPER(?name" + i + "))";
                String parameter = "%" + words[i] + "%";
                queryParameters.put("name" + i, parameter);
                queryParameters.put("description" + i, parameter);
            }
        }
        FlexibleSearchQuery query = new FlexibleSearchQuery(querytext);
        query.addQueryParameters(queryParameters);
        return query;
    }

    /**
     * Method to fetch sagia categories by category label
     *
     * @param categoryLabel
     * @return
     */
    @Override
    public List<SagiaCategoryModel> getSagiaCategoriesByLabel(@Nonnull final String categoryLabel) {

        Objects.requireNonNull(categoryLabel, "categoryLabel is required");
        FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {"+ SagiaCategoryModel.PK +"} FROM {"+
                SagiaCategoryModel._TYPECODE +"} WHERE {"+ SagiaCategoryModel.LABEL +"} = ?categoryLabel");
        Map<String, String> params = new HashMap<>();
        params.put("categoryLabel", categoryLabel);
        query.addQueryParameter("categoryLabel", categoryLabel);
        final SearchResult<SagiaCategoryModel> searchResult = getFlexibleSearchService().search(query);
        final List<SagiaCategoryModel> sagiaCategoryModels = searchResult.getResult();
        return sagiaCategoryModels;

    }

}
