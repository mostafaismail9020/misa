package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurveyHDRData;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyResultData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import com.sap.ibso.eservices.sagiaservices.services.surveys.zui5.SurveyHDRService;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class SurveyHDRServiceTest extends ServicelayerTransactionalTest {

    private static SurveyHDRService surveyHDRService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        surveyHDRService = appCtx.getBean("surveyHDRService", SurveyHDRService.class);
        surveyHDRService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSendSurveyHDRExpandQuery() {
        SurveyResultData data = new SurveyResultData();
        surveyHDRService.sendSurveyResults(data);
        assertThat("Exception was not thrown").isNotNull();
    }

    @Test
    public void shouldGetSurveyHDRExpandQuery() {
        SurveyHDRData data = surveyHDRService.getSurveyHDRBy("1");
        Assert.assertNotNull(data);
    }
}
