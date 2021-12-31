package com.sap.ibso.eservices.facade.customerticket.impl;

import org.apache.log4j.Logger;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.customerticketingfacades.customerticket.DefaultCustomerTicketingFacade;
import de.hybris.platform.customerticketingfacades.data.TicketData;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticketsystem.data.CsTicketParameter;
import com.sap.ibso.eservices.core.sagia.services.SagiaTicketService;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;


public class DefaultSagiaCustomerTicketingFacade extends DefaultCustomerTicketingFacade
{
	protected static final Logger LOG = Logger.getLogger(DefaultSagiaCustomerTicketingFacade.class);
	@Resource
	private SagiaTicketService sagiaTicketService;
	@Resource
	private Converter<CsTicketModel, TicketData> ticketListConverter;

	public SagiaTicketService getSagiaTicketService() {
		return sagiaTicketService;
	}

	public void setSagiaTicketService(SagiaTicketService sagiaTicketService) {
		this.sagiaTicketService = sagiaTicketService;
	}
	
	protected Converter<CsTicketModel, TicketData> getTicketListConverter()
	{
		return ticketListConverter;
	}

	public void setTicketListConverter(final Converter<CsTicketModel, TicketData> ticketListConverter)
	{
		this.ticketListConverter = ticketListConverter;
	}

	@Override
	public TicketData createTicket(final TicketData ticketData)
	{
		final CsTicketModel ticket;
		final CsTicketParameter ticketParameter = createCsTicketParameter(ticketData);
		ticket = getTicketBusinessService().createTicket(ticketParameter);
		ticketData.setId(ticket.getTicketID());
		return ticketData;
	}

	@Override
	protected CsTicketParameter createCsTicketParameter(final TicketData ticketData)
	{
		final CsTicketParameter ticketParameter = super.createCsTicketParameter(ticketData);
		ticketParameter.setQueAnsMap(ticketData.getQueAnsMap());
		ticketParameter.setSector(ticketData.getSector());
		ticketParameter.setLocation(ticketData.getLocation());
		return ticketParameter;
	}
	
	@Override
	public SearchPageData<TicketData> getTicketsByB2BUnit(final PageableData pageableData, String b2bUnit)
	{
		SearchPageData<CsTicketModel> ticketsByUnit = getSagiaTicketService().getTicketsByB2BUnit(pageableData, b2bUnit);
		return convertPageData(ticketsByUnit, getTicketListConverter());
	}
	
	@Override
	public SearchPageData<TicketData> getTicketsByTicketCategory(final PageableData pageableData, String ticketCategory, String sector)
	{
		SearchPageData<CsTicketModel> ticketsByTicketCategory = getSagiaTicketService().getTicketsByTicketCategory(pageableData, ticketCategory, sector);
		return convertPageData(ticketsByTicketCategory, getTicketListConverter());
	}
}