/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.sagia.enums.ValidationError;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.appintments.NationalInvestorAppointment;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.facades.user.SagiaUserFacade;
import com.sap.ibso.eservices.facades.user.data.SagiaRegisterData;
import com.sap.ibso.eservices.facades.user.exception.DuplicateEmailException;
import com.sap.ibso.eservices.facades.user.exception.DuplicatePhoneNumberException;
import com.sap.ibso.eservices.facades.user.exception.RegistrationValidationFailException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractRegisterPageController;
import com.sap.ibso.eservices.storefront.forms.NationalInvestorForm;
import com.sap.ibso.eservices.storefront.forms.SagiaInvestorJsonResponse;
import com.sap.ibso.eservices.storefront.forms.SagiaRegisterForm;
import com.sap.ibso.eservices.storefront.response.SagiaRegisterResponse;
import de.hybris.platform.acceleratorstorefrontcommons.consent.data.ConsentCookieData;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ConsentForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.constants.CommerceFacadesConstants;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Register Controller for mobile. Handles login and register for the account flow.
 */
@Controller
@RequestMapping(value = "/register")
public class SagiaRegisterPageController extends SagiaAbstractRegisterPageController {
    private static final Logger LOGGER = LogManager.getLogger(SagiaRegisterPageController.class);
    private static final String REGISTRATION_FAILED = "Registration failed: ";
    private static final String NUMBER_REGEX = "[0-9]+";
    private static final String FALSE = "false";

    @Resource(name = "sagiaConfigurationFacade")
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Resource(name = "sagiaRegistrationValidator")
    private Validator registrationValidator;

    @Resource(name = "sagiaNationalInvestorFacade")
    private SagiaNationalInvestorFacade sagiaNationalInvestorFacade;

    @Resource(name = "sagiaNationalInvestorValidator")
    private Validator nationalInvestorValidator;

    @Resource(name = "sagiaCountryFacade")
    private SagiaCountryFacade sagiaCountryFacade;

    @Resource(name = "sagiaSectorFacade")
    private SagiaSectorFacade sagiaSectorFacade;

    @Resource(name = "sagiaUserFacade")
    private SagiaUserFacade sagiaUserFacade;

