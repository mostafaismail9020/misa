/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaUserDao;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.ticket.model.CsTicketModel;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.dao.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaUserDao extends DefaultGenericDao<CustomerModel> implements SagiaUserDao
{
	private static final String CUSTOMER_TABLE = "customerTable";
	private static final String MOBILE_NUMBER_VALUE = "mobileNumberValue";
	private static final String MOBILE_COUNTRY_CODE = "mobileCountryCode";
	private static final String EMAIL_VALUE = "emailValue";
	private static final String UID_VALUE = "uidValue";
	private static final String USER_GROUPS = "usergroups";
	private static final String CONTACT_EMAIL = "contactEmail";

	@Resource
	private SearchRestrictionService searchRestrictionService;

	private static final String FIND_CSTICKETS_CREATED_BY_DESIRED_GROUPS_USERS = "SELECT {PK} FROM {CsTicket} where {customer} IN ({{SELECT DISTINCT {b2bcustomer:pk}"
			+ " FROM { B2BCustomer 				AS b2bcustomer "
			+ " JOIN   PrincipalGroupRelation 	AS b2bunitrelation 		 	ON {b2bunitrelation:source} = {b2bcustomer:pk} "
			+ " JOIN   B2BUnit 	  				AS b2bunit	 				ON {b2bunit:pk} = {b2bunitrelation:target} "
			+ " JOIN   PrincipalGroupRelation 	AS desiredgrouprelations	ON {desiredgrouprelations:source} = {b2bcustomer:pk} "
			+ " JOIN   UserGroup 				AS desiredgroups      		ON {desiredgroups:pk} = {desiredgrouprelations:target}} "
			+ " WHERE {desiredgroups:uid} IN (?usergroups) AND {b2bcustomer:active} = "+Boolean.TRUE+"}})";

	private static final String FIND_WOBDUSER_WITH_NO_OPPORTUNITY = "SELECT DISTINCT {b2bcustomer:pk}"
			+ " FROM { B2BCustomer 				AS b2bcustomer "
			+ " JOIN   PrincipalGroupRelation 	AS b2bunitrelation 		 	ON {b2bunitrelation:source} = {b2bcustomer:pk} "
			+ " JOIN   B2BUnit 	  				AS b2bunit	 				ON {b2bunit:pk} = {b2bunitrelation:target} "
			+ " JOIN   PrincipalGroupRelation 	AS desiredgrouprelations	ON {desiredgrouprelations:source} = {b2bcustomer:pk} "
			+ " JOIN   UserGroup 				AS desiredgroups      		ON {desiredgroups:pk} = {desiredgrouprelations:target}} "
			+ " WHERE {desiredgroups:uid} IN (?usergroups) AND {b2bcustomer:active} = "+Boolean.TRUE+" AND {b2bcustomer:pk} NOT IN ({{"
			+ " SELECT DISTINCT {customer} FROM {CsTicket} where {customer} IN ({{SELECT DISTINCT {b2bcustomer:pk}"
			+ " FROM { B2BCustomer 				AS b2bcustomer "
			+ " JOIN   PrincipalGroupRelation 	AS b2bunitrelation 		 	ON {b2bunitrelation:source} = {b2bcustomer:pk} "
			+ " JOIN   B2BUnit 	  				AS b2bunit	 				ON {b2bunit:pk} = {b2bunitrelation:target} "
			+ " JOIN   PrincipalGroupRelation 	AS desiredgrouprelations	ON {desiredgrouprelations:source} = {b2bcustomer:pk} "
			+ " JOIN   UserGroup 				AS desiredgroups      		ON {desiredgroups:pk} = {desiredgrouprelations:target}} "
			+ " WHERE {desiredgroups:uid} IN (?usergroups) AND {b2bcustomer:active} = "+Boolean.TRUE+"}})"
			+ " }})";

	private static final String QUERY_CONTACT_TICKET_LIST = "SELECT {ct:pk} FROM "
			+ " {ContactTicket AS ct JOIN Customer AS cu ON {ct:customer} = {cu:pk}}"
			+ " WHERE {cu:userNameEmail} = ?userNameEmail";
		
	private static final String QUERY_CONTACT_TICKET = "SELECT {p:pk} FROM {ContactTicket AS p} WHERE {p.ticketID} = ?ticketId";

	
	private FlexibleSearchService flexibleSearchService;

	public DefaultSagiaUserDao(final String typecode)
	{
		super(typecode);
	}

	public List<CustomerModel> getCustomers(final String uid, final String mobileNumber, final String mobileCountryCode, final String email) {

		final String query = "SELECT {PK} FROM {Customer} WHERE {UID} = ?uidValue OR ({mobileNumber} = ?mobileNumberValue AND {mobileCountryCode} = ?mobileCountryCode) OR {usernameemail} = ?emailValue";

		final Map<String, String> parameters = new HashMap<>();
		parameters.put("pk", CustomerModel.PK);

		parameters.put(CUSTOMER_TABLE, CustomerModel._TYPECODE);
		parameters.put(UID_VALUE, uid.toLowerCase());
		parameters.put(MOBILE_NUMBER_VALUE, mobileNumber);
		parameters.put(EMAIL_VALUE, email.toLowerCase());
		parameters.put(MOBILE_COUNTRY_CODE, mobileCountryCode);

		return getResults(query, parameters, CustomerModel.class);
	}

	public List<CustomerModel> getCustomerByUid(final String uid) {
		validateParameterNotNull(uid, "User Uid cannot be null");

		final Map<String, String> parameters = new HashMap<>();
		parameters.put(CustomerModel.UID, uid.toLowerCase());

		return find(parameters);
	}

	public List<CustomerModel> getCustomerByMobileNumber(final String mobileNumber,  final String mobileCountryCode) {
		validateParameterNotNull(mobileNumber, "User mobileNumber cannot be null");
		validateParameterNotNull(mobileCountryCode, "User mobileCountryCode cannot be null");
		final String query ;
		final Map<String, String> parameters;

		if(StringUtils.isNotEmpty(mobileCountryCode))
		{
		query = "SELECT {PK} FROM {Customer} WHERE {mobileNumber} = ?mobileNumberValue AND {mobileCountryCode} = ?mobileCountryCode";
		parameters = new HashMap<>();
		parameters.put(CUSTOMER_TABLE, CustomerModel._TYPECODE);
		parameters.put(MOBILE_NUMBER_VALUE, mobileNumber);
		parameters.put(MOBILE_COUNTRY_CODE, mobileCountryCode);
		}
		else
		{
			query = "SELECT {PK} FROM {Customer} WHERE {mobileNumber} = ?mobileNumberValue";
			parameters = new HashMap<>();
			parameters.put(CUSTOMER_TABLE, CustomerModel._TYPECODE);
			parameters.put(MOBILE_NUMBER_VALUE, mobileNumber);
		}

		return getResults(query, parameters, CustomerModel.class);
	}

	public List<CustomerModel> getCustomerByEmail(final String email) {
		validateParameterNotNull(email, "User email cannot be null");

		final String query = "SELECT {PK} FROM {Customer} WHERE {usernameemail} = ?emailValue";

		final Map<String, String> parameters = new HashMap<>();
		parameters.put(CUSTOMER_TABLE, CustomerModel._TYPECODE);
		parameters.put(EMAIL_VALUE, email.toLowerCase());
		return getResults(query, parameters, CustomerModel.class);
	}

	@Override
	public List<CsTicketModel> getAllTicketsByCustomerUnderGiveUserGroup(final String userGroupUid) {
		validateParameterNotNull(userGroupUid, "User group uid cannot be null");
		final Map<String, String> parameters = new HashMap<>();
		parameters.put(USER_GROUPS, userGroupUid);
		return getResultsWithoutSearchRestrictions(FIND_CSTICKETS_CREATED_BY_DESIRED_GROUPS_USERS,
				parameters, CsTicketModel.class);
	}

	@Override
	public List<B2BCustomerModel> getAllWOBDCustomerWithNoTickets(final String userGroupUid) {
		validateParameterNotNull(userGroupUid, "User group uid cannot be null");
		final Map<String, String> parameters = new HashMap<>();
		parameters.put(USER_GROUPS, userGroupUid);
		return getResultsWithoutSearchRestrictions(FIND_WOBDUSER_WITH_NO_OPPORTUNITY,
				parameters, B2BCustomerModel.class);
	}

	@Override
	public List<ContactTicketModel> getUserRaisedOpportunities(String userNameEmail) {
		validateParameterNotNull(userNameEmail, "User userNameEmail cannot be null");
		
//		final String query = "SELECT {PK} FROM {ContactTicket} WHERE {email} = ?contactEmail ";
//		final Map<String, String> parameters = new HashMap<>();
//		parameters.put(CONTACT_EMAIL, contactEmail);
//		return getResults(query, parameters, ContactTicketModel.class);
		
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("userNameEmail", userNameEmail);
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_CONTACT_TICKET_LIST, params);
		final SearchResult<ContactTicketModel> resultList = flexibleSearchService.search(searchQuery);
		
		return resultList.getResult(); 
	}

	@Override
	public ContactTicketModel getContactTicketForTicketId(String ticketId) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("ticketId", ticketId);
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_CONTACT_TICKET, params);
		final SearchResult<ContactTicketModel> resultList = flexibleSearchService.search(searchQuery);
		
		return (null != resultList && resultList.getResult().size() > 0) ? resultList.getResult().get(0) : null;
	}

	public <T> List<T> getResults(final String queryString,
					final Map<String, String> parameters,
	                              final Class<T> resultType)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.setResultClassList(Collections.singletonList(resultType));
		query.addQueryParameters(parameters);
		query.setNeedTotal(false);
		final SearchResult<T> results = flexibleSearchService.search(query);

		return results.getResult();
	}

	public <T> List<T> getResultsWithoutSearchRestrictions(final String queryString,
								  final Map<String, String> parameters,
								  final Class<T> resultType)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.setResultClassList(Collections.singletonList(resultType));
		query.addQueryParameters(parameters);
		query.setNeedTotal(false);
		searchRestrictionService.disableSearchRestrictions();
		final SearchResult<T> results = flexibleSearchService.search(query);
		
		return results.getResult();
	}

	@Override
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Override
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
