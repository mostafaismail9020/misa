package com.sap.ibso.eservices.core.event;

import com.sap.ibso.eservices.core.model.SagiaPaymentAPIErrorModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

public class SagiaPaymentApiErrorEventListener extends AbstractEventListener<SagiaPaymentApiErrorEvent> {

    private ModelService modelService;

    @Override
    protected void onEvent(SagiaPaymentApiErrorEvent event) {
        // Saving error in database.
        getModelService().save(this.mapFromPaymentErrorEventToModel(event));
    }

    private SagiaPaymentAPIErrorModel mapFromPaymentErrorEventToModel(SagiaPaymentApiErrorEvent event){
        SagiaPaymentAPIErrorModel sagiaPaymentAPIErrorModel = getModelService().create(SagiaPaymentAPIErrorModel.class);
        sagiaPaymentAPIErrorModel.setApiUrl(event.getApiUrl());
        sagiaPaymentAPIErrorModel.setCreationDate(event.getCreationDate());
        sagiaPaymentAPIErrorModel.setCustomer(event.getCustomer());
        sagiaPaymentAPIErrorModel.setErrorMessage(event.getErrorMessage());
        sagiaPaymentAPIErrorModel.setHttpMethod(event.getHttpMethod());
        sagiaPaymentAPIErrorModel.setRequestBody(event.getRequestBody());
        sagiaPaymentAPIErrorModel.setResponseBody(event.getResponseBody());
        return  sagiaPaymentAPIErrorModel;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
