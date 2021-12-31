package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.appintments.NationalInvestorAppointment;
import com.sap.ibso.eservices.facades.sagia.SagiaAppointmentFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaNationalInvestorFacade;
import com.sap.ibso.eservices.facades.user.data.SagiaRegisterData;
import com.sap.ibso.eservices.facades.user.exception.DuplicatePhoneNumberException;
import com.sap.ibso.eservices.storefront.forms.NationalInvestorForm;
import com.sap.ibso.eservices.storefront.forms.SagiaRegisterForm;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ConsentForm;
import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.acceleratorstorefrontcommons.strategy.CustomerConsentDataStrategy;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.consent.ConsentFacade;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.site.BaseSiteService;
import org.apache.commons.configuration.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

/**
 * Unit Test Class for SagiaRegisterPageController
 */
@UnitTest

public class SagiaRegisterPageControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Mock
    Validator validator;

    @Mock
    SagiaRegisterForm form;

    @Mock
    CustomerFacade customerFacade;

    @Mock
    BindingResult bindingResult;

    @Mock
    AutoLoginStrategy autoLoginStrategy;

    @Mock
    SagiaRegisterForm sagiaRegisterForm;

    @Mock
    ConsentForm consentForm;

    @Mock
    ConsentFacade consentFacade;

    @Mock
    CustomerConsentDataStrategy customerConsentDataStrategy;

    @Mock
    HttpSessionRequestCache httpSessionRequestCache;

    @Mock
    SavedRequest savedRequest;

    @Mock
    ConfigurationService configurationService;

    @Mock
    Configuration configuration;

    @Mock
    BaseSiteModel baseSiteModel;

    @Mock
    BaseSiteService baseSiteService;

    @Mock
    ListItem listItemServiceType;

    @Mock
    ListItem listItemService;

    @Mock
    NIPLeagalStatusSet nipLeagalStatusSet;

    @Mock
    NIPCountrySet nipCountrySetCountry;

    @Mock
    NIPCountrySet nipCCCCountrySet;

    @Mock
    NIPRegionSet nipRegionSet;

    @Mock
    NipCitySet nipCitySet;

    @Mock
    NipISICSectionSet nipISICSectionSet;

    @Mock
    SagiaAppointmentFacade sagiaAppointmentFacade;

    @Mock
    SagiaNationalInvestorFacade sagiaNationalInvestorFacade;

    @Mock
    NationalInvestorForm nationalInvestorForm;

    @Mock
    NationalInvestorHeaderSet nationalInvestorHeaderSet;

    @Mock
    MultipartFile multipartFile;

    @Mock
    NationalInvestorAppointment nationalInvestorAppointment;

    @Mock
    Validator nationalInvestorValidator;

    @Spy
    @InjectMocks
    private RedirectAttributes redirectAttributes;


    @Spy
    @InjectMocks
    private SagiaRegisterPageController sagiaRegisterPageController;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaRegisterPageControllerTest() {
        sagiaRegisterPageController = new SagiaRegisterPageController();
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
    public void doRegisterTest() {

        doNothing().when(validator).validate(form, bindingResult);
        given(bindingResult.hasErrors()).willReturn(false);
        given(form.getEmail()).willReturn("email");
        given(form.getPwd()).willReturn("pass");
        doNothing().when(autoLoginStrategy).login("email", "pass", request, response);
        given(form.getConsentForm()).willReturn(consentForm);
        given(consentForm.getConsentGiven()).willReturn(true);
        given(consentForm.getConsentTemplateId()).willReturn("templateId");
        given(consentForm.getConsentTemplateVersion()).willReturn(1);
        doNothing().when(consentFacade).giveConsent("templateId", Integer.valueOf(1));
        doNothing().when(customerConsentDataStrategy).populateCustomerConsentDataInSession();
        given(httpSessionRequestCache.getRequest(request, response)).willReturn(savedRequest);
        given(savedRequest.getRedirectUrl()).willReturn("redirectUrl");

        try {
            doNothing().when(customerFacade).register(Matchers.any(SagiaRegisterData.class));
        } catch (DuplicateUidException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

        try {
            assertEquals("redirect:redirectUrl", sagiaRegisterPageController.doRegister("referer", form, bindingResult, model, request, response, redirectAttributes));
        } catch (CMSItemNotFoundException e) {

        }
    }


    @Test
    public void doRegisterWithDuplicateUidExceptionTest() {

        doNothing().when(validator).validate(form, bindingResult);
        given(bindingResult.hasErrors()).willReturn(false);
        given(form.getEmail()).willReturn("email");
        given(form.getPwd()).willReturn("pass");
        doNothing().when(autoLoginStrategy).login("email", "pass", request, response);
        given(form.getConsentForm()).willReturn(consentForm);
        given(consentForm.getConsentGiven()).willReturn(true);
        given(consentForm.getConsentTemplateId()).willReturn("templateId");
        given(consentForm.getConsentTemplateVersion()).willReturn(1);
        doNothing().when(consentFacade).giveConsent("templateId", Integer.valueOf(1));
        doNothing().when(customerConsentDataStrategy).populateCustomerConsentDataInSession();
        given(httpSessionRequestCache.getRequest(request, response)).willReturn(savedRequest);
        given(savedRequest.getRedirectUrl()).willReturn("redirectUrl");
        given(configurationService.getConfiguration()).willReturn(configuration);
        given(baseSiteService.getCurrentBaseSite()).willReturn(baseSiteModel);
        given(baseSiteModel.getUid()).willReturn("id");

        try {
            doThrow(new DuplicateUidException("Testing DuplicateUidException...")).when(customerFacade).register(Matchers.any(SagiaRegisterData.class));
        } catch (DuplicateUidException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

        try {
            assertEquals("pages/account/accountLoginPage", sagiaRegisterPageController.doRegister("referer", form, bindingResult, model, request, response, redirectAttributes));
        } catch (CMSItemNotFoundException e) {

        }
    }


    @Test
    public void doRegisterWithDuplicatePhoneNumberException() {

        doNothing().when(validator).validate(form, bindingResult);
        given(bindingResult.hasErrors()).willReturn(false);
        given(form.getEmail()).willReturn("email");
        given(form.getPwd()).willReturn("pass");
        doNothing().when(autoLoginStrategy).login("email", "pass", request, response);
        given(form.getConsentForm()).willReturn(consentForm);
        given(consentForm.getConsentGiven()).willReturn(true);
        given(consentForm.getConsentTemplateId()).willReturn("templateId");
        given(consentForm.getConsentTemplateVersion()).willReturn(1);
        doNothing().when(consentFacade).giveConsent("templateId", Integer.valueOf(1));
        doNothing().when(customerConsentDataStrategy).populateCustomerConsentDataInSession();
        given(httpSessionRequestCache.getRequest(request, response)).willReturn(savedRequest);
        given(savedRequest.getRedirectUrl()).willReturn("redirectUrl");
        given(configurationService.getConfiguration()).willReturn(configuration);
        given(baseSiteService.getCurrentBaseSite()).willReturn(baseSiteModel);
        given(baseSiteModel.getUid()).willReturn("id");

        try {
            doThrow(new DuplicatePhoneNumberException(new Exception())).when(customerFacade).register(Matchers.any(SagiaRegisterData.class));
        } catch (DuplicatePhoneNumberException | DuplicateUidException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

        try {
            assertEquals("pages/account/accountLoginPage", sagiaRegisterPageController.doRegister(form, bindingResult, model, request, response, redirectAttributes));

        } catch (CMSItemNotFoundException e) {

        }

    }

    @Test
    public void getNationalInvestorDataTest() {

        Collection<ListItem> formOptions = new ArrayList<>();
        Collection<ListItem> branches = new ArrayList<>();
        Collection<ListItem> departments = new ArrayList<>();
        Collection<ListItem> serviceTypes = new ArrayList<>();
        Collection<ListItem> services = new ArrayList<>();
        Collection<NIPLeagalStatusSet> nipLegalStatuses = new ArrayList<>();
        Collection<NIPCountrySet> nipGCCCountryCollection = new ArrayList<>();
        Collection<NIPRegionSet> nipRegionCollection = new ArrayList<>();
        Collection<NipCitySet> nipCityCollection = new ArrayList<>();
        Collection<NipISICSectionSet> nipISICSections = new ArrayList<>();

        given(listItemServiceType.getFieldKey()).willReturn("SAGIASER");
        given(listItemService.getFieldSubType()).willReturn("SAGIASER");
        given(listItemService.getFieldKey()).willReturn("999999_47");
        given(nipCountrySetCountry.getNationalityCategory()).willReturn("SAUDI");


        nipGCCCountryCollection.add(nipCountrySetCountry);


        given(sagiaAppointmentFacade.getAppointmentOptions()).willReturn(formOptions);
        given(sagiaAppointmentFacade.getDepartments(formOptions)).willReturn(departments);
        given(sagiaAppointmentFacade.getBranches(formOptions)).willReturn(branches);
        given(sagiaAppointmentFacade.getServiceTypes(formOptions)).willReturn(serviceTypes);
        given(sagiaAppointmentFacade.getService(formOptions)).willReturn(services);
        given(sagiaNationalInvestorFacade.getLegalStatuses()).willReturn(nipLegalStatuses);
        // given(sagiaNationalInvestorFacade.getCountries()).willReturn(nipCountryCollection);
        given(sagiaNationalInvestorFacade.getRegions("SA")).willReturn(nipRegionCollection);
        given(sagiaNationalInvestorFacade.getCities()).willReturn(nipCityCollection);
        given(sagiaNationalInvestorFacade.getISICSections()).willReturn(nipISICSections);


        assertEquals("{\"branches\":[],\"nipLegalStatuses\":[],\"nipCountries\":[],\"nipGCCCountries\":[],\"nipRegions\":[],\"nipCities\":[],\"nipISICSections\":[],\"crmCountriesJson\":\"[]\",\"departments\":[],\"serviceTypes\":[],\"services\":[],\"nationalInvestorAppointment\":{}}",
                sagiaRegisterPageController.getNationalInvestorData());


    }


    @Test
    public void populateNationalInvestorTest() {
        List<MultipartFile> files = new ArrayList<>();
        files.add(multipartFile);
        given(multipartFile.getSize()).willReturn(Long.valueOf(10));
        given(nationalInvestorForm.getFiles()).willReturn(files);
        given(multipartFile.getOriginalFilename()).willReturn("name");
        given(multipartFile.getContentType()).willReturn("type");
        byte[] dim = {'1'};
        try {
            given(multipartFile.getBytes()).willReturn(dim);
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        //assertEquals("name", sagiaRegisterPageController.populateNationalInvestor(nationalInvestorForm).getAttachmentsSet().get(0).getFilename());
    }

    @Test
    public void registerNationalInvestorTest() {

        given(nationalInvestorAppointment.getDateString()).willReturn("01 Jul, 2010");
        given(nationalInvestorAppointment.getTimeFromString()).willReturn("10:10");
        doNothing().when(sagiaNationalInvestorFacade).createAppointment(nationalInvestorAppointment);

        assertEquals("{\"success\":\"true\"}", sagiaRegisterPageController.registerNationalInvestor(nationalInvestorAppointment));

    }

    @Test
    public void getNationalInvestorHeaderSetTest() {

        assertNotNull(sagiaRegisterPageController.getNationalInvestorHeaderSet("5"));
    }


    @Test
    public void getLegalStatusesTest() {

        assertNotNull(sagiaRegisterPageController.getLegalStatuses());
    }

    @Test
    public void getNationalitiesTest() {

        assertNotNull(sagiaRegisterPageController.getNationalities("category"));
    }

    @Test
    public void getRegionsTest() {

        assertNotNull(sagiaRegisterPageController.getRegions("country"));
    }

    @Test
    public void getCitiesTest() {
        assertNotNull(sagiaRegisterPageController.getCities("region"));
    }

    @Test
    public void getIsicSectionsTest() {

        assertNotNull(sagiaRegisterPageController.getIsicSections());
    }

    @Test
    public void getIsicDivisionsTest() {

        assertNotNull(sagiaRegisterPageController.getIsicDivisions("section"));
    }


    @Test
    public void registerNationalInvestorCrNumberTest() {

        given(bindingResult.hasErrors()).willReturn(false);
        List<MultipartFile> files = new ArrayList<>();
        files.add(multipartFile);
        given(multipartFile.getSize()).willReturn(Long.valueOf(10));
        given(nationalInvestorForm.getFiles()).willReturn(files);
        given(multipartFile.getOriginalFilename()).willReturn("name");
        given(multipartFile.getContentType()).willReturn("type");
        byte[] dim = {'1'};
        try {
            given(multipartFile.getBytes()).willReturn(dim);
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());

            try {
                doNothing().when(nationalInvestorValidator).validate(nationalInvestorForm, bindingResult);
                assertEquals("", sagiaRegisterPageController.registerNationalInvestorCrNumber(nationalInvestorForm, bindingResult).toString());
            } catch (Exception ex) {
                Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
            }


        }

    }

}

