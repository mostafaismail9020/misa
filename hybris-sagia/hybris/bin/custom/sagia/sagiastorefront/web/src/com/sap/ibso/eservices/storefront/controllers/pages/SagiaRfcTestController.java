package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.core.model.ServiceTypeCategoryModel;
import com.sap.ibso.eservices.core.model.ServiceTypeModel;
import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.sagia.services.SagiaServiceTypeCategoryService;
import com.sap.ibso.eservices.core.sagia.services.SagiaServiceTypeService;
import com.sap.ibso.eservices.facades.data.AccountManagerData;
import com.sap.ibso.eservices.facades.sagia.SagiaAccountManagerFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaServiceRequestFacade;
import com.sap.ibso.eservices.sagiaservices.auth.CredentialVerificationService;
import com.sap.ibso.eservices.sagiaservices.company.CompanyInformationService;
import com.sap.ibso.eservices.sagiaservices.data.company.BasicCompanyData;
import com.sap.ibso.eservices.sagiaservices.data.duration.AverageProcessingTimeData;
import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationData;
import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationItemData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.document.DocumentService;
import com.sap.ibso.eservices.sagiaservices.duration.AverageProcessingTimeService;
import com.sap.ibso.eservices.sagiaservices.feedback.FeedbackService;
import com.sap.ibso.eservices.sagiaservices.license.PaymentAssignmentService;
import com.sap.ibso.eservices.sagiaservices.overview.ServiceRequestsOverviewService;
import com.sap.ibso.eservices.sagiaservices.overview.data.ServiceRequestData;
import com.sap.ibso.eservices.sagiaservices.overview.data.ServiceRequestsOverviewData;
import com.sap.ibso.eservices.sagiaservices.price.PriceSimulationService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaIsicDetPsService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaProdPsService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.ZQeemahService;
import com.sap.ibso.eservices.sagiaservices.services.license.info.LicenseInformationService;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.sap.core.configuration.model.SAPConfigurationModel;
import de.hybris.platform.sap.core.configuration.model.SAPRFCDestinationModel;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Provides request mappings to quickly "manually" test (mostly) RFC-based service calls.
 */
@Controller
@RequestMapping(value = "/manual-test")
public class SagiaRfcTestController
{
    private static final String REDIRECT_LOGIN = "redirect:/login";
    @Resource(name = "baseStoreService")
    private BaseStoreService baseStoreService;

    @Resource(name = "sagiaEServicesDocumentService")
    private DocumentService documentService;

    @Resource(name = "zQeemahService")
    private ZQeemahService zQeemahService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sagiaEServicesAverageProcessingTimeService")
    private AverageProcessingTimeService averageProcessingTimeService;

    @Resource(name = "sagiaEServicesFeedbackService")
    private FeedbackService feedbackService;

    @Resource(name="sagiaEServicesPriceSimulationService")
    private PriceSimulationService priceSimulationService;

    @Resource(name="sagiaEServicesPaymentAssignmentService")
    private PaymentAssignmentService paymentAssignmentService;

    @Resource(name = "isicDetPsService")
    private SagiaIsicDetPsService isicDetPsService;

    @Resource(name = "prodPsService")
    private SagiaProdPsService prodPsService;

    @Resource(name="licenseInformationService")
    private LicenseInformationService licenseInformationService;

    @Resource(name="sessionService")
    private SessionService sessionService;

    @Resource(name="crmInvestorApplicantCodeGenerator")
    private KeyGenerator applicantCodeGenerator;

    @Resource(name = "sagiaEServicesCompanyInformationService")
    private CompanyInformationService companyInformationService;

    @Resource(name="sagiaEServicesServiceRequestsOverviewService")
    private ServiceRequestsOverviewService serviceRequestsOverviewService;

    @Resource(name = "sagiaServiceRequestFacade")
    private SagiaServiceRequestFacade serviceRequestFacade;

