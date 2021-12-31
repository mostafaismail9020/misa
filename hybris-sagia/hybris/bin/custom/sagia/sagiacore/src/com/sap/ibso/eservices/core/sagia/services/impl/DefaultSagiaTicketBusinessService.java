
package com.sap.ibso.eservices.core.sagia.services.impl;

import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.sagia.services.SagiaNotificationService;
import com.sap.ibso.eservices.core.sagia.services.TicketStatusService;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.catalog.model.CompanyModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.util.Config;
import de.hybris.platform.workflow.WorkflowProcessingService;
import de.hybris.platform.workflow.WorkflowService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.investsaudi.model.scpi.outbound.process.ScpiOutInvestmentOpportunityProcessModel;
import com.sap.ibso.eservices.core.model.TicketAnswerModel;
import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.model.TicketQuestionModel;
import com.sap.ibso.eservices.core.sagia.dao.TicketConfigurationDAO;
import com.sap.ibso.eservices.core.sagia.dao.TicketQuestionDAO;

import de.hybris.platform.comments.model.CommentAttachmentModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsAgentGroupModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketException;
import de.hybris.platform.ticket.service.impl.DefaultTicketBusinessService;
import de.hybris.platform.ticket.strategies.TicketEventStrategy;
import de.hybris.platform.ticketsystem.data.CsTicketParameter;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.tx.TransactionBody;
import com.investsaudi.scpi.outbound.services.MISAScpiOutboundServiceImpl;

public class DefaultSagiaTicketBusinessService extends DefaultTicketBusinessService {

	private static final Logger LOG = Logger.getLogger(DefaultSagiaTicketBusinessService.class);
	public static final String DEFAULT_CREATION_NOTE = "DEFAULT_CREATION_NOTE";
	boolean userNotificationRequierd = Boolean.FALSE;
	boolean approverNotificaionRequired = Boolean.FALSE;
	private TicketEventStrategy ticketEventStra;

	@Resource(name = "ticketQuestionDAO")
	private TicketQuestionDAO ticketQuestionDAO;

	@Resource
	private TicketConfigurationDAO ticketConfigurationDAO;

	@Resource
	SagiaNotificationService sagiaNotificationService;

	@Resource
	private B2BUnitService b2bUnitService ;

	private WorkflowService workflowService;
	private WorkflowProcessingService workflowProcessingService;

	private TicketStatusService ticketStatusService;

	@Resource(name = "businessProcessService")
	private BusinessProcessService businessProcessService;
	
	@Resource(name = "misaScpiDefaultOutboundService")
	private MISAScpiOutboundServiceImpl misaScpiOutboundService;
	

	public CsCustomerEventModel addNoteToTicket(CsTicketModel ticket, CsInterventionType intervention,
			CsEventReason reason, String note, Collection<MediaModel> attachments) {

		// note added and it's information requested, so change status to
		if (note != null && ticket.getState().equals(CsTicketState.INFORMATIONREQUESTED)) {

			ticketStatusService.activateInitialWorkflowAction(ticket);

			ticket.setState(CsTicketState.MISAINITIALREVIEW);
			getModelService().save(ticket);
		}

		return super.addNoteToTicket(ticket, intervention, reason, note, attachments);
	}

