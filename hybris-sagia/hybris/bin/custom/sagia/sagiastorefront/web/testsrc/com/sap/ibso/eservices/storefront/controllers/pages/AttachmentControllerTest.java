package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.classification.ClassificationUpgradeContentService;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

import static org.mockito.BDDMockito.given;

/**
 * Unit Test Class for AttachmentController
 */
@UnitTest
public class AttachmentControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";
    public static final String PDF = "application/pdf";


    @Spy
    @InjectMocks
    private AttachmentController attachmentController;

    @Mock
    private ContentDetailsService contentDetailsService;

    @Mock
    private ClassificationUpgradeContentService classificationUpgradeContentService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private InputStream inputStream;

    @Mock
    private InputStreamResource inputStreamResource;

    @Mock
    private HttpHeaders httpHeaders;

    /**
     * Default constructor, initialize spied mocks.
     */
    public AttachmentControllerTest() {
        attachmentController = new AttachmentController();
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
        try{
            given(contentDetailsService.readAttachmentBy("objectId", "documentId")).willReturn(inputStream);

            Assert.assertEquals(inputStream, attachmentController.getPdfAttachment("objectId","documentId").getBody().getInputStream());
            Assert.assertEquals(HttpStatus.OK, attachmentController.getPdfAttachment("objectId","documentId").getStatusCode());
            Assert.assertEquals(MediaType.parseMediaType(PDF), attachmentController.getPdfAttachment("objectId","documentId").getHeaders().getContentType());
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getZClassAttachmentTest(){
        try{
            given(classificationUpgradeContentService.readContentAttachmentBy("objectId", "docGUID")).willReturn(inputStream);

            Assert.assertEquals(inputStream, attachmentController.getZClassAttachment("objectId","docGUID",request,model).getBody().getInputStream());
            Assert.assertEquals(HttpStatus.OK, attachmentController.getZClassAttachment("objectId","docGUID",request,model).getStatusCode());
            Assert.assertEquals(MediaType.parseMediaType(PDF), attachmentController.getZClassAttachment("objectId","docGUID",request,model).getHeaders().getContentType());
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getFileTest(){
        try{
            given(classificationUpgradeContentService.readContentAttachmentBy("objectId", "docGUID")).willReturn(inputStream);

            Assert.assertEquals(inputStream, attachmentController.getFile("objectId","docGUID",request,model).getBody().getInputStream());
            Assert.assertEquals(HttpStatus.OK, attachmentController.getFile("objectId","docGUID",request,model).getStatusCode());
            Assert.assertEquals(MediaType.parseMediaType(PDF), attachmentController.getFile("objectId","docGUID",request,model).getHeaders().getContentType());
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }
}
