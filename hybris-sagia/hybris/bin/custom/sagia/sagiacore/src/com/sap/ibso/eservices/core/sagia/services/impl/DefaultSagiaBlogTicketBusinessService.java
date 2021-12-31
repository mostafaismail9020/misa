
package com.sap.ibso.eservices.core.sagia.services.impl;

import com.casblogaddon.model.CsBlogTicketModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.impl.DefaultTicketBusinessService;
import de.hybris.platform.ticket.strategies.TicketEventStrategy;
import de.hybris.platform.ticketsystem.data.CsTicketParameter;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

public class DefaultSagiaBlogTicketBusinessService extends DefaultTicketBusinessService {

	private static final Logger LOG = Logger.getLogger(DefaultSagiaBlogTicketBusinessService.class);

	private TicketEventStrategy ticketEventStra;

	public static final String DEFAULT_CREATION_NOTE = "DEFAULT_CREATION_NOTE";

	@Resource(name = "blogTicketParameterConverter")
	private Converter<CsTicketParameter, CsBlogTicketModel> blogTicketParameterConverter;
			
	@Override
	public CsTicketModel createTicket(CsTicketParameter ticketParameter) {

		CsBlogTicketModel ticket = (CsBlogTicketModel) blogTicketParameterConverter.convert(ticketParameter);
		CsCustomerEventModel creationEvent = this.ticketEventStra.createCreationEventForTicket(ticket,
				ticketParameter.getReason(), ticketParameter.getInterventionType(), DEFAULT_CREATION_NOTE);

		CsTicketModel csTicket = createTicketInternal(ticket, creationEvent);
		getModelService().save(csTicket);
		return csTicket;
		
	}
	
	public TicketEventStrategy getTicketEventStra() {
		return ticketEventStra;
	}

	public void setTicketEventStra(TicketEventStrategy ticketEventStra) {
		this.ticketEventStra = ticketEventStra;
	}

}