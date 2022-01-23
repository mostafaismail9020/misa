/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.core.model.SagiaSectorModel;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.facades.user.exception.DuplicatePhoneNumberException;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaRuntimeException;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.SagiaUpdatePwdForm;
import com.sap.ibso.eservices.storefront.forms.SagiaUpdateUsernameForm;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateEmailForm;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;
import org.apache.commons.lang.StringUtils;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.commercefacades.user.exceptions.PasswordMismatchException;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.i18n.L10NService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for home page
 */
@Controller
@RequestMapping("/my-sagia")
public class SagiaAccountPageController extends SagiaAbstractPageController {
    private static final Logger LOG = Logger.getLogger(SagiaAccountPageController.class);

    private static final int SAGIA_ITEMS_PER_PAGE = 5;
    private static final int SAGIA_FIRST_PAGE_INDEX = 1;

    private static final String SAGIA_COMPANY_PROFILE_CMS_PAGE = "sagia-profile";
    private static final String BREADCRUMBS_ATTR = "breadcrumbs";
    private static final String TEXT_ACCOUNT_PROFILE = "text.account.profile";
    private static final String REDIRECT_TO_PROFILE_PAGE = REDIRECT_PREFIX + "/my-sagia/sagia-profile";
    private static final String ENTITY_NAME = "ComplaintsAndEnquiryHdrs";

