package com.sap.ibso.eservices.storefront.controllers.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.draft.DraftInfo;
import com.sap.ibso.eservices.facades.data.specialservices.*;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.sagiaservices.data.DraftData;
import com.sap.ibso.eservices.sagiaservices.data.DraftJsonData;
import com.sap.ibso.eservices.sagiaservices.data.DraftParameter;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GlobalLicenseCancellation;
import com.sap.ibso.eservices.sagiaservices.services.impl.SpecialServiceAttachmentService;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/special-services")
public class SagiaSpecialServicesController extends SagiaAbstractPageController {
    private static final Logger LOG = Logger.getLogger(SagiaSpecialServicesController.class);
    private static final String SAGIA_SPECIAL_SERVICES_CMS_PAGE = "special-services";
    private static final String SAGIA_SPECIAL_SERVICES_CREATE_CMS_PAGE = "special-services-create";

    private static final String SAGIA_SERVICE_APPLICANTS = "ServiceApplicants";
    private static final String PDF = "application/pdf";
    private static final String ENTITY_NAME = "SPCHECKHISTORYSET";
    private static final String SERVICE_TYPE = "serviceType";
    private static final String SPECIAL_SERVICES = "specialServices";
    private static final String SPECIAL_SERVICE_HEADER = "specialServiceHeader";
    private static final String PROCESSING_TIME = "processingTime";
    private static final String CREATION_ALLOWED = "creationAllowed";
    private static final String SERVICE_PREFIX = "specialServices.";
    private static final String PARAMETERS = "parameters";
    private static final String E0002 = "E0002";
    private static final int STAGE_2 = 2;
    private static final String CANCELSUPPORTLETTER = "cancelLetter";
    private static final String SPECIAL_SERVICE_ERROR_MSG = "specialServiceErrorMsg";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "sagiaSpecialServicesFacade")
    private SagiaSpecialServiceFacade sagiaSpecialServiceFacade;

    @Resource(name = "specialServiceHeaderValidator")
    private Validator specialServiceHeaderValidator;

    @Resource(name = "sagiaLicenseCancellationFacade")
    private SagiaLicenseCancellationFacade sagiaLicenseCancellationFacade;

    @Resource(name = "i18nService")
    private I18NService i18NService;
	
	@Resource(name = "sagiaSearchService")
	private SagiaSearchService searchService;

    @Resource(name = "specialServiceAttachmentService")
    private SpecialServiceAttachmentService specialServiceAttachmentService;

    private final Map<String, String> serviceType = new HashMap<String, String>() {{ //NOSONAR
        put("exit-re-entry-visa", "ZSPLJAWA01");
        put("renewal-of-iqama", "ZSPLJAWA02");
        put("transfer-of-iqama", "ZSPLJAWA03");
        put("final-exit-visa", "ZSPLJAWA04");
    }};

    @Resource(name = "sagiaTermsAndConditionsFacade")
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;

    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:S3776","squid:S134"})
    @RequestMapping(value = {"", "/{type}", "/{type}/display/{srId}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getSpecialServices(@PathVariable("type") String type, @PathVariable(name = "srId", required = false) Integer srId, final Model model) throws CMSItemNotFoundException, IOException {
        String serviceDiscriminator = serviceType.getOrDefault(type, null);
        boolean isCancellationLetter = isCancellationLetterStatusOk();
        if (serviceDiscriminator == null) {
            return "redirect:/404";
        }

        Collection<SpecialService> specialServices = sagiaSpecialServiceFacade.getServices(serviceDiscriminator);
        model.addAttribute(PROCESSING_TIME, averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute(SERVICE_TYPE, type);

        if (specialServices != null && !specialServices.isEmpty()) {
            if (srId != null) {
                // Filter the special services collection for the service request ID
                Optional<SpecialService> specialService = specialServices.stream().filter(service -> srId.equals(service.getId())).findFirst();

                if (specialService.isPresent()) {
                    model.addAttribute(SPECIAL_SERVICE_HEADER, sagiaSpecialServiceFacade.getService(srId));
                    model.addAttribute(SPECIAL_SERVICES, specialServices);
                    model.addAttribute(SERVICE_TYPE, type);
                    model.addAttribute("fromServiceRequestOverview",true);
                    String errorMessage = specialServices.iterator().next().getErrorMessage();
                    if (errorMessage != null && !errorMessage.isEmpty()) {
                        model.addAttribute(SPECIAL_SERVICE_ERROR_MSG, errorMessage);
                    }
                    model.addAttribute(CREATION_ALLOWED, !specialServices.iterator().next().getCreationAllowed().isEmpty());
                } else {
                    // Auto-fix: no special service found for service request ID, therefore select the first one of the collection
                    selectFirstElement(specialServices, model);
                }
            } else {
                // There is no service request ID, therefore select the first one of the collection
                selectFirstElement(specialServices, model);
            }
        }

        model.addAttribute(ENTITY_STATUS, getEntityStatus());
        model.addAttribute(CANCELSUPPORTLETTER,isCancellationLetter);
        
        SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(serviceDiscriminator);
        model.addAttribute("sagiaService", sagiaService);
        
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_SPECIAL_SERVICES_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_SPECIAL_SERVICES_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * Reads the service request which corresponds to the first element of the given collection of special service requests
     * and places it into the model. An error message is placed into the model in case the first element has no
     * service request identifier.
     *
     * @param specialServices the collection of special service requests
     * @param model the model
     * @throws IOException if a technical error occurs
     */
    private void selectFirstElement(Collection<SpecialService> specialServices, Model model) throws IOException {
        Integer serviceId = specialServices.iterator().next().getId();
        if (serviceId != null) {
            model.addAttribute(SPECIAL_SERVICE_HEADER, sagiaSpecialServiceFacade.getService(serviceId));
            model.addAttribute(SPECIAL_SERVICES, specialServices);
        }
        String errorMessage = specialServices.iterator().next().getErrorMessage();
        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute(SPECIAL_SERVICE_ERROR_MSG, errorMessage);
        }
        model.addAttribute(CREATION_ALLOWED, !specialServices.iterator().next().getCreationAllowed().isEmpty());
    }

    @RequestMapping(value = {"latest/{type}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    public ResponseEntity getLatestSpecialServiceCreated(@PathVariable("type") String type, final Model model) {
        String serviceDiscriminator = serviceType.getOrDefault(type, null);
        if (serviceDiscriminator != null) {
            return new ResponseEntity<>(sagiaSpecialServiceFacade.getLatestServiceCreated(serviceDiscriminator), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Could not read the last entity created!", HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{type}/{id}")
    @ResponseBody
    @RequireHardLogIn
    public String getRealEstateById(@PathVariable("type") String type, @PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(sagiaSpecialServiceFacade.getService(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pdf/{entitySetName}/{objectId}/{documentGuid}")
    @RequireHardLogIn
    public ResponseEntity<InputStreamResource> getPdfAttachment(@PathVariable("entitySetName") String entitySetName,
                                                                @PathVariable("objectId") String objectId,
                                                                @PathVariable("documentGuid") String documentGuid, HttpServletRequest request, Model model) {
        try (InputStream inputStream = sagiaSpecialServiceFacade.getAttachements(objectId, documentGuid)) {
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(inputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(PDF));
            return new ResponseEntity<>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = {"/{serviceType}/create", "/{serviceType}/edit/{id}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String editAppointment(@PathVariable(SERVICE_TYPE) String type, @PathVariable("id") Optional<String> id, final Model model, final RedirectAttributes redirectModel) throws IOException, CMSItemNotFoundException {
        if (checkEntityStatusSpecialServices(redirectModel)) {
            return REDIRECT_PREFIX + "/special-services/" + type;
        }
        String serviceDiscriminator = serviceType.getOrDefault(type, null);
        model.addAttribute(SERVICE_TYPE, type);

        boolean draftExists = sagiaDraftFacade.isDraftExists(serviceDiscriminator);
        model.addAttribute("draftExists", draftExists);
        model.addAttribute("formName", SERVICE_PREFIX + serviceDiscriminator);
        model.addAttribute("serviceId", serviceDiscriminator);

        SpecialServiceHeader specialServiceHeader = new SpecialServiceHeader();
        ServiceApplicant serviceApplicant = new ServiceApplicant();
        if (id.isPresent()) {
            specialServiceHeader = sagiaSpecialServiceFacade.getService(Integer.valueOf(id.get()));
            serviceApplicant = specialServiceHeader.getApplicants().stream().findFirst().get();
            String specialServiceHeaderJson = new Gson().toJson(specialServiceHeader);
            model.addAttribute("specialServiceHeaderJson", specialServiceHeaderJson);
        }
        model.addAttribute(SPECIAL_SERVICE_HEADER, specialServiceHeader);
        model.addAttribute("serviceApplicant", serviceApplicant);
		
		SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(serviceDiscriminator);
		model.addAttribute("sagiaService", sagiaService);
        model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());
        
        String currentLanguage = i18NService.getCurrentLocale().getLanguage().toUpperCase();

        Collection<Country> countries = sagiaSpecialServiceFacade.getCountryCollection(currentLanguage);
        model.addAttribute("countries", countries);
        Collection<Region> regions = sagiaSpecialServiceFacade.getRegionCollection(currentLanguage);
        model.addAttribute("regions", regions);
        Collection<Attachment> attachments = sagiaSpecialServiceFacade.getAttachmentCollection(
                currentLanguage, "ZS13", "ZSPECIAL_SERVICES", serviceDiscriminator);
        model.addAttribute("attachments", attachments);

        List<ServiceApplicant> serviceApplicants = new ArrayList<>();
        if (getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS) != null) {
            serviceApplicants = getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS);
        }
        model.addAttribute("serviceApplicants", serviceApplicants);

        Collection<ServiceApplicant> existingApplicants = new ArrayList<>();
        if("transfer-of-iqama".equals(type) || "final-exit-visa".equals(type)) {
            existingApplicants = sagiaSpecialServiceFacade.getApplicants("ZS13", "ZSPLJAWA", serviceDiscriminator);
        }
        model.addAttribute("existingApplicants", existingApplicants);

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_SPECIAL_SERVICES_CREATE_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_SPECIAL_SERVICES_CREATE_CMS_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(value = {"/{serviceType}/create", "/{serviceType}/edit/{id}"}, method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public ResponseEntity<String> saveAppointment(@ModelAttribute SpecialServiceHeader specialServiceHeader, BindingResult bindingResult,
                                                  @PathVariable(SERVICE_TYPE) String type, @PathVariable("id") Optional<String> id,
                                                  final Model model) {
        String serviceDiscriminator = serviceType.getOrDefault(type, null);
        getSpecialServiceHeaderValidator().validate(specialServiceHeader,bindingResult);
        //validate supported attachments (the UI also validates,
        //but make sure that all supported attachments were uploaded and if not, do not let the request go to CRM
        sagiaSpecialServiceFacade.validUploadedAttachments(serviceDiscriminator, specialServiceHeader.getFiles(), specialServiceHeader.getDraftFiles());
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Gson().toJson(bindingResult.getAllErrors()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        model.addAttribute(SERVICE_TYPE, type);
        if (getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type) != null) {
            Collection<ServiceApplicant> sessionServiceApplicants = getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type);
            Set<ServiceApplicant> applicants = new HashSet<>(sessionServiceApplicants);
            specialServiceHeader.setApplicants(applicants);
        }
        specialServiceHeader.setType("ZS13");
        specialServiceHeader.setCategoryCode1("ZSPECIAL_SERVICES");
        specialServiceHeader.setCategoryCode2(serviceDiscriminator);
        sagiaSpecialServiceFacade.saveSpecialService(specialServiceHeader);
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel)userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.SPECIAL_SERVICES);
        if (getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type) != null && ("exit-re-entry-visa".equals(type) || "renewal-of-iqama".equals(type))) {
            getSessionService().removeAttribute(SAGIA_SERVICE_APPLICANTS + type);
        }
        HashMap<String, String> responseData = new HashMap<>();
        responseData.put("success", "true");
        sagiaDraftFacade.removeDraft(serviceDiscriminator);
        sagiaDraftFacade.removeJsonDraft(type + "." + SAGIA_SERVICE_APPLICANTS + ".json");
        return new ResponseEntity<>(new Gson().toJson(responseData), HttpStatus.OK);
    }

    @RequestMapping(value = "/{serviceType}/get-applicants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    public ResponseEntity<List<ServiceApplicant>> getApplicants(@PathVariable(SERVICE_TYPE) String type) {
        List<ServiceApplicant> serviceApplicants = new ArrayList<>();
        if (getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type) != null) {
            Collection<ServiceApplicant> sessionServiceApplicants = getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type);
            serviceApplicants.addAll(sessionServiceApplicants);
        }
        getSessionService().setAttribute(SAGIA_SERVICE_APPLICANTS + type, serviceApplicants);
        return new ResponseEntity<>(serviceApplicants, HttpStatus.OK);
    }

    @RequestMapping(value = "/{serviceType}/add-applicant", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    public ResponseEntity<List<ServiceApplicant>> addApplicant(@ModelAttribute ServiceApplicant applicant, @PathVariable(SERVICE_TYPE) String type) {
        List<ServiceApplicant> serviceApplicants = new ArrayList<>();
        applicant.setObjectGuid(UUID.randomUUID().toString());
        if (getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type) != null) {
            Collection<ServiceApplicant> sessionServiceApplicants = getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type);
            serviceApplicants.addAll(sessionServiceApplicants);
            serviceApplicants.add(applicant);
        } else {
            serviceApplicants.add(applicant);
        }
        getSessionService().setAttribute(SAGIA_SERVICE_APPLICANTS + type, serviceApplicants);
        return new ResponseEntity<>(serviceApplicants, HttpStatus.OK);
    }

    @RequestMapping(value = "/{serviceType}/remove-applicant/{guid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    public ResponseEntity<List<ServiceApplicant>> removeApplicant(@PathVariable("guid") String guid, @PathVariable(SERVICE_TYPE) String type) {
        List<ServiceApplicant> serviceApplicants = new ArrayList<>();
        if (getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type) != null) {
            Collection<ServiceApplicant> sessionServiceApplicants = getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type);
            serviceApplicants.addAll(sessionServiceApplicants.stream().filter(x -> !guid.equals(x.getObjectGuid())).collect(Collectors.toList()));
        }
        getSessionService().setAttribute(SAGIA_SERVICE_APPLICANTS + type, serviceApplicants);
        return new ResponseEntity<>(serviceApplicants, HttpStatus.OK);
    }

    @RequestMapping(value="/{serviceType}/get-draft", method = RequestMethod.GET)
    @ResponseBody
    public DraftInfo getDraftData(
            @PathVariable(SERVICE_TYPE) String type) throws IOException {
        final DraftInfo jsonUtilityDraft = sagiaDraftFacade.getJsonUtilityDraft(type);
        if (jsonUtilityDraft != null) {

            Type itemsListType = new TypeToken<List<ServiceApplicant>>() {}.getType();
            List<ServiceApplicant> draftServiceApplicants = new Gson().fromJson(jsonUtilityDraft.getData() ,itemsListType);

            List<ServiceApplicant> serviceApplicants = new ArrayList<>();
            final Set<String> idsElements = new HashSet<>();
            if (getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type) != null) {
                Collection<ServiceApplicant> sessionServiceApplicants = getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type);
                final Set<String> existingDraftApplicantsGuids = sessionServiceApplicants.stream().map(ServiceApplicant::getObjectGuid).collect(Collectors.toSet());
                idsElements.addAll(existingDraftApplicantsGuids);
                serviceApplicants.addAll(sessionServiceApplicants);
            }

            draftServiceApplicants.forEach(draftApplicant -> {
                if (!idsElements.contains(draftApplicant.getObjectGuid())) {
                    serviceApplicants.add(draftApplicant);
                }
            });

            getSessionService().setAttribute(SAGIA_SERVICE_APPLICANTS + type, serviceApplicants);
        }
        return sagiaDraftFacade.getDraft(type);
    }

    @RequestMapping(value = "/{serviceType}/save-draft", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @ResponseBody
    public void saveDraftMultipartData(
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            @RequestParam(value = "paramsCount", defaultValue = "0", required = false) Integer paramsCount,
            @RequestParam(value = "attachments", required = false) List<String> attachments,
            @RequestParam(value = "attachmentsNames", required = false) List<String> attachmentsNames,
            @RequestParam(value = "draftFiles", required = false) List<String> draftFiles,
            @PathVariable(SERVICE_TYPE) String type,
            MultipartHttpServletRequest request, HttpServletResponse response) {
        DraftData draftData = new DraftData();
        draftData.setId(type);

        List<DraftParameter> parameters = new ArrayList<>();
        for (int i = 0; i < paramsCount; i++)
        {
            DraftParameter parameter = new DraftParameter();
            parameter.setName(request.getParameter(PARAMETERS + "[" + i + "][name]"));
            parameter.setValue(request.getParameter(PARAMETERS + "[" + i + "][value]"));
            parameter.setType(request.getParameter(PARAMETERS + "[" + i + "][type]"));

            if (request.getParameter(PARAMETERS + "[" + i + "][fileName]") != null) {
                parameter.setFileName(request.getParameter(PARAMETERS + "[" + i + "][fileName]"));
            }

            parameters.add(parameter);
        }

        if (getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type) != null) {
            Collection<ServiceApplicant> sessionServiceApplicants = getSessionService().getAttribute(SAGIA_SERVICE_APPLICANTS + type);
            String json = new Gson().toJson(sessionServiceApplicants);

            final DraftJsonData jsonData = new DraftJsonData();
            jsonData.setJson(json);
            jsonData.setServiceId(type);

            sagiaDraftFacade.saveUtilityDraft(jsonData);
        }

        draftData.setParameters(parameters);
        draftData.setFiles(files);
        draftData.setDraftFiles(draftFiles);
        draftData.setAttachmentsNames(attachments);
        draftData.setAttachmentsInputNames(attachmentsNames);

        sagiaDraftFacade.save(draftData, type);
    }

    private boolean isCancellationLetterStatusOk(){
        final  GlobalLicenseCancellation globalLicenseCancellation = sagiaLicenseCancellationFacade.getGlobalLicenseCancellation("", "");
        int stage;
        try {
            stage = Integer.parseInt(globalLicenseCancellation.getStage());
        } catch (NumberFormatException e) {
            return false;
        }
        return ((stage == STAGE_2 && E0002.equals(globalLicenseCancellation.getSrStCode())) || stage > STAGE_2);
    }

    public Validator getSpecialServiceHeaderValidator() {
        return specialServiceHeaderValidator;
    }

    public void setSpecialServiceHeaderValidator(Validator specialServiceHeaderValidator) {
        this.specialServiceHeaderValidator = specialServiceHeaderValidator;
    }

    /*
     * Suppress sonar warning (squid:S1067 | Expressions should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:S1067"})
    protected boolean checkEntityStatusSpecialServices(RedirectAttributes redirectModel) {
        //disable all hybris logic for validation. driver is crm only
//        final String sessionEntityStatus = getEntityStatus();
//        if ((!StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_REVOKED) &&
//                !StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_EXPIRED) &&
//                !StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_ACTIVE) &&
//                !StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_SUSPENDED) &&
//                (StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_NATIONAL) || StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_CANCEL))
//        ) /*|| (StringUtils.containsIgnoreCase(sessionEntityStatus, ENTITY_STATUS_REVOKED) && isCancellationLetterStatusOk())*/){
//            return false;
//        }
//        redirectModel.addFlashAttribute(IS_ENTITY_STATUS_VALID, false);

        redirectModel.addFlashAttribute(IS_ENTITY_STATUS_VALID, true);
        return false;
    }
}
