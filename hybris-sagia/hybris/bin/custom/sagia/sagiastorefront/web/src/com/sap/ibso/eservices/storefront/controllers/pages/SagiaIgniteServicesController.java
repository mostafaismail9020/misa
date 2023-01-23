package com.sap.ibso.eservices.storefront.controllers.pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.sagiaservices.data.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GetTextData;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.CreateIgniteServiceForm;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.localization.Localization;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is the controller for Sagia Government Services.
 */

@Controller
@RequestMapping(value = "/services/ignite")
public class SagiaIgniteServicesController extends SagiaAbstractPageController {
    private static final Logger LOG = Logger.getLogger(SagiaIgniteServicesController.class);
    private static final String SAGIA_IGNITE_SERVICES_CMS_PAGE = "sagia-igniteServices";
    private static final String SAGIA_CREATE_IGNITE_SERVICES_CMS_PAGE = "sagia-createIgniteServices";
    private static final String ENTITY_NAME = "GovtServices";
    private static final String UTF_8 = "UTF-8";
    private static final String SERVICE_NAME = "serviceName";
    private static final String CREATE_IGNITE_SERVICE = "createIgniteServices";
    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;
    @Resource
    private SagiaIgniteCategoryFacade sagiaIgniteCategoryFacade;

    @Resource
    private SagiaGovtServiceFacade sagiaGovtServiceFacade;

    @Resource
    private Validator createIgniteServiceValidator;

    @Resource
    private SessionService sessionService;
    @Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;								   

    @Autowired
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private SagiaDraftFacade sagiaDraftFacade;

    /**
     * Displays the services page with no data.
     * @param model
     * @return empty government services page.
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showServices(final Model model) throws CMSItemNotFoundException {
        return getView(model, SAGIA_IGNITE_SERVICES_CMS_PAGE);
    }

    /**
     * Gets all the service instances and info section data from CRM.
     * @param categoryUrl
     * @param serviceUrl
     * @param model
     * @return sagia government services page.
     * @throws CMSItemNotFoundException exception
     * @throws UnsupportedEncodingException exception
     */
    @RequestMapping(value = "/{categoryUrl}/{serviceUrl}", method = RequestMethod.GET)
    @RequireHardLogIn
    public String serviceDetails(@PathVariable(value = "categoryUrl", required = false) String categoryUrl,
                                 @PathVariable(value = "serviceUrl") String serviceUrl,
                                 @RequestParam(value = "srID", required = false) String srID,
                                 final Model model, final HttpServletRequest request) throws CMSItemNotFoundException {
        if ("ZMOCI_22".equals(serviceUrl)) {
            return REDIRECT_PREFIX + "/legalconsultations/";
        }
        Collection<SagiaCRMIgniteServiceData> serviceList = sagiaIgniteCategoryFacade.getIgniteServicesByCategory(serviceUrl);
        if (CollectionUtils.isNotEmpty(serviceList)) {
            String sagiaCRMIgniteServiceDataId = serviceList.iterator().next().getSrID();
            if(StringUtils.isNotEmpty(sagiaCRMIgniteServiceDataId)) {
                SagiaCRMIgniteService service = sagiaIgniteCategoryFacade.getIgniteServiceById(sagiaCRMIgniteServiceDataId);
                List<ContentHDRData> attachments = service.getAttachments();
                List<GetTextData> messages = service.getGovtServicesToTextNav();
                model.addAttribute("attachments", attachments);
                model.addAttribute("messages", messages);
            }
            model.addAttribute("serviceList", serviceList);
        }
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute("categoryUrl", categoryUrl);
        model.addAttribute("infoData", sagiaIgniteCategoryFacade.getInfoData());
        model.addAttribute("srID", srID != null? srID : 0);

        addServiceNameAndDescription(model, request.getServletPath());
        if (CollectionUtils.isNotEmpty(serviceList)) {
            model.addAttribute("firstServiceId", Iterables.get(serviceList, 0).getSrID());
        }
        SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(serviceUrl);
        model.addAttribute("sagiaService", sagiaService);
        return getView(model, SAGIA_IGNITE_SERVICES_CMS_PAGE);
    }

    private void addServiceNameAndDescription(Model model, String servletPath) {
        if(servletPath.indexOf('_') >= 0 && servletPath.length() > servletPath.indexOf('_') + 1) {
            model.addAttribute("serviceDescription", getServiceDescription(servletPath.substring(servletPath.lastIndexOf('/') + 1)));
            model.addAttribute(SERVICE_NAME, getServiceName(servletPath.substring(servletPath.lastIndexOf('/') + 1)));
        }
    }


