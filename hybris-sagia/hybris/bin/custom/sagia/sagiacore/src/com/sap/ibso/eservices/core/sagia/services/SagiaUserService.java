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
package com.sap.ibso.eservices.core.sagia.services;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.sap.ibso.eservices.core.sagia.enums.ValidationError;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;
import java.util.Set;

/**
 * Provides access to the User Service
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaUserService extends UserService
{
	/**
	 * validates UniqueUserAttributes
	 * @param uid uid
	 * @param email email
	 * @return List of ValidationError
	 */
	List<ValidationError> validateUniqueUserAttributes(String uid, String email);

	/**
	 * validates Uniqueness
	 * @param userName userName
	 * @param email email
	 * @return boolean
	 */
	boolean validateUniqueness(final String userName, final String email);

	/**
	 * Returns a customer by her email.
	 * @param email - The email for which the customer is retrieved
	 * @return CustomerModel
	 */
	CustomerModel getCustomerByEmail(final String email);

	/**
	 * Returns a customer by her mobile number.
	 * @param email - The email for which the customer is retrieved
	 * @return CustomerModel
	 */
	CustomerModel getCustomerByMobileNumber(String mobileNumber, String mobileCountryCode);

    /**
     * Gets the approvers email.
     *
     * @param b2BUnitModel the B2BUnitModel
     * @return the approvers email
     */
    List<String> getApproversEmail(B2BUnitModel b2BUnitModel);

	/**
	 * Gets all child b 2 b customers.
	 *
	 * @return the all child b 2 b customers
	 */
	Set<B2BCustomerModel> getAllChildB2BCustomers();

	/**
	 * Gets ContactTicket Created by the customer
	 * @param contactEmail contactEmail
	 * @return all the tickets raised by the customer
	 */
	List<ContactTicketModel> getUserRaisedOpportunities(String contactEmail);

	/**
	 * Gets ContactTicket Created by the customer
	 * @param ticketId ticketId
	 * @return the ticket raised by the customer
	 */
	ContactTicketModel getContactTicketForTicketId(String ticketId);

	/**
	 * Add comments to ContactTicket
	 * @param ticketId ticketId
	 * @param comments comments
	 * @return the ticket raised by the customer
	 */
	ContactTicketModel addContactTicketComments(String ticketId, String comments);

        boolean attachServiceRequestToContactTicket(ServiceRequestModel serviceRequest, String ticketId);



  	  /**
    	 * Add comments to ContactTicket
    	 * @param ticketId ticketId
    	 * @param comments comments
    	 * @return the ticket raised by the customer
    	 */
        public void saveTicketAttachments(final byte[] bytes, final String ticketId,
    			final MediaModel mediaModel);

}
