package com.sap.ibso.eservices.bol.price;

public interface SalesOrderBackendService {

    /**
     * Updates a Sales Order in the CRM once a payment has been completed.
     * The sales order should be updated with the transaction id of the payment,
     * which is contained in the param POJO and mark it as already payed.
     * @param salesOrderParam param POJO containing the sales order id and transaction id
     *                        among other payment data.
     * @return <code>true</code> if the sales order has been successfully updated in CRM,
     *         <code>false</code> otherwise.
     */
    boolean afterPaymentUpdate(SalesOrderParam salesOrderParam);
}
