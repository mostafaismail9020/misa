package com.sap.ibso.eservices.sagiaservices.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

public final class JsonUtils {

    private static final Logger LOG = Logger.getLogger( JsonUtils.class );
    private JsonUtils() {
    }

    public static boolean isJSONValid(String jsonInString) {

        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readTree(jsonInString);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            return false;
        }
        return true;

    }
}
