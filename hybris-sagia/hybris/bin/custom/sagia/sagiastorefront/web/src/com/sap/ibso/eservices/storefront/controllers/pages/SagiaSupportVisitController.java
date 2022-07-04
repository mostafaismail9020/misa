package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ibso.eservices.facades.data.DocumentAttachment;
import com.sap.ibso.eservices.facades.data.SupportVisit;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.impl.SagiaSupportVisitFacade;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.storefront.forms.SupportVisitForm;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaSupportVisitValidator;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.util.localization.Localization;
import org.apache.log4j.Logger;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ws.security.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "/support-visits")
public class SagiaSupportVisitController  extends AbstractPageController {

    private static final Logger LOG = Logger.getLogger(SagiaSupportVisitController.class);
    private static final String SUPPORT_VISIT_PAGE = "support-visits";
    private static final String SUPPORT_VISIT_CREATE_PAGE = "support-visits-create";
    private static final String TEXT_ERROR_SUPPORT_VISITS = "text.error.supportVisits";
    private static final String SUPPORT_VISITS_CREATE = "/support-visits/create";
    private static final String ORG_SPRINGFRAMEWORK_VALIDATION_BINDING_RESULT = "org.springframework.validation.BindingResult.";
    private static final String SUPPORT_VISIT = "supportVisit";
    private static final String ENTITY_NAME = "FollowupServices";
    private static final String SUPPORTS_VISITS_SERVICE_ID = "ZSVR";
    
    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;
    
    @Autowired
    private SagiaSupportVisitFacade supportVisitFacade;

    @Resource(name = "sagiaSupportVisitValidator")
    private SagiaSupportVisitValidator sagiaSupportVisitValidator;

    @Resource
    private SagiaDraftFacade sagiaDraftFacade;
	
	@Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;
	

