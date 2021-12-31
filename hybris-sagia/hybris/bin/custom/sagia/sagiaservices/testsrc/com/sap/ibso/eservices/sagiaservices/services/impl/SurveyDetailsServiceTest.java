package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurveyHDRData;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyAnswers;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyResultData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import com.sap.ibso.eservices.sagiaservices.services.surveys.zesrv.SurveyDetailsEnhService;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SurveyHDREnhData;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class SurveyDetailsServiceTest extends ServicelayerTransactionalTest {

    private static SurveyDetailsEnhService surveyDetailsService;
    private static final String SURVEY_ID = "Surveyid";
    private static final String SURVEY_VERSION = "SurveyVersion";
    private static final String SURVEY_DEFAULT_VERSION = "0000000001";
    private static final String SURVEY_LANG = "Language";

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        surveyDetailsService = appCtx.getBean("surveyDetailsService", SurveyDetailsEnhService.class);
        surveyDetailsService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetSurvey() {
        SurveyHDREnhData surveyHDRData = surveyDetailsService.getSurveyBy("1", "1");
        Assert.assertTrue(surveyHDRData != null);
    }

    @Test
    public void shouldGetAmendProductsWithDescription() {
        final String langCode = surveyDetailsService.getI18NService().getCurrentLocale().getLanguage().toUpperCase();

        SurveyAnswers surveyAnswers = new SurveyAnswers();
        surveyAnswers.setAnswerId("1");
        surveyAnswers.setQuestId("1");
        surveyAnswers.setSurveyId("1");
        surveyAnswers.setTxtLg("1");
        surveyAnswers.setAnswerId("1");
        SurveyResultData surveyResultData = new SurveyResultData();
        surveyResultData.setAnswers(Arrays.asList(surveyAnswers));
        surveyResultData.setSurveyId("1");
        surveyResultData.setTransactionId("1");
        surveyResultData.setApplicationId("1");
        surveyResultData.setSurveyVersion("1");

        final Map<String, String> parameters = new HashMap<>();
        parameters.put(SURVEY_ID, "1");
        parameters.put(SURVEY_VERSION, "1");
        parameters.put(SURVEY_LANG, langCode);
        surveyDetailsService.sendSurveyResults(surveyResultData);
        assertThat("Exception was not thrown").isNotNull();
    }
}
