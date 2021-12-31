package com.sap.ibso.eservices.storefront.controllers.pages;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ibso.eservices.facades.data.RealEstate;
import com.sap.ibso.eservices.facades.data.RegionCityData;
import com.sap.ibso.eservices.facades.sagia.RealEstateFacade;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaRealEstateService;
import com.sap.ibso.eservices.storefront.forms.SagiaRealEstateForm;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.session.SessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

/**
 * Unit Test Class for SagiaRealEstateController
 */
@UnitTest
public class SagiaRealEstateControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Spy
    @InjectMocks
    private SagiaRealEstateController sagiaRealEstateController;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private MessageSource messageSource;

    @Mock
    private RealEstateFacade realEstateFacade;

    //only here for the mock to work
    @Mock
    private SagiaRealEstateService sagiaRealEstateService;

    @Mock
    private Validator createRealEstateValidator;

    @Mock
    private SagiaRealEstateForm sagiaRealEstateForm;

    @Mock
    private I18NService i18nService;

    @Mock
    private BindingResult result;

    @Mock
    private RealEstate realEstate;

    @Mock
    private SessionService sessionService;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private Collection<RegionCityData> cityData;


    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaRealEstateControllerTest() {
        sagiaRealEstateController = new SagiaRealEstateController();
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
    public void showRealEstateTest(){
        Assert.assertNull(sagiaRealEstateController.showRealEstate(model,""));
    }

    @Test
    public void createRealEstateTest(){
        given(i18nService.getCurrentLocale()).willReturn(Locale.ENGLISH);
        Assert.assertNull(sagiaRealEstateController.createRealEstate(Optional.of("id"), model, sagiaRealEstateForm, redirectAttributes));
    }

    @Test
    public void saveRealEstateHasNoErrorsTest() throws IOException {
        List<MultipartFile> files = new ArrayList<>();
        files.add(multipartFile);
        given(sessionService.getAttribute("sagiaRealEstateForm")).willReturn(sagiaRealEstateForm);
        given(sagiaRealEstateForm.getFiles()).willReturn(files);
        given(multipartFile.getSize()).willReturn(Long.valueOf(0));
        doNothing().when(createRealEstateValidator).validate(sagiaRealEstateForm,result);
        Assert.assertEquals("redirect:/real-estate",sagiaRealEstateController.saveRealEstate(Optional.of("id"),sagiaRealEstateForm,
                model,result,redirectAttributes,request));
    }

    @Test
    public void saveRealEstateHasErrorsTest() throws IOException {
        Registry.activateMasterTenant();
        doNothing().when(createRealEstateValidator).validate(sagiaRealEstateForm,result);
        given(result.hasErrors()).willReturn(true);
        Assert.assertEquals("redirect:/real-estate/create",sagiaRealEstateController.saveRealEstate(Optional.of("id"),sagiaRealEstateForm,
                model,result,redirectAttributes,request));
    }

    //@Test
    public void getRealEstateByIdTest(){
        given(realEstateFacade.getRealEstateById("realEstateId")).willReturn(realEstate);
        try {
            Assert.assertEquals(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(realEstate),
                    sagiaRealEstateController.getRealEstateById("realEstateId"));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getAllSubcategoriesTest(){
        given(realEstateFacade.getCities("regionId")).willReturn(cityData);
        Assert.assertEquals(cityData,sagiaRealEstateController.getAllCities("regionId"));
    }
}

