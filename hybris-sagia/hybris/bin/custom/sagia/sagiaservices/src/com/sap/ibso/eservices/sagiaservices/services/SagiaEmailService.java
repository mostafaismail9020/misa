package com.sap.ibso.eservices.sagiaservices.services;

import de.hybris.platform.acceleratorservices.email.impl.DefaultEmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SagiaEmailService
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaEmailService extends DefaultEmailService {
    private static final Logger LOG = LoggerFactory.getLogger(SagiaEmailService.class);

    /**
     * @param message message
     * @param e e
     */
    protected void logInfo(final EmailMessageModel message, final EmailException e) {
        LOG.error("Could not send e-mail pk [" + message.getPk() + "] subject [" + message.getSubject() + "] cause: " + e.getMessage());
        LOG.error(e.getMessage(), e);
    }
}
