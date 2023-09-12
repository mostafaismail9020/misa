package com.investsaudi.portal.facades.solrfacetsearch.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

public class SagiaPdfNameValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{

    private FieldNameProvider fieldNameProvider;
	
	private String getPdfName(final Object model) {
		if (model instanceof ProductModel) {
			ProductModel product = (ProductModel) model;
			if(CollectionUtils.isNotEmpty(product.getDetail())) {
				String fileName = product.getDetail().iterator().next().getRealFileName();
				if (StringUtils.isNotEmpty(fileName)) {
					fileName = fileName.replace(".pdf", "");
					Set<String> fileNames = new HashSet<>();
					fileNames.add(fileName);
					fileNames.addAll(Arrays.asList(fileName.split("-")));
					fileNames.addAll(Arrays.asList(fileName.split("_")));
					return StringUtils.join(fileNames, " ");
				}
			}
		}
		return StringUtils.EMPTY;
	}

	@Override
	public Collection<FieldValue> getFieldValues(IndexConfig var1, IndexedProperty indexedProperty, Object model)
			throws FieldValueProviderException {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();

        fieldValues.addAll(createFieldValue(model, indexedProperty));
        return fieldValues;
	}


	protected List<FieldValue> createFieldValue(final Object model,
	                                            final IndexedProperty indexedProperty) throws FieldValueProviderException
	{
	    final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
	
	    final String pdfName = getPdfName(model);
	    if (pdfName != null)
	    {
	        addFieldValues(fieldValues, indexedProperty, pdfName);
	    }
	
	    return fieldValues;
	}
	
	
	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
	                              final Object value)
	{
	    final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);
	    for (final String fieldName : fieldNames)
	    {
	        fieldValues.add(new FieldValue(fieldName, value));
	    }
	}

	
	protected FieldNameProvider getFieldNameProvider()
	{
	    return fieldNameProvider;
	}
	
	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
	    this.fieldNameProvider = fieldNameProvider;
	}

}
