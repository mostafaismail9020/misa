package com.sap.ibso.eservices.storefront.controllers.pages;


import com.sap.ibso.eservices.facades.sagia.impl.DefaultLicensePrintingFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for LicensePrintingController
 */
public class LicensePrintingControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Spy
    @InjectMocks
    private LicensePrintingController licensePrintingController;

    @Mock
    private DefaultLicensePrintingFacade sagiaLicensePrintingFacade;

    @Spy
    @InjectMocks
    private HttpServletResponse responseMock;

    @Mock
    private ServletOutputStream outputStream;

    /**
     * Default constructor, initialize spied mocks.
     */
    public LicensePrintingControllerTest() {
        licensePrintingController = new LicensePrintingController();
        responseMock = new MockHttpServletResponse();
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
    public void printWrittenNotificationNullPdfTest(){
        given(sagiaLicensePrintingFacade.getLicense()).willReturn(null);
        licensePrintingController.printLicense(model,request,response);
        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND,response.getStatus());
    }

    @Test
    public void printWrittenNotificationTest(){
        try{
            byte[] pdf = new byte[]{1};
            given(sagiaLicensePrintingFacade.getLicense()).willReturn(pdf);
            given(responseMock.getOutputStream()).willReturn(outputStream);
            licensePrintingController.printLicense(model,request,responseMock);
            verify(responseMock).setHeader("Content-Disposition", "inline;filename=myLicense.pdf");
            verify(responseMock).setContentType("application/pdf");
            verify(responseMock).setDateHeader("Expires", -1);
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void printWrittenNotificationIoExceptionTest(){
        try{
            byte[] pdf = new byte[]{1};
            given(sagiaLicensePrintingFacade.getLicense()).willReturn(pdf);
            given(responseMock.getOutputStream()).willReturn(outputStream);
            doThrow(new IOException("Testing SC_INTERNAL_SERVER_ERROR")).doNothing().when(outputStream).write(pdf);
            licensePrintingController.printLicense(model,request,responseMock);
            Assert.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, responseMock.getStatus());
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }
}