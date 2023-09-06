package com.investsaudi.portal.facades.solrfacetsearch.providers;

import com.investsaudi.portal.core.jalo.NewsProduct;
import com.investsaudi.portal.core.model.EventProductModel;
import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import com.investsaudi.portal.core.model.NewsProductModel;

import com.investsaudi.portal.core.model.ReportProductModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import org.springframework.beans.factory.annotation.Required;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SagiaProductCreationDateValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{

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
                                                 final Object model) throws FieldValueProviderException
    {
        if (model instanceof ProductModel)
        {
            final ProductModel product = (ProductModel) model;

            final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();

            if (indexedProperty.isLocalized())
            {
                final Collection<LanguageModel> languages = indexConfig.getLanguages();
                for (final LanguageModel language : languages)
                {
                    fieldValues.addAll(createFieldValue(product, language, indexedProperty));
                }
            }
            else
            {
                fieldValues.addAll(createFieldValue(product, null, indexedProperty));
            }
            return fieldValues;
        }
        else
        {

            throw new FieldValueProviderException("Error :"+ model);
        }
    }

    protected List<FieldValue> createFieldValue(final ProductModel product, final LanguageModel language,
                                                final IndexedProperty indexedProperty)
    {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
        final String formatedDate = formatDate(product);


        if (formatedDate != null)
        {
            addFieldValues(fieldValues, indexedProperty, language, formatedDate);
        }

        return fieldValues;
    }

    private String formatDate(ProductModel product) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (product instanceof NewsProductModel) {
        	return dateFormat.format(((NewsProductModel) product).getNewsDate());
		}
        else if (product instanceof EventProductModel) {
        	return dateFormat.format(((EventProductModel) product).getEventDate());
		}
        else if (product instanceof ReportProductModel) {
            return dateFormat.format(((ReportProductModel) product).getReportDate());
        }
		return dateFormat.format(product.getCreationtime());
	}

	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
                                  final LanguageModel language, final Object value)
    {
        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
                language == null ? null : language.getIsocode());
        for (final String fieldName : fieldNames)
        {
            fieldValues.add(new FieldValue(fieldName, value));
        }
    }

}
