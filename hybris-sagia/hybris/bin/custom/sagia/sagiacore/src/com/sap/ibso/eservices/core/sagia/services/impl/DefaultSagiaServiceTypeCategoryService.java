package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.ServiceTypeCategoryModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaServiceTypeCategoryDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaServiceTypeCategoryService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements access to service type categories.
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaServiceTypeCategoryService implements SagiaServiceTypeCategoryService
{
    private SagiaServiceTypeCategoryDAO sagiaServiceTypeCategoryDAO;

    @Override
    public Map<String, ServiceTypeCategoryModel> getServiceTypeCategoriesByCodes()
    {
        // Find service type categories
        return map(sagiaServiceTypeCategoryDAO.find());
    }

    @Override
    public Map<String, ServiceTypeCategoryModel> getOverviewRelevantServiceTypeCategoriesByCodes()
    {
        // Find service type categories for overview relevant service types
        return map(sagiaServiceTypeCategoryDAO.findForOverviewRelevantServiceTypes());
    }

    /**
     * Creates a map of service type categories by service type category codes.
     *
     * @param serviceTypeCategories the service type categories
     * @return the map of service type categories by service type category codes
     */
    private Map<String, ServiceTypeCategoryModel> map(List<ServiceTypeCategoryModel> serviceTypeCategories)
    {
        // Check list
        if (serviceTypeCategories == null || serviceTypeCategories.isEmpty())
        {
            return Collections.emptyMap();
        }

        // At least one service type category was found
        Map<String, ServiceTypeCategoryModel> map = new HashMap<>(serviceTypeCategories.size());
        for (ServiceTypeCategoryModel model : serviceTypeCategories)
        {
            map.put(model.getCode(), model);
        }

        return map;
    }

    /**
     * Sets the data access object for service type categories.
     *
     * @param sagiaServiceTypeCategoryDAO the data access object for service type categories
     */
    @Required
    public void setSagiaServiceTypeCategoryDAO(SagiaServiceTypeCategoryDAO sagiaServiceTypeCategoryDAO)
    {
        this.sagiaServiceTypeCategoryDAO = sagiaServiceTypeCategoryDAO;
    }
}
