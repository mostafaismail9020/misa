
package com.investsaudiportal.scpi.outbound.services;

import com.investsaudiportal.scpi.outbound.actions.SendLeadTicketToScpiAction;
import de.hybris.platform.comments.model.CommentAttachmentModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.sap.sapcpiadapter.model.SAPCpiOutboundB2BCustomerModel;
import de.hybris.platform.sap.sapcpiadapter.service.SapCpiOutboundService;
import de.hybris.platform.sap.sapcpiadapter.service.impl.SapCpiOutboundServiceImpl;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.ticket.events.model.CsTicketEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import rx.Observable;

import javax.annotation.Resource;
import java.util.*;

public class ScpiOutboundServiceImpl extends SapCpiOutboundServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(ScpiOutboundServiceImpl.class);

    // Lead Ticket Outbounds
    private static final String OUTBOUND_LEAD_TICKET_OBJECT = "LeadTicket";
    private static final String OUTBOUND_LEAD_TICKET_DESTINATION = "scpiLeadTicketDestination";

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
}
