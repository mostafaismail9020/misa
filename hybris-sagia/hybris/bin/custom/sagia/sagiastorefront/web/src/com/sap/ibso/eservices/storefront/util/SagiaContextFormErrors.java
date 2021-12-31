package com.sap.ibso.eservices.storefront.util;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SagiaContextFormErrors {
	
	@JsonProperty("section")
	private String section;
	@JsonProperty("formErrors")
    private Map<String, String> formErrors;
	@JsonProperty("popupError")
    private String popupError;
	@JsonProperty("tabName")
	private String tabName;
    
	public String getSection() {
		return section;
	}
	public SagiaContextFormErrors setSection(String section) {
		this.section = section;
		return this;
	}
	public Map<String, String> getFormErrors() {
		return formErrors;
	}
	public SagiaContextFormErrors setFormErrors(Map<String, String> formErrors) {
		this.formErrors = formErrors;
		return this;
	}
	public String getTabName() {
		return tabName;
	}
	public SagiaContextFormErrors setTabName(String tabName) {
		this.tabName = tabName;
		return this;
	}
	public String getPopupError() {
		return popupError;
	}
	public SagiaContextFormErrors setPopupError(String popupError) {
		this.popupError = popupError;
		return this;
	}
}