	public CsTicketModel createTicket(CsTicketParameter ticketParameter) {
		userNotificationRequierd = Boolean.FALSE;
		approverNotificaionRequired = Boolean.FALSE;
		List<CommentAttachmentModel> attachments = new ArrayList<CommentAttachmentModel>();

		if (CollectionUtils.isNotEmpty(ticketParameter.getAttachments())) {
			Iterator<MultipartFile> var4 = ticketParameter.getAttachments().iterator();
			while (var4.hasNext()) {
				MultipartFile file = (MultipartFile) var4.next();

				try {
					CommentAttachmentModel attachmentModel = (CommentAttachmentModel) this.getModelService()
							.create(CommentAttachmentModel.class);

					attachmentModel
							.setItem(this.getTicketAttachmentsService().createAttachment(file.getOriginalFilename(),
									file.getContentType(), file.getBytes(), this.getUserService().getCurrentUser()));

					attachments.add(attachmentModel);

				} catch (IOException var6) {

					LOG.error(var6.getMessage(), var6);
					return null;
				}
			}
		}

		CsTicketModel ticket = (CsTicketModel) this.getTicketParameterConverter().convert(ticketParameter);

		if (StringUtils.isNotEmpty(ticketParameter.getSector())) {
			TicketConfigurationModel ticketConfigurationModel = ticketConfigurationDAO
					.getTicketConfigurationByCode(ticketParameter.getSector());
			if (ticketConfigurationModel != null) {
				ticket.setConfiguration(ticketConfigurationModel);
				UserGroupModel userGroup = ticketConfigurationModel.getUserGroup();
				if (userGroup != null) {
					// assignTicketToGroup(ticket, (CsAgentGroupModel) userGroup);
					ticket.setAssignedGroup((CsAgentGroupModel) userGroup);
					ticket.setAssignedAgent(((CsAgentGroupModel) userGroup).getDefaultAssignee());
				}

			}

		}

		CsCustomerEventModel creationEvent = this.ticketEventStra.createCreationEventForTicket(ticket,
				ticketParameter.getReason(), ticketParameter.getInterventionType(), DEFAULT_CREATION_NOTE);

		attachments.forEach((attachmentModelx) -> {
			attachmentModelx.setAbstractComment(creationEvent);
		});

		try {
			boolean isOpprtunityWorkFlowRequired = isOpportunityWorkFlowEnabled();
			return (CsTicketModel) Transaction.current().execute(new TransactionBody() {
				public Object execute() throws TicketException {
					getModelService().saveAll(attachments);
					CsTicketModel csTicket = createTicketInternal(ticket, creationEvent);
					csTicket.setLocation(ticketParameter.getLocation());

					// set UPLOADED as initial state when it's a opportunity ticket
					TicketConfigurationModel ticketConfig = csTicket.getConfiguration();
					if (ticketConfig != null && !ticketConfig.isIsServiceRequest()) {
						if (isOpprtunityWorkFlowRequired){
							csTicket.setState(CsTicketState.WOAGPENDINGAPPROVAL);
							userNotificationRequierd = Boolean.TRUE;
							approverNotificaionRequired = Boolean.TRUE;
						} else {
							csTicket.setState(CsTicketState.UPLOADED);
						}
					}

					Set<TicketAnswerModel> answers = getAnswers(ticketParameter);
					if (answers != null) {
						csTicket.setAnswers(answers);
						getModelService().saveAll(answers);
					}
					getModelService().save(csTicket);

					if(userNotificationRequierd){
						sagiaNotificationService.sendOpportunityCreatedNotificationToUser(csTicket);
					}
					if(approverNotificaionRequired){
						sagiaNotificationService.sendOpportunityCreatedNotificationToApprover(csTicket);
					}

					if (ticketConfig != null && !ticketConfig.isIsServiceRequest()) {
						if (isOpprtunityWorkFlowRequired ||
								Config.getBoolean("opportunity.scpi.interface.enable", true)) {
							final ScpiOutInvestmentOpportunityProcessModel
									scpiOutInvestmentOpportunityProcessModel =
									(ScpiOutInvestmentOpportunityProcessModel) businessProcessService
											.createProcess("scpiOutInvestmentOpportunityProcess-" + ticket.getTicketID()
													+ "-" + System.currentTimeMillis(), "scpiOutInvestmentOpportunityProcess");
							scpiOutInvestmentOpportunityProcessModel.setCsTicket(csTicket);
							getModelService().save(scpiOutInvestmentOpportunityProcessModel);
							businessProcessService.startProcess(scpiOutInvestmentOpportunityProcessModel);
						}

					}

					return csTicket;
				}
			});
		} catch (Exception e) {
			throw new SystemException(
					"Could not create a ticket for the user: " + this.getUserService().getCurrentUser().getUid(), e);
		}
	}

	private Set<TicketAnswerModel> getAnswers(CsTicketParameter ticketParameter) {
		Set<TicketAnswerModel> answers = new HashSet<TicketAnswerModel>();
		Map<String, String> queAnsMap = ticketParameter.getQueAnsMap();
		if (queAnsMap != null) {
			for (String questionCode : queAnsMap.keySet()) {
				TicketQuestionModel ticketQuestionModel = getTicketQuestionModel(questionCode, ticketParameter);
				if (ticketQuestionModel != null) {
					TicketAnswerModel ticketAnswer = getModelService().create(TicketAnswerModel.class);
					ticketAnswer.setAnswer(queAnsMap.get(questionCode));
					ticketAnswer.setTicketQuestion(ticketQuestionModel);
					answers.add(ticketAnswer);
				}
			}
			return answers;
		}
		return null;
	}

	private TicketQuestionModel getTicketQuestionModel(String questionCode, CsTicketParameter ticketParameter) {
		TicketQuestionModel question = ticketQuestionDAO.getQuestion(questionCode);
		return question;
	}

	private boolean isOpportunityWorkFlowEnabled() {
		boolean isOpportunityWorkFlowEnabled = false;
		UserModel userModel = getUserService().getCurrentUser();
		if(userModel instanceof B2BCustomerModel) {
			B2BCustomerModel b2BCustomerModel = (B2BCustomerModel) userModel;
			CompanyModel b2BUnitModel = b2bUnitService.getParent(b2BCustomerModel.getDefaultB2BUnit());
			if(isWOBDUser() && b2BUnitModel != null && b2BUnitModel instanceof  B2BUnitModel
					&& ((B2BUnitModel)b2BUnitModel).isOpportunityApprovalRequired()) {
				isOpportunityWorkFlowEnabled = true;
			}
		}
		return isOpportunityWorkFlowEnabled;
	}

	private boolean isWOBDUser() {
		boolean result = Boolean.FALSE;
		UserModel currentUser = getUserService().getCurrentUser();
		if (currentUser != null) {
			Set<PrincipalGroupModel> curGroups = currentUser.getGroups();
			for (PrincipalGroupModel curGroup : curGroups) {
				if (SagiaCoreConstants.WORKFLOW_BD_ID.equalsIgnoreCase(curGroup.getUid())) {
					result = Boolean.TRUE;
					break;
				}
			}
		}
		return result;
	}

	public TicketEventStrategy getTicketEventStra() {
		return ticketEventStra;
	}

	public void setTicketEventStra(TicketEventStrategy ticketEventStra) {
		this.ticketEventStra = ticketEventStra;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void setWorkflowProcessingService(WorkflowProcessingService workflowProcessingService) {
		this.workflowProcessingService = workflowProcessingService;
	}

	public void setTicketStatusService(TicketStatusService ticketStatusService) {
		this.ticketStatusService = ticketStatusService;
	}
}