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

import java.util.Date;
import java.util.List;

import com.sap.ibso.eservices.core.model.SagiaJsonUtilityModel;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.dao
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaJsonUtilityDAO
{
	List<SagiaJsonUtilityModel> getJsonUtility(String userPk, String serviceId);

	List<SagiaJsonUtilityModel> getJsonUtilities(String userPk);

	List<SagiaJsonUtilityModel> getJsonUtilities(Date date);
}
