package com.sap.ibso.eservices.sagiaservices.license;

/**
 * Provides access to payment information assignments to e-services.
 */
public interface PaymentAssignmentService
{
    /**
     * Enumerates fixed service types.
     */
    enum ServiceType
    {
        /**
         * Indicates an already successfully submitted license application according to Qeemah 1.
         */
        QEEMAH_1,
        /**
         * Indicates an already successfully submitted license application according to Qeemah 2.
         */
        QEEMAH_2
    }

    /**
     * Assigns a payment transaction identifier to an e-service for the currently logged in user.
     * An already successfully submitted license application is in this context an example for such an e-service.
     * <p>
     * The following basic parameter check is performed:
     * service identifier, service type and payment transaction identifier must not be null.
     * </p>
     *
     * @param serviceId the service identifier
     * @param serviceType the service type (from an enumeration of fixed service types)
     * @param transactionId the payment transaction identifier
     * @throws IllegalArgumentException if a basic parameter check fails
     */
    void assignPaymentInformation(String serviceId, ServiceType serviceType, String transactionId);

    /**
     * Assigns a payment transaction identifier to an e-service for the currently logged in user.
     * Note: The service type provided to this method (as String) must be known by the underlying implementation. Therefore
     * the caller needs to ensure to provide a supported service type.
     * <p>
     * The following basic parameter check is performed:
     * service identifier, service type and payment transaction identifier must not be null.
     * </p>
     *
     * @param serviceId the service identifier
     * @param serviceType the service type
     * @param transactionId the payment transaction identifier
     * @throws IllegalArgumentException if a basic parameter check fails
     */
    void assignPaymentInformation(String serviceId, String serviceType, String transactionId);
}
