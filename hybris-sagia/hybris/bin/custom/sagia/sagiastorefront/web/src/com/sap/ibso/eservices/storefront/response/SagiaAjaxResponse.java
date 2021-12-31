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
public class SagiaAjaxResponse
{
	private String message;
	private Integer status;


	public SagiaAjaxResponse(final Integer status)
	{
		this.status = status;
	}

	public SagiaAjaxResponse(final String message, final Integer status)
	{
		this.message = message;
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

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(final Integer status)
	{
		this.status = status;
	}
}
