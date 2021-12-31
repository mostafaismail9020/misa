package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.AbstractCmsContentPageSetupUnitTestHelper;
import com.sap.ibso.eservices.storefront.controllers.pages.SagiaPaginationController;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.session.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;

/**
 * Unit Test Class for SurveyHDRController
 */
@UnitTest
public class SagiaPaginationControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";



    @Mock
    SessionService sessionService;

    @Mock
    SagiaPaginationFacade sagiaPaginationFacade;

    @Mock
    SagiaConfigurationFacade sagiaConfigurationFacade;

    @Mock
    SagiaServiceRequestFacade sagiaServiceRequestFacade;

    @Mock
    PaymentFacade sagiaPaymentFacade;

    @Mock
    SagiaAppointmentFacade sagiaAppointmentFacade;


    @Mock
    SagiaComplaintFacade sagiaComplaintFacade;




    @Spy
    @InjectMocks
    private SagiaPaginationController sagiaPaginationController;



    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaPaginationControllerTest() {

        sagiaPaginationController = new SagiaPaginationController();
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
    public void getServicePageTest(){
       given (sagiaConfigurationFacade.getItemsPerPage()).willReturn("10");
        assertNotNull(sagiaPaginationController.getServicePage("10"));
    }

    @Test
    public void getPaymentPageTest() {
        given(sagiaConfigurationFacade.getItemsPerPage()).willReturn("10");
        assertNotNull(sagiaPaginationController.getPaymentPage("10"));
    }

    @Test
    public void getTicketPageTest(){
        given(sagiaConfigurationFacade.getItemsPerPage()).willReturn("10");
        assertNotNull(sagiaPaginationController.getTicketPage("10"));
    }


    @Test
    public void getAppointmentPageTest(){
        given(sagiaConfigurationFacade.getItemsPerPage()).willReturn("10");
        assertNotNull(sagiaPaginationController.getAppointmentPage("10"));
    }

    @Test
    public void getSortedAppointmentList(){
        given(sagiaConfigurationFacade.getItemsPerPage()).willReturn("10");
        assertNotNull(sagiaPaginationController.getSortedAppointmentList("parameter", "10"));
    }

    @Test
    public void getSortedServiceList(){
        given(sagiaConfigurationFacade.getItemsPerPage()).willReturn("10");
        assertNotNull(sagiaPaginationController.getSortedServiceList("parameter", "10"));
    }

    @Test
    public void getSortedPaymentListTest(){
        given(sagiaConfigurationFacade.getItemsPerPage()).willReturn("10");
        assertNotNull(sagiaPaginationController.getSortedPaymentList("parameter", "10"));
    }

    @Test
    public void getSortedTicketListTest(){
        given(sagiaConfigurationFacade.getItemsPerPage()).willReturn("10");
        assertNotNull(sagiaPaginationController.getSortedTicketList("parameter", "10"));
    }



}
