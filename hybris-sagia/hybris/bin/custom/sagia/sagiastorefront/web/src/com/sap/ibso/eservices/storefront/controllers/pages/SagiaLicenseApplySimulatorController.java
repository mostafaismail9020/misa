package com.sap.ibso.eservices.storefront.controllers.pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.facades.data.ProfileCompanyData;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import com.sap.ibso.eservices.facades.data.zqeemah.ValidateSareholder;
import com.sap.ibso.eservices.facades.data.zqeemah2.BasicOrganizationInformation;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.storefront.forms.SagiaApplyReviewForm;
import com.sap.ibso.eservices.storefront.forms.validation.license.SagiaLicenseValidator;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/simulator")
/*
 * Suppress sonar warning (squid:S1068 | Unused "private" fields should be removed
 * Suppress sonar warning (squid:CommentedOutCodeLine | Sections of code should not be "commented out
 * will be removed
 */
@SuppressWarnings({"squid:S1068","squid:CommentedOutCodeLine"})
public class SagiaLicenseApplySimulatorController extends SagiaLicenseApplyController {
    private static final Logger LOGGER = LogManager.getLogger(SagiaLicenseApplySimulatorController.class);

    private static final String SAGIA_LICENSE_APPLY_CMS_PAGE = "license-apply";
    private static final String MIGS_SESSION_URL = "migs.sessionjs.url";
    private static final String ENTITY_NAME = "QemahServiceReq";
    private static final String SHAREHOLDER_TYPE_EXISTING = "Existing";
    private static final String SHAREHOLDER_TYPE_PERSON = "Person";
    private static final String SHAREHOLDER_TYPE_ORGANIZATION = "Organization";

    private static final String BATCHNO = "batchNo";
    private static final String USERINPUT = "userInput";
    private static final String QEEMAH_1_DATA = "qeemah1Data";
    private static final String NEW_SHAREHOLDERS = "newShareholders";
    private static final String EXISTING_SHAREHOLDERS = "existingShareholders";
    private static final String SAGIA_LICENSE_APPLY_DRAFT = "licenseApplyDraftId";

