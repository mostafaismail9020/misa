package com.sap.ibso.eservices.core.sagia.dao.password;

import com.sap.ibso.eservices.core.model.SagiaPaymentModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public interface SagiaPaymentDAO {

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
    SagiaPaymentModel getBySalesOrderId(String salesOrderId);
    
    List<SagiaPaymentModel> getSagiaPaymentsByCustomer(CustomerModel customer);


}
