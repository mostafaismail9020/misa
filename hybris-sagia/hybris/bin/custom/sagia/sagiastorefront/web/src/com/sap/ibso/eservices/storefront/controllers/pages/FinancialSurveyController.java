package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.core.sagia.services.LicenseApplyService;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.sagia.services.impl.DefaultSagiaDraftService;
import com.sap.ibso.eservices.facades.data.MOJInheritSet;
import com.sap.ibso.eservices.facades.data.license.amendment.LicenseAmendment;
import com.sap.ibso.eservices.facades.data.license.amendment.Shareholder;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItems;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaFinancialSurveyFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaIsicFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.UpdatableComplaintDetails;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaLicenseAmendmentValidator;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.user.data.FinancialSurvey;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/my-sagia/financial-survey/complete")
public class FinancialSurveyController extends SagiaAbstractPageController {
    private static final Logger LOG = Logger.getLogger(DefaultSagiaDraftService.class);
    private static final String SAGIA_FINANCIAL_SURVEY_CMS_PAGE = "financial-survey";
    private static final String BATCHNO = "batchNo";
    private static final String USERINPUT = "userInput";
    private static final String ENTITY_NAME = "AmendHeaders";
    private static final Integer BATCH_SIZE_FOR_PRODUCTS_SEARCH = 100;
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

    @Resource(name = "licenseApplyService")
    private LicenseApplyService licenseApplyService;

    @RequestMapping(value = {"", "/display/{quarterCode}"}, method = RequestMethod.GET)
    @LicenseRequired
    @RequireHardLogIn
    public String completeFinancialSurvey(final Model model, final HttpServletRequest request, @PathVariable(name="quarterCode", required = false) String quarterCode) throws CMSItemNotFoundException {

        model.addAttribute("controllerUrl", "/my-sagia/financial-survey");
        model.addAttribute("draftExists", sagiaDraftFacade.isJsonDraftExists(SAGIA_FINANCIAL_SURVEY_DRAFT+quarterCode));
		//SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SAGIA_LICENSE_AMEND);
       // model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());
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
        sagiaDraftFacade.removeJsonDraft(SAGIA_FINANCIAL_SURVEY_DRAFT);
        return financialSurvey;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public List<LicenseAmendment> amendLicenseHeaders() {
        return sagiaFinancialSurveyFacade.getLicenseAmendmentsHeaders();
    }


    @RequestMapping(value = "/{quarterCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public FinancialSurvey getFinancialSurvey(@PathVariable String quarterCode) {
        return sagiaFinancialSurveyFacade.getFinancialSurvey(quarterCode);
    }

    @RequestMapping(value = "/amendmentTypes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public LicenseAmendment getAmendmentTypes(@RequestBody LicenseAmendment licenseAmendment) {
        licenseAmendment.setErrors(new HashSet<>());

        Set<String> errors = new SagiaLicenseAmendmentValidator().validate(licenseAmendment);
        if (!errors.isEmpty()) {
            licenseAmendment.setErrors(errors);
            return licenseAmendment;
        }

        LicenseAmendment licenseWithAmendmentTypes = sagiaFinancialSurveyFacade.getLicenseAmendmentTypes(licenseAmendment);

        licenseWithAmendmentTypes.setNoChanges(false);
        Set<String> businessValidationErrors = licenseWithAmendmentTypes.getErrors();
        if (businessValidationErrors.isEmpty() && licenseWithAmendmentTypes.getAmendmentTypesView().isEmpty()) {
            licenseWithAmendmentTypes.setNoChanges(true);
        }
        return licenseWithAmendmentTypes;
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
        return sagiaFinancialSurveyFacade.getShareholder(shareholderId);
    }

    @RequestMapping(value = "/verifyInherit/{deceasedId}/{deedNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequireHardLogIn
    public MOJInheritSet getVerifyInherit(@PathVariable("deceasedId") String deceasedId, @PathVariable("deedNumber") String deedNumber, final Model model) {
    	boolean mojVerified = false;
    	MOJInheritSet mojInherit = sagiaFinancialSurveyFacade.getVerifyInherit(deceasedId, deedNumber);
    	if("X".equalsIgnoreCase(mojInherit.getIsMojVerified())) {
    		mojVerified = true;
    	}
    	model.addAttribute("isMojVerified", mojVerified);
    	model.addAttribute("mojDeceasedName", mojInherit.getDeceasedName());
    	return mojInherit;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public String getFiltersForProducts(@RequestBody Map<String, String> filtersList) {
        String top = String.valueOf(BATCH_SIZE_FOR_PRODUCTS_SEARCH);

        String skip;
        if (filtersList.get(BATCHNO) != null && Integer.parseInt(filtersList.get(BATCHNO)) > 0) {
            skip = String.valueOf(((Integer.parseInt(filtersList.get(BATCHNO)) - 1) * BATCH_SIZE_FOR_PRODUCTS_SEARCH) - 1);
        } else {
            skip = "0";
        }
        Collection<ProductData> productsList;
        if ((filtersList.get(USERINPUT)) != null && !filtersList.get(USERINPUT).isEmpty()) {
            if (StringUtils.isNumeric(filtersList.get(USERINPUT))) {
                productsList = sagiaFinancialSurveyFacade.getAmendProductsListWithId(filtersList.get(USERINPUT), skip, top);
            } else {
                productsList = sagiaFinancialSurveyFacade.getAmendProductsListWithDescription(filtersList.get(USERINPUT), skip, top);
            }
        } else {
            productsList = sagiaFinancialSurveyFacade.getAmendProductsList(skip, top);
        }

        if (CollectionUtils.isNotEmpty(productsList)) {
            return  new Gson().toJson(productsList);
        }
        return "[]";
    }


    @RequestMapping(value = "/isic/{licenseType}", method = RequestMethod.GET)
    @ResponseBody
    public Map getIsic(@PathVariable String licenseType, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
       // validateUser(request, response);
        //return sagiaIsicFacade.getIsicFromMasterData(licenseType);
        return sagiaIsicFacade.getIsic(licenseType);
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



    @RequestMapping(value = "/saveAttachment", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    public void saveDraftMultipartData(
            @RequestParam(value = "letterOfSupportFile", required = false) MultipartFile file,
            @RequestParam(value = "CustomField") String id,
            MultipartHttpServletRequest request, HttpServletResponse response
    ) {


        id.toUpperCase();
        file.getName();


       // sagiaDraftFacade.save(draftData, id);
    }



}
