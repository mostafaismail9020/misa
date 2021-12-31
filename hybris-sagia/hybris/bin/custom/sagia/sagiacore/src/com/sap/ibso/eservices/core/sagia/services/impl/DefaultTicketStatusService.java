package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.sagia.services.TicketStatusService;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketException;
import de.hybris.platform.ticket.strategies.TicketUpdateStrategy;
import de.hybris.platform.workflow.WorkflowProcessingService;
import de.hybris.platform.workflow.WorkflowService;
import de.hybris.platform.workflow.WorkflowTemplateService;
import de.hybris.platform.workflow.enums.WorkflowActionStatus;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;
import de.hybris.platform.workflow.model.WorkflowModel;
import de.hybris.platform.workflow.model.WorkflowTemplateModel;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Collection;

public class DefaultTicketStatusService implements TicketStatusService {

	private static final Logger LOG = Logger.getLogger(DefaultTicketStatusService.class);

	private static final String OPPORTUNITY_TICKET_STATUS_WORKFLOW = "OpportunityTicketStatusWorkflow";

	private static final String INFORMATION_COMPLETE_ACTION = "Is Information Complete?";
	private static final String IS_VALID_OPPORTUNITY_ACTION = "Is Valid Opportunity?";
	private static final String APPROVE_OPPORTUNITY_ACTION = "Aprove Opportunity?";
	private static final String INVALID_OPPORTUNITY_JOB = "InvalidOpportunityJob";
	private static final String REJECT_OPPORTUNITY_JOB = "RejectOpportunityJob";
	private static final String APPROVED_BY_MARKETING = "Approved by Marketing?";
	private static final String IS_PUBLISHED_TO_INVEST_SAUDI = "Is Published to InvestSaudi?";

	private static final String INFORMATION_COMPLETE_CRON_JOB = "Information Complete CronJob";
	private static final String INFORMATION_INCOMPLETE_CRON_JOB = "Information Incomplete CronJob";

	private static final String VALID_OPPORTUNTITY_CRON_JOB = "Valid Opportuntity CronJob";
	private static final String INVALID_OPPORTUNITY_CRON_JOB = "Invalid Opportunity CronJob";

	private static final String APPROVE_OPPORTUNTITY_CRON_JOB = "Approve Opportuntity CronJob";
	private static final String REJECT_OPPORTUNTITY_CRON_JOB = "Reject Opportuntity CronJob";

	private static final String APPROVED_BY_MARKETING_CRON_JOB = "Approved by Marketing CronJob";
	public static final String PUBLISH_TO_INVEST_SAUDI_CRON_JOB = "Publish to InvestSaudi CronJob";


	private WorkflowService workflowService;
	private WorkflowTemplateService workflowTemplateService;
	private WorkflowProcessingService workflowProcessingService;
	private UserService userService;
	private ModelService modelService;

	@Resource
	private TicketUpdateStrategy ticketUpdateStrategy;

	@Override
	public void startTicketStatusWorkflow(final CsTicketModel currentTicket, EmployeeModel csAgentSelected)
	{
		// start the opportunity workflow for the current ticket
		final WorkflowTemplateModel workflowTemplate = this.workflowTemplateService.getWorkflowTemplateForCode(OPPORTUNITY_TICKET_STATUS_WORKFLOW);

		final WorkflowModel workflow = this.workflowService.createWorkflow(workflowTemplate, currentTicket, userService.getAdminUser());
		modelService.save(workflow);

		currentTicket.setWorkflowCode(workflow.getCode());
		modelService.save(currentTicket);

		for (final WorkflowActionModel action : workflow.getActions())
		{
			//set the principal assigned to handle this workflow actions based on assigned agent choosed in ticket on backoffice
			if(csAgentSelected != null){
				action.setPrincipalAssigned(csAgentSelected);
			}

			modelService.save(action);
		}

		this.workflowProcessingService.startWorkflow(workflow);
		workflow.setStatus(CronJobStatus.RUNNING);
		modelService.save(workflow);

	}

