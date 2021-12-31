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
package com.sap.ibso.eservices.facades.sagia;


import com.sap.ibso.eservices.facades.data.AccountManagerData;

/**
 * Provides access to SAccountManagerFacade
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaAccountManagerFacade
{
	/**
	 * Retrieves AccountManagerData
	 * @return AccountManagerData
	 */
	AccountManagerData getAccountManagerData();
}
