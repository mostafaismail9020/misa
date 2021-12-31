package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.sagiaservices.data.payment.PaymentDetailsData;

/**
 * SagiaPaymentDetailsFacade
 */
public interface SagiaPaymentDetailsFacade {

    /**
     * Payment details of a new license.
     *
     * @return - Payment details DTO containing requested information about license payment.
     */

    /**
     * Price simulation for apply, amendment, renewal processes.
     * @param serviceType service for which the price simulation is computed.
     * @param qeemah qeemah for which the price simulation is computed.
     * @return price simulation POJO
     */
    PaymentDetailsData requestPaymentDetails(String serviceType, String qeemah);

    /**
     * Only the application process reqires a qeemah to be specified.
     * If there is no qeemah specified, another RFC function will be called.
     * @param serviceType service for which the price simulation is computed.
     * @return price simulation POJO
     */
    default PaymentDetailsData requestPaymentDetails(String serviceType){
        return requestPaymentDetails(serviceType,null);
    }
}
