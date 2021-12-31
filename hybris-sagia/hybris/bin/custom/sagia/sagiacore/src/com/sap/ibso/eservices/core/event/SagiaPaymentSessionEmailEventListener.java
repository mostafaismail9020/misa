package com.sap.ibso.eservices.core.event;

import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.mail.MailUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class SagiaPaymentSessionEmailEventListener extends AbstractEventListener<SagiaPaymentSessionEmailEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(SagiaPaymentSessionEmailEventListener.class);
    public static final java.lang.String PAYMENT_SESSION_NOTIFICATION_RECIPIENTS = "payment.session.notification.recipients";

    @Override
    protected void onEvent(SagiaPaymentSessionEmailEvent event) {
        // Sending email;
        try {
            this.processEmail(event);
        } catch (EmailException e) {
            LOG.error("Problem sending sagia payment api error email. Note that commons.mail.send() can block if behind a firewall/proxy. Error message: {}",
                    e.getMessage());
        }
    }

    private void processEmail(final SagiaPaymentSessionEmailEvent event) throws EmailException {
        final String subject = new StringBuilder("[Customer: ")
                .append(event.getCustomer().getCustomerID()).append("] ")
                .append("Sagia Payment error event").toString();
        Email email = MailUtils.getPreConfiguredEmail();
        LOG.debug(subject);
        email.setSubject(subject);
        email.setMsg(getEmailMessageFromEvent(event));
        email.setTLS(true);
        this.addEmailRecipients(email);
        email.send();
    }

    private void addEmailRecipients(Email email) throws EmailException {
        final String recipientConfiguration = Config.getString(PAYMENT_SESSION_NOTIFICATION_RECIPIENTS, null);
        final String[] notificationRecipients = StringUtils.split(recipientConfiguration, ',');
        if (Objects.nonNull(notificationRecipients) && notificationRecipients.length > 0) {
            email.addTo(notificationRecipients);
        } else {
            StringBuilder eMessage = new StringBuilder("Property ")
                    .append(PAYMENT_SESSION_NOTIFICATION_RECIPIENTS)
                    .append(" is not configured, session payment error email cannot be sent.");
            throw new EmailException(eMessage.toString());
        }
    }


    private String getEmailMessageFromEvent(SagiaPaymentSessionEmailEvent event) {
        final String lineSeparator = System.getProperty("line.separator");
        StringBuilder emailMessage = new StringBuilder();
        emailMessage.append("3DSecureID value not found in the session which expected to be in the session, information from database: ")
                .append(lineSeparator).append(lineSeparator)
                .append("Customer id: ").append(event.getCustomer().getCustomerID())
                .append(lineSeparator)
                .append("Secure3DId: ").append(event.getSecure3DId())
                .append(lineSeparator)
                .append("Payment session information: ").append(lineSeparator).append(event.getPaymentMap());
        return emailMessage.toString();
    }

}
