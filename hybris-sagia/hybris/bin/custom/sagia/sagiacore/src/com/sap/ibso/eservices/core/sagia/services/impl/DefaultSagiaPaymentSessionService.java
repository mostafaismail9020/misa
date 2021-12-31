package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaPaymentSessionModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaPaymentSessionDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaPaymentSessionService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Optional;

public class DefaultSagiaPaymentSessionService extends AbstractBusinessService implements SagiaPaymentSessionService {

    private SagiaPaymentSessionDAO sagiaPaymentSessionDAO;

    @Override
    public Optional<SagiaPaymentSessionModel> getSagiaPaymentSessionByCustomer(CustomerModel customerModel) {
        return sagiaPaymentSessionDAO.getSagiaPaymentSessionByCustomer(customerModel);
    }

    @Override
    public void saveSagiaPaymentSession(CustomerModel customerModel, String secureId3D, String paymentMap, String transactionId) {
        SagiaPaymentSessionModel sagiaPaymentSessionModel;
        Optional<SagiaPaymentSessionModel> sagiaPaymentSessionOpt = this.getSagiaPaymentSessionByCustomer(customerModel);

        if (sagiaPaymentSessionOpt.isPresent()) {
            sagiaPaymentSessionModel = sagiaPaymentSessionOpt.get();
        } else {
            sagiaPaymentSessionModel = getModelService().create(SagiaPaymentSessionModel.class);
        }
        sagiaPaymentSessionModel.setSecureId3D(secureId3D);
        sagiaPaymentSessionModel.setCustomer(customerModel);
        sagiaPaymentSessionModel.setPaymentMap(paymentMap);
        sagiaPaymentSessionModel.setTransactionId(transactionId);
        getModelService().save(sagiaPaymentSessionModel);
    }



    public SagiaPaymentSessionDAO getSagiaPaymentSessionDAO() {
        return sagiaPaymentSessionDAO;
    }

    @Required
    public void setSagiaPaymentSessionDAO(SagiaPaymentSessionDAO sagiaPaymentSessionDAO) {
        this.sagiaPaymentSessionDAO = sagiaPaymentSessionDAO;
    }
}
