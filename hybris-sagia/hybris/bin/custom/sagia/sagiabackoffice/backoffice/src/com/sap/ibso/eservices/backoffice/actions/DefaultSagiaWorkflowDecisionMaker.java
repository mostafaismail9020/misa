package com.sap.ibso.eservices.backoffice.actions;

import static com.hybris.backoffice.workflow.constants.WorkflowNotificationEventTypes.NOTIFICATION_EVENT_WORKFLOW_DECISION;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.zkoss.util.resource.Labels;

import com.hybris.backoffice.widgets.notificationarea.event.NotificationEvent;
import com.hybris.backoffice.workflow.impl.DefaultWorkflowDecisionMaker;
import com.hybris.cockpitng.engine.WidgetInstanceManager;
import com.investsaudi.model.B2BRegistrationModel;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.workflow.WorkflowAttachmentService;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

public class DefaultSagiaWorkflowDecisionMaker extends DefaultWorkflowDecisionMaker {
	
	public final static String REGISTRATION_WORKFLOW = "B2BUserRegistration";
	public final static String REGISTRATION_APPROVAL = "B2BRegistrationApproval";
	public final static String REGISTRATION_APPROVED = "B2BRRegistrationApproved";
	public final static String REGISTRATION_REJECTED = "B2BRRegistrationRejected";
	
	public final static String REGISTRATION_REJECTED_MISSING_REJECT_REASON =  "notifications.missing.reject.reason";
	public final static String REGISTRATION_MISSING_DEFAULT_UNIT =  "notifications.missing.default.b2bunit";
	

	@Override
	public void makeDecision(WorkflowActionModel workflowAction, WorkflowDecisionModel selectedDecision,
			WidgetInstanceManager widgetInstanceManager) {

		// Don't do anything special if we are not addressing the registration workflow
		if (!isB2BRegistrationWorkflowAction(workflowAction)) {
			super.makeDecision(workflowAction, selectedDecision, widgetInstanceManager);
			return;
		}

		if (isRejectedDecision(selectedDecision) && !validateRejectDecision(workflowAction)) {
			getNotificationService().clearNotifications(getNotificationSource(widgetInstanceManager));
			getNotificationService().notifyUser(getNotificationSource(widgetInstanceManager),NOTIFICATION_EVENT_WORKFLOW_DECISION,
					NotificationEvent.Level.FAILURE, Labels.getLabel(REGISTRATION_REJECTED_MISSING_REJECT_REASON), selectedDecision, workflowAction);			
			return;
		}
		
		super.makeDecision(workflowAction, selectedDecision, widgetInstanceManager);

		
	}
	
	
	/**
	 * @return True if the reject decision can proceed, i.e. we have a reject reason
	 */
	protected boolean validateRejectDecision(final WorkflowActionModel action)
	{
		// TODO: Add system property to make reject reason optional or mandatory, default to mandatory
		return StringUtils.isNotBlank(getB2BRegistrationModel(action).getRejectReason());
	}

	/**
	 * @return True if the approve decision can proceed, i.e. we have a default b2b unit assigned to the customer
	 */
	protected boolean validateApproveDecision(final WorkflowActionModel action)
	{
		return getB2BRegistrationModel(action).getDefaultB2BUnit() != null;
	}

	/**
	 * @return True if the user chose to reject the registration
	 */
	protected boolean isRejectedDecision(final WorkflowDecisionModel decision)
	{
		return StringUtils.equalsIgnoreCase(decision.getCode(),
				REGISTRATION_REJECTED);
	}

	/**
	 * @return True if the user chose to approve the registration
	 */
	protected boolean isApprovedDecision(final WorkflowDecisionModel decision)
	{
		return StringUtils.equalsIgnoreCase(decision.getCode(),
				REGISTRATION_APPROVED);
	}

	/**
	 * @return True if this action is part of the B2B registration process, false otherwise
	 */
	protected boolean isB2BRegistrationWorkflowAction(final WorkflowActionModel action)
	{
		return StringUtils.equalsIgnoreCase(action.getTemplate().getCode(),
				REGISTRATION_APPROVAL);
	}

	/**
	 * Gets the {@link B2BRegistrationModel} instance attached to the {@link WorkflowActionModel}
	 * 
	 * @param action
	 *           The action on which we are taking a decision
	 * @return The attached B2BRegistrationModel instance
	 */
	protected B2BRegistrationModel getB2BRegistrationModel(final WorkflowActionModel action)
	{
		final List<ItemModel> models = getWorkflowAttachmentService().getAttachmentsForAction(action,
				B2BRegistrationModel.class.getName());
		return (B2BRegistrationModel) models.iterator().next();
	}
	
	/**
	 * @return WorkflowAttachmentService instance from the {@link Registry}
	 */
	protected WorkflowAttachmentService getWorkflowAttachmentService()
	{
		return Registry.getApplicationContext().getBean(WorkflowAttachmentService.class);
	}

}
