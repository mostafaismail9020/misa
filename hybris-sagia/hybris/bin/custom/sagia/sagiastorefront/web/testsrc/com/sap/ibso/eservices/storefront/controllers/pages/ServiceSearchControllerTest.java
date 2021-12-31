package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.SagiaServiceData;
import com.sap.ibso.eservices.facades.data.SagiaServiceTabData;
import com.sap.ibso.eservices.facades.sagia.SagiaSearchFacade;
import de.hybris.bootstrap.annotations.UnitTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.HashMap;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for ServiceSearchController
 */
@UnitTest
public class ServiceSearchControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private ServiceSearchController serviceSearchController;

    @Mock
    private SagiaSearchFacade sagiaSearchFacade;

    @Mock
    private HashMap<String, List<SagiaServiceData>> serviceResultList;

    @Mock
    private List<SagiaServiceTabData> tabsList;


    /**
     * Default constructor, initialize spied mocks.
     */
    public ServiceSearchControllerTest() {
        serviceSearchController = new ServiceSearchController();

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
    public void getServiceSearchCmsPageTest(){
        try {
            given(sagiaSearchFacade.getAllServices()).willReturn(serviceResultList);
            String page = serviceSearchController.getServiceSearchCmsPage(model);
            verify(model).addAttribute("SagiaServices", serviceResultList);
            Assert.assertNull(page);
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void searchTextTest(){
        try {
            given(sagiaSearchFacade.getAllServices()).willReturn(serviceResultList);
            given(serviceResultList.isEmpty()).willReturn(true);
            Assert.assertEquals("",serviceSearchController.searchText("searchText"));
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getServiceDetailsTest(){
        try {
            given(sagiaSearchFacade.getServiceDetails("code")).willReturn(tabsList);
            given(tabsList.isEmpty()).willReturn(true);
            Assert.assertEquals("",serviceSearchController.getServiceDetails("code"));
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }
}
