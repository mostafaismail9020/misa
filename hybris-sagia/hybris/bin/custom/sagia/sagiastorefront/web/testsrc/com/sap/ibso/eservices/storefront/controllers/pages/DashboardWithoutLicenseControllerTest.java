package com.sap.ibso.eservices.storefront.controllers.pages;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for DashboardWithoutLicenseController
 */
@UnitTest
public class DashboardWithoutLicenseControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";
    private final static String SAGIA_DASHBOARD_CMS_PAGE = "dashboard-without-license";

    @Spy
    @InjectMocks
    private DashboardWithoutLicenseController dashboardWithoutLicenseController;


    /**
     * Default constructor, initialize spied mocks.
     */
    public DashboardWithoutLicenseControllerTest() {
        dashboardWithoutLicenseController = new DashboardWithoutLicenseController();
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
    public void getDashboardErrorTest(){
        try{
            given(dashboardWithoutLicenseController.getDashboard(model)).willThrow(new RuntimeException("Exception successfully thrown."));
        }catch(RuntimeException ex){

        }catch(Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getDashboardTest(){
        try{
            Assert.assertEquals(null,dashboardWithoutLicenseController.getDashboard(model));
        }catch(Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

}
