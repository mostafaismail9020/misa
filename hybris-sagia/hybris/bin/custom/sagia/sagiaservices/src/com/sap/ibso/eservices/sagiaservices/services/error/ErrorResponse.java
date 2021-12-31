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
public class ErrorResponse {
	@JsonProperty("error")
	public Error error;

	/**
	 * @return
	 */
	public Error getError()
	{
		return error;
	}

	/**
	 * @param error
	 */
	public void setError(final Error error)
	{
		this.error = error;
	}
}




