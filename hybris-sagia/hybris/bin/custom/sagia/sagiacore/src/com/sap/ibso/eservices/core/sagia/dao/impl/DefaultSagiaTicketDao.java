package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaTicketDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.ticket.dao.impl.DefaultTicketDao;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.core.model.enumeration.EnumerationValueModel;

import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.search.restriction.SearchRestrictionService;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;

public class DefaultSagiaTicketDao extends DefaultTicketDao implements SagiaTicketDao {
	
	private SearchRestrictionService searchRestrictionService;
	
	private static final String FIND_TICKETS_BY_UNIT_QUERY = "SELECT {" + CsTicketModel._TYPECODE
			+ ":" + CsTicketModel.PK + "} FROM { " + CsTicketModel._TYPECODE + " as "
			+ CsTicketModel._TYPECODE + " JOIN " + B2BCustomerModel._TYPECODE + " as " + B2BCustomerModel._TYPECODE + " ON {"
			+ CsTicketModel._TYPECODE + ":" + CsTicketModel.CUSTOMER + "} = {" + B2BCustomerModel._TYPECODE + ":"
			+ B2BCustomerModel.PK + "} JOIN " + B2BUnitModel._TYPECODE + " as " + B2BUnitModel._TYPECODE + " ON {"
			+ B2BUnitModel._TYPECODE + ":" + B2BUnitModel.PK + "} = {" + B2BCustomerModel._TYPECODE + ":"
			+ B2BCustomerModel.DEFAULTB2BUNIT + "}} WHERE {" + B2BUnitModel._TYPECODE + ":" + B2BUnitModel.UID + "} = ?b2bUnit";

	private static String FIND_TICKETS_FOR_GIVEN_UNIT_QUERY = "SELECT {" + CsTicketModel._TYPECODE
			+ ":" + CsTicketModel.PK + "} FROM { " + CsTicketModel._TYPECODE + " as "
			+ CsTicketModel._TYPECODE + " JOIN " + B2BCustomerModel._TYPECODE + " as " + B2BCustomerModel._TYPECODE + " ON {"
			+ CsTicketModel._TYPECODE + ":" + CsTicketModel.CUSTOMER + "} = {" + B2BCustomerModel._TYPECODE + ":"
			+ B2BCustomerModel.PK + "} JOIN " + B2BUnitModel._TYPECODE + " as " + B2BUnitModel._TYPECODE + " ON {"
			+ B2BUnitModel._TYPECODE + ":" + B2BUnitModel.PK + "} = {" + B2BCustomerModel._TYPECODE + ":"
			+ B2BCustomerModel.DEFAULTB2BUNIT + "} JOIN "+EnumerationValueModel._TYPECODE+" as "+EnumerationValueModel._TYPECODE+" ON {"
			+ EnumerationValueModel._TYPECODE + ":" +EnumerationValueModel.PK+"} = {"+CsTicketModel._TYPECODE+":"+CsTicketModel.STATE+"}} "
			+ " WHERE {" + B2BUnitModel._TYPECODE + ":" + B2BUnitModel.UID + "} IN (?b2bUnit) AND "
			+ "{"+EnumerationValueModel._TYPECODE +":"+EnumerationValueModel.CODE+"} in ('"+ CsTicketState.WOAGPENDINGAPPROVAL.getCode()+"', '"
			+ CsTicketState.WOAGAPPROVED.getCode()+"', '"+CsTicketState.WOAGREJECTED.getCode()+"', '"+CsTicketState.UPLOADED.getCode()+"')";


//	private static final String FIND_TICKETS_BY_TICKET_CATEGORY = "SELECT {" + CsTicketModel._TYPECODE
//			+ ":" + CsTicketModel.PK + "} FROM { " + CsTicketModel._TYPECODE + " as "
//			+ CsTicketModel._TYPECODE + " JOIN " + EnumerationValueModel._TYPECODE + " as " + EnumerationValueModel._TYPECODE + " ON {"
//			+ CsTicketModel._TYPECODE + ":" + CsTicketModel.CATEGORY + "} = {" + EnumerationValueModel._TYPECODE + ":"
//			+ EnumerationValueModel.PK + "}} WHERE {" + EnumerationValueModel._TYPECODE + ":" + EnumerationValueModel.CODE + "} = ?ticketCategory";
	
//	private static final String FIND_TICKETS_BY_TICKET_CATEGORY = "select {CsTicket.pk} from {csticket as CsTicket "
//			+ "JOIN enumerationvalue as ev ON {CsTicket.category} = {ev.pk} "
//			+ "JOIN b2bcustomer as bc ON {bc.pk} = {CsTicket.customer} "
//			+ "JOIN b2bunit as B2BUnit ON {B2BUnit.pk} = {bc.defaultb2bunit} "
//			+ "JOIN TicketConfiguration as tc ON {CsTicket.configuration} = {tc.pk}} "
//			+ "WHERE {ev.code} = ?ticketCategory AND {CsTicket.configuration} LIKE '?sector%'" ;
	
	private static final String SORT_TICKETS_BY_DATE_DESC = " ORDER BY {" + CsTicketModel._TYPECODE + ":"
			+ CsTicketModel.MODIFIEDTIME + "} DESC";

	private static final String SORT_TICKETS_BY_TICKETID_DESC = " ORDER BY {" + CsTicketModel._TYPECODE + ":"
			+ CsTicketModel.TICKETID + "} DESC";

	private static final String SORT_TICKETS_BY_STATE_DESC = " ORDER BY {" + CsTicketModel._TYPECODE + ":"
			+ CsTicketModel.STATE + "} DESC";

	private static final String SORT_TICKETS_BY_SECTOR_DESC = " ORDER BY {" + CsTicketModel._TYPECODE + ":"
			+ CsTicketModel.CONFIGURATION + "} DESC";
	
	private static final String SORT_TICKETS_BY_UNIT_DESC = " ORDER BY {" + B2BUnitModel._TYPECODE + ":"
			+ B2BUnitModel.NAME + "} DESC";
	
	private static final String SORT_TICKETS_BY_DATE_ASC = " ORDER BY {" + CsTicketModel._TYPECODE + ":"
			+ CsTicketModel.MODIFIEDTIME + "} ASC";

	private static final String SORT_TICKETS_BY_TICKETID_ASC = " ORDER BY {" + CsTicketModel._TYPECODE + ":"
			+ CsTicketModel.TICKETID + "} ASC";

	private static final String SORT_TICKETS_BY_STATE_ASC = " ORDER BY {" + CsTicketModel._TYPECODE + ":"
			+ CsTicketModel.STATE + "} ASC";

	private static final String SORT_TICKETS_BY_SECTOR_ASC = " ORDER BY {" + CsTicketModel._TYPECODE + ":"
			+ CsTicketModel.CONFIGURATION + "} ASC";
	
