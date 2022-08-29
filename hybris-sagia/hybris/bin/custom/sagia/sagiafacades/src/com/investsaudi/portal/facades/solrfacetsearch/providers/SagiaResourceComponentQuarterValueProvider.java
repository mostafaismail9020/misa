package com.investsaudi.portal.facades.solrfacetsearch.providers;

import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SagiaResourceComponentQuarterValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{

    private FieldNameProvider fieldNameProvider;
    private CommonI18NService commonI18NService;
    private MessageSource messageSource;



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

        final String yearValue = getQuarterValue(investSaudiResourceComponentModel, language);


        if (yearValue != null)
        {
            addFieldValues(fieldValues, indexedProperty, language, yearValue);
        }

        return fieldValues;
    }

    private String getQuarterValue(InvestSaudiResourceComponentModel investSaudiResourceComponent, LanguageModel language) {

        Map<String, String> mapVals = createquarterMap(language);
        Date date = investSaudiResourceComponent.getResourceDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return mapVals.get(String.valueOf(calendar.get(Calendar.MONTH)));
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


    private  Map<String, String> createquarterMap(LanguageModel language) {
        Map<String, String> monthVocab = new HashMap<>();
        monthVocab.put("1", getLocalizedValue("1",language));
        monthVocab.put("2", getLocalizedValue("1",language));
        monthVocab.put("3", getLocalizedValue("1",language));
        monthVocab.put("4", getLocalizedValue("2",language));
        monthVocab.put("5", getLocalizedValue("2",language));
        monthVocab.put("6", getLocalizedValue("2",language));
        monthVocab.put("7", getLocalizedValue("3",language));
        monthVocab.put("8", getLocalizedValue("3",language));
        monthVocab.put("9", getLocalizedValue("3",language));
        monthVocab.put("10", getLocalizedValue("4",language));
        monthVocab.put("11", getLocalizedValue("4",language));
        monthVocab.put("12", getLocalizedValue("4",language));
       return monthVocab;
    }

    private  String  getLocalizedValue(String messageKey,  LanguageModel language) {
        try {
            Locale local = new Locale(language.getIsocode());
            return messageSource.getMessage(messageKey, null, local );
        } catch (Exception ex) {
            return messageKey;
        }
    }


    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
