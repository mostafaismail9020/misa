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
package com.sap.ibso.eservices.sagiaservices.core.queues.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.sap.ibso.eservices.sagiaservices.core.queues.data.ProductExpressUpdateElementData;

import org.springframework.util.Assert;


/**
 * Class populate information from ProductModel to ProductExpressUpdateElementData
 */
public class ProductExpressUpdateElementPopulator implements Populator<ProductModel, ProductExpressUpdateElementData>
{
	@Override
	public void populate(final ProductModel source, final ProductExpressUpdateElementData target) 
			throws ConversionException //NOSONAR
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());
		if (source.getCatalogVersion() != null)
		{
			target.setCatalogVersion(source.getCatalogVersion().getVersion());
			if (source.getCatalogVersion().getCatalog() != null)
			{
				target.setCatalogId(source.getCatalogVersion().getCatalog().getId());
			}
		}
	}
}
