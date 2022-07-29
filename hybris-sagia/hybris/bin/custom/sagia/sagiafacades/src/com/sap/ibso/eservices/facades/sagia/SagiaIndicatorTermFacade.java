package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.SagiaIndicatorTermData;

import java.util.List;

/**
 * SagiaIndicatorTermFacade
 */
public interface SagiaIndicatorTermFacade {

    List<SagiaIndicatorTermData> getAllActiveIndicatorTerms();

}



