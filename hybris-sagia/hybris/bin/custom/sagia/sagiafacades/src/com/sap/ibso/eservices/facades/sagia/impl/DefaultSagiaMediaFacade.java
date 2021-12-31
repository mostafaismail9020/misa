package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaMediaModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaMediaService;
import com.sap.ibso.eservices.facades.data.SagiaMediaData;
import com.sap.ibso.eservices.facades.sagia.SagiaMediaFacade;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 * DefaultSagiaMediaFacade
 */
public class DefaultSagiaMediaFacade implements SagiaMediaFacade {
    private Converter<SagiaMediaModel,SagiaMediaData> sagiaMediaConverter;
    private SagiaMediaService sagiaMediaService;

    /**
     * @return
     */
    public Converter<SagiaMediaModel, SagiaMediaData> getSagiaMediaConverter() {
        return sagiaMediaConverter;
    }

    /**
     * sets SagiaMediaConverter
     * @param sagiaMediaConverter sagiaMediaConverter
     */
    public void setSagiaMediaConverter(Converter<SagiaMediaModel, SagiaMediaData> sagiaMediaConverter) {
        this.sagiaMediaConverter = sagiaMediaConverter;
    }

    /**
     * retrieves SagiaMediaService
     * @return SagiaMediaService
     */
    public SagiaMediaService getSagiaMediaService() {
        return sagiaMediaService;
    }

    /**
     * @param sagiaMediaService
     */
    public void setSagiaMediaService(SagiaMediaService sagiaMediaService) {
        this.sagiaMediaService = sagiaMediaService;
    }

    /**
     * retrieves SagiaMediaForPageName
     * @param  pageName pageName
     * @return SagiaMediaData
     */
    public SagiaMediaData getSagiaMediaForPageName(String pageName){
        return getSagiaMediaConverter().convert(getSagiaMediaService().getSagiaMediaForName(pageName));
    }

    @Override
    public String getContactUpdateTemplateUrl() {
        SagiaMediaData pageMedia = getSagiaMediaForPageName("contactInfoTemplate");
        return pageMedia.getAttachments()
                .stream()
                .filter(file -> "contactInfoTemplateSample".equalsIgnoreCase(file.getCode()))
                .findFirst()
                .map(file -> file.getDownloadUrl())
                .orElse("");
    }
}
