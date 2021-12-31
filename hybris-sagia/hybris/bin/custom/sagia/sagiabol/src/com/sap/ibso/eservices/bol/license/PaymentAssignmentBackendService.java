package com.sap.ibso.eservices.bol.license;

import com.sap.ibso.eservices.bol.license.data.PaymentAssignmentBackendData;

/**
 * Provides synchronous access to payment information assignments to e-services in SAP backend system.
 */
public interface PaymentAssignmentBackendService
{
    /**
     * Assigns a payment transaction identifier to an e-service.
     *
     * @param serviceId the service identifier
     * @param serviceType the backend service type (from an enumeration of fixed service types)
     * @param transactionId the payment transaction identifier
     * @param entityId the identifier of the entity for which the payment assignment shall be performed
     * @return the payment assignment backend data
     */
    PaymentAssignmentBackendData assignPaymentInformation(String serviceId, BackendServiceType serviceType, String transactionId, String entityId);

    /**
     * Assigns a payment transaction identifier to an e-service.
     * Note: The service type provided to this method (as String) must be known by the underlying implementation. Therefore
     * the caller needs to ensure to provide a supported service type.
     *
     * @param serviceId the service identifier
     * @param serviceType the backend service type (as String)
     * @param transactionId the payment transaction identifier
     * @param entityId the identifier of the entity for which the payment assignment shall be performed
     * @return the payment assignment backend data
     */
    PaymentAssignmentBackendData assignPaymentInformation(String serviceId, String serviceType, String transactionId, String entityId);
}
