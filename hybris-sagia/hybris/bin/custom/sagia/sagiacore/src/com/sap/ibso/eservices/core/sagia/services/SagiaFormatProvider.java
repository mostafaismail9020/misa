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
package com.sap.ibso.eservices.core.sagia.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.Chronology;
import java.util.Date;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.core.sagia.format.SagiaDateTimeData;
import com.sap.ibso.eservices.core.sagia.format.SagiaTimeData;

/**
 * Provides access to the Format Provider
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.date
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaFormatProvider
{
	SagiaDateData getLocalizedDateData(LocalDate date);
	SagiaDateData getLocalizedDateData(Long millis);
	SagiaDateData getLocalizedDateData(LocalDateTime date);
	SagiaDateData getLocalizedDateData(Date date);
	SagiaDateTimeData getLocalizedDateTimeData(LocalDateTime dateTime);
	SagiaDateTimeData getLocalizedDateTimeData(Long millis);
	SagiaTimeData getLocalizedTimeData(LocalTime time);
	SagiaDateData getLocalizedDateWithDaysShift(String date, Integer days);
	String getDateFormat();
	void updateFormatsInfo();
	String getUIDateFormat();
	String getUITimeFormat();
	LocalDate getDateFromUIDateStringOrNull(String uiDateString);
	LocalDateTime getDateTimeFromUIDateString(String uiDateString);
	LocalDateTime getLocalDateTimeFromuiUAQDateString(String uiDateString, Chronology chronology);
	LocalDate getLocalDateFromuiUAQDateString(String uiDateString, Chronology chronology);
	LocalDateTime getDateTime(String uiDateString, String uiTimeString);
	LocalTime getTimeFromUITime(String uiTimeString);
	String getShortDateFromUTC(String extendedDate);

	String formatBackEndStrToUIStr(String dateString);
	String formatBackEndDateToUIStr(Date date);
	String formatBackEndDateToBackEndStr(Date date);
	String formatUIStrToBackStr(String dateString);
	Date formatUIStrToBackDate(String dateString);
	
	String formatUiUAQStrToBackEndUAQStr(String uiDateString);
	String formatBackEndUAQStrTouUiUAQStr(String dateString);
	
	String convertGregorianStrToUAQStr(String dateString);
	String convertGregorianDateToUAQStr(Date date);
	String convertuiUAQStrToBackEndGreStr(String dateString);
	Date convertuiUAQStrToBackEndGreDate(String dateString);
	
	String getUIDateFormatUAQ();
	String getUITimeFormatUAQ();
	String convertNFormatuiUAQDateToBackEndGreDate(LocalDateTime dateTime);
	String formatUIDateStrForBackEndDate(LocalDateTime dateTime);
	
	LocalDate convertGregorianDateToLocaldate(String dateString);
	
	String formatBooleanForODATA(Boolean bol);
}
