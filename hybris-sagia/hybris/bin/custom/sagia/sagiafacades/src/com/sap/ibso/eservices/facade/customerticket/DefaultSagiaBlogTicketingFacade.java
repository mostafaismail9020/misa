package com.sap.ibso.eservices.facade.customerticket;

import com.sap.ibso.eservices.core.sagia.services.impl.DefaultSagiaBlogTicketBusinessService;
import com.sap.ibso.eservices.facades.data.BlogTicketData;
import de.hybris.platform.customerticketingfacades.customerticket.DefaultCustomerTicketingFacade;
import de.hybris.platform.customerticketingfacades.data.TicketData;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticketsystem.data.CsTicketParameter;
import org.apache.log4j.Logger;

import javax.annotation.Resource;


public class DefaultSagiaBlogTicketingFacade extends DefaultCustomerTicketingFacade
{
	protected static final Logger LOG = Logger.getLogger(DefaultSagiaBlogTicketingFacade.class);

	@Resource(name = "blogTicketBusinessService")
	private DefaultSagiaBlogTicketBusinessService blogTicketBusinessService;

	@Override
	public TicketData createTicket(final TicketData ticketData)
	{
		final CsTicketModel ticket;
		final CsTicketParameter ticketParameter = createCsTicketParameter(ticketData);

		ticket = blogTicketBusinessService.createTicket(ticketParameter);
		ticketData.setId(ticket.getTicketID());
		return ticketData;
	}

	@Override
	protected CsTicketParameter createCsTicketParameter(final TicketData ticketData)
	{

		final CsTicketParameter ticketParameter = super.createCsTicketParameter(ticketData);

		BlogTicketData blogTicketData = (BlogTicketData) ticketData;
		ticketParameter.setBlogComment(blogTicketData.getBlogComment());
		ticketParameter.setBlogPostComponent(blogTicketData.getBlogPostComponent());

		return ticketParameter;
	}
}