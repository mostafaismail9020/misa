package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.SagiaLicenseCancellationFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GlobalLicenseCancellation;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseClearanceSetData;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseCancelFormData;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseClearanceFormData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

/**
 * Unit Test Class for LicenseCancellationController
 */

public class LicenseCancellationControllerTest extends AbstractCmsContentPageSetupUnitTestHelper{
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Spy
    @InjectMocks
    private LicenseCancellationController licenseCancellationController;

    @Mock
    private GlobalLicenseCancellation globalLicenseCancellation;

    @Mock
    private LicenseClearanceSetData licenseClearanceSetData;

    @Mock
    private SagiaLicenseCancellationFacade sagiaLicenseCancellationFacade;

    @Mock
    private ContentDetailsService contentDetailsService;

    @Mock
    private InputStream inputStream;
    @Mock
    private BindingResult result;

    @Mock
    private LicenseClearanceFormData licenseClearanceFormData;

    @Mock
    private LicenseCancelFormData licenseCancelFormData;

    @Mock
    private RedirectAttributes redirectAttributes;


    /**
     * Default constructor, initialize spied mocks.
     */
    public LicenseCancellationControllerTest() {
        licenseCancellationController = new LicenseCancellationController();
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
    public void getAttachmentsTest(){
        try{
            given(contentDetailsService.readAttachmentBy("objectId", "docGUID")).willReturn(inputStream);

            Assert.assertEquals(inputStream, licenseCancellationController.getAttachments("objectId","docGUID",request,model).getBody().getInputStream());
            Assert.assertEquals(HttpStatus.OK, licenseCancellationController.getAttachments("objectId","docGUID",request,model).getStatusCode());
            Assert.assertEquals(MediaType.parseMediaType("application/pdf"), licenseCancellationController.getAttachments("objectId","docGUID",request,model).getHeaders().getContentType());
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void submitCancellationDataHasErrorsTest(){
        try{
            given(result.hasErrors()).willReturn(true);
            Assert.assertEquals("conflict",licenseCancellationController.submitCancellationData(model,licenseCancelFormData,result,redirectAttributes));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void submitCancellationDataTest(){
        try{
            given(result.hasErrors()).willReturn(false);
            doNothing().when(sagiaLicenseCancellationFacade).createLicenseCancellation(licenseCancelFormData);
            given(sagiaLicenseCancellationFacade.getGlobalLicenseCancellation("","")).willReturn(globalLicenseCancellation);
            given(globalLicenseCancellation.getSrID()).willReturn("0");
            given(globalLicenseCancellation.getStage()).willReturn("01");

            Assert.assertNull(licenseCancellationController.submitCancellationData(model,licenseCancelFormData,result,redirectAttributes));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void nextStageClearanceDataHasErrorsTest(){
        try{
            given(result.hasErrors()).willReturn(true);
            Assert.assertEquals("conflict",licenseCancellationController.nextStageClearanceData(model,licenseClearanceFormData,result));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void nextStageClearanceDataTest(){
        try{
            given(result.hasErrors()).willReturn(false);
            doNothing().when(sagiaLicenseCancellationFacade).createLicenseCancellation(licenseCancelFormData);
            given(sagiaLicenseCancellationFacade.getGlobalLicenseCancellation("","")).willReturn(globalLicenseCancellation);
            given(globalLicenseCancellation.getSrStCode()).willReturn("E0001");
            given(globalLicenseCancellation.getSrID()).willReturn("1");
            given(globalLicenseCancellation.getStage()).willReturn("03");
            Assert.assertNull(licenseCancellationController.nextStageClearanceData(model,licenseClearanceFormData,result));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

//    @Test
//    public void nextStage1ClearanceDataHasErrorsTest(){
//        try{
//            given(result.hasErrors()).willReturn(false);
//            doNothing().when(sagiaLicenseCancellationFacade).createLicenseCancellation(licenseCancelFormData);
//            given(sagiaLicenseCancellationFacade.getGlobalLicenseCancellation("","")).willReturn(globalLicenseCancellation);
//            given(globalLicenseCancellation.getSrStCode()).willReturn("E0001");
//            given(globalLicenseCancellation.getSrID()).willReturn("1");
//            given(globalLicenseCancellation.getStage()).willReturn("04");
//            Assert.assertNull(licenseCancellationController.nextStage1ClearanceData(model,licenseClearanceFormData,result));
//        }catch (Exception e){
//            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
//        }
//    }
//
//    @Test
//    public void nextStage1ClearanceDataTest(){
//        try{
//            given(result.hasErrors()).willReturn(true);
//            Assert.assertEquals("conflict",licenseCancellationController.nextStage1ClearanceData(model,licenseClearanceFormData,result));
//        }catch (Exception e){
//            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
//        }
//    }

    @Test
    public void submitClearanceDataHasErrorsTest(){
        try{
            given(result.hasErrors()).willReturn(true);
            Assert.assertEquals("conflict",licenseCancellationController.submitClearanceData(model,licenseClearanceFormData,result,redirectAttributes));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void submitClearanceDataTest(){
        try{
            given(result.hasErrors()).willReturn(false);
            doNothing().when(sagiaLicenseCancellationFacade).createLicenseCancellation(licenseCancelFormData);
            given(sagiaLicenseCancellationFacade.getGlobalLicenseCancellation("","")).willReturn(globalLicenseCancellation);
            given(globalLicenseCancellation.getSrStCode()).willReturn("E0002");
            given(globalLicenseCancellation.getSrID()).willReturn("1");
            given(globalLicenseCancellation.getStage()).willReturn("05");
            Assert.assertNull(licenseCancellationController.nextStageClearanceData(model,licenseClearanceFormData,result));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }


}
