package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.sagia.services.impl.DefaultSagiaDraftService;
import com.sap.ibso.eservices.facades.data.license.amendment.LicenseAmendment;
import com.sap.ibso.eservices.facades.data.license.amendment.Shareholder;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItems;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseAmendmentFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaLicenseAmendmentValidator;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.facades.data.MOJInheritSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/my-sagia/license/amend")
public class LicenseAmendmentController extends SagiaAbstractPageController {
    private static final Logger LOG = Logger.getLogger(DefaultSagiaDraftService.class);
    private static final String SAGIA_LICENSE_AMEND_CMS_PAGE = "license-amend";
    private static final String BATCHNO = "batchNo";
    private static final String USERINPUT = "userInput";
    private static final String ENTITY_NAME = "AmendHeaders";
    private static final Integer BATCH_SIZE_FOR_PRODUCTS_SEARCH = 100;
    private static final String SAGIA_LICENSE_AMEND = "ZSR5";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "defaultLicenseAmendmentFacade")
    private SagiaLicenseAmendmentFacade sagiaLicenseAmendmentFacade;

    @Resource(name = "sagiaTermsAndConditionsFacade")
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;
	    
    @Resource(name = "sagiaSearchService")
    private SagiaSearchService searchService;

    @RequestMapping(value = {"", "/display/{srId}"}, method = RequestMethod.GET)
    @LicenseRequired
    @RequireHardLogIn
    public String amendLicense(final Model model, final HttpServletRequest request, @PathVariable(name="srId", required = false) String srId) throws CMSItemNotFoundException {
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute("controllerUrl", "/my-sagia/license");
        model.addAttribute("draftExists", sagiaDraftFacade.isJsonDraftExists(SAGIA_LICENSE_AMEND));
		SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SAGIA_LICENSE_AMEND);
        model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());


        model.addAttribute("srId", "");
        if (request.getRequestURI().contains("display")) {
            model.addAttribute("srId", srId != null ? srId : "");
        }

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_AMEND_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_AMEND_CMS_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public LicenseAmendment saveLicenseAmendment(@RequestBody LicenseAmendment licenseAmendment) {

        Set<String> errors = new SagiaLicenseAmendmentValidator().validate(licenseAmendment);
        if (!errors.isEmpty()) {
            licenseAmendment.setErrors(errors);
            return licenseAmendment;
        }

        sagiaLicenseAmendmentFacade.saveLicenseAmendment(licenseAmendment);
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel)userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
        sagiaDraftFacade.removeJsonDraft(SAGIA_LICENSE_AMEND);
        return licenseAmendment;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public List<LicenseAmendment> amendLicenseHeaders() {
        return sagiaLicenseAmendmentFacade.getLicenseAmendmentsHeaders();
    }

    @RequestMapping(value = "/checkAvailability", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity checkAvailability() {
        try {
            sagiaLicenseAmendmentFacade.checkLicenseAmendmentAvailability();
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(),e);
            throw new SagiaCRMException(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{srId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public LicenseAmendment getLicense(@PathVariable String srId) {
        return sagiaLicenseAmendmentFacade.getLicenseAmendment(srId);
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

        LicenseAmendment licenseWithAmendmentTypes = sagiaLicenseAmendmentFacade.getLicenseAmendmentTypes(licenseAmendment);

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
        return sagiaLicenseAmendmentFacade.getListItems();
    }

    @RequestMapping(value = "/shareholder/{shareholderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseBody
    public Shareholder getShareholder(@PathVariable String shareholderId) {
        return sagiaLicenseAmendmentFacade.getShareholder(shareholderId);
    }
    
    @RequestMapping(value = "/verifyInherit/{deceasedId}/{deedNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequireHardLogIn
    public MOJInheritSet getVerifyInherit(@PathVariable("deceasedId") String deceasedId, @PathVariable("deedNumber") String deedNumber, final Model model) {
    	boolean mojVerified = false;
    	MOJInheritSet mojInherit = sagiaLicenseAmendmentFacade.getVerifyInherit(deceasedId, deedNumber);
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
                productsList = sagiaLicenseAmendmentFacade.getAmendProductsListWithId(filtersList.get(USERINPUT), skip, top);
            } else {
                productsList = sagiaLicenseAmendmentFacade.getAmendProductsListWithDescription(filtersList.get(USERINPUT), skip, top);
            }
        } else {
            productsList = sagiaLicenseAmendmentFacade.getAmendProductsList(skip, top);
        }

        if (CollectionUtils.isNotEmpty(productsList)) {
            return  new Gson().toJson(productsList);
        }
        return "[]";
    }
}
