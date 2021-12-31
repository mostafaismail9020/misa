package com.sap.ibso.eservices.sagiaservices.services.surveys.zesrv;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

import java.util.HashMap;
import java.util.Map;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurveyHDREnhData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyCRMEnhData;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyResultData;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyTransactionIdParser;

import de.hybris.platform.servicelayer.i18n.I18NService;

/**
 * SurveyDetailsEnhService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SurveyDetailsEnhService extends AbstractSagiaService<SurveyHDREnhData>{
	private I18NService i18NService;
	private static final String SURVEY_DATA_EXPAND = "SurveyHdrToSurvQuestDefNav,SurveyHdrToSurvQuestDefNav/SurveyQuestDefToSurvQuestDDLBNav";
	private static final String SURVEY_ID = "Surveyid";
	private static final String SURVEY_VERSION = "SurveyVersion";
	private static final String SURVEY_LANG = "Language";

	/**
	 * read Survey from ZESRV_ENH_ODATA_SRV
	 * @param transactionId transactionId
	 * @return SurveyHDREnhData
	 */
	public final SurveyHDREnhData getNotificationSurvey(String transactionId) {
		SurveyTransactionIdParser transactionIdParser = new SurveyTransactionIdParser(transactionId);
		SurveyHDREnhData result = getSurveyBy(transactionIdParser.getSurveyId(),
				transactionIdParser.getSurveyVersion());
		result.setTransactionId(transactionId);
		return result;
	}
	
	/**
	 * retrieves Survey from ZUI5_SAGIA_SRV
	 * @param  surveyId surveyId
	 * @param surveyVersion surveyVersion
 	 * @return SurveyHDREnhData
	 */
	public final SurveyHDREnhData getSurveyBy(String surveyId, String surveyVersion)  {
		final String langCode = i18NService.getCurrentLocale().getLanguage().toUpperCase();
		final Map<String, String> parameters = new HashMap<>();
		parameters.put(SURVEY_ID, surveyId);
		parameters.put(SURVEY_VERSION, surveyVersion);
		parameters.put(SURVEY_LANG, langCode);
		return super.getByProperties(SurveyHDREnhData.class, parameters, REQUEST_PARAMETER_EXPAND, SURVEY_DATA_EXPAND);
	}

	/**
	 * submit SurveyResults
	 * @param data data
	 */
	public void sendSurveyResults(SurveyResultData data) {
		final String langCode = i18NService.getCurrentLocale().getLanguage().toUpperCase();

		SurveyCRMEnhData crmData = new SurveyCRMEnhData();
		crmData.setAnswers(data.getAnswers());
		crmData.setSurveyId(data.getSurveyId());
		
		SurveyTransactionIdParser transactionIdParser = new SurveyTransactionIdParser(data.getTransactionId());
		//when on POST, set serviceId for transactionId field
		crmData.setTransactionId(transactionIdParser.getServiceId());
		crmData.setApplicationId(data.getApplicationId());
		crmData.setSurveyVersion(data.getSurveyVersion());
		crmData.setLanguage(langCode);

		create(crmData);
	}
	
	public I18NService getI18NService() {
		return i18NService;
	}

	public void setI18NService(final I18NService i18NService) {
		this.i18NService = i18NService;
	}
}
