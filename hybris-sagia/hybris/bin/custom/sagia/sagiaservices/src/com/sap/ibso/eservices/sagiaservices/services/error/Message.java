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
public class Message {
	@JsonProperty("lang")
	public String lang;
	@JsonProperty("value")
	public String value;

	/**
	 * @return
	 */
	public String getLang()
	{
		return lang;
	}

	/**
	 * @param lang
	 */
	public void setLang(final String lang)
	{
		this.lang = lang;
	}

	/**
	 * @return
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(final String value)
	{
		this.value = value;
	}
}
