package com.investsaudi.portal.core.service;

import com.investsaudi.portal.core.model.ContactTicketModel;
import de.hybris.platform.comments.services.CommentService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.impl.DefaultTicketBusinessService;
import de.hybris.platform.ticket.strategies.TicketEventStrategy;
import de.hybris.platform.ticketsystem.data.ContactTicketParameter;
import de.hybris.platform.ticketsystem.data.CsTicketParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class ContactTicketBusinessService extends DefaultTicketBusinessService {

    private static final Logger log = LoggerFactory.getLogger(ContactTicketBusinessService.class);

    @Resource(name = "contactTicketParameterConverter")
    private Converter<ContactTicketParameter, ContactTicketModel> contactTicketParameterConverter;

    @Resource(name = "contactTicketEventStrategy")
    private TicketEventStrategy ticketEventStrategy;

    @Resource(name = "commentService")
    private CommentService commentService;

    @Override
    public CsTicketModel createTicket(CsTicketParameter ticketParameter) {

        if (ticketParameter instanceof ContactTicketParameter) {
            ContactTicketParameter contactTicketParameter = (ContactTicketParameter) ticketParameter;
            CsTicketModel ticket = contactTicketParameterConverter.convert(contactTicketParameter);
            CsCustomerEventModel creationEvent = ticketEventStrategy.createCreationEventForTicket(ticket,
                ticketParameter.getReason(), ticketParameter.getInterventionType(), ticketParameter.getCreationNotes());

            CsTicketModel csTicket = createTicketInternal(ticket, creationEvent);
            getModelService().save(csTicket);
            return csTicket;
        }

        log.warn("This service can only create ticket of type ContactTicketParameter, parameter type: [{}]",
            ticketParameter
                .getClass().getName());
        return null;
    }

}