package com.sap.ibso.eservices.sagiaservices.services.indicatorterm.impl;

import com.sap.ibso.eservices.core.model.SagiaIndicatorTermModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaIndicatorTermDAO;
import com.sap.ibso.eservices.sagiaservices.services.indicatorterm.SagiaIndicatorTermService;

import javax.annotation.Resource;
import java.util.List;

public class DefaultSagiaIndicatorTermServiceImpl implements SagiaIndicatorTermService {

    @Resource
    private SagiaIndicatorTermDAO sagiaIndicatorTermDAO;


    @Override
    public List<SagiaIndicatorTermModel> getActiveIndicatorTerms() {
        return sagiaIndicatorTermDAO.getActiveIndicatorTerms();
    }
}
