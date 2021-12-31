package com.sap.ibso.eservices.sagiaservices.services.surveys.zui5;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurveyHDRData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyCRMData;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyResultData;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * SurveyHDRService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class SurveyHDRService extends AbstractSagiaService<SurveyHDRData> {

    private static final String SURVEY_DATA_EXPAND = "SurveyHdrToSurvQuestDefNav,SurveyHdrToSurvQuestDefNav/SurveyQuestDefToSurvQuestDDLBNav";

    /**
     * retrieves SurveyHDRData by id
     * @param id id
     * @return SurveyHDRData
     * @throws IOException exception
     */
    public final SurveyHDRData get(String id) {
        return super.get(SurveyHDRData.class, (Object) id, REQUEST_PARAMETER_EXPAND, SURVEY_DATA_EXPAND);
    }

    /**
     * sends SurveyResults
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
     * retrieves SurveyHDR by id
     * @param surveyid surveyid
     * @return SurveyHDRData
     */
    public final SurveyHDRData getSurveyHDRBy(String surveyid) {
        return get(SurveyHDRData.class, surveyid, new QueryOptionsBuilder()
                .expand(createSurveyHDRExpandQuery())
                .build());

    }

    /**
     * creates SurveyHDRExpandQuery
     * @return String
     */
    private String createSurveyHDRExpandQuery() {
        return Arrays.asList(SurveyHDRExpandableEntities.values())
                .stream()
                .map(SurveyHDRExpandableEntities::navEntity)
                .collect(Collectors.joining("/"));
    }

}
