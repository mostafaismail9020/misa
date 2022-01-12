package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.SagiaServiceData;
import com.sap.ibso.eservices.facades.data.SagiaServiceTabData;

import java.util.List;
import java.util.Map;

/**
 * SagiaSearchFacade
 */
public interface SagiaSearchFacade {
    /**
     * searchs for Services by text
     * @param text text
     * @return services Map
     */
    Map<String, List<SagiaServiceData>> searchServices(String text);

    /**
     * retrieves ServiceDetails
     * @param code code
     * @return List of SagiaServiceTabData
     */
    List<SagiaServiceTabData> getServiceDetails(String code);

    /**
     * retrieves AllServices
     * @return services Map
     */
    Map<String, List<SagiaServiceData>> getAllServices();

    Map<String,List<SagiaServiceData>> getSagiaServicesByCategoryLabel(String categoryLabel);
}
