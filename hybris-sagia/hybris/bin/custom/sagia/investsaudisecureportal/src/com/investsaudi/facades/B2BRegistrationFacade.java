/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.investsaudi.facades;

import com.investsaudi.data.B2BRegistrationData;
import com.investsaudi.data.SagiaB2BUnitData;
import com.investsaudi.data.SagiaUserData;
import com.investsaudi.exceptions.CustomerAlreadyExistsException;
import com.investsaudi.exceptions.PhoneNumberUsedException;
import de.hybris.platform.b2b.model.B2BCustomerModel;

import java.util.List;


/**
 * Facade responsible for everything related to B2B registration
 */
public interface B2BRegistrationFacade
{

	/**
	 * Initiates the registration process for B2B. This method will first validate the submitted data, check if a user or
	 * a company to the given name already exists, persist the registration request (as a model) and initiate the
	 * workflow so that the registration request either gets approved OR rejected.
	 * 
	 * @param data
	 *           The registration data
	 * @throws PhoneNumberUsedException 
	 */
	public void register(B2BRegistrationData data) throws CustomerAlreadyExistsException, PhoneNumberUsedException;
	
	/**
	 * validates UniqueValue
	 * @param userName userName
	 * @param email email
	 * @param mobileNumber mobileNumber
	 * @param mobileCountryCode mobileCountryCode
 	 * @return boolean
	 */
	boolean validateUniqueValue(final String userName, final String email, final String mobileNumber, final String mobileCountryCode);

	/**
	 * Creates the child unit.
	 *
	 * @param data the data
	 */
	void createChildUnit(SagiaB2BUnitData data);
    
    /**
     * Creates the user.
     *
     * @param data the data
     * @return true, if successful
     */
    B2BCustomerModel createUser(SagiaUserData data) throws PhoneNumberUsedException, CustomerAlreadyExistsException;

	/**
	 * Gets the child units for current user.
	 *
	 * @return the child units for current user
	 */
	List<SagiaB2BUnitData> getChildUnitsForCurrentUser();

	SagiaB2BUnitData getB2BUnitforCrurrentUser();

	/**
	 * Gets the user group for new user.
	 *
	 * @return the user group for new user
	 */
	List<String> getUserGroupForNewUser();
}
