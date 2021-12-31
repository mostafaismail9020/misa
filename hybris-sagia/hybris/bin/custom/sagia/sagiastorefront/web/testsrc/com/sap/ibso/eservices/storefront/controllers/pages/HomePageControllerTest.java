package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.mockito.BDDMockito.given;


/**
 * Unit Test Class for HomePageController
 */
public class HomePageControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {

    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Spy
    @InjectMocks
    private RedirectAttributes redirectAttributes;


    @Spy
    @InjectMocks
    private HomePageController homePageController;


    @Mock
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

    @Mock
    private CMSSiteService cmsSiteService;

    /**
     * Default constructor, initialize spied mocks.
     */
    public HomePageControllerTest() {
        homePageController = new HomePageController();
        redirectAttributes = new RedirectAttributesModelMap();
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
    public void homeLogoutAndCloseAccount(){
      /*  try {
            Assert.assertEquals("redirect:/",homePageController.home(true,true,model,redirectAttributes));
        } catch (CMSItemNotFoundException e) {
            Assert.fail(e.getMessage());
        }*/
    }

    @Test
    public void homeNotLogoutAndHasLicense(){
   /*     try {
            given(sagiaDashboardWithoutLicenseFacade.hasLicense()).willReturn(true);
            Assert.assertEquals("redirect:/dashboard",homePageController.home(true,false,model,redirectAttributes));
        } catch (CMSItemNotFoundException e) {
            Assert.fail(e.getMessage());
        }*/
    }


    @Test
    public void homeNotLogoutAndHasNoLicense(){
       /* try {
            given(sagiaDashboardWithoutLicenseFacade.hasLicense()).willReturn(false);
            Assert.assertEquals("redirect:/dashboard-without-license",homePageController.home(true,false,model,redirectAttributes));
        } catch (CMSItemNotFoundException e) {
            Assert.fail(e.getMessage());
        }*/
    }
}
