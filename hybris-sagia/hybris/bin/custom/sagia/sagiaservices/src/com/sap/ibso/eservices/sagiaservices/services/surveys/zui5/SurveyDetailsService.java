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
package com.sap.ibso.eservices.sagiaservices.services.surveys.zui5;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurveyHDRData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyCRMData;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyResultData;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * SurveyDetailsService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SurveyDetailsService extends AbstractSagiaService<SurveyHDRData>
{
	private I18NService i18NService;

	private static final String SURVEY_DATA_EXPAND = "SurveyHdrToSurvQuestDefNav,SurveyHdrToSurvQuestDefNav/SurveyQuestDefToSurvQuestDDLBNav";
	private static final String DEFAULT_SURVEY_ID = "''";

	/**
	 * retrieves SurveyHDRData by surveyId
	 * @return SurveyHDRData
	 */
	public final Optional<SurveyHDRData> getZui5Survey() {
		SurveyHDRData zui5Survey =  get(SurveyHDRData.class, DEFAULT_SURVEY_ID, new QueryOptionsBuilder().expand(SURVEY_DATA_EXPAND).build());
		if(isValidCRMSurveyData(zui5Survey)) {
			return Optional.of(zui5Survey);
		}
		return Optional.empty();
	}

	/**
	 * according to metadata from ZUI5_SAGIA_SRV, Surveyid field is unique key
	 * if empty or null -> not valid
	 *
	 * @param surveyHDRData
	 */
	private boolean isValidCRMSurveyData(SurveyHDRData surveyHDRData) {
		if (Objects.isNull(surveyHDRData)) {
			return false;
		}

		return !StringUtils.isBlank(surveyHDRData.getSurveyid());
	}

	/**
	 * sends SurveyResultData
	 * @param data data
	 */
	public void sendSurveyResults(SurveyResultData data)
	{
		SurveyCRMData crmData = new SurveyCRMData();
		crmData.setAnswers(data.getAnswers());
		crmData.setSurveyId(data.getSurveyId());
		crmData.setApplicationId(data.getApplicationId());
		crmData.setSurveyVersion(data.getSurveyVersion());

		create(crmData);
	}

	/**
	 * @return I18NService
	 */
	public I18NService getI18NService()
	{
		return i18NService;
	}

	/**
	 * @param i18NService i18NService
	 */
	public void setI18NService(final I18NService i18NService)
	{
		this.i18NService = i18NService;
	}
}
