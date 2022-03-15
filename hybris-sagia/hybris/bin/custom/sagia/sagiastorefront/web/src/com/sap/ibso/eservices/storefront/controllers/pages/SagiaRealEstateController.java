package com.sap.ibso.eservices.storefront.controllers.pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.RealEstate;
import com.sap.ibso.eservices.facades.data.RegionCityData;
import com.sap.ibso.eservices.facades.sagia.RealEstateFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentList;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateEntityDetailsSet;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaRuntimeException;
import com.sap.ibso.eservices.sagiaservices.services.impl.SagiaRealEstateAttachmentService;
import com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.SagiaRealEstateForm;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.localization.Localization;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping(value = "/real-estate")
public class SagiaRealEstateController extends SagiaAbstractPageController {

	private static final Logger LOG = Logger.getLogger(SagiaRealEstateController.class);
	 
    private static final String SAGIA_REAL_ESTATE_FORM = "sagiaRealEstateForm";
    private static final String REAL_ESTATE = "realEstate";
    private static final String CONSENT = "consent";
    private static final String REAL_ESTATE_CREATE = "/real-estate/create";
    public static final String REAL_ESTATE_RESUBMITTED_ATTACHMENTS = "realEstateResubmittedAttachments";
    public static final String DOCUMENTS_TO_UPLOAD = "documentsToUpload";
    
    private static final String SAGIA_REAL_ESTATE_CMS_PAGE = "realestate";
    private static final String SAGIA_CREATE_REAL_ESTATE_CMS_PAGE = "realestate-create";
	private static final String SAGIA_CREATE_REAL_ESTATE_TYPE_CODE = "ZRES";
	
	
    @Resource
    private MessageSource messageSource;

    @Resource
    private I18NService i18NService;

    @Resource
    private RealEstateFacade realEstateFacade;
	
	@Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;

    @Autowired
    private SagiaDraftFacade sagiaDraftFacade;

    @Resource(name = "mediaService")
    private MediaService mediaService;

    @Resource(name = "sagiaRealEstateValidator")
    private Validator createRealEstateValidator;

    @Resource
    private SagiaRealEstateAttachmentService sagiaRealEstateAttachmentService;
    
    @Autowired
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Autowired
    private UserService userService;

    
    /**
     * Get Real Estate info from CRM and add it to the model.
     *
     * @param model
     * @return Real Estate page.
     */
    @RequestMapping(value = {"", "/display/{srId}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String showRealEstate(final Model model, @PathVariable(name = "srId", required = false) String srId) {
        Collection<RealEstate> realEstateHistory = realEstateFacade.getRealEstateHistory();

        if (CollectionUtils.isNotEmpty(realEstateHistory))
        {
            model.addAttribute("realEstateHistory", realEstateHistory);

            RealEstate selectedRealEstate;
            // Consider the service request ID if present, otherwise select the first element in the collection
            if (srId != null)
            {
                // Filter the real estate service requests for the service request ID and select it
                Optional<RealEstate> realEstateRequest = realEstateHistory.stream().filter(serviceRequest -> srId.equals(serviceRequest.getObjectId())).findFirst();
                if (realEstateRequest.isPresent())
                {
                    selectedRealEstate = realEstateFacade.getRealEstateById(srId);
                    model.addAttribute("fromServiceRequestOverview",true);
                }
                else
                {
                    // Auto-fix: no real estate service request found for service request ID, therefore select the first one of the collection
                    selectedRealEstate = realEstateFacade.getRealEstateById(realEstateHistory.stream().findFirst().get().getObjectId());
                }
            }
            else
            {
                // There is no service request ID, therefore select the first one of the collection
                selectedRealEstate = realEstateFacade.getRealEstateById(realEstateHistory.stream().findFirst().get().getObjectId());
            }

            model.addAttribute("firstElement", selectedRealEstate);
        }

        SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SAGIA_CREATE_REAL_ESTATE_TYPE_CODE);
        model.addAttribute("sagiaService", sagiaService);
        model.addAttribute("inProcess", messageSource.getMessage("realEstate.status.inProcess", null, i18NService.getCurrentLocale()));
        model.addAttribute("rejected", messageSource.getMessage("realEstate.status.rejected", null, i18NService.getCurrentLocale()));
        model.addAttribute("completed", messageSource.getMessage("realEstate.status.completed", null, i18NService.getCurrentLocale()));
        model.addAttribute(ENTITY_STATUS, getEntityStatus());
        model.addAttribute(CURRENT_ENTITY_STATUS, getCurrentEntityStatus());
        return getView(model, SAGIA_REAL_ESTATE_CMS_PAGE);
    }
    
