/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.WarningLettersData;
import com.sap.ibso.eservices.facades.data.specialservices.FollowUpData;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaFollowUpFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMCreateException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateViolationReplyFormData;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.response.SagiaAjaxResponse;
import com.sap.ibso.eservices.storefront.response.SagiaResponse;
import com.sap.ibso.eservices.storefront.response.SagiaResponseStatus;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/violation-replies")
public class SagiaViolationRepliesController extends SagiaAbstractPageController {
    private static final Logger LOG = Logger.getLogger(SagiaViolationRepliesController.class);

    private static final String VIOLATION_REPLIES_PAGE        = "violation-replies";
    private static final String VIOLATION_REPLIES_CREATE_PAGE = "violation-replies-create";
    private static final String ENTITY_NAME = "FollowupServices";
    private static final String VIOLATION_REPLIES_CREATE_FORM = "violationRepliesCreate";
    private static final String VIOLATION_REPLIES_SERVICE_ID  = "ZFLUP_02";
    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "sagiaFollowUpFacade")
    private SagiaFollowUpFacade sagiaFollowUpFacade;

    @Resource(name = "violationReplyValidator")
    private Validator violationReplyValidator;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;

    @Autowired
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/display/{srId}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String violationsRepliesPage(@PathVariable(name = "srId", required = false) final String srId, final Model model) throws CMSItemNotFoundException {

        final List<FollowUpData> violationReplies = sagiaFollowUpFacade.getViolationReplyEntries();
        model.addAttribute("replies", violationReplies);
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));

        String selectedElementId;
        if (violationReplies != null && !violationReplies.isEmpty()) {
            // Consider the service request ID if present, otherwise select the first element in the list
            if (srId != null)
            {
                // Filter the violation replies for the service request ID and mark it as selected
                Optional<FollowUpData> violationReply = violationReplies.stream().filter(reply -> srId.equals(reply.getSrId())).findFirst();
                if (violationReply.isPresent())
                {
                    selectedElementId = srId;
                    violationReply.get().setCurrent(true);
                }
                else
                {
                    // Auto-fix: no violation reply found for service request ID, therefore select the first one of the list
                    selectedElementId = violationReplies.get(0).getSrId();
                    violationReplies.get(0).setCurrent(true);
                }
            }
            else
            {
                // There is no service request ID, therefore select the first one of the list
                selectedElementId = violationReplies.get(0).getSrId();
                violationReplies.get(0).setCurrent(true);
            }

            final FollowUpData selectedItem = sagiaFollowUpFacade.getFollowUpEntry(selectedElementId);
            model.addAttribute("selectedItem", selectedItem);
        }

        storeCmsPageInModel(model, getContentPageForLabelOrId(VIOLATION_REPLIES_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(VIOLATION_REPLIES_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(value = "/{srID}", method = RequestMethod.GET)
    @RequireHardLogIn
    public String warningLetterDetails(@PathVariable("srID") String srID, HttpServletRequest request, Model model)   {
        try {
            model.addAttribute("selectedItem", sagiaFollowUpFacade.getFollowUpEntry(srID));
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
        return "fragments/followup/expandedViolationReply";
    }

    @RequestMapping(value = "/create/{letterNumber}", method = RequestMethod.GET)
    @RequireHardLogIn
    public String violationDetails(@PathVariable("letterNumber") String letterNumber, HttpServletRequest request, Model model)   {
        try {
            model.addAttribute("warningLetter", sagiaFollowUpFacade.getWarningLetterData(letterNumber));
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
        return "fragments/followup/violationRepliesCreateViolations";
    }

	@RequestMapping(value = "/latest", method = RequestMethod.GET)
	@RequireHardLogIn
	public ResponseEntity<FollowUpData> getLatestViolationReply(HttpServletRequest request, Model model) {
		FollowUpData latestFollowUp = sagiaFollowUpFacade
						.getViolationReplyEntries()
						.stream()
						.findFirst()
						.orElse(null);
		return new ResponseEntity<>(latestFollowUp, HttpStatus.OK);
	}

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String violationsRepliesCreatePage(final Model model) throws CMSItemNotFoundException {
        try {
            sagiaFollowUpFacade.checkCreateViolationReply();
            model.addAttribute("violationReplyForm",new CreateViolationReplyFormData());
            final List<WarningLettersData> warningLettersData = sagiaFollowUpFacade.getWarningLettersViolationRepliesData();
            model.addAttribute("warningLetters", warningLettersData);

            boolean draftExists = sagiaDraftFacade.isDraftExists(VIOLATION_REPLIES_SERVICE_ID);
            model.addAttribute("draftExists", draftExists);
            model.addAttribute("formName", VIOLATION_REPLIES_CREATE_FORM);
            model.addAttribute("serviceId", VIOLATION_REPLIES_SERVICE_ID);

            if (warningLettersData != null && !warningLettersData.isEmpty()) {
                model.addAttribute("warningLetter", warningLettersData.get(0));
            }

        }
        /* This exception throws when you have a one request with the status "in process" */
        catch (SagiaCRMException e) {
            LOG.warn(e.getMessage(), e);
            return REDIRECT_PREFIX + "/violation-replies";
        }
        catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
        
        storeCmsPageInModel(model, getContentPageForLabelOrId(VIOLATION_REPLIES_CREATE_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(VIOLATION_REPLIES_CREATE_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public SagiaAjaxResponse checkViolationReply(final Model model) throws JSONException {
        try {
            sagiaFollowUpFacade.checkCreateViolationReply();
            return new SagiaAjaxResponse(0);
        } catch (SagiaCRMException e) {
            LOG.info(e.getMessage(),e);
            String message = "";
            if(e.getMessage().indexOf('{') >= 0){
                String jsonError = new JSONObject(e.getMessage().substring(e.getMessage().indexOf('{'))).getString("error");
                if(StringUtils.isNotEmpty(jsonError)){
                    String jsonMessage = new JSONObject(jsonError).getString("message");
                    if(StringUtils.isNotEmpty(jsonMessage)){
                        message = new JSONObject(jsonMessage).getString("value");
                    }
                }
            }
            return new SagiaAjaxResponse(StringUtils.isNotEmpty(message) ? message : "Another Violation Reply is in Progress", 1);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity<SagiaResponse> createViolationReplies(@RequestBody CreateViolationReplyFormData form, BindingResult bindingResult)   {
            getViolationReplyValidator().validate(form,bindingResult);
            if(bindingResult.hasErrors()){
                return new ResponseEntity<>(new SagiaResponse(SagiaResponseStatus.VALIDATION_ERROR, new Gson().toJson(bindingResult.getAllErrors())),HttpStatus.BAD_REQUEST);
            }

            try {
                sagiaFollowUpFacade.createViolationReply(form);
                sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel)userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.FOLLOW_UP);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            catch (SagiaCRMCreateException ex) {
                LOG.warn(ex.getMessage(),ex);
                return new ResponseEntity<>(new SagiaResponse(SagiaResponseStatus.ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
            }
    }

    public Validator getViolationReplyValidator() {
        return violationReplyValidator;
    }

    public void setViolationReplyValidator(Validator violationReplyValidator) {
        this.violationReplyValidator = violationReplyValidator;
    }
}
