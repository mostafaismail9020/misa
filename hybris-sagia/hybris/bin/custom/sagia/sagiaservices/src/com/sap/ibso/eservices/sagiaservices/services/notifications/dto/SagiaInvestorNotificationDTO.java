package com.sap.ibso.eservices.sagiaservices.services.notifications.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SagiaInvestorNotificationDTO {

	@JsonProperty("readDate")
	private String readDate;
	@JsonProperty("investorId")
	private String investorId;
	@JsonProperty("transactionId")
	private String transactionId;
	@JsonProperty("notificationType")
	private String notificationType;
	@JsonProperty("notificationPriority")
	private String notificationPriority;
	@JsonProperty("expiryDate")
	private SagiaDateData expiryDate;
	@JsonProperty("notificationText")
	private String notificationText;
	@JsonProperty("createdBy")
	private String createdBy;
	@JsonProperty("createdOn")
	private SagiaDateData createdOn;
	@JsonProperty("amountPayable")
	private BigDecimal amountPayable;
	@JsonProperty("mainRequest")
	private String mainRequest;

	private Map<String,List<PaymentItem>> paymentItems;

	public SagiaInvestorNotificationDTO setInvestorId(final String investorId) {
		this.investorId = investorId;
		return this;
	}

	public String getInvestorId() {
		return investorId;
	}

	public SagiaInvestorNotificationDTO setTransactionId(final String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public SagiaInvestorNotificationDTO setNotificationType(final String notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public SagiaInvestorNotificationDTO setNotificationPriority(final String notificationPriority) {
		this.notificationPriority = notificationPriority;
		return this;
	}

	public String getNotificationPriority() {
		return notificationPriority;
	}

	public SagiaInvestorNotificationDTO setExpiryDate(final SagiaDateData expiryDate) {
		this.expiryDate = expiryDate;
		return this;
	}

	public SagiaDateData getExpiryDate() {
		return expiryDate;
	}

	public SagiaInvestorNotificationDTO setNotificationText(final String notificationText) {
		this.notificationText = notificationText;
		return this;
	}

	public String getNotificationText() {
		return notificationText;
	}

	public SagiaInvestorNotificationDTO setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public SagiaInvestorNotificationDTO setCreatedOn(final SagiaDateData createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public SagiaDateData getCreatedOn() {
		return createdOn;
	}
	
	public SagiaInvestorNotificationDTO setReadDate(final String readDate) {
		this.readDate = readDate;
		return this;
	}

	public String getReadDate() {
		return readDate;
	}

	public Map<String, List<PaymentItem>> getPaymentItems() {
		return paymentItems;
	}

	public void setPaymentItems(Map<String, List<PaymentItem>> paymentItems) {
		this.paymentItems = paymentItems;
	}

	public BigDecimal getAmountPayable() {
		return amountPayable;
	}

	public SagiaInvestorNotificationDTO setAmountPayable(BigDecimal amountPayable) {
		this.amountPayable = amountPayable;
		return this;
	}

	public String getMainRequest() {
		return mainRequest;
	}

	public void setMainRequest(String mainRequest) {
		this.mainRequest = mainRequest;
	}
}
