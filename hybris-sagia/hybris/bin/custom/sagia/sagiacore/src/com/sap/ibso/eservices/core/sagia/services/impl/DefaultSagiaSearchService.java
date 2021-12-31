package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaServiceDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.*;

/**
 * Default implementation of Sagia Search Service
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaSearchService extends AbstractBusinessService implements SagiaSearchService {

    private transient SagiaServiceDAO sagiaServiceDAO;

    public SagiaServiceDAO getSagiaServiceDAO() {
        return sagiaServiceDAO;
    }

    public void setSagiaServiceDAO(SagiaServiceDAO sagiaServiceDAO) {
        this.sagiaServiceDAO = sagiaServiceDAO;
    }

    @Override
    public Set<SagiaServiceModel> searchSagiaServices(String text) {
        return getSagiaServiceDAO().searchSagiaServices(text);
    }

    @Override
    public SagiaServiceModel getSagiaServiceByCode(String code) {
        return getSagiaServiceDAO().getServiceForCode(code);
    }

    @Override
    public Set<SagiaServiceModel> getAllServices() {
        List<SagiaServiceModel> servicesList = getSagiaServiceDAO().getAllServices();
        return  servicesList == null || servicesList.isEmpty() ? Collections.emptySet() : new HashSet<>(servicesList);
    }

    @Override
    public Map<String, SagiaServiceModel> getAllServicesByCodes()
    {
        Set<SagiaServiceModel> services = getAllServices();

        // Check set
        if (services == null || services.isEmpty())
        {
            return Collections.emptyMap();
        }

        // At least one service type was found
        Map<String, SagiaServiceModel> map = new HashMap<>(services.size());
        for (SagiaServiceModel model : services)
        {
            map.put(model.getCode(), model);
        }

        return map;
    }
}