    /**
     * Get the latest service created
     */
    @RequestMapping(value = "/{categoryUrl}/{serviceUrl}/latest", method = RequestMethod.GET)
    @RequireHardLogIn
    public ResponseEntity getLatestServiceCreated(@PathVariable(value = "categoryUrl", required = false) String categoryUrl,
                                 @PathVariable(value = "serviceUrl") String serviceUrl,
                                 final Model model)  {
        Collection<SagiaCRMIgniteServiceData> serviceList = sagiaIgniteCategoryFacade.getIgniteServicesByCategory(serviceUrl);
        SagiaCRMIgniteServiceData latestIgniteService = serviceList.stream().findFirst().orElse(null);
        return new ResponseEntity<>(latestIgniteService, HttpStatus.OK);
    }

    /**
     * Get a service instance by ID from CRM.
     * @param serviceId
     * @return JSON object containing service data.
     */
    @RequestMapping(value = "/{categoryUrl}/{serviceUrl}/{serviceId}")
    @ResponseBody
    @RequireHardLogIn
    public String getIgniteServiceById(@PathVariable("serviceId") String serviceId) {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(sagiaIgniteCategoryFacade.getIgniteServiceById(serviceId));
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Gets the list of files to be uploaded from CRM.
     * @param model
     * @param srID
     * @param serviceName
     * @param categoryUrl
     * @param serviceUrl
     * @param createIgniteService
     * @return government service creation page.
     * @throws CMSItemNotFoundException exception
     * @throws UnsupportedEncodingException exception
     */
    @RequestMapping(value = "/{categoryUrl}/{serviceUrl}/create", method = RequestMethod.GET)
    @RequireHardLogIn
    public String createIgniteServices(Model model,
                                     @RequestParam(value = "srID", required = false) String srID,
                                     @RequestParam(value = SERVICE_NAME, required = false) String serviceName,
                                     @PathVariable("categoryUrl") String categoryUrl,
                                     @PathVariable("serviceUrl") String serviceUrl,
                                     @ModelAttribute(CREATE_IGNITE_SERVICE) CreateIgniteServiceForm createIgniteService
    ) throws CMSItemNotFoundException, UnsupportedEncodingException {
        model.addAttribute("srID", srID);
        model.addAttribute("categoryUrl", categoryUrl);
        model.addAttribute("serviceUrl", serviceUrl);
        if(StringUtils.isNotEmpty(serviceName)) {
            model.addAttribute(SERVICE_NAME, URLDecoder.decode(serviceName, UTF_8));
        }
        model.addAttribute("infoData", sagiaIgniteCategoryFacade.getInfoData());

        if (sessionService.getAttribute(CREATE_IGNITE_SERVICE) != null) {
            createIgniteService = sessionService.getAttribute(CREATE_IGNITE_SERVICE);
            model.addAttribute("hasErrors", "true");
            model.addAttribute("consent", createIgniteService.isConsentBox());
        }
		SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(serviceUrl);        
        model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());

        createIgniteService.setDocumentsToUpload(sagiaIgniteCategoryFacade.getFilesToBeUploaded(serviceUrl));

        if (CollectionUtils.isNotEmpty(createIgniteService.getFileText()) && CollectionUtils.isNotEmpty(createIgniteService.getDocumentsToUpload())) {
            int index = 0;
            for (CRMIgniteServiceUploadData uploadData : createIgniteService.getDocumentsToUpload()) {
                uploadData.setFileText(createIgniteService.getFileText().get(index++));
            }
        }
        model.addAttribute(CREATE_IGNITE_SERVICE, createIgniteService);

        boolean draftExists = sagiaDraftFacade.isDraftExists(serviceUrl);
        model.addAttribute("draftExists", draftExists);
        model.addAttribute("serviceId", serviceUrl);

        return getView(model, SAGIA_CREATE_IGNITE_SERVICES_CMS_PAGE);
    }

