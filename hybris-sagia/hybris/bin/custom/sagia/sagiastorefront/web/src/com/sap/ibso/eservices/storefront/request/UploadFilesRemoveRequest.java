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
package com.sap.ibso.eservices.storefront.request;

import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.request
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class UploadFilesRemoveRequest
{
	private String       code;
	private List<String> codes;

	public String getCode()
	{
		return code;
	}

	public void setCode(final String code)
	{
		this.code = code;
	}

	public List<String> getCodes()
	{
		return codes;
	}

	public void setCodes(final List<String> codes)
	{
		this.codes = codes;
	}
}
