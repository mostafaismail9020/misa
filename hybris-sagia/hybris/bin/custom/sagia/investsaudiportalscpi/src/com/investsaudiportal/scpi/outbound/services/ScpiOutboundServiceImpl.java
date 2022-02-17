
package com.investsaudiportal.scpi.outbound.services;

import com.investsaudi.portal.core.model.ContactTicketModel;
import de.hybris.platform.sap.sapcpiadapter.service.SapCpiOutboundService;
import de.hybris.platform.sap.sapcpiadapter.service.impl.SapCpiOutboundServiceImpl;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScpiOutboundServiceImpl extends SapCpiOutboundServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(ScpiOutboundServiceImpl.class);

    // Lead Ticket Outbounds
    private static final String OUTBOUND_LEAD_TICKET_OBJECT = "LeadTicket";
    private static final String OUTBOUND_LEAD_TICKET_DESTINATION = "scpiLeadTicketDestination";
	private static final String OUTBOUND_CUSTOMER_EVENT_OBJECT = "CsCustomerEvent";
    private static final String OUTBOUND_CUSTOMER_EVENT_DESTINATION = "scpiCsCustomerEventDestination";
    private static final String OUTBOUND_SERVICE_REQUEST_OBJECT = "ServiceRequest";
    private static final String OUTBOUND_SERVICE_REQUEST_DESTINATION = "scpiServiceRequestDestination";
    private static final String OUTBOUND_TICKET_ATTACHMENT_OBJECT = "OutboundTicketAttachment";
    private static final String OUTBOUND_TICKET_ATTACHMENT_DESTINATION = "scpiTicketAttachmentDestination";


    public void sendLeadTicket(CsTicketModel leadTicket) {

        getOutboundServiceFacade().send(leadTicket, OUTBOUND_LEAD_TICKET_OBJECT, OUTBOUND_LEAD_TICKET_DESTINATION).subscribe(

                // onNext
                responseEntityMap -> {
                    if (SapCpiOutboundService.isSentSuccessfully(responseEntityMap)) {

                        LOG.info("Lead Ticket [{}] has been sent to C4C through SCPI! {}",
                                leadTicket.getTicketID(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));

                    } else {

                        LOG.error("Lead Ticket  [{}] has not been sent to C4C! {}",
                                leadTicket.getTicketID(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));

                    }
                }
                // onError
                , error -> {
                    LOG.error("Lead Ticket [{}] has not been sent to C4C through SCPI! {}",
                            leadTicket.getTicketID(), error.getMessage(), error);
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
                    } else {

                        LOG.error("ServiceRequest  [{}] has not been sent to C4C! {}",
                                serviceRequest.getId(), SapCpiOutboundService.getPropertyValue(responseEntityMap, RESPONSE_MESSAGE));
                    }
                }
                // onError
                , error -> {
                    LOG.error("ServiceRequest [{}] has not been sent to C4C through SCPI! {}",
                            serviceRequest.getId(), error.getMessage(), error);
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
