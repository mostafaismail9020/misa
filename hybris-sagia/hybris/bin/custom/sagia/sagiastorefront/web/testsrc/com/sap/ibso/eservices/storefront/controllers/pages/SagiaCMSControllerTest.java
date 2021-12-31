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
 * Unit Test Class for SagiaCMSController
 */
@UnitTest
public class SagiaCMSControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private SagiaCMSController sagiaCMSController;


    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaCMSControllerTest() {
        sagiaCMSController = new SagiaCMSController();
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
    public void getPageTest(){
        try {
            assertEquals(null, sagiaCMSController.getPage("pageId",model));
        } catch (CMSItemNotFoundException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

}
