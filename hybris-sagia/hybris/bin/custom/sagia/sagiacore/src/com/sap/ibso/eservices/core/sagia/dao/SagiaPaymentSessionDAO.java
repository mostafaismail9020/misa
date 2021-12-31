package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SagiaPaymentSessionModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Optional;

public interface SagiaPaymentSessionDAO {
    /**
     * @param customerModel
     * @return Sagia payment session for a giving customer.
     */
    Optional<SagiaPaymentSessionModel> getSagiaPaymentSessionByCustomer(CustomerModel customerModel);
}
