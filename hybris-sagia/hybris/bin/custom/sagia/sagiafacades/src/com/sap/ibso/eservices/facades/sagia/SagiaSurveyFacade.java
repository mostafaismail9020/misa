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
package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.SurveyData;
import com.sap.ibso.eservices.facades.data.SurveyDetailsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurveyHDRData;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyResultData;

/**
 * Provides access to SagiaSurveyFacade
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaSurveyFacade
{
	/**
	 * Retrieves SurveyListData
	 * @return SurveyDetailsData
	 */
	SurveyDetailsData getSurveyListData();

	/**
	 * sends SurveyResults
	 * @param data data
	 */
	void sendSurveyResults(final SurveyResultData data);

	/**
	 * validates SurveyResults
	 * @param data data
	 * @return Integer
	 */
	void validateSurveyResults(final SurveyResultData data);

	/**
	 * get one survey that was retrieved from Notification Service
	 * @param transactionId transactionId
	 * @return SurveyData
	 */
	SurveyData getNotificationSurveyBy(String transactionId);

	/**
	 * get the Survey from ZUI5_SAGIA_SRV 
	 * @param surveyId surveyId
	 * @param surveyVersion surveyVersion
	 * @return SurveyData
	 */
	SurveyData getZui5SurveyBy(String surveyId, String surveyVersion);

	/**
	 * get the Survey from ZUI5_SAGIA_SRV
	 * @return Boolean
	 */
	SurveyHDRData getMandatorySurvey();
}
