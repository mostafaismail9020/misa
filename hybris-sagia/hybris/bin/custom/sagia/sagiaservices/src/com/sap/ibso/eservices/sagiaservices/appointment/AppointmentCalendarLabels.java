package com.sap.ibso.eservices.sagiaservices.appointment;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppointmentCalendarLabels {

	@JsonProperty("views")
	private Map<String, String> views;
	@JsonProperty("daysVocabShort")
	private Map<String, String> daysVocabShort;
	@JsonProperty("daysVocab")
	private Map<String, String> daysVocab;
	@JsonProperty("viewsVocab")
	private Map<String, String> viewsVocab;
	@JsonProperty("buttons")
	private Map<String, String> buttons;
	@JsonProperty("buttonsActions")
	private Map<String, String> buttonsActions;

	public Map<String, String> getViews() {
		return views;
	}

	public Map<String, String> getDaysVocabShort() {
		return daysVocabShort;
	}

	public Map<String, String> getViewsVocab() {
		return viewsVocab;
	}

	public Map<String, String> getButtons() {
		return buttons;
	}

	public Map<String, String> getButtonsActions() {
		return buttonsActions;
	}

	public AppointmentCalendarLabels setViews(Map<String, String> views) {
		this.views = views;
		return this;
	}

	public AppointmentCalendarLabels setDaysVocabShort(Map<String, String> daysVocabShort) {
		this.daysVocabShort = daysVocabShort;
		return this;
	}

	public Map<String, String> getDaysVocab() {
		return daysVocab;
	}

	public AppointmentCalendarLabels setDaysVocab(Map<String, String> daysVocab) {
		this.daysVocab = daysVocab;
		return this;
	}

	public AppointmentCalendarLabels setViewsVocab(Map<String, String> viewsVocab) {
		this.viewsVocab = viewsVocab;
		return this;
	}

	public AppointmentCalendarLabels setButtons(Map<String, String> buttons) {
		this.buttons = buttons;
		return this;
	}

	public AppointmentCalendarLabels setButtonsActions(Map<String, String> buttonsActions) {
		this.buttonsActions = buttonsActions;
		return this;
	}
}
