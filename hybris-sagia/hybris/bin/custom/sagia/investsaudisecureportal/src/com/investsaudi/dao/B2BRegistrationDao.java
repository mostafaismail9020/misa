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
package com.investsaudi.dao;

import de.hybris.platform.core.model.user.EmployeeModel;

import java.util.List;


/**
 * DAO with B2B registration specific methods
 */
public interface B2BRegistrationDao
{

	/**
	 * @param userGroup
	 *           The user group to look for
	 * @return All employees that are part of the specified user group
	 */
	public List<EmployeeModel> getEmployeesInUserGroup(String userGroup);

}
