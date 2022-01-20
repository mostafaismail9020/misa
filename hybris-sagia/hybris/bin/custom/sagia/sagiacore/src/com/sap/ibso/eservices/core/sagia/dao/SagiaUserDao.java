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
package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.investsaudi.portal.core.model.ContactTicketModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.ticket.model.CsTicketModel;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.dao
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaUserDao
{
	List<CustomerModel> getCustomers(final String uid, final String mobileNumber, final String mobileCountryCode, final String email);
	List<CustomerModel> getCustomerByUid(final String uid);
	List<CustomerModel> getCustomerByMobileNumber(final String mobileNumber, final String mobileCountryCode);
	List<CustomerModel> getCustomerByEmail(final String email);
	List<CsTicketModel> getAllTicketsByCustomerUnderGiveUserGroup(String userGroupUid);
	List<B2BCustomerModel> getAllWOBDCustomerWithNoTickets(String userGroupUid);
	List<ContactTicketModel> getUserRaisedOpportunities(String contactEmail);
	ContactTicketModel getContactTicketForTicketId(String ticketId);
}
