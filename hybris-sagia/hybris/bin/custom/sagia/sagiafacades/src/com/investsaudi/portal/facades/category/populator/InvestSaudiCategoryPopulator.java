/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.facades.category.populator;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.converters.populator.CategoryPopulator;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.commercefacades.product.data.SectorFactsFiguresData;
import com.investsaudi.portal.core.model.SectorFactsFiguresComponentModel;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;


/**
 * Converter implementation for {@link CategoryModel} as source and
 * {@link CategoryData} as target type.
 */
public class InvestSaudiCategoryPopulator extends CategoryPopulator
{

    @Resource
    private I18NService i18NService;
    
    
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
		
		final Collection<MediaModel> logoImages = source.getLogo();
		if (CollectionUtils.isNotEmpty(logoImages))
		{
			target.setLogo(getImageConverter().convert(logoImages.iterator().next()));
		}
		
		final Collection<MediaModel> bannerImages = source.getOthers();
		if (CollectionUtils.isNotEmpty(bannerImages))
		{
			target.setBanner(getImageConverter().convert(bannerImages.iterator().next()));
		}
		
		final Collection<MediaModel> normalImages = source.getNormal();
		if (CollectionUtils.isNotEmpty(normalImages))
		{
			target.setNormal(getImageConverter().convert(normalImages.iterator().next()));
		}
		
		final Optional<CategoryModel> parentCategory = source.getSupercategories().stream().findFirst();
		if(parentCategory.isPresent()){
			target.setParentCategoryCode(parentCategory.get().getCode());
		}
		
		Locale currentLocale = i18NService.getCurrentLocale();
		target.setOverview(source.getSectorOverview(currentLocale));
		target.setShortOverview(source.getSectorShortOverview(currentLocale));
		
		populateCategorySectorFactsFiguresData(source, target);
	}
	
	 private void populateCategorySectorFactsFiguresData(CategoryModel categoryModel, CategoryData categoryData) {
		 SectorFactsFiguresData factsFiguresData = null;
		 List<SectorFactsFiguresData> sectorFactsFiguresList = new ArrayList<SectorFactsFiguresData>();
		 
		 Locale currentLocale = i18NService.getCurrentLocale();
		 Collection<SectorFactsFiguresComponentModel> sectorFactsFigures = categoryModel.getSectorFactsFigures();
		 		
		 for (SectorFactsFiguresComponentModel factsFigures : emptyIfNull(sectorFactsFigures)) {
			factsFiguresData = new SectorFactsFiguresData();
			factsFiguresData.setFigures(factsFigures.getFigures());
			factsFiguresData.setUnit(factsFigures.getUnit());
			factsFiguresData.setFacts(factsFigures.getFacts(currentLocale));
			
			sectorFactsFiguresList.add(factsFiguresData);
		 }
		 categoryData.setSectorFactsFigures(sectorFactsFiguresList);
    }
}
