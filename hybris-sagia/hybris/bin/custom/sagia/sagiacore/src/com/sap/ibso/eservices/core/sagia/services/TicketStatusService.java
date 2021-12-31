/**
 * 
 */
package com.sap.ibso.eservices.core.sagia.services;

import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.workflow.model.WorkflowModel;

public interface TicketStatusService
{

	void startTicketStatusWorkflow(final CsTicketModel csTicketModel, EmployeeModel csAgentSelected);

	void activateInitialWorkflowAction(CsTicketModel ticket);

	void decideAndGoToNext(WorkflowModel actionModel);
	void decideAndGoToNext(WorkflowModel actionModel, CsTicketState newState);

	void updateFromMisaInitialReviewToReviewed(CsTicketModel currentTicket);

	void updateFromMisaInitialReviewToInformationRequested (CsTicketModel currentTicket);

	void updateFromReviewedToDiscarded (CsTicketModel currentTicket);

	void updateFromReviewedToSCReview(CsTicketModel currentTicket);

    void updateFromSCReviewToMisaFinalReview(CsTicketModel currentTicket);

	void updateFromSCReviewToSCRejected(CsTicketModel currentTicket);

    void updateFromMisaFinalReviewToReady(CsTicketModel currentTicket);

    void updateFromReadyToPublished(CsTicketModel currentTicket);
}
