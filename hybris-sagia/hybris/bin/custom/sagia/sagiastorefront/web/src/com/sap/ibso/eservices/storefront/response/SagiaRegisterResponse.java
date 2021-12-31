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

import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.response
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaRegisterResponse
{

	private Integer code;
	private List<Integer> errors;

	public SagiaRegisterResponse(final Integer code)
	{
		this.code = code;
	}

	public SagiaRegisterResponse(final Integer code, final List<Integer> errors)
	{
		this.code = code;
		this.errors = errors;
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(final Integer code)
	{
		this.code = code;
	}

	public List<Integer> getErrors()
	{
		return errors;
	}

	public void setErrors(final List<Integer> errors)
	{
		this.errors = errors;
	}
}


