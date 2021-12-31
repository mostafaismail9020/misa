package com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto;

import java.util.Arrays;
import java.util.List;

import com.sap.ibso.eservices.sagiaservices.utils.CollectionUtils;

public class SurveyTransactionIdParser {

	private String transactionIdExpression;
	private static final String TRANSACTION_ID_DELIMITER = "-";

	public SurveyTransactionIdParser(String transactionIdExpression) {
		this.transactionIdExpression = transactionIdExpression;
	}

	public List<String> getSplittedTransactionId() {
		return Arrays.asList(this.transactionIdExpression.split(TRANSACTION_ID_DELIMITER));
	}

	public String getSurveyId() {
		return CollectionUtils.getByIndexOrNull(getSplittedTransactionId(), 0);
	}

	public String getSurveyVersion() {
		return CollectionUtils.getByIndexOrNull(getSplittedTransactionId(), 1);
	}

	public String getServiceId() {
		return CollectionUtils.getByIndexOrNull(getSplittedTransactionId(), 2);
	}
}
