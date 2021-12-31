package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.model.SagiaUserTermsAndConditionsModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaTermsAndConditionsService;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

/**
 * DefaultTermsAndConditionsFacade
 */
public class DefaultTermsAndConditionsFacade implements SagiaTermsAndConditionsFacade {

    private ModelService modelService;

    private SagiaTermsAndConditionsService sagiaTermsAndConditionsService;

    /**
     * Accept a version of Terms and Conditions by a Customer on an event.
     * @param customer - Customer who accepts T&C
     * @param event - Event on which the T&C were accepted
     */
    @Override
    public void acceptTermsAndConditions(CustomerModel customer, TermsAndConditionsAcceptanceEventEnum event) {
        SagiaUserTermsAndConditionsModel sagiaUserTermsAndConditionsModel = new SagiaUserTermsAndConditionsModel();
        sagiaUserTermsAndConditionsModel.setUser(customer);
        sagiaUserTermsAndConditionsModel.setTermsAndConditions(sagiaTermsAndConditionsService.getActive(event));
        modelService.save(sagiaUserTermsAndConditionsModel);
    }

    /**
     * get ModelService
     * @return ModelService
     */
    public ModelService getModelService() {
        return modelService;
    }

    /**
     * set ModelService
     * @param modelService modelService
     */
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    /**
     * get SagiaTermsAndConditionsService
     * @return SagiaTermsAndConditionsService
     */
    public SagiaTermsAndConditionsService getSagiaTermsAndConditionsService() {
        return sagiaTermsAndConditionsService;
    }

    /**
     * set SagiaTermsAndConditionsService
     * @param sagiaTermsAndConditionsService sagiaTermsAndConditionsService
     */
    public void setSagiaTermsAndConditionsService(SagiaTermsAndConditionsService sagiaTermsAndConditionsService) {
        this.sagiaTermsAndConditionsService = sagiaTermsAndConditionsService;
    }
}
