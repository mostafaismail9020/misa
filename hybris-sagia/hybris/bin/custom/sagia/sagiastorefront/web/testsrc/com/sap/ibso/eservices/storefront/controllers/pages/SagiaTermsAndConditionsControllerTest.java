package com.sap.ibso.eservices.storefront.controllers.pages;


import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;

/**
 * Unit Test Class for SagiaTermsAndConditionsController
 */
@UnitTest
public class SagiaTermsAndConditionsControllerTest extends AbstractCmsContentPageSetupUnitTestHelper{

    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private SagiaTermsAndConditionsController sagiaTermsAndConditionsController;




    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaTermsAndConditionsControllerTest() {

        sagiaTermsAndConditionsController = new SagiaTermsAndConditionsController();
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
    public void getTermsAndConditionsTest(){
        try {
            assertEquals("fragments/checkout/termsAndConditionsPopup", sagiaTermsAndConditionsController.getTermsAndConditions(model));
        } catch (CMSItemNotFoundException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

}
