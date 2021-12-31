package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.impl.GovtServicesAttachmentService;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.BDDMockito.given;

/**
 * Unit Test Class for GovtServicesAttachmentController
 */
@UnitTest
public class GovtServicesAttachmentControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    public static final String PDF = "application/pdf";
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private GovtServicesAttachmentController govtServicesAttachmentController;

    @Mock
    private GovtServicesAttachmentService govtServicesAttachmentService;

    @Mock
    private InputStream inputStream;

    /**
     * Default constructor, initialize spied mocks.
     */
    public GovtServicesAttachmentControllerTest() {
        govtServicesAttachmentController = new GovtServicesAttachmentController();
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
    public void getPdfAttachmentTest(){
        try {
            given(govtServicesAttachmentService.readGovtServicesPdf("objectId", "documentId")).willReturn(inputStream);

            Assert.assertEquals(HttpStatus.OK,govtServicesAttachmentController.getPdfAttachment("objectId","documentId").getStatusCode());
            Assert.assertEquals(MediaType.parseMediaType(PDF),govtServicesAttachmentController.getPdfAttachment("objectId","documentId").getHeaders().getContentType());
            Assert.assertEquals(inputStream,govtServicesAttachmentController.getPdfAttachment("objectId","documentId").getBody().getInputStream());
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getPdfAttachmentExceptionTest(){
        given(govtServicesAttachmentService.readGovtServicesPdf("objectId", "documentId")).willThrow(new RuntimeException("Testing runtime exception"));
        //given(govtServicesAttachmentService).willThrow(new RuntimeException("Testing runtime exception"));
        Assert.assertEquals(HttpStatus.CONFLICT,govtServicesAttachmentController.getPdfAttachment("objectId","documentId").getStatusCode());
    }
}
