package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 */
public class SagiaObjectMapper extends ObjectMapper {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractSagiaService.class);

    /**
     * @param fromValue
     * @param toValue
     */
    public void convertValue(Object fromValue, Object toValue) {
        if (fromValue == null || toValue == null) {
            return;
        }
        JavaType toValueType = _typeFactory.constructType(toValue.getClass());
        try (TokenBuffer tokenBuffer = isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) ?
                new TokenBuffer(this, false).forceUseOfBigDecimal(true)
                : new TokenBuffer(this, false)) {


            SerializationConfig config = getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE);
            _serializerProvider(config).serializeValue(tokenBuffer, fromValue);
            JsonParser jsonParser = tokenBuffer.asParser();
            DeserializationConfig deserConfig = getDeserializationConfig();
            JsonToken jsonToken = _initForReading(jsonParser);
            if (jsonToken != JsonToken.VALUE_NULL && jsonToken != JsonToken.END_ARRAY && jsonToken != JsonToken.END_OBJECT) {
                deserialize(toValue, toValueType, jsonParser, deserConfig);
            }
            jsonParser.close();

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * @param toValue
     * @param toValueType
     * @param jsonParser
     * @param deserConfig
     * @throws IOException
     */
    private void deserialize(Object toValue, JavaType toValueType, JsonParser jsonParser, DeserializationConfig deserConfig) throws IOException {
        DeserializationContext deserializationContext = createDeserializationContext(jsonParser, deserConfig);
        JsonDeserializer<Object> jsonDeserializer = _findRootDeserializer(deserializationContext, toValueType);
        try {
            jsonDeserializer.deserialize(jsonParser, deserializationContext, toValue);
        } catch(JsonMappingException e) {
            LOG.warn(e.getMessage(), e);
        }
    }
}
