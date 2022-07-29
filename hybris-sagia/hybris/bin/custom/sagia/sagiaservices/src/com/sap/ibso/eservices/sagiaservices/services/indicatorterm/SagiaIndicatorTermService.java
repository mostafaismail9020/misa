package com.sap.ibso.eservices.sagiaservices.services.indicatorterm;

import com.sap.ibso.eservices.core.jalo.SagiaIndicatorTerm;

import java.util.List;

/**
 * The interface SagiaIndicatorTermService.
 */
public interface SagiaIndicatorTermService {

    List<SagiaIndicatorTerm> getActiveIndicatorTerms();
}
