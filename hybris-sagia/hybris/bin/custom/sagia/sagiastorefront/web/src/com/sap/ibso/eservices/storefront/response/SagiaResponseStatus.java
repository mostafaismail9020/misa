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
package com.sap.ibso.eservices.storefront.response;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.response
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public enum SagiaResponseStatus
{
	SUCCESS("SUCCESS"),
	VALIDATION_ERROR("VALIDATION_ERROR"),
	ERROR("ERROR")
	;
	private String name;

	SagiaResponseStatus(final String status)
	{
		this.name = status;
	}

	public String getStatus()
	{
		return name;
	}
}
