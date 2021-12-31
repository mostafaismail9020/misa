package com.sap.ibso.eservices.storefront.controllers.pages;


import com.sap.ibso.eservices.facades.sagia.SagiaGovtCategoryFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaGovtServiceFacade;
import com.sap.ibso.eservices.sagiaservices.data.SagiaCRMGovtServiceData;
import com.sap.ibso.eservices.storefront.forms.CreateGovtServiceForm;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.session.SessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SagiaServicesController
 */
@UnitTest
public class SagiaServicesControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private SagiaServicesController sagiaServicesController;

    @Mock
    private SagiaGovtCategoryFacade sagiaGovtCategoryFacade;

    @Mock
    private SagiaGovtServiceFacade sagiaGovtServiceFacade;

    @Mock
    private Validator createGovtServiceValidator;

    @Mock
    private CreateGovtServiceForm createGovtService;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private BindingResult result;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private SessionService sessionService;

    @Mock
    private String categoryUrl;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaServicesControllerTest() {

        sagiaServicesController = new SagiaServicesController();
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
    public void serviceDetailsTest(){
        try{
            Collection<SagiaCRMGovtServiceData> serviceList = new ArrayList<>();
            given(sagiaGovtCategoryFacade.getCRMServicesByCategory("serviceUrl")).willReturn(serviceList);
            Assert.assertNull(sagiaServicesController.serviceDetails("categoryUrl","serviceUrl",null, model, request));
            verify(model).addAttribute("serviceNameDecoded", "serviceName");
            verify(model).addAttribute("serviceName", URLEncoder.encode("serviceName", "UTF-8"));
            verify(model).addAttribute("categoryUrl", "categoryUrl");
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void createGovtServicesWithModelTest(){
        given(sessionService.getAttribute("createGovtService")).willReturn(createGovtService);


        try{

            Assert.assertNull(sagiaServicesController.createGovtServices(model,"srID","serviceName",
                    "categoryUrl","serviceUrl",createGovtService));


            verify(model).addAttribute("srID", "srID");
            verify(model).addAttribute("serviceName", URLDecoder.decode("serviceName", "UTF-8"));
            verify(model).addAttribute("categoryUrl", "categoryUrl");
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void createGovtServiceWithRequestTest(){

        given(sessionService.getAttribute("createGovtService")).willReturn(createGovtService);
        try{
            List<MultipartFile> multipartFiles = new ArrayList<>();
            multipartFiles.add(multipartFile);
            List<String> dockeyIdList = new ArrayList<>();
            dockeyIdList.add("mockItem");

            given(createGovtService.getFiles()).willReturn(multipartFiles);
            given(multipartFile.getSize()).willReturn(1L);
            given(createGovtService.getDockeyID()).willReturn(dockeyIdList);
            given(result.hasErrors()).willReturn(false);
            given(createGovtService.getServiceName()).willReturn("serviceName");

            byte[] dim = {'1'};
            try {
                given(multipartFile.getBytes()).willReturn( dim );
            } catch (IOException e) {
                Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
            }

            doNothing().when(createGovtServiceValidator).validate(createGovtService,result);


            Assert.assertEquals("redirect:/services/government/" + createGovtService.getCategoryUrl() + "/" + createGovtService.getServiceType(),sagiaServicesController.createGovtService(request,createGovtService,result,redirectAttributes));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getRealEstateByIdRuntimeExceptionTest(){
        try{
            given(sagiaGovtCategoryFacade.getGovtServiceById("serviceId")).willThrow(new RuntimeException("Testing Runtime Exception..."));
            sagiaServicesController.getGovtServiceById("serviceId");
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void showServicesTest(){
        try{
            Assert.assertNull(sagiaServicesController.showServices(model));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

    }





}
