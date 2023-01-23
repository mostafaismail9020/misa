package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaGovtServiceDAO;
import com.sap.ibso.eservices.sagiaservices.data.SagiaCRMIgniteService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;
import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * SagiaIgniteService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2023 SAP
 */
public class SagiaIgniteService extends AbstractSagiaService<SagiaCRMIgniteService> {
    private static final String FILTER_BY_SERVICE_TYPE = "ServiceType eq '";
    private SagiaGovtServiceDAO sagiaGovtServiceDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(SagiaIgniteService.class);
    /**
     * Call to data access object to get a map with government services and categories.
     * @return Map with list of services that belong to the categories.
     * The categories represent the keys and the list of service represents the values for each key.
     */
    public Map<SagiaCategoryModel, List<SagiaServiceModel>> getGovtCategoriesServices() {
        return sagiaGovtServiceDAO.getGovtCategoriesServices();
    }

    /**
     * Get a collection of services from CRM that belong to a category.
     * @param serviceUrl serviceUrl
     * @return a collection of services that belong to a category.
     */
    public Collection<SagiaCRMIgniteService> getCRMServicesByCategory(String serviceUrl) {
        try {
            return super.getCollection(SagiaCRMIgniteService.class, REQUEST_PARAMETER_FILTER, FILTER_BY_SERVICE_TYPE + serviceUrl + "'");
        } catch(Exception e) {
            LOGGER.error(e.getMessage(),e);
            return Collections.emptyList();
        }
    }

    /**
     *  Get CRM service instance based on a given id.
     * @param serviceId serviceId
     * @return CRM service insance based on a given id.
     */
    public SagiaCRMIgniteService getCRMServiceById(String serviceId) {
        return super.get(SagiaCRMIgniteService.class, (Object)serviceId, REQUEST_PARAMETER_EXPAND, "GovtServicesToContentHDRNav,GovtServicesToTextNav");
    }

    public void setSagiaGovtServiceDAO(SagiaGovtServiceDAO sagiaGovtServiceDAO) {
        this.sagiaGovtServiceDAO = sagiaGovtServiceDAO;
    }
}
