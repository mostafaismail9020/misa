package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.facades.data.FinancialSurveyData;
import com.sap.ibso.eservices.core.sagia.services.SagiaDashboardSectionsService;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.draft.DraftInfo;
import com.sap.ibso.eservices.facades.data.payment.PaymentData;
import com.sap.ibso.eservices.facades.employment.data.EmploymentData;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.facades.sagia.impl.DefaultLicensePrintingFacade;
import com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.preferences.SagiaCustomerPreferencesDTO;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.util.SagiaUtils;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import com.sap.ibso.eservices.storefront.controllers.SagiaConstants;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.UserService;
import com.investsaudi.portal.core.model.ContactTicketModel;

import org.apache.commons.collections.MapUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import de.hybris.platform.commercefacades.product.data.OpportunityData;
import org.springframework.util.StringUtils;
import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;

import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.investsaudi.portal.core.service.InvestSaudiMediaCenterService;

@Controller
@RequestMapping(value = "/dashboard")
public class InvestorDashboardController extends SagiaAbstractPageController {
	
	private static final Logger LOG = Logger.getLogger(InvestorDashboardController.class);
			
    private static final int NUMBER_OF_NEWS = 3;
	private static final String SAGIA_DASHBOARD_CMS_PAGE = "dashboard";
    private static final String SAGIA_FIRST_PAGE_INDEX = "1";
    private static final String REDIRECT_TO_DASHBOARD_WITHOUT_LICENSE = REDIRECT_PREFIX + "/dashboard-without-license";
    private static final String REDIRECT_TO_DASHBOARD_PAGE = REDIRECT_PREFIX + "/dashboard";


    @Resource(name = "sagiaLicensePrintingFacade")
    private DefaultLicensePrintingFacade sagiaLicensePrintingFacade;
    
    @Resource
    private InvestSaudiMediaCenterService investSaudiMediaCenterService;

    @Resource(name = "sagiaLicenseFacade")
    private SagiaLicenseFacade licenseFacade;

    @Resource(name = "sagiaEmploymentFacade")
    private SagiaEmploymentFacade employmentFacade;

    @Resource(name = "sagiaServiceRequestFacade")
    private SagiaServiceRequestFacade serviceRequestFacade;

    @Resource(name = "sagiaComplaintFacade")
    private SagiaComplaintFacade sagiaComplaintFacade;

    @Resource(name = "sagiaAccountManagerFacade")
    private SagiaAccountManagerFacade sagiaAccountManagerFacade;

    @Resource(name = "sagiaPaymentFacade")
    private PaymentFacade sagiaPaymentFacade;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;

    @Resource(name = "ZUI5SagiaFacade")
    private ZUI5SagiaFacade zui5SagiaFacade;

    @Resource(name = "sagiaMediaFacade")
    private SagiaMediaFacade sagiaMediaFacade;

    @Resource(name = "sagiaCustomerFacade")
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Resource(name = "sagiaDashboardSectionsService")
    private SagiaDashboardSectionsService sagiaDashboardSectionsService;

    @Resource(name = "sagiaPaginationFacade")
    private SagiaPaginationFacade sagiaPaginationFacade;

    @Resource(name = "sagiaConfigurationFacade")
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Resource(name = "sagiaDashboardWithoutLicenseFacade")
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

    @Resource(name = "commonI18NService")
    private CommonI18NService commonI18NService;

    @Resource(name = "sagiaAccountFacade")
    private SagiaAccountFacade sagiaAccountFacade;
    
    @Resource
    private InvestSaudiCategoryFacade investSaudiCategoryFacade;
    
