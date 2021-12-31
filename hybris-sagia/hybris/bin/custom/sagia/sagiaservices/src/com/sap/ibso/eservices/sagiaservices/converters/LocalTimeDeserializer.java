package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 */
class LocalTimeDeserializer extends StdDeserializer<LocalTime> {
    public LocalTimeDeserializer() {
        this(null);
    }

    protected LocalTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(jsonParser.getCodec().readValue(jsonParser, Long.class));
        return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
    }
}
