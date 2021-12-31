package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryHDR;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaLegalConsultationFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaPaginationFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.LegalInquiryHDRService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto.LegalConsultationFormData;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaLegalConsultationValidator;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/legalconsultations")
public class SagiaLegalConsultationController extends SagiaAbstractPageController {
    private static final Logger LOG = Logger.getLogger(SagiaLegalConsultationController.class);
    private static final String SAGIA_LEGAL_CONSULTATION_CREATE_CMS_PAGE = "legal-consultation-create";
    private static final String SAGIA_LEGAL_CONSULTATION_CMS_PAGE = "legal-consultation";
    private static final String ENTITY_NAME = "LegalInquiryHDRSet";
    private static final String SERVICE_ID = "ZS20";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "legalInquiryHDRService")
    private LegalInquiryHDRService legalInquiryHDRService;

    @Resource(name = "sagiaLegalConsultationValidator")
    SagiaLegalConsultationValidator sagiaLegalConsultationValidator;

    @Resource(name = "sagiaLegalConsultationFacade")
    private SagiaLegalConsultationFacade sagiaLegalConsultationFacade;

    @Resource(name = "defaultSagiaPaginationFacade")
    private SagiaPaginationFacade sagiaPaginationFacade;

    @Resource(name = "sagiaTermsAndConditionsFacade")
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;
	
	@Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;

    /**
     * read  Legal Consultation by srID
     * @return details for a specific Legal Consultation entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{srId}/details")
    @RequireHardLogIn
    public ResponseEntity<LegalInquiryHDR> getLegalConsultationDetails(@PathVariable("srId") String srId,
                                                                       HttpServletRequest request, Model model) {
        LegalInquiryHDR legalInquiryHDR = sagiaLegalConsultationFacade.getLegalInquiryHDR(srId);
        return new ResponseEntity<>(legalInquiryHDR, HttpStatus.OK);
    }

    /**
     * read Legal Consultations list
     * @param model
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(path= {"","/display/{srId}"},method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getLegalConsultationList(final Model model, final HttpServletRequest request, @PathVariable(name = "srId", required=false) String srId) throws CMSItemNotFoundException{
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        try {
            model.addAttribute("legalInquiryTypes", sagiaLegalConsultationFacade.getLegalInquiryTypes());
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }

        List<LegalInquiryHDR> legalConsultations = sagiaLegalConsultationFacade.getLegalInquiryHDR();
        getSessionService().setAttribute("legalConsultations", legalConsultations);
        model.addAttribute("legalConsultations", legalConsultations);

        if (!legalConsultations.isEmpty()) {
            String selectedLegalConsultationId = legalConsultations.get(0).getSrId();
            if (StringUtils.isNotEmpty(srId) && legalConsultations.stream().anyMatch(l -> srId.equals(l.getSrId()))) {
                selectedLegalConsultationId = srId;
            }

            LegalInquiryHDR latestLegalConsultation = sagiaLegalConsultationFacade.getLegalInquiryHDR(selectedLegalConsultationId);
            model.addAttribute("latestLegalConsultation", latestLegalConsultation);
        }

        model.addAttribute("serviceDescription", getServiceDescription(request.getServletPath()));
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LEGAL_CONSULTATION_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LEGAL_CONSULTATION_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }

    /**
     * @return page for creating a new Legal Consultation request
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String newLegalConsultation(final Model model) throws CMSItemNotFoundException {
        try {
			SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SERVICE_ID);
        	model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());
            model.addAttribute("legalInquiryTypes", sagiaLegalConsultationFacade.getLegalInquiryTypes());
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }

        boolean draftExists = sagiaDraftFacade.isDraftExists(SERVICE_ID);
        model.addAttribute("draftExists", draftExists);
        model.addAttribute("serviceId", SERVICE_ID);

        model.addAttribute("legalConsultationFormData", new LegalConsultationFormData());
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LEGAL_CONSULTATION_CREATE_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LEGAL_CONSULTATION_CREATE_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }

    /**
     * Create a legal consultation request
     * @param model
     * @param legalConsultationFormData request payload
     * @param result
     */
    @RequestMapping(value="/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public String submit(final Model model, @ModelAttribute("legalConsultationFormData") final LegalConsultationFormData legalConsultationFormData,
                         final BindingResult result, RedirectAttributes redirectModel)  throws JSONException {
        sagiaLegalConsultationValidator.validate(legalConsultationFormData, result);
        if (result.hasErrors()) {
            getSessionService().setAttribute("legalConsultationFormData", legalConsultationFormData);
            redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.legalConsultationFormData", result);
            return REDIRECT_PREFIX + "/legalconsultations";
        }

        if (legalConsultationFormData.getFiles().size() > 4) {
            throw new IllegalArgumentException("Please upload only the requested files");
        }
        try {
            legalInquiryHDRService.createLegalConsultation(legalConsultationFormData);
            sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.LEGAL_CONSULTATION);
            redirectModel.addFlashAttribute("requestFeedback", "true");
        } catch (SagiaCRMException e) {
            LOG.warn(e.getMessage(),e);
            int status = 0;
            if (e.getMessage().indexOf('{') >= 0) {
                status = Integer.valueOf(e.getMessage().substring(0, e.getMessage().indexOf('{')).replaceAll("[^0-9]", ""));
                LOG.info("Legal status creation status: " + status);
                String jsonError = new JSONObject(e.getMessage().substring(e.getMessage().indexOf('{'))).getString("error");
                if (StringUtils.isNotEmpty(jsonError)) {
                    String jsonMessage = new JSONObject(jsonError).getString("message");
                    if (StringUtils.isNotEmpty(jsonMessage)) {
                        redirectModel.addFlashAttribute("errorMessage", new JSONObject(jsonMessage).getString("value"));
                    }
                }
            }
        }
        return REDIRECT_PREFIX + "/legalconsultations";
    }

    /**
     * Displays a legal consultation based on its id
     * @param srId
     * @param model
     * @param request
     */

    @RequestMapping(method = RequestMethod.GET, path = "/{srId}")
    @RequireHardLogIn
    public String getLegalConsultationBy(@PathVariable("srId") String srId, HttpServletRequest request, Model model) {
        try {
            model.addAttribute("legalConsultation_detail", sagiaLegalConsultationFacade.getLegalInquiryHDR(srId));
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        }
        return "fragments/legalConsultation/expandedLegalConsultation";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/attachment/{objectId}/{documentID}")
    @RequireHardLogIn
    public ResponseEntity<InputStreamResource> getComplaintAttachment(@PathVariable("objectId") String objectId,
                                                                      @PathVariable("documentID") String documentId) {
        try {
            InputStream attachmentStream = sagiaLegalConsultationFacade.readAttachment(objectId, documentId);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(attachmentStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            return new ResponseEntity<>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @ModelAttribute("legalConsultationFormData")
    public LegalConsultationFormData getLegalConsultationFormData() {
        return new LegalConsultationFormData();
    }
}





