/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiaservices.core.conv;

import com.thoughtworks.xstream.converters.Converter;


/**
 * Abstract implementation of {@link RedirectableConverter} interface. Contains implementation of methods common to all
 * {@link RedirectableConverter} interface implementations.
 */
public abstract class AbstractRedirectableConverter implements RedirectableConverter
{
	private Converter targetConverter;

	@Override
	public void setTargetConverter(final Converter converter)
	{
		this.targetConverter = converter;

	}

	protected Converter getTargetConverter()
	{
		return targetConverter;
	}


}
