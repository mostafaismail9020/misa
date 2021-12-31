package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.constants.SagiaFacadesConstants;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseCancellationFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GlobalLicenseCancellation;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseCancellationSetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseClearanceSetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseToTextNavData;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseCancelFormData;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseClearanceFormData;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/my-sagia/license/cancel")
public class LicenseCancellationController extends SagiaAbstractPageController {
    private static final String SR_ID = "SrID";
    private static final String STAGE = "Stage";
    private static final String GLOBAL_LICENSE_CANCELLATION = "globalLicenseCancellation";
    private static final String FORM_CANCEL_LICENSE_STAGE_1 = "formCancelLicenseStage1";
    private static final String DRAFT_EXISTS = "draftExists";
    private static final String MY_SAGIA_LICENSE_CANCEL = "/my-sagia/license/cancel";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;
    @Resource(name = "sagiaLicenseCancellationFacade")
    private SagiaLicenseCancellationFacade sagiaLicenseCancellationFacade;
    @Resource(name = "contentDetailsService")
    private ContentDetailsService contentDetailsService;
    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;
    @Resource(name = "licenseClearanceValidator")
    private Validator licenseClearanceValidator;
    @Resource(name = "sagialicenseCancelValidator")
    private Validator sagialicenseCancelValidator;
    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;
    @Resource(name = "i18nService")
    private I18NService i18nService;

    private static final String SAGIA_CANCELLATION_CMS_PAGE = "license-cancellation";
    private static final String SAGIA_CANCELLATION_CMS_PAGE_1 = "license-cancellation_1";
    private static final String SAGIA_CANCELLATION_CMS_PAGE_2 = "license-cancellation_2";
    private static final String SAGIA_CANCELLATION_CMS_PAGE_3 = "license-cancellation_3";
    private static final String SAGIA_CANCELLATION_CMS_PAGE_4 = "license-cancellation_4";
    private static final String STACKTRACE_LOG = "stacktrace:";
    private static final String E0001 = "E0001";
    private static final String E0002 = "E0002";
    private static final String E0003 = "E0003";
    private static final String UPLOADED_ATTACHMENTS = "uploadedAttachments";
    private static final String SUPPORTING_ATTACHMENTS = "supportingAttachments";
    private static final String PROCESSING_TIME = "processingTime";
    private static final String LICENSE_CLEARANCE_FORM_DATA = "licenseClearanceFormData";
    private static final String LICENSE_CANCEL_FORM_DATA = "licenseCancelFormData";
    private static final String ENTITY_NAME_CLEARANCE = "LicenseClearanceSet";
    private static final String ENTITY_NAME_CANCEL = "LicenseCancellationSet";
    private static final Logger LOG = Logger.getLogger(LicenseCancellationController.class);

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:MethodCyclomaticComplexity","squid:S3776","squid:S134"})
    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getLicenseCancellation(final Model model) throws CMSItemNotFoundException, IOException {
        int srId;
        String cancellationMessage = "";
        
        GlobalLicenseCancellation globalLicenseCancellation = sagiaLicenseCancellationFacade.getGlobalLicenseCancellation("","");
        if(globalLicenseCancellation != null && globalLicenseCancellation.getIsAllowed().equalsIgnoreCase("N")) {
        	cancellationMessage = globalLicenseCancellation.getMsgToInvestor();
        }
        LOG.info("****Message: "+cancellationMessage+" ******* IsAllowed: "+globalLicenseCancellation.getIsAllowed()+ " ^^^^ cancellationMessage: "+globalLicenseCancellation.getMsgToInvestor());
        model.addAttribute("licenseCancellationMessage", cancellationMessage);
        
        LicenseClearanceSetData licenseClearanceSetData;
        model.addAttribute("allowCancellationLetter", checkEntityStatus(model));
        setUpModel(model, globalLicenseCancellation);
        try {
            srId = Integer.parseInt(globalLicenseCancellation.getSrID());
        } catch (NumberFormatException e) {
            srId = 0;
        }

        String stage = globalLicenseCancellation.getStage();
        String srStCode = globalLicenseCancellation.getSrStCode();

        // if the status is revoked and the stage is greater that 01 we should redirect to step 1
        boolean backToStage1ToSubmit = false;
        if(StringUtils.containsIgnoreCase(getEntityStatus(), ENTITY_STATUS_REVOKED) && !"01".equals(stage)){
            stage = "01";
            globalLicenseCancellation.setStage(stage);
            backToStage1ToSubmit = true;
        }

        // if we should redirect to step one and the request is also rejected we should allow to submit documents
        // for License Cancellation Letter
        boolean backToStage1ToReview = backToStage1ToSubmit && E0003.equals(srStCode);
        
        LOG.info("**** IsInstant "+globalLicenseCancellation.getIsInstant()+" ^^^^ srStCode: "+srStCode+" ***stage: "+stage+" ^^^^ CrNO: "+globalLicenseCancellation.getCrNo()+" ***** ZakathNo: "+globalLicenseCancellation.getZakatNo()+" ^^^^backToStage1ToSubmit: "+backToStage1ToSubmit+" ******* backToStage1ToReview: "+backToStage1ToReview);
        model.addAttribute("isInstant", globalLicenseCancellation.getIsInstant());
        model.addAttribute("crNo", globalLicenseCancellation.getCrNo());
        model.addAttribute("zakathNo", globalLicenseCancellation.getZakatNo());
        if (backToStage1ToReview || ("01".equals(stage) && (srId == 0 || E0003.equals(srStCode)))) {
        	LOG.info("**** 1 ");
            model.addAttribute(LICENSE_CLEARANCE_FORM_DATA, new LicenseClearanceFormData());
            model.addAttribute(SUPPORTING_ATTACHMENTS, sagiaLicenseCancellationFacade.readLicenseCancellationSupportingAttachments(SagiaFacadesConstants.LICENSE_CLEARANCE_SUPPORTING_ATTACHMENTS));
            if( E0003.equals(srStCode)){
                String statusMes = messageSource.getMessage("licenseReplacement.status", null,
                        i18nService.getCurrentLocale());
                GlobalMessages.addConfMessage(model, statusMes+globalLicenseCancellation.getSrStDesc());
                licenseClearanceSetData = sagiaLicenseCancellationFacade.getLicenseClearanceSetData(globalLicenseCancellation.getSrID(), stage);
                if(licenseClearanceSetData != null && CollectionUtils.isNotEmpty(licenseClearanceSetData.getLicenseClearToTextNav())) {
                    for (LicenseToTextNavData data : licenseClearanceSetData.getLicenseClearToTextNav()) {
                        GlobalMessages.addConfMessage(model, data.getComments());
                    }
                }
            }
            boolean draftExists = sagiaDraftFacade.isDraftExists(FORM_CANCEL_LICENSE_STAGE_1);
            model.addAttribute(DRAFT_EXISTS, draftExists);
            return getView(model, SAGIA_CANCELLATION_CMS_PAGE_1);
        }


        if (backToStage1ToSubmit || ("01".equals(stage) && E0001.equals(srStCode))) {
        	LOG.info("**** 2 ");
            licenseClearanceSetData = sagiaLicenseCancellationFacade.getLicenseClearanceSetData(globalLicenseCancellation.getSrID(), stage);
            model.addAttribute(UPLOADED_ATTACHMENTS, licenseClearanceSetData.getLicenseClearToContentNav());
            model.addAttribute(PROCESSING_TIME, averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME_CLEARANCE));
            return getView(model, SAGIA_CANCELLATION_CMS_PAGE);
        }

