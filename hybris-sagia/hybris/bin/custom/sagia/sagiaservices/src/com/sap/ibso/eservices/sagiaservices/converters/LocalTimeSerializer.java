package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
class LocalTimeSerializer extends StdSerializer<LocalTime> {
    public LocalTimeSerializer() {
        this(null);
    }

    protected LocalTimeSerializer(Class<LocalTime> vc) {
        super(vc);
    }

    @Override
    public void serialize(LocalTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'PT'HH'H'mm'M'ss'S'");
        jsonGenerator.writeString(localDateTime.format(formatter));
    }
}
