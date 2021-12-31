package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.model.SagiaTermsAndConditionsModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaTermsAndConditionsDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultTermsAndConditionsDAO extends DefaultGenericDao<SagiaTermsAndConditionsModel> implements SagiaTermsAndConditionsDAO {

    /**
     * Constructor configured in spring
     *
     * @param typecode - The typecode of the entity
     */
    public DefaultTermsAndConditionsDAO(String typecode) {
        super(typecode);
    }

    /**
     * @return - All terms and conditions stored in DB
     */
    @Override
    public List<SagiaTermsAndConditionsModel> getAll() {
        return super.find();
    }

    /**
     * Retrieves the most recent saved terms and conditions.
     * Period : now - seconds
     * @param seconds - Period of seconds
     * @param event - Acceptance event
     * @return - List of recently saved terms and conditions
     */
    @Override
    public List<SagiaTermsAndConditionsModel> getRecentlySaved(int seconds,TermsAndConditionsAcceptanceEventEnum event) {
        final String queryString = "SELECT {pk} FROM {SagiaTermsAndConditions} WHERE {"+SagiaTermsAndConditionsModel.CREATIONTIME+"}>=?date " +
                "AND {"+SagiaTermsAndConditionsModel.ACCEPTANCEEVENT +"}=?event " +
                "ORDER BY {" + SagiaTermsAndConditionsModel.CREATIONTIME + "} DESC";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, -seconds);
        query.addQueryParameter("date", now.getTime());
        query.addQueryParameter("event", event);
        SearchResult<SagiaTermsAndConditionsModel> searchResult = getFlexibleSearchService().search(query);

        return searchResult.getCount() > 0 ? searchResult.getResult() : null;
    }

    /**
     * Get the active terms and conditions for an event
     * @param event - The event for which T&C are retrieved
     * @return - Terms and Conditions Model
     */
    @Override
    public SagiaTermsAndConditionsModel getActive(TermsAndConditionsAcceptanceEventEnum event) {
        Map<String,Object> map = new HashMap<>();
        map.put(SagiaTermsAndConditionsModel.ACCEPTANCEEVENT,event);
        map.put(SagiaTermsAndConditionsModel.ACTIVE,true);

        final SortParameters sortParameters = new SortParameters();
        sortParameters.addSortParameter(SagiaTermsAndConditionsModel.VERSION, SortParameters.SortOrder.DESCENDING);

        List<SagiaTermsAndConditionsModel> results = super.find(map,sortParameters);

        if(results == null || results.isEmpty()){
            return null;
        }

        return results.get(0);
    }

}
