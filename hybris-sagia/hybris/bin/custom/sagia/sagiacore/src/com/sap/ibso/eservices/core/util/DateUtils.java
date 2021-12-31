package com.sap.ibso.eservices.core.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    /**
     * Convert Local date time to classic java.util.Date
     * @param dateToConvert
     * @return
     */
    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
