package com.investsaudi.portal.facades.solrfacetsearch.providers;

import com.investsaudi.portal.core.model.ArticleProductModel;
import com.investsaudi.portal.core.model.EventProductModel;
import com.investsaudi.portal.core.model.NewsProductModel;
import com.investsaudi.portal.core.model.OpportunityProductModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class SagiaProductUrlValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{
	private UrlResolver<ProductModel> urlResolver;
    private FieldNameProvider fieldNameProvider;
    private CommonI18NService commonI18NService;
    private static final String FORWARD_SLASH = "/";
    private static final String SECTOR_URL = "/sectors-opportunities/";
    private static final String EVENT_URL = "/mediaCenter/events/";
    private static final String NEWS_URL = "/mediaCenter/news/";

    protected FieldNameProvider getFieldNameProvider()
    {
        return fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
    {
        this.fieldNameProvider = fieldNameProvider;
    }

    protected UrlResolver<ProductModel> getUrlResolver()
    {
        return urlResolver;
    }

    @Required
    public void setUrlResolver(final UrlResolver<ProductModel> urlResolver)
    {
        this.urlResolver = urlResolver;
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
    public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
                                                 final Object model) throws FieldValueProviderException
    {
            final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();

            if (indexedProperty.isLocalized())
            {
                final Collection<LanguageModel> languages = indexConfig.getLanguages();
                for (final LanguageModel language : languages)
                {
                    fieldValues.addAll(createFieldValue(model, language, indexedProperty));
                }
            }
            else
            {
                fieldValues.addAll(createFieldValue(model, null, indexedProperty));
            }
            return fieldValues;
    }

    protected List<FieldValue> createFieldValue(final Object model, final LanguageModel language,
                                                final IndexedProperty indexedProperty) throws FieldValueProviderException
    {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

        final String productUrl = getProductUrl(model, language);
        if (productUrl != null)
        {
            addFieldValues(fieldValues, indexedProperty, language, productUrl);
        }

        return fieldValues;
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

    protected String getProductUrl(final Object model, final LanguageModel language) throws FieldValueProviderException
    {
        String url= FORWARD_SLASH;
    	if(model instanceof OpportunityProductModel) {
    		final OpportunityProductModel opportunityProduct = (OpportunityProductModel) model;
   
        if (CollectionUtils.isNotEmpty(opportunityProduct.getSupercategories())) {

            List<CategoryModel> prdSuperCategory = (List<CategoryModel>) opportunityProduct.getSupercategories();

            final Optional<CategoryModel> parentCategory = emptyIfNull(opportunityProduct.getSupercategories()).stream().findFirst();
            if (parentCategory.isPresent()) {
                url=  SECTOR_URL + parentCategory.get().getCode() + FORWARD_SLASH + opportunityProduct.getCode();
            }
        }
    	}
    	else if (model instanceof ArticleProductModel) {
			return "/aboutSaudi";			
		}
    	else if (model instanceof EventProductModel) {
			return EVENT_URL + ((EventProductModel) model).getCode();
		}
    	else if (model instanceof NewsProductModel) {
    		return NEWS_URL +  ((NewsProductModel) model).getCode();	
    	}
        else
        {
            throw new FieldValueProviderException("Category structure not valid for :"+ ((ItemModel) model).getPk());
        }
        return url;
    }
}