	private static final String SORT_TICKETS_BY_UNIT_ASC = " ORDER BY {" + B2BUnitModel._TYPECODE + ":"
			+ B2BUnitModel.NAME + "} ASC";
	
	
	@Override
	public SearchPageData<CsTicketModel> findTicketsByTicketCategory(PageableData pageableData, String ticketCategory, String sector) {
		String FIND_TICKETS_BY_TICKET_CATEGORY = "";
		
		if(StringUtils.isNotBlank(sector)) {
			FIND_TICKETS_BY_TICKET_CATEGORY = "select {CsTicket.pk} from {csticket as CsTicket "
					+ "JOIN enumerationvalue as ev ON {CsTicket.category} = {ev.pk} "
					+ "JOIN b2bcustomer as bc ON {bc.pk} = {CsTicket.customer} "
					+ "JOIN b2bunit as B2BUnit ON {B2BUnit.pk} = {bc.defaultb2bunit} "
					+ "JOIN TicketConfiguration as tc ON {CsTicket.configuration} = {tc.pk}} "
					+ "WHERE {ev.code} = ?ticketCategory AND {tc.code} LIKE '"+sector+"%' " ;
			
		}else {
			FIND_TICKETS_BY_TICKET_CATEGORY = "select {CsTicket.pk} from {csticket as CsTicket "
					+ "JOIN enumerationvalue as ev ON {CsTicket.category} = {ev.pk} "
					+ "JOIN b2bcustomer as bc ON {bc.pk} = {CsTicket.customer} "
					+ "JOIN b2bunit as B2BUnit ON {B2BUnit.pk} = {bc.defaultb2bunit}} "
					+ "WHERE {ev.code} = ?ticketCategory " ;
		}
		
		final Map<String, Object> queryParams = new HashMap<String, Object>(1);
		queryParams.put("ticketCategory", ticketCategory);

		final List<SortQueryData> sortQueries = Arrays
				.asList(
						createSortQueryData("byDateDesc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_DATE_DESC),
						createSortQueryData("byDateAsc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_DATE_ASC),
                        createSortQueryData("byTicketIDDesc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_TICKETID_DESC),
                        createSortQueryData("byTicketIDAsc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_TICKETID_ASC),
				        createSortQueryData("byStateDesc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_STATE_DESC),
				        createSortQueryData("byStateAsc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_STATE_ASC),
				        createSortQueryData("bySectorDesc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_SECTOR_DESC),
				        createSortQueryData("bySectorAsc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_SECTOR_ASC),
				        createSortQueryData("byUnitDesc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_UNIT_DESC),
						createSortQueryData("byUnitAsc", FIND_TICKETS_BY_TICKET_CATEGORY + SORT_TICKETS_BY_UNIT_ASC));
		
		getSearchRestrictionService().disableSearchRestrictions();
		return getPagedFlexibleSearchService().search(sortQueries, "byDateDesc", queryParams, pageableData);
	}
	
	@Override
	public SearchPageData<CsTicketModel> findTicketsByB2BUnit(PageableData pageableData, String b2bUnit) {
		final Map<String, Object> queryParams = new HashMap<String, Object>(1);
		queryParams.put("b2bUnit", b2bUnit);

		final List<SortQueryData> sortQueries = Arrays
				.asList(
						createSortQueryData("byDateDesc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_DATE_DESC),
						createSortQueryData("byDateAsc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_DATE_ASC),
                        createSortQueryData("byTicketIDDesc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_TICKETID_DESC),
                        createSortQueryData("byTicketIDAsc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_TICKETID_ASC),
				        createSortQueryData("byStateDesc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_STATE_DESC),
				        createSortQueryData("byStateAsc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_STATE_ASC),
				        createSortQueryData("bySectorDesc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_SECTOR_DESC),
				        createSortQueryData("bySectorAsc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_SECTOR_ASC),
				        createSortQueryData("byUnitDesc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_UNIT_DESC),
						createSortQueryData("byUnitAsc", FIND_TICKETS_BY_UNIT_QUERY + SORT_TICKETS_BY_UNIT_ASC));
		
		getSearchRestrictionService().disableSearchRestrictions();
		return getPagedFlexibleSearchService().search(sortQueries, "byDateDesc", queryParams, pageableData);
	}

	@Override
	public SearchPageData<CsTicketModel> findUnApprovedTicketsByB2BUnit(final PageableData pageableData, final Set<B2BUnitModel> b2bUnits) {
		final Map<String, Object> queryParams = new HashMap<String, Object>(1);
		queryParams.put ("b2bUnit", createUidList(b2bUnits));
		final List<SortQueryData> sortQueries = Arrays
				.asList(
						createSortQueryData("byDateDesc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_DATE_DESC),
						createSortQueryData("byDateAsc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_DATE_ASC),
						createSortQueryData("byTicketIDDesc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_TICKETID_DESC),
						createSortQueryData("byTicketIDAsc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_TICKETID_ASC),
						createSortQueryData("byStateDesc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_STATE_DESC),
						createSortQueryData("byStateAsc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_STATE_ASC),
						createSortQueryData("bySectorDesc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_SECTOR_DESC),
						createSortQueryData("bySectorAsc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_SECTOR_ASC),
						createSortQueryData("byUnitDesc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_UNIT_DESC),
						createSortQueryData("byUnitAsc", FIND_TICKETS_FOR_GIVEN_UNIT_QUERY + SORT_TICKETS_BY_UNIT_ASC));
		getSearchRestrictionService().disableSearchRestrictions();
		return getPagedFlexibleSearchService().search(sortQueries, "byDateDesc", queryParams, pageableData);
	}

	private List<String> createUidList(final Set<B2BUnitModel> b2bUnits) {
		List<String> uids = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(b2bUnits)) {
			for (B2BUnitModel b2BUnitModel : b2bUnits) {
				uids.add(b2BUnitModel.getUid());
			}
		}
		return uids;
	}


	protected SortQueryData createSortQueryData(final String sortCode, final String query)
	{
		final SortQueryData result = new SortQueryData();
		result.setSortCode(sortCode);
		result.setQuery(query);
		return result;
	}
	
	protected SearchRestrictionService getSearchRestrictionService()
	{
		return searchRestrictionService;
	}

	@Required
	public void setSearchRestrictionService(final SearchRestrictionService searchRestrictionService)
	{
		this.searchRestrictionService = searchRestrictionService;
	}
  
  @Override
	public List<ContactTicketModel> getScpiTickets(String convertedDate) {
		final StringBuilder query = new StringBuilder();
		query.append(" SELECT {PK} FROM { ContactTicket ");
		query.append(" } WHERE {sent2Scpi} is null or {sent2Scpi} = 0 ");
		query.append(" AND {creationtime} >= ?convertedDate ");
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("convertedDate", convertedDate);
		final SearchResult<ContactTicketModel> result = getFlexibleSearchService().search(query.toString(), parameters);
		return result.getResult();
	}

	@Override
	public List<ServiceRequestModel> getScpiServiceRequest(String convertedDate) {
		
		final StringBuilder query = new StringBuilder();
		query.append(" SELECT {PK} FROM { ServiceRequest ");
		query.append(" } WHERE {sent2Scpi} is null or {sent2Scpi} = 0 ");
		query.append(" AND {creationtime} >= ?convertedDate ");
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("convertedDate", convertedDate);
		final SearchResult<ServiceRequestModel> result = getFlexibleSearchService().search(query.toString(), parameters);
		return result.getResult();
	}

	@Override
	public List<CsCustomerEventModel> getScpiCustomerEvents(String convertedDate){
		
		final StringBuilder query = new StringBuilder();
		query.append(" SELECT {PK} FROM { CsCustomerEvent ");
		query.append(" } WHERE {sent2Scpi} is null or {sent2Scpi} = 0 ");
		query.append(" AND {creationtime} >= ?convertedDate ");
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("convertedDate", convertedDate);
		final SearchResult<CsCustomerEventModel> result = getFlexibleSearchService().search(query.toString(), parameters);
		return result.getResult(); 
	}

}
