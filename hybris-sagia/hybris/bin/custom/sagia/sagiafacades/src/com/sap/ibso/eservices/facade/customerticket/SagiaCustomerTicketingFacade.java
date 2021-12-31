package com.sap.ibso.eservices.facade.customerticket;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.customerticketingfacades.data.TicketData;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.service.TicketException;

/**
 * The interface Sagia customer ticketing facade.
 */
public interface SagiaCustomerTicketingFacade {

    /**
     * Sets state of the ticket.
     *
     * @param ticketId the ticket id
     * @param state    the state
     * @param message  the message
     * @throws TicketException        the ticket exception
     * @throws IllegalAccessException the illegal access exception
     */
    void setTicketState(String ticketId, CsTicketState state, String message) throws TicketException, IllegalAccessException;

    /**
     * Gets un approved tickets by b 2 b unit.
     *
     * @param pageableData the pageable data
     * @param b2bUnit      the b 2 b unit
     * @return the un approved tickets by b 2 b unit
     */
    SearchPageData<TicketData> getUnApprovedTicketsByB2BUnit(final PageableData pageableData, String b2bUnit);

    /**
     * Gets ticket.
     *
     * @param ticketId the ticket id
     * @return the ticket
     */
    TicketData getTicket(String ticketId) throws IllegalAccessException;
}
