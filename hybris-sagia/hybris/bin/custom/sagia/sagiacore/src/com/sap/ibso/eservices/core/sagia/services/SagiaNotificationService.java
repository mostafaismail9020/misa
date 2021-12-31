package com.sap.ibso.eservices.core.sagia.services;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.ticket.model.CsTicketModel;

/**
 * The Interface SagiaNotificationService.
 * This interface is created to send handle required notifications.
 */
public interface SagiaNotificationService {

    /**
     * Send user created notification.
     *
     * @param b2BCustomerModel the b 2 B customer model
     * @param userRole the user role
     */
    void sendUserCreatedNotification(B2BCustomerModel b2BCustomerModel, String userRole, String initialPassword);


    /**
     * Send opportunity created notification to user.
     *
     * @param csTicket the cs ticket
     */
    void sendOpportunityCreatedNotificationToUser(CsTicketModel csTicket);


    /**
     * Send opportunity created notification to approver.
     *
     * @param csTicket the cs ticket
     */
    void sendOpportunityCreatedNotificationToApprover(CsTicketModel csTicket);

    /**
     * Send opportunity update notification to user.
     *
     * @param ticketId the ticketID
     */
     void sendOpportunityUpdateNotificationToUser(String ticketId);
}