        if (srId > 0 && "02".equals(stage)) {
        	LOG.info("**** 3 ");
            licenseClearanceSetData = sagiaLicenseCancellationFacade.getLicenseClearanceSetData(globalLicenseCancellation.getSrID(), stage);
            model.addAttribute(UPLOADED_ATTACHMENTS, licenseClearanceSetData.getLicenseClearToContentNav());
            return getView(model, SAGIA_CANCELLATION_CMS_PAGE);
        }

        if (srId == 0) {
        	LOG.info("**** 4 ");
            boolean draftExists = sagiaDraftFacade.isDraftExists(FORM_CANCEL_LICENSE_STAGE_1);
            model.addAttribute(DRAFT_EXISTS, draftExists);
            return getView(model, SAGIA_CANCELLATION_CMS_PAGE_1);
        }

        return checkNextStages(model, globalLicenseCancellation);
    }

	private boolean checkEntityStatus(Model model) {
		String entityStatus = getEntityStatus();
		List<String> allowedStatuses = Arrays.asList("active", "expired", "revoked");
		boolean allowedEntityStatus = allowedStatuses.stream()
				.anyMatch(allowedStatus -> StringUtils.containsIgnoreCase(entityStatus, allowedStatus));
		if (!allowedEntityStatus) {
			GlobalMessages.addErrorMessage(model, "licenseCancellation.document.upload.stage1.entityError");
			return false;
		}
		return true;
	}

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:MethodCyclomaticComplexity","squid:S3776","squid:S134"})
    private String checkNextStages(Model model, GlobalLicenseCancellation globalLicenseCancellation) throws CMSItemNotFoundException, IOException {
        final String stage = globalLicenseCancellation.getStage();
        if ("03".equals(stage)) {
        	LOG.info("**** 5 ");
            return getView(model, SAGIA_CANCELLATION_CMS_PAGE_2);
        }
        final String srStCode = globalLicenseCancellation.getSrStCode();
        if ("04".equals(stage) && (srStCode.isEmpty() || E0003.equals(srStCode))) {
        	LOG.info("**** 6 ");
            model.addAttribute(LICENSE_CANCEL_FORM_DATA, new LicenseCancelFormData());
            model.addAttribute(SUPPORTING_ATTACHMENTS, sagiaLicenseCancellationFacade.readLicenseCancellationSupportingAttachments(SagiaFacadesConstants.LICENSE_CANCEL_SUPPORTING_ATTACHMENTS));
            if( E0003.equals(srStCode)){
                String statusMes = messageSource.getMessage("licenseReplacement.status", null,
                        i18nService.getCurrentLocale());
                GlobalMessages.addConfMessage(model, statusMes+globalLicenseCancellation.getSrStDesc());
                LicenseCancellationSetData licenseCancellationSetData = sagiaLicenseCancellationFacade.getLicenseCancellationSetData(globalLicenseCancellation.getSrID(), stage);
                if(licenseCancellationSetData != null && CollectionUtils.isNotEmpty(licenseCancellationSetData.getLicenseCancelToTextNav())) {
                    for (LicenseToTextNavData data : licenseCancellationSetData.getLicenseCancelToTextNav()) {
                        GlobalMessages.addConfMessage(model, data.getComments());
                    }
                }
            }
            boolean draftExists = sagiaDraftFacade.isDraftExists("formCancelLicenseStage4");
            model.addAttribute(DRAFT_EXISTS, draftExists);
            return getView(model, SAGIA_CANCELLATION_CMS_PAGE_3);
        }

        if ("04".equals(stage) && E0001.equals(srStCode)) {

            LicenseCancellationSetData licenseCancellationSetData = sagiaLicenseCancellationFacade.getLicenseCancellationSetData(globalLicenseCancellation.getSrID(), stage);
            if(isCurrentRequestInProcess(globalLicenseCancellation)) {
                GlobalMessages.addConfMessage(model, messageSource.getMessage("license.cancel.inprocess.notification", null, i18nService.getCurrentLocale()));
            }
            model.addAttribute(UPLOADED_ATTACHMENTS, licenseCancellationSetData.getLicenseCancelToContentNav());
            model.addAttribute(PROCESSING_TIME, averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME_CANCEL));
            return getView(model, SAGIA_CANCELLATION_CMS_PAGE);
        }

        if ("05".equals(stage) && E0002.equals(srStCode)) {
            return getView(model, SAGIA_CANCELLATION_CMS_PAGE_4);
        }

        boolean draftExists = sagiaDraftFacade.isDraftExists(FORM_CANCEL_LICENSE_STAGE_1);
        model.addAttribute(DRAFT_EXISTS, draftExists);
        return getView(model, SAGIA_CANCELLATION_CMS_PAGE_1);
    }

	private boolean isCurrentRequestInProcess(GlobalLicenseCancellation licenseCancellationSetData) {
		String inProcessText = messageSource.getMessage("license.cancel.status.inprocess", null,
				i18nService.getCurrentLocale());
		return StringUtils.equalsIgnoreCase(licenseCancellationSetData.getSrStDesc(), inProcessText);
	}

	private String getView(Model model, String sagiaCancellationCmsPage3) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(sagiaCancellationCmsPage3));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(sagiaCancellationCmsPage3));
        return getViewForPage(model);
    }

    private void setUpModel(Model model, GlobalLicenseCancellation globalLicenseCancellation) {
        model.addAttribute(SR_ID, globalLicenseCancellation.getSrID());
        model.addAttribute(STAGE, globalLicenseCancellation.getStage());
        model.addAttribute(GLOBAL_LICENSE_CANCELLATION, globalLicenseCancellation);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/attachment/{objectId}/{documentID}")
    @RequireHardLogIn
    public ResponseEntity<InputStreamResource> getAttachments(@PathVariable("objectId") String objectId, @PathVariable("documentID") String documentId, HttpServletRequest request, Model model) {
        try {
            InputStream complaintAndEnquiry = contentDetailsService.readAttachmentBy(objectId, documentId);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(complaintAndEnquiry);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            return new ResponseEntity<>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(STACKTRACE_LOG, e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/stage1/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public String submitClearanceData(final Model model, @ModelAttribute("licenseClearanceFormData") final LicenseClearanceFormData licenseClearanceFormData, final BindingResult result, final RedirectAttributes redirectModel) throws JSONException {
    	
    	if(licenseClearanceFormData.getIsInstant()) {
    		if (StringUtils.isBlank(licenseClearanceFormData.getCrNo())) {
    			result.rejectValue("crNo", "general.entercrno");
            }
            if (StringUtils.isBlank(licenseClearanceFormData.getZakathNo())) {
            	result.rejectValue("zakathNo", "general.enterzakathno");
            }
    		if (!licenseClearanceFormData.getTermsAndConditionsChecked()) {
    			result.rejectValue("termsAndConditionsChecked", "general.accepttermsandconditions");
    		}
    	}else {
    		getLicenseClearanceValidator().validate(licenseClearanceFormData, result);
    	}
    	
        if (result.hasErrors()) {
            redirectModel.addFlashAttribute(LICENSE_CLEARANCE_FORM_DATA, licenseClearanceFormData);
            redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.licenseClearanceFormData", result);
            return REDIRECT_PREFIX + MY_SAGIA_LICENSE_CANCEL;
        }
        try {
            sagiaLicenseCancellationFacade.createLicenseClearance(licenseClearanceFormData);
            return getLicenseCancellation(model);
        } catch (Exception ex) {
            LOG.error(STACKTRACE_LOG, ex);
            if (StringUtils.isEmpty(ex.getMessage())) {
                return REDIRECT_PREFIX + MY_SAGIA_LICENSE_CANCEL;
            }
            String jsonError = new JSONObject(ex.getMessage().substring(ex.getMessage().indexOf('{'))).getString("error");
            if (StringUtils.isNotEmpty(jsonError)) {
                String jsonMessage = new JSONObject(jsonError).getString("message");
                if (StringUtils.isNotEmpty(jsonMessage)) {
                    String jsonValue = new JSONObject(jsonMessage).getString("value");
                    if (StringUtils.isNotEmpty(jsonValue)) {
                        GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, jsonValue);
                    }
                }
            }

            return REDIRECT_PREFIX + MY_SAGIA_LICENSE_CANCEL;
        }
    }

    @RequestMapping(value = "/stage/next", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public String nextStageClearanceData(final Model model, @ModelAttribute("licenseClearanceFormData") final LicenseClearanceFormData licenseClearanceFormData, final BindingResult result) {
        try {
            GlobalLicenseCancellation globalLicenseCancellation = sagiaLicenseCancellationFacade.getGlobalLicenseCancellation("","");
            LicenseClearanceSetData licenseClearanceSetData = sagiaLicenseCancellationFacade.getLicenseClearanceSetData(globalLicenseCancellation.getSrID(),globalLicenseCancellation.getStage());
            sagiaLicenseCancellationFacade.updateGlobalLicenseCancellation(licenseClearanceSetData);
            return getLicenseCancellation(model);
        } catch (CMSItemNotFoundException e) {
            return e.getMessage();
        } catch (IOException e) {
            LOG.error(STACKTRACE_LOG,e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/stage4/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public ResponseEntity submitCancellationData(final Model model, @ModelAttribute("licenseCancelFormData") final LicenseCancelFormData licenseCancelFormData, final BindingResult result,RedirectAttributes redirectModel) {
        try {
        	if(licenseCancelFormData.getIsInstant()) {
        		if (!licenseCancelFormData.getTermsAndConditionsChecked()) {
        			result.rejectValue("termsAndConditionsChecked", "general.accepttermsandconditions");
        		}
        	}else {
        		sagialicenseCancelValidator.validate(licenseCancelFormData,result);
        	}
            
            if(result.hasErrors()) {
                return new ResponseEntity(new Gson().toJson(result.getAllErrors()), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            sagiaLicenseCancellationFacade.createLicenseCancellation(licenseCancelFormData);
            GlobalLicenseCancellation latestCancellationData = sagiaLicenseCancellationFacade.getGlobalLicenseCancellation("","");
            return new ResponseEntity(latestCancellationData.getSrID(), HttpStatus.CREATED);
        } catch (Exception ex) {
            LOG.error(STACKTRACE_LOG,ex);
            return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public Validator getLicenseClearanceValidator() {
        return licenseClearanceValidator;
    }

    public void setLicenseClearanceValidator(Validator licenseClearanceValidator) {
        this.licenseClearanceValidator = licenseClearanceValidator;
    }
}
