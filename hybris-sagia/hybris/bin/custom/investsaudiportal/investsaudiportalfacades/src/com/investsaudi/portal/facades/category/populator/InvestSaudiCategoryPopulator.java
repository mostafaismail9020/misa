/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.facades.category.populator;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.converters.populator.CategoryPopulator;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.core.model.media.MediaModel;

import java.util.Optional;

/**
 * Converter implementation for {@link CategoryModel} as source and
 * {@link CategoryData} as target type.
 */
public class InvestSaudiCategoryPopulator extends CategoryPopulator
{

	@Override
	public void populate(final CategoryModel source, final CategoryData target)
	{
		super.populate(source, target);
		final MediaModel picture = source.getPicture();
		final MediaModel thumbnail = source.getThumbnail();
		if (picture != null)
		{
			target.setPicture(getImageConverter().convert(picture));
			target.getPicture().setAltText(picture.getAltText());
		}
		if (thumbnail != null)
		{
			target.setThumbnail(getImageConverter().convert(thumbnail));
			target.getThumbnail().setAltText(thumbnail.getAltText());
		}
		final Optional<CategoryModel> parentCategory = source.getSupercategories().stream().findFirst();
		if(parentCategory.isPresent()){
			target.setParentCategoryCode(parentCategory.get().getCode());
		}
	}
}
