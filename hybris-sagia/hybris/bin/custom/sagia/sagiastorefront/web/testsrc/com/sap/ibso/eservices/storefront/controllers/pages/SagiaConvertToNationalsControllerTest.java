package com.sap.ibso.eservices.storefront.controllers.pages;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ConvToNationalsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.sagiaservices.services.license.ConvToNationalsFormData;
import com.sap.ibso.eservices.sagiaservices.services.license.ConvToNationalsResubmitFormData;
import com.sap.ibso.eservices.sagiaservices.services.license.ConvertToNationalsAttachmentFile;
import com.sap.ibso.eservices.sagiaservices.services.license.ConvertToNationalsService;
import com.sap.ibso.eservices.storefront.controllers.pages.SagiaConvertToNationalsController;
import de.hybris.bootstrap.annotations.UnitTest;

/**
 * Unit Test Class for SagiaConvertToNationalsController
 */
@UnitTest
public class SagiaConvertToNationalsControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {

    @Mock
    CustomizationListService customizationListService;

    @Mock
    ConvertToNationalsService convertToNationalsService;

    @Mock
    ConvToNationalsData convToNationalsData;

    @Mock
    CustomizingGetData customizingGetData;

    @Mock
    ContentHDRData convNatToContentNavData;

    @Mock
    ConvToNationalsFormData convToNationalsFormData;

    @Mock
    BindingResult bindingResult;

    @Mock
    ConvertToNationalsAttachmentFile convertToNationalsAttachmentFile;

    @Mock
    MultipartFile multipartFile;

    @Mock
    ConvToNationalsResubmitFormData convToNationalsResubmitFormData;


    @Spy
    @InjectMocks
    private SagiaConvertToNationalsController sagiaConvertToNationalsController;


    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaConvertToNationalsControllerTest() {
        sagiaConvertToNationalsController = new SagiaConvertToNationalsController();
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
    public void getConvertToNationalDetailsTest(){

        given(convertToNationalsService.getConvertToNationalBy("srID")).willReturn(convToNationalsData);
        List<CustomizingGetData> customizingGetDataList = new ArrayList<>();
        customizingGetDataList.add(customizingGetData);
        given(customizationListService.readConvToNationalsInfo()).willReturn(customizingGetDataList);

        assertEquals(HttpStatus.OK,sagiaConvertToNationalsController.getConvertToNationalDetails("srID", request,model).getStatusCode());


    }

    @Test
    public void getConvertToNationalDetailsWithRuntimeExceptionTest(){

        doThrow(new RuntimeException("Testing Runtime Exception...")).when(convertToNationalsService).getConvertToNationalBy("srID");
        List<CustomizingGetData> customizingGetDataList = new ArrayList<>();
        customizingGetDataList.add(customizingGetData);
        given(customizationListService.readConvToNationalsInfo()).willReturn(customizingGetDataList);
        assertEquals(HttpStatus.OK,sagiaConvertToNationalsController.getConvertToNationalDetails("srID", request,model).getStatusCode());

    }

    @Test
    public void getConvertToNationalMessagesTest(){
        given(convertToNationalsService.getConvertToNationalBy("srID")).willReturn(convToNationalsData);
        List<ContentHDRData> convNatToContentNavDataList = new ArrayList<>();
        convNatToContentNavDataList.add(convNatToContentNavData);
        given(convToNationalsData.getConvNatToContentNav()).willReturn(convNatToContentNavDataList);
        assertEquals(HttpStatus.OK, sagiaConvertToNationalsController.getConvertToNationalMessages("srID",request, model).getStatusCode());

    }

    @Test
    public void getConvertToNationalMessagesWithRuntimeExceptionTest(){
        doThrow(new RuntimeException("Testing Runtime Exception...")).when(convertToNationalsService).getConvertToNationalBy("srID");
        List<ContentHDRData> convNatToContentNavDataList = new ArrayList<>();
        convNatToContentNavDataList.add(convNatToContentNavData);
        given(convToNationalsData.getConvNatToContentNav()).willReturn(convNatToContentNavDataList);
        assertEquals(HttpStatus.OK, sagiaConvertToNationalsController.getConvertToNationalMessages("srID",request, model).getStatusCode());

    }

    @Test
    public void submitTest(){
        List<CustomizingGetData> customizingGetDataList = new ArrayList<>();
        customizingGetDataList.add(customizingGetData);
        given(customizationListService.readConvToNationalsSupportingAttachments()).willReturn(customizingGetDataList);
        sagiaConvertToNationalsController.submit(model, convToNationalsFormData,null);
    }


    @Test
    public void submitWithRuntimeExceptionTest(){
        List<CustomizingGetData> customizingGetDataList = new ArrayList<>();
        customizingGetDataList.add(customizingGetData);
        doThrow(new RuntimeException("Testing Runtime Exception")).when(customizationListService).readConvToNationalsSupportingAttachments();
        sagiaConvertToNationalsController.submit(model, convToNationalsFormData,null);
    }

    @Test
    public void resubmitTest(){
        given(convertToNationalsService.getConvertToNationalBy( "srId")).willReturn(convToNationalsData);
        List<ContentHDRData> convNatToContentNavDataList = new ArrayList<>();
        convNatToContentNavDataList.add(convNatToContentNavData);
        List<ConvertToNationalsAttachmentFile> newlyUploadedFiles = new ArrayList<>();
        newlyUploadedFiles.add(convertToNationalsAttachmentFile);
        given(convertToNationalsAttachmentFile.getMultiPartFile()).willReturn(multipartFile);
        given(multipartFile.getSize()).willReturn(Long.valueOf(10));
        doNothing().when(convertToNationalsService).update(convToNationalsResubmitFormData, convNatToContentNavDataList);
        sagiaConvertToNationalsController.resubmit("srId", "srGuid",model,convToNationalsResubmitFormData);
    }

    @Test
    public void resubmitWithRuntimeExceptionTest(){
        given(convertToNationalsService.getConvertToNationalBy( "srId")).willReturn(convToNationalsData);
        List<ContentHDRData> convNatToContentNavDataList = new ArrayList<>();
        convNatToContentNavDataList.add(convNatToContentNavData);
        List<ConvertToNationalsAttachmentFile> newlyUploadedFiles = new ArrayList<>();
        newlyUploadedFiles.add(convertToNationalsAttachmentFile);
        given(convertToNationalsAttachmentFile.getMultiPartFile()).willReturn(multipartFile);
        given(multipartFile.getSize()).willReturn(Long.valueOf(10));
        doThrow(new RuntimeException("Testing Runtime Exception...")).when(convertToNationalsService).update(any(ConvToNationalsResubmitFormData.class), anyList());
        sagiaConvertToNationalsController.resubmit("srId", "srGuid",model,convToNationalsResubmitFormData);

    }
}
