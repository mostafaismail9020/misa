package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.facades.data.WarningLettersData;
import com.sap.ibso.eservices.facades.data.specialservices.FollowUpData;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaFollowUpFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMCreateException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateWarningLetterExtensionFormData;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.response.SagiaAjaxResponse;
import com.sap.ibso.eservices.storefront.response.SagiaResponse;
import com.sap.ibso.eservices.storefront.response.SagiaResponseStatus;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.controllers.pages
 * @link http://sap.com/
 * @copyright 2018 SAP
 */

@Controller
@RequestMapping(value = "/warning-letters")
public class SagiaWarningLettersExtensionController extends SagiaAbstractPageController {
    private static final Logger LOG = Logger.getLogger(SagiaViolationRepliesController.class);

    private static final String WARNING_LETTERS_EXTENSION_PAGE = "warning-letters";
    private static final String WARNING_LETTERS_EXTENSION_CREATE_PAGE = "warning-letters-create";
    private static final String WARNING_LETTERS_EXTENSION_CREATE_FORM = "warningLetterExtensionCreate";
    private static final String WARNING_LETTERS_EXTENSION_SERVICE_ID = "ZFLUP_03";
    private static final String ENTITY_NAME = "FollowupServices";
    
    
    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;
    
    @Resource(name = "sagiaFollowUpFacade")
    private SagiaFollowUpFacade sagiaFollowUpFacade;
    
    @Resource(name = "warningLettersExtensionValidator")
    private Validator warningLettersExtensionValidator;
    
    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;
    
    @Resource(name = "sagiaTermsAndConditionsFacade")
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;
    
    @Resource(name = "userService")
    private UserService userService;
    
    @Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;
    

