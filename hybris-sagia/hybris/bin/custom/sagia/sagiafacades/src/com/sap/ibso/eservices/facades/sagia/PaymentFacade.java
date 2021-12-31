package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.core.model.SagiaPaymentModel;
import com.sap.ibso.eservices.facades.data.account.SuccessfulPaymentData;
import com.sap.ibso.eservices.facades.data.payment.PaymentData;

import java.util.List;

/**
 * Provides access to PaymentFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface PaymentFacade {

    /**
     * get Payments
     * @return - Payments DTOs for the dashboard
     */

    List<PaymentData> getPayments();

    /**
     * get Payment
     * @param id - The id of the payment entity
     * @return - Payment DTO for the dashboard retrieved by id
     */
    PaymentData getPayment(String id);

    /**
     * Registers a successful payment transaction in Hybris. It actually saves
     * two objects in the database, one for each sale order(CRM) associated
     * with the payment. Each of those two items stores if the sales order
     * was already updated in the CRM or not.
     * @param successfulPaymentData POJO containing payment data after success.
     */
    void savePayment(SuccessfulPaymentData successfulPaymentData);

    /**
     * Using a RFC, this updates the sales orders in CRM after a payment
     * was sucessfully completed. There are two sales orders to be updated
     * for License Application process.
     * @param salesOrder1 - First sales order.
     */
    void updateLicenseApplicationSaleOrdersInCRM(SagiaPaymentModel salesOrder1);
}