	public void activateInitialWorkflowAction(CsTicketModel ticket) {
		WorkflowModel workflowFromTicket = workflowService.getWorkflowForCode(ticket.getWorkflowCode());

		if (LOG.isDebugEnabled())
		{
			LOG.debug(String.format("Activating initial decision from workflow '%s'.", workflowFromTicket.getName()));
		}

		for (final WorkflowActionModel action : workflowFromTicket.getActions()) {
			if (action.getName().equals(INFORMATION_COMPLETE_ACTION)) {
				workflowProcessingService.activate(action);
			}
		}
	}

	/**
	 * Public method use to decide workflow and navigate throught the next node.
	 * This method can be used from opportunity tickets cronjobs, for example, that triggers a decision.
	 * @param workflow
	 */
	@Override
	public void decideAndGoToNext(WorkflowModel workflow) {
		decideAndGoToNext(workflow, null);
	}

	/**
	 * Public method use to decide workflow and navigate throught the next node.
	 * This method can be used from opportunity tickets cronjobs, for example, that triggers a decision.
	 * @param workflow
	 * @param newState
	 */
	@Override
	public void decideAndGoToNext(WorkflowModel workflow, CsTicketState newState)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(String.format("Updating decisions from workflow '%s'.", workflow.getName()));
		}


		if(newState != null){

			CsTicketState newTicketState = Enum.valueOf(CsTicketState.class, newState.getCode().toUpperCase());

			switch(newTicketState){
				case REVIEWED:
					resolveIsInformationCompleteTransition(workflow);
					break;
				case SUBCOMMITTEEREVIEW:
					resolveReviewedToSCReviewTransition(workflow);
					break;
				case DISCARTED:
				case SUBCOMMITTEEREJECTED:
					resolveToTerminatedTransition(workflow);
					break;
				case MISAFINALREVIEW:
					resolveSCReviewToMisaFinalReviewTransition(workflow);
					break;
				case READY:
					resolveMisaFinalReviewToReadyTransition(workflow);
					break;
				case PUBLISHED:
					resolveMisReadyToPublishedTransition(workflow);
					break;
			}
		}
	}

	private void resolveMisReadyToPublishedTransition(WorkflowModel workflow) {

		for (final WorkflowActionModel actionFound : workflow.getActions())
		{
			for (WorkflowDecisionModel decision : actionFound.getDecisions()){

				if(actionFound.getName().equals(IS_PUBLISHED_TO_INVEST_SAUDI) && actionFound.getStatus().equals(WorkflowActionStatus.IN_PROGRESS)){

					workflowProcessingService.decideAction(actionFound, decision);
					modelService.save(actionFound);

					//TODO ends workflow
					workflowProcessingService.endWorkflow(workflow);
					modelService.save(workflow);
				}
			}

			//terminate cronjobs to this transition
			if (actionFound.getName().equalsIgnoreCase(PUBLISH_TO_INVEST_SAUDI_CRON_JOB))
			{
				actionFound.setStatus(WorkflowActionStatus.TERMINATED);
				modelService.save(actionFound);
			}
		}
	}

	private void resolveMisaFinalReviewToReadyTransition(WorkflowModel workflow) {

		for (final WorkflowActionModel actionFound : workflow.getActions())
		{
			for (WorkflowDecisionModel decision : actionFound.getDecisions()){

				if(actionFound.getName().equals(APPROVED_BY_MARKETING) && actionFound.getStatus().equals(WorkflowActionStatus.IN_PROGRESS)){
					workflowProcessingService.decideAction(actionFound, decision);
					modelService.save(actionFound);
				}
			}

			//terminate cronjobs to this transition
			if (actionFound.getName().equalsIgnoreCase(APPROVED_BY_MARKETING_CRON_JOB))
			{
				actionFound.setStatus(WorkflowActionStatus.TERMINATED);
				modelService.save(actionFound);
			}

			if (actionFound.getName().equals(IS_PUBLISHED_TO_INVEST_SAUDI) && actionFound.getStatus().equals(WorkflowActionStatus.PENDING)) {
				workflowProcessingService.activate(actionFound);
				modelService.save(actionFound);
			}
		}

	}


	private void resolveSCReviewToMisaFinalReviewTransition(WorkflowModel workflow) {

		for (final WorkflowActionModel actionFound : workflow.getActions())
		{
			for (WorkflowDecisionModel decision : actionFound.getDecisions()){

				if(actionFound.getName().equals(APPROVE_OPPORTUNITY_ACTION) && actionFound.getStatus().equals(WorkflowActionStatus.IN_PROGRESS)){
					workflowProcessingService.decideAction(actionFound, decision);
					modelService.save(actionFound);
				}
			}

			//terminate cronjobs to this transition
			if (actionFound.getName().equalsIgnoreCase(APPROVE_OPPORTUNTITY_CRON_JOB) || actionFound.getName().equalsIgnoreCase(REJECT_OPPORTUNTITY_CRON_JOB))
			{
				actionFound.setStatus(WorkflowActionStatus.TERMINATED);
				modelService.save(actionFound);
			}

			if (actionFound.getName().equals(APPROVED_BY_MARKETING) && actionFound.getStatus().equals(WorkflowActionStatus.PENDING)) {
				workflowProcessingService.activate(actionFound);
				modelService.save(actionFound);
			}
		}

	}

	private void resolveReviewedToSCReviewTransition(WorkflowModel workflow) {

		for (final WorkflowActionModel actionFound : workflow.getActions())
		{
			for (WorkflowDecisionModel decision : actionFound.getDecisions()){

				//conclude current workflow node
				if(actionFound.getName().equals(IS_VALID_OPPORTUNITY_ACTION) && actionFound.getStatus().equals(WorkflowActionStatus.IN_PROGRESS)){
					workflowProcessingService.decideAction(actionFound, decision);
					modelService.save(actionFound);
				}
			}

			//terminate cronjobs to this transition
			if (actionFound.getName().equalsIgnoreCase(VALID_OPPORTUNTITY_CRON_JOB) || actionFound.getName().equalsIgnoreCase(INVALID_OPPORTUNITY_CRON_JOB))
			{
				actionFound.setStatus(WorkflowActionStatus.TERMINATED);
				modelService.save(actionFound);
			}

			//start next node
			if (actionFound.getName().equals(APPROVE_OPPORTUNITY_ACTION) && actionFound.getStatus().equals(WorkflowActionStatus.PENDING)) {
				workflowProcessingService.activate(actionFound);
				modelService.save(actionFound);
			}
		}
	}

	/**
	 * Method used internally to resolve the information Complete Transition step and activate next node of workflow.
	 * @param workflow
	 */
	private void resolveIsInformationCompleteTransition(WorkflowModel workflow) {

		for (final WorkflowActionModel actionFound : workflow.getActions())
		{
			for (WorkflowDecisionModel decision : actionFound.getDecisions()){

				//conclude current workflow node
				if(actionFound.getName().equals(INFORMATION_COMPLETE_ACTION) && actionFound.getStatus().equals(WorkflowActionStatus.IN_PROGRESS)){
					workflowProcessingService.decideAction(actionFound, decision);
					modelService.save(actionFound);
				}
			}

			//terminate cronjobs to this transition
			if (actionFound.getName().equalsIgnoreCase(INFORMATION_COMPLETE_CRON_JOB) || actionFound.getName().equalsIgnoreCase(INFORMATION_INCOMPLETE_CRON_JOB))
			{
				actionFound.setStatus(WorkflowActionStatus.TERMINATED);
				modelService.save(actionFound);
			}

			//start next node
			if (actionFound.getName().equals(IS_VALID_OPPORTUNITY_ACTION) && actionFound.getStatus().equals(WorkflowActionStatus.PENDING)) {
				workflowProcessingService.activate(actionFound);
				modelService.save(actionFound);
			}
		}
	}

	private void resolveToTerminatedTransition(WorkflowModel workflow) {

		if (LOG.isDebugEnabled())
		{
			LOG.debug(String.format("resolveToTerminatedTransition '%s'.", workflow.getName()));
		}

		for (final WorkflowActionModel actionFound : workflow.getActions())
		{
			if (actionFound.getTemplate().getCode().equals(INVALID_OPPORTUNITY_JOB) || actionFound.getTemplate().getCode().equals(REJECT_OPPORTUNITY_JOB))
			{
				updateWorkflowToTerminateNode(workflow);
			}
		}

		endsWorkflow(workflow);

	}

	private void endsWorkflow(WorkflowModel workflow) {
		workflow.setStatus(CronJobStatus.FINISHED);
		workflowProcessingService.endWorkflow(workflow);
		modelService.save(workflow);
	}


	public void updateFromMisaInitialReviewToReviewed (CsTicketModel currentTicket) {

		if(currentTicket.getState().equals(CsTicketState.MISAINITIALREVIEW)){

			try {
				ticketUpdateStrategy.setTicketState(currentTicket, CsTicketState.REVIEWED);
				modelService.save(currentTicket);

			} catch (TicketException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateFromMisaInitialReviewToInformationRequested (CsTicketModel currentTicket){

		if(currentTicket.getState().equals(CsTicketState.MISAINITIALREVIEW)){
			try {
				//update ticket & event board
				ticketUpdateStrategy.setTicketState(currentTicket, CsTicketState.INFORMATIONREQUESTED);
				modelService.save(currentTicket);
			} catch (TicketException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateFromReviewedToSCReview (CsTicketModel currentTicket) {

		if(currentTicket.getState().equals(CsTicketState.REVIEWED)){

			try {
				ticketUpdateStrategy.setTicketState(currentTicket, CsTicketState.SUBCOMMITTEEREVIEW);
				modelService.save(currentTicket);

			} catch (TicketException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateFromReviewedToDiscarded(CsTicketModel currentTicket)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(String.format("Update Action to Discarded workflow '%s'.", currentTicket.getWorkflowCode()));
		}

		try
		{
			ticketUpdateStrategy.setTicketState(currentTicket, CsTicketState.DISCARTED);
			modelService.save(currentTicket);
		}
		catch (TicketException e)
		{
			e.printStackTrace();
		}
	}

	private void updateWorkflowToTerminateNode (WorkflowModel workflow)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(String.format("Updating workflow '%s'.", workflow.getName()));
		}

		for (WorkflowActionModel action : workflow.getActions())
		{
			if (!action.getStatus().getCode().equalsIgnoreCase(WorkflowActionStatus.COMPLETED.name()))
			{
				action.setStatus(WorkflowActionStatus.TERMINATED);
				modelService.save(action);
			}
		}

		return;
	}

	@Override
	public void updateFromSCReviewToMisaFinalReview(CsTicketModel currentTicket) {

		if(currentTicket.getState().equals(CsTicketState.SUBCOMMITTEEREVIEW)){

			try {
				ticketUpdateStrategy.setTicketState(currentTicket, CsTicketState.MISAFINALREVIEW);
				modelService.save(currentTicket);

			} catch (TicketException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateFromSCReviewToSCRejected(CsTicketModel currentTicket) {

		if (LOG.isDebugEnabled())
		{
			LOG.debug(String.format("Update Action to Discarded workflow '%s'.", currentTicket.getWorkflowCode()));
		}

		try
		{
			ticketUpdateStrategy.setTicketState(currentTicket, CsTicketState.SUBCOMMITTEEREJECTED);
			modelService.save(currentTicket);
		}
		catch (TicketException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void updateFromMisaFinalReviewToReady(CsTicketModel currentTicket) {

		if(currentTicket.getState().equals(CsTicketState.MISAFINALREVIEW)){

			try {
				ticketUpdateStrategy.setTicketState(currentTicket, CsTicketState.READY);
				modelService.save(currentTicket);

			} catch (TicketException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateFromReadyToPublished(CsTicketModel currentTicket) {

		if(currentTicket.getState().equals(CsTicketState.READY)){

			try {
				ticketUpdateStrategy.setTicketState(currentTicket, CsTicketState.PUBLISHED);
				modelService.save(currentTicket);

			} catch (TicketException e) {
				e.printStackTrace();
			}
		}
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setWorkflowProcessingService(WorkflowProcessingService workflowProcessingService) {
		this.workflowProcessingService = workflowProcessingService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void setWorkflowTemplateService(WorkflowTemplateService workflowTemplateService) {
		this.workflowTemplateService = workflowTemplateService;
	}

	public void setTicketUpdateStrategy(TicketUpdateStrategy ticketUpdateStrategy) {
		this.ticketUpdateStrategy = ticketUpdateStrategy;
	}
}