    @RequestMapping(value = {"", "/display/{srId}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String warningLettersPage(@PathVariable(name = "srId", required = false) final String srId, final Model model) 
    		throws CMSItemNotFoundException {
    	
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        storeCmsPageInModel(model, getContentPageForLabelOrId(WARNING_LETTERS_EXTENSION_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(WARNING_LETTERS_EXTENSION_PAGE));

        final List<FollowUpData> warningLetters = sagiaFollowUpFacade.getWarningLetterEntries();
        model.addAttribute("warningLetters", warningLetters);

        String selectedElementId;
        if (warningLetters != null && !warningLetters.isEmpty()) {
            // Consider the service request ID if present, otherwise select the first element in the list
            if (srId != null)
            {
                // Filter the warning letter for the service request ID and mark it as selected
                Optional<FollowUpData> warningLetter = warningLetters.stream().filter(letter -> srId.equals(letter.getSrId())).findFirst();
                if (warningLetter.isPresent())
                {
                    selectedElementId = srId;
                    warningLetter.get().setCurrent(true);
                }
                else
                {
                    // Auto-fix: no warning letter found for service request ID, therefore select the first one of the list
                    selectedElementId = warningLetters.get(0).getSrId();
                    warningLetters.get(0).setCurrent(true);
                }
            }
            else
            {
                // There is no service request ID, therefore select the first one of the list
                selectedElementId = warningLetters.get(0).getSrId();
                warningLetters.get(0).setCurrent(true);
            }

            final FollowUpData selectedItem = sagiaFollowUpFacade.getFollowUpEntry(selectedElementId);
            model.addAttribute("selectedItem", selectedItem);
        }

        SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(WARNING_LETTERS_EXTENSION_SERVICE_ID);
        model.addAttribute("sagiaService", sagiaService);
        return getViewForPage(model);
    }

    @RequestMapping(value = "/{srID}", method = RequestMethod.GET)
    @RequireHardLogIn
    public String warningLetterDetails(@PathVariable("srID") String srID, HttpServletRequest request, Model model) {
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute("selectedItem", sagiaFollowUpFacade.getFollowUpEntry(srID));
        return "fragments/followup/expandedWarningLetter";
    }

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    @RequireHardLogIn
    public ResponseEntity<FollowUpData> getLatestWarningLetter(HttpServletRequest request, Model model) {
        FollowUpData latestFollowUp = sagiaFollowUpFacade
                .getWarningLetterEntries()
                .stream()
                .findFirst()
                .orElse(null);
        return new ResponseEntity<>(latestFollowUp, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String warningLettersCreatePage(final Model model) throws CMSItemNotFoundException {
        try {
            sagiaFollowUpFacade.checkCreateViolationReply();
            model.addAttribute("createWarningLetterExtensionFormData", new CreateWarningLetterExtensionFormData());
            final List<WarningLettersData> warningLettersData = sagiaFollowUpFacade.getWarningLettersExtensionData();
            model.addAttribute("warningLetters", warningLettersData);

            boolean draftExists = sagiaDraftFacade.isDraftExists(WARNING_LETTERS_EXTENSION_SERVICE_ID);
            model.addAttribute("formName", WARNING_LETTERS_EXTENSION_CREATE_FORM);
            model.addAttribute("serviceId", WARNING_LETTERS_EXTENSION_SERVICE_ID);
            model.addAttribute("draftExists", draftExists);

            if (warningLettersData != null && !warningLettersData.isEmpty()) {
                model.addAttribute("selectedWarningLetter", warningLettersData.get(0));
            }
            
            SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(WARNING_LETTERS_EXTENSION_SERVICE_ID);
            model.addAttribute("sagiaService", sagiaService);
            
        } catch (SagiaCRMException e) { // This exception is thrown when you have a one request with the status "in process"
            LOG.error(e.getMessage(), e);
            return REDIRECT_PREFIX + "/warning-letters";
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }

        storeCmsPageInModel(model, getContentPageForLabelOrId(WARNING_LETTERS_EXTENSION_CREATE_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(WARNING_LETTERS_EXTENSION_CREATE_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public SagiaAjaxResponse checkWarningLetter(final Model model) throws JSONException {
        try {
            sagiaFollowUpFacade.checkCreateWarningLetter();
            return new SagiaAjaxResponse(0);
        } catch (SagiaCRMException e) {
            LOG.info(e.getMessage(), e);
            String message = "";
            if (e.getMessage().indexOf('{') >= 0) {
                String jsonError = new JSONObject(e.getMessage().substring(e.getMessage().indexOf('{'))).getString("error");
                if (StringUtils.isNotEmpty(jsonError)) {
                    String jsonMessage = new JSONObject(jsonError).getString("message");
                    if (StringUtils.isNotEmpty(jsonMessage)) {
                        message = new JSONObject(jsonMessage).getString("value");
                    }
                }
            }
            return new SagiaAjaxResponse(StringUtils.isNotEmpty(message) ? message : "Another warning Letter is in Progress", 1);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity<SagiaResponse> createWarningLetter(@RequestBody CreateWarningLetterExtensionFormData form, BindingResult bindingResult) {
        getWarningLettersExtensionValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new SagiaResponse(SagiaResponseStatus.VALIDATION_ERROR, new Gson().toJson(bindingResult.getAllErrors())), HttpStatus.BAD_REQUEST);
        }

        try {
            sagiaFollowUpFacade.createWarningLetterExtension(form);
            sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.FOLLOW_UP);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SagiaCRMCreateException ex) {
            LOG.info(ex.getMessage(), ex);
            return new ResponseEntity<>(new SagiaResponse(SagiaResponseStatus.ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/create/{letterNumber}", method = RequestMethod.GET)
    @RequireHardLogIn
    public String violationDetails(@PathVariable("letterNumber") String letterNumber, HttpServletRequest request, Model model) {
        try {
            model.addAttribute("warningLetter", sagiaFollowUpFacade.getWarningLetterData(letterNumber));
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
        return "fragments/followup/warningLettersCreateTimeExcerpt";
    }

    @RequestMapping(value = "/date-info", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public SagiaDateData getDateInfo(@RequestParam final String date, @RequestParam final Integer days) {
        if (days < 0) {
            final SagiaDateData result = new SagiaDateData();
            result.setDateFormatted("");
            return result;
        }
        try {
            return sagiaFollowUpFacade.getDateData(date, days);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            final SagiaDateData result = new SagiaDateData();
            result.setDateFormatted("");
            return result;
        }
    }

    public Validator getWarningLettersExtensionValidator() {
        return warningLettersExtensionValidator;
    }

    public void setWarningLettersExtensionValidator(Validator warningLettersExtensionValidator) {
        this.warningLettersExtensionValidator = warningLettersExtensionValidator;
    }
}