    /**
     * Creates a new service instance in CRM, validates the user input and keeps the files uploaded in case the validation fails.
     * @param request
     * @param createIgniteService
     * @param result
     * @param redirectAttributes
     * @return same page if valdiation fails. Otherwise government services page.
     * @throws UnsupportedEncodingException exception
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public String createIgniteService(final HttpServletRequest request, @ModelAttribute(CREATE_IGNITE_SERVICE) final CreateIgniteServiceForm createIgniteService,
                                    final BindingResult result, final RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        keepPreviousUploadedFiles(createIgniteService);
        createIgniteServiceValidator.validate(createIgniteService, result);

        if (!result.hasErrors()) {
            redirectAttributes.addAttribute(SERVICE_NAME, URLDecoder.decode(createIgniteService.getServiceName(), UTF_8));
            sagiaGovtServiceFacade.createGovtService(populateForm(createIgniteService));
            handleTermsAndConditionsAcceptance(createIgniteService.getCategoryUrl());
            sessionService.removeAttribute(CREATE_IGNITE_SERVICE);
            redirectAttributes.addFlashAttribute("requestFeedback", "true");
            return REDIRECT_PREFIX + "/services/ignite/" + createIgniteService.getCategoryUrl() + "/" + createIgniteService.getServiceType() + "/create";
        } else {
            GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, Localization.getLocalizedString("create.igniteServices." + result.getFieldError().getField()));
            redirectAttributes.addAttribute(SERVICE_NAME, URLEncoder.encode(createIgniteService.getServiceName(), UTF_8));
            sessionService.setAttribute(CREATE_IGNITE_SERVICE, createIgniteService);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createIgniteService", result);
            return REDIRECT_PREFIX + "/services/ignite/" + createIgniteService.getCategoryUrl() + "/" + createIgniteService.getServiceType() + "/create";
        }
    }


    private void keepPreviousUploadedFiles(@ModelAttribute(CREATE_IGNITE_SERVICE) CreateIgniteServiceForm createIgniteService) {
        CreateIgniteServiceForm sessionForm = sessionService.getAttribute(CREATE_IGNITE_SERVICE);

        if (sessionForm != null && CollectionUtils.isNotEmpty(sessionForm.getFiles())) {
            int index = 0;
            for (MultipartFile file : createIgniteService.getFiles()) {
                if (file.getSize() <= 0 && sessionForm.getFiles().get(index).getSize() > 0) {
                    createIgniteService.getFiles().set(index, sessionForm.getFiles().get(index));
                }
                index++;
            }
        }

        if (CollectionUtils.isNotEmpty(createIgniteService.getFiles())) {
            int index = 0;
            for (MultipartFile file : createIgniteService.getFiles()) {
                createIgniteService.getFileText().set(index++, file.getOriginalFilename().toUpperCase());
            }
        }
    }

    private void handleTermsAndConditionsAcceptance(String category){
        TermsAndConditionsAcceptanceEventEnum event;

        switch (category){
            case "interiorAndPassport":
                event = TermsAndConditionsAcceptanceEventEnum.INTERIOR_PASSPORT;
                break;
            case "labour":
                event = TermsAndConditionsAcceptanceEventEnum.LABOUR;
                break;
            case "interiorAndRecruitment":
                event = TermsAndConditionsAcceptanceEventEnum.INTERIOR_RECRUITMENT;
                break;
            case "commerceAndIndustry":
                event = TermsAndConditionsAcceptanceEventEnum.COMMERCE_INDUSTRY;
                break;
            case "igniteOperational":
                event = TermsAndConditionsAcceptanceEventEnum.IGNITE_OPERATIONS;
                break;
            case "ignitePermission":
                event = TermsAndConditionsAcceptanceEventEnum.IGNITE_PERMISSIONS;
                break;
            default:
                event = TermsAndConditionsAcceptanceEventEnum.INTERIOR_PASSPORT;
                break;
        }
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(),event);
    }

    private SagiaGovtServiceFileUpload populateForm(CreateIgniteServiceForm createIgniteService) {
        List<MultipartFile> files = createIgniteService.getFiles();

        SagiaGovtServiceFileUpload sagiaGovtServiceFileUpload = new SagiaGovtServiceFileUpload();

        if (CollectionUtils.isEmpty(files)) {
            return sagiaGovtServiceFileUpload;
        }
        int index = 0;
        List<GovtServicesToUploadNav> attachments = new ArrayList<>();

        for (MultipartFile file : files) {
            GovtServicesToUploadNav govtFile = new GovtServicesToUploadNav();
            if (file.getSize() > 0) {
                govtFile.setMimeType(file.getContentType());
                govtFile.setFilename(file.getOriginalFilename());
                govtFile.setDockeyID(createIgniteService.getDockeyID().get(index++));
                try {
                    govtFile.setFileContString(Base64.encode(file.getBytes()));
                } catch (IOException e) {
                    LOG.error("Content could not be parsed for file: " + file.getOriginalFilename(), e);
                }

                attachments.add(govtFile);
            }
        }
        sagiaGovtServiceFileUpload.setGovtServicesToUploadNav(attachments);
        sagiaGovtServiceFileUpload.setMinistryType(createIgniteService.getMinistryType());
        sagiaGovtServiceFileUpload.setServiceType(createIgniteService.getServiceType());

        return sagiaGovtServiceFileUpload;
    }

    private String getView(Model model, String page) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(page));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(page));
        return getViewForPage(model);
    }
}
