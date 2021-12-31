package com.sap.ibso.eservices.storefront.controllers.pages;


import com.sap.ibso.eservices.core.sagia.services.SagiaDashboardSectionsService;
import com.sap.ibso.eservices.facades.data.AccountManagerData;
import com.sap.ibso.eservices.facades.data.LicenseData;
import com.sap.ibso.eservices.facades.data.SagiaMediaData;
import com.sap.ibso.eservices.facades.data.TicketData;
import com.sap.ibso.eservices.facades.data.payment.PaymentData;
import com.sap.ibso.eservices.facades.employment.data.EmploymentData;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for InvestorDashboardController
 */
public class InvestorDashboardControllerTest  extends AbstractCmsContentPageSetupUnitTestHelper{
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private InvestorDashboardController investorDashboardController;

    @Mock
    private MultipartFile file;

    @Mock
    private SagiaLicenseFacade licenseFacade;

    @Mock
    private SagiaEmploymentFacade employmentFacade;

    @Mock
    private SagiaServiceRequestFacade serviceRequestFacade;

    @Mock
    private SagiaComplaintFacade sagiaComplaintFacade;

    @Mock
    private SagiaAccountManagerFacade sagiaAccountManagerFacade;

    @Mock
    private PaymentFacade sagiaPaymentFacade;

    @Mock
    private ZUI5SagiaFacade zui5SagiaFacade;

    @Mock
    private SagiaMediaFacade sagiaMediaFacade;

    @Mock
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Mock
    private SagiaDashboardSectionsService sagiaDashboardSectionsService;

    @Mock
    private HomeHDRData homeHDR;

    @Mock
    private LicenseData licenseData;

    @Mock
    private EmploymentData employmentData;

    @Mock
    private AccountManagerData accountManagerData;

    @Mock
    private SagiaMediaData pageMedia;

    @Mock
    private MediaData dashboardMedia;

    @Mock
    private Map<String, ArrayList<String>> userDashboardSections;

    @Mock
    private CustomerData customerData;

    /**
     * Default constructor, initialize spied mocks.
     */
    public InvestorDashboardControllerTest() {
        investorDashboardController = new InvestorDashboardController();
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
    public void handleFileUploadTest() {
        try {
            Assert.assertEquals("redirect:/dashboard",investorDashboardController.handleFileUpload(file));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getDashboardTest(){
        try {
            MediaData mediaData1 = new MediaData();
            mediaData1.setCode("dashboardinfo");
            List<MediaData> mediaDataList = new ArrayList<>();
            mediaDataList.add(mediaData1);

            List<PaymentData> paymentDataList = new ArrayList<>();
            List<ServiceRequestData> serviceRequestsData = new ArrayList<>();
             List<TicketData> tickets = new ArrayList<>();

            given(zui5SagiaFacade.getHomeHDRData()).willReturn(homeHDR);
            given(sagiaPaymentFacade.getPayments()).willReturn(paymentDataList);
            given(licenseFacade.getLicense(homeHDR)).willReturn(licenseData);
            given(employmentFacade.getEmploymentData(homeHDR)).willReturn(employmentData);
            given(serviceRequestFacade.getServiceRequestsData()).willReturn(serviceRequestsData);
            given(sagiaComplaintFacade.getTickets()).willReturn(tickets);
            given(sagiaAccountManagerFacade.getAccountManagerData()).willReturn(accountManagerData);
            given(sagiaMediaFacade.getSagiaMediaForPageName("dashboard")).willReturn(pageMedia);
            given(pageMedia.getAttachments()).willReturn(mediaDataList);
            given(sagiaDashboardSectionsService.getUserDashboardSections()).willReturn(userDashboardSections);
            given(sagiaCustomerFacade.getCurrentCustomer()).willReturn(customerData);
            given(customerData.getDashboardMedia()).willReturn(dashboardMedia);

            Assert.assertNull(investorDashboardController.getDashboard(model));
            verify(model).addAttribute("dashboardinfo", mediaData1.getUrl());
            verify(model).addAttribute("dashboardBanner", dashboardMedia.getUrl());
        } catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void handleFileUploadExceptionTest(){
        try {
            doThrow(new RuntimeException("Testing Runtime Exception")).when(sagiaCustomerFacade).updateDashboardPicture(file);
            investorDashboardController.handleFileUpload(file);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }
}
