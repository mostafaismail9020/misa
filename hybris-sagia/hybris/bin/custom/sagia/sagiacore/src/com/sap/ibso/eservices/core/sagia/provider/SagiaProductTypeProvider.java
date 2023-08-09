/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.core.sagia.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.investsaudi.portal.core.model.ArticleProductModel;
import com.investsaudi.portal.core.model.EventProductModel;
import com.investsaudi.portal.core.model.NewsProductModel;

import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

public class SagiaProductTypeProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider {

	private FieldNameProvider fieldNameProvider;
	
	protected FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}
	
	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model)
			throws FieldValueProviderException {
		return createFieldValues(indexedProperty, getProductType(model));
	}

	private String getProductType(Object model) {
		if (model instanceof NewsProductModel) {
			return "News";
		}
		else if (model instanceof ArticleProductModel) {
			return "Article";
		}
		else if (model instanceof EventProductModel) {
			return "Event";
		}
		return "Opportunity";
	}

	protected Collection<FieldValue> createFieldValues(final IndexedProperty indexedProperty, final String value)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}

		return fieldValues;
	} 
}
