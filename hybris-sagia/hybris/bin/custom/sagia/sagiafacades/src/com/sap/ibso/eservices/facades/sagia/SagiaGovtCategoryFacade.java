package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import com.sap.ibso.eservices.facades.data.services.SagiaServiceData;
import com.sap.ibso.eservices.sagiaservices.data.CRMGovtServiceUploadData;
import com.sap.ibso.eservices.sagiaservices.data.SagiaCRMGovtService;
import com.sap.ibso.eservices.sagiaservices.data.SagiaCRMGovtServiceData;
import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceInfo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * SagiaGovtCategoryFacade
 */
public interface SagiaGovtCategoryFacade {
    /**
     * retrieves GovtCategoriesServices
     * @return govtCategoriesServices map
     */
    Map<SagiaCategoryData, List<SagiaServiceData>> getGovtCategoriesServices();

    /**
     * retrieves CRMServicesByCategory
     * @param serviceUrl serviceUrl
     * @return Collection of SagiaCRMGovtServiceData
     */
    Collection<SagiaCRMGovtServiceData> getCRMServicesByCategory(String serviceUrl);

    /**
     * retrieves GovtServiceById
     * @param serviceId serviceId
     * @return SagiaCRMGovtService
     */
    SagiaCRMGovtService getGovtServiceById(String serviceId);

    /**
     * retrieves FilesToBeUploaded
     * @param serviceUrl serviceUrl
     * @return Collection of CRMGovtServiceUploadData
     */
    Collection<CRMGovtServiceUploadData> getFilesToBeUploaded(String serviceUrl);

    /**
     * retrieves InfoData
     * @return SagiaGovtServiceInfo
     */
    SagiaGovtServiceInfo getInfoData();
}
