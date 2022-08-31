/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.facades.solrfacetsearch.populators;

import com.sap.ibso.eservices.facades.data.InvestmentHighlightsReportData;
import de.hybris.platform.commercefacades.product.ImageFormatMapping;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ImageDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


/**
 * Converter implementation for {@link SearchResultValueData} as
 * source and {@link ProductData} as target type.
 */
public class SearchResultInvestmentHighlightsReportPopulator implements Populator<SearchResultValueData, InvestmentHighlightsReportData>
{
	private ImageFormatMapping imageFormatMapping;
	private CommonI18NService commonI18NService;

	protected ImageFormatMapping getImageFormatMapping()
	{
		return imageFormatMapping;
	}

	@Required
	public void setImageFormatMapping(final ImageFormatMapping imageFormatMapping)
	{
		this.imageFormatMapping = imageFormatMapping;
	}

	protected CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	@Override
	public void populate(final SearchResultValueData source, final InvestmentHighlightsReportData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		// Pull the values directly from the SearchResult object
		target.setResourceTitle(this.<String> getValue(source, "resourceTitle"));
		target.setResourceDate(this.<String> getValue(source, "resourceDate"));
		target.setRegionSpecific(this.<String> getValue(source, "regionSpecific"));
		target.setResourceBulletPoints(this.<String> getValue(source, "resourceBulletPoints"));
		target.setResourceThumbnailImage(this.<String> getValue(source, "resourceThumbnailImage"));
		target.setResourceFullReport(this.<String> getValue(source, "resourceFullReportURL"));
		target.setResourceShortInformation(this.<String> getValue(source, "resourceShortInformation"));
		/*final List<ImageData> images = createImageData(source);
		if (CollectionUtils.isNotEmpty(images))
		{
			target.setImages(images);
		}
*/
	}



	protected List<ImageData> createImageData(final SearchResultValueData source)
	{
		final List<ImageData> result = new ArrayList<ImageData>();

		addImageData(source, "thumbnail", result);
		addImageData(source, "product", result);

		return result;
	}

	protected void addImageData(final SearchResultValueData source, final String imageFormat, final List<ImageData> images)
	{
		final String mediaFormatQualifier = getImageFormatMapping().getMediaFormatQualifierForImageFormat(imageFormat);
		if (mediaFormatQualifier != null && !mediaFormatQualifier.isEmpty())
		{
			addImageData(source, imageFormat, mediaFormatQualifier, ImageDataType.PRIMARY, images);
		}
	}

	protected void addImageData(final SearchResultValueData source, final String imageFormat, final String mediaFormatQualifier,
			final ImageDataType type, final List<ImageData> images)
	{
		final String imgValue = getValue(source, "img-" + mediaFormatQualifier);
		if (imgValue != null && !imgValue.isEmpty())
		{
			final ImageData imageData = createImageData();
			imageData.setImageType(type);
			imageData.setFormat(imageFormat);
			imageData.setUrl(imgValue);

			images.add(imageData);
		}
	}


	protected <T> T getValue(final SearchResultValueData source, final String propertyName)
	{
		if (source.getValues() == null)
		{
			return null;
		}

		// DO NOT REMOVE the cast (T) below, while it should be unnecessary it is required by the javac compiler
		return (T) source.getValues().get(propertyName);
	}

	protected PromotionData createPromotionData()
	{
		return new PromotionData();
	}

	protected ImageData createImageData()
	{
		return new ImageData();
	}
}
