package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaRealEstateAttachmentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.BDDMockito.given;

/**
 * Unit Test Class for RealEstateAttachmentController
 */
public class RealEstateAttachmentControllerTest {
    public static final String PDF = "application/pdf";
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private RealEstateAttachmentController realEstateAttachmentController;

    @Mock
    private InputStream inputStream;

    /**
     * Default constructor, initialize spied mocks.
     */
    public RealEstateAttachmentControllerTest() {
        realEstateAttachmentController = new RealEstateAttachmentController();
    }

    @Mock
    private SagiaRealEstateAttachmentService sagiaRealEstateAttachmentService;

    /**
     * Initialize mocks
     */
    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getPdfAttachmentTest(){
        try {
            given(sagiaRealEstateAttachmentService.readRealEstatePdf("objectId", "documentId")).willReturn(inputStream);

            Assert.assertEquals(HttpStatus.OK, realEstateAttachmentController.getPdfAttachment("objectId","documentId").getStatusCode());
            Assert.assertEquals(MediaType.parseMediaType(PDF),realEstateAttachmentController.getPdfAttachment("objectId","documentId").getHeaders().getContentType());
            Assert.assertEquals(inputStream,realEstateAttachmentController.getPdfAttachment("objectId","documentId").getBody().getInputStream());
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getPdfAttachmentExceptionTest(){
        given( sagiaRealEstateAttachmentService.readRealEstatePdf("objectId", "documentId")).willThrow(new RuntimeException("Testing runtime exception"));
        //given(govtServicesAttachmentService).willThrow(new RuntimeException("Testing runtime exception"));
        Assert.assertEquals(HttpStatus.CONFLICT,realEstateAttachmentController.getPdfAttachment("objectId","documentId").getStatusCode());
    }
}
