package com.sap.ibso.eservices.storefront.controllers.pages;

import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;

/**
 * Unit Test Class for SagiaDateProviderController
 */
@UnitTest
public class SagiaDateProviderControllerTest {


    @Spy
    @InjectMocks
    private SagiaDateProviderController sagiaDateProviderController;


    /**
     * Default constructor, initialize spied mocks.
     */
    /**
     * Initialize mocks
     */
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        }

    @Test
    public void getFormattedDateTest(){
        assertEquals(null,sagiaDateProviderController.getFormattedDate("millis"));
    }

}
