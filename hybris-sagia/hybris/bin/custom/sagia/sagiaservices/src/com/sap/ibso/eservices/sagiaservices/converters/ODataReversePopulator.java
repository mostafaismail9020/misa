package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ODataReversePopulator <DTO> implements Populator<DTO, ODataModel>
{

    @Override
    public void populate(DTO dto, ODataModel model) throws ConversionException {
        populate(dto,model, SagiaPropertyNamingStrategy.UPPER_CAMEL_CASE);
    }

    /**
     * @param dto
     * @param model
     * @param propertyNamingStrategy
     * @throws ConversionException
     */
    public void populate(DTO dto, ODataModel model, PropertyNamingStrategy propertyNamingStrategy) throws ConversionException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new SimpleModule().addSerializer(LocalDateTime.class, new LocalDateTimeSerializer()));
        mapper.registerModule(new SimpleModule().addSerializer(LocalDate.class, new LocalDateSerializer()));
        mapper.registerModule(new SimpleModule().addSerializer(LocalTime.class, new LocalTimeSerializer()));
        mapper.setPropertyNamingStrategy(propertyNamingStrategy);
        populate(dto, model, mapper);
    }

    /**
     * @param dto
     * @param model
     * @throws ConversionException
     */
    public void populateWithDefaultFieldName(DTO dto, ODataModel model) throws ConversionException {
        ObjectMapper mapper = getMapper();
        populate(dto, model, mapper);
    }

    /**
     * @param dto
     * @param model
     * @throws ConversionException
     */
    public void populateWithDefaultFieldNameAndNonNull(DTO dto, ODataModel model) throws ConversionException {
        ObjectMapper mapper = getMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        populate(dto, model, mapper);
    }

    /**
     * @return
     */
    private ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new SimpleModule().addSerializer(LocalDateTime.class, new LocalDateTimeSerializer()));
        mapper.registerModule(new SimpleModule().addSerializer(LocalDate.class, new LocalDateSerializer()));
        mapper.registerModule(new SimpleModule().addSerializer(LocalTime.class, new LocalTimeSerializer()));
        // If more complicated use mixins
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        return mapper;
    }

    private void populate(DTO dto, ODataModel model, ObjectMapper mapper) throws ConversionException {
        Map<String, Object> map = mapper.convertValue(dto, Map.class);
        model.set(map);
    }
}
