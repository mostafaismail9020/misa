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
package com.investsaudi.workflows.actions.ticket;

import com.investsaudi.workflows.actions.AbstractAutomatedWorkflowTemplateJob;
import com.sap.ibso.eservices.core.sagia.services.TicketStatusService;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.workflow.enums.WorkflowActionStatus;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;
import org.apache.log4j.Logger;

import javax.annotation.Resource;


/**
 * Action called when a information is incomplete, verified by a Cs Agent
 */
public class InvalidOpportunityAutomatedWorkflowTemplateJob extends AbstractAutomatedWorkflowTemplateJob
{

	private static final Logger LOG = Logger.getLogger(InvalidOpportunityAutomatedWorkflowTemplateJob.class);

	@Resource
	private TicketStatusService ticketStatusService;


	@Override
	public WorkflowDecisionModel perform(final WorkflowActionModel workflowAction)
	{
		final CsTicketModel currentTicket = getModelOfType(workflowAction, CsTicketModel.class);

		LOG.info("Updating status: " + currentTicket.getState() + " from workflow: " + getClass());

		ticketStatusService.updateFromReviewedToDiscarded(currentTicket);

		return null;

	}

	public void setTicketStatusService(TicketStatusService ticketStatusService) {
		this.ticketStatusService = ticketStatusService;
	}
}