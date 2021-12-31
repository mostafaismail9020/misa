package com.sap.ibso.eservices.core.strategies;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.sagia.services.TicketStatusService;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.events.model.CsTicketEventModel;
import de.hybris.platform.ticket.model.CsAgentGroupModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketException;
import de.hybris.platform.ticket.strategies.impl.DefaultTicketUpdateStrategy;
import de.hybris.platform.workflow.WorkflowService;
import de.hybris.platform.workflow.model.WorkflowModel;

public class  DefaultSagiaTicketUpdateStrategy extends DefaultTicketUpdateStrategy {

	private TicketStatusService ticketStatusService;
	private WorkflowService workflowService;

	private static final String DISCARDED = "InvalidOpportunityJob";

	@Override
	protected void preChangeTicketGroup(CsTicketModel ticket, CsAgentGroupModel group) throws TicketException {
		//super.preChangeTicketGroup(ticket, group);
		ticket.setAssignedAgent(null);
	}
	
	@Override
	public CsTicketEventModel assignTicketToAgent(CsTicketModel ticket, EmployeeModel agent) throws TicketException {

		// in Opportunity Flow: first status is UPLOADED and it's being assigned to some CS Agent, change to "MISA INITIAL REVIEW"
		if(ticket.getState().equals(CsTicketState.UPLOADED) && agent != null){

			ticketStatusService.startTicketStatusWorkflow(ticket, agent);

			ticket.setState(CsTicketState.MISAINITIALREVIEW);
			getModelService().save(ticket);
		}

		return super.assignTicketToAgent(ticket, agent);
	}

	@Override
	public void setTicketState(CsTicketModel ticket, CsTicketState newState, String note) throws TicketException {
		super.setTicketState(ticket, newState, "");

		manageOpportunityTicketWorkflow(ticket, newState);
	}


	private void manageOpportunityTicketWorkflow(CsTicketModel ticket, CsTicketState newState) {

		//only enters in workflow when its not a service request ticket (opportuntiy only)
		if (ticket.getConfiguration() != null && !ticket.getConfiguration().isIsServiceRequest()) {

			WorkflowModel workflowFromTicket = workflowService.getWorkflowForCode(ticket.getWorkflowCode());

			if(workflowFromTicket != null) {
				ticketStatusService.decideAndGoToNext(workflowFromTicket, newState);
			}
		}
	}


	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void setTicketStatusService(TicketStatusService ticketStatusService) {
		this.ticketStatusService = ticketStatusService;
	}
}
