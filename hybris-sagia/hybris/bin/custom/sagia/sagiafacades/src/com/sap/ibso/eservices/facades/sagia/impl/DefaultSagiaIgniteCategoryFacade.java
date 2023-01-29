package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import com.sap.ibso.eservices.facades.data.services.SagiaServiceData;
import com.sap.ibso.eservices.facades.populators.*;
import com.sap.ibso.eservices.facades.sagia.SagiaIgniteCategoryFacade;
import com.sap.ibso.eservices.sagiaservices.data.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.IgniteServiceUploadData;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaGovtInfoDataService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaIgniteInfoDataService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaIgniteService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaIgniteSupportingDocumentsService;
import de.hybris.platform.jalo.JaloObjectNoLongerValidException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * DefaultSagiaIgniteCategoryFacade
 */
public class DefaultSagiaIgniteCategoryFacade implements SagiaIgniteCategoryFacade {

    private SagiaIgniteService sagiaIgniteService;
    private SagiaIgniteInfoDataService sagiaIgniteInfoDataService;
    private SagiaCategoryDataPopulator sagiaCategoryDataPopulator;
    private SagiaGovtServicePopulator sagiaGovtServicePopulator;
    private SagiaIgniteServiceCRMPopulator sagiaIgniteServiceCRMPopulator;
    private SagiaIgniteCRMUploadPopulator sagiaIgniteCRMUploadPopulator;
    private SagiaIgniteInfoDataPopulator sagiaIgniteInfoDataPopulator;
    private SagiaIgniteSupportingDocumentsService sagiaIgniteSupportingDocumentsService;
    private static final Logger LOG = LoggerFactory.getLogger(DefaultSagiaIgniteCategoryFacade.class);

    /**
     * Gets a map with government services and categories and populates tha data object map.
     * @return map with govt categories and services that belong to the categories.
     */
    @Override
    public Map<SagiaCategoryData, List<SagiaServiceData>> getIgniteCategoriesServices() {
        Map<SagiaCategoryModel, List<SagiaServiceModel>> categoryServiceModelMap = sagiaIgniteService.getGovtCategoriesServices();
        Map<SagiaCategoryData, List<SagiaServiceData>> categoryServiceDataMap = new LinkedHashMap<>();

        if (MapUtils.isNotEmpty(categoryServiceModelMap)) {

            categoryServiceModelMap.forEach((k, v) ->
                    {
                        List<SagiaServiceData> sagiaServiceDataList = new ArrayList<>();
                        SagiaCategoryData sagiaCategoryData = new SagiaCategoryData();
                        sagiaCategoryDataPopulator.populate(k, sagiaCategoryData);
                        v.stream().filter(Objects::nonNull).forEach(service -> {
                            try {
                                SagiaServiceData sagiaServiceData = new SagiaServiceData();
                                sagiaGovtServicePopulator.populate(service, sagiaServiceData);
                                sagiaServiceDataList.add(sagiaServiceData);
                            } catch (JaloObjectNoLongerValidException e){
                                LOG.error("Object "+ service +" is no longer valid.",e);
                            }
                        });
                        categoryServiceDataMap.put(sagiaCategoryData, sagiaServiceDataList);
                    }
            );
        }

        return categoryServiceDataMap;
    }

