
package com.investsaudiportal.scpi.outbound.services;

import de.hybris.platform.sap.sapcpiadapter.service.SapCpiOutboundService;
import de.hybris.platform.sap.sapcpiadapter.service.impl.SapCpiOutboundServiceImpl;
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
}
