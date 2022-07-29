package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.jalo.SagiaIndicatorTerm;
import com.sap.ibso.eservices.facades.data.SagiaIndicatorTermData;
import com.sap.ibso.eservices.facades.sagia.SagiaIndicatorTermFacade;
import com.sap.ibso.eservices.sagiaservices.services.indicatorterm.SagiaIndicatorTermService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class DefaultSagiaIndicatorTermFacade implements SagiaIndicatorTermFacade {



    @Resource
    private SagiaIndicatorTermService sagiaIndicatorTermService;

    @Resource
    private Converter<SagiaIndicatorTerm, SagiaIndicatorTermData> sagiaIndicatorTermConverter;



    @Override
    public List<SagiaIndicatorTermData> getAllActiveIndicatorTerms() {
        List<SagiaIndicatorTerm> listIndicorTermModels = sagiaIndicatorTermService.getActiveIndicatorTerms();
        List<SagiaIndicatorTermData> listIndicorTermsData = new ArrayList<>();
        for (SagiaIndicatorTerm sagiaIndicatorTermModel : listIndicorTermModels){
            SagiaIndicatorTermData sagiaIndicotrTermData = sagiaIndicatorTermConverter.convert(sagiaIndicatorTermModel);
            listIndicorTermsData.add(sagiaIndicotrTermData);
        }
        return  listIndicorTermsData ;
    }
}
