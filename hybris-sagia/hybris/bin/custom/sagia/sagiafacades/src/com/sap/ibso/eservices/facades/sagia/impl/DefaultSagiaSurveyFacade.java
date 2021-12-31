/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.SurveyData;
import com.sap.ibso.eservices.facades.data.SurveyDetailsData;
import com.sap.ibso.eservices.facades.populators.SurveyDataPopulator;
import com.sap.ibso.eservices.facades.populators.zesrvEnhOData.SurveyEnhDataPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaSurveyFacade;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurvQuestDefEnhData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurveyHDREnhData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurveyHDRData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaInvalidQuestionnaireException;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyAnswers;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyResultData;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyTransactionIdParser;
import com.sap.ibso.eservices.sagiaservices.services.surveys.zesrv.SurveyDetailsEnhService;
import com.sap.ibso.eservices.sagiaservices.services.surveys.zui5.SurveyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * DefaultSagiaSurveyFacade
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.sagia.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */

public class DefaultSagiaSurveyFacade implements SagiaSurveyFacade {
    private SurveyDataPopulator surveyDataPopulator;
    private SurveyEnhDataPopulator surveyEnhDataPopulator;
    private SagiaFormatProvider sagiaFormatProvider;
    private SurveyDetailsService surveyDetailsService;
    private SurveyDetailsEnhService surveyDetailsEnhService;

    private static final String NUMBER_FIELD = "6";
    private static final String SURVEY_TYPE = "SY";

    @Autowired
    private ZUI5SagiaFacade zui5SagiaFacade;

    @Autowired
    private SagiaInvestorNotificationsFacade investorNotificationsFacade;

    /**
     * Only one survey is sent from ZUI5ODataService and it is mandatory
     * Because, the CRM is not capable to send not found when the survey is not present/or can not be retrieved,
     * manually check if the keys are present
     * if they are empty -> by CRM logic, it means is invalid/not present
     */
    @Override
    public SurveyData getZui5SurveyBy(String surveyId, String surveyVersion) {
        final SurveyHDREnhData surveyHDRData = surveyDetailsEnhService.getSurveyBy(surveyId, surveyVersion);
        final SurveyData surveyData = new SurveyData();
        surveyEnhDataPopulator.populate(surveyHDRData, surveyData);
        surveyData.setIsFetchedFromNotificationService(false);
        return surveyData;
    }
    
    @Override
    public SurveyData getNotificationSurveyBy(String transactionId) {
        final SurveyHDREnhData surveyHDRData = surveyDetailsEnhService.getNotificationSurvey(transactionId);
        final SurveyData surveyData = new SurveyData();
        surveyEnhDataPopulator.populate(surveyHDRData, surveyData);
        surveyData.setIsFetchedFromNotificationService(true);
        surveyData.setTransactionId(transactionId);
        return surveyData;
    }

    @Override
    public SurveyDetailsData getSurveyListData() {
        final LocalDateTime now = LocalDateTime.now();
        final SurveyDetailsData result = new SurveyDetailsData();
        result.setNewSurveys(new ArrayList<>());
        result.setSurveys(new ArrayList<>());
        result.setTabCount(0);

        //STEP 1: read surveys from ZUI5_SAGIA_SRV/SurveyHDRs('')
        //surveys that come from ZUI5_SAGIA_SRV do not have transactionId
		Optional<SurveyHDRData> odataZui5Survey = surveyDetailsService.getZui5Survey();
		if (odataZui5Survey.isPresent()) {
			SurveyHDRData mandatorySurvey = odataZui5Survey.get();
			SurveyData surveyDataFromZUI5 = createSurveyDataFrom(mandatorySurvey);
			addSurvey(result, surveyDataFromZUI5, now, mandatorySurvey.getValidfrom());
		}
        
        //STEP 2: read surveys from ZESRV_ENH_ODATA_SRV/SurveyHDRSet
        List<SurveyData> surveysFromNotificationService = readSurveysFromNotificationsService();
        //surveys that come from Notification Service do not have property ValidFrom
        result.getSurveys().addAll(surveysFromNotificationService);
        return result;
    }

    @Override
    public SurveyHDRData getMandatorySurvey() {
        Optional<SurveyHDRData> odataZui5Survey = surveyDetailsService.getZui5Survey();
        if (odataZui5Survey.isPresent()){
           return odataZui5Survey.get();

        }

        return null;
    }

	private List<SurveyData> readSurveysFromNotificationsService() {
		List<InvestorNotificationData> listNotifications = investorNotificationsFacade
				.getCRMNotificationsByType(SURVEY_TYPE);
		final List<String> surveyTransactionIds = listNotifications.stream().map(InvestorNotificationData::getTransactionId)
				.collect(Collectors.toList());
		List<SurveyData> surveysFromNotificationService = new ArrayList<>();
		// Optimization for requesting the same surveys just a one time
		final Map<String, SurveyData> requestedSurveys = new HashMap<>();
		for (String surveyTransactionId : surveyTransactionIds) {
			String surveyId = new SurveyTransactionIdParser(surveyTransactionId).getSurveyId();
			if (requestedSurveys.containsKey(surveyId)) {
				final SurveyData surveyData = requestedSurveys.get(surveyId);
				surveyData.setTransactionId(surveyTransactionId);
				surveyData.setIsFetchedFromNotificationService(true);
				surveysFromNotificationService.add(surveyData);
			} else {
				final SurveyHDREnhData surveyHDRData = surveyDetailsEnhService.getNotificationSurvey(surveyTransactionId);
				final SurveyData surveyData = new SurveyData();
				surveyEnhDataPopulator.populate(surveyHDRData, surveyData);
				surveyData.setIsFetchedFromNotificationService(true);
				surveyData.setTransactionId(surveyTransactionId);
				surveysFromNotificationService.add(surveyData);
				requestedSurveys.put(surveyId, surveyData);
			}
		}
		return surveysFromNotificationService;
	}
	
