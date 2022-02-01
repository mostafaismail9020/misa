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
package com.sap.ibso.eservices.facades.user;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import com.sap.ibso.eservices.facades.data.SagiaServiceRequestFormData;
import com.sap.ibso.eservices.core.enums.IncidentCategory;
import com.sap.ibso.eservices.core.enums.ServiceCategory;
import com.sap.ibso.eservices.core.enums.Priority;


import java.util.List;

/**
 * SagiaUserFacade
 *
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.user
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaUserFacade
{
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
	 * Gets all child b 2 b customers.
	 *
	 * @return the all child b 2 b customers
	 */
	List<CustomerData> getAllChildB2BCustomers();
	
	boolean validateSagiaUerFormData(final SagiaServiceRequestFormData sagiaServiceRequestFormData, final String ticketId);
	
	List<IncidentCategory> getIncidentCategoryEnumValues();
	
	List<ServiceCategory> getServiceCategoryEnumValues();
	
	List<Priority> getPriorityEnumValues();
}
