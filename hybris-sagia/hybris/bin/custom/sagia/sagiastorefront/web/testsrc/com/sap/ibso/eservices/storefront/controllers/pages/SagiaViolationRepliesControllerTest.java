package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.WarningLettersData;
import com.sap.ibso.eservices.facades.data.specialservices.FollowUpData;
import com.sap.ibso.eservices.facades.sagia.SagiaFollowUpFacade;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateViolationReplyFormData;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SagiaViolationRepliesController
 */
@UnitTest
public class SagiaViolationRepliesControllerTest extends AbstractCmsContentPageSetupUnitTestHelper{
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";
    private static final String VIOLATION_REPLIES_PAGE = "violation-replies";
    private static final String VIOLATION_REPLIES_CREATE_PAGE = "violation-replies-create";

    @Mock
    private SagiaFollowUpFacade sagiaFollowUpFacade;

    @Mock
    private CreateViolationReplyFormData createViolationReplyFormData;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Spy
    @InjectMocks
    private SagiaViolationRepliesController sagiaViolationRepliesController;


    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaViolationRepliesControllerTest() {

        sagiaViolationRepliesController = new SagiaViolationRepliesController();
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
    public void createViolationReplyTest(){
        try{
            doNothing().when(sagiaFollowUpFacade).createViolationReply(createViolationReplyFormData);
            Assert.assertEquals("redirect: /sagiastorefront/violation-replies",sagiaViolationRepliesController.createViolationReplies(createViolationReplyFormData,null));
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void violationsRepliesCreatePageTest(){
        try{
            List<WarningLettersData> warningLettersData = new ArrayList<>();
            WarningLettersData warningLetters = new WarningLettersData();
            warningLettersData.add(warningLetters);

            given(sagiaFollowUpFacade.getWarningLettersViolationRepliesData()).willReturn(warningLettersData);

            Assert.assertNull(sagiaViolationRepliesController.violationsRepliesCreatePage(model));

            verify(model).addAttribute("warningLetters",warningLettersData);
            verify(model).addAttribute("warningLetter",warningLetters);
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void violationsRepliesPageTest(){
        try{
            List<FollowUpData> violationReplies = new ArrayList<>();
            FollowUpData violationReply = new FollowUpData();
            violationReplies.add(violationReply);

            given(sagiaFollowUpFacade.getViolationReplyEntries()).willReturn(violationReplies);

            Assert.assertNull(sagiaViolationRepliesController.violationsRepliesPage("", model));
            Assert.assertTrue(model.containsAttribute("selectedItem"));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void warningLetterDetailsTest(){
        try{
            Assert.assertEquals("fragments/followup/expandedViolationReply",sagiaViolationRepliesController.warningLetterDetails("srID",request,model));
            verify(model).addAttribute("selectedItem",sagiaFollowUpFacade.getFollowUpEntry("srID"));
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }


}
