package com.investsaudi.portal.facades.solrfacetsearch.providers;

import org.apache.commons.lang3.StringUtils;

import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ProductUrlValueProvider;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;

public class SagiaProductImageUrlValueProvider extends ProductUrlValueProvider 
{
	@Override
	protected String getProductUrl(final ProductModel product, final LanguageModel language)
	{
		return getProductImageURL(product);
	}

	private String getProductImageURL(final ProductModel product) {
		return null != product.getPicture() ? product.getPicture().getURL() : StringUtils.EMPTY;
	}
}
