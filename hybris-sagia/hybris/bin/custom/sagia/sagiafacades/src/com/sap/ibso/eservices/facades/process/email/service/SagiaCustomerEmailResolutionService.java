package com.sap.ibso.eservices.facades.process.email.service;

import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerEmailResolutionService;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.util.mail.MailUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

/**
 * SagiaCustomerEmailResolutionService
 */
public class SagiaCustomerEmailResolutionService extends DefaultCustomerEmailResolutionService {

    private static final Logger LOG = Logger.getLogger(SagiaCustomerEmailResolutionService.class);

    @Override
    protected String validateAndProcessEmailForCustomer(final CustomerModel customerModel) {
        validateParameterNotNullStandardMessage("customerModel", customerModel);

        final String email = CustomerType.GUEST.equals(customerModel.getType()) ? StringUtils.substringAfter(
                customerModel.getUserNameEmail(), "|") : customerModel.getUserNameEmail();
        try {
            MailUtils.validateEmailAddress(email, "customer email");
            return email;
        } catch (final EmailException e) {
            LOG.info("Given uid is not appropriate email. Customer PK: " + customerModel.getPk() + " Exception: " + e.getClass().getName(), e);
        }
        return null;
    }
}
