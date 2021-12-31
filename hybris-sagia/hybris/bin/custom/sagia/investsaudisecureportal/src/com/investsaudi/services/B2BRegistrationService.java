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
package com.investsaudi.services;

import com.investsaudi.data.SagiaB2BUnitData;
import com.investsaudi.data.SagiaUserData;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.core.model.user.EmployeeModel;

import java.util.List;


/**
 * Service methods that are used by the B2B registration process.
 */
public interface B2BRegistrationService
{

	/**
	 * Gets the list of employees that are part of a given user group.
	 *
	 * @param userGroup           The name of the user group
	 * @return Employees within the user group
	 */
	public List<EmployeeModel> getEmployeesInUserGroup(String userGroup);

	/**
	 * Gets the contact email address of the specified list of employees
	 * 
	 * @param employees
	 *           List of employees to get email address from
	 * @return List of email addresses. It is possible that the list is empty since employees are not required to have an
	 *         email nor a contact address
	 */
	public List<EmailAddressModel> getEmailAddressesOfEmployees(List<EmployeeModel> employees);

	/**
	 * Creates the B2BUnit.
	 *
	 * @param data the data
     */
	void createChildUnit(SagiaB2BUnitData data);

    /**
     * Gets List of Child Units for Current User.
     *
     * @param b2BUnitModel the b 2 B unit model
     * @return the child units
     */
    List<SagiaB2BUnitData> getChildUnits(B2BUnitModel b2BUnitModel);

    SagiaB2BUnitData getB2BUnitforCrurrentUser();

    /**
     * Creates the B2BUser.
     *
     * @param data the data
     * @return the b 2 B customer model
     */
    B2BCustomerModel createUser(SagiaUserData data);
}
