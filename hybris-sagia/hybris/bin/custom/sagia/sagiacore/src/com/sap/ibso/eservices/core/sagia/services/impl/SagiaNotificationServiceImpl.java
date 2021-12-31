/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.core.sagia.services.impl;


import com.sap.ibso.eservices.core.event.OpportunityCreatedApproverEvent;
import com.sap.ibso.eservices.core.event.OpportunityCreatedEvent;
import com.sap.ibso.eservices.core.event.OpportunityUpdatedEvent;
import com.sap.ibso.eservices.core.event.UserCreatedEvent;
import com.sap.ibso.eservices.core.sagia.services.SagiaNotificationService;
import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketService;
import org.apache.log4j.Logger;
import org.fest.util.Collections;

import javax.annotation.Resource;
import java.util.List;


public class SagiaNotificationServiceImpl implements SagiaNotificationService {
    private static final Logger LOG = Logger.getLogger(SagiaNotificationServiceImpl.class);

    @Resource
    private CMSSiteService cmsSiteService;
    @Resource
    private BaseStoreService baseStoreService;
    @Resource
    private CommonI18NService commonI18NService;
    @Resource
    private EventService eventService;
    @Resource
    private SagiaUserService userService;
    @Resource
    private TicketService ticketService;

    @Override
    public void sendUserCreatedNotification(final B2BCustomerModel b2BCustomerModel, final String userRole, final String initialPassword) {
        LOG.info(String.format("Start Sending User Created Email to User # %s", b2BCustomerModel.getUserNameEmail()));
        try {
            UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
            userCreatedEvent.setSite(cmsSiteService.getCurrentSite());
            userCreatedEvent.setBaseStore(baseStoreService.getCurrentBaseStore());
            userCreatedEvent.setCustomer(b2BCustomerModel);
            userCreatedEvent.setLanguage(b2BCustomerModel.getSessionLanguage());
            userCreatedEvent.setCurrency(commonI18NService.getCurrentCurrency());
            userCreatedEvent.setRole(userRole);
            userCreatedEvent.setInitialPassword(initialPassword);
            eventService.publishEvent(userCreatedEvent);
        } catch (Exception exception) {
            LOG.error(String.format("Not able to send User Created Email to User# %s", b2BCustomerModel.getUserNameEmail()));
        }
    }

    @Override
    public void sendOpportunityCreatedNotificationToUser(final CsTicketModel csTicket) {
        LOG.info(String.format("Start Sending Opportunity Created email to User Ticket# %s", csTicket.getTicketID()));
        try {
            OpportunityCreatedEvent opportunityCreatedEvent = new OpportunityCreatedEvent();
            opportunityCreatedEvent.setSite(cmsSiteService.getCurrentSite());
            opportunityCreatedEvent.setBaseStore(baseStoreService.getCurrentBaseStore());
            opportunityCreatedEvent.setCustomer((CustomerModel) csTicket.getCustomer());
            opportunityCreatedEvent.setLanguage(csTicket.getCustomer().getSessionLanguage());
            opportunityCreatedEvent.setCurrency(commonI18NService.getCurrentCurrency());
            opportunityCreatedEvent.setOpportunityId(csTicket.getTicketID());
            eventService.publishEvent(opportunityCreatedEvent);
        } catch (Exception exception) {
            LOG.error(String.format("Not able to Send Opportunity Created email to User Ticket# %s", csTicket.getTicketID()));
        }
    }

    @Override
    public void sendOpportunityCreatedNotificationToApprover(final CsTicketModel csTicket) {
        LOG.info(String.format("Start Sending Opportunity Created email to Approver Ticket# %s", csTicket.getTicketID()));
        try {
            B2BUnitModel unitModel = ((B2BCustomerModel) csTicket.getCustomer()).getDefaultB2BUnit();
            List<String> approversEmail = userService.getApproversEmail(unitModel);
            if (!Collections.isEmpty(approversEmail)) {
                OpportunityCreatedApproverEvent opportunityApproverEvent = new OpportunityCreatedApproverEvent();
                opportunityApproverEvent.setSite(cmsSiteService.getCurrentSite());
                opportunityApproverEvent.setBaseStore(baseStoreService.getCurrentBaseStore());
                opportunityApproverEvent.setCustomer((CustomerModel) csTicket.getCustomer());
                opportunityApproverEvent.setLanguage(csTicket.getCustomer().getSessionLanguage());
                opportunityApproverEvent.setCurrency(commonI18NService.getCurrentCurrency());
                opportunityApproverEvent.setOpportunityId(csTicket.getTicketID());
                opportunityApproverEvent.setApproversEmail(approversEmail);
                eventService.publishEvent(opportunityApproverEvent);
            } else {
                LOG.error("No Approver Exists,can not send email");
            }
        } catch (Exception exception) {
            LOG.info(String.format("Not able to Send Opportunity Created email to Approver Ticket# %s", csTicket.getTicketID()));
        }
    }

    @Override
    public void sendOpportunityUpdateNotificationToUser(final String ticketId) {
        LOG.info(String.format("Start Sending Opportunity Update email for ticket# %s", ticketId));
        CsTicketModel csTicket = ticketService.getTicketForTicketId(ticketId);
        try {
            OpportunityUpdatedEvent opportunityUpdateEvent = new OpportunityUpdatedEvent();
            opportunityUpdateEvent.setSite(cmsSiteService.getCurrentSite());
            opportunityUpdateEvent.setBaseStore(baseStoreService.getCurrentBaseStore());
            opportunityUpdateEvent.setCustomer((CustomerModel) csTicket.getCustomer());
            opportunityUpdateEvent.setLanguage(csTicket.getCustomer().getSessionLanguage());
            opportunityUpdateEvent.setCurrency(commonI18NService.getCurrentCurrency());
            opportunityUpdateEvent.setOpportunityId(csTicket.getTicketID());
            opportunityUpdateEvent.setCurrentState(csTicket.getState().getCode());
            eventService.publishEvent(opportunityUpdateEvent);
        } catch (Exception exception) {
            LOG.info(String.format("Not able to Send Opportunity Rejected email for ticket# %s", ticketId));
        }
    }

}