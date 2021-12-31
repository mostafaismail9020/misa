package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

/**
 *
 */
class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    public LocalDateTimeDeserializer() {
        this(null);
    }

    protected LocalDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Calendar calendar = Calendar.getInstance();
        Long milis = jsonParser.getCodec().readValue(jsonParser, Long.class);
        if(milis != null){
            calendar.setTimeInMillis(milis);
            return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        } else {
            return null;
        }
    }
}
