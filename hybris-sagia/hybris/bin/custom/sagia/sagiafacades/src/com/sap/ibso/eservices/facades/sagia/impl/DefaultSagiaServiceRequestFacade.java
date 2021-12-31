package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.facades.populators.license.ServiceRequestPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaServiceRequestFacade;
import com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData;
import com.sap.ibso.eservices.sagiaservices.overview.ServiceRequestsOverviewService;
import com.sap.ibso.eservices.sagiaservices.overview.data.ServiceRequestsOverviewData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implements facade to retrieve service request overview data.
 */
public class DefaultSagiaServiceRequestFacade implements SagiaServiceRequestFacade
{
    @Autowired
    private ServiceRequestsOverviewService serviceRequestsOverviewService;
    @Autowired
    private ServiceRequestPopulator serviceRequestPopulator;
    @Autowired
    private SagiaSearchService searchService;

    /**
     * Retrieves a list of all submitted service requests of an investor company which is associated to the current user.
     * The list is intended to be displayed in overview components e.g. in dashboard.
     *
     * @return the service requests for overview components
     */
    @Override
    public List<ServiceRequestData> getServiceRequestsData()
    {
        ServiceRequestsOverviewData serviceRequestsOverviewData = serviceRequestsOverviewService.getServiceRequests();
        Map<String, SagiaServiceModel> servicesByCodes = searchService.getAllServicesByCodes();

        List<ServiceRequestData> serviceRequestDataList = new ArrayList<>();
        for (com.sap.ibso.eservices.sagiaservices.overview.data.ServiceRequestData serviceRequest : serviceRequestsOverviewData.getServiceRequests())
        {
            ServiceRequestData serviceRequestData = new ServiceRequestData();
            serviceRequestPopulator.populate(serviceRequest, serviceRequestData, servicesByCodes);
            serviceRequestDataList.add(serviceRequestData);
        }

        return serviceRequestDataList;
    }
}
