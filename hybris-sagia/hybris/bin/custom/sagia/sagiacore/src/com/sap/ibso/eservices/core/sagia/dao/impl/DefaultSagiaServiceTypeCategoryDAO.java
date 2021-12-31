package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.ServiceTypeCategoryModel;
import com.sap.ibso.eservices.core.model.ServiceTypeModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaServiceTypeCategoryDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

/**
 * Implements a data access object for service type categories.
 */
public class DefaultSagiaServiceTypeCategoryDAO extends DefaultGenericDao<ServiceTypeCategoryModel> implements SagiaServiceTypeCategoryDAO
{
    /* Flexible search query:
     * SELECT {c.pk} FROM {ServiceTypeCategory AS c JOIN ServiceType AS s ON {c.serviceType} = {s.pk}} WHERE {s.overviewRelevant} = '1'
     */
    private static final String FIND_CATEGORIES_FOR_OVERVIEW_RELEVANT_SERVICE_TYPES =
            "SELECT {c." + ServiceTypeCategoryModel.PK + "} FROM {" + ServiceTypeCategoryModel._TYPECODE +
                    " AS c JOIN " + ServiceTypeModel._TYPECODE + " AS s ON {c." + ServiceTypeCategoryModel.SERVICETYPE +
                    "} = {s." + ServiceTypeModel.PK + "}} WHERE {s." + ServiceTypeModel.OVERVIEWRELEVANT + "} = '1'";

    private static final FlexibleSearchQuery findForOverviewRelevantServiceTypesQuery;

    /**
     * Creates a data access object instance.
     */
    public DefaultSagiaServiceTypeCategoryDAO()
    {
        // ServiceTypeCategory
        super(ServiceTypeCategoryModel._TYPECODE);
    }

    @Override
    public List<ServiceTypeCategoryModel> findForOverviewRelevantServiceTypes()
    {
        SearchResult<ServiceTypeCategoryModel> searchResult = getFlexibleSearchService().search(findForOverviewRelevantServiceTypesQuery);
        return searchResult.getResult();
    }

    static
    {
        // Create query
        findForOverviewRelevantServiceTypesQuery = new FlexibleSearchQuery(FIND_CATEGORIES_FOR_OVERVIEW_RELEVANT_SERVICE_TYPES);
    }
}
