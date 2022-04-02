package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONException;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.sagia.services.impl.DefaultSagiaDraftService;
import com.sap.ibso.eservices.facades.data.license.amendment.Shareholder;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItems;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaFinancialSurveyFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaIsicFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.UpdatableComplaintDetails;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto.FinancialStatementForm;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaFinancialSurveyValidator;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.user.data.FinancialSurvey;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.ticket.service.TicketAttachmentsService;
import de.hybris.platform.util.localization.Localization;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/my-sagia/financial-survey/complete")
public class FinancialSurveyController extends SagiaAbstractPageController {
    private static final Logger LOG = Logger.getLogger(DefaultSagiaDraftService.class);
    private static final String SAGIA_FINANCIAL_SURVEY_CMS_PAGE = "financial-survey";
    private static final String MAX_FILE_IN_MB = "2";
    private static final String SAGIA_FINANCIAL_SURVEY_DRAFT = "FSURVEY_";
    private static final String PDF = "pdf";


    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "defaultFinancialSurveyFacade")
    private SagiaFinancialSurveyFacade sagiaFinancialSurveyFacade;

    @Resource(name = "sagiaTermsAndConditionsFacade")
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;

    @Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;

    @Resource(name = "defaultSagiaIsicFacade")
    private SagiaIsicFacade sagiaIsicFacade;

    @Resource(name = "defaultTicketAttachmentsService")
    private TicketAttachmentsService ticketAttachmentsService;


    private static final String SERVICE_ID = "ZS11";
    private static final String FORM_GLOBAL_ERROR = "form.global.error";
    private static final String FORM_SURVEY_GLOBAL_ERROR = "financial.survey.global.error";

    @RequestMapping(value = {"", "/display/{quarterCode}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    public String completeFinancialSurvey(final Model model, final HttpServletRequest request, @PathVariable(name="quarterCode", required = false) String quarterCode) throws CMSItemNotFoundException {

        model.addAttribute("controllerUrl", "/my-sagia/financial-survey");
        model.addAttribute("maxUploadSize", MAX_FILE_IN_MB);
        model.addAttribute("financialStatementForm", new FinancialStatementForm());

        if (request.getRequestURI().contains("display")) {
            model.addAttribute("quarterCode", quarterCode != null ? quarterCode : "");
        }


        model.addAttribute("srId", "");

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_FINANCIAL_SURVEY_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_FINANCIAL_SURVEY_CMS_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public FinancialSurvey saveFinancialSurvey(@RequestBody FinancialSurvey financialSurvey) {

        sagiaFinancialSurveyFacade.saveFinancialSurvey(financialSurvey);

        /*Set<String> errors = new SagiaLicenseAmendmentValidator().validate(financialSurvey);
        if (!errors.isEmpty()) {
            financialSurvey.setErrors(errors);
            return financialSurvey;
        }*/

        // sagiaFinancialSurveyFacade.saveLicenseAmendment(financialSurvey);
        //   sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel)userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
        // sagiaDraftFacade.removeJsonDraft(SAGIA_FINANCIAL_SURVEY_DRAFT);
        return financialSurvey;
    }


    @RequestMapping(value = "/saveCompanyProfile" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public FinancialSurvey saveFinancialSurveyCompanyProfileSection(@RequestBody FinancialSurvey financialSurvey) {

        Set<String> errors = new SagiaFinancialSurveyValidator().validateEntity(financialSurvey);

        if (!errors.isEmpty()) {
            financialSurvey.setErrors(errors);
            return financialSurvey;
        }
        sagiaFinancialSurveyFacade.saveFinancialSurveyCompanyProfile(financialSurvey);

        return financialSurvey;
    }


    @RequestMapping(value = "/saveShareholderEquity" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public FinancialSurvey saveShareholderEquity(@RequestBody FinancialSurvey financialSurvey) {

        Set<String> errors = new SagiaFinancialSurveyValidator().validateEntity(financialSurvey);//TODO Change by Equity validator
        if (!errors.isEmpty()) {
            financialSurvey.setErrors(errors);
            return financialSurvey;
        }
        sagiaFinancialSurveyFacade.saveShareholderEquity(financialSurvey);

        return financialSurvey;
    }


    @RequestMapping(value = "/saveBrnachesAndSubsidiaries" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public FinancialSurvey saveBrnachesAndSubsidiaries(@RequestBody FinancialSurvey financialSurvey) {

        Set<String> errors = new SagiaFinancialSurveyValidator().validateBranchesAndSubsidiaries(financialSurvey);
        if (!errors.isEmpty()) {
            financialSurvey.setErrors(errors);
            return financialSurvey;
        }
        sagiaFinancialSurveyFacade.saveFinancialSurveyBranchesAndSubsidiaries(financialSurvey);

        return financialSurvey;
    }

    @RequestMapping(value = "/saveShareholders" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public FinancialSurvey saveShareholders(@RequestBody FinancialSurvey financialSurvey) {

        /*Set<String> errors = new SagiaFinancialSurveyValidator().validateBranchesAndSubsidiaries(financialSurvey);
        if (!errors.isEmpty()) {
            financialSurvey.setErrors(errors);
            return financialSurvey;
        }*/
        sagiaFinancialSurveyFacade.saveFinancialSurveyShareholders(financialSurvey);

        return financialSurvey;
    }



    @RequestMapping(value = "/{quarterCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public FinancialSurvey getFinancialSurvey(@PathVariable String quarterCode) {
        return sagiaFinancialSurveyFacade.getFinancialSurvey(quarterCode);
    }



    @RequestMapping(value = "/listItems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public ListItems getListItems() {
        return sagiaFinancialSurveyFacade.getListItems();
    }

    @RequestMapping(value = "/shareholder/{shareholderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public Shareholder getShareholder(@PathVariable String shareholderId) {
        return null ;//sagiaFinancialSurveyFacade.getShareholder(shareholderId);
    }



    @RequestMapping(value = "/isic/{licenseType}", method = RequestMethod.GET)
    @ResponseBody
    public Map getIsic(@PathVariable String licenseType, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        // validateUser(request, response);
       // return sagiaIsicFacade.getIsicFromMasterData(licenseType);
        return sagiaIsicFacade.getAllIsic();
    }


    /**
     * read a single complaint, by id, with $expand parameter
     * @param srID id of the complaint
     */
    @RequestMapping(method = RequestMethod.GET, path = "/messages/{srID}")
    @RequireHardLogIn
    public String getComplaintAndEnquiry(@PathVariable("srID") String srID, HttpServletRequest request, Model model)  {
        try {
            FinancialSurvey financialSurvey = sagiaFinancialSurveyFacade.getFinancialSurvey(srID);
           /* ComplaintsAndEnquiryHdrsData complaintAndEnquiry = complaintsAndEnquiryService
                    .getComplaintBy(srID);
            model.addAttribute("expandedComplaintFormData", complaintAndEnquiry);
            model.addAttribute("messages", complaintAndEnquiry.getCompAndEnqHdrToTextNav());
            model.addAttribute("uploadedAttachments", complaintAndEnquiry.getCompAndEnqHdrToContentNav());*/
            model.addAttribute("messages", financialSurvey.getMessages());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return "fragments/financialSurvey/questionMessage";
    }

    /**
     * update an existing complaint
     * the CRM service does not return the entity back after the update is made
     * request again the entity by id
     * @param srID id of the complaint
     * @param detailsToUpdate fiels to be updated
     */
    @RequestMapping(value = "/{srID}/update", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @RequireHardLogIn
    public @ResponseBody
    FinancialSurvey updateComplaint(@PathVariable("srID") String srID,
                                    @RequestBody UpdatableComplaintDetails detailsToUpdate, HttpServletRequest request, final BindingResult result) {

        // sagiaComplaintDetailsValidator.validate(detailsToUpdate, result);

        if (result.hasErrors()) {
            //    return complaintsAndEnquiryService.getComplaintBy(srID);
            return null;
        } else {
            sagiaFinancialSurveyFacade.addFinancialSurveyMessage(detailsToUpdate.getTextMsg(), srID);
            FinancialSurvey financialSurvey = sagiaFinancialSurveyFacade.getFinancialSurvey(srID);
            return financialSurvey;
        }
    }




    /**
     * Create a Financial Statement request
     * @param model
     * @param financialStatementForm request payload
     * @param result
     */
    @RequestMapping(value="/saveAttachment", method = RequestMethod.POST, headers = ("content-type=multipart/form-data") )
    @RequireHardLogIn
    public String submit(final Model model,  @ModelAttribute("financialStatementForm") final FinancialStatementForm financialStatementForm,
                         final BindingResult result, RedirectAttributes redirectModel)  throws JSONException {
        //financialStatementValidator.validate(financialStatementForm, result);

        LOG.info("saveAttachment");

        FinancialSurvey financialSurvey = sagiaFinancialSurveyFacade.getFinancialSurvey(financialStatementForm.getSrId());
        if (! ( financialSurvey.getIsCompanyProfileSectionFilled() && financialSurvey.getIsEquitySectionFilled()
                && financialSurvey.getIsShareholdersSectionFilled() && financialSurvey.getIsBranchSectionFilled() ))  {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, Localization.getLocalizedString(FORM_SURVEY_GLOBAL_ERROR));
            getSessionService().setAttribute("financialStatementForm", financialStatementForm);
            if(!financialSurvey.getIsCompanyProfileSectionFilled()){
                return REDIRECT_PREFIX + "/my-sagia/financial-survey/complete/display/"+financialStatementForm.getSrId()+"#tab1";
            }else if (!financialSurvey.getIsBranchSectionFilled()) {
                return REDIRECT_PREFIX + "/my-sagia/financial-survey/complete/display/"+financialStatementForm.getSrId()+"#tab2";
            }else if (!financialSurvey.getIsEquitySectionFilled()) {
                return REDIRECT_PREFIX + "/my-sagia/financial-survey/complete/display/"+financialStatementForm.getSrId()+"#tab3";
            }else if (!financialSurvey.getIsShareholdersSectionFilled()) {
                return REDIRECT_PREFIX + "/my-sagia/financial-survey/complete/display/"+financialStatementForm.getSrId()+"#tab4";
            }
            return REDIRECT_PREFIX + "/my-sagia/financial-survey/complete/display/"+financialStatementForm.getSrId()+"#tab5";
        }

        LOG.info("saveAttachment Start Processing");

        if (financialStatementForm.getFiles().size() <= 0) {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, Localization.getLocalizedString(FORM_GLOBAL_ERROR));
            //throw new IllegalArgumentException("Please upload only the requested files");
            getSessionService().setAttribute("financialStatementForm", financialStatementForm);
            redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.financialStatementForm", result);
            return REDIRECT_PREFIX + "/my-sagia/financial-survey/complete/display/"+financialStatementForm.getSrId()+"#tab5";
        }

        if (result.hasErrors()) {
            getSessionService().setAttribute("financialStatementForm", financialStatementForm);
            redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.financialStatementForm", result);
            return REDIRECT_PREFIX + "/my-sagia/financial-survey/complete/display/"+financialStatementForm.getSrId()+"#tab5";
        }

        try {

            List<MediaModel> attachments = null;
            MediaModel mediaModel = null;
            if (CollectionUtils.isNotEmpty(financialStatementForm.getFiles()))
            {

                for (final MultipartFile file : financialStatementForm.getFiles())
                {
                    if (file.getSize() == 0) { //no file added
                        continue;
                     }
                    try
                    {
                        mediaModel = ticketAttachmentsService.createAttachment(file.getOriginalFilename(), file.getContentType(),
                                file.getBytes(), userService.getCurrentUser());
                        // submit Financial Survey for Review
                    }
                    catch (final IOException e)
                    {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
            sagiaFinancialSurveyFacade.submitFinancialSurveyForReview(mediaModel,financialStatementForm.getSrId());
            sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.FINANCIAL_STATEMENT);
            redirectModel.addFlashAttribute("requestFeedback", "true");
        } catch (Exception e) {
            LOG.warn(e.getMessage(),e);
        }
        return REDIRECT_PREFIX + "/my-sagia/financial-survey/complete/display/"+financialStatementForm.getSrId()+"#tab5";
    }


    @ModelAttribute("financialStatementForm")
    public FinancialStatementForm getFinancialStatementForm() {
        return new FinancialStatementForm();
    }




}
