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

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SurveyAnswers {
    @JsonProperty("Surveyid")
    private String surveyId;
    @JsonProperty("QuestID")
    private String questId;
    @JsonProperty("AnswerID")
    private String answerId;
    @JsonProperty("Txtlg")
    private String txtLg;
    @JsonProperty("AnswType")
    private String answType;

    /**
     * 
     * @return
     */
    public String getAnswType() {
		return answType;
	}

    /**
     * 
     * @param answType
     */
	public void setAnswType(String answType) {
		this.answType = answType;
	}

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
    public String getQuestId() {
        return questId;
    }

    /**
     * @param questId
     */
    public void setQuestId(final String questId) {
        this.questId = questId;
    }

    /**
     * @return
     */
    public String getAnswerId() {
        return answerId;
    }

    /**
     * @param answerId
     */
    public void setAnswerId(final String answerId) {
        this.answerId = answerId;
    }

    /**
     * @return
     */
    public String getTxtLg() {
        return txtLg;
    }

    /**
     * @param txtLg
     */
    public void setTxtLg(final String txtLg) {
        this.txtLg = txtLg;
    }

}
