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
package com.sap.ibso.eservices.core.sagia.enums;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.enums
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public enum ValidationError
{
	DUPLICATE_UID(101),
	DUPLICATE_EMAIL(102),
	DUPLICATE_MOBILE_NUMBER(103)
	;

	private Integer _title;

	ValidationError(final Integer _title)
	{
		this._title = _title;
	}

	public Integer getTitle()
	{
		return _title;
	}
}
