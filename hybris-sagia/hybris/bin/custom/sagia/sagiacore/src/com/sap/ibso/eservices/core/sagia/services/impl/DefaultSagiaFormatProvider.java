/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.core.sagia.services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.DecimalStyle;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.sap.ibso.eservices.core.model.SagiaDateFormatModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaDateFormatDAO;
import com.sap.ibso.eservices.core.sagia.exception.SagiaFormatException;
import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.core.sagia.format.SagiaDateTimeData;
import com.sap.ibso.eservices.core.sagia.format.SagiaDateTimeFormats;
import com.sap.ibso.eservices.core.sagia.format.SagiaTimeData;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;

import de.hybris.platform.servicelayer.i18n.I18NService;

/**
 * Default implementation of FormatProvider
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.date.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaFormatProvider implements SagiaFormatProvider
{
	private I18NService        i18NService;
	private transient SagiaDateFormatDAO sagiaDateFormatDAO;
	private static final Logger LOG = Logger.getLogger(DefaultSagiaFormatProvider.class);

	private final ConcurrentMap<String, SagiaDateTimeFormats> languagePatterns = new ConcurrentHashMap<>();
	private static final String ENGLISH_LANGUAGE_KEY = "en";
	private static final String ARABIC_LANGUAGE_KEY = "ar";
	public static final String DATE_FORMAT_BACKEND = "yyyyMMdd";
	

	private static final LinkedHashMap<String, String> matchersDatePatterns;
	static
	{
		matchersDatePatterns = new LinkedHashMap<>();
		matchersDatePatterns.put("d", "dd");
		matchersDatePatterns.put("dd", "dd"); //For Umm-al-Qura UI format
		matchersDatePatterns.put("D", "EEE");
		matchersDatePatterns.put("l", "EEEE");
		matchersDatePatterns.put("j", "d");
		matchersDatePatterns.put("J", "d'st'");
		matchersDatePatterns.put("w", "u"); //Need to check
		matchersDatePatterns.put("F", "d");
		matchersDatePatterns.put("M", "MMM");
		matchersDatePatterns.put("m", "MM");
		matchersDatePatterns.put("mm", "MM"); //For Umm-al-Qura UI format 
		matchersDatePatterns.put("n", "M");
		matchersDatePatterns.put("y", "yy");
		matchersDatePatterns.put("Y", "yyyy");
	}

	private static final LinkedHashMap<String, String> matchersTimePatterns;
	static
	{
		matchersTimePatterns = new LinkedHashMap<>();
		matchersTimePatterns.put("H", "HH");
		matchersTimePatterns.put("h", "h");
		matchersTimePatterns.put("i", "mm");
		matchersTimePatterns.put("S", "ss");
		matchersTimePatterns.put("s", "s");
		matchersTimePatterns.put("K", "a");

	}

	@Override
	public SagiaDateData getLocalizedDateData(final Long millis)
	{
		final Instant instant = Instant.ofEpochMilli(millis);
		final LocalDate date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		return getLocalizedDateData(date);
	}

	@Override
	public SagiaDateData getLocalizedDateData(final LocalDateTime date)
	{
		if(Objects.isNull(date)) {
			return new SagiaDateData();
		}
		return getLocalizedDateData(date.toLocalDate());
	}

	@Override
	public SagiaDateTimeData getLocalizedDateTimeData(final Long millis)
	{
		final Instant instant = Instant.ofEpochMilli(millis);
		final LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		return getLocalizedDateTimeData(dateTime);
	}

	@Override
	public SagiaDateData getLocalizedDateData(final LocalDate date)
	{
		if (date == null) {
			LOG.error("LocalDate for formatting is null");
			return new SagiaDateData();
		}

		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.getDatePattern(), Locale.forLanguageTag(locale.getLanguage()));

		final SagiaDateData result = new SagiaDateData();
		result.setPattern(format.getDatePattern());
		result.setDate(date);
		result.setDateFormatted(date.format(formatter));
		result.setDateUIPattern(format.getUiDatePattern());
		result.setMillis(date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
		return result;
	}

	@Override
	public SagiaDateData getLocalizedDateData(final Date date)
	{
		if (date == null) {
			LOG.error("LocalDate for formatting is null");
			return new SagiaDateData();
		}
		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format.getDatePattern());

		final SagiaDateData result = new SagiaDateData();
		result.setPattern(format.getDatePattern());
		result.setDateFormatted(simpleDateFormat.format(date));
		result.setDateUIPattern(format.getUiDatePattern());
		result.setMillis(date.getTime());
		return result;
	}

	@Override
	public SagiaDateTimeData getLocalizedDateTimeData(final LocalDateTime dateTime)
	{
		if (dateTime == null) {
			LOG.error("LocalDateTime for formatting is null");
			return new SagiaDateTimeData();
		}

		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		final DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern(format.getDateTimePattern(), Locale.forLanguageTag(locale.getLanguage()));
		final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(format.getDatePattern(), Locale.forLanguageTag(locale.getLanguage()));
		final ZoneId zoneId = ZoneId.of("UTC" );

		final SagiaDateTimeData result = new SagiaDateTimeData();
		result.setPattern(format.getDateTimePattern());
		result.setDateTime(dateTime);
		result.setDateTimeFormatted(dateTime.format(formatterDateTime));
		result.setDateFormatted(dateTime.format(formatterDate));
		result.setMillis(dateTime.atZone(zoneId).toInstant().toEpochMilli());

		return result;
	}

	@Override
	public SagiaTimeData getLocalizedTimeData(final LocalTime time)
	{
		if (time == null) {
			LOG.error("LocalDateTime for formatting is null");
			return new SagiaTimeData();
		}

		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format.getTimePattern(), Locale.forLanguageTag(locale.getLanguage()));

		final SagiaTimeData result = new SagiaTimeData();
		result.setPattern(format.getTimePattern());
		result.setTime(time);
		result.setTimeFormatted(time.format(timeFormatter));

		return result;
	}

	@Override
	public SagiaDateData getLocalizedDateWithDaysShift(final String date, final Integer days)
	{
		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.getDatePattern(), Locale.forLanguageTag(locale.getLanguage()));

		final LocalDate localDate = LocalDate.parse(date, formatter);
		final LocalDate resultDate = localDate.plusDays(days);

		final SagiaDateData result = new SagiaDateData();
		result.setPattern(format.getDatePattern());
		result.setDate(resultDate);
		result.setDateFormatted(resultDate.format(formatter));
		result.setMillis(resultDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
		return result;
	}

	@Override
	public String getDateFormat()
	{
		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		return format.getDatePattern();
	}

	@Override
	public void updateFormatsInfo()
	{
		languagePatterns.clear();
		final List<SagiaDateFormatModel> formats = sagiaDateFormatDAO.getDateFormats();

		formats.forEach(format -> {
			final SagiaDateTimeFormats element = new SagiaDateTimeFormats();
			element.setDatePattern(format.getDatePattern());
			element.setDateTimePattern(format.getDateTimePattern());
			element.setLangCode(format.getLangCode());
			element.setTimePattern(format.getTimePattern());
			element.setUiDatePattern(format.getUiDatePattern());
			element.setUiTimePattern(format.getUiTimePattern());
			element.setUiDatePatternUmm(format.getUiDatePatternUmm());
			element.setUiTimePatternUmm(format.getUiDatePatternUmm());
			
			String dateParsePattern = format.getUiDatePattern();
			for (Map.Entry<String, String> signPair : matchersDatePatterns.entrySet()) {
				dateParsePattern = dateParsePattern.replaceAll(signPair.getKey(), signPair.getValue());
			}
			element.setParseUIDateStringPattern(dateParsePattern);

			String timeParsePattern = format.getUiTimePattern();
			for (Map.Entry<String, String> signPair : matchersTimePatterns.entrySet()) {
				timeParsePattern = timeParsePattern.replaceAll(signPair.getKey(), signPair.getValue());
			}
			element.setParseUITimeStringPattern(dateParsePattern);

			languagePatterns.put(format.getLangCode().toUpperCase(), element);
		});
	}

	/*
	 * Gregorian
	 * Date calendar remain same, its just date format
	 */
	@Override
	public String formatBackEndStrToUIStr(String dateString) {
    	
    	DateFormat nicFormat = new SimpleDateFormat(DATE_FORMAT_BACKEND);
		try {
			Date date = nicFormat.parse(dateString);
			final Locale locale = i18NService.getCurrentLocale();
			final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
			return new SimpleDateFormat(format.getParseUIDateStringPattern()).format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		return "";
    }
   
	/*
	 * Gregorian
	 * Date calendar remain same, its just date format
	 */
	@Override
	public String formatBackEndDateToUIStr(Date date) {
		String dateStr = formatBackEndDateToBackEndStr(date);
		return formatBackEndStrToUIStr(dateStr);
	}	

	/*
	 * Gregorian
	 * Date calendar remain same, its just date format
	 */
	@Override
	public String formatBackEndDateToBackEndStr(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(DATE_FORMAT_BACKEND).format(date);
	}
	
	/*
	 * Gregorian
	 * Date calendar remain same, its just date format
	 */
	@Override
	public String formatUIStrToBackStr(String dateString) {
		LocalDateTime dateTime = getDateTimeFromUIDateString(dateString);
		 return DateTimeFormatter.ofPattern(DATE_FORMAT_BACKEND).format(dateTime);
    }
	
	
	/*
	 * Gregorian
	 * Date calendar remain same, its just date format
	 */
	@Override
	public String formatUIDateStrForBackEndDate(LocalDateTime dateTime) {
		 return DateTimeFormatter.ofPattern(DATE_FORMAT_BACKEND).format(dateTime);
    }
	
	
	@Override
	public Date formatUIStrToBackDate(String dateString) {
		String formatUIStrToBackStr = formatUIStrToBackStr(dateString);
		try {
			return new SimpleDateFormat(DATE_FORMAT_BACKEND).parse(formatUIStrToBackStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	
	
	/*
	 * UAQ Str Date --> Gregorian Str Date
	 * 
	 */
	@Override
	public String convertuiUAQStrToBackEndGreStr(String dateString) {
		LocalDateTime dateTime = getLocalDateTimeFromuiUAQDateString(dateString, HijrahChronology.INSTANCE);
		 return dateTime != null ? DateTimeFormatter.ofPattern(DATE_FORMAT_BACKEND).format(dateTime) : ""; 
    }


public String convertNFormatuiUAQDateToBackEndGreDate(LocalDateTime dateTime) {
	
	 return dateTime != null ? DateTimeFormatter.ofPattern(DATE_FORMAT_BACKEND).format(dateTime) : "";
}


	/*
	 * UAQ Str Date --> Gregorian Date
	 * 
	 */
	@Override
	public Date convertuiUAQStrToBackEndGreDate(String dateString) {
		if(StringUtils.isNotEmpty(dateString))
		{
			String formattedDate = convertuiUAQStrToBackEndGreStr(dateString);
			try {
				return StringUtils.isNotBlank(formattedDate)?new SimpleDateFormat(DATE_FORMAT_BACKEND).parse(formattedDate) : null;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
    }
	
	/*
	 * Gregorian Str Date --> UAQ Str Date
	 * 
	 */
	@Override
	public String convertGregorianStrToUAQStr(String dateString) {
		try {
			Locale locale = i18NService.getCurrentLocale();
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_BACKEND, locale);
			LocalDate localDate = LocalDate.parse(dateString, formatter);

			if (localDate != null) {
				Chronology chrono = HijrahChronology.INSTANCE;
				ChronoLocalDate cDate = chrono.date(localDate);
				final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format.getParseUIDateStringPattern(),
						locale);
				return dateFormatter.format(cDate);
			}

		} catch (DateTimeException ex) {
		}
		return "";
    }
    
	
	@Override
	public LocalDate convertGregorianDateToLocaldate(String dateString) {
		try {
			Locale locale = i18NService.getCurrentLocale();
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_BACKEND, locale);
			LocalDate localDate = LocalDate.parse(dateString, formatter);

			if (localDate != null) {
				
				return localDate;
			}

		} catch (DateTimeException ex) {
		}
		return null;
    }
	/*
	 * Gregorian Date --> UAQ Str Date
	 * 
	 */
	@Override
	public String convertGregorianDateToUAQStr(Date date) {
		String format = new SimpleDateFormat(DATE_FORMAT_BACKEND).format(date);
		return convertGregorianStrToUAQStr(format);
	}
	
	@Override
	public String formatUiUAQStrToBackEndUAQStr(String uiDateString) {
		final Locale currentLocale =  i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(currentLocale.getLanguage());
		ChronoLocalDate cDate = parseChronoDateFromString(uiDateString, format.getParseUIDateStringPattern(), HijrahChronology.INSTANCE);
		 return cDate != null ? cDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT_BACKEND, currentLocale)) : "";
    }
	
	@Override
	public String formatBackEndUAQStrTouUiUAQStr(String dateString) {
		final Locale currentLocale = i18NService.getCurrentLocale();
		ChronoLocalDate cDate = parseChronoDateFromString(dateString, DATE_FORMAT_BACKEND, HijrahChronology.INSTANCE);
		final SagiaDateTimeFormats format = getFormatElement(currentLocale.getLanguage());
		 return cDate != null ? cDate.format(DateTimeFormatter.ofPattern(format.getParseUIDateStringPattern(), currentLocale)) : "";
	}
	
	@Override
	public String getUIDateFormat()
	{
		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		return format.getUiDatePattern();
	}

	@Override
	public String getUITimeFormat()
	{
		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		return format.getUiTimePattern();
	}
	
	@Override
	public String getUIDateFormatUAQ()
	{
		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		return format.getUiDatePatternUmm();
	}

	@Override
	public String getUITimeFormatUAQ()
	{
		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		return format.getUiTimePatternUmm();
	}

	@Override
	public LocalDate getDateFromUIDateStringOrNull(String uiDateString) {
		if(StringUtils.isEmpty(uiDateString)) {
			return null;
		}
		final Locale currentLocale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(currentLocale.getLanguage());
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.getParseUIDateStringPattern(), Locale.forLanguageTag(currentLocale.getLanguage()));

		LocalDate date;
		try {
            date = LocalDate.parse(uiDateString, formatter);
		} catch (DateTimeParseException e) {
            date = getFallbackDate(uiDateString, currentLocale);
        }

        return date;
    }

	@Override
	public LocalDateTime getDateTimeFromUIDateString(final String uiDateString)
	{
		final Locale currentLocale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(currentLocale.getLanguage());
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.getParseUIDateStringPattern(), Locale.forLanguageTag(currentLocale.getLanguage()));

		LocalDate date;
        try {
            date = LocalDate.parse(uiDateString, formatter);
        } catch (DateTimeParseException e) {
            date = getFallbackDate(uiDateString, currentLocale);
        }
		return date.atStartOfDay();
	}

	@Override
	public LocalDateTime getLocalDateTimeFromuiUAQDateString(String uiDateString, Chronology chronology)
	{
		
		LocalDate date = getLocalDateFromuiUAQDateString(uiDateString, chronology);
		if(date != null)
		{
			return date.atStartOfDay();
		}
		return null;
	}
	
	@Override
	public LocalDate getLocalDateFromuiUAQDateString(String uiDateString, Chronology chronology)
	{
		try {
			final Locale currentLocale = i18NService.getCurrentLocale();
			final SagiaDateTimeFormats format = getFormatElement(currentLocale.getLanguage());
			ChronoLocalDate cDate = parseChronoDateFromString(uiDateString, format.getParseUIDateStringPattern(), chronology);
			if(cDate != null)
			{
				return LocalDate.from(cDate);
			}	
		} catch (DateTimeParseException e) {
		}
		return null;
	}
	
	private ChronoLocalDate parseChronoDateFromString(String uiDateString, String pattern, Chronology chronology)
	{
		final Locale currentLocale = i18NService.getCurrentLocale();
		try {
			if (uiDateString != null && !uiDateString.isEmpty()) {
			
				Chronology chrono = IsoChronology.INSTANCE;
				 if (chronology != null) {
		                chrono = chronology;
		            }

				DateTimeFormatter df = new DateTimeFormatterBuilder().parseLenient()
						.appendPattern(pattern).toFormatter().withChronology(chrono)
						.withDecimalStyle(DecimalStyle.of(currentLocale)).withLocale(currentLocale);
				TemporalAccessor temporal = df.parse(uiDateString);
				return chrono.date(temporal);
			}
		} catch (DateTimeParseException e) {
			
			
		}
		return  getFallbackDateWithChrono( uiDateString,  pattern,  currentLocale,  chronology);
	}
	
	private LocalDate getFallbackDate(String uiDateString, Locale currentLocale) {
        final Locale locale = currentLocale.getLanguage().equals(ENGLISH_LANGUAGE_KEY) ? new Locale(ARABIC_LANGUAGE_KEY) : new Locale(ENGLISH_LANGUAGE_KEY);
        SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.getParseUIDateStringPattern(), Locale.forLanguageTag(locale.getLanguage()));
        return LocalDate.parse(uiDateString, formatter);
    }
	
	private ChronoLocalDate getFallbackDateWithChrono(String uiDateString, String pattern, Locale currentLocale, Chronology chronology) {
        final Locale locale = currentLocale.getLanguage().equals(ENGLISH_LANGUAGE_KEY) ? new Locale(ARABIC_LANGUAGE_KEY) : new Locale(ENGLISH_LANGUAGE_KEY);
        try {
			if (uiDateString != null && !uiDateString.isEmpty()) {
				Chronology chrono = IsoChronology.INSTANCE;
				 if (chronology != null) {
		                chrono = chronology;
		            }

				DateTimeFormatter df = new DateTimeFormatterBuilder().parseLenient()
						.appendPattern(pattern).toFormatter().withChronology(chrono)
						.withDecimalStyle(DecimalStyle.of(locale)).withLocale(locale);
				TemporalAccessor temporal = df.parse(uiDateString);
				return chrono.date(temporal);
			}
		} catch (DateTimeParseException e) {
			
			
		}
		return null;
    }

	@Override
	public LocalDateTime getDateTime(final String uiDateString, final String uiTimeString)
	{
		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format.getDatePattern(), Locale.forLanguageTag(locale.getLanguage()));
		final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format.getParseUITimeStringPattern());

		final LocalDate date =  LocalDate.parse(uiDateString, dateFormatter);
		final LocalTime time = LocalTime.parse(uiTimeString, timeFormatter);

		return LocalDateTime.of(date, time);
	}

	@Override
	public LocalTime getTimeFromUITime(final String uiTimeString)
	{
		final Locale locale = i18NService.getCurrentLocale();
		final SagiaDateTimeFormats format = getFormatElement(locale.getLanguage());
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.getParseUITimeStringPattern());

		return LocalTime.parse(uiTimeString, formatter);
	}

	private SagiaDateTimeFormats getFormatElement(String language)
	{
		if (languagePatterns.isEmpty()) {
			updateFormatsInfo();
		}
		if (languagePatterns.isEmpty()){
			throw new SagiaFormatException("Formats configuration for languages in not loaded, ask your administrator to specify formats.");
		}

		final String key = language.toUpperCase();
		if (languagePatterns.containsKey(key))
		{
			return languagePatterns.get(key);
		}

		return languagePatterns.get(ENGLISH_LANGUAGE_KEY.toUpperCase());
	}

	public I18NService getI18NService()
	{
		return i18NService;
	}

	public void setI18NService(final I18NService i18NService)
	{
		this.i18NService = i18NService;
	}

	public SagiaDateFormatDAO getSagiaDateFormatDAO()
	{
		return sagiaDateFormatDAO;
	}

	public void setSagiaDateFormatDAO(final SagiaDateFormatDAO sagiaDateFormatDAO)
	{
		this.sagiaDateFormatDAO = sagiaDateFormatDAO;
	}

	/**
	 * expect input a date in format yyyy-MM-ddTHH:mm:ss
	 * return in format dd MM yyyy
	 * e.g input: 2013-05-25T12:01:40 
	 * returns 25 03 2013
	 * in case of parse exception, the initial value is returned
	 */
	@Override
	public String getShortDateFromUTC(String date) {
		if (date == null) {
			return null;
		}
		String dateToParse = date.trim();
		if(dateToParse.isEmpty()) {
			return dateToParse;
		}
		try {
			DateFormat sourceFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date dt = sourceFormat1.parse(dateToParse);
			DateFormat sourceFormat2 = new SimpleDateFormat("dd MM yyyy");
			return sourceFormat2.format(dt);
		} catch (ParseException e) {
			LOG.debug("Date " + date + " could not be parsed. " + e.getMessage());
			return date;
		}
	}
	
	@Override
	public String formatBooleanForODATA(Boolean bol) {
		if(bol != null && bol) {
			return "X";
		}else {
			return "";
		}
		
	}
}