    @Resource(name = "sagiaServiceTypeService")
    private SagiaServiceTypeService serviceTypeService;

    @Resource(name = "sagiaServiceTypeCategoryService")
    private SagiaServiceTypeCategoryService serviceTypeCategoryService;

    @Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;

    @Resource(name = "sagiaAccountManagerFacade")
    private SagiaAccountManagerFacade accountManagerFacade;

    @Resource(name = "credentialVerificationService")
    private CredentialVerificationService credentialVerificationService;

    private static final Logger LOGGER = LogManager.getLogger(SagiaRfcTestController.class);

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public void getPdfDocument(final Model model, HttpServletRequest request, final HttpServletResponse response)
    {
        LOGGER.info("TM Test!");

        BaseStoreModel baseStore = baseStoreService.getCurrentBaseStore();

        SAPConfigurationModel configuration = baseStore.getSAPConfiguration();
        String name = configuration.getCore_name();
        SAPRFCDestinationModel destination = configuration.getSAPRFCDestination();

        LOGGER.info(name);
        LOGGER.info(destination);
        LOGGER.info(destination.getRfcDestinationName());

        LOGGER.info("---");

        //byte[] pdf = documentService.getDigitalLicense(); // test data: entity ID 607060
        //byte[] pdf = documentService.getWarningLetter("526299"); // test data: entity ID 609775
        //byte[] pdf = documentService.getNotificationNotes(); // test data: entity ID 600854
        //byte[] pdf = documentService.getSadadSalesOrderInvoice("14009464"); // test data: entity ID 601847
        // byte[] pdf = documentService.getSadadServiceRequestInvoice("20000490"); // test data: entity ID 607060
        //byte[] pdf = documentService.getSadadSalesOrderInvoice("13020326"); // test data: entity ID 613392
        byte[] pdf = documentService.getSadadServiceRequestInvoice("20000606"); // test data: entity ID 613392
        //byte[] pdf = documentService.getSadadServiceRequestInvoice("13020326"); // test data: entity ID 613392
        //byte[] pdf = documentService.getSadadSalesOrderInvoice("20000606"); // test data: entity ID 613392
        // 20000606 | 13020326
        // 13020413 | 600314

        //byte[] pdf = documentService.getSadadSalesOrderInvoice("13020413"); // test data: entity ID 600314
        // byte[] pdf = documentService.getSadadServiceRequestInvoice("20000896"); // test data: entity ID 600314

        LOGGER.info("PDF byte array length: " + pdf.length);

        if (pdf.length == 0)
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setHeader("Content-Disposition", "inline;filename=myLicense.pdf");
        response.setContentType("application/pdf");
        response.setDateHeader("Expires", -1);
        try
        {
            response.getOutputStream().write(pdf);
            response.getOutputStream().flush();
        } catch (final IOException e)
        {
            LOGGER.info("Unable to write invoice PDF on output stream" + e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String testOData()
    {
        Optional<ApplicationStatusData> data = zQeemahService.getApplicationStatus();
        if (data.isPresent())
        {
            ApplicationStatusData applicationStatusData = data.get();
            LOGGER.info("Application status: " + applicationStatusData.getInvId() + ", " + applicationStatusData.getStatus() + ", " + applicationStatusData.getStatusDesc());
        }
        else
        {
            LOGGER.info("No application status information available for current user.");
        }

        return REDIRECT_LOGIN;
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public String testInvestorId()
    {
        boolean successful = zQeemahService.createApplicantReferenceId();

        if (successful)
        {
            LOGGER.info("New applicant reference ID: " + ((CustomerModel) userService.getCurrentUser()).getApplicantReferenceID());
        }
        else
        {
            LOGGER.info("Existing applicant reference ID: " + ((CustomerModel) userService.getCurrentUser()).getApplicantReferenceID());
        }


        return REDIRECT_LOGIN;
    }

    @RequestMapping(value = "/uuid", method = RequestMethod.GET)
    public String testUuid()
    {
        LOGGER.info(UUID.randomUUID().toString());
        LOGGER.info(UUID.randomUUID().toString());
        LOGGER.info(UUID.randomUUID().toString());

        return REDIRECT_LOGIN;
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public void testAccountManagerService()
    {
        AccountManagerData accountManagerData = accountManagerFacade.getAccountManagerData();

        LOGGER.info(accountManagerData.getTitle());
        LOGGER.info(accountManagerData.getFirstName());
        LOGGER.info(accountManagerData.getLastName());
        LOGGER.info(accountManagerData.getEmail());
        LOGGER.info(accountManagerData.getPhoneNumber());
        LOGGER.info(accountManagerData.getMobileNumber());

        LOGGER.info("---");
    }

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public String testAverageProcessingTime()
    {
        List<String> serviceTypes = Arrays.asList("ZSR1", "ZSR2", "ZSR3", "ZSR4", "ZSR5", "ZSR6", "ZSR7", "ZSR8", "ZSR9", "ZSRF",
                "ZS10", "ZS11", "ZS12", "ZS13", "ZS14", "ZS15", "ZS16", "ZS17", "ZS20", "ZS21", "ZSVR", "ZRVR");

        for (String serviceType : serviceTypes)
        {
            AverageProcessingTimeData averageProcessingTimeData = averageProcessingTimeService.getAverageProcessingTimeData(serviceType);
            LOGGER.info(averageProcessingTimeData.getServiceType() + ": " + averageProcessingTimeData.getDays() + " Days " +
                    averageProcessingTimeData.getHours() + " Hours " + averageProcessingTimeData.getMinutes() + " Minutes " +
                    averageProcessingTimeData.getSeconds() + " Seconds");
        }

        return REDIRECT_LOGIN;
    }

    //Roman test method
    @RequestMapping(value = "/isictest", method = RequestMethod.GET)
    public String isictest()
    {
        isicDetPsService.deleteIsicDetPs("6000003629");
        prodPsService.deleteProdPs("6000003629");
        return REDIRECT_LOGIN;
    }

    @RequestMapping(value = "/rating", method = RequestMethod.GET)
    public String testRating()
    {
        // Test Data service IDs: 40000222 to 40000229 (in series)
        feedbackService.submitUserExperienceFeedback("40000222", FeedbackService.Rating.THREE_STARS, "My first test comment", "someemail@something.com");

        return REDIRECT_LOGIN;
    }

    @RequestMapping(value = "/price", method = RequestMethod.GET)
    public String testPriceSimulation()
    {

        PriceSimulationData priceSimulationData = priceSimulationService.getPriceSimulationData("ZS16"); // entity ID: 626739
        PriceSimulationData priceSimulationDataApply = priceSimulationService.getPriceSimulationData("ZSGS","QEE1");

        LOGGER.info("Number of priced e-service(s) for Renewal and Amendment: " + priceSimulationData.getPriceSimulationItems().size());

        for (PriceSimulationItemData item : priceSimulationData.getPriceSimulationItems())
        {
            LOGGER.info(item.getServiceName() + " " + item.getNetValue() + " " + item.getCurrencyIso());
        }

        LOGGER.info("Number of priced e-service(s) for License Apply " + priceSimulationDataApply.getPriceSimulationItems().size());

        for (PriceSimulationItemData item : priceSimulationDataApply.getPriceSimulationItems())
        {
            LOGGER.info(item.getServiceName() + " " + item.getNetValue() + " " + item.getCurrencyIso());
        }

        return REDIRECT_LOGIN;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String testPaymentAssignment()
    {
        // success, entity ID 627387
        paymentAssignmentService.assignPaymentInformation("20001730", PaymentAssignmentService.ServiceType.QEEMAH_1, "12132121");

        // failure, entity ID 627387
        paymentAssignmentService.assignPaymentInformation("20001749", PaymentAssignmentService.ServiceType.QEEMAH_1, "1122334455");

        return REDIRECT_LOGIN;
    }

    @RequestMapping(value = "/hasLicense", method = RequestMethod.GET)
    public String testHasLicense()
    {
        boolean hasLicense = licenseInformationService.hasLicense();
        LOGGER.info("Current user has license: " + hasLicense);

        return REDIRECT_LOGIN;
    }

    @RequireHardLogIn
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String testSessionAttribute(@RequestParam(value = "content") String content)
    {
        Session session = sessionService.getCurrentSession();
        LOGGER.info("Session Identifier:" + session.getSessionId());

        String cachedContent = session.getAttribute("sagia.test.content");

        if (cachedContent == null)
        {
            LOGGER.info("Not yet cached: " + content);
            session.setAttribute("sagia.test.content", content);
        }
        else
        {
            LOGGER.info("Cached: " + cachedContent);
        }

        return REDIRECT_LOGIN;
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public void testApplicantCodeGenerator()
    {
        String applicantCode = (String) applicantCodeGenerator.generate();
        LOGGER.info("Applicant code: " + applicantCode);
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public void testBasicCompanyInformation()
    {
        BasicCompanyData data = companyInformationService.getBasicInformation();

        LOGGER.info("Entity name in English: " + data.getCompanyNameInEnglish());
        LOGGER.info("Entity name in Arabic: " + data.getCompanyNameInArabic());
        LOGGER.info("Business email: " + data.getBusinessEmailAddress());
        LOGGER.info("Legal status: " + data.getLegalStatus());
        LOGGER.info("Country: " + data.getCountry());
        LOGGER.info("Region: " + data.getRegion());
        LOGGER.info("City: " + data.getCity());
        LOGGER.info("Capital: " + data.getCapitalValue() + " Currency: " + data.getCurrencyIso());
        LOGGER.info("ISIN code: " + data.getIsinCode());
        LOGGER.info("ISIC section: " + data.getIsicSection());
        LOGGER.info("ISIC division: " + data.getIsicDevision());
    }

    @RequestMapping(value = "/sr-facade", method = RequestMethod.GET)
    public void testServiceRequestFacade()
    {
        List<com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData> serviceRequests = serviceRequestFacade.getServiceRequestsData();
        logServiceRequestData(serviceRequests);
    }

    private void logServiceRequestData(List<com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData> serviceRequests)
    {
        LOGGER.info("counter: request number, date, status, category 4, category 3, category 2, category 1, service type, URL path");

        int counter = 1;
        for(com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData serviceRequest : serviceRequests)
        {
            String number = serviceRequest.getRequestNumber();
            SagiaDateData date = serviceRequest.getRequestDate();
            String status = serviceRequest.getStatusDescription();
            String serviceTypeName = serviceRequest.getServiceType();
            String categoryOneName = serviceRequest.getCategoryLevelOne();
            String categoryTwoName = serviceRequest.getCategoryLevelTwo();
            String categoryThreeName = serviceRequest.getCategoryLevelThree();
            String categoryFourName = serviceRequest.getCategoryLevelFour();
            String urlPath = serviceRequest.getUrlPath();

            LOGGER.info(counter + ": " + number + ", " + date.getDateFormatted() + ", " + status + ", 4: " + categoryFourName + ", 3: " + categoryThreeName + ", 2: " + categoryTwoName + ", 1: " + categoryOneName + ", ST: " + serviceTypeName + ", URL path: " + urlPath);
            counter++;
        }
    }

    @RequestMapping(value = "/sr", method = RequestMethod.GET)
    public void testServiceRequestsOverview()
    {
        ServiceRequestsOverviewData overviewData = serviceRequestsOverviewService.getServiceRequests();
        logServiceRequests(overviewData.getServiceRequests());

        ServiceRequestsOverviewData overviewDataInArabic = serviceRequestsOverviewService.translate(overviewData, "ar");
        logServiceRequests(overviewDataInArabic.getServiceRequests());
    }

    private void logServiceRequests(List<ServiceRequestData> serviceRequests)
    {
        int counter = 1;
        for(ServiceRequestData serviceRequest : serviceRequests)
        {
            String number = serviceRequest.getServiceRequestNumber();
            LocalDate date = serviceRequest.getServiceRequestCreationDate();
            String status = serviceRequest.getServiceRequestStatusDescription();
            String serviceTypeName = serviceRequest.getServiceTypeName();
            String categoryOneName = serviceRequest.getCategoryLevelOneName();
            String categoryTwoName = serviceRequest.getCategoryLevelTwoName();
            String categoryThreeName = serviceRequest.getCategoryLevelThreeName();
            String categoryFourName = serviceRequest.getCategoryLevelFourName();

            LOGGER.info(counter + ": " + number + ", " + date + ", " + status + ", " + categoryFourName + ", " + categoryThreeName + ", " + categoryTwoName + ", " + categoryOneName + ", " + serviceTypeName);
            counter++;
        }
    }

    @RequestMapping(value = "/serviceTypes", method = RequestMethod.GET)
    public void testServiceTypeService()
    {
        Map<String, ServiceTypeModel> serviceTypesByCodes = serviceTypeService.getServiceTypesByCodes();
        logServiceTypes(serviceTypesByCodes);

        LOGGER.info("----------");

        serviceTypesByCodes = serviceTypeService.getOverviewRelevantServiceTypesByCodes();
        logServiceTypes(serviceTypesByCodes);

        LOGGER.info("----------");

        Map<String, ServiceTypeCategoryModel> serviceTypeCategoriesByCodes = serviceTypeCategoryService.getServiceTypeCategoriesByCodes();
        logServiceTypeCategories(serviceTypeCategoriesByCodes.values());

    }

    /*
     * Suppress sonar warning (squid:S2864 | "entrySet()" should be iterated when both the key and value are needed
     * The structure is right in the given context.
     */
    @SuppressWarnings({"squid:S2864"})
    private void logServiceTypes(Map<String, ServiceTypeModel> serviceTypesByCodes)
    {
        LOGGER.info("Found service types: " + serviceTypesByCodes.size());

        for(String code : serviceTypesByCodes.keySet())
        {
            ServiceTypeModel serviceType = serviceTypesByCodes.get(code);
            String overview = serviceType.getOverviewRelevant() ? "x" : "-";
            String name = serviceType.getName(Locale.ENGLISH);
            LOGGER.info(code + ", " + overview + ", " + name);

            logServiceTypeCategories(serviceType.getServiceTypeCategories());
        }
    }

    private void logServiceTypeCategories(Collection<ServiceTypeCategoryModel> categories)
    {
        for(ServiceTypeCategoryModel category : categories)
        {
            String code = category.getCode();
            String name = category.getName(Locale.ENGLISH);
            int level = category.getLevel();
            String serviceTypeCode = category.getServiceType().getCode();
            String parent = category.getParentCategory() == null ? "no parent" :  category.getParentCategory().getCode();
            String subCategories = category.getSubCategories() == null || category.getSubCategories().isEmpty() ? "no subcategories" : "subcategories";
            LOGGER.info("Category: " + code + ", " + level + ", " + name + ", " + serviceTypeCode + ", " + parent + ", " + subCategories);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public void testSearchService()
    {
        Map<String, SagiaServiceModel> servicesByCodes = searchService.getAllServicesByCodes();
        LOGGER.info("Sagia service codes:");
        servicesByCodes.keySet().forEach(code -> LOGGER.info(code));
    }

    @RequestMapping(value = "/credential", method = RequestMethod.GET)
    public void testCredentialVerificationService()
    {
        boolean isValid = credentialVerificationService.isValid("613392", "test1234x");
        LOGGER.info("Is valid: " + isValid);
    }
}