    @Resource
    private InvestSaudiProductFacade investSaudiProductFacade;
    
    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "defaultFinancialSurveyFacade")
    private SagiaFinancialSurveyFacade sagiaFinancialSurveyFacade;

    @RequestMapping(value = "/serviceRequests/print/{srId}", method = RequestMethod.GET)
    @RequireHardLogIn
    public void printDocument(final Model model, HttpServletRequest request, final HttpServletResponse response, @PathVariable(name = "srId", required = false) String srId) {
        SagiaUtils.writeByteArray(response, sagiaLicensePrintingFacade.getDocument(srId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/payments")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getPayments() {
        Map<String, Object> responseMap = new HashMap<>();
        List<PaymentData> paymentDataList = sagiaPaymentFacade.getPayments();
        getSessionService().setAttribute("Payments", paymentDataList);
        responseMap.put("PaymentsPagesNumber",
                sagiaPaginationFacade.getPagesNumber(paymentDataList.size(), Double.parseDouble(sagiaConfigurationFacade.getItemsPerPage())));
        responseMap.put("paymentsItemsPerPage", sagiaConfigurationFacade.getItemsPerPage());
        responseMap.put("paymentData",
                sagiaPaginationFacade.getPaymentListForPage(paymentDataList,
                        Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()), paymentDataList.size()));

        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/payments/{itemsPerPage}")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getPayments(@PathVariable int itemsPerPage) {
        Map<String, Object> responseMap = new HashMap<>();
        List<PaymentData> paymentDataList = sagiaPaymentFacade.getPayments();
        getSessionService().setAttribute("Payments", paymentDataList);
        responseMap.put("PaymentsPagesNumber",
                sagiaPaginationFacade.getPagesNumber(paymentDataList.size(), itemsPerPage));
        responseMap.put("paymentsItemsPerPage", itemsPerPage);
        responseMap.put("showItemsPerPage", sagiaConfigurationFacade.getShowItemsPerPage());
        responseMap.put("paymentData",
                sagiaPaginationFacade.getPaymentListForPage(paymentDataList,
                        Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), itemsPerPage, paymentDataList.size()));

        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/licenseAndEmployee")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getLicenceAndEmployeeData() {
        HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
        LicenseRenewalIndicators renewalIndicators = sagiaAccountFacade.getRenewalIndicators(homeHDR);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("renewalIndicators", renewalIndicators);
        LicenseData licenseData = licenseFacade.getLicense(homeHDR);
        if(licenseData == null) {
            throw new SagiaCRMException("no license data");
        }
        List<BranchData> branchDataList = licenseData.getBranches();
        getSessionService().setAttribute("Branches", branchDataList);
        responseMap.put("BranchesPagesNumber", sagiaPaginationFacade.getPagesNumber(branchDataList.size(),
                Double.parseDouble(sagiaConfigurationFacade.getItemsPerPage())));
        responseMap.put("branchesItemsPerPage", sagiaConfigurationFacade.getItemsPerPage());
        branchDataList = sagiaPaginationFacade.getDashboardBranchesListForPage(branchDataList,
                Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()), branchDataList.size());
        licenseData.setBranches(branchDataList);
        responseMap.put("license", licenseData);
        EmploymentData employmentData = employmentFacade.getEmploymentData(homeHDR);
        responseMap.put("employment", employmentData);
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/licenseAndEmployee/{itemsPerPage}")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getLicenceAndEmployeeData(@PathVariable int itemsPerPage) {
        HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
        LicenseRenewalIndicators renewalIndicators = sagiaAccountFacade.getRenewalIndicators(homeHDR);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("renewalIndicators", renewalIndicators);
        LicenseData licenseData = licenseFacade.getLicense(homeHDR);
        if(licenseData == null) {
            throw new SagiaCRMException("no license data");
        }
        List<BranchData> branchDataList = licenseData.getBranches();
        getSessionService().setAttribute("Branches", branchDataList);
        responseMap.put("BranchesPagesNumber", sagiaPaginationFacade.getPagesNumber(branchDataList.size(),
                itemsPerPage));
        responseMap.put("branchesItemsPerPage", itemsPerPage);
        branchDataList = sagiaPaginationFacade.getDashboardBranchesListForPage(branchDataList,
                Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), itemsPerPage, branchDataList.size());
        licenseData.setBranches(branchDataList);
        responseMap.put("license", licenseData);
        EmploymentData employmentData = employmentFacade.getEmploymentData(homeHDR);
        responseMap.put("employment", employmentData);
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accountManager")
    @RequireHardLogIn
    @ResponseBody
    public AccountManagerData getAccountManagerData() {
        return sagiaAccountManagerFacade.getAccountManagerData();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/banners")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, String> getBannerData() {
        Map<String, String> responseMap = new HashMap<>();
        SagiaMediaData pageMedia = sagiaMediaFacade.getSagiaMediaForPageName(SAGIA_DASHBOARD_CMS_PAGE);
        List<MediaData> attachments = pageMedia.getAttachments();
        Optional<MediaData> smallBanner;
        Optional<MediaData> mediumBanner;
        Optional<MediaData> largeBanner;
        Optional<MediaData> extraLargeBanner;
        if("EN".equalsIgnoreCase(commonI18NService.getCurrentLanguage().getIsocode())) {
            smallBanner = attachments.stream().filter(x -> "dashboardInfoSmall".equals(x.getCode())).findFirst();
            mediumBanner = attachments.stream().filter(x -> "dashboardInfoMedium".equals(x.getCode())).findFirst();
            largeBanner = attachments.stream().filter(x -> "dashboardInfoLarge".equals(x.getCode())).findFirst();
            extraLargeBanner = attachments.stream().filter(x -> "dashboardInfoExtraLarge".equals(x.getCode())).findFirst();
        } else {
            smallBanner = attachments.stream().filter(x -> "dashboardInfoSmall-ar".equals(x.getCode())).findFirst();
            mediumBanner = attachments.stream().filter(x -> "dashboardInfoMedium-ar".equals(x.getCode())).findFirst();
            largeBanner = attachments.stream().filter(x -> "dashboardInfoLarge-ar".equals(x.getCode())).findFirst();
            extraLargeBanner = attachments.stream().filter(x -> "dashboardInfoExtraLarge-ar".equals(x.getCode())).findFirst();
        }
        responseMap.put("smallBannerUrl", smallBanner.isPresent() ? smallBanner.get().getUrl() : "");
        responseMap.put("mediumBannerUrl", mediumBanner.isPresent() ? mediumBanner.get().getUrl() : "");
        responseMap.put("largeBannerUrl", largeBanner.isPresent() ? largeBanner.get().getUrl() : "");
        responseMap.put("extraLargeBannerUrl", extraLargeBanner.isPresent() ? extraLargeBanner.get().getUrl() : "");
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/drafts")
    @RequireHardLogIn
    @ResponseBody
    public List<DraftInfo> getDrafts() {
        return sagiaDraftFacade.getDrafts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/serviceRequests")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getServiceRequests() {
        List<ServiceRequestData> serviceRequestsData = serviceRequestFacade.getServiceRequestsData();
        serviceRequestsData.sort(Comparator.comparing(ServiceRequestData::getRequestDate,Comparator.comparing(SagiaDateData::getDate)).reversed());
        Map<String, Object> responseMap = new HashMap<>();
        getSessionService().setAttribute("ServiceRequests", serviceRequestsData);
        responseMap.put("serviceRequestPagesNumber", sagiaPaginationFacade.getPagesNumber(serviceRequestsData.size(), Double.parseDouble(sagiaConfigurationFacade.getItemsPerPage())));
        responseMap.put("serviceRequestsItemsPerPage", sagiaConfigurationFacade.getItemsPerPage());
        responseMap.put("serviceRequestsData", sagiaPaginationFacade.
                getServiceListForPage(serviceRequestsData, Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()), serviceRequestsData.size()));
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/serviceRequests/{itemsPerPage}")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getServiceRequests(@PathVariable int itemsPerPage) {
        List<ServiceRequestData> serviceRequestsData = serviceRequestFacade.getServiceRequestsData();
        serviceRequestsData.sort(Comparator.comparing(ServiceRequestData::getRequestDate,Comparator.comparing(SagiaDateData::getDate)).reversed());
        Map<String, Object> responseMap = new HashMap<>();
        getSessionService().setAttribute("ServiceRequests", serviceRequestsData);
        responseMap.put("serviceRequestPagesNumber", sagiaPaginationFacade.getPagesNumber(serviceRequestsData.size(), itemsPerPage));
        responseMap.put("serviceRequestsItemsPerPage", itemsPerPage);
        responseMap.put("showItemsPerPage", sagiaConfigurationFacade.getShowItemsPerPage());
        responseMap.put("serviceRequestsData", sagiaPaginationFacade.
                getServiceListForPage(serviceRequestsData, Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), itemsPerPage, serviceRequestsData.size()));
        return responseMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tickets")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getTickets() {
        List<TicketData> tickets = sagiaComplaintFacade.getTickets();
        Map<String, Object> responseMap = new HashMap<>();
        getSessionService().setAttribute("Tickets", tickets);
        responseMap.put("TicketsPagesNumber", sagiaPaginationFacade.getPagesNumber(tickets.size(), Double.parseDouble(sagiaConfigurationFacade.getItemsPerPage())));
        responseMap.put("ticketsItemsPerPage", sagiaConfigurationFacade.getItemsPerPage());
        responseMap.put("tickets",
                sagiaPaginationFacade.getTicketListForPage(tickets, Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()),
                        tickets.size()));
        return responseMap;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/financialSurveys")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getFinancialSurveys() {
        List<FinancialSurveyData> financialSurveyList =  sagiaFinancialSurveyFacade.getFinancialSurveyList();
        Map<String, Object> responseMap = new HashMap<>();
        getSessionService().setAttribute("Surveys", financialSurveyList);
        responseMap.put("surveys",financialSurveyList);
        return responseMap;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/tickets/{itemsPerPage}")
    @RequireHardLogIn
    @ResponseBody
    public Map<String, Object> getTickets(@PathVariable int itemsPerPage) {
        List<TicketData> tickets = sagiaComplaintFacade.getTickets();
        Map<String, Object> responseMap = new HashMap<>();
        getSessionService().setAttribute("Tickets", tickets);
        responseMap.put("TicketsPagesNumber", sagiaPaginationFacade.getPagesNumber(tickets.size(), itemsPerPage));
        responseMap.put("ticketsItemsPerPage", itemsPerPage);
        responseMap.put("tickets", sagiaPaginationFacade.getTicketListForPage(tickets, Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), itemsPerPage, tickets.size()));
        return responseMap;
    }

    /**
     * Displays the dashboard page
     *
     * @param model - Model for the view
     * @return - Dashboard URL
     * @throws CMSItemNotFoundException exception
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    public String getDashboard(final Model model) throws CMSItemNotFoundException {
    	sagiaDashboardWithoutLicenseFacade.evictApplicationStatus();
        if (!sagiaDashboardWithoutLicenseFacade.hasLicense()) {
            return REDIRECT_TO_DASHBOARD_WITHOUT_LICENSE;
        }

        MediaData dashboardMedia = sagiaCustomerFacade.getCurrentCustomer().getDashboardMedia();
        if (dashboardMedia != null) {
            model.addAttribute("dashboardBanner", dashboardMedia.getUrl());
        }
        if(sagiaConfigurationFacade.getDashboardBannerUrl() != null){
            model.addAttribute("DashboardBannerUrl", sagiaConfigurationFacade.getDashboardBannerUrl());
        }

        //model.addAttribute("displayTutorial", sagiaCustomerFacade.displayDashboardTutorial());

        CustomerData customerData = sagiaCustomerFacade.getCurrentCustomer();
        if (customerData.getProfilePicture() != null) {
            model.addAttribute("profilePicture", customerData.getProfilePicture().getUrl());
        }
        model.addAttribute("MIGS_Session_JS", configurationService.getConfiguration().getString(SagiaConstants.MIGS_SESSION_URL));
        model.addAttribute("currentCustomerSector", customerData.getSector());
        if (Objects.nonNull(customerData.getSector()) && Objects.nonNull(customerData.getSector().getSectorCode()) 
        		&& !customerData.getSector().getSectorCode().isEmpty()) {
        	model.addAttribute("customerSectorCategory", investSaudiCategoryFacade.getCategoryForCode(customerData.getSector().getSectorCode()));
        	List<OpportunityData> featuredOpportunities = investSaudiProductFacade.getFeaturedOpportunitiesByCategory(3, 
        			customerData.getSector().getSectorCode() );
        	model.addAttribute("featuredOpportunities", featuredOpportunities);
        }
        model.addAttribute("lastNews", investSaudiMediaCenterService.getNews(NUMBER_OF_NEWS));
        
        CustomerModel customerModel = (CustomerModel) userService.getCurrentUser();
        if (null != customerModel) {
        	List<ContactTicketModel> contactTicketList = sagiaCustomerFacade.getUserRaisedOpportunities(customerModel.getUserNameEmail());
        	LOG.debug("-------contactTicketList="+contactTicketList.size());
        	model.addAttribute("userOpportunityTickets", contactTicketList);        
        	model.addAttribute("customerLastLogon", customerModel.getLastSuccessLogin());
        }
        
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_DASHBOARD_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_DASHBOARD_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * controller method, that updates the dashboard picture for current user and redirects to dashboard page.
     *
     * @param file to upload
     * @return redirect to dashboard page
     */
    @RequestMapping(value = "/update-dashboardPic", method = RequestMethod.POST, consumes = "multipart/form-data")
    @RequireHardLogIn
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        sagiaCustomerFacade.updateDashboardPicture(file);
        return REDIRECT_TO_DASHBOARD_PAGE;
    }

	@RequestMapping(value = "/shouldSetNowCompanyPhoto", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	@RequireHardLogIn
	public void displayOptionToSetNowCompanyPhoto(@RequestBody SagiaCustomerPreferencesDTO customerPreferences) {
		sagiaCustomerFacade.shouldDisplayOptionToSetNowCompanyPhoto(customerPreferences.shouldDisplayOptionToSetNowCompanyPhoto());
	}

    /**
     * controller method, that returns the current dashboard sections map of current user.
     *
     * @return profile sections
     */
    @RequestMapping(value = "/sections", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, ArrayList<String>> getProfileSections() {
        if (MapUtils.isEmpty(sagiaDashboardSectionsService.getUserDashboardSections())) {
            sagiaDashboardSectionsService.initializeDashboardSections();
        }
        return sagiaDashboardSectionsService.getUserDashboardSections();
    }
}
