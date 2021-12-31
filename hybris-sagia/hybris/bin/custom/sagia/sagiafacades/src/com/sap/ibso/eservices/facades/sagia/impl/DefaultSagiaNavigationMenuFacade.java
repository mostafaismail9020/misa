package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCategoryDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.facades.data.SagiaServiceData;
import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import com.sap.ibso.eservices.facades.populators.SagiaCategoryDataPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaNavigationMenuFacade;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.*;

/**
 * DefaultSagiaNavigationMenuFacade
 */
public class DefaultSagiaNavigationMenuFacade implements SagiaNavigationMenuFacade {
    private SagiaSearchService sagiaSearchService;
    private Converter<SagiaServiceModel, SagiaServiceData> sagiaServiceConverter;
    private Converter<SagiaCategoryModel, SagiaCategoryData> sagiaCategoryConverter;
    private SagiaCategoryDAO sagiaCategoryDAO;
    private SagiaCategoryDataPopulator sagiaCategoryDataPopulator;

    /**
     * @param sagiaSearchService
     */
    public void setSagiaSearchService(SagiaSearchService sagiaSearchService) {
        this.sagiaSearchService = sagiaSearchService;
    }

    /**
     * @param sagiaServiceConverter
     */
    public void setSagiaServiceConverter(Converter<SagiaServiceModel, SagiaServiceData> sagiaServiceConverter) {
        this.sagiaServiceConverter = sagiaServiceConverter;
    }

    /**
     * @param sagiaCategoryConverter
     */
    public void setSagiaCategoryConverter(Converter<SagiaCategoryModel, SagiaCategoryData> sagiaCategoryConverter) {
        this.sagiaCategoryConverter = sagiaCategoryConverter;
    }

    /**
     * @return
     */
    public SagiaSearchService getSagiaSearchService() {
        return sagiaSearchService;
    }

    /**
     * @return
     */
    public Converter<SagiaServiceModel, SagiaServiceData> getSagiaServiceConverter() {
        return sagiaServiceConverter;
    }

    /**
     * @return
     */
    public Converter<SagiaCategoryModel, SagiaCategoryData> getSagiaCategoryConverter() {
        return sagiaCategoryConverter;
    }

    /**
     * @return
     */
    public SagiaCategoryDAO getSagiaCategoryDAO() {
        return sagiaCategoryDAO;
    }

    /**
     * @param sagiaCategoryDAO
     */
    public void setSagiaCategoryDAO(SagiaCategoryDAO sagiaCategoryDAO) {
        this.sagiaCategoryDAO = sagiaCategoryDAO;
    }

    /**
     * @return
     */
    @Override
    public Map<String, List<SagiaServiceData>> getNavigationMenuServices() {
        return getAllServices();
    }

    /**
     * @return
     */
    @Override
    public Map<String, List<SagiaCategoryData>> getNavigationMenuCategories() {
        Map<String, List<SagiaCategoryData>> resultMap = new HashMap<>();
        List<SagiaCategoryData> resultList;
        List<SagiaCategoryData> resultListWithoutLabel = new ArrayList<>();
        for (SagiaCategoryModel category : sagiaCategoryDAO.getAllCategories()) {
            if (category.getLabel() != null) {
                if (resultMap.get(category.getLabel()) != null) {
                    resultList = resultMap.get(category.getLabel());
                } else {
                    resultList = new ArrayList<>();
                }
                resultList.add(sagiaCategoryConverter.convert(category));
                resultMap.put(category.getLabel(), resultList);
            } else {
                resultListWithoutLabel.add(sagiaCategoryConverter.convert(category));
                resultMap.put("no_label", resultListWithoutLabel);
            }
        }
        return resultMap;
    }

    /**
     * @return
     */
    private HashMap<String,List<SagiaServiceData>> getAllServices(){
        Set<SagiaServiceModel> sagiaServiceModelSet = getSagiaSearchService().getAllServices();
        return  this.createServiceMap(sagiaServiceModelSet);
    }

    /**
     * @param sagiaServiceModelSet
     * @return
     */
    private HashMap<String,List<SagiaServiceData>> createServiceMap(Set<SagiaServiceModel> sagiaServiceModelSet){
        HashMap<String,List<SagiaServiceData>> resultListMap = new HashMap<>();
        List<SagiaServiceData> resultList;
        for(SagiaServiceModel service : sagiaServiceModelSet){
            if(service.getCategory()!= null){
                if(resultListMap.get(service.getCategory().getName()) != null){
                    resultList = resultListMap.get(service.getCategory().getName());
                } else {
                    resultList = new ArrayList<>();
                }
                resultList.add(convertToServiceForNavigation(service));
                resultListMap.put(service.getCategory().getName(),resultList);
            }
        }
        return resultListMap;
    }

    /**
     * converter which populates the serviceData url differently if it is instance of government service
     * @param serviceModel
     * @return
     */
    private SagiaServiceData convertToServiceForNavigation(SagiaServiceModel serviceModel) {
        SagiaServiceData serviceData = new SagiaServiceData();
        SagiaCategoryModel sagiaCategoryModel = serviceModel.getCategory();
        serviceData.setName(serviceModel.getName());
        serviceData.setDescription(serviceModel.getDescription());
        serviceData.setCode(serviceModel.getCode());
        serviceData.setUrl(serviceModel.getUrl());
        if (sagiaCategoryModel != null) {
            SagiaCategoryData categoryData = new SagiaCategoryData();
            sagiaCategoryDataPopulator.populate(sagiaCategoryModel, categoryData);
            serviceData.setCategory(categoryData);
        }
        if (serviceModel instanceof SagiaCommerceIndustryServiceModel ||
                serviceModel instanceof SagiaLabourServiceModel ||
                serviceModel instanceof SagiaInteriorAndRecruitmentServiceModel ||
                serviceModel instanceof SagiaInteriorAndPassportServiceModel) {
            if (sagiaCategoryModel != null) {
                String url = "services/government/" + sagiaCategoryModel.getCode() + "/" + sagiaCategoryModel.getCategoryUrl() + "_" + serviceModel.getUrl();
                serviceData.setMenuUrl(url + "?serviceName=" + serviceModel.getName());
                serviceData.setUrl(sagiaCategoryModel.getCategoryUrl() + "_" + serviceModel.getUrl());
            }
        } else {
            serviceData.setMenuUrl(serviceModel.getUrl());
        }
        return serviceData;
    }

    public void setSagiaCategoryDataPopulator(SagiaCategoryDataPopulator sagiaCategoryDataPopulator) {
        this.sagiaCategoryDataPopulator = sagiaCategoryDataPopulator;
    }
}

