package com.investsaudi.portal.facades.solrfacetsearch.providers;

import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SagiaResourceComponentFullReportValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
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
        if (model instanceof InvestSaudiResourceComponentModel)
        {
            final InvestSaudiResourceComponentModel investSaudiResourceComponent = (InvestSaudiResourceComponentModel) model;

            final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();

            if (indexedProperty.isLocalized())
            {
                final Collection<LanguageModel> languages = indexConfig.getLanguages();
                for (final LanguageModel language : languages)
                {
                    fieldValues.addAll(createFieldValue(investSaudiResourceComponent, language, indexedProperty));
                }
            }
            else
            {
                fieldValues.addAll(createFieldValue(investSaudiResourceComponent, null, indexedProperty));
            }
            return fieldValues;
        }
        else
        {

            throw new FieldValueProviderException("Error :"+ model);
        }
    }

    protected List<FieldValue> createFieldValue(final InvestSaudiResourceComponentModel investSaudiResourceComponentModel, final LanguageModel language,
                                                final IndexedProperty indexedProperty)
    {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

        final String reportURL = getReportURL(investSaudiResourceComponentModel, language);


        if (reportURL != null)
        {
            addFieldValues(fieldValues, indexedProperty, language, reportURL);
        }

        return fieldValues;
    }

    private String getReportURL(InvestSaudiResourceComponentModel investSaudiResourceComponent, LanguageModel language) {

        if (investSaudiResourceComponent.getResourceFullReport() == null) {
            return null;
        }
        if ( "EN".equalsIgnoreCase(language.getIsocode())){
            for ( MediaModel media : investSaudiResourceComponent.getResourceFullReport().getMedias() ) {
                if ( media.getURL().contains("english") ) {
                    return media.getDownloadURL();
                }
            }
        }else {
            for ( MediaModel media : investSaudiResourceComponent.getResourceFullReport().getMedias() ) {
                if ( media.getURL().contains("arabic") ) {
                    return media.getDownloadURL();
                }
            }
        }
        return null;
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
