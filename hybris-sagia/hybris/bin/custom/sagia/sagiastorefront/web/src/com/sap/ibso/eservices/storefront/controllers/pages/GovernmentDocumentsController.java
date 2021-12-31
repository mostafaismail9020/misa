package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.AmanahData;
import com.sap.ibso.eservices.facades.data.BranchData;
import com.sap.ibso.eservices.facades.data.WasselCheckData;

import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaGovernmentDocumentsFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtHeaderData;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.localization.Localization;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/governmentDocuments")
public class GovernmentDocumentsController extends AbstractPageController {
    private static final String FORM_GLOBAL_ERROR = "form.global.error";

    @Resource(name = "sagiaLicenseFacade")
    private SagiaLicenseFacade licenseFacade;

    @Resource(name = "sagiaGovernmentDocumentsFacade")
    private SagiaGovernmentDocumentsFacade governmentDocumentsFacade;

    @Resource(name = "sagiaBranchValidator")
    private Validator branchValidator;

    @Autowired
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Autowired
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Autowired
    private UserService userService;

    private static final String SAGIA_GOVERNMENT_DOCUMENTS_CMS_PAGE = "government-documents";
    private static final String SAGIA_DOCUMENTS_BRANCHES_CMS_PAGE = "documents-branches";


    private static final String BRANCH_STATUS_NOT_COMPLETED = "N";

    private static final Logger LOG = Logger.getLogger(GovernmentDocumentsController.class);
    private static final String ENTITY_NAME = "LicenseReplaceMents";
    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    /**
     * The page with the form for the main branch
     *
     * @param model
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getMainBranch(final Model model) throws CMSItemNotFoundException {

        WasselCheckData wasselCheckData = governmentDocumentsFacade.requestWasselCheck();
        model.addAttribute("wasselCheckMessage",wasselCheckData.getMessage());

        BranchData branchData = licenseFacade.getGovtHeader("");
        model.addAttribute("statusCode", branchData.getStatusCode());

        model.addAttribute("branchData", branchData);
        model.addAttribute("amanahs", governmentDocumentsFacade.getAmanahList());
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_GOVERNMENT_DOCUMENTS_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_GOVERNMENT_DOCUMENTS_CMS_PAGE));
        return getViewForPage(model);
    }


    /**
     * The branch submitted in the first step of the government documents process is handled by this method.
     * It redirects to page which updates all the branches and submit the request if the validation passes.
     * If not, it redirects back and display the messages.
     *
     * @param branch - Branch DTO which is submitted.
     * @param bindingResult - The binding result created by the validation.
     * @param redirectModel - Redirect model used for adding the bindingResult of the validation in case validation fails.
     */
    @RequireHardLogIn
    @RequestMapping(path = "/branches", method = RequestMethod.POST)
    public String getBranches(final BranchData branch, final Model model, BindingResult bindingResult,
                              final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
        if(branch.getStatusCode().equalsIgnoreCase(BRANCH_STATUS_NOT_COMPLETED)){
            getBranchValidator().validate(branch, bindingResult);

            if (bindingResult.hasErrors()) {
                GlobalMessages.addErrorMessage(model, Localization.getLocalizedString(FORM_GLOBAL_ERROR));

                GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, Localization.getLocalizedString(FORM_GLOBAL_ERROR));
                redirectModel.addFlashAttribute("branchData", branch);
                redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.branchData", bindingResult);

                return REDIRECT_PREFIX + "/governmentDocuments";
            }
        }
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.GOVERNMENT_DOCUMENTS);
        model.addAttribute("googleMapKey",sagiaConfigurationFacade.getGoogleMapKey());



        Collection<BranchData> branches = licenseFacade.getBranches(((CustomerModel)userService.getCurrentUser()).getEntityID());
        model.addAttribute("branches", branches);
        model.addAttribute("branchesJSON", new Gson().toJson(branches));

        List<AmanahData> amanahs = governmentDocumentsFacade.getAmanahList();
        model.addAttribute("amanahs", amanahs);

        model.addAttribute("mainBranchJSON", new Gson().toJson(branch));

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_DOCUMENTS_BRANCHES_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_DOCUMENTS_BRANCHES_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * This method intercept the Ajax request when all the branches are submitted and the javascript validation passes.
     * It saves them to the CRM.
     *
     * @param branchData - The main branch containing all the subset of its sub-branches posted if the javascript validation passes.
     * @return - A ResponseEntity with the service Id returned from CRM if the post to CRM succeed, internal server error otherwise.
     */
    @RequireHardLogIn
    @RequestMapping(path = "/updateBranches", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateBranches(@RequestBody BranchData branchData, final Model model) {
        try {
            GovtHeaderData response = licenseFacade.saveGovernmentHeader(branchData);
            return new ResponseEntity(new Gson().toJson(response.getServiceId()), HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error("stacktrace",e);
            return new ResponseEntity(new Gson().toJson(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Validator getBranchValidator() {
        return branchValidator;
    }

    public void setBranchValidator(Validator branchValidator) {
        this.branchValidator = branchValidator;
    }
}


