package com.sap.ibso.eservices.core.sagia.services;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;

/**
 * Provides access to the Changing Email Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaChangingEmailService {

    /**
     * Updates the email for a customer
     * @param customerModel - Customer for which the email is updated
     * @throws ModelSavingException - Exception thrown if save fails
     */
    void updateEmail(CustomerModel customerModel) throws ModelSavingException;
}
