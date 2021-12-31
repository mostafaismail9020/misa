package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;


/**
 * Unit Test Class for SagiaRealtimeController
 */
@UnitTest
public class SagiaRealtimeControllerTest  {


    @Mock
    SagiaConfigurationFacade sagiaConfigurationFacade;

    @Spy
    @InjectMocks
    private SagiaRealtimeController sagiaRealtimeController;


    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaRealtimeControllerTest() {
        sagiaRealtimeController = new SagiaRealtimeController();
    }


    /**
     * Initialize mocks
     */
    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getRealtimeConnectivityDetailsTest(){
        given(sagiaConfigurationFacade.getRealtimeEmailUs()).willReturn("emailUs");
        given(sagiaConfigurationFacade.getRealtimeCallInternational()).willReturn("callIntern");

        assertEquals("emailUs", sagiaRealtimeController.getRealtimeConnectivityDetails().get("realtimeEmailUs"));
        assertEquals("callIntern",sagiaRealtimeController.getRealtimeConnectivityDetails().get("realtimeCallInternational"));
    }

}
