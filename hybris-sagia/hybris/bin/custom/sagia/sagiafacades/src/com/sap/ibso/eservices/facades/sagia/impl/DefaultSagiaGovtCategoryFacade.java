package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import com.sap.ibso.eservices.facades.data.services.SagiaServiceData;
import com.sap.ibso.eservices.facades.populators.*;
import com.sap.ibso.eservices.facades.sagia.SagiaGovtCategoryFacade;
import com.sap.ibso.eservices.sagiaservices.data.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtServiceUploadData;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaGovtInfoDataService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaGovtService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaSupportingDocumentsService;
import de.hybris.platform.jalo.JaloObjectNoLongerValidException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * DefaultSagiaGovtCategoryFacade
 */
public class DefaultSagiaGovtCategoryFacade implements SagiaGovtCategoryFacade {

    private SagiaGovtService sagiaGovtService;
    private SagiaGovtInfoDataService sagiaGovtInfoDataService;
    private SagiaCategoryDataPopulator sagiaCategoryDataPopulator;
    private SagiaGovtServicePopulator sagiaGovtServicePopulator;
    private SagiaGovtServiceCRMPopulator sagiaGovtServiceCRMPopulator;
    private SagiaGovtCRMUploadPopulator sagiaGovtCRMUploadPopulator;
    private SagiaGovtInfoDataPopulator sagiaGovtInfoDataPopulator;
    private SagiaSupportingDocumentsService sagiaSupportingDocumentsService;
    private static final Logger LOG = LoggerFactory.getLogger(DefaultSagiaGovtCategoryFacade.class);

    /**
     * Gets a map with government services and categories and populates tha data object map.
     * @return map with govt categories and services that belong to the categories.
     */
    @Override
    public Map<SagiaCategoryData, List<SagiaServiceData>> getGovtCategoriesServices() {
        Map<SagiaCategoryModel, List<SagiaServiceModel>> categoryServiceModelMap = sagiaGovtService.getGovtCategoriesServices();
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
    public Collection<SagiaCRMGovtServiceData> getCRMServicesByCategory(String serviceUrl) {
        Collection<SagiaCRMGovtService> serviceList = sagiaGovtService.getCRMServicesByCategory(serviceUrl);
        Collection<SagiaCRMGovtServiceData> servicesDataList = new LinkedList<>();
        if (CollectionUtils.isNotEmpty(serviceList)) {
            serviceList.forEach(service -> {
                SagiaCRMGovtServiceData serviceData = new SagiaCRMGovtServiceData();
                sagiaGovtServiceCRMPopulator.populate(service, serviceData);
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
    public SagiaCRMGovtService getGovtServiceById(String serviceId) {
        return sagiaGovtService.getCRMServiceById(serviceId);
    }

    /**
     * Gets a collection of data that needs to be uploaded.
     * @param serviceUrl
     * @return collection of files to be uploaded.
     */
    @Override
    public Collection<CRMGovtServiceUploadData> getFilesToBeUploaded(String serviceUrl) {
        Collection<GovtServiceUploadData> documentsToUpload = sagiaSupportingDocumentsService.getFilesToBeUploaded(serviceUrl);
        Collection<CRMGovtServiceUploadData> crmDocumentsToUpload = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(documentsToUpload)){
            documentsToUpload.forEach(item -> {
                CRMGovtServiceUploadData crmItem = new CRMGovtServiceUploadData();
                sagiaGovtCRMUploadPopulator.populate(item, crmItem);
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
    public SagiaGovtServiceInfo getInfoData() {
        SagiaGovtServiceInfoDataCRM crmInfoData = sagiaGovtInfoDataService.getInfoData();
        SagiaGovtServiceInfo infoData = new SagiaGovtServiceInfo();
        sagiaGovtInfoDataPopulator.populate(crmInfoData, infoData);
        return infoData;
    }

    /**
     * @param sagiaGovtService
     */
    public void setSagiaGovtService(SagiaGovtService sagiaGovtService) {
        this.sagiaGovtService = sagiaGovtService;
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
     * @param sagiaGovtServiceCRMPopulator
     */
    public void setSagiaGovtServiceCRMPopulator(SagiaGovtServiceCRMPopulator sagiaGovtServiceCRMPopulator) {
        this.sagiaGovtServiceCRMPopulator = sagiaGovtServiceCRMPopulator;
    }

    /**
     * @param sagiaGovtCRMUploadPopulator
     */
    public void setSagiaGovtCRMUploadPopulator(SagiaGovtCRMUploadPopulator sagiaGovtCRMUploadPopulator) {
        this.sagiaGovtCRMUploadPopulator = sagiaGovtCRMUploadPopulator;
    }

    /**
     * @param sagiaSupportingDocumentsService
     */
    public void setSagiaSupportingDocumentsService(SagiaSupportingDocumentsService sagiaSupportingDocumentsService) {
        this.sagiaSupportingDocumentsService = sagiaSupportingDocumentsService;
    }

    /**
     * @param sagiaGovtInfoDataService
     */
    public void setSagiaGovtInfoDataService(SagiaGovtInfoDataService sagiaGovtInfoDataService) {
        this.sagiaGovtInfoDataService = sagiaGovtInfoDataService;
    }

    /**
     * @param sagiaGovtInfoDataPopulator
     */
    public void setSagiaGovtInfoDataPopulator(SagiaGovtInfoDataPopulator sagiaGovtInfoDataPopulator) {
        this.sagiaGovtInfoDataPopulator = sagiaGovtInfoDataPopulator;
    }
}