    @RequestMapping(path= {"","display/{srId}"},method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String supportVisitPage(final Model model, @PathVariable(name = "srId", required=false) String srId) throws CMSItemNotFoundException {
        final List<SupportVisit> supportVisits = supportVisitFacade.getSupportVisits();

        if(supportVisits != null && CollectionUtils.isNotEmpty(supportVisits)){
            SupportVisit selectedVisit;
            if(srId != null){
                Optional<SupportVisit> supportVisit = supportVisits.stream().filter(visit -> srId.equals(visit.getSrId())).findFirst();
                if(supportVisit.isPresent()){
                    selectedVisit = supportVisitFacade.getSupportVisit(srId);
                    model.addAttribute("fromServiceRequestOverview",true);
                }
                else{
                    selectedVisit = supportVisitFacade.getSupportVisit(supportVisits.stream().findFirst().get().getSrId());
                }
            }
            else{
                selectedVisit = supportVisitFacade.getSupportVisit(supportVisits.stream().findFirst().get().getSrId());
            }
            model.addAttribute("selectedSupportVisit",selectedVisit);
        }

        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute("supportVisits", supportVisits);
        
        SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SUPPORTS_VISITS_SERVICE_ID);
        model.addAttribute("sagiaService", sagiaService);
        
        storeCmsPageInModel(model, getContentPageForLabelOrId(SUPPORT_VISIT_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SUPPORT_VISIT_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(value = "/{srId}", method = RequestMethod.GET)
    @ResponseBody
    @RequireHardLogIn
    public String supportVisitDetails(@PathVariable("srId") String srID) {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(supportVisitFacade.getSupportVisit(srID));
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String supportVisitCreatePage(HttpServletRequest request, Model model) throws CMSItemNotFoundException {
        boolean draftExists = sagiaDraftFacade.isDraftExists(SUPPORTS_VISITS_SERVICE_ID);
        model.addAttribute("draftExists", draftExists);
        model.addAttribute("serviceId", SUPPORTS_VISITS_SERVICE_ID);
        
		SagiaServiceModel sagService = searchService.getSagiaServiceByCode(SUPPORTS_VISITS_SERVICE_ID);  
		model.addAttribute("sagiaService", sagService);
        model.addAttribute("maxUploadSize", sagService.getMaxFileUploadSize());

        model.addAttribute(SUPPORT_VISIT, new SupportVisitForm());
        storeCmsPageInModel(model, getContentPageForLabelOrId(SUPPORT_VISIT_CREATE_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SUPPORT_VISIT_CREATE_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public String saveSupportVisitCreatePage(@ModelAttribute(SUPPORT_VISIT) final SupportVisitForm supportVisit, RedirectAttributes redirectAttributes, final BindingResult result)   {
        try {
            sagiaSupportVisitValidator.validate(supportVisit, result);

            if (result.hasErrors()) {
                getSessionService().setAttribute(SUPPORT_VISIT, supportVisit);
                redirectAttributes.addFlashAttribute(ORG_SPRINGFRAMEWORK_VALIDATION_BINDING_RESULT + SUPPORT_VISIT, result);
                return REDIRECT_PREFIX + SUPPORT_VISITS_CREATE;
            }
            SupportVisit supportVisitDTO = new SupportVisit();
            supportVisitDTO.setDateString(supportVisit.getDateString());
            supportVisitDTO.setTextMsg(supportVisit.getAddInfo() + "-"+ supportVisit.getMessage());
            List<MultipartFile> files = supportVisit.getFiles();
            setAttachments(supportVisitDTO, files);
            if (!saveSupportVisit(redirectAttributes, supportVisitDTO)) {
                return REDIRECT_PREFIX + SUPPORT_VISITS_CREATE;
            }
            redirectAttributes.addFlashAttribute("requestFeedback", "true");
            return REDIRECT_PREFIX + SUPPORT_VISITS_CREATE;
        } catch (Exception ex) {
            LOG.error(ex.getMessage(),ex);
            flashMessage(redirectAttributes, null);
            return REDIRECT_PREFIX + SUPPORT_VISITS_CREATE;
        }
    }

    private void flashMessage(RedirectAttributes redirectAttributes, String jsonErrorMessage) {
        GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER,
                StringUtils.isNotEmpty(jsonErrorMessage) ?  jsonErrorMessage : Localization.getLocalizedString(TEXT_ERROR_SUPPORT_VISITS));
    }

    private boolean saveSupportVisit(RedirectAttributes redirectAttributes, SupportVisit supportVisitDTO) throws JSONException {
        try {
            supportVisitFacade.saveSupportVisit(supportVisitDTO);
        } catch (SagiaCRMException ex){
            LOG.error("Displaying CRM error message to the user. ");
            parseExceptionAndAddFlashMessage(redirectAttributes, ex);
            return false;
        }
        return true;
    }

    private void parseExceptionAndAddFlashMessage(RedirectAttributes redirectAttributes, SagiaCRMException ex) throws JSONException {
        if (StringUtils.isEmpty(ex.getMessage())) {
            flashMessage(redirectAttributes, null);
            return;
        }

        String jsonError = new JSONObject(ex.getMessage().substring(ex.getMessage().indexOf("{"))).getString("error");
        if (StringUtils.isEmpty(jsonError)) {
            flashMessage(redirectAttributes, null);
            return;
        }

        String jsonMessage = new JSONObject(jsonError).getString("message");
        if (StringUtils.isEmpty(jsonMessage)) {
            String jsonErrorMessage = new JSONObject(jsonMessage).getString("value");
            flashMessage(redirectAttributes, jsonErrorMessage);
            return;
        }
        String jsonValue = new JSONObject(jsonMessage).getString("value");
        if (StringUtils.isNotEmpty(jsonValue)) {
            GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, jsonValue);
            return;
        }

        flashMessage(redirectAttributes, null);
    }

    private void setAttachments(SupportVisit supportVisitDTO, List<MultipartFile> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        Set<DocumentAttachment> attachments = new HashSet<>();
        for (MultipartFile file : files) {
            DocumentAttachment doc = new DocumentAttachment();
            if (file.getSize() > 0) {
                doc.setFileName(StringUtils.substringBefore(file.getOriginalFilename(), "."));
                doc.setMimeType(file.getContentType());
                try {
                    doc.setFileContent(Base64.encode(file.getBytes()));
                } catch (IOException e) {
                    LOG.error("Content could not be parsed for file: " + file.getOriginalFilename(), e);
                }
                attachments.add(doc);
            }
        }
        supportVisitDTO.setSupportVstToAttachNav(attachments);
    }

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    @RequireHardLogIn
    public ResponseEntity getLatestVisit(HttpServletRequest request, Model model)   {
        final List<SupportVisit> supportVisits = supportVisitFacade.getSupportVisits();
        SupportVisit supportVisit = supportVisits.stream().findFirst().orElse(null);
        return new ResponseEntity<>(supportVisit, HttpStatus.CREATED);
    }
}
