package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaPaymentSessionModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Map;
import java.util.Optional;

public interface SagiaPaymentSessionService {

    /**
     * Retrieves the SagiaPayment session when the session is lost, based on the logged in customer.
     *
     * @param customerModel
     * @return Sagia payment session for a giving customer.
     */
    Optional<SagiaPaymentSessionModel> getSagiaPaymentSessionByCustomer(CustomerModel customerModel);

    /**
     * Save sagia payment session for a given customer and sessionId.
     * @param customerModel
     * @param secureId3D
     * @param paymentMap
     * @param transactionId
     */
    void saveSagiaPaymentSession(CustomerModel customerModel, String secureId3D, String paymentMap, String transactionId);
}
