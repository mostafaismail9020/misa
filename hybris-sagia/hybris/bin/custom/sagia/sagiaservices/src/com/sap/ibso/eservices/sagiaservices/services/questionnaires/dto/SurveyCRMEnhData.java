package com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyCRMEnhData {

    @JsonProperty("Surveyid")
    private String surveyId;
    @JsonProperty("ApplicationId")
    private String applicationId;
    @JsonProperty("TransactionId")
    private String transactionId;
    @JsonProperty("SurveyVersion")
    private String surveyVersion;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Surveytitle")
    private String title;
    @JsonProperty("SurveyHdrToSurvQuestAnsNav")
    private List<SurveyAnswers> answers;
    
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getSurveyVersion() {
		return surveyVersion;
	}
	public void setSurveyVersion(String surveyVersion) {
		this.surveyVersion = surveyVersion;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<SurveyAnswers> getAnswers() {
		return answers;
	}
	public void setAnswers(List<SurveyAnswers> answers) {
		this.answers = answers;
	}
}
