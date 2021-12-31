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
public class SagiaResponse
{
	private SagiaResponseStatus status;
	private String message;

	public SagiaResponse(final SagiaResponseStatus status, final String message)
	{
		this.status = status;
		this.message = message;
	}

	public SagiaResponseStatus getStatus()
	{
		return status;
	}

	public void setStatus(final SagiaResponseStatus status)
	{
		this.status = status;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}
}
