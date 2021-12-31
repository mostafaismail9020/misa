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
package com.sap.ibso.eservices.backoffice.exception;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaIllegalPatternException extends RuntimeException
{
	public SagiaIllegalPatternException(final String message)
	{
		super(message);
	}
}
