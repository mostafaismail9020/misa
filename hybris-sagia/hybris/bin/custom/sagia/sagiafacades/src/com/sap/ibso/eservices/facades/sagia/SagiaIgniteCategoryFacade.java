package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import com.sap.ibso.eservices.facades.data.services.SagiaServiceData;
import com.sap.ibso.eservices.sagiaservices.data.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * SagiaIgniteCategoryFacade
 */
public interface SagiaIgniteCategoryFacade {
    /**
     * retrieves IgniteCategoriesServices
     * @return igniteCategoriesServices map
     */
    Map<SagiaCategoryData, List<SagiaServiceData>> getIgniteCategoriesServices();

    /**
     * retrieves SagiaIgniteServiceData
     * @param serviceUrl serviceUrl
     * @return Collection of getIgniteServicesByCategory
     */
    Collection<SagiaCRMIgniteServiceData> getIgniteServicesByCategory(String serviceUrl);

    /**
     * retrieves IgniteServiceById
     * @param serviceId serviceId
     * @return SagiaIgniteService
     */
    SagiaCRMIgniteService getIgniteServiceById(String serviceId);

    /**
     * retrieves FilesToBeUploaded
     * @param serviceUrl serviceUrl
     * @return Collection of CRMIgniteServiceUploadData
     */
    Collection<CRMIgniteServiceUploadData> getFilesToBeUploaded(String serviceUrl);

    /**
     * retrieves InfoData
     * @return SagiaIgniteServiceInfo
     */
    SagiaIgniteServiceInfo getInfoData();
}
