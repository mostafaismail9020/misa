package com.sap.ibso.eservices.core.sagia.services;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.ticket.service.TicketException;

import java.util.List;
/**
 * The interface Sagia ticket service.
 */
public interface SagiaTicketService {

	/**
	 * Gets tickets by ticket category.
	 *
	 * @param pageableData   the pageable data
	 * @param ticketCategory the ticket category
	 * @param sector         the sector
	 * @return the tickets by ticket category
	 */
	SearchPageData<CsTicketModel> getTicketsByTicketCategory(PageableData pageableData, String ticketCategory, String sector);

	/**
	 * Gets tickets by b 2 b unit.
	 *
	 * @param pageableData the pageable data
	 * @param b2bUnit      the b 2 b unit
	 * @return the tickets by b 2 b unit
	 */
	SearchPageData<CsTicketModel> getTicketsByB2BUnit(PageableData pageableData, String b2bUnit);

	/**
	 * Save opportunity state and trigger event.
	 *
	 * @param ticketId the ticket id
	 * @param state    the state
	 * @param message  the message
	 * @throws TicketException        the ticket exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	void saveOpportunityStateAndTriggerEvent(String ticketId, CsTicketState state, String message) throws TicketException, IllegalAccessException;

	/**
	 * Gets tickets by b 2 b unit.
	 *
	 * @param pageableData the pageable data
	 * @param b2bUnit      the b 2 b unit
	 * @return the tickets by b 2 b unit
	 */
	SearchPageData<CsTicketModel> getUnApprovedTicketsByB2BUnit(PageableData pageableData, String b2bUnit);

	/**
	 * Is current user valid approver boolean.
	 *
	 * @param ticketId               the ticket id
	 * @param ticketUserB2BUnitModel the ticket user b 2 b unit model
	 * @return the boolean
	 * @throws IllegalAccessException the illegal access exception
	 */
	boolean isCurrentUserValidApprover(String ticketId, B2BUnitModel ticketUserB2BUnitModel)
			throws IllegalAccessException;
			
	List<ContactTicketModel> getScpiTickets();
	List<ServiceRequestModel> getScpiServiceRequest();
}
