package com.sap.ibso.eservices.sagiaservices.price;

/**
 * Provides access to e-services related sales orders.
 */
public interface SalesOrderService {

    /**
     * Updates a Sales Order in the CRM once the payment has been completed
     * in Hybris. Because the initial update might fail,
     * {@link com.sap.ibso.eservices.sagiaservices.jobs.SagiaUpdateSaleOrderJob}
     * is a cronjob which periodically uses this method to update already payed
     * Sales Orders in CRM.
     * @param salesOrderParam sales order param POJO containing the sales order id and transaction id
     *                        among other data about the payment.
     * @return <code>true</code> if the sales order has been successfully updated in CRM,
     *         <code>false</code> otherwise.
     */
     boolean afterPaymentUpdate(SalesOrderParam salesOrderParam);
}
