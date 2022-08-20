package com.investsaudi.portal.facades.solrfacetsearch.providers;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.investsaudi.portal.core.model.ProvinceComponentModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.url.UrlResolver;
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

public class SagiaRegionValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{
    private FieldNameProvider fieldNameProvider;
    private CommonI18NService commonI18NService;

    protected FieldNameProvider getFieldNameProvider()
    {
        return fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
    {
        this.fieldNameProvider = fieldNameProvider;
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
                                                 final Object model) throws FieldValueProviderException {
        if (model == null) {
            throw new IllegalArgumentException("No model given");
        }
        final List<FieldValue> fieldValues = new ArrayList<>();
//        if (model instanceof OpportunityProductModel && ((OpportunityProductModel) model).getRegionSpecific()!= null && ((OpportunityProductModel) model).getRegionSpecific()) {
        if (model instanceof OpportunityProductModel) {
            List<ProvinceComponentModel> regions = (List<ProvinceComponentModel>) ((OpportunityProductModel) model).getRegion();
            for (ProvinceComponentModel region : emptyIfNull(regions)) {
                final Object value = region.getUid();
                final Collection<String> filedNames = getFieldNameProvider().getFieldNames(indexedProperty,null);
                for (final String filedName : filedNames){

                    fieldValues.add(new FieldValue(filedName, value));
                }
            }
        }
        return  fieldValues;
    }

}
