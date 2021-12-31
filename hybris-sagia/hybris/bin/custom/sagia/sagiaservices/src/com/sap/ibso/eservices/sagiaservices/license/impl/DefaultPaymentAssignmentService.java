package com.sap.ibso.eservices.sagiaservices.license.impl;

import com.sap.ibso.eservices.bol.BackendAwareService;
import com.sap.ibso.eservices.bol.license.BackendServiceType;
import com.sap.ibso.eservices.bol.license.PaymentAssignmentBackendService;
import com.sap.ibso.eservices.bol.license.data.PaymentAssignmentBackendData;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.license.PaymentAssignmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

/**
 * Implements access to payment information assignments to successfully submitted license applications.
 */
public class DefaultPaymentAssignmentService extends BackendAwareService implements PaymentAssignmentService
{
    private static final Logger LOGGER = LogManager.getLogger(DefaultPaymentAssignmentService.class);

    private InvestorMappingService investorMappingService;

    /**
     * Creates the default payment assignment service.
     *
     * @param paymentAssignmentBackendServiceBeanName the bean name (or alias) of the payment assignment backend service
     * @param investorMappingService               the investor mapping service to retrieve the entity identifier associated with the current user
     */
    public DefaultPaymentAssignmentService(String paymentAssignmentBackendServiceBeanName, InvestorMappingService investorMappingService)
    {
        super(paymentAssignmentBackendServiceBeanName);
        this.investorMappingService = investorMappingService;
    }

    @Override
    public void assignPaymentInformation(String serviceId, ServiceType serviceType, String transactionId)
    {
        // Basic parameter checks
        Assert.notNull(serviceId,"Service identifier must not be null.");
        Assert.notNull(serviceType,"Service type informtion must not be null.");
        Assert.notNull(transactionId,"Payment transaction identifier must not be null.");

        // Backend service access
        PaymentAssignmentBackendService backendService = getBackendService();
        // Assign the payment transaction identifier to e-service in the corresponding backend system
        PaymentAssignmentBackendData backendData = backendService.assignPaymentInformation(serviceId, BackendServiceType.valueOf(serviceType.name()), transactionId, investorMappingService.getEntityId());

        // Log potential backend messages
        MessageUtil.logBackendMessages(backendData.getMessages(), LOGGER);
    }

    @Override
    public void assignPaymentInformation(String serviceId, String serviceType, String transactionId)
    {
        // Basic parameter checks
        Assert.notNull(serviceId,"Service identifier must not be null.");
        Assert.notNull(serviceType,"Service type informtion must not be null.");
        Assert.notNull(transactionId,"Payment transaction identifier must not be null.");

        // Backend service access
        PaymentAssignmentBackendService backendService = getBackendService();

        // Assign the payment transaction identifier to e-service in the corresponding backend system
        PaymentAssignmentBackendData backendData = backendService.assignPaymentInformation(serviceId, serviceType, transactionId, investorMappingService.getEntityId());

        // Log potential backend messages
        MessageUtil.logBackendMessages(backendData.getMessages(), LOGGER);
    }
}