    @Resource(name = "sagiaAppointmentFacade")
    private SagiaAppointmentFacade sagiaAppointmentFacade;

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "sagiaTermsAndConditionsFacade")
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Resource(name = "userService")
    private UserService userService;

    private HttpSessionRequestCache httpSessionRequestCache;

    private static final String FORM_GLOBAL_ERROR = "form.global.error";

    private static final String CONSENT_FORM_GLOBAL_ERROR = "consent.form.global.error";

    @Override
    protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException {
        return getContentPageForLabelOrId("login");
    }

    @Override
    protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response) {
        if (httpSessionRequestCache.getRequest(request, response) != null) {
            return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
        }
        return "/";
    }

    @Override
    protected String getView() {
        return ControllerConstants.Views.Pages.Account.AccountLoginPage;
    }

    @Resource(name = "httpSessionRequestCache")
    public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache) {
        this.httpSessionRequestCache = accHttpSessionRequestCache;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doRegister(final Model model) throws CMSItemNotFoundException {
    	model.addAttribute(new LoginForm());
    	model.addAttribute(new SagiaRegisterForm());
    	model.addAttribute(new NationalInvestorForm());
    	model.addAttribute(new NationalInvestorAppointment());
    	
    	
        return getDefaultRegistrationPage(model);
    }

    @RequestMapping(value = "/newcustomer", method = RequestMethod.POST)
    public String doRegister(final SagiaRegisterForm form, final BindingResult bindingResult, final Model model,
                             final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel)
            throws CMSItemNotFoundException {
        getRegistrationValidator().validate(form, bindingResult);
        return processRegisterUserRequest( form, bindingResult, model, request, response, redirectModel, null);
    }

    @RequestMapping(value = "/national-investor/data", method = RequestMethod.GET)
    @ResponseBody
    public String getNationalInvestorData() {
        Collection<ListItem> branches = new ArrayList<>();
        Collection<ListItem> departments = new ArrayList<>();
        Collection<ListItem> serviceTypes = null;
        Collection<ListItem> services = null;
        Collection<NIPLeagalStatusSet> nipLegalStatuses = new ArrayList<>();
        Collection<NIPCountrySet> nipCountryCollection = new ArrayList<>();
        Collection<NIPCountrySet> nipGCCCountryCollection = new ArrayList<>();
        Collection<NIPRegionSet> nipRegionCollection = new ArrayList<>();
        Collection<NipCitySet> nipCityCollection = new ArrayList<>();
        Collection<NipISICSectionSet> nipISICSections = new ArrayList<>();
        try {
            Collection<ListItem> formOptions = sagiaAppointmentFacade.getAppointmentOptions();
            departments = sagiaAppointmentFacade.getDepartments(formOptions);
            branches = sagiaAppointmentFacade.getBranches(formOptions);
            serviceTypes = sagiaAppointmentFacade.getServiceTypes(formOptions).stream().filter(x -> "SAGIASER".equals(x.getFieldKey())).collect(Collectors.toList());
            services = sagiaAppointmentFacade.getService(formOptions).stream().filter(x -> "SAGIASER".equals(x.getFieldSubType()) && "999999_47".equals(x.getFieldKey())).collect(Collectors.toList());
            nipLegalStatuses = sagiaNationalInvestorFacade.getLegalStatuses();
            nipCountryCollection = sagiaNationalInvestorFacade.getCountries();
            nipGCCCountryCollection = nipCountryCollection.stream().filter(
                    x -> ("GCC".equals(x.getNationalityCategory())) || ("SAUDI".equals(x.getNationalityCategory()))).collect(Collectors.toList());
            nipRegionCollection = sagiaNationalInvestorFacade.getRegions("SA");
            nipCityCollection = sagiaNationalInvestorFacade.getCities();
            nipISICSections = sagiaNationalInvestorFacade.getISICSections();
        } catch (SagiaODataException e) {
            LOGGER.error(e.getMessage(), e);
        }
        Model model = new ExtendedModelMap();
        model.addAttribute("branches", branches);
        model.addAttribute("nipLegalStatuses", nipLegalStatuses);
        model.addAttribute("nipCountries", nipCountryCollection);
        model.addAttribute("nipGCCCountries", nipGCCCountryCollection);
        model.addAttribute("nipRegions", nipRegionCollection);
        model.addAttribute("nipCities", nipCityCollection);
        model.addAttribute("nipISICSections", nipISICSections);
        String crmCountriesJson = new Gson().toJson(nipCountryCollection);
        model.addAttribute("crmCountriesJson", crmCountriesJson);

        NationalInvestorAppointment nationalInvestorAppointment = new NationalInvestorAppointment();
        model.addAttribute("departments", departments);
        if (departments != null && !departments.isEmpty()) {
            ListItem department = departments.iterator().next();
            nationalInvestorAppointment.setDepartmentDescription(department.getDescription());
            nationalInvestorAppointment.setDepartment(department.getFieldKey());
        }

        model.addAttribute("serviceTypes", serviceTypes);
        if (serviceTypes != null && !serviceTypes.isEmpty()) {
            ListItem serviceType = serviceTypes.iterator().next();
            model.addAttribute("serviceType", serviceType);
            nationalInvestorAppointment.setServiceType1(serviceType.getFieldKey());
        }

        model.addAttribute("services", services);
        if (services != null && !services.isEmpty()) {
            ListItem service = services.iterator().next();
            model.addAttribute("service", service);
            nationalInvestorAppointment.setService1(service.getFieldKey());
        }
        model.addAttribute("nationalInvestorAppointment", nationalInvestorAppointment);

        return new Gson().toJson(model);
    }

    public NationalInvestorHeaderSet populateNationalInvestor(NationalInvestorForm nationalInvestorForm) {
        NationalInvestorHeaderSet nationalInvestorHeaderSet = new NationalInvestorHeaderSet();
        nationalInvestorHeaderSet.setCrNumber(nationalInvestorForm.getCrNumber());
        nationalInvestorHeaderSet.setSrqGuid(nationalInvestorForm.getSrqGuid());
        nationalInvestorHeaderSet.setSrqId(nationalInvestorForm.getSrqId());
        nationalInvestorHeaderSet.setPartner(nationalInvestorForm.getPartner());
        nationalInvestorHeaderSet.setNameArabic(nationalInvestorForm.getNameArabic());
        nationalInvestorHeaderSet.setNameEnglish(nationalInvestorForm.getNameEnglish());
        nationalInvestorHeaderSet.setCapital(nationalInvestorForm.getCapital());
        nationalInvestorHeaderSet.setLegalStatus(nationalInvestorForm.getLegalStatus());
        nationalInvestorHeaderSet.setCompNationality(nationalInvestorForm.getCompNationality());
        nationalInvestorHeaderSet.setCompNationality(nationalInvestorForm.getCompNationality());
        nationalInvestorHeaderSet.setCountry(nationalInvestorForm.getCountry());
        nationalInvestorHeaderSet.setRegion(nationalInvestorForm.getRegion());
        nationalInvestorHeaderSet.setCity(nationalInvestorForm.getCity());
        nationalInvestorHeaderSet.setMobile(nationalInvestorForm.getMobile());
        nationalInvestorHeaderSet.setEmail(nationalInvestorForm.getEmail());
        nationalInvestorHeaderSet.setLicenceType(nationalInvestorForm.getLicenceType());
        nationalInvestorHeaderSet.setIsicSection(nationalInvestorForm.getIsicSection());
        nationalInvestorHeaderSet.setIsicDivision(nationalInvestorForm.getIsicDivision());
        nationalInvestorHeaderSet.setNumber700(nationalInvestorForm.getNumber700());
        nationalInvestorHeaderSet.setZakatNumber(nationalInvestorForm.getZakatNumber());
        nationalInvestorHeaderSet.setMolNumber(nationalInvestorForm.getMolNumber());
        nationalInvestorHeaderSet.setGosiNumber(nationalInvestorForm.getGosiNumber());
        nationalInvestorHeaderSet.setContactName(nationalInvestorForm.getContactName());
        nationalInvestorHeaderSet.setContactNationality(nationalInvestorForm.getContactNationality());
        nationalInvestorHeaderSet.setContactCountry(nationalInvestorForm.getContactCountry());
        nationalInvestorHeaderSet.setContactRegion(nationalInvestorForm.getContactRegion());
        nationalInvestorHeaderSet.setContactCity(nationalInvestorForm.getContactCity());
        nationalInvestorHeaderSet.setContactMobile(nationalInvestorForm.getContactMobile());
        nationalInvestorHeaderSet.setContactEmail(nationalInvestorForm.getContactEmail());
        nationalInvestorHeaderSet.setCrIssueDate(nationalInvestorForm.getCrIssueDate());
        nationalInvestorHeaderSet.setCrExpiryDate(nationalInvestorForm.getCrExpiryDate());
        nationalInvestorHeaderSet.setCrActivity(nationalInvestorForm.getCrActivity());
        nationalInvestorHeaderSet.setCrExpiryCheck(nationalInvestorForm.getCrExpiryCheck());
        nationalInvestorHeaderSet.setCrValidCheck(nationalInvestorForm.getCrValidCheck());
        nationalInvestorHeaderSet.setSuccess(nationalInvestorForm.getSuccess());
        nationalInvestorHeaderSet.setError(nationalInvestorForm.getError());
        List<MultipartFile> files = nationalInvestorForm.getFiles();
        List<NationalInvestorAtachment> attachments = new ArrayList<>();
        setAttachments(nationalInvestorHeaderSet, files, attachments);
        return nationalInvestorHeaderSet;
    }

    private void setAttachments(NationalInvestorHeaderSet nationalInvestorHeaderSet, List<MultipartFile> files, List<NationalInvestorAtachment> attachments) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                NationalInvestorAtachment nationalInvestorAtachment = new NationalInvestorAtachment();
                nationalInvestorAtachment.setFilename(file.getOriginalFilename());
                nationalInvestorAtachment.setMimeType(file.getContentType());
                try {
                    nationalInvestorAtachment.setContent(new String(file.getBytes()));
                } catch (IOException e) {
                    LOGGER.error("Content could not be parsed for file: " + file.getOriginalFilename(), e);
                }
                attachments.add(nationalInvestorAtachment);
            }
        }
        nationalInvestorHeaderSet.setAttachmentsSet(attachments);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(@RequestHeader(value = "referer", required = false) final String referer, final SagiaRegisterForm form,
                             final BindingResult bindingResult, final Model model, final HttpServletRequest request,
                             final HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
        getRegistrationValidator().validate(form, bindingResult);
        return processRegisterUserRequest(form, bindingResult, model, request, response, redirectModel, null);
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String doRegisterApplyForLicense(@RequestHeader(value = "referer", required = false) final String referer, final SagiaRegisterForm form,
                             final BindingResult bindingResult, final Model model, final HttpServletRequest request,
                             final HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
        getRegistrationValidator().validate(form, bindingResult);
        return processRegisterUserRequest(form, bindingResult, model, request, response, redirectModel, "/my-sagia/license/apply");
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SagiaRegisterResponse> doRegisterAjax(@RequestHeader(value = "referer", required = false) final String referer, final SagiaRegisterForm form,
                                                               final BindingResult bindingResult, final Model model, final HttpServletRequest request,
                                                               final HttpServletResponse response, final RedirectAttributes redirectModel)  {
        getRegistrationValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new SagiaRegisterResponse(RegisterResponse.VALIDATION_ERROR.getCode()), HttpStatus.BAD_REQUEST);
        }
        final List<ValidationError> errors = processAjaxRegisterUserRequest(form, request, response, false,referer);
        if (CollectionUtils.isEmpty(errors)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        final List<Integer> errorsCodes = errors.stream().map(ValidationError::getTitle).collect(Collectors.toList());
        return new ResponseEntity<>(new SagiaRegisterResponse(RegisterResponse.VALIDATION_ERROR.getCode(), errorsCodes), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/crm", method = RequestMethod.POST)
//    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity<SagiaRegisterResponse> doRegisterCrm(@RequestBody final SagiaRegisterForm form,
                                                               final BindingResult bindingResult,
                                                               final HttpServletRequest request,
                                                               final HttpServletResponse response)  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(sagiaConfigurationFacade.getTechnicalUsername() == null || authentication.getName() == null ||
                !sagiaConfigurationFacade.getTechnicalUsername().equalsIgnoreCase(authentication.getName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        form.setTermsAndConditionsChecked(true);
        getRegistrationValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new SagiaRegisterResponse(RegisterResponse.VALIDATION_ERROR.getCode()), HttpStatus.BAD_REQUEST);
        }
        final List<ValidationError> errors = processAjaxRegisterUserRequest(form, request, response, false,null);
        if (CollectionUtils.isEmpty(errors)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        final List<Integer> errorsCodes = errors.stream().map(ValidationError::getTitle).collect(Collectors.toList());
        return new ResponseEntity<>(new SagiaRegisterResponse(RegisterResponse.VALIDATION_ERROR.getCode(), errorsCodes), HttpStatus.BAD_REQUEST);
    }


    /**
     * Register national investor without a CR number. All null values are set to empty string.
     * End date is calculated based on the input (start time plus 15 minutes).
     *
     * @param nationalInvestorAppointment
     * @return
     */
    @RequestMapping(value = "/national-investor", method = RequestMethod.POST)
    @ResponseBody
    public String registerNationalInvestor(NationalInvestorAppointment nationalInvestorAppointment) {
        nationalInvestorAppointment.setAppointmentID(0);
        nationalInvestorAppointment.setAction("");
        nationalInvestorAppointment.setStatus("");
        nationalInvestorAppointment.setStatusDescription("");
        nationalInvestorAppointment.setBpID("");
        nationalInvestorAppointment.setAppointmentPrint("");
        NationalInvestorAppointment investorResult = sagiaNationalInvestorFacade.createAppointment(nationalInvestorAppointment);
        Map<String, String> responseJson = new HashMap<>();
        responseJson.put("success", "true");
        responseJson.put("responseData", investorResult.getAppointmentID().toString());
        return new Gson().toJson(responseJson);
    }

    /**
     * Loads the available time slots for a given date and branch.
     *
     * @param selectedDate
     * @param branch
     * @return
     */
    @RequestMapping(value = "/get-calendar-slots", method = RequestMethod.GET)
    @ResponseBody
    public String getCalendarSlots(@RequestParam("selectedDate") String selectedDate, @RequestParam("branch") String branch) {
        return new Gson().toJson(
                sagiaNationalInvestorFacade.getNewInvestorCalendarSlots(
                        LocalDate.parse(selectedDate + " 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd mm:ss")), branch));
    }

    /**
     * retrieves the countries list and provide it in JSON format for frontend
     *
     * @return
     */
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    @ResponseBody
    public String getCountries() {
        return new Gson().toJson(sagiaCountryFacade.getCountriesList());
    }

    /**
     * retrieves the countries list and provide it in JSON format for frontend
     *
     * @return
     */
    @RequestMapping(value = "/sectors", method = RequestMethod.GET)
    @ResponseBody
    public String getSagiaSectors() {
        return new Gson().toJson(sagiaSectorFacade.getSectorsList());
    }

    @RequestMapping(value = "/unifiedLicenseUrl", method = RequestMethod.GET)
    @ResponseBody
    public String getUnifiedLicenseUrl() {
        return sagiaConfigurationFacade.getUnifiedLicenseUrl();
    }

    /**
     *
     * @return Validation Response that contains code
     */
    @RequestMapping(value = "/validation", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String validate(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String mobileNumber,
            @RequestParam(required = false) String mobileCountryCode) {
        if (Strings.isNotEmpty(email) && (StringUtils.length(email) > 255 || !validateEmailAddress(email))) {
            return FALSE;
        }

        if (Strings.isNotEmpty(mobileNumber) && (StringUtils.length(mobileNumber) > 255 || !mobileNumber.matches(NUMBER_REGEX))) {
            return FALSE;
        }

        boolean validationResult = sagiaUserFacade.validateUniqueValue(userName, email, mobileNumber, mobileCountryCode);
        if (!validationResult) {
            return FALSE;
        }
        return "true";
    }

    /**
     * This method takes data from the registration form and create a new customer account and attempts to log in using
     * the credentials of this new user.
     *
     * @throws CMSItemNotFoundException exception
     */
    private String processRegisterUserRequest(final SagiaRegisterForm registerForm, final BindingResult bindingResult,
                                              final Model model, final HttpServletRequest request, final HttpServletResponse response,
                                              final RedirectAttributes redirectModel,
                                              final String successRedirectUrl) throws CMSItemNotFoundException {
        if (bindingResult.hasErrors()) {
            return handleRegistrationError(registerForm, model);
        }

        final SagiaRegisterData data = getSagiaRegisterData(registerForm,null);

        try {
            getCustomerFacade().register(data);
            getAutoLoginStrategy().login(registerForm.getUserName().toLowerCase(), registerForm.getPwd(), request, response);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
                    "registration.confirmation.message.title");
        } catch (final DuplicateEmailException e) {
            LOGGER.warn(REGISTRATION_FAILED + e.getLocalizedMessage(),e);
            bindingResult.rejectValue("email", "register.email.duplicate");
            return handleRegistrationError(registerForm, model);
        } catch (final DuplicateUidException e) {
            LOGGER.warn(REGISTRATION_FAILED + e.getLocalizedMessage(),e);
            bindingResult.rejectValue("userName", "register.userName.duplicate");
            return handleRegistrationError(registerForm, model);
        } catch (final DuplicatePhoneNumberException e) {
            LOGGER.warn(REGISTRATION_FAILED + e.getLocalizedMessage(),e);
            bindingResult.rejectValue("mobileCountryCode", "register.mobileCountryCode.duplicate");
            bindingResult.rejectValue("mobileNumber", "register.mobileNumber.duplicate");
            return handleRegistrationError(registerForm, model);
        } catch (final RegistrationValidationFailException e) {
            LOGGER.warn(REGISTRATION_FAILED + e.getLocalizedMessage(),e);
            return handleRegistrationError(registerForm, model);
        }

        // Consent form data
        try {
            final ConsentForm consentForm = registerForm.getConsentForm();
            if (consentForm != null && consentForm.getConsentGiven()) {
                getConsentFacade().giveConsent(consentForm.getConsentTemplateId(), consentForm.getConsentTemplateVersion());
            }
        } catch (final Exception e) {
            LOGGER.error("Error occurred while creating consents during registration", e);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, CONSENT_FORM_GLOBAL_ERROR);
        }

        saveConsentCookie(request);

        customerConsentDataStrategy.populateCustomerConsentDataInSession();
        if (Strings.isNotEmpty(successRedirectUrl)) {
            return REDIRECT_PREFIX + successRedirectUrl;
        }

        return REDIRECT_PREFIX + getSuccessRedirect(request, response);
    }

    private void saveConsentCookie(HttpServletRequest request) {
        // save anonymous-consent cookies as ConsentData
        final Cookie cookie = WebUtils.getCookie(request, WebConstants.ANONYMOUS_CONSENT_COOKIE);
        if (cookie != null) {
            try {
                final List<ConsentCookieData> consentCookieDataList = Arrays.asList(new ObjectMapper().readValue(
                        URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.displayName()), ConsentCookieData[].class));
                consentCookieDataList.stream().filter(consentData -> CommerceFacadesConstants.CONSENT_GIVEN.equals(consentData.getConsentState()))
                        .forEach(consentData -> consentFacade.giveConsent(consentData.getTemplateCode(),
                                consentData.getTemplateVersion()));
            } catch (final UnsupportedEncodingException e) {
                LOGGER.error(String.format("Cookie Data could not be decoded : %s", cookie.getValue()), e);
            } catch (final IOException e) {
                LOGGER.error("Cookie Data could not be mapped into the Object", e);
            } catch (final Exception e) {
                LOGGER.error("Error occurred while creating Anonymous cookie consents", e);
            }
        }
    }

    private SagiaRegisterData getSagiaRegisterData(SagiaRegisterForm registerForm, String campaign) {
        final SagiaRegisterData data = new SagiaRegisterData();
        if(registerForm.getEmail() != null && !registerForm.getEmail().isEmpty()) {
        	data.setEmail(registerForm.getEmail().toLowerCase());
        }
        data.setFirstName(registerForm.getFirstName());
        data.setLastName(registerForm.getLastName());
        data.setLogin(registerForm.getUserName());
        data.setPassword(registerForm.getPwd());
        data.setTitleCode(registerForm.getTitleCode());
        data.setCompany(registerForm.getCompany());
        data.setMobileCountryCode(registerForm.getMobileCountryCode());
        data.setMobileNumber(registerForm.getMobileNumber());
        data.setSectorCode(registerForm.getSector());
        data.setCountryCode(registerForm.getCountryCode());
        data.setCampaign(campaign);
        return data;
    }

    private List<ValidationError> processAjaxRegisterUserRequest(final SagiaRegisterForm registerForm,
                                                                 final HttpServletRequest request, final HttpServletResponse response,
                                                                 final Boolean processLoginUser, final String referer) {
        int campaignCodeIndex=referer.indexOf("=");
        String campaign=null;
        if(null != referer && -1!=campaignCodeIndex)
        {
            campaign=referer.substring(campaignCodeIndex+1);
        }
        final SagiaRegisterData data = getSagiaRegisterData(registerForm,campaign);

        if(StringUtils.isNotEmpty(registerForm.getEntityId())){
            data.setEntityId(registerForm.getEntityId());
        }

        try {
            getCustomerFacade().register(data);
            if (processLoginUser) {
                getAutoLoginStrategy().login(registerForm.getUserName().toLowerCase(), registerForm.getPwd(), request, response);
            }
        } catch (final DuplicateEmailException e) {
            LOGGER.warn(REGISTRATION_FAILED + e.getLocalizedMessage(),e);
            return Collections.singletonList(ValidationError.DUPLICATE_EMAIL);
        } catch (final DuplicateUidException e) {
            LOGGER.warn(REGISTRATION_FAILED + e.getLocalizedMessage(),e);
            return Collections.singletonList(ValidationError.DUPLICATE_UID);
        } catch (final DuplicatePhoneNumberException e) {
            LOGGER.warn(REGISTRATION_FAILED + e.getLocalizedMessage(),e);
            return Collections.singletonList(ValidationError.DUPLICATE_MOBILE_NUMBER);
        } catch (final RegistrationValidationFailException e) {
            LOGGER.warn(REGISTRATION_FAILED + e.getLocalizedMessage(),e);
            return e.getErrors();
        } catch (final Exception e) {
            LOGGER.warn(REGISTRATION_FAILED + e.getLocalizedMessage(),e);
            throw e;
        }

        // Consent form data
        try {
            final ConsentForm consentForm = registerForm.getConsentForm();
            if (consentForm != null && consentForm.getConsentGiven()) {
                getConsentFacade().giveConsent(consentForm.getConsentTemplateId(), consentForm.getConsentTemplateVersion());
            }
        } catch (final Exception e) {
            LOGGER.error("Error occurred while creating consents during registration", e);
        }

        saveConsentCookie(request);

        customerConsentDataStrategy.populateCustomerConsentDataInSession();
        return Collections.emptyList();
    }

    /**
     * Prepares a model in case of detected registration errors and returns a view.
     *
     * @param registerForm the registration form
     * @param model        the model
     * @return the view (from {@link SagiaRegisterPageController#getView()}
     * @throws CMSItemNotFoundException in case of issue to retrieve CMS page
     */
    private String handleRegistrationError(SagiaRegisterForm registerForm, final Model model) throws CMSItemNotFoundException {
        model.addAttribute(registerForm);
        model.addAttribute(new LoginForm());
        model.addAttribute(new GuestForm());
        model.addAttribute("nationalInvestorForm", new NationalInvestorForm());
        model.addAttribute("nationalInvestorAppointment", new NationalInvestorAppointment());
        model.addAttribute("registerFormError", FORM_GLOBAL_ERROR);
        return handleRegistrationError(model);
    }

    @Override
    protected Validator getRegistrationValidator() {
        return registrationValidator;
    }

    /**
     * Ajax call to load an investor information based on the CR Number.
     * The response is used to pre-fill the form. In case of an invalid number an error will be displayed.
     *
     * @param crNumber crNumber
     * @return
     */
    @RequestMapping(path = {"/national-investor/{crNumber}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getNationalInvestorHeaderSet(@PathVariable("crNumber") String crNumber) {
        return new Gson().toJson(sagiaNationalInvestorFacade.getNationalInvestorHeaderSet(crNumber));
    }

    /**
     * Ajax call made for loading the legal statuses for the National Investor Registration WITH CR Number scenario
     *
     * @return
     */
    @RequestMapping(path = {"/get-legal-statuses"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getLegalStatuses() {
        return new Gson().toJson(sagiaNationalInvestorFacade.getLegalStatuses());
    }

    /**
     * Ajax call made for loading the nationalities for the National Investor Registration WITH CR Number scenario
     *
     * @param nationalityCategory
     * @return
     */
    @RequestMapping(path = {"/get-nationalities/{nationalityCategory}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getNationalities(@PathVariable("nationalityCategory") String nationalityCategory) {
        Collection<NIPCountrySet> countries = sagiaNationalInvestorFacade.getCountries(nationalityCategory).stream().filter(
                x -> ("GCC".equals(x.getNationalityCategory())) || ("SAUDI".equals(x.getNationalityCategory()))).collect(Collectors.toList());
        return new Gson().toJson(countries);
    }

    /**
     * Ajax call made for loading the regions given a country for the National Investor Registration WITH CR Number scenario
     *
     * @param countryName
     * @return
     */
    @RequestMapping(path = {"/get-regions/{country}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getRegions(@PathVariable("country") String countryName) {
        return new Gson().toJson(sagiaNationalInvestorFacade.getRegions(countryName));
    }

    /**
     * Ajax call made for loading the cities given a region for the National Investor Registration WITH CR Number scenario
     *
     * @param region
     * @return
     */
    @RequestMapping(path = {"/get-cities/{region}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCities(@PathVariable("region") String region) {
        Collection<NipCitySet> cities = new ArrayList<>();
        if (region != null && !region.trim().isEmpty()) {
            cities = sagiaNationalInvestorFacade.getCities(region);
        }
        return new Gson().toJson(cities);
    }

    /**
     * Ajax call made for loading the ISIC Sections for the National Investor Registration WITH CR Number scenario
     *
     * @return
     */
    @RequestMapping(path = {"/get-isic-sections"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getIsicSections() {
        return new Gson().toJson(sagiaNationalInvestorFacade.getISICSections());
    }

    /**
     * Ajax call made for loading the ISIC Divisions given a ISIC Section for the National Investor Registration WITH CR Number scenario
     *
     * @param sectionCode
     * @return
     */
    @RequestMapping(path = {"/get-isic-divisions/{sectionCode}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getIsicDivisions(@PathVariable("sectionCode") String sectionCode) {
        return new Gson().toJson(sagiaNationalInvestorFacade.getISICDivisions(sectionCode));
    }

    /**
     * Post handler called with ajax for registering as National Investor Registration WITH CR Number
     *
     * @param nationalInvestorForm
     * @param result
     * @return
     */
    @RequestMapping(value = "/national-investor-cr", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @ResponseBody
    public SagiaInvestorJsonResponse registerNationalInvestorCrNumber(@ModelAttribute NationalInvestorForm nationalInvestorForm, BindingResult result) {
        SagiaInvestorJsonResponse response = new SagiaInvestorJsonResponse();
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.REGISTRATION);
        nationalInvestorValidator.validate(nationalInvestorForm, result);
        if (result.hasErrors()) {
            Map<String, String> errorsMap = new HashMap<>();
            result.getFieldErrors().forEach(e -> {
                String message = messageSource.getMessage(e, null);
                errorsMap.put(e.getField(), message);
            });
            response.setErrors(errorsMap);
            response.setValid(false);
            return response;
        }
        NationalInvestorHeaderSet returnedFromCRM = sagiaNationalInvestorFacade.saveNationalInvestor(populateNationalInvestor(nationalInvestorForm));
        response.setValid(true);
        response.setResponseData(returnedFromCRM);
        return response;
    }

    private enum RegisterResponse {
        SUCCESS(0),
        VALIDATION_ERROR(1);

        private Integer code;

        RegisterResponse(final Integer code)
        {
            this.code = code;
        }
        public Integer getCode()
        {
            return code;
        }
    }

    private enum ValidationResponse {
        SUCCESS(0),
        VALIDATION_ERROR(1);

        private Integer code;

        ValidationResponse(final Integer code)
        {
            this.code = code;
        }
        public Integer getCode()
        {
            return code;
        }
    }

    private boolean validateEmailAddress(final String email) {
        return Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b").matcher(email).matches();
    }
}