    /**
     * Gets all the services belonging to a given category.
     * @param serviceUrl
     * @return collection with all services belonging to a category.
     */
    @Override
    public Collection<SagiaCRMIgniteServiceData> getIgniteServicesByCategory(String serviceUrl) {
        Collection<SagiaCRMIgniteService> serviceList = sagiaIgniteService.getCRMServicesByCategory(serviceUrl);
        Collection<SagiaCRMIgniteServiceData> servicesDataList = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(serviceList)) {
            serviceList.forEach(service -> {
                SagiaCRMIgniteServiceData serviceData = new SagiaCRMIgniteServiceData();
                sagiaIgniteServiceCRMPopulator.populate(service, serviceData);
                servicesDataList.add(serviceData);
            });
        }
        return servicesDataList;
    }

       /**
     * Gets a govt service by id.
     * @param serviceId
     * @return instance of a service fetched by id.
     */
    @Override
    public SagiaCRMIgniteService getIgniteServiceById(String serviceId) {
        return sagiaIgniteService.getCRMServiceById(serviceId);
    }

    /**
     * Gets a collection of data that needs to be uploaded.
     * @param serviceUrl
     * @return collection of files to be uploaded.
     */
    @Override
    public Collection<CRMIgniteServiceUploadData> getFilesToBeUploaded(String serviceUrl) {
        Collection<IgniteServiceUploadData> documentsToUpload = sagiaIgniteSupportingDocumentsService.getFilesToBeUploaded(serviceUrl);
        Collection<CRMIgniteServiceUploadData> crmDocumentsToUpload = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(documentsToUpload)){
            documentsToUpload.forEach(item -> {
                CRMIgniteServiceUploadData crmItem = new CRMIgniteServiceUploadData();
                sagiaIgniteCRMUploadPopulator.populate(item, crmItem);
                crmDocumentsToUpload.add(crmItem);
            });
        }
        return crmDocumentsToUpload;
    }

    /**
     * Gets the info data for the government categories from CRM
     * and populates the data object to be sent to the controller.
     * @return info data object
     */
    @Override
    public SagiaIgniteServiceInfo getInfoData() {
        SagiaIgniteServiceInfoDataCRM crmInfoData = sagiaIgniteInfoDataService.getInfoData();
        SagiaIgniteServiceInfo infoData = new SagiaIgniteServiceInfo();
        sagiaIgniteInfoDataPopulator.populate(crmInfoData, infoData);
        return infoData;
    }

    /**
     * @param sagiaIgniteService
     */
    public void setSagiaIgniteService(SagiaIgniteService sagiaIgniteService) {
        this.sagiaIgniteService = sagiaIgniteService;
    }

    /**
     * @param sagiaGovtServicePopulator
     */
    public void setSagiaGovtServicePopulator(SagiaGovtServicePopulator sagiaGovtServicePopulator) {
        this.sagiaGovtServicePopulator = sagiaGovtServicePopulator;
    }

    /**
     * @param sagiaCategoryDataPopulator
     */
    public void setSagiaCategoryDataPopulator(SagiaCategoryDataPopulator sagiaCategoryDataPopulator) {
        this.sagiaCategoryDataPopulator = sagiaCategoryDataPopulator;
    }

    /**
     * @param sagiaIgniteServiceCRMPopulator
     */
    public void setSagiaIgniteServiceCRMPopulator(SagiaIgniteServiceCRMPopulator sagiaIgniteServiceCRMPopulator) {
        this.sagiaIgniteServiceCRMPopulator = sagiaIgniteServiceCRMPopulator;
    }

    /**
     * @param sagiaIgniteCRMUploadPopulator
     */
    public void setSagiaIgniteCRMUploadPopulator(SagiaIgniteCRMUploadPopulator sagiaIgniteCRMUploadPopulator) {
        this.sagiaIgniteCRMUploadPopulator = sagiaIgniteCRMUploadPopulator;
    }

    /**
     * @param sagiaIgniteSupportingDocumentsService
     */
    public void setSagiaIgniteSupportingDocumentsService(SagiaIgniteSupportingDocumentsService sagiaIgniteSupportingDocumentsService) {
        this.sagiaIgniteSupportingDocumentsService = sagiaIgniteSupportingDocumentsService;
    }

    /**
     * @param sagiaIgniteInfoDataService
     */
    public void setSagiaIgniteInfoDataService(SagiaIgniteInfoDataService sagiaIgniteInfoDataService) {
        this.sagiaIgniteInfoDataService = sagiaIgniteInfoDataService;
    }

    /**
     * @param sagiaIgniteInfoDataPopulator
     */
    public void setSagiaIgniteInfoDataPopulator(SagiaIgniteInfoDataPopulator sagiaIgniteInfoDataPopulator) {
        this.sagiaIgniteInfoDataPopulator = sagiaIgniteInfoDataPopulator;
    }
}
