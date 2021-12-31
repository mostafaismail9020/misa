package com.sap.ibso.eservices.sagiaservices.services.payment;

import com.sap.ibso.eservices.sagiaservices.data.payment.SalesOrderPayment;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * PaymentDetailsService
 */
public class PaymentDetailsService extends AbstractSagiaService<SalesOrderPayment> {

    /**
     * retrieves SalesOrderPayment by id
     * @param id id
     * @return - A payment entity by its id
     */
    public SalesOrderPayment getPayment(String id){
        return super.get(SalesOrderPayment.class,id);
    }
}
