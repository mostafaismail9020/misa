package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.core.sagia.services.SagiaDashboardSectionsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for InvestorDashboardEditController
 */
public class InvestorDashboardEditControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    private final static List<String> SAGIA_DASHBOARD_EDIT_MYLICENSE_TABS =
            Arrays.asList("GeneralDetails", "Shareholders", "ContactPerson", "GeneralManager");

    @Spy
    @InjectMocks
    private InvestorDashboardEditController investorDashboardEditController;

    @Mock
    private SagiaDashboardSectionsService sagiaDashboardSectionsService;

    @Mock
    Map<String, ArrayList<String>> userDashboardSections;

    @Mock
    private MultipartFile multipartFile;

    /**
     * Default constructor, initialize spied mocks.
     */
    public InvestorDashboardEditControllerTest() {
        investorDashboardEditController = new InvestorDashboardEditController();
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
    public void getDashboardTest(){
        try {
            given(sagiaDashboardSectionsService.getUserDashboardSections()).willReturn(userDashboardSections);
            given(userDashboardSections.isEmpty()).willReturn(false);
            String dashBoard =investorDashboardEditController.getDashboard(model);
            verify(model).addAttribute("dashboardSections", sagiaDashboardSectionsService.getUserDashboardSections());
            verify(model).addAttribute("tabs", SAGIA_DASHBOARD_EDIT_MYLICENSE_TABS);
            Assert.assertNull(dashBoard);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getDashboardRuntimeExceptionTest(){
        try {
            given(sagiaDashboardSectionsService.getUserDashboardSections()).willThrow(new RuntimeException("Testing runtime exception.."));
            investorDashboardEditController.getDashboard(model);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void handleFileUploadTest(){
        try {
            Assert.assertEquals("redirect:/dashboard-edit", investorDashboardEditController.handleFileUpload(multipartFile));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }
}
