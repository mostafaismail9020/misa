package com.sap.ibso.eservices.core.sagia.dao;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.util.List;
import java.util.Set;

public interface SagiaTicketDao {

	SearchPageData<CsTicketModel> findTicketsByTicketCategory(PageableData pageableData, String ticketCategory, String sector);
	SearchPageData<CsTicketModel> findTicketsByB2BUnit(PageableData pageableData, String b2bUnit);
	SearchPageData<CsTicketModel> findUnApprovedTicketsByB2BUnit(PageableData pageableData, Set<B2BUnitModel> childB2BUnits);
	List<ContactTicketModel> getScpiTickets();
	List<ServiceRequestModel> getScpiServiceRequest();
	List<CsCustomerEventModel> getScpiCustomerEvents();
}
