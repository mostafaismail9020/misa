package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.sagia.services.SagiaChangingEmailService;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

/**
 * Default implementation of changing email service
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaChangingEmailService extends DefaultCustomerAccountService implements SagiaChangingEmailService{


    /**
     * Updates the email of a customer
     *
     * @param customerModel - The customer for which the email is changed
     * @throws ModelSavingException - Exception thrown if save fails
     */
    public void updateEmail(CustomerModel customerModel) throws ModelSavingException {

        validateParameterNotNullStandardMessage("customerModel", customerModel);
        internalSaveCustomer(customerModel);

    }

    @Override
    public void internalSaveCustomer(final CustomerModel customerModel) throws ModelSavingException
    {
            getModelService().save(customerModel);

    }
}
