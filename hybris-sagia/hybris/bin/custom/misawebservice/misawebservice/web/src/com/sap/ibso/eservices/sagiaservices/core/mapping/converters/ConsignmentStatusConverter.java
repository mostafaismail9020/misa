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
package com.sap.ibso.eservices.sagiaservices.core.mapping.converters;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.webservicescommons.mapping.WsDTOMapping;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;


/**
 * Bidirectional converter between {@link ConsignmentStatus} and String
 */
@WsDTOMapping
public class ConsignmentStatusConverter extends BidirectionalConverter<ConsignmentStatus, String>
{
	@Override
	public ConsignmentStatus convertFrom(final String source, final Type<ConsignmentStatus> destinationType,
			final MappingContext mappingContext)
	{
		return ConsignmentStatus.valueOf(source);
	}

	@Override
	public String convertTo(final ConsignmentStatus source, final Type<String> destinationTyp,
			final MappingContext mappingContexte)
	{
		return source.toString();
	}
}
