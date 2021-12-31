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
package com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto;

import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SurveyResultData
{
	private String surveyId;
	private String applicationId;
	private String transactionId;
	private String surveyVersion;
	private List<SurveyAnswers> answers;
	private Boolean isFetchedFromNotificationService;

	/**
	 * @return
	 */
	public String getSurveyId()
	{
		return surveyId;
	}

	/**
	 * @param surveyId
	 */
	public void setSurveyId(final String surveyId)
	{
		this.surveyId = surveyId;
	}

	/**
	 * @return
	 */
	public String getApplicationId()
	{
		return applicationId;
	}

	/**
	 * @param applicationId
	 */
	public void setApplicationId(final String applicationId)
	{
		this.applicationId = applicationId;
	}

	/**
	 * @return
	 */
	public String getTransactionId()
	{
		return transactionId;
	}

	/**
	 * @param transactionId
	 */
	public void setTransactionId(final String transactionId)
	{
		this.transactionId = transactionId;
	}

	/**
	 * @return
	 */
	public String getSurveyVersion()
	{
		return surveyVersion;
	}

	/**
	 * @param surveyVersion
	 */
	public void setSurveyVersion(final String surveyVersion)
	{
		this.surveyVersion = surveyVersion;
	}

	/**
	 * @return
	 */
	public List<SurveyAnswers> getAnswers()
	{
		return answers;
	}

	/**
	 * @param answers
	 */
	public void setAnswers(final List<SurveyAnswers> answers)
	{
		this.answers = answers;
	}

	public Boolean getIsFetchedFromNotificationService() {
		return isFetchedFromNotificationService;
	}
	
	public void setIsFetchedFromNotificationService(Boolean fetchedFromNotifications) {
		this.isFetchedFromNotificationService = fetchedFromNotifications;
	}
}
