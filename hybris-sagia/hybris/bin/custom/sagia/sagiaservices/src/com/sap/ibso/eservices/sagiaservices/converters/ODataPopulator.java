package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang3.Validate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ODataPopulator<DTO> implements Populator<ODataModel, DTO>
{
    /**
     * @param model
     * @param dto
     * @throws ConversionException
     */
    @Override
    public void populate(ODataModel model, DTO dto) throws ConversionException {
        populate(model, dto, SagiaPropertyNamingStrategy.UPPER_CAMEL_CASE);
    }

    /**
     * @param model
     * @param dto
     * @param propertyNamingStrategy
     * @throws ConversionException
     */
    public void populate(ODataModel model, DTO dto, PropertyNamingStrategy propertyNamingStrategy) throws ConversionException {
        Validate.notNull(model, "model cannot be null");
        Validate.notEmpty(model.get(), "model cannot be empty");
        Validate.notNull(dto, "dto cannot be null");
        SagiaObjectMapper sagiaObjectMapper = new SagiaObjectMapper();
        sagiaObjectMapper
                .registerModule(new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer()))
                .registerModule(new SimpleModule().addDeserializer(LocalTime.class, new LocalTimeDeserializer()))
                .registerModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer()))
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .setPropertyNamingStrategy(propertyNamingStrategy);
        sagiaObjectMapper.convertValue(model.get(), dto);
    }

}
