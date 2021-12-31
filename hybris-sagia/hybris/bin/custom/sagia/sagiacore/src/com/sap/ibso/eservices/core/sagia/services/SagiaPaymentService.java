package com.sap.ibso.eservices.core.sagia.services;


import com.sap.ibso.eservices.core.model.SagiaPaymentModel;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

/**
 * Service for the Sagia Payments. Payments are saved after the investor received a
 * payment notification and chooses to pay for the License. After the payment is completed,
 * two payment objects are saved into Hybris, associated with two sales order from CRM.
 * When the sales orders are updated into the CRM and marked as already payed, the Hybris
 * objects are modified and set the their status to true.
 */
public interface SagiaPaymentService {

    /**
     * After a payment is made in Hybris, the Sale Orders should be updated in CRM.
     * If an update fails, a cronjob perriodically re-tries to update them.
     * This method retrieves the payments stored in Hybris for which the update
     * in CRM was not completed, no matter what the reason.
     * @return sagia payments that were not updated in CRM.
     */
   List<SagiaPaymentModel> getNotUpdatedPayments();

    /**
     * Retrieving a payment from Hybris by its sales order id.
     * @param salesOrderId - sales order id.
     * @return - Payment model associated with specific sales order id.
     */
    SagiaPaymentModel getBySalesOrder(String salesOrderId);

    /**
     * Verifies if a sales order has associated with it a payment in Hybris.
     * @param salesOrderId - sales order id.
     * @return <code>true</code> if the sales order id has associated a payment
     *         with it, <code>false</code> otherwise.
     */
    boolean isSalesOrderSaved(String salesOrderId);
    
    /**
     * Retrieving a payment from Hybris by its customer.
     * @param customer - user.
     * @return - Payment model associated with specific user.
     */
    List<SagiaPaymentModel> getSagiaPaymentsByCustomer(CustomerModel customer);

    /**
     * Verifies if at least one of the two sales orders are saved in Hybris.
     * @param salesOrderId1 - first sales order.
     * @param salesOrderId2 - second sales order.
     * @return <code>true</code> if at least one sales order exists in Hybris,
     *         <code>false</code> otherwise.
     */
    default boolean isAtLeastOneSalesOrderSaved(String salesOrderId1, String salesOrderId2){
        return isSalesOrderSaved(salesOrderId1) || isSalesOrderSaved(salesOrderId2);
    }
}
