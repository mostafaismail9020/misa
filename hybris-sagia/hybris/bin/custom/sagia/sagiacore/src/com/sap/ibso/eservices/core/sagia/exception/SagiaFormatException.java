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
package com.sap.ibso.eservices.core.sagia.exception;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.exception
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaFormatException extends RuntimeException
{
	public SagiaFormatException()
	{
	}

	public SagiaFormatException(final String message)
	{
		super(message);
	}
}
