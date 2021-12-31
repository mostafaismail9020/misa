package com.sap.ibso.eservices.storefront.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedBackPayloadData {
	
	@JsonProperty("numberOfStars")
	private Integer numberOfStars;
	@JsonProperty("feedbackText")
	private String feedbackText;
	@JsonProperty("serviceId")
	private String serviceId;
	
	public Integer getNumberOfStars() {
		return numberOfStars;
	}
	public void setNumberOfStars(Integer numberOfStars) {
		this.numberOfStars = numberOfStars;
	}
	public String getMessage() {
		return feedbackText;
	}
	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
}
