package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 */
class LocalDateSerializer extends StdSerializer<LocalDate> {

    public static final String YYYY_M_MDD = "yyyyMMdd";

    public LocalDateSerializer() {
        this(null);
    }

    protected LocalDateSerializer(Class<LocalDate> vc) {
        super(vc);
    }

    @Override
    public void serialize(LocalDate localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_M_MDD);
        jsonGenerator.writeString(localDateTime.format(formatter));
    }
}
