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
package com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SurveyCRMData {
    @JsonProperty("Surveyid")
    private String surveyId;

    @JsonProperty("ApplicationId")
    private String applicationId;

    @JsonProperty("Surveyversion")
    private String surveyVersion;

    @JsonProperty("SurveyHdrToSurvQuestAnsNav")
    private List<SurveyAnswers> answers;

    /**
     * @return
     */
    public String getSurveyId() {
        return surveyId;
    }

    /**
     * @param surveyId
     */
    public void setSurveyId(final String surveyId) {
        this.surveyId = surveyId;
    }

    /**
     * @return
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId
     */
    public void setApplicationId(final String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * @return
     */
    public String getSurveyVersion() {
        return surveyVersion;
    }

    /**
     * @param surveyVersion
     */
    public void setSurveyVersion(final String surveyVersion) {
        this.surveyVersion = surveyVersion;
    }

    /**
     * @return
     */
    public List<SurveyAnswers> getAnswers() {
        return answers;
    }

    /**
     * @param answers
     */
    public void setAnswers(final List<SurveyAnswers> answers) {
        this.answers = answers;
    }
}
