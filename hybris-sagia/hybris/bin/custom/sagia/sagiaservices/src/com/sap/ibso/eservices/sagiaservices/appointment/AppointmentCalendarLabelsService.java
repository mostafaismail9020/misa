package com.sap.ibso.eservices.sagiaservices.appointment;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import de.hybris.platform.servicelayer.i18n.I18NService;

/**
 * AppointmentCalendarLabelsService
 */
public class AppointmentCalendarLabelsService {

    private MessageSource messageSource;
    private I18NService i18NService;
    private Locale currentLocale;
    private static final Logger LOG = LoggerFactory.getLogger(AppointmentCalendarLabelsService.class);

	public AppointmentCalendarLabels getCalendarLabels() {
		return buildCalendarLabels();
	}
	
	private AppointmentCalendarLabels buildCalendarLabels() {
		this.currentLocale = i18NService.getCurrentLocale();
		Map<String, String> views = createViews();
		Map<String, String> daysVocabShort = createDaysVocabShort();
		Map<String, String> daysVocab = createDaysVocab();
		Map<String, String> viewsVocab = createViewsVocab();
		Map<String, String> buttons = createButtons();
		Map<String, String> buttonsActions = createButtonsActions();
		
		return new AppointmentCalendarLabels()
				.setViews(views)
				.setDaysVocabShort(daysVocabShort)
				.setDaysVocab(daysVocab)
				.setViewsVocab(viewsVocab)
				.setButtons(buttons)
				.setButtonsActions(buttonsActions);
	}
	
	private Map<String, String> createViews() {
		Map<String, String> viewsLabels = new HashMap<>();
		viewsLabels.put("week", getLocalizedValue("appointments.calendar.label.week"));
		viewsLabels.put("month", getLocalizedValue("appointments.calendar.label.month"));
		return viewsLabels;
	}
	
	private Map<String, String> createDaysVocabShort() {
		Map<String, String> daysVocab = new HashMap<>();
		daysVocab.put("Sun", getLocalizedValue("appointments.calendar.label.days.short.sunday"));
		daysVocab.put("Mon", getLocalizedValue("appointments.calendar.label.days.short.monday"));
		daysVocab.put("Tue", getLocalizedValue("appointments.calendar.label.days.short.tuesday"));
		daysVocab.put("Wed", getLocalizedValue("appointments.calendar.label.days.short.wednesday"));
		daysVocab.put("Thu", getLocalizedValue("appointments.calendar.label.days.short.thursday"));
		daysVocab.put("Fri", getLocalizedValue("appointments.calendar.label.days.short.friday"));
		daysVocab.put("Sat", getLocalizedValue("appointments.calendar.label.days.short.saturday"));
		return daysVocab;
	}
	
	
	private Map<String, String> createDaysVocab() {
		Map<String, String> daysVocab = new HashMap<>();
		daysVocab.put("Sunday", getLocalizedValue("appointments.calendar.label.days.sunday"));
		daysVocab.put("Monday", getLocalizedValue("appointments.calendar.label.days.monday"));
		daysVocab.put("Tuesday", getLocalizedValue("appointments.calendar.label.days.tuesday"));
		daysVocab.put("Wednesday", getLocalizedValue("appointments.calendar.label.days.wednesday"));
		daysVocab.put("Thursday", getLocalizedValue("appointments.calendar.label.days.thursday"));
		daysVocab.put("Friday", getLocalizedValue("appointments.calendar.label.days.friday"));
		daysVocab.put("Saturday", getLocalizedValue("appointments.calendar.label.days.saturday"));
		return daysVocab;
	}
	
	private Map<String, String> createViewsVocab() {
		Map<String, String> viewsVocabMap = new HashMap<>();
		String day = getLocalizedValue("appointments.calendar.label.day");
		String week = getLocalizedValue("appointments.calendar.label.week");
		String month = getLocalizedValue("appointments.calendar.label.month");

		viewsVocabMap.put("Day", StringUtils.capitalize(day));
		viewsVocabMap.put("Week", StringUtils.capitalize(week));
		viewsVocabMap.put("Month", StringUtils.capitalize(month));
		return viewsVocabMap;
	}
	
	private Map<String, String> createButtons() {
		Map<String, String> buttonsMap = new HashMap<>();
		buttonsMap.put("previous", getLocalizedValue("appointments.calendar.label.previous"));
		buttonsMap.put("title", getLocalizedValue("appointments.calendar.label.title"));
		buttonsMap.put("today", getLocalizedValue("appointments.calendar.label.today"));
		buttonsMap.put("next", getLocalizedValue("appointments.calendar.label.next"));
		return buttonsMap;
	}
	
	private Map<String, String> createButtonsActions() {
		Map<String, String> buttonsActionsMap = new HashMap<>();
		buttonsActionsMap.put("goBack", getLocalizedValue("appointments.calendar.label.previous"));
		buttonsActionsMap.put("title", getLocalizedValue("appointments.calendar.label.title"));
		buttonsActionsMap.put("goNext", getLocalizedValue("appointments.calendar.label.gonext"));
		buttonsActionsMap.put("goNow", getLocalizedValue("appointments.calendar.label.gonow"));
		return buttonsActionsMap;
	}
	
	private String getLocalizedValue(String messageKey) {
		try {
			return messageSource.getMessage(messageKey, null, this.currentLocale);
		} catch (Exception ex) {
			LOG.debug("Could not read translation of key: " + messageKey, ex);
			return messageKey;
		}
	}
    
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }


}
