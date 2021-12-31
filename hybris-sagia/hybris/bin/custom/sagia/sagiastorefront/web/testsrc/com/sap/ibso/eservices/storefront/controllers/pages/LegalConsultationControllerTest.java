/*
package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.AttachmentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.GetTextData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ODataService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.AttachmentHDRService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.ContentHDRService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.GetTextService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.LegalInquiryHDRService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.apache.olingo.odata2.api.edm.Edm;
import org.apache.olingo.odata2.api.edm.EdmEntityContainer;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


*/
/**
 * Unit Test Class for SagiaLegalConsultationController
 *//*

@UnitTest
public class LegalConsultationControllerTest  extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Mock
    LegalInquiryHDRService legalInquiryHDRService;
    @Mock
    AttachmentHDRService attachmentHDRService;
    @Mock
    ContentHDRService contentHDRService;
    @Mock
    GetTextService getTextService;

    @Mock
    LegalInquiryHDRData legalInquiryHDRData ;

    @Mock
    AttachmentHDRData attachmentHDRData;

    @Mock
    ContentHDRData contentHDRData;

    @Mock
    GetTextData getTextData;


    @Spy
    @InjectMocks
    private SagiaLegalConsultationController legalConsultationController;


    */
/**
 * Default constructor, initialize spied mocks.
 *//*

    public LegalConsultationControllerTest() {

        legalConsultationController = new SagiaLegalConsultationController();
    }


    */
/**
 * Initialize mocks
 *//*

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        setupCmsContentPage();
    }

    @Test
    public void getLegalConsultationListTest(){
     */
/*   try {
            given(oDataService.getEdm()).willReturn(edm);

        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
    }

        try {
            given(edm.getDefaultEntityContainer()).willReturn(edmEntityContainer);
            given(edmEntityContainer.getEntitySet(anyString())).willReturn(entitySet);
        } catch (EdmException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
*//*


        Collection<LegalInquiryHDRData> collection = Arrays.asList(legalInquiryHDRData);
        given(legalInquiryHDRService.getLegalConsultationList()).willReturn(collection);

        try {
            assertEquals("pages/legalconsultations/legalConsultation", legalConsultationController.getLegalConsultationList(model));
        } catch (CMSItemNotFoundException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        verify(model).addAttribute("legalConsultation_list", legalInquiryHDRData);

    }


    @Test
    public void getLegalConsultationAttachmentTest(){

        Collection<AttachmentHDRData> collection = Arrays.asList(attachmentHDRData);
        doReturn(collection).when(attachmentHDRService).getLegalConsultationAttachment();

        try {
            assertEquals("pages/legalconsultations/legalConsultation", legalConsultationController.getLegalConsultationAttachment(model));
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        verify(model).addAttribute("legalConsultation_attachment", collection);;

    }

    @Test
    public void getLegalConsultationContentTest(){

        Collection<ContentHDRData> collection = Arrays.asList(contentHDRData);
        given(contentHDRService.getLegalConsultationContent()).willReturn(collection);

        try {
            assertEquals("pages/legalconsultations/legalConsultation", legalConsultationController.getLegalConsultationContent(model));
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

        verify(model).addAttribute("legalConsultation_content", collection);


    }

    @Test
    public void getLegalConsultationGetText(){

        Collection<GetTextData> collection = Arrays.asList(getTextData);
        given(getTextService.getLegalConsultationGetText()).willReturn(collection);

        try {
            assertEquals("pages/legalconsultations/legalConsultation", legalConsultationController.getLegalConsultationGetText(model));
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        verify(model).addAttribute("legalConsultation_gettext", collection);
    }


}*/
