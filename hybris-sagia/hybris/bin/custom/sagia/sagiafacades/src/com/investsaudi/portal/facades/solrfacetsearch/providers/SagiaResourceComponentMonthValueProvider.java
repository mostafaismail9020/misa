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

public class SagiaResourceComponentMonthValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
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

        final String monthValue = getMonthValue(investSaudiResourceComponentModel, language);


        if (monthValue != null)
        {
            addFieldValues(fieldValues, indexedProperty, language, monthValue);
        }

        return fieldValues;
    }

    private String getMonthValue(InvestSaudiResourceComponentModel investSaudiResourceComponent, LanguageModel language) {

        Map<String, String> mapVals = createMonthMap(language);
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


    private  Map<String, String> createMonthMap(LanguageModel language) {
        Map<String, String> monthVocab = new HashMap<>();
        monthVocab.put(String.valueOf(Calendar.JANUARY), getLocalizedValue("month.january",language));
        monthVocab.put(String.valueOf(Calendar.FEBRUARY), getLocalizedValue("month.february",language));
        monthVocab.put(String.valueOf(Calendar.MARCH), getLocalizedValue("month.march",language));
        monthVocab.put(String.valueOf(Calendar.APRIL), getLocalizedValue("month.april",language));
        monthVocab.put(String.valueOf(Calendar.MAY), getLocalizedValue("month.may",language));
        monthVocab.put(String.valueOf(Calendar.JUNE), getLocalizedValue("month.june",language));
        monthVocab.put(String.valueOf(Calendar.JULY), getLocalizedValue("month.july",language));
        monthVocab.put(String.valueOf(Calendar.AUGUST), getLocalizedValue("month.august",language));
        monthVocab.put(String.valueOf(Calendar.SEPTEMBER), getLocalizedValue("month.september",language));
        monthVocab.put(String.valueOf(Calendar.OCTOBER), getLocalizedValue("month.october",language));
        monthVocab.put(String.valueOf(Calendar.NOVEMBER), getLocalizedValue("month.november",language));
        monthVocab.put(String.valueOf(Calendar.DECEMBER), getLocalizedValue("month.december",language));
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
