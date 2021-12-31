package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.facades.data.WarningLettersData;
import com.sap.ibso.eservices.facades.data.specialservices.FollowUpData;
import com.sap.ibso.eservices.facades.sagia.SagiaFollowUpFacade;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateViolationReplyFormData;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateWarningLetterExtensionFormData;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SagiaWarningLettersExtensionController
 */
@UnitTest
public class SagiaWarningLettersExtensionControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    private static final String WARNING_LETTERS_EXTENSION_PAGE = "warning-letters";
    private static final String WARNING_LETTERS_EXTENSION_CREATE_PAGE = "warning-letters-create";


    @Spy
    @InjectMocks
    private RedirectAttributes redirectAttributes;

    @Spy
    @InjectMocks
    private SagiaWarningLettersExtensionController sagiaWarningLettersExtensionController;

    @Mock
    private SagiaFollowUpFacade sagiaFollowUpFacade;

    @Mock
    private CreateWarningLetterExtensionFormData createWarningLetterExtensionFormData;

    @Mock
    private BindingResult result;

    @Mock
    private SagiaDateData sagiaDateData;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaWarningLettersExtensionControllerTest() {
        sagiaWarningLettersExtensionController = new SagiaWarningLettersExtensionController();
        redirectAttributes = new RedirectAttributesModelMap();
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
    public void warningLettersPageTest(){
        try{
            List<FollowUpData> warningLetters = new ArrayList<>();
            FollowUpData warningLetter = new FollowUpData();
            warningLetters.add(warningLetter);

            given(sagiaFollowUpFacade.getWarningLetterEntries()).willReturn(warningLetters);

            Assert.assertNull(sagiaWarningLettersExtensionController.warningLettersPage("", model));
            Assert.assertTrue(model.containsAttribute("warningLetters"));
            Assert.assertTrue(model.containsAttribute("selectedItem"));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void warningLettersCreatePageTest(){
        try{
            List<WarningLettersData> warningLetters = new ArrayList<>();
            WarningLettersData warningLetter = new WarningLettersData();
            warningLetters.add(warningLetter);

            given(sagiaFollowUpFacade.getWarningLettersExtensionData()).willReturn(warningLetters);

            Assert.assertNull(sagiaWarningLettersExtensionController.warningLettersCreatePage(model));
            Assert.assertTrue(model.containsAttribute("warningLetters"));
            Assert.assertTrue(model.containsAttribute("selectedWarningLetter"));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void warningLetterDetailsTest(){
        try{
            Assert.assertEquals("fragments/followup/expandedWarningLetter",sagiaWarningLettersExtensionController.warningLetterDetails("srID",request,model));
            verify(model).addAttribute("selectedItem",sagiaFollowUpFacade.getFollowUpEntry("srID"));
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void createViolationReplyTest(){
        try{
            doNothing().when(sagiaFollowUpFacade).createWarningLetterExtension(createWarningLetterExtensionFormData);
            Assert.assertEquals("redirect: /sagiastorefront/warning-letters",sagiaWarningLettersExtensionController.createWarningLetter(createWarningLetterExtensionFormData,null));
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getDateInfoDaysLessThanZeroTest(){
        try{
            Assert.assertEquals("",sagiaWarningLettersExtensionController.getDateInfo("",-1).getDateFormatted());
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getDateInfoDaysRuntimeExceptionTest(){
        try{
            given(sagiaFollowUpFacade.getDateData("", 1)).willThrow(new RuntimeException("Testing runtime exception.."));
            Assert.assertEquals("",sagiaWarningLettersExtensionController.getDateInfo("",1).getDateFormatted());
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getDateInfoDaysTest(){
        try{
            given(sagiaFollowUpFacade.getDateData("", 1)).willReturn(sagiaDateData);
            Assert.assertEquals(sagiaDateData,sagiaWarningLettersExtensionController.getDateInfo("",1));
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }
}
