package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.ServiceTypeModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaServiceTypeService;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements access to service types.
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaServiceTypeService implements SagiaServiceTypeService
{
    private GenericDao<ServiceTypeModel> sagiaServiceTypeDAO;

    @Override
    public Map<String, ServiceTypeModel> getServiceTypesByCodes()
    {
        // Find service types
        return map(sagiaServiceTypeDAO.find());
    }

    @Override
    public Map<String, ServiceTypeModel> getOverviewRelevantServiceTypesByCodes()
    {
        // Find overview relevant service types
        Map<String, Boolean> parameters = new HashMap<>(1);
        parameters.put(ServiceTypeModel.OVERVIEWRELEVANT, Boolean.TRUE);

        return map(sagiaServiceTypeDAO.find(parameters));
    }

    /**
     * Creates a map of service types by service type codes.
     *
     * @param serviceTypes the service types
     * @return the map of service types by service type codes
     */
    private Map<String, ServiceTypeModel> map(List<ServiceTypeModel> serviceTypes)
    {
        // Check list
        if (serviceTypes == null || serviceTypes.isEmpty())
        {
            return Collections.emptyMap();
        }

        // At least one service type was found
        Map<String, ServiceTypeModel> map = new HashMap<>(serviceTypes.size());
        for (ServiceTypeModel model : serviceTypes)
        {
            map.put(model.getCode(), model);
        }

        return map;
    }

    /**
     * Sets the data access object for service types.
     *
     * @param sagiaServiceTypeDAO the data access object for service types
     */
    @Required
    public void setSagiaServiceTypeDAO(GenericDao<ServiceTypeModel> sagiaServiceTypeDAO)
    {
        this.sagiaServiceTypeDAO = sagiaServiceTypeDAO;
    }
}