    private static final Integer BATCH_SIZE_FOR_PRODUCTS_SEARCH = 100;
    private static final String ENTITY_NUMBER = "entityNumber";
    private static final String NAME = "name";
    private static final String MOCK_SERVICE_PATH = "/sagiaservices";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "sagiaPaymentDetailsFacade")
    private SagiaPaymentDetailsFacade sagiaPaymentDetailsFacade;

    @Resource(name = "sagiaLicenseApplyValidator")
    private SagiaLicenseValidator sagiaLicenseApplyValidator;

    @Resource(name = "sagiaZqeemahFacade")
    private SagiaZqeemahFacade sagiaZqeemahFacade;

    @Resource(name = "sagiaZqeemah2Facade")
    private SagiaZqeemah2Facade sagiaZqeemah2Facade;

    @Resource(name = "defaultLicenseAmendmentFacade")
    private SagiaLicenseAmendmentFacade sagiaLicenseAmendmentFacade;

    @Resource(name = "defaultSagiaIsicFacade")
    private SagiaIsicFacade sagiaIsicFacade;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private ApplicationContext appContext;

    @Resource(name="i18NService")
    private I18NService i18NService;

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @RequestMapping(path = "/paymentDetails/{serviceType}/{qeemah}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String requestPaymentDetails(final Model model, final HttpServletRequest request, final HttpServletResponse response,
                                        @PathVariable("serviceType") String serviceType, @PathVariable(value = "qeemah", required = false) String qeemah) {
        Assert.isTrue(Strings.isNotEmpty(serviceType),"License process is not specified while interrogating for price simulation.");

        if(Strings.isNotEmpty(qeemah)){
            return new Gson().toJson(sagiaPaymentDetailsFacade.requestPaymentDetails(serviceType, qeemah));
        }
        else{
            return new Gson().toJson(sagiaPaymentDetailsFacade.requestPaymentDetails(serviceType));
        }
    }

    @RequestMapping(path = "/businessTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getBusinessTypes(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //SagiaIsicDetService service = (SagiaIsicDetService)appContext.getBean("isicDetService");
        //String originalUrl = service.getoDataService().getRootUrl();
        try {
            //service.getoDataService().setRootUrl(getMockUrl(request));
            return new Gson().toJson(sagiaZqeemahFacade.getBusinessTypeList());
        } finally {
            //service.getoDataService().setRootUrl(originalUrl);
        }
        // return new Gson().toJson(sagiaZqeemahFacade.getBusinessTypeList());
    }

    @RequestMapping(path = "/dropdownsQDF", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsQDF(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //QeemahGeneralQstService service = (QeemahGeneralQstService)appContext.getBean("qeemahGeneralQstService");
        //String originalUrl = service.getoDataService().getRootUrl();
        try {
            //service.getoDataService().setRootUrl(getMockUrl(request));
            return new Gson().toJson(sagiaZqeemah2Facade.getGeneralQst());
        } finally {
            //service.getoDataService().setRootUrl(originalUrl);
        }
        // return new Gson().toJson(sagiaZqeemah2Facade.getGeneralQst());
    }

    @RequestMapping(path = "/isicSectionsQDF/{licenseType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getIsicSectionsQDF(@PathVariable String licenseType, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //ISICDetailsService service = (ISICDetailsService)appContext.getBean("iSICDetailsService");
        //String originalUrl = service.getoDataService().getRootUrl();
        try {
            //service.getoDataService().setRootUrl(getMockUrl(request));
            return new Gson().toJson(sagiaZqeemah2Facade.getISICDetails("Sec", licenseType, ""));
        } finally {
            //service.getoDataService().setRootUrl(originalUrl);
        }
        // return new Gson().toJson(sagiaZqeemah2Facade.getISICDetails("Sec", licenseType, ""));
    }

    @RequestMapping(path = "/isicDivisionsQDF/{licenseType}/{sectionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getIsicDivisionsQDF(@PathVariable String licenseType, @PathVariable String sectionId, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //ISICDetailsService service = (ISICDetailsService)appContext.getBean("iSICDetailsService");
        //String originalUrl = service.getoDataService().getRootUrl();
        try {
            //service.getoDataService().setRootUrl(getMockUrl(request));
            return new Gson().toJson(sagiaZqeemah2Facade.getISICDetails("Div", licenseType, sectionId));
        } finally {
            //service.getoDataService().setRootUrl(originalUrl);
        }
        // return new Gson().toJson(sagiaZqeemah2Facade.getISICDetails("Div", licenseType, sectionId));
    }

    @RequestMapping(path = "/dropdownsQeemah1/cities/{region}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCitiesQeemah1(@PathVariable String region, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //OrgInfoDictionaryService service = (OrgInfoDictionaryService)appContext.getBean("orgInfoDictionaryService");
        //String originalUrl = service.getoDataService().getRootUrl();
        try {
            //service.getoDataService().setRootUrl(getMockUrl(request));
            return new Gson().toJson(sagiaZqeemahFacade.getCitiesList(region));
        } finally {
            //service.getoDataService().setRootUrl(originalUrl);
        }
        // return new Gson().toJson(sagiaZqeemahFacade.getCitiesList(region));
    }

    @RequestMapping(path = "/dropdownsQeemah2/cities/{region}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCitiesQeemah2(@PathVariable String region, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //DropdownValueService service = (DropdownValueService)appContext.getBean("dropdownValueService");
        //String originalUrl = service.getoDataService().getRootUrl();
        try {
            //service.getoDataService().setRootUrl(getMockUrl(request));
            return new Gson().toJson(sagiaZqeemah2Facade.getDropdownValues(null, "CT", region));
        } finally {
            //service.getoDataService().setRootUrl(originalUrl);
        }
        // return new Gson().toJson(sagiaZqeemah2Facade.getDropdownValues(null, "CT", region));
    }

    @RequestMapping(path = "/countryCode/{country}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCountryCode(@PathVariable String country, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //TelecodeService service = (TelecodeService)appContext.getBean("telecodeService");
        //String originalUrl = service.getoDataService().getRootUrl();
        try {
            //service.getoDataService().setRootUrl(getMockUrl(request));
            return new Gson().toJson(sagiaZqeemahFacade.getTeleCode(country));
        } finally {
            //service.getoDataService().setRootUrl(originalUrl);
        }
        // return new Gson().toJson(sagiaZqeemahFacade.getTeleCode(country));
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public String getFiltersForProducts(@RequestBody Map<String, String> filtersList, final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        String top = String.valueOf(BATCH_SIZE_FOR_PRODUCTS_SEARCH);

        String skip;
        if (filtersList.get(BATCHNO) != null && Integer.parseInt(filtersList.get(BATCHNO)) > 0) {
            skip = String.valueOf(((Integer.parseInt(filtersList.get(BATCHNO)) - 1) * BATCH_SIZE_FOR_PRODUCTS_SEARCH) - 1);
        } else {
            skip = "0";
        }
        Collection<ProductData> productsList;
        if ((filtersList.get(USERINPUT)) != null && !filtersList.get(USERINPUT).isEmpty()) {
            if (StringUtils.isNumeric(filtersList.get(USERINPUT))) {
                productsList = sagiaLicenseAmendmentFacade.getAmendProductsListWithId(filtersList.get(USERINPUT), skip, top);
            } else {
                productsList = sagiaLicenseAmendmentFacade.getAmendProductsListWithDescription(filtersList.get(USERINPUT), skip, top);
            }
        } else {
            productsList = sagiaLicenseAmendmentFacade.getAmendProductsList(skip, top);
        }

        if (CollectionUtils.isNotEmpty(productsList)) {
            return new Gson().toJson(productsList);
        }
        return "[]";

    }

    @RequestMapping(path = "/dropdownsQeemah1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsQeemah1(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        Map<String, List<? extends Object>> result = new HashMap<>();
        result.put("legalStatus", sagiaZqeemahFacade.getLegalStatusList());
        result.put("regions", sagiaZqeemahFacade.getRegionsList());
        result.put("countries", sagiaZqeemahFacade.getCountriesList());
        result.put("roles", getRoles());
        result.put("education", getEducation());
        result.put("multinationalCompany", getMultinationalCompany());
        result.put("expectedInvestments", getExpectedInvestmentValues());
        return new Gson().toJson(result);
    }

    // TODO: TMK implement mock
    @RequestMapping(value = "/{shareholderEntityNumber}", method = RequestMethod.GET)
//    @RequireHardLogIn
    @ResponseBody
    public String checkExistingShareholder(@PathVariable("shareholderEntityNumber") String shareholderEntityNumber) throws JsonProcessingException {
        ValidateSareholder validateSareholder = new ValidateSareholder();
        validateSareholder.setBpNumber(StringUtils.leftPad(shareholderEntityNumber, 10, "0"));
        validateSareholder.setBpName("Company name");
        validateSareholder.setBpType("2");
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(validateSareholder);
    }

    @RequestMapping(path = "/dropdownsQeemah2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsQeemah2(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //DropdownValueService service = (DropdownValueService)appContext.getBean("dropdownValueService");
        //String originalUrl = service.getoDataService().getRootUrl();
        try {
            //service.getoDataService().setRootUrl(getMockUrl(request));
            Map<String, Collection> qeemah2Dropdowns = sagiaZqeemah2Facade.getQeemah2Dropdowns();
            qeemah2Dropdowns.put("roles", getRoles());
            qeemah2Dropdowns.put("expectedInvestments", getExpectedInvestmentValues());
            return new Gson().toJson(qeemah2Dropdowns);
        } finally {
            //service.getoDataService().setRootUrl(originalUrl);
        }
    }

    @RequestMapping(path = "/stockMarketQuestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getStockMarketQuestions(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        return new ResponseEntity<>(sagiaZqeemah2Facade.getFinancialQuestions(), HttpStatus.OK);
    }

    @RequestMapping(value = "/isic/{licenseType}", method = RequestMethod.GET)
    @ResponseBody
    public Map getIsic(@PathVariable String licenseType, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        // id="sagiaIsicSectionService" class="com.sap.ibso.eservices.sagiaservices.services.isic.SectionService"
        // id="sagiaIsicDivisionService" class="com.sap.ibso.eservices.sagiaservices.services.isic.DivisionService"
        // id="sagiaIsicGroupService" class="com.sap.ibso.eservices.sagiaservices.services.isic.GroupService"
        // id="sagiaIsicClassService" class="com.sap.ibso.eservices.sagiaservices.services.isic.ClassService"
        // id="sagiaIsicBranchService" class="com.sap.ibso.eservices.sagiaservices.services.isic.BranchService"
        // id="sagiaIsicActivityService" class="com.sap.ibso.eservices.sagiaservices.services.isic.ActivityService"
//        SecurityContextHolder.getContext().getAuthentication()
//        SectionService service = (SectionService)appContext.getBean("sagiaIsicSectionService");
//        String originalUrl = service.getoDataService().getRootUrl();
//        try {
//            service.getoDataService().setRootUrl(getMockUrl(request));
            return sagiaIsicFacade.getIsic(licenseType);
//        } finally {
//            service.getoDataService().setRootUrl(originalUrl);
//        }
        // return sagiaIsicFacade.getIsic(licenseType);
    }

//    private String getMockUrl(final HttpServletRequest request) {
//        URI contextUri = null;
//        try {
//            URI requestUri = new URI(request.getRequestURL().toString());
//            contextUri = new URI(requestUri.getScheme(),
//                    requestUri.getAuthority(),
//                    MOCK_SERVICE_PATH,
//                    null,
//                    null);
//        } catch (URISyntaxException e) {
//            LOGGER.warn(e.getMessage(),e);
//        }
//        return contextUri != null ? contextUri.toString() : null;
//    }

    @RequestMapping(value = "/license-apply", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity licenseApply(HttpServletRequest request){
        throw new SagiaCRMException("This is a simulation, saving is not allowed");
    }

    @RequestMapping(value = "/license-apply", method = RequestMethod.GET)
    public String licenseApply(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException {
        model.addAttribute("sagiaApplyReviewForm", new SagiaApplyReviewForm());
        model.addAttribute("sagiaLicenseApplyForm", new ProfileCompanyData());
        model.addAttribute("MIGS_Session_JS", configurationService.getConfiguration().getString(MIGS_SESSION_URL));
        model.addAttribute("basicOrganizationInformation", new BasicOrganizationInformation());
        model.addAttribute("organizationInformationExtended", new OrganizationInformation());
        model.addAttribute("licenseTypes", sagiaZqeemahFacade.getLicenseTypes());
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute("draftExists", sagiaDraftFacade.isJsonDraftExists(SAGIA_LICENSE_APPLY_DRAFT));
        model.addAttribute("activeTab", "entityInformation");
        model.addAttribute("controllerUrl", "/simulator");

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_APPLY_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_APPLY_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }

    private List<DropdownValue> getRoles() {
        List<DropdownValue> roles = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        roles.add(createRole("1", messageSource.getMessage("licenseApply.role.Agent", null, locale)));
        roles.add(createRole("2", messageSource.getMessage("licenseApply.role.Employee", null, locale)));
        roles.add(createRole("3", messageSource.getMessage("licenseApply.role.Investor", null, locale)));
        roles.add(createRole("4", messageSource.getMessage("licenseApply.role.GM", null, locale)));
        return roles;
    }

    private class ExpectedInvestment {
        private String key;
        private String value;

        ExpectedInvestment(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    private List<ExpectedInvestment> getExpectedInvestmentValues() {
        List<ExpectedInvestment> expectedInvestments = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        expectedInvestments.add(new ExpectedInvestment("LT1", messageSource.getMessage("license.apply.expectedinvestment.LT1", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("1-5", messageSource.getMessage("license.apply.expectedinvestment.1to5", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("5-10", messageSource.getMessage("license.apply.expectedinvestment.5to10", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("10-25", messageSource.getMessage("license.apply.expectedinvestment.10to25", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("25-50", messageSource.getMessage("license.apply.expectedinvestment.25to50", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("50-100", messageSource.getMessage("license.apply.expectedinvestment.50to100", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("100-500", messageSource.getMessage("license.apply.expectedinvestment.100to500", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("500-1000", messageSource.getMessage("license.apply.expectedinvestment.500to1000", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("GT1000", messageSource.getMessage("license.apply.expectedinvestment.GT1000", null, locale)));
        return expectedInvestments;
    }

    private DropdownValue createRole(String role, String roleText) {
        DropdownValue result = new DropdownValue();
        result.setRole(role);
        result.setRoleText(roleText);
        return result;
    }

    private List<DropdownValue> getEducation() {
        List<DropdownValue> education = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        education.add(createEducation("0001", messageSource.getMessage("licenseApply.education.HighSchool", null, locale)));
        education.add(createEducation("0002", messageSource.getMessage("licenseApply.education.Diploma", null, locale)));
        education.add(createEducation("0003", messageSource.getMessage("licenseApply.education.BS", null, locale)));
        education.add(createEducation("0004", messageSource.getMessage("licenseApply.education.Master", null, locale)));
        education.add(createEducation("0005", messageSource.getMessage("licenseApply.education.PhD", null, locale)));
        education.add(createEducation("0006", messageSource.getMessage("licenseApply.education.Professor", null, locale)));
        return education;
    }

    private DropdownValue createEducation(String education, String educationText) {
        DropdownValue result = new DropdownValue();
        result.setEducation(education);
        result.setEducationText(educationText);
        return result;
    }

    private List<DropdownValue> getMultinationalCompany() {
        List<DropdownValue> multinationalCompany = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        multinationalCompany.add(createMultinationalCompany("1", messageSource.getMessage("licenseApply.multinationalCompany.Yes", null, locale)));
        multinationalCompany.add(createMultinationalCompany("2", messageSource.getMessage("licenseApply.multinationalCompany.No", null, locale)));
        return multinationalCompany;
    }

    private DropdownValue createMultinationalCompany(String multinationalCompany, String multinationalCompanyText) {
        DropdownValue result = new DropdownValue();
        result.setMultinationalCompany(multinationalCompany);
        result.setMultinationalCompanyText(multinationalCompanyText);
        return result;
    }
}
