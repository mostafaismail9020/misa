package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.impl.DefaultLicensePrintingFacade;
import com.sap.ibso.eservices.facades.sagia.impl.SagiaInvestorNotificationsFacade;
import com.sap.ibso.eservices.sagiaservices.services.notifications.InvestorNotificationCount;
import com.sap.ibso.eservices.sagiaservices.services.notifications.dto.SagiaInvestorNotificationDTO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for InvestorNotificationController
 */
public class InvestorNotificationControllerTest extends AbstractCmsContentPageSetupUnitTestHelper{
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Spy
    @InjectMocks
    private InvestorNotificationController investorNotificationController;

    @Mock
    private SagiaInvestorNotificationsFacade investorNotificationsFacade;

    @Mock
    private DefaultLicensePrintingFacade sagiaLicensePrintingFacade;

    @Mock
    private List<SagiaInvestorNotificationDTO> notifications;

    @Mock
    private SagiaInvestorNotificationDTO notification;

    @Mock
    private InvestorNotificationCount investorNotificationCount;

    @Spy
    @InjectMocks
    private HttpServletResponse responseMock;

    @Mock
    private ServletOutputStream outputStream;

    /**
     * Default constructor, initialize spied mocks.
     */
    public InvestorNotificationControllerTest() {
        investorNotificationController = new InvestorNotificationController();
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
    public void readInvestorNotificationsTest(){
        try {
            List<SagiaInvestorNotificationDTO> notificationsHelper = new ArrayList<>();
            notificationsHelper.add(notification);
            given(investorNotificationsFacade.getAllNotifications()).willReturn(notificationsHelper);
            String readInvestorNotifications = investorNotificationController.readInvestorNotifications(model);

            verify(model).addAttribute("notifications", notificationsHelper);
            Assert.assertNull(readInvestorNotifications);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void readDashboardNotificationsTest(){
        try {
            given(investorNotificationsFacade.getNotificationBy("transactionId")).willReturn(notification);
            String readDashboardNotificationData = investorNotificationController.
                    readDashboardNotificationData("transactionId",model);

            verify(model).addAttribute("notification", notification);
            Assert.assertNull(readDashboardNotificationData);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void readDashboardNotificationsRuntimeExceptionTest(){
        try {
            given(investorNotificationsFacade.getNotificationBy("transactionId")).willThrow(new RuntimeException("Testing runtime exception.."));
            investorNotificationController.readDashboardNotificationData("transactionId",model);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void updateNotificationTest(){
        try{
            investorNotificationController.updateNotification(notification);
            investorNotificationController.updateNotification("transactionId",notification);
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getInvestorNotificationAsJsonTest(){
        try{
            given(investorNotificationsFacade.getAllNotifications()).willReturn(notifications);
            Assert.assertEquals(notifications, investorNotificationController.getInvestorNotificationAsJson().getBody());
            Assert.assertEquals(HttpStatus.OK, investorNotificationController.getInvestorNotificationAsJson().getStatusCode());
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getSinglenvestorNotificationAsJsonTest(){
        try{
            given(investorNotificationsFacade.getNotificationBy("transactionId")).willReturn(notification);
            Assert.assertEquals(notification, investorNotificationController.getSingleInvestorNotificationAsJson("transactionId").getBody());
            Assert.assertEquals(HttpStatus.OK, investorNotificationController.getSingleInvestorNotificationAsJson("transactionId").getStatusCode());
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getNotificationsCountTest(){
        try{
            given(investorNotificationsFacade.getNotificationCount()).willReturn(investorNotificationCount);
            Assert.assertEquals(investorNotificationCount, investorNotificationController.getNotificationsCount().getBody());
            Assert.assertEquals(HttpStatus.OK, investorNotificationController.getNotificationsCount().getStatusCode());
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void printLicenseWarningLetterNullPdfTest(){
        given(sagiaLicensePrintingFacade.getWarningLetter("transactionId")).willReturn(null);
        investorNotificationController.printLicenseWarningLetter(response,"transactionId");
        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND,response.getStatus());
    }

    @Test
    public void printLicenseWarningLetterTest(){
        try{
            byte[] pdf = new byte[]{1};
            given(sagiaLicensePrintingFacade.getWarningLetter("transactionId")).willReturn(pdf);
            given(responseMock.getOutputStream()).willReturn(outputStream);
            investorNotificationController.printLicenseWarningLetter(responseMock,"transactionId");
            verify(responseMock).setHeader("Content-Disposition", "inline;filename=myLicense.pdf");
            verify(responseMock).setContentType("application/pdf");
            verify(responseMock).setDateHeader("Expires", -1);
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void printLicenseWarningLetterIoExceptionTest(){
        try{
            byte[] pdf = new byte[]{1};
            given(sagiaLicensePrintingFacade.getWarningLetter("transactionId")).willReturn(pdf);
            given(responseMock.getOutputStream()).willReturn(outputStream);
            doThrow(new IOException("Testing SC_INTERNAL_SERVER_ERROR")).doNothing().when(outputStream).write(pdf);
            investorNotificationController.printLicenseWarningLetter(responseMock,"transactionId");
            Assert.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, responseMock.getStatus());
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }


    @Test
    public void printWrittenNotificationNullPdfTest(){
        given(sagiaLicensePrintingFacade.getNotificationNotes()).willReturn(null);
        investorNotificationController.printWrittenNotification(response);
        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND,response.getStatus());
    }

    @Test
    public void printWrittenNotificationTest(){
        try{
            byte[] pdf = new byte[]{1};
            given(sagiaLicensePrintingFacade.getNotificationNotes()).willReturn(pdf);
            given(responseMock.getOutputStream()).willReturn(outputStream);
            investorNotificationController.printWrittenNotification(responseMock);
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
            given(sagiaLicensePrintingFacade.getNotificationNotes()).willReturn(pdf);
            given(responseMock.getOutputStream()).willReturn(outputStream);
            doThrow(new IOException("Testing SC_INTERNAL_SERVER_ERROR")).doNothing().when(outputStream).write(pdf);
            investorNotificationController.printWrittenNotification(responseMock);
            Assert.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, responseMock.getStatus());
        }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }
}
