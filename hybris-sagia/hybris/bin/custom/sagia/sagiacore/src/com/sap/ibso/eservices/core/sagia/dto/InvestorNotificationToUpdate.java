package com.sap.ibso.eservices.core.sagia.dto;

public class InvestorNotificationToUpdate {

	private String readDate;
	private String transactionId;
	private String investorId;
	
	public String getReadDate() {
		return readDate;
	}

	public InvestorNotificationToUpdate setReadDate(String readDate) {
		this.readDate = readDate;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public InvestorNotificationToUpdate setTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getInvestorId() {
		return investorId;
	}

	public InvestorNotificationToUpdate setInvestorId(String investorId) {
		this.investorId = investorId;
		return this;
	}
}