	private SurveyData createSurveyDataFrom(SurveyHDRData surveyHDRData) {
		final SurveyData fdiSurveyData = new SurveyData();
		surveyDataPopulator.populate(surveyHDRData, fdiSurveyData);
		fdiSurveyData.setIsFetchedFromNotificationService(false);
		return fdiSurveyData;
	}

	/**
	 * validFrom should not be null, because it is assigned when the survey is created
	 * but better check for null
	 * @param detailsData
	 * @param surveyData
	 * @param now
	 * @param validFrom
	 */
    private void addSurvey(SurveyDetailsData detailsData, SurveyData surveyData, LocalDateTime now, LocalDateTime validFrom) {
        final LocalDateTime twoWeeksAgo = now.minusDays(14);
        if (Objects.isNull(validFrom) || twoWeeksAgo.isAfter(validFrom)) {
            detailsData.getSurveys().add(surveyData);
        } else {
            //it is a new survey
            detailsData.getNewSurveys().add(surveyData);
            detailsData.setTabCount(detailsData.getTabCount() + 1);
        }
    }

    /**
     * @return
     */
    public SurveyDataPopulator getSurveyDataPopulator() {
        return surveyDataPopulator;
    }

    /**
     * @param surveyDataPopulator
     */
    public void setSurveyDataPopulator(final SurveyDataPopulator surveyDataPopulator) {
        this.surveyDataPopulator = surveyDataPopulator;
    }

    @Override
	public void sendSurveyResults(final SurveyResultData survey) {
		if (survey.getIsFetchedFromNotificationService()) {
			surveyDetailsEnhService.sendSurveyResults(survey);
		} else {
			surveyDetailsService.sendSurveyResults(survey);
		}
	}

	@Override
	public void validateSurveyResults(final SurveyResultData data) {

		final SurveyHDREnhData surveyHDRData = surveyDetailsEnhService.getSurveyBy(data.getSurveyId(),
				data.getSurveyVersion());
		final HashMap<String, SurveyAnswers> questionMap = new HashMap<>();
		data.getAnswers().forEach(answer -> questionMap.put(answer.getQuestId(), answer));

		for (SurvQuestDefEnhData question : surveyHDRData.getSurvQuestDefDataSet()) {
			if (question.getMandquest() && !questionMap.containsKey(question.getQuestID())) {
				throw new SagiaInvalidQuestionnaireException(
						"Invalid questionnaire. Mandatory questions are not present.");
			}
			if (questionMap.containsKey(question.getQuestID())) {
				final SurveyAnswers answer = questionMap.get(question.getQuestID());
				if (NUMBER_FIELD.equals(question.getAnswType()) && !answer.getTxtLg().matches("\\d+")) {
					throw new SagiaInvalidQuestionnaireException("Invalid questionnaire. Numeric question not valid");
				}
			}
		}
	}

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    /**
     * @return
     */
    public SagiaInvestorNotificationsFacade getInvestorNotificationsFacade() {
        return investorNotificationsFacade;
    }

    /**
     * @param investorNotificationsFacade
     */
    public void setInvestorNotificationsFacade(final SagiaInvestorNotificationsFacade investorNotificationsFacade) {
        this.investorNotificationsFacade = investorNotificationsFacade;
    }

    /**
     * @return
     */
    public SurveyDetailsService getSurveyDetailsService() {
        return surveyDetailsService;
    }

    /**
     * @param surveyDetailsService surveyDetailsService
     */
    public void setSurveyDetailsService(final SurveyDetailsService surveyDetailsService) {
        this.surveyDetailsService = surveyDetailsService;
    }
    
    public SurveyDetailsEnhService getSurveyDetailsEnhService() {
        return surveyDetailsEnhService;
    }

    /**
     * @param surveyDetailsEnhService surveyDetailsEnhService
     */
    public void setSurveyDetailsEnhService(final SurveyDetailsEnhService surveyDetailsEnhService) {
        this.surveyDetailsEnhService = surveyDetailsEnhService;
    }
    
    public SurveyEnhDataPopulator getSurveyEnhDataPopulator() {
        return surveyEnhDataPopulator;
    }

    /**
     * @param surveyEnhDataPopulator surveyEnhDataPopulator
     */
    public void setSurveyEnhDataPopulator(final SurveyEnhDataPopulator surveyEnhDataPopulator) {
        this.surveyEnhDataPopulator = surveyEnhDataPopulator;
    }
}
