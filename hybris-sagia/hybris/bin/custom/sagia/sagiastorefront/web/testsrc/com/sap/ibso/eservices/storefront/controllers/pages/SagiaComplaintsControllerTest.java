package com.sap.ibso.eservices.storefront.controllers.pages;


import atg.taglib.json.util.JSONException;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ComplaintsAndEnquiryHdrsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.ComplaintsAndEnquiryService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.UpdatableComplaintDetails;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.util.concurrent.Runnables.doNothing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SagiaComplaintsController
 */
@UnitTest
public class SagiaComplaintsControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Mock
    ComplaintsAndEnquiryService complaintsAndEnquiryService;

    @Mock
    CustomizationListService customizationListService;

    @Mock
    ContentDetailsService contentDetailsService;

    @Mock
    ComplaintsAndEnquiryHdrsData complaintsAndEnquiryHdrsData;

    @Mock
    CustomizingGetData customizingGetData;

    @Mock
    ComplaintFormData complaintFormData;

    @Mock
    BindingResult bindingResult;

    @Mock
    UpdatableComplaintDetails updatableComplaintDetails;

    @Mock
    RedirectAttributes redirectModel;


    @Spy
    @InjectMocks
    private SagiaComplaintsController sagiaComplaintsController;


    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaComplaintsControllerTest() {
        sagiaComplaintsController = new SagiaComplaintsController();
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
    public void getComplaintAndEnquiryTest(){

        given(complaintsAndEnquiryService.getComplaintBy("srID")).willReturn(complaintsAndEnquiryHdrsData);

        assertEquals("fragments/complaint/expandedComplaint", sagiaComplaintsController.getComplaintAndEnquiry("srID", request, model));

        verify(model).addAttribute("expandedComplaintFormData", complaintsAndEnquiryHdrsData);
        verify(model).addAttribute("messages", complaintsAndEnquiryHdrsData.getCompAndEnqHdrToTextNav());
        verify(model).addAttribute("uploadedAttachments", complaintsAndEnquiryHdrsData.getCompAndEnqHdrToContentNav());

    }


    @Test
    public void getComplaintAndEnquiryExceptionTest(){

        doThrow(new RuntimeException("Testing Runtime Exception..")).when(complaintsAndEnquiryService).getComplaintBy("srID");
        assertEquals("fragments/complaint/expandedComplaint", sagiaComplaintsController.getComplaintAndEnquiry("srID", request, model));
        }

    @Test
    public void getComplaintAndEnquiryMessagesTest(){
       given(complaintsAndEnquiryService.getComplaintBy("srID")).willReturn(complaintsAndEnquiryHdrsData);
       assertEquals(complaintsAndEnquiryHdrsData.getCompAndEnqHdrToTextNav(), sagiaComplaintsController.getComplaintAndEnquiryMessages("srID"));

    }

    @Test
    public void getComplaintAndEnquiryMessagesExceptionTest(){
        doThrow(new RuntimeException("Testing Runtime Exception..")).when(complaintsAndEnquiryService).getComplaintBy("srID");
        assertEquals(complaintsAndEnquiryHdrsData.getCompAndEnqHdrToTextNav(), sagiaComplaintsController.getComplaintAndEnquiryMessages("srID"));

    }

    @Test
    public void submitTest() throws JSONException {
        List<CustomizingGetData> listCustomizing = new ArrayList<>();
        listCustomizing.add(customizingGetData);
        given(customizationListService.readComplaintsSupportingAttachments()).willReturn(listCustomizing);
        given(bindingResult.hasErrors()).willReturn(false);
        Mockito.doNothing().when(complaintsAndEnquiryService).createComplaint(complaintFormData, listCustomizing);
        assertEquals("redirect:/my-sagia/sagia-profile", sagiaComplaintsController.submit(model,complaintFormData, bindingResult, redirectModel));
        assertEquals("redirect:/my-sagia/sagia-profile", sagiaComplaintsController.submit(model,complaintFormData, bindingResult, redirectModel));


        }


    @Test
    public void submitWithBindingErrorsTest() throws JSONException {
         given(bindingResult.hasErrors()).willReturn(true);
        assertEquals("conflict", sagiaComplaintsController.submit(model,complaintFormData, bindingResult, redirectModel));


    }

    @Test
    public void submitExceptionTest() throws JSONException {
        List<CustomizingGetData> listCustomizing = new ArrayList<>();
        listCustomizing.add(customizingGetData);
        given(customizationListService.readComplaintsSupportingAttachments()).willReturn(listCustomizing);
        given(bindingResult.hasErrors()).willReturn(false);
        doThrow(new RuntimeException("Testing Runtime Exception..")).when(complaintsAndEnquiryService).createComplaint(complaintFormData, listCustomizing);
        assertEquals("redirect:/my-sagia/sagia-profile", sagiaComplaintsController.submit(model,complaintFormData, bindingResult, redirectModel));


    }

    @Test
    public void updateComplaintTest(){
        Mockito.doNothing().when(complaintsAndEnquiryService).updateComplaint(updatableComplaintDetails, "srID");
        assertEquals(complaintsAndEnquiryService.getComplaintBy("srID"),sagiaComplaintsController.updateComplaint("srID",updatableComplaintDetails,request, bindingResult));
    }

    @Test
    public void updateComplaintExceptionTest(){
        doThrow(new RuntimeException("Testing Runtime Exception..")).when(complaintsAndEnquiryService).updateComplaint(updatableComplaintDetails, "srID");
        assertEquals(complaintsAndEnquiryService.getComplaintBy("srID"),sagiaComplaintsController.updateComplaint("srID",updatableComplaintDetails,request, bindingResult));
    }


    @Test
    public void getComplaintFormData(){
        assertNotNull(sagiaComplaintsController.getComplaintFormData());
    }





}
