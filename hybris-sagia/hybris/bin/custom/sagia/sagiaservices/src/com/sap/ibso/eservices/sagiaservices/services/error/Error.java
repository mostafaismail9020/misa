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
package com.sap.ibso.eservices.sagiaservices.services.error;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.error
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class Error {
	@JsonProperty("message")
	public Message message;
	@JsonProperty("code")
	public String code;

	/**
	 * @return
	 */
	public Message getMessage()
	{
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(final Message message)
	{
		this.message = message;
	}

	/**
	 * @return
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(final String code)
	{
		this.code = code;
	}
}