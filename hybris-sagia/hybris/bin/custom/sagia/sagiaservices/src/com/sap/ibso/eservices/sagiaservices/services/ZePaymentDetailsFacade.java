package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.sagiaservices.data.payment.SalesOrderPayment;
import com.sap.ibso.eservices.sagiaservices.services.payment.PaymentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.payment.PaymentService;

import java.util.Collection;

/**
 * ZePaymentDetailsFacade
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ZePaymentDetailsFacade {
    private PaymentService paymentService;
    private PaymentDetailsService paymentDetailsService;

    /**
     * setPaymentService
     * @param paymentService paymentService
     */
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * getPaymentService
     * @return PaymentService
     */
    public PaymentService getPaymentService() {
        return paymentService;
    }

    /**
     * getPayments
     * @return - The list of all payments from CRM
     */
    public Collection<SalesOrderPayment> getPayments() {
        return paymentService.getPayments();
    }


    /**
     * getPayment
     * @param id id
     * @return - A payment entity by its id
     */
    public SalesOrderPayment getPayment(String id) {
        return paymentDetailsService.getPayment(id);
    }

    /**
     * setPaymentDetailsService
     * @param paymentDetailsService paymentDetailsService
     */
    public void setPaymentDetailsService(PaymentDetailsService paymentDetailsService) {
        this.paymentDetailsService = paymentDetailsService;
    }

    /**
     * getPaymentDetailsService
     * @return PaymentDetailsService
     */
    public PaymentDetailsService getPaymentDetailsService() {
        return paymentDetailsService;
    }
}
