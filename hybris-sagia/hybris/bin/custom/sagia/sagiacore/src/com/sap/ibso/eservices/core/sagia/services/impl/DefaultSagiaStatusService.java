package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.StatusModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaStatusService;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements access to status codes and names.
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaStatusService implements SagiaStatusService
{
    private GenericDao<StatusModel> sagiaStatusDAO;

    @Override
    public Map<String, StatusModel> getStatusesByCodes()
    {
        // Find statuses
        return map(sagiaStatusDAO.find());
    }

    /**
     * Creates a map of statuses by status codes.
     *
     * @param statuses the statuses
     * @return the map of statuses by status codes
     */
    private Map<String, StatusModel> map(List<StatusModel> statuses)
    {
        // Check list
        if (statuses == null || statuses.isEmpty())
        {
            return Collections.emptyMap();
        }

        // At least one status was found
        Map<String, StatusModel> map = new HashMap<>(statuses.size());
        for (StatusModel model : statuses)
        {
            map.put(model.getCode(), model);
        }

        return map;
    }

    /**
     * Sets the data access object for statuses.
     *
     * @param sagiaStatusDAO the data access object for statuses
     */
    @Required
    public void setSagiaStatusDAO(GenericDao<StatusModel> sagiaStatusDAO)
    {
        this.sagiaStatusDAO = sagiaStatusDAO;
    }
}
