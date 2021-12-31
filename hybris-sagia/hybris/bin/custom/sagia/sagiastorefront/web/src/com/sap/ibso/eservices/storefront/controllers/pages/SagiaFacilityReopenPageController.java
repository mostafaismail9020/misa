package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.reopenfacility.ReopenFacility;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.facades.sagia.impl.DefaultSagiaLicenseFacade;
import com.sap.ibso.eservices.facades.sagia.impl.DefaultSagiaReopenFacilityFacade;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityAddress;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import com.sap.ibso.eservices.storefront.util.JsonResponseWrapper;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.localization.Localization;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/facility-reopen")
public class SagiaFacilityReopenPageController extends SagiaAbstractPageController {

    private static final String SAGIA_FACILITY_REOPEN_CMS_PAGE = "facility-reopen";
    private static final String SAGIA_CREATE_FACILITY_REOPEN_CMS_PAGE = "facility-reopen-create";
    private static final String SERVICE_ID = "Z_ONLINE";
    private static final String SAGIA_CREATE_FACILITY_FORM = "facilityReopen";
    private static final String REOPEN_FACILITY= "/facility-reopen";
    private static final String REOPEN_FACILITY_CREATE= "/facility-reopen/create";

    @Resource(name = "sagiaReopenFacilityFacade")
    private DefaultSagiaReopenFacilityFacade sagiaReopenFacilityFacade;

    @Resource(name = "sagiaLicenseFacade")
    private DefaultSagiaLicenseFacade sagiaLicenseFacade;

    @Resource(name = "sagiaReopenFacilityValidator")
    private Validator reopenFacilityValidator;

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;
	
	@Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;

    @Autowired
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getFacilityReopen(final Model model, final HttpServletRequest request) throws CMSItemNotFoundException {
        ReopenFacilityAddress reopenFacilityHistory = sagiaReopenFacilityFacade.loadReopenHistory();

        model.addAttribute("reopenFacilityRequests", reopenFacilityHistory);
        ReopenFacility reopenFacility = new ReopenFacility();
        if (CollectionUtils.isNotEmpty(reopenFacilityHistory.getServiceRequests())) {
            String srId = reopenFacilityHistory.getServiceRequests().stream().findFirst().get().getObjectId();
            reopenFacility = sagiaReopenFacilityFacade.loadDecisionExecutionVisit(srId);

        }
        model.addAttribute("serviceDescription", getServiceDescription(request.getServletPath()));
        model.addAttribute("reopenFacility", reopenFacility);
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_FACILITY_REOPEN_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_FACILITY_REOPEN_CMS_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(value = "get/{srId}", method = RequestMethod.GET)
    @RequireHardLogIn
    public @ResponseBody
    String getFacilityReopenItem(@PathVariable("srId") String srId) {
        ReopenFacility reopenFacilityItem = sagiaReopenFacilityFacade.loadDecisionExecutionVisit(srId);
        return new Gson().toJson(reopenFacilityItem);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getCreateFacilityReOpen(final Model model) throws CMSItemNotFoundException {
        ReopenFacilityAddress reopenFacilityHistory = sagiaReopenFacilityFacade.loadReopenHistory();
        if(reopenFacilityHistory != null && !BooleanUtils.isTrue(reopenFacilityHistory.getCreateRequestEnabled())){
            return REDIRECT_PREFIX + "/facility-reopen";
        }

		SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SERVICE_ID);
        model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());
        model.addAttribute("reopenFacility", new ReopenFacility());

        boolean draftExists = sagiaDraftFacade.isDraftExists(SERVICE_ID);
        model.addAttribute("draftExists", draftExists);
        model.addAttribute("serviceId", SERVICE_ID);
        model.addAttribute("formId", SAGIA_CREATE_FACILITY_FORM);

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CREATE_FACILITY_REOPEN_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CREATE_FACILITY_REOPEN_CMS_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RequireHardLogIn
    public String postCreateFacilityReOpen(ReopenFacility reopenFacility, final Model model, BindingResult bindingResult, final RedirectAttributes redirectModel) throws ODataException {
        JsonResponseWrapper response = new JsonResponseWrapper();
        reopenFacilityValidator.validate(reopenFacility, bindingResult);
        if (!bindingResult.hasErrors()) {
            if(reopenFacility.getAttachmentIds().isEmpty()){
                throw new SagiaCRMException(getMessageSource().getMessage("validation.attachments.required", null, getI18nService().getCurrentLocale()));
            }
            response.setSuccess(true);
            sagiaReopenFacilityFacade.saveDecisionExecutionVisit(reopenFacility);
            sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.FOLLOW_UP);
            return REDIRECT_PREFIX + REOPEN_FACILITY;
        } else {
            response.setSuccess(false);
            HashMap<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(e -> {
                String message = messageSource.getMessage(e, getI18nService().getCurrentLocale());
                errors.put(e.getField(), message);
            });
            response.setFormErrors(errors);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, Localization.getLocalizedString("reopenFacility.form.error"));
            return REDIRECT_PREFIX + REOPEN_FACILITY_CREATE;

        }
    }
}
