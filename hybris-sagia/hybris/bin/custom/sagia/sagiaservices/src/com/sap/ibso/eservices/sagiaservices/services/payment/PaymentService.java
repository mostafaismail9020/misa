package com.sap.ibso.eservices.sagiaservices.services.payment;

import com.sap.ibso.eservices.sagiaservices.data.payment.SalesOrderPayment;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * PaymentService
 */
public class PaymentService extends AbstractSagiaService<SalesOrderPayment> {

    /**
     * @return - The list of all payments from CRM
     */
    public Collection<SalesOrderPayment> getPayments()  {
        return super.getCollection(SalesOrderPayment.class);
    }
}
