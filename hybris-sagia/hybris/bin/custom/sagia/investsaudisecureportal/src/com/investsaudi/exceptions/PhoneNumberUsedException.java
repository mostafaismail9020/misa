/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.investsaudi.exceptions;

import de.hybris.platform.servicelayer.exceptions.BusinessException;


/**
 * Thrown when a phone number already used
 */
public class PhoneNumberUsedException extends BusinessException
{

	public PhoneNumberUsedException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	public PhoneNumberUsedException(final String message)
	{
		super(message);
	}

	public PhoneNumberUsedException(final Throwable cause)
	{
		super(cause);

	}

}
