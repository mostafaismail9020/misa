package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.license.amendment.LicenseAmendment;
import com.sap.ibso.eservices.facades.data.license.amendment.Shareholder;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseAmendmentFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for LicenseAmendmentController
 */
public class LicenseAmendmentControllerTest extends AbstractCmsContentPageSetupUnitTestHelper{
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private LicenseAmendmentController licenseAmendmentController;

    @Mock
    private SagiaLicenseAmendmentFacade sagiaLicenseAmendmentFacade;

//    @Mock
//    private List<LicenseAmendmentType> amendmentTypes;

    @Mock
    private LicenseAmendment licenseAmendment;

    @Mock
    private Shareholder shareholder;

    /**
     * Default constructor, initialize spied mocks.
     */
    public LicenseAmendmentControllerTest() {
        licenseAmendmentController = new LicenseAmendmentController();
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
    public void amendLicenseTest(){
    try {

     assertNull(licenseAmendmentController.amendLicense(model, null, null));
    }catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void amendLicenseRuntimeExceptionTest(){
        try {
           given(sagiaLicenseAmendmentFacade.getShareholder("613391")).willThrow(new RuntimeException("Testing Runtime Exception.."));
            licenseAmendmentController.amendLicense(model, null, null);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }


    @Test
  public void getAmendmentTypeTest() {

        try {
            given(sagiaLicenseAmendmentFacade.getLicenseAmendmentTypes(licenseAmendment)).willReturn(licenseAmendment);
            Assert.assertEquals(licenseAmendment, licenseAmendmentController.getAmendmentTypes(licenseAmendment));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void saveLicenseAmendmentTest(){
        assertNull(licenseAmendmentController.saveLicenseAmendment(licenseAmendment));
    }

    @Test
    public void getShareholderTest(){
        given(sagiaLicenseAmendmentFacade.getShareholder("shareholderId")).willReturn(shareholder);
        Assert.assertEquals(shareholder,licenseAmendmentController.getShareholder("shareholderId"));
    }
}