    private static final String CONFIRMATION_UPDATED = "text.account.profile.confirmationUpdated";
    private static final String UPDATE_FAILED = "Update failed";
    private static final String SUCCESS = "{'success' : '";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "sagiaCustomerFacade")
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Resource(name = "sagiaAccountFacade")
    private SagiaAccountFacade sagiaAccountFacade;

    @Resource(name = "sagiaSurveyFacade")
    private SagiaSurveyFacade sagiaSurveyFacade;

    @Resource(name = "sagiaComplaintFacade")
    private SagiaComplaintFacade sagiaComplaintFacade;

    @Resource(name = "accountBreadcrumbBuilder")
    private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

    @Resource(name = "sagiaSectorFacade")
    private SagiaSectorFacade sagiaSectorFacade;

    @Resource(name = "sagiaCountryFacade")
    private SagiaCountryFacade sagiaCountryFacade;

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @Resource(name = "sagiaChangingEmailFacade")
    private SagiaChangingEmailFacade changingEmailFacade;

    @Resource(name = "sagiaEmailValidator")
    private Validator emailValidator;

    @Resource(name = "sagiaUsernameValidator")
    private Validator usernameValidator;

    @Resource(name = "sagiaProfileValidator")
    private Validator sagiaProfileValidator;

    @Resource(name = "sagiaConfigurationFacade")
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Resource(name = "sagiaPaginationFacade")
    private SagiaPaginationFacade sagiaPaginationFacade;

    @Resource(name = "sagiaLicenseFacade")
    private SagiaLicenseFacade sagiaLicenseFacade;

    @Resource(name = "ZUI5SagiaFacade")
    private ZUI5SagiaFacade zui5SagiaFacade;

    @Resource(name = "l10nService")
    private L10NService l10nService;

    @Autowired
    private UserService userService;

    @Autowired
    private MediaService mediaService;

    private SagiaAccountFacade getSagiaAccountFacade() {
        return sagiaAccountFacade;
    }

    private SagiaSurveyFacade getSagiaSurveyFacade() {
        return sagiaSurveyFacade;
    }

    private SagiaCustomerFacade getSagiaCustomerFacade() {
        return sagiaCustomerFacade;
    }

    @RequestMapping(value = "/sagia-profile", method = RequestMethod.GET)
    @RequireHardLogIn
    public String profile(final Model model) throws CMSItemNotFoundException {
        return getViewForPage(populateSagiaProfile(model));
    }

    @RequestMapping(value = "/update-my-profile", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public String updatePersonalData(@RequestBody ProfilePersonalData profilePersonalData, final BindingResult bindingResult) {
        getSagiaProfileValidator().validate(profilePersonalData, bindingResult);
        if (!bindingResult.hasErrors()) {
            CustomerData customerData = sagiaCustomerFacade.getCurrentCustomer();
            customerData.setTitleCode(profilePersonalData.getTitle().getCode());
            if(StringUtils.isNotEmpty(profilePersonalData.getFirstName())) {
            	customerData.setFirstName(XSSFilterUtil.filter(profilePersonalData.getFirstName()));
            }
            if(StringUtils.isNotEmpty(profilePersonalData.getLastName())) {
            	customerData.setLastName(XSSFilterUtil.filter(profilePersonalData.getLastName()));
            }
            if(StringUtils.isNotEmpty(profilePersonalData.getCompany())) {
            	customerData.setCompany(XSSFilterUtil.filter(profilePersonalData.getCompany()));
            }
            //customerData.setFirstName(profilePersonalData.getFirstName());
            //customerData.setLastName(profilePersonalData.getLastName());
            //customerData.setCompany(profilePersonalData.getCompany());
            customerData.setMobileCountryCode(profilePersonalData.getMobileCountryCode());
            customerData.setMobileNumber(profilePersonalData.getMobileNumber());
            customerData.setSector(profilePersonalData.getSector());
            customerData.setCountry(profilePersonalData.getCountry());
            customerData.setCompanyWebsite(profilePersonalData.getCompanyWebsite());

            try {
                sagiaCustomerFacade.updateProfile(customerData);
                return SUCCESS + l10nService.getLocalizedString(CONFIRMATION_UPDATED) + "'}";
            } catch (DuplicatePhoneNumberException e) {
                LOG.error(e.getMessage(), e);
                throw new DuplicatePhoneNumberException(l10nService.getLocalizedString("update.profile.duplicate.phoneNumber"), e);
            } catch (DuplicateUidException e) {
                LOG.error(e.getMessage(), e);
                throw new SagiaRuntimeException("duplicate UID", e);
            }
        } else {
            throw new SagiaRuntimeException(l10nService.getLocalizedString("profile.data.invalid"));
        }
    }

    private Validator getSagiaProfileValidator() {
        return sagiaProfileValidator;
    }

    @RequestMapping(value = {"/update-password", "/update-username", "/update-email"}, method = RequestMethod.GET)
    @RequireHardLogIn
    public String getViewForPasswordUsernameEmail(final Model model) throws CMSItemNotFoundException {
        return getViewForPage(populateSagiaProfile(model));
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public String updatePassword(@RequestBody final SagiaUpdatePwdForm updatePwdForm, final BindingResult bindingResult) {
        getSagiaCustomerFacade().validate(updatePwdForm.getPwd(), bindingResult);
        if (!bindingResult.hasErrors()) {
            if (updatePwdForm.getPwd().equals(updatePwdForm.getCheckPwd())) {
                getSagiaCustomerFacade().updatePassword(updatePwdForm.getOldPwd(), updatePwdForm.getPwd(), bindingResult);
                return SUCCESS + l10nService.getLocalizedString("text.account.confirmation.password.updated") + "'}";
            } else {
                throw new SagiaRuntimeException(l10nService.getLocalizedString("validation.checkPwd.equals"));
            }
        } else {
            throw new SagiaRuntimeException(UPDATE_FAILED);
        }
    }

    @RequestMapping(value = "/update-email", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public String updateEmail(@RequestBody final UpdateEmailForm updateEmailForm, final BindingResult bindingResult) {
        getEmailValidator().validate(updateEmailForm, bindingResult);
        if(!bindingResult.hasErrors()) {
            CustomerData customerData = sagiaCustomerFacade.getCurrentCustomer();
            customerData.setEmail(updateEmailForm.getEmail());
            try {
                changingEmailFacade.updateEmail(customerData, updateEmailForm.getPassword());
                return SUCCESS + l10nService.getLocalizedString(CONFIRMATION_UPDATED) + "'}";
            } catch(ModelSavingException e) {
                LOG.error(e.getMessage(), e);
                throw new SagiaRuntimeException(l10nService.getLocalizedString("update.email.duplicate"));
            } catch (PasswordMismatchException e) {
                throw new SagiaRuntimeException(l10nService.getLocalizedString("update.profile.incorrect.password"));
            }
        } else {
            throw new SagiaRuntimeException(UPDATE_FAILED);
        }
    }

    private Validator getEmailValidator() {
        return emailValidator;
    }

    private Validator getUsernameValidator() {
        return usernameValidator;
    }

    @RequestMapping(value = "/update-username", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public String updateUsername(@RequestBody final SagiaUpdateUsernameForm sagiaUpdateUsernameForm, final BindingResult bindingResult) {
        getUsernameValidator().validate(sagiaUpdateUsernameForm, bindingResult);
        if (!bindingResult.hasErrors()) {
            try {
                sagiaCustomerFacade.changeUid(sagiaUpdateUsernameForm.getUsername(), sagiaUpdateUsernameForm.getPassword());

                // Replace the spring security authentication with the new UID
                final String newUid = sagiaCustomerFacade.getCurrentCustomer().getUid().toLowerCase();
                final Authentication oldAuthentication = SecurityContextHolder.getContext().getAuthentication();
                final UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(newUid, null,
                        oldAuthentication.getAuthorities());
                newAuthentication.setDetails(oldAuthentication.getDetails());
                SecurityContextHolder.getContext().setAuthentication(newAuthentication);

                return SUCCESS + l10nService.getLocalizedString(CONFIRMATION_UPDATED) + "'}";
            } catch (final DuplicateUidException e) {
                LOG.error(e.getMessage(), e);
                throw new SagiaRuntimeException(l10nService.getLocalizedString("register.userName.duplicate"));
            } catch (final PasswordMismatchException e) {
                LOG.error(e.getMessage(), e);
                throw new SagiaRuntimeException(l10nService.getLocalizedString("update.profile.incorrect.password"));
            }
        } else {
            throw new SagiaRuntimeException(UPDATE_FAILED);
        }
    }

    @RequestMapping(value = "/validate-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public String validateOldPassword(@RequestBody String password) {
        return "{\"isValid\" : " + getSagiaCustomerFacade().validateCurrentPassword(password) + "}";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/attachment/{objectId}/{documentID}")
    @RequireHardLogIn
    public ResponseEntity<InputStreamResource> getComplaintAttachment(@PathVariable("objectId") String objectId,
                                                                      @PathVariable("documentID") String documentId) {
        try {
            InputStream attachmentStream = getSagiaAccountFacade().readAttachment(objectId, documentId);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(attachmentStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            return new ResponseEntity<>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Gets a title matching a title code from a list of titles.
     * If no matching title exists then an empty title (i.e. code and name of the {@link TitleData} instance are empty strings)
     * is returned.
     *
     * @param titles the list of titles
     * @param code   the title code
     * @return the title
     */
    private TitleData getTitleForCode(final List<TitleData> titles, final String code) {
        // Look for matching title
        if (titles != null && code != null) {
            for (final TitleData title : titles) {
                if (code.equals(title.getCode())) {
                    return title;
                }
            }
        }

        // Create empty title
        TitleData title = new TitleData();
        title.setCode("");
        title.setName("");

        return title;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/companyData")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getCompanyData() {
        Map<String, Object> responseMap = new HashMap<>();
        final BasicCompanyData companyData = getSagiaAccountFacade().getProfileCompanyData();
        HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
        responseMap.put("sagiaCompanyForm", companyData);
        final ContactData contactData = getSagiaAccountFacade().getCompanyContacts();
        responseMap.put("sagiaProfileGeneralManagerForm", contactData.getProfileGeneralManagerData());
        responseMap.put("licenseClassification", homeHDR.getLicenseInfoData().getLicClass());
        responseMap.put("sagiaProfileRepresentativesForm", contactData.getProfileCompanyRepresentativesData());
        responseMap.put("branches",sagiaLicenseFacade.getBranches(((CustomerModel)userService.getCurrentUser()).getEntityID()));
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/personalData")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getPersonalData() {
        Map<String, Object> responseMap = new HashMap<>();
        final CustomerData customerData = sagiaCustomerFacade.getCurrentCustomer();
        if (customerData.getProfilePicture() != null) {
            responseMap.put("profilePicture", customerData.getProfilePicture().getUrl());
        }
        if (customerData.getCompanyLogo() != null) {
            responseMap.put("companyLogo", customerData.getCompanyLogo().getUrl());
        }
        if (customerData.getCustomerId() != null) {
            ProfileData profileData = new ProfileData();
            ProfilePersonalData profilePersonalData = new ProfilePersonalData();
            profilePersonalData.setTitle(getTitleForCode(userFacade.getTitles(), customerData.getTitleCode()));
            profilePersonalData.setFirstName(customerData.getFirstName());
            profilePersonalData.setLastName(customerData.getLastName());
            profilePersonalData.setSector(customerData.getSector());
            profilePersonalData.setMobileCountryCode(customerData.getMobileCountryCode());
            profilePersonalData.setMobileNumber(customerData.getMobileNumber());
            profilePersonalData.setCompany(customerData.getCompany());
            profilePersonalData.setCountry(customerData.getCountry());
            profilePersonalData.setCompanyWebsite(customerData.getCompanyWebsite());
            profileData.setProfilePersonalData(profilePersonalData);
            responseMap.put("sagiaProfilePersonalForm", profileData.getProfilePersonalData());
        }
        responseMap.put("titles", userFacade.getTitles());
        if(sagiaSectorFacade.getSectorsList().contains(customerData.getSector().getCode()))
        {
            responseMap.put("sectors", sagiaSectorFacade.getSectorsList());
        }
        else
        {
            List<SagiaSectorData> sectorsList=sagiaSectorFacade.getSectorsList();
            sectorsList.add(customerData.getSector());
            responseMap.put("sectors", sectorsList);
        }
        responseMap.put("countries", sagiaCountryFacade.getCountriesList());
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/securityData")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getSecurityData() {
        Map<String, Object> responseMap = new HashMap<>();
        final CustomerData customerData = sagiaCustomerFacade.getCurrentCustomer();
        if (customerData.getCustomerId() != null) {
            responseMap.put("currentEmail", customerData.getEmail());
            responseMap.put("currentUsername", customerData.getUid());
        }
        String backendRegex = getSagiaCustomerFacade().getSagiaConfigurationFacade().getPasswordRegex();
        String currentLanguage = getI18nService().getCurrentLocale().getLanguage();
        String backendRegexErrorMessage = sagiaConfigurationFacade.getPasswordErrorMessage(currentLanguage);
        responseMap.put("backendRegex", backendRegex);
        responseMap.put("backendRegexErrorMessage", backendRegexErrorMessage);
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/surveyData")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getSurveyData() {
        Map<String, Object> responseMap = new HashMap<>();
        final SurveyDetailsData surveyDetailsData = getSagiaSurveyFacade().getSurveyListData();
        responseMap.put("newSurveyList", surveyDetailsData.getNewSurveys());
        responseMap.put("surveyList", surveyDetailsData.getSurveys());
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/surveyCount")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Integer> getSurveyCount() {
        Map<String, Integer> responseMap = new HashMap<>();
        Integer surveyCount = 0;
        try {
            final SurveyDetailsData surveyDetailsData = getSagiaSurveyFacade().getSurveyListData();
            if(surveyDetailsData != null) {
                surveyCount = surveyDetailsData.getTabCount();
            }
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
        }
        responseMap.put("surveyCount", surveyCount);
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/complaintsCount")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Integer> getComplaintsCount() {
        Map<String, Integer> responseMap = new HashMap<>();
        int complaintsCount = 0;
        try {
            List<TicketData> allTicketsData = sagiaComplaintFacade.getTickets();
            if(allTicketsData != null) {
                complaintsCount = sagiaComplaintFacade.getOpenTicketsSize(allTicketsData);
            }
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
        }
        responseMap.put("complaintsCount", complaintsCount);
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/complaintData")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getComplaintData() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("complaintFormData", sagiaComplaintFacade.getComplaintFormData());
        List<TicketData> allTicketsData = sagiaComplaintFacade.getTickets();
        getSessionService().setAttribute("Tickets", allTicketsData);
        responseMap.put("TicketsPagesNumber", sagiaPaginationFacade.getPagesNumber(allTicketsData.size(), SAGIA_ITEMS_PER_PAGE));
        responseMap.put("complaintList",
                sagiaPaginationFacade.getTicketListForPage(allTicketsData, SAGIA_FIRST_PAGE_INDEX, SAGIA_ITEMS_PER_PAGE, allTicketsData.size()));
        responseMap.put("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        responseMap.put("ticketsItemsPerPage", SAGIA_ITEMS_PER_PAGE);
        responseMap.put("showItemsPerPage", sagiaConfigurationFacade.getShowItemsPerPage());
        return responseMap;
    }

    private Model populateSagiaProfile(Model model) throws CMSItemNotFoundException {

        model.addAttribute("contactUpdateTemplateUrl",mediaService.getMedia("contactInfoTemplateSample").getURL());
        model.addAttribute("complaintFormData", new ComplaintFormData());
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_COMPANY_PROFILE_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_COMPANY_PROFILE_CMS_PAGE));
        model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs(TEXT_ACCOUNT_PROFILE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

        return model;
    }

    /**
     * controller method, that updates the profile picture for current user and redirects to profile page.
     *
     * @param file to upload
     */
    @RequestMapping(value = "/update-profilePic", method = RequestMethod.POST, consumes = "multipart/form-data")
    @RequireHardLogIn
    public String handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam(value="companyLogo",defaultValue="false") boolean companyLogo) {
        try {
        	if(companyLogo) {
        		sagiaCustomerFacade.updateCompanyLogo(file);
        	}else {
            sagiaCustomerFacade.updateProfilePicture(file);
            }
            
        } catch(IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return REDIRECT_TO_PROFILE_PAGE;
    }
}
