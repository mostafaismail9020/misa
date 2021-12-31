package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.core.model.SagiaServiceTabModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.facades.data.SagiaServiceData;
import com.sap.ibso.eservices.facades.data.SagiaServiceTabData;
import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import com.sap.ibso.eservices.facades.sagia.SagiaSearchFacade;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.*;

/**
 * DefaultSagiaSearchFacade
 */
public class DefaultSagiaSearchFacade implements SagiaSearchFacade {

    private SagiaSearchService sagiaSearchService;
    private Converter<SagiaServiceModel,SagiaServiceData> sagiaServiceConverter;
    private Converter<SagiaServiceTabModel,SagiaServiceTabData> sagiaServiceTabConverter;
    private Converter<SagiaCategoryModel,SagiaCategoryData> sagiaCategoryConverter;

    /**
     * @return
     */
    public SagiaSearchService getSagiaSearchService() {
        return sagiaSearchService;
    }

    /**
     * @param sagiaSearchService
     */
    public void setSagiaSearchService(SagiaSearchService sagiaSearchService) {
        this.sagiaSearchService = sagiaSearchService;
    }

    /**
     * @return
     */
    public Converter<SagiaServiceModel, SagiaServiceData> getSagiaServiceConverter() {
        return sagiaServiceConverter;
    }

    /**
     * @param sagiaServiceConverter
     */
    public void setSagiaServiceConverter(Converter<SagiaServiceModel, SagiaServiceData> sagiaServiceConverter) {
        this.sagiaServiceConverter = sagiaServiceConverter;
    }

    /**
     * @return
     */
    public Converter<SagiaServiceTabModel, SagiaServiceTabData> getSagiaServiceTabConverter() {
        return sagiaServiceTabConverter;
    }

    /**
     * @param sagiaServiceTabConverter
     */
    public void setSagiaServiceTabConverter(Converter<SagiaServiceTabModel, SagiaServiceTabData> sagiaServiceTabConverter) {
        this.sagiaServiceTabConverter = sagiaServiceTabConverter;
    }

    /**
     * @return
     */
    public Converter<SagiaCategoryModel, SagiaCategoryData> getSagiaCategoryConverter() {
        return sagiaCategoryConverter;
    }

    /**
     * @param sagiaCategoryConverter
     */
    public void setSagiaCategoryConverter(Converter<SagiaCategoryModel, SagiaCategoryData> sagiaCategoryConverter) {
        this.sagiaCategoryConverter = sagiaCategoryConverter;
    }


    /**
     * @param text
     * @return
     */
    @Override
    public Map<String,List<SagiaServiceData>> searchServices(String text) {
        return  createServiceMap(getSagiaSearchService().searchSagiaServices(text));
    }

    /**
     * retrieves ServiceDetails
     * @param code code
     * @return List of SagiaServiceTabData
     */
    public List<SagiaServiceTabData> getServiceDetails(String code){
        SagiaServiceModel serviceModel = getSagiaSearchService().getSagiaServiceByCode(code);
        List<SagiaServiceTabData> tabsList = new ArrayList<>();
        if(serviceModel.getTabs() != null){
            for (SagiaServiceTabModel tab:
                    serviceModel.getTabs()) {
                tabsList.add(getSagiaServiceTabConverter().convert(tab));
            }
        }
        return tabsList;
    }


    /**
     * @return
     */
    public Map<String,List<SagiaServiceData>> getAllServices(){
        Set<SagiaServiceModel> sagiaServiceModelSet = getSagiaSearchService().getAllServices();
        return  this.createServiceMap(sagiaServiceModelSet);
    }

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
                resultList.add(getSagiaServiceConverter().convert(service));
                resultListMap.put(service.getCategory().getName(),resultList);
            }
        }
        return resultListMap;
    }

}
