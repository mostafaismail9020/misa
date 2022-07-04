
package com.investsaudiportal.scpi.outbound.services;

import com.investsaudi.portal.core.model.ContactTicketModel;
import de.hybris.platform.sap.sapcpiadapter.service.SapCpiOutboundService;
import de.hybris.platform.sap.sapcpiadapter.service.impl.SapCpiOutboundServiceImpl;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class ScpiOutboundServiceImpl extends SapCpiOutboundServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(ScpiOutboundServiceImpl.class);
    @Resource
    private ModelService modelService;

    // Lead Ticket Outbounds
    private static final String OUTBOUND_LEAD_TICKET_OBJECT = "LeadTicket";
    private static final String OUTBOUND_LEAD_TICKET_DESTINATION = "scpiLeadTicketDestination";
	private static final String OUTBOUND_CUSTOMER_EVENT_OBJECT = "CsCustomerEvent";
    private static final String OUTBOUND_CUSTOMER_EVENT_DESTINATION = "scpiCsCustomerEventDestination";
    private static final String OUTBOUND_SERVICE_REQUEST_OBJECT = "ServiceRequest";
    private static final String OUTBOUND_SERVICE_REQUEST_DESTINATION = "scpiServiceRequestDestination";
    private static final String OUTBOUND_TICKET_ATTACHMENT_OBJECT = "OutboundTicketAttachment";
    private static final String OUTBOUND_TICKET_ATTACHMENT_DESTINATION = "scpiTicketAttachmentDestination";
    private static final String COMMA = ",";

    public void sendLeadTicket(CsTicketModel leadTicket) {
		ContactTicketModel contactTicket = (ContactTicketModel)leadTicket;

        getOutboundServiceFacade().send(leadTicket, OUTBOUND_LEAD_TICKET_OBJECT, OUTBOUND_LEAD_TICKET_DESTINATION).subscribe(
                // onNext
                responseEntityMap -> {
                    if (SapCpiOutboundService.isSentSuccessfully(responseEntityMap)) {
                        LOG.info("Lead Ticket [{}] has been sent to C4C through SCPI! {}",
                                leadTicket.getTicketID(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE)); 
					contactTicket.setSent2Scpi(true);
                    modelService.save(contactTicket);
                    modelService.refresh(contactTicket);
                        if(LOG.isDebugEnabled()){
                            
                            StringBuilder payload = new StringBuilder("ticketId : ".concat(contactTicket.getTicketID()).concat(COMMA).concat("Priority : ").concat(contactTicket.getPriority().toString())
                                    .concat(COMMA).concat("c4cAccountId : ").concat(contactTicket.getC4CAccountID()).concat(COMMA).concat("commerceUserid : ").concat(contactTicket.getCommerceUserID())
                                    .concat(COMMA).concat("company :").concat(contactTicket.getCompany()).concat(COMMA).concat("contactSubject : ").concat(contactTicket.getContactSubject()).concat(COMMA)
                                    .concat("email : ").concat(contactTicket.getEmail()).concat(COMMA).concat("jobtitle : ").concat(contactTicket.getJobTitle()).concat(COMMA)
                                    .concat("message : ").concat(contactTicket.getMessage()).concat(COMMA).concat("mobile : ").concat(contactTicket.getMobile()).concat(COMMA)
                                    .concat("name : ").concat(contactTicket.getName()).concat(COMMA).concat("opportunityCode : ").concat(contactTicket.getOpportunityCode()).concat(COMMA)
                                    .concat("sectorCategory : ").concat(contactTicket.getSectorCategory()).concat(COMMA).concat("sectorCategoryCode : ").concat(contactTicket.getSectorCategoryCode()).concat(COMMA)
                                    .concat("sectorCategoryName : ").concat(contactTicket.getSectorCategoryName()).concat("COMMA").concat("courceCode : ").concat(contactTicket.getSourceCode()));
                            LOG.info("Lead Ticket [{}] has been sent to C4C through SCPI! {} with payLoad : ",  payload);
                        }
                    } else {
                        LOG.error("Lead Ticket  [{}] has not been sent to C4C! {}",
                                leadTicket.getTicketID(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));
					contactTicket.setSent2Scpi(false);
                    modelService.save(contactTicket);
                    modelService.refresh(contactTicket);
                    }
                }
                // onError
                , error -> {
                    LOG.error("Lead Ticket [{}] has not been sent to C4C through SCPI! {}",
                            leadTicket.getTicketID(), error.getMessage(), error);
					contactTicket.setSent2Scpi(false);
                    modelService.save(contactTicket);
                    modelService.refresh(contactTicket);
                }
        );
    }
	
	public void sendCsCustomerEvent(CsCustomerEventModel csCustomerEvent) {
        getOutboundServiceFacade().send(csCustomerEvent, OUTBOUND_CUSTOMER_EVENT_OBJECT, OUTBOUND_CUSTOMER_EVENT_DESTINATION).subscribe(
                // onNext
                responseEntityMap -> {
                    if (SapCpiOutboundService.isSentSuccessfully(responseEntityMap)) {
                        LOG.info("CsCustomerEvent [{}] has been sent to C4C through SCPI! {}",
                                csCustomerEvent.getCode(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));
                        if(LOG.isDebugEnabled()){
                            StringBuilder payload = new StringBuilder("code : ".concat(csCustomerEvent.getCode()).concat(COMMA).concat("interventionType : ").concat(csCustomerEvent.getInterventionType().getCode())
                                    .concat(COMMA).concat("subject : ").concat(csCustomerEvent.getSubject()).concat(COMMA).concat("text : ").concat(csCustomerEvent.getText()).concat(COMMA));
                            LOG.info("CsCustomerEvent [{}] has been sent to C4C through SCPI! {} with payLoad : ",  payload);
                        }
                    } else {

                        LOG.error("CsCustomerEvent  [{}] has not been sent to C4C! {}",
                                csCustomerEvent.getCode(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));
                    }
                }
                // onError
                , error -> {
                    LOG.error("CsCustomerEvent [{}] has not been sent to C4C through SCPI! {}",
                            csCustomerEvent.getCode(), error.getMessage(), error);
                }
        );
    }
	
	public void sendServiceRequest(ServiceRequestModel serviceRequest) {
        getOutboundServiceFacade().send(serviceRequest, OUTBOUND_SERVICE_REQUEST_OBJECT, OUTBOUND_SERVICE_REQUEST_DESTINATION).subscribe(
                // onNext
                responseEntityMap -> {
                    if (SapCpiOutboundService.isSentSuccessfully(responseEntityMap)) {
                        LOG.info("ServiceRequest [{}] has been sent to C4C through SCPI! {}",
                                serviceRequest.getId(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));
						serviceRequest.setSent2Scpi(true);
                        modelService.save(serviceRequest);
                        modelService.refresh(serviceRequest);
                        if(LOG.isDebugEnabled()){
                            StringBuilder payload = new StringBuilder("description : ".concat(serviceRequest.getDescription()).concat(COMMA).concat("incidentCategory : ").concat(serviceRequest.getIncidentCategory().getCode())
                                    .concat(COMMA).concat("partnerType : ").concat(serviceRequest.getPartnerType().getCode()).concat(COMMA)
                                    .concat("priority : ").concat(serviceRequest.getPriority().getCode()).concat(COMMA)
                                    .concat("serviceCategory :").concat(serviceRequest.getServiceCategory().getCode()).concat(COMMA)
                                    .concat("subject : ").concat(serviceRequest.getSubject()).concat(COMMA)
                                    .concat("ticketId :").concat(serviceRequest.getContactTicket().getTicketID()));
                            LOG.info("ServiceRequest [{}] has been sent to C4C through SCPI! {} with payLoad : ",  payload);
                        }
                    } else {
                        LOG.error("ServiceRequest  [{}] has not been sent to C4C! {}",
                                serviceRequest.getId(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));
					serviceRequest.setSent2Scpi(false);
                    modelService.save(serviceRequest);
                    modelService.refresh(serviceRequest);
                    }
                }
                // onError
                , error -> {
                    LOG.error("ServiceRequest [{}] has not been sent to C4C through SCPI! {}",
                            serviceRequest.getId(), error.getMessage(), error);
						serviceRequest.setSent2Scpi(false);
                        modelService.save(serviceRequest);
                        modelService.refresh(serviceRequest);
                }
        );
    }
	
	   public void sendTicketAttachment(ContactTicketModel contactTicket) {
        getOutboundServiceFacade().send(contactTicket, OUTBOUND_TICKET_ATTACHMENT_OBJECT, OUTBOUND_TICKET_ATTACHMENT_DESTINATION).subscribe(
                // onNext
                responseEntityMap -> {
                    if (SapCpiOutboundService.isSentSuccessfully(responseEntityMap)) {

                        LOG.info("contactTicket [{}] has been sent to C4C through SCPI! {}",
                                contactTicket.getTicketID(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));
                    } else {
                        LOG.error("contactTicket  [{}] has not been sent to C4C! {}",
                                contactTicket.getTicketID(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));
                    }
                }
                // onError
                , error -> {
                    LOG.error("contactTicket [{}] has not been sent to C4C through SCPI! {}",
                            contactTicket.getTicketID(), error.getMessage(), error);
                }
        );
    }
}
