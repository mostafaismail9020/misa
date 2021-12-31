package com.sap.ibso.eservices.sagiaservices.utils;

import com.google.gson.internal.LinkedTreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ObjectUtils {


	public static final String MMM_DD_YYYY = "MMM dd, yyyy";
	public static final String YYYY_M_MDD = "yyyyMMdd";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String EMPTY_DATE = "00000000";

	private static final Logger LOG = LoggerFactory.getLogger(ObjectUtils.class);

	private ObjectUtils() {}

	public static LocalDateTime convertToDate(String parsableDate) {
		return LocalDateTime.from(DateTimeFormatter.ofPattern(DD_MM_YYYY).parse(parsableDate));
	}

	public static String getDateFormatted(LocalDateTime date) {
		if (date != null) {
			return date.format((DateTimeFormatter.ofPattern(MMM_DD_YYYY)));

		} else {
			return "";
		}
	}

	public static String modifyDateFormat(String unformattedDate) {
		if (unformattedDate != null && !unformattedDate.isEmpty() && !EMPTY_DATE.equals(unformattedDate)) {
			LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern(YYYY_M_MDD).parse(unformattedDate));
			return localDate.format(DateTimeFormatter.ofPattern(MMM_DD_YYYY));
		} else {
			return "";
		}
	}


	public static String getCRMDateFormat(String formattedDate){
		if (formattedDate != null && !formattedDate.isEmpty() && !EMPTY_DATE.equals(formattedDate)) {
			LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern(MMM_DD_YYYY).parse(formattedDate));
			return localDate.format(DateTimeFormatter.ofPattern(YYYY_M_MDD));
		} else {
			return "";
		}
	}

	public static String getCRMDateFormat(LocalDate date){
		if(Objects.isNull(date)) {
			return null;
		}
		return date.format(DateTimeFormatter.ofPattern(YYYY_M_MDD));
	}

	public static LocalDateTime extractDateFrom(Map<String, Object> map, String key) {

		Object dateObject = map.get(key);
		if(dateObject == null) {
			return null;
		}
		if (dateObject instanceof GregorianCalendar) {
			Calendar calendar = ((GregorianCalendar) dateObject);
			TimeZone tz = calendar.getTimeZone();
			ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
			return LocalDateTime.ofInstant(calendar.toInstant(), zid);
		}
		// assuming otherwise it is of type string
		return ObjectUtils.convertToDate(CollectionUtils.getValueFrom(map, key));
	}
	
	public static String toString(Object object) {
		if(object == null) {
			return null;
		}
		return String.valueOf(object);
	}
	
	public static String getValueOrNullFrom(LinkedTreeMap map, String key) {
		Object value = map.get(key);
		if (value == null) {
			return null;
		}
		try {
			return String.valueOf(value);
		} catch (Exception ex) {
			LOG.warn(ex.getMessage(),ex);
			return null;
		}
	}

	public static String changeDateFormat(String unformattedDate, String fromFormat, String toFormat) {
		if (unformattedDate != null && !unformattedDate.isEmpty() && !EMPTY_DATE.equals(unformattedDate)) {
			LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern(fromFormat).parse(unformattedDate));
			return localDate.format(DateTimeFormatter.ofPattern(toFormat));
		} else {
			return "";

		}
	}
	
	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}
		return s1.equalsIgnoreCase(s2);
	}
}
