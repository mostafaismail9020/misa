package com.sap.ibso.eservices.sagiaservices.services.b2b;

import de.hybris.platform.b2b.model.B2BCustomerModel;

import java.util.Set;

/**
 * The interface Sagia b 2 b customer.
 */
public interface SagiaB2BCustomer {
    /**
     * Gets all b 2 b customers.
     *
     * @return the all b 2 b customers
     */
    Set<B2BCustomerModel> getB2BCustomersToDisable();
}
