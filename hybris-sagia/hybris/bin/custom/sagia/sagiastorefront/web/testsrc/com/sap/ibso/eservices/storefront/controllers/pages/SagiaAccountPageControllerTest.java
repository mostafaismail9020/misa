package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import com.sap.ibso.eservices.storefront.forms.SagiaUpdatePwdForm;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SagiaAccountPageController
 */
@UnitTest
public class SagiaAccountPageControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Mock
    private CustomerData customerData;

    @Mock
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Mock
    private ProfilePersonalData profilePersonalData;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private SagiaAccountFacade sagiaAccountFacade;

    @Mock
    private SagiaSurveyFacade sagiaSurveyFacade;

    @Mock
    private ImageData imageData;

    @Mock
    private BasicCompanyData companyData;

    @Mock
    private BasicCompanyDictionariesData dictionariesData;

    @Mock
    private ContactData contactData;

    @Mock
    private SurveyData surveyData;

    @Mock
    private SagiaComplaintFacade sagiaComplaintFacade;

    @Mock
    private ComplaintFormData complaintFormData;

    @Mock
    private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

    @Mock
    private Breadcrumb breadcrumb;

    @Mock
    private SagiaUpdatePwdForm sagiaUpdatePwdForm;

    @Mock
    private InputStream inputStream;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private BasicCompanyDictionary basicCompanyDictionaryData;

    @Mock
    private TitleData titleData;

    @Mock
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Spy
    @InjectMocks
    private RedirectAttributes redirectAttributes;

    @Spy
    @InjectMocks
    private SagiaAccountPageController sagiaAccountPageController;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaAccountPageControllerTest() {
        sagiaAccountPageController = new SagiaAccountPageController();
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
    public void updatePersonalDataTest(){
        given(sagiaCustomerFacade.getCurrentCustomer()).willReturn(customerData);
        given(profilePersonalData.getTitle()).willReturn(titleData);

        try {
            doNothing().when(sagiaCustomerFacade).updateProfile(customerData);
            String redirectUrl = sagiaAccountPageController.updatePersonalData(profilePersonalData, bindingResult);
            assertEquals("redirect: /sagiastorefront/sagia/en/my-sagia/sagia-profile",  redirectUrl);
        }  catch (DuplicateUidException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void updatePasswordTest(){
        given(sagiaCustomerFacade.getCurrentCustomer()).willReturn(customerData);
        given(customerData.getCustomerId()).willReturn("customerId");
        given(customerData.getProfilePicture()).willReturn(imageData);
        List<Breadcrumb> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(breadcrumb);
        List<TicketData> listTicketData = new ArrayList<>();
        try {
            //given(sagiaAccountFacade.getProfileForProfileCode("customerId")).willReturn(profileData);
            given(sagiaAccountFacade.getProfileCompanyData()).willReturn(companyData);
            given(sagiaAccountFacade.getCompanyContacts()).willReturn(contactData);
            given(sagiaSurveyFacade.getZui5SurveyBy("","")).willReturn(surveyData);
            given(sagiaComplaintFacade.getTickets()).willReturn(listTicketData);
            given(sagiaComplaintFacade.getComplaintFormData()).willReturn(complaintFormData);
            given(accountBreadcrumbBuilder.getBreadcrumbs(anyString())).willReturn(breadcrumbs);

            given(sagiaCustomerFacade.getSagiaConfigurationFacade()).willReturn(sagiaConfigurationFacade);
            given(sagiaConfigurationFacade.getPasswordRegex()).willReturn("pass\\");
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

        try {
            String view = sagiaAccountPageController.getViewForPasswordUsernameEmail(model);
            assertEquals("null#passwordTab", view);
        } catch (CMSItemNotFoundException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        verify(model).addAttribute("profilePicture", imageData.getUrl());

        verify(model).addAttribute("tabs", Arrays.asList("Company", "personal", "Security", "Enquiry", "Feedback"));

        verify(model).addAttribute("complaintFormData", complaintFormData);
        verify(model).addAttribute("surveyData", surveyData);
        verify(model).addAttribute("breadcrumbs", breadcrumbs);
    }

    @Test
    public void updatePasswordWithParamsTest() {
        given(sagiaUpdatePwdForm.getPwd()).willReturn("pwd");
        given(sagiaUpdatePwdForm.getCheckPwd()).willReturn("pwd");

        given(bindingResult.hasErrors()).willReturn(false);

        doNothing().when(sagiaCustomerFacade).validate("pwd", bindingResult);
        doNothing().when(sagiaCustomerFacade).updatePassword("oldPwd", "pwd", bindingResult);

        assertEquals("redirect:/logout", sagiaAccountPageController.updatePassword(sagiaUpdatePwdForm, bindingResult));
    }

    @Test
    public void getComplaintAttachment(){
        given(sagiaAccountFacade.readAttachment("objectId", "documentId")).willReturn(inputStream);
        assertEquals(HttpStatus.OK, sagiaAccountPageController.getComplaintAttachment("objectId","documentId").getStatusCode());
        try {
            assertEquals(inputStream, sagiaAccountPageController.getComplaintAttachment("objectId","documentId").getBody().getInputStream());
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        assertEquals(MediaType.parseMediaType("application/pdf"), sagiaAccountPageController.getComplaintAttachment("objectId","documentId").getHeaders().getContentType());
    }

    @Test
    public void handleFileUploadTest(){
        try {
            doNothing().when(sagiaCustomerFacade).updateProfilePicture(multipartFile);
            assertEquals("redirect:/my-sagia/sagia-profile", sagiaAccountPageController.handleFileUpload(multipartFile));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void profileTest() {
        given(sagiaCustomerFacade.getCurrentCustomer()).willReturn(customerData);
        given(customerData.getCustomerId()).willReturn("customerId");
        given(customerData.getProfilePicture()).willReturn(imageData);
        List<Breadcrumb> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(breadcrumb);
        try {
            //given(sagiaAccountFacade.getProfileForProfileCode("customerId")).willReturn(profileData);
            given(sagiaAccountFacade.getProfileCompanyData()).willReturn(companyData);
            given(sagiaAccountFacade.getCompanyContacts()).willReturn(contactData);
            given(sagiaSurveyFacade.getZui5SurveyBy("","")).willReturn(surveyData);
            given(sagiaComplaintFacade.getComplaintFormData()).willReturn(complaintFormData);
            given(accountBreadcrumbBuilder.getBreadcrumbs(anyString())).willReturn(breadcrumbs);

            given(sagiaCustomerFacade.getSagiaConfigurationFacade()).willReturn(sagiaConfigurationFacade);
            given(sagiaConfigurationFacade.getPasswordRegex()).willReturn("pass\\");
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

        try {
            assertNull(sagiaAccountPageController.profile(model));
        } catch (CMSItemNotFoundException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        verify(model).addAttribute("profilePicture", imageData.getUrl());
        verify(model).addAttribute("tabs", Arrays.asList("Company", "personal", "Security", "Enquiry", "Feedback"));
        verify(model).addAttribute("complaintFormData", complaintFormData);
        verify(model).addAttribute("sagiaCompanyForm", companyData);
        verify(model).addAttribute("sagiaCompanyDictionaries", dictionariesData);
        verify(model).addAttribute("surveyData", surveyData);
        verify(model).addAttribute("breadcrumbs", breadcrumbs);
    }
}
