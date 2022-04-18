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
package com.investsaudiportal.scpi.outbound.actions;

import com.investsaudiportal.model.scpi.outbound.process.ScpiOutLeadTicketProcessModel;
import com.investsaudiportal.scpi.outbound.services.ScpiOutboundServiceImpl;

import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.platform.ticket.model.CsTicketModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

/**
 * SendLeadTicketToScpiAction providing a common implementation for the execute method.
 */

public class SendLeadTicketToScpiAction extends AbstractAction<ScpiOutLeadTicketProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(SendLeadTicketToScpiAction.class);

    @Resource(name = "defaultScpiOutboundService")
    ScpiOutboundServiceImpl scpiOutboundService;

    @Override
    public Set<String> getTransitions() {
        return AbstractAction.createTransitions("OK", "NOK");
    }

    @Override
    public String execute(ScpiOutLeadTicketProcessModel scpiOutLeadTicketProcessModel) throws RetryLaterException, Exception {

        try {
            CsTicketModel csTicketModel = scpiOutLeadTicketProcessModel.getCsTicket();
            scpiOutboundService.sendLeadTicket(csTicketModel);
            return("OK");
        }
        catch (Exception e) {
            LOG.error("Error while sending LeadTicket: " + scpiOutLeadTicketProcessModel.getCsTicket().getTicketID()+" to SCPI ",e);
            return("NOK");
        }

    }

}
