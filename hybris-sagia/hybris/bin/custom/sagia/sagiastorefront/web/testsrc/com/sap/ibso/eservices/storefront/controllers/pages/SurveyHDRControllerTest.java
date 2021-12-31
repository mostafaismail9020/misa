package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.SurveyData;
import com.sap.ibso.eservices.facades.sagia.SagiaAccountFacade;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SurveyHDRController
 */
@UnitTest
public class SurveyHDRControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Spy
    @InjectMocks
    private SurveyHDRController surveyHDRController;

    @Mock
    private SagiaAccountFacade sagiaAccountFacade;

    @Mock
    private SurveyData surveyHDRData;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SurveyHDRControllerTest() {
        surveyHDRController = new SurveyHDRController();
    }

    /**
     * Initialize mocks
     */
    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);
        setupCmsContentPage();
    }

    @Test
    public void getSurveyHDRByIdTest(){
        try{
            given(sagiaAccountFacade.getSurveyHDRById("")).willReturn(surveyHDRData);
            Assert.assertNull(surveyHDRController.getSurveyHDRById("''",request,model));
            verify(model).addAttribute("surveyData",surveyHDRData);
            verify(model).addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getSurveyHDRByIdRuntimeExceptionTest(){
        try{
            given(sagiaAccountFacade.getSurveyHDRById("")).willThrow(new RuntimeException("Testing Runtime Exception..."));
           surveyHDRController.getSurveyHDRById("''",request,model);
           }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }


}