    /**
     * get the first entry from the list
     */
	@RequestMapping(method = RequestMethod.GET, path = "/latest", produces = "application/json")
	@RequireHardLogIn
	public ResponseEntity<RealEstate> getLatestCreatedRealEstate(HttpServletRequest request, final Model model) {
		return new ResponseEntity<>(realEstateFacade.getLatestRealEstate(), HttpStatus.OK);
	}

    /**
     * Populate Request Type drop-down, Real Estate Type drop-down and
     * list of regions.
     *
     * @param id
     * @param model
     * @param realEstate
     * @return Real Estate creation page.
     */
    @RequestMapping(path = {"/create"}, method = RequestMethod.GET)
    @RequireHardLogIn
    public String createRealEstate(@PathVariable("id") Optional<String> id, final Model model,
                                   @ModelAttribute(REAL_ESTATE) SagiaRealEstateForm realEstate, final RedirectAttributes redirectModel) {
    	
    	
        if (checkEntityStatusRealEstate(redirectModel)) {
            return REDIRECT_PREFIX + "/real-estate";
        }
        SagiaRealEstateForm sessionForm = getSessionService().getAttribute(SAGIA_REAL_ESTATE_FORM);
        fillRequestAndPurchaseTypes(model);
		SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SAGIA_CREATE_REAL_ESTATE_TYPE_CODE);	
        model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());
        
        try {
            if (sessionForm != null) {
                realEstate.setDocumentsToUpload(realEstateFacade.getAttachmentSet(sessionForm.getRequestType(), sessionForm.getPurchaseType()));                
                model.addAttribute("hasErrors", "true");
            }
        } catch (SagiaODataException e) {
            LOG.warn(e.getMessage(), e);
        }

		/*
		 * if (CollectionUtils.isNotEmpty(realEstate.getFileText()) &&
		 * CollectionUtils.isNotEmpty(realEstate.getDocumentsToUpload())) { int index =
		 * 0; LOG.info("%%%%%%%%% enter creat 10"); for (RealEstateAttachmentList
		 * uploadData : realEstate.getDocumentsToUpload()) {
		 * uploadData.setFileText(realEstate.getFileText().get(index++)); }
		 * LOG.info("%%%%%%%%% enter creat 11"); }
		 */

        model.addAttribute(REAL_ESTATE, realEstate);
        return getView(model, SAGIA_CREATE_REAL_ESTATE_CMS_PAGE);
    }

    /**
     * Populate entire form.
     *
     * @param id
     * @param model
     * @param realEstateForm
     * @return Real Estate creation page.
     */
    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S3776","squid:S134"})
    @RequestMapping(path = {"/resubmit/{id}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String resubmitRealEstate(@PathVariable("id") String id, final Model model,
                                   @ModelAttribute(REAL_ESTATE) SagiaRealEstateForm realEstateForm, final RedirectAttributes redirectModel) {
        if (checkEntityStatusRealEstate(redirectModel) ||
                (StringUtils.isNotEmpty(realEstateForm.getStatus()) && StringUtils.containsIgnoreCase(realEstateForm.getStatus(), "rejected")) ) {
            return REDIRECT_PREFIX + "/real-estate";
        }

        if(id != null){
            RealEstate realEstate = realEstateFacade.getRealEstateById(id);
            if(realEstate.getSagiaPurchaseDate() != null) {
                realEstate.setPurchaseDate(ObjectUtils.changeDateFormat(realEstate.getSagiaPurchaseDate().getDateFormatted(), "dd MM yyyy", "MMM dd, yyyy"));
            }
            Set<RealEstateAttachment> attachments = realEstate.getAttachmentsSet();
            int index = 0;
            for(RealEstateAttachment a : attachments){
                //add index for each attachment
                a.setLocalId(index++);
            }
            getSessionService().getCurrentSession().setAttribute(REAL_ESTATE_RESUBMITTED_ATTACHMENTS, new ArrayList<>(attachments));

            //fill resubmitted file names on the documents to upload
            Collection<RealEstateAttachmentList> documentsToUpload = realEstateFacade.getAttachmentSet(realEstate.getRequestType(), realEstate.getPurchaseType());
            if(CollectionUtils.isNotEmpty(documentsToUpload)){
                index = 0;
                getSessionService().setAttribute(DOCUMENTS_TO_UPLOAD, documentsToUpload);
                for(RealEstateAttachmentList document : documentsToUpload){
                    if(realEstate.getAttachmentsSet().iterator().hasNext()){
                        RealEstateAttachment attachment = realEstate.getAttachmentsSet().iterator().next();
                        document.setLocalId(index++);
                        document.setResubmittedFileName(attachment.getFilename());
                        realEstate.getAttachmentsSet().remove(attachment);
                    }
                }
            }
            realEstateForm.setDocumentsToUpload(documentsToUpload);

            BeanUtils.copyProperties(realEstate, realEstateForm);
        }
        fillRequestAndPurchaseTypes(model);
        model.addAttribute(REAL_ESTATE, realEstateForm);
        return getView(model, SAGIA_CREATE_REAL_ESTATE_CMS_PAGE);
    }

    /**
     * Validate data input and create a Real Estate instance.
     *
     * @param id
     * @param sagiaRealEstateForm
     * @param model
     * @param result
     * @param redirectModel
     * @param request
     * @return same page if validation fails, otherwise Real Estate page.
     */
    @RequestMapping(path = {"/save"}, method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public String saveRealEstate(@PathVariable("id") Optional<String> id, @ModelAttribute(SAGIA_REAL_ESTATE_FORM) final SagiaRealEstateForm sagiaRealEstateForm, final Model model, BindingResult result, final RedirectAttributes redirectModel, HttpServletRequest request) throws IOException {
        if (request.getParameterValues("consentBox") != null) {
            sagiaRealEstateForm.setTermsAndConditionsChecked(true);
        }
        if(StringUtils.isBlank(sagiaRealEstateForm.getRequestType()) ||
                StringUtils.isBlank(sagiaRealEstateForm.getPurchaseType())){
            return REDIRECT_PREFIX + REAL_ESTATE_CREATE;
        }
        LOG.info("%%%%%%%%% enter Save "+sagiaRealEstateForm.getMojDeedDate()+" getPurchaseDate: "+sagiaRealEstateForm.getPurchaseDate()+" identityNumber: "+sagiaRealEstateForm.getIdentityNumber()+" deedNumber: "+sagiaRealEstateForm.getDeedNumber()+" **isMojVerified "+sagiaRealEstateForm.getMojVerified());
        LOG.info("%%%%%%%%% enter Save2 "+sagiaRealEstateForm.getRegion()+" getRegionName: "+sagiaRealEstateForm.getRegionName()+" getCity(): "+sagiaRealEstateForm.getCity()+" getCityName(): "+sagiaRealEstateForm.getCityName());
        try {        
        keepPreviousUploadedFiles(sagiaRealEstateForm);       
        RealEstate realEstate = populateRealEstate(sagiaRealEstateForm);
        fillResubmittedFiles(realEstate, sagiaRealEstateForm);
        createRealEstateValidator.validate(sagiaRealEstateForm, result);
        if (!result.hasErrors()) {
            realEstateFacade.saveRealEstate(realEstate);
            sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel)userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.REAL_ESTATE);
            getSessionService().removeAttribute(SAGIA_REAL_ESTATE_FORM);
            redirectModel.addFlashAttribute("requestFeedback", "true");
            sagiaDraftFacade.removeDraft(SAGIA_CREATE_REAL_ESTATE_TYPE_CODE);
            return REDIRECT_PREFIX + REAL_ESTATE_CREATE;
        } else {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, Localization.getLocalizedString("realEstate.form.error"));
            getSessionService().setAttribute(SAGIA_REAL_ESTATE_FORM, sagiaRealEstateForm);
            redirectModel.addFlashAttribute(REAL_ESTATE, sagiaRealEstateForm);
            redirectModel.addFlashAttribute(CONSENT, sagiaRealEstateForm.isTermsAndConditionsChecked());
            redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.realEstate", result);
            return REDIRECT_PREFIX + REAL_ESTATE_CREATE;
        }
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, Localization.getLocalizedString("realEstate.form.error"));
            getSessionService().setAttribute(SAGIA_REAL_ESTATE_FORM, sagiaRealEstateForm);
            redirectModel.addFlashAttribute(REAL_ESTATE, sagiaRealEstateForm);
            return REDIRECT_PREFIX + REAL_ESTATE_CREATE;
        }
    }

    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S3776","squid:S134","squid:MethodCyclomaticComplexity"})
    private void fillResubmittedFiles(RealEstate realEstate, SagiaRealEstateForm sagiaRealEstateForm) {
        List<RealEstateAttachment> realEstateResubmittedAttachments = getSessionService().getAttribute(REAL_ESTATE_RESUBMITTED_ATTACHMENTS);
        List<RealEstateAttachment> realEstateAttachmentsFromPage = null;
        if(realEstate.getAttachmentsSet() != null) {
        	realEstateAttachmentsFromPage = new ArrayList<>(realEstate.getAttachmentsSet());
        }else {
        	realEstateAttachmentsFromPage = new ArrayList<>();
        }
        //List<RealEstateAttachment> realEstateAttachmentsFromPage = new ArrayList<>(realEstate.getAttachmentsSet());
        Set<RealEstateAttachment> realEstateAttachments = new HashSet<>();
        Collection<RealEstateAttachmentList> documentsToUpload = getSessionService().getAttribute(DOCUMENTS_TO_UPLOAD);

        if (CollectionUtils.isNotEmpty(documentsToUpload)) {
            getSessionService().removeAttribute(DOCUMENTS_TO_UPLOAD);

            if (CollectionUtils.isNotEmpty(realEstateAttachmentsFromPage)) {
                if (realEstateAttachmentsFromPage.size() == documentsToUpload.size()) {
                    realEstate.setAttachmentsSet(new HashSet<>(realEstateAttachmentsFromPage));
                } else if (realEstateAttachmentsFromPage.size() > documentsToUpload.size()) {
                    throw new SagiaRuntimeException("Numbered of attached documents can not be greater than the required documents!");
                } else {
                    if (CollectionUtils.isNotEmpty(realEstateResubmittedAttachments)) {
                        sagiaRealEstateForm.setResubmittedForm(true);

                        for(RealEstateAttachmentList docToUpload : documentsToUpload) {
                            RealEstateAttachment sessionAttachment = null;
                            RealEstateAttachment pageAttachment = null;

                            if(CollectionUtils.isNotEmpty(realEstateResubmittedAttachments)){
                                sessionAttachment = realEstateResubmittedAttachments.stream().filter(item -> docToUpload.getLocalId().equals(item.getLocalId())).findAny().orElse(null);
                            }

                            if(CollectionUtils.isNotEmpty(realEstateAttachmentsFromPage)){
                                pageAttachment = realEstateAttachmentsFromPage.stream().filter(item -> docToUpload.getLocalId().equals(item.getLocalId())).findAny().orElse(null);
                            }

                            if(sessionAttachment != null && pageAttachment != null){
                                if (sessionAttachment.getLocalId().equals(pageAttachment.getLocalId())) {
                                    realEstateAttachments.add(pageAttachment);
                                } else {
                                    realEstateAttachments.add(addResubmittedAttachment(sessionAttachment));
                                }
                            } else if(sessionAttachment != null) {
                                realEstateAttachments.add(addResubmittedAttachment(sessionAttachment));
                            } else if(pageAttachment != null) {
                                realEstateAttachments.add(pageAttachment);
                            }
                        }
                    }

                    realEstate.setAttachmentsSet(realEstateAttachments);
                    getSessionService().removeAttribute(REAL_ESTATE_RESUBMITTED_ATTACHMENTS);
                }
            }
            else {
                sagiaRealEstateForm.setResubmittedForm(true);
                for(RealEstateAttachment attachment : realEstateResubmittedAttachments){
                    realEstateAttachments.add(addResubmittedAttachment(attachment));
                }
                realEstate.setAttachmentsSet(realEstateAttachments);
            }
        }
    }


    private RealEstateAttachment addResubmittedAttachment(RealEstateAttachment resubmittedAttachment){
        try {
            if(StringUtils.isEmpty(resubmittedAttachment.getFileContString())) {
                resubmittedAttachment.setFileContString(
                        Base64.encode(IOUtils.toByteArray(
                                sagiaRealEstateAttachmentService.readRealEstatePdf(
                                        resubmittedAttachment.getObjectId(), resubmittedAttachment.getDocGuid())))
                );
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
        }
        //set attributes to null because if not, they will be added in the post request and the post request will fail
        resubmittedAttachment.setObjectId(null);
        resubmittedAttachment.setObjectGUID(null);
        resubmittedAttachment.setDocGuid(null);
        //remove extension from filename
        String fileName = resubmittedAttachment.getFilename();
        if (StringUtils.isNotEmpty(fileName) && fileName.contains(".")) {
            resubmittedAttachment.setFilename(fileName.substring(0, fileName.lastIndexOf('.')));
        }
        return resubmittedAttachment;
    }


    private void fillRequestAndPurchaseTypes(Model model) {
        Map<String, String> requestType = new HashMap<>();
        if(allowBuyOption()) {
            requestType.put("Buy", getLocalizedValue("realEstate.request.type.buy"));
            model.addAttribute("allowBuy", true);
        }
        requestType.put("Sell", getLocalizedValue("realEstate.request.type.sell"));

        Map<String, String> purchaseType = new HashMap<>();
        purchaseType.put("10", getLocalizedValue("realEstate.type.personalHousing"));
        purchaseType.put("20", getLocalizedValue("realEstate.type.industrial"));
        purchaseType.put("30", getLocalizedValue("realEstate.type.administrative"));
        purchaseType.put("40", getLocalizedValue("realEstate.type.warehousing"));
        purchaseType.put("50", getLocalizedValue("realEstate.type.investment"));
        
        Map<String, String> identityType = new HashMap<>();
        identityType.put("11", getLocalizedValue("realEstate.type.saudiId"));
        identityType.put("12", getLocalizedValue("realEstate.type.iqamaId"));
        identityType.put("7", getLocalizedValue("realEstate.type.passportId"));
        identityType.put("3", getLocalizedValue("realEstate.type.crNumber"));

        model.addAttribute("requestType", requestType);
        model.addAttribute("purchaseType", purchaseType);
        model.addAttribute("identityType", identityType);

		    boolean draftExists = sagiaDraftFacade.isDraftExists(SAGIA_CREATE_REAL_ESTATE_TYPE_CODE);
		    model.addAttribute("draftExists", draftExists);
		    model.addAttribute("serviceId", SAGIA_CREATE_REAL_ESTATE_TYPE_CODE);

        model.addAttribute("regionCities", realEstateFacade.getRegionCitySet());
    }

    private void keepPreviousUploadedFiles(SagiaRealEstateForm sagiaRealEstateForm) {

        SagiaRealEstateForm sessionForm = getSessionService().getAttribute(SAGIA_REAL_ESTATE_FORM);
        if (sessionForm != null && CollectionUtils.isNotEmpty(sessionForm.getFiles())) {
            int index = 0;
            for (MultipartFile file : sagiaRealEstateForm.getFiles()) {
                if (file.getSize() <= 0 && sessionForm.getFiles().get(index).getSize() > 0) {
                    sagiaRealEstateForm.getFiles().set(index, sessionForm.getFiles().get(index));
                }
                index++;
            }
        }

        if (CollectionUtils.isNotEmpty(sagiaRealEstateForm.getFiles())) {
            int index = 0;
            for (MultipartFile file : sagiaRealEstateForm.getFiles()) {
                sagiaRealEstateForm.getFileText().set(index++, file.getOriginalFilename().toUpperCase());
            }
        }
    }

    /**
     * Get list of cities based on the region.
     *
     * @param regionId regionId
     * @return list of cities found for the given region.
     */
    @RequestMapping(value = "/getCity/{regionId}", method = RequestMethod.GET)
    @RequireHardLogIn
    public @ResponseBody
    Collection<RegionCityData> getAllCities(@PathVariable("regionId") String regionId) {
        try {
            return realEstateFacade.getCities(regionId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    /**
     * Get Real Estate info and pass it as a JSON object to the view.
     *
     * @param realEstateId
     * @return JSON object with Real Estate info.
     */
    @RequestMapping(value = "/{realEstateId}")
    @ResponseBody
    @RequireHardLogIn
    public String getRealEstateById(@PathVariable("realEstateId") String realEstateId) {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(realEstateFacade.getRealEstateById(realEstateId));
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage(),e);
        }
        return null;
    }
    
    @RequestMapping(value = "/deeds/{identityType}/{identityNumber}/{deedNumber}", method = RequestMethod.GET)
    @ResponseBody
    @RequireHardLogIn
    public String getDeedList(@PathVariable("identityType") String identityType, @PathVariable("identityNumber") String identityNumber, @PathVariable("deedNumber") String deedNumber, final Model model,
            @ModelAttribute SagiaRealEstateForm realEstate, final RedirectAttributes redirectModel) {
        try {
    	realEstate.setMojVerified("FALSE");
    	Collection<RealEstate> deedInfoList = realEstateFacade.getDeedInfoList(identityNumber, identityType, deedNumber);
    	if (CollectionUtils.isNotEmpty(deedInfoList))
        {
            model.addAttribute("deedInfoList", deedInfoList);
            realEstate.setMojVerified("TRUE");
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(deedInfoList);
        }
    	
        } catch (JsonProcessingException e) {
        	realEstate.setMojVerified("FALSE");
           LOG.warn(e.getMessage(),e);
        }
        return null;
    }
    
    @RequestMapping(value = "/deedDetails/{identityType}/{identityNumber}/{deedNumber}", method = RequestMethod.GET)
    @ResponseBody
    @RequireHardLogIn
    public String getDeedDetails(@PathVariable("identityType") String identityType, @PathVariable("identityNumber") String identityNumber, @PathVariable("deedNumber") String deedNumber, final Model model,
            @ModelAttribute SagiaRealEstateForm sagiaRealEstateForm, final RedirectAttributes redirectModel) {
        try {
    	RealEstate deedDetail = realEstateFacade.getDeedDetails(identityNumber, identityType, deedNumber);
    	if (deedDetail != null)
        {
    		sagiaRealEstateForm.setMojVerified("TRUE");
            model.addAttribute("mojVerified", true);
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(deedDetail);
        }else {
        	sagiaRealEstateForm.setMojVerified("FALSE");
        	return null;
        }
    	
        } catch (JsonProcessingException e) {
        	sagiaRealEstateForm.setMojVerified("FALSE");
           LOG.warn(e.getMessage(),e);
        }
        return null;
    }
    

    @RequestMapping(value = "/attachmentset/{requestType}/{realEstateType}")
    @ResponseBody
    @RequireHardLogIn
    public String getAttachmentSet(@PathVariable("requestType") String requestType, @PathVariable("realEstateType") String realEstateType) {
        try {
            getSessionService().removeAttribute(SAGIA_REAL_ESTATE_FORM);
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(realEstateFacade.getAttachmentSet(requestType, realEstateType));
        } catch (JsonProcessingException e) {
           LOG.warn(e.getMessage(),e);
        }
        return null;
    }

    private String getView(Model model, String page) {
        try {
            storeCmsPageInModel(model, getContentPageForLabelOrId(page));
            setUpMetaDataForContentPage(model, getContentPageForLabelOrId(page));
        } catch (CMSItemNotFoundException e) {
            LOG.error(e.getMessage(),e);
        }
        return getViewForPage(model);
    }

    private String getLocalizedValue(String message) {
        return messageSource.getMessage(message, null, getI18nService().getCurrentLocale());
    }

    private RealEstate populateRealEstate(SagiaRealEstateForm sagiaRealEstateForm) {

        RealEstate realEstate = prepopulateRealEstate(sagiaRealEstateForm);

        Set<RealEstateAttachment> attachments = setRealEstateAttachments(realEstate, sagiaRealEstateForm.getFiles());

        final List<String> draftFiles = sagiaRealEstateForm.getFileData();
        if (CollectionUtils.isEmpty(draftFiles)) {
            return realEstate;
        }
        for (String fileCode : draftFiles) {
            final RealEstateAttachment realEstateAttachment = new RealEstateAttachment();
            try {
                final CatalogUnawareMediaModel file = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                realEstateAttachment.setFilename(StringUtils.substringBefore(file.getRealFileName(), "."));
                realEstateAttachment.setMimeType(file.getMime());
                realEstateAttachment.setFileContString(Base64.encode(mediaService.getDataFromMedia(file)));
                attachments.add(realEstateAttachment);
            } catch (UnknownIdentifierException | AmbiguousIdentifierException ex) {
                LOG.error("Error getting content of media for code: " + fileCode, ex);
                LOG.error(ex, ex);
            }
        }

        return realEstate;
    }

    private Set<RealEstateAttachment> setRealEstateAttachments(RealEstate realEstate, List<MultipartFile> files) {
        Set<RealEstateAttachment> attachments = new HashSet<>();
        if (CollectionUtils.isEmpty(files)) {
            return attachments;
        }
        int index = 0;
        for (MultipartFile file : files) {
            RealEstateAttachment realEstateAttachment = new RealEstateAttachment();
            realEstateAttachment.setLocalId(index++);
            if (file.getSize() > 0) {
                realEstateAttachment.setFilename(StringUtils.substringBefore(file.getOriginalFilename(), "."));
                realEstateAttachment.setMimeType(file.getContentType());
                try {
                    realEstateAttachment.setFileContString(Base64.encode(file.getBytes()));
                } catch (IOException e) {
                    LOG.error("Content could not be parsed for file: " + file.getOriginalFilename(), e);
                }
                attachments.add(realEstateAttachment);
            }
        }
        realEstate.setAttachmentsSet(attachments);

        return attachments;
    }

    private RealEstate prepopulateRealEstate(SagiaRealEstateForm sagiaRealEstateForm) {
        RealEstate realEstate = new RealEstate();
        realEstate.setRequestType(sagiaRealEstateForm.getRequestType());
        realEstate.setPurchaseType(sagiaRealEstateForm.getPurchaseType());
        realEstate.setPlotNo(sagiaRealEstateForm.getPlotNo());
        realEstate.setPlotNo2(sagiaRealEstateForm.getPlotNo2());
        realEstate.setPlotNo3(sagiaRealEstateForm.getPlotNo3());
        realEstate.setPlotNo4(sagiaRealEstateForm.getPlotNo4());
        realEstate.setPlotNo5(sagiaRealEstateForm.getPlotNo5());
        realEstate.setPlotArea(sagiaRealEstateForm.getPlotArea());
        realEstate.setDeedNo(sagiaRealEstateForm.getDeedNo());
        if(StringUtils.isNotEmpty(sagiaRealEstateForm.getMojDeedDate())) {
        	realEstate.setPurchaseDate(sagiaRealEstateForm.getMojDeedDate());
        }else {
        	realEstate.setPurchaseDate(sagiaRealEstateForm.getPurchaseDate());
        }
        //realEstate.setPurchaseDate(sagiaRealEstateForm.getPurchaseDate());
        realEstate.setOutsideMakkah(sagiaRealEstateForm.getOutsideMakkah());
        realEstate.setApprovedIndustrial(sagiaRealEstateForm.getApprovedIndustrial());
        realEstate.setProjectValue(sagiaRealEstateForm.getProjectValue());
        realEstate.setPrice(sagiaRealEstateForm.getPrice());
        realEstate.setRegion(sagiaRealEstateForm.getRegionName());
        realEstate.setCity(sagiaRealEstateForm.getCityName());
        realEstate.setHousingType(sagiaRealEstateForm.getHousingType());
        realEstate.setDistrict(sagiaRealEstateForm.getDistrict());
        realEstate.setUnitNo(sagiaRealEstateForm.getUnitNo());
        realEstate.setBlockNo(sagiaRealEstateForm.getBlockNo());
        realEstate.setRemarks(sagiaRealEstateForm.getRemarks());
        realEstate.setThirtyMore(sagiaRealEstateForm.getThirtyMore());
        realEstate.setBpID(sagiaRealEstateForm.getBpID());
        realEstate.setPostingDate(sagiaRealEstateForm.getPostingDate());
        realEstate.setStatus(sagiaRealEstateForm.getStatus());
        
        realEstate.setIdNo(sagiaRealEstateForm.getIdentityNumber());
        realEstate.setMojDeedDate(sagiaRealEstateForm.getPurchaseDate());
        realEstate.setMojRegion(sagiaRealEstateForm.getMojRegion());
        realEstate.setMojCity(sagiaRealEstateForm.getMojCity());
        realEstate.setIssuerCourtName(sagiaRealEstateForm.getMojIssuerCourtName());
        realEstate.setMojVerified(sagiaRealEstateForm.getMojVerified());
        return realEstate;
    }


    @RequestMapping(value = "/checkAllowedRequestType", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity<RealEstateEntityDetailsSet> checkAllowedRequestType() {
            RealEstateEntityDetailsSet realEstateEntityDetailsSet = new RealEstateEntityDetailsSet();
        try {
            realEstateEntityDetailsSet = realEstateFacade.getRealEstateEntityDetailsSet();
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(),e);
            throw new SagiaCRMException(e.getMessage());
        }
        return new ResponseEntity(realEstateEntityDetailsSet,HttpStatus.OK);
    }
}
