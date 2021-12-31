package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.data.BranchData;
import com.sap.ibso.eservices.facades.sagia.SagiaGovernmentDocumentsFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseFacade;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.core.Registry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;
import java.util.Collection;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * Unit Test Class for GovernmentDocumentsController
 */
@UnitTest
public class GovernmentDocumentsControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Spy
    @InjectMocks
    private RedirectAttributes redirectAttributes;

    @Spy
    @InjectMocks
    private GovernmentDocumentsController governmentDocumentsController;

    @Mock
    private SagiaLicenseFacade licenseFacade;

    @Mock
    private SagiaGovernmentDocumentsFacade governmentDocumentsFacade;

    @Mock
    private Validator branchValidator;

    @Mock
    private BranchData branchData;

    @Mock
    private BindingResult bindingResult;


    /**
     * Default constructor, initialize spied mocks.
     */
    public GovernmentDocumentsControllerTest() {
        governmentDocumentsController = new GovernmentDocumentsController();
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
    public void getMainBranchContainsAttributeTest(){
        try {
            given(model.containsAttribute("org.springframework.validation.BindingResult.branchData")).willReturn(true);
            given(licenseFacade.getGovtHeader("")).willReturn(branchData);
            governmentDocumentsController.getMainBranch(model);

            verify(model).addAttribute("branchData",branchData);
            verify(model).addAttribute("amanahs", governmentDocumentsFacade.getAmanahList());
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getMainBranchContainsAttributeExceptionTest(){
        try {
            given(model.containsAttribute("org.springframework.validation.BindingResult.branchData")).willReturn(true);
            given(licenseFacade.getGovtHeader("")).willThrow(new RuntimeException("Testing runtime exception"));
            governmentDocumentsController.getMainBranch(model);

            verify(model).addAttribute("amanahs", governmentDocumentsFacade.getAmanahList());
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getBranchesHasErrorsTest(){
        Registry.activateMasterTenant();
        try
        {
            doNothing().when(branchValidator).validate(branchData,bindingResult);
            given(bindingResult.hasErrors()).willReturn(true);

            //Assert.assertEquals("redirect:/governmentDocuments",governmentDocumentsController.getBranches(branchData,model,bindingResult,redirectAttributes));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

        verify(model).addAttribute(Matchers.eq(GlobalMessages.ERROR_MESSAGES_HOLDER),anyObject());
    }

    @Test
    public void getBranchesHasNoErrorsTest(){
        try
        {
            BranchData branchData = new BranchData();
            doNothing().when(branchValidator).validate(branchData,bindingResult);
            given(bindingResult.hasErrors()).willReturn(false);
            Collection<BranchData> branches = licenseFacade.getBranches("613392");

            //governmentDocumentsController.getBranches(branchData,model,bindingResult,redirectAttributes);

            verify(model).addAttribute("branches", branches);
            verify(model).addAttribute("branchesJSON", new Gson().toJson(branches));
            verify(model).addAttribute("amanahs", governmentDocumentsFacade.getAmanahList());
            Assert.assertTrue(model.containsAttribute("mainBranchJSON"));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

    }

    @Test
    public void updateBranchesTest(){
        try{
            doNothing().when(licenseFacade).saveGovernmentHeader(branchData);
            Assert.assertEquals(new Gson().toJson("Some service ID"), governmentDocumentsController.updateBranches(branchData,model).getBody());
            Assert.assertEquals(HttpStatus.CREATED, governmentDocumentsController.updateBranches(branchData,model).getStatusCode());
        }catch(Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }
}
