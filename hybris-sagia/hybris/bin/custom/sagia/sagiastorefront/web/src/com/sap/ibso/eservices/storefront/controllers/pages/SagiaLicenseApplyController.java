package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.event.SagiaPublishLicenseEvent;
import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.PersonShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.core.sagia.services.LicenseApplyService;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.odata.ServiceRequestCreation;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import com.sap.ibso.eservices.facades.data.zqeemah.Product;
import com.sap.ibso.eservices.facades.data.zqeemah.*;
import com.sap.ibso.eservices.facades.data.zqeemah2.*;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.facades.sagia.impl.DefautSagiaLicenseApplyFacade;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.ZQeemahService;
import com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils;
import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import com.sap.ibso.eservices.storefront.controllers.SagiaConstants;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.SagiaApplyReviewForm;
import com.sap.ibso.eservices.storefront.forms.validation.license.SagiaLicenseApplyValidator;
import com.sap.ibso.eservices.storefront.forms.validation.license.contact.ContactPersonValidator;
import com.sap.ibso.eservices.storefront.forms.validation.license.entity.EntityInfoValidator;
import com.sap.ibso.eservices.storefront.forms.validation.license.shareholder.ExistingShareHoldersValidator;
import com.sap.ibso.eservices.storefront.forms.validation.license.shareholder.OrganizationShareHoldersValidator;
import com.sap.ibso.eservices.storefront.forms.validation.license.shareholder.PersonShareHoldersValidator;
import com.sap.ibso.eservices.storefront.util.SagiaContextFormErrorsConverter;
import com.sap.nic.NICUserData;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.Constants;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/my-sagia/license")
public class SagiaLicenseApplyController extends SagiaAbstractPageController {
    private static final Logger LOG = LoggerFactory.getLogger(SagiaLicenseApplyController.class);

    private static final String SAGIA_LICENSE_APPLY_CMS_PAGE = "license-apply";
    private static final String SAGIA_LICENSE_NEW_APPLY_CMS_PAGE = "new-license-apply";
    private static final String SAGIA_LICENSE_SHAREHOLDER_APPLY_CMS_PAGE = "shareholder-apply";
    private static final String SAGIA_LICENSE_CONTACTPERSON_APPLY_CMS_PAGE = "new-apply-contactperson";
    private static final String SAGIA_LICENSE_REVIEW_CMS_PAGE = "new-apply-review";
    private static final String ENTITY_NAME = "QemahServiceReq";
    private static final String SHAREHOLDER_TYPE_EXISTING = "Existing";
    private static final String SHAREHOLDER_TYPE_PERSON = "Person";
    private static final String SHAREHOLDER_TYPE_ORGANIZATION = "Organization";
    
    private static final String PREFIX_DELEGATE = "DEL-" ; 
    private static final String PREFIX_SHAREHOLDER = "SHR-" ; 
    private static final String ORIGIN_CONTACT_OTHER = "OTHER" ;

    private static final String BATCHNO = "batchNo";
    private static final String USERINPUT = "userInput";
    private static final String QEEMAH_1_DATA = "qeemah1Data";
    private static final String NEW_SHAREHOLDERS = "newShareholders";
    private static final String EXISTING_SHAREHOLDERS = "existingShareholders";
    private static final String SAGIA_LICENSE_APPLY_DRAFT = "ZS16";

    private static final Integer BATCH_SIZE_FOR_PRODUCTS_SEARCH = 100;
    private static final String ENTITY_NUMBER = "entityNumber";
    private static final String NAME = "name";

    private static final String SAGIA_LICENSE_SERVICE_REQUEST_ID = "LicenseServiceRequestId";
    private static final String LEGAL_STATUS = "legalStatus";
    public static final String SHAREHOLDER_ID_TYPE_PASSPORT = "4";
    private static final String UTF_8 = "UTF-8";
    private static final String ADVANCE_LICENSE_NR = "advanceLicenseNr";

    private static final String THIS_CONTROLLER_REDIRECTION_URL = "/my-sagia/license";

    private GrantedAuthority customerAuthority = new SimpleGrantedAuthority("ROLE_" + Constants.USER.CUSTOMER_USERGROUP.toUpperCase());

    @Resource(name = "sagiaCustomerFacade")
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;
    
    @Resource(name = "sagiaNationalInvestorFacade")
    private SagiaNationalInvestorFacade sagiaNationalInvestorFacade;

    @Resource(name = "sagiaPaymentDetailsFacade")
    private SagiaPaymentDetailsFacade sagiaPaymentDetailsFacade;

    @Resource(name = "sagiaZqeemahFacade")
    private SagiaZqeemahFacade sagiaZqeemahFacade;

    @Resource(name = "sagiaZqeemah2Facade")
    private SagiaZqeemah2Facade sagiaZqeemah2Facade;
    
    
    @Resource(name = "sagiaODataFacade")
    private SagiaODataFacade sagiaODataFacade;

    @Resource(name = "defaultLicenseAmendmentFacade")
    private SagiaLicenseAmendmentFacade sagiaLicenseAmendmentFacade;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;

    @Resource(name = "zQeemahService")
    private ZQeemahService zQeemahService;

    @Resource(name = "defaultSagiaIsicFacade")
    private SagiaIsicFacade sagiaIsicFacade;

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @Resource(name = "sagiaCountryFacade")
    private SagiaCountryFacade sagiaCountryFacade;

    @Resource(name = "sagiaDashboardWithoutLicenseFacade")
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;
    
    @Resource(name = "sagiaLicenseTypeRequirementFacade")
    private SagiaLicenseTypeRequirementFacade sagiaLicenseTypeRequirementFacade;
    
    @Resource(name = "i18NService")
    private I18NService i18NService;

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "sagiaLicenseApplyValidator")
    private SagiaLicenseApplyValidator sagiaLicenseApplyValidator;

    @Resource(name = "sagiaTermsAndConditionsFacade")
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Resource(name = "sagiaApplyReviewValidator")
    private Validator applyReviewValidator;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource(name = "defaultInvestorMappingService")
    private InvestorMappingService investorMappingService;

    @Resource(name = "sagiaLicenseApplyFacade")
    private DefautSagiaLicenseApplyFacade sagiaLicenseApplyFacade;

    @Resource(name = "entityInfoValidator")
    private EntityInfoValidator entityInfoValidator;

    @Resource(name = "existingShareHoldersValidator")
    private ExistingShareHoldersValidator existingShareHoldersValidator;

    @Resource (name = "personShareHoldersValidator")
    private PersonShareHoldersValidator personShareHoldersValidator;

    @Resource (name = "organizationShareHoldersValidator")
    private OrganizationShareHoldersValidator organizationShareHoldersValidator;

    @Resource (name = "contactPersonValidator")
    private ContactPersonValidator contactPersonValidator;
    

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
    
    @Resource
	private LicenseApplyService licenseApplyService;
    
    @Autowired
    private EventService eventService;

    private static String temporaryLicenseConstant = Config.getString("temporary.licese.constant.value", "6");
    private static String regularQeemahChannel = Config.getString("regular.qeemah.channel.constant.value", "R");
    private static String immediateQeemahChannel = Config.getString("immediate.qeemah.channel.constant.value", "I");
    
    private boolean isCustomerAuthority(final Authentication authentication) {
        return org.apache.commons.collections.CollectionUtils.isNotEmpty(authentication.getAuthorities())
                && authentication.getAuthorities().contains(customerAuthority);
    }

    @RequestMapping(path = "/questions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getQuestions(@PathVariable String id, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new ResponseEntity(sagiaZqeemah2Facade.getGeneralQuestonaireById(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/get-isic-details/{isicSection}/{isicDivision}/{isicGroup}/{isicClass}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getIsicDetails(@PathVariable String isicSection, @PathVariable String isicDivision, @PathVariable String isicGroup, @PathVariable String isicClass) {
        List<IsicDetails> isicDetails = sagiaZqeemahFacade.getISICFullDetailsList("C", i18NService.getCurrentLocale().getLanguage().toUpperCase(),
                isicSection, isicDivision, isicGroup, isicClass);
        return new ResponseEntity<List<IsicDetails>>(isicDetails, HttpStatus.OK);
    }

//    @RequestMapping(path = "/paymentDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String requestPaymentDetails(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
//        validateUser(request, response);
//        return new Gson().toJson(sagiaPaymentDetailsFacade.requestPaymentDetails());
//    }

    @RequestMapping(path = "/businessTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getBusinessTypes(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new Gson().toJson(sagiaZqeemahFacade.getBusinessTypeList());
    }

    @RequestMapping(path = "/dropdownsQDF", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsQDF(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new Gson().toJson(sagiaZqeemah2Facade.getGeneralQst());
    }

    @RequestMapping(path = "/isicSectionsQDF/{licenseType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getIsicSectionsQDF(@PathVariable String licenseType, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        // removed licenseType in order to get all sections for new shareholder (SAH-2844)
        List<ISICDetails> activeISICSection = sagiaIsicFacade.getActiveISICSection();
        return new Gson().toJson(activeISICSection);
    }

    @RequestMapping(path = "/isicDivisionsQDF/{licenseType}/{sectionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getIsicDivisionsQDF(@PathVariable String licenseType, @PathVariable String sectionId, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        // removed licenseType in order to get all divisions for new shareholder (SAH-2844)
        List<ISICDetails> activeISICDivision = sagiaIsicFacade.getActiveISICDivision(sectionId);
        return new Gson().toJson(activeISICDivision);
    }

    @RequestMapping(path = "/dropdownsQeemah1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsQeemah1(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        Map<String, List<? extends Object>> result = new HashMap<>();
        result.put(LEGAL_STATUS, sagiaZqeemahFacade.getLegalStatusList());
        result.put("regions", sagiaZqeemahFacade.getRegionsList());
        result.put("countries", sagiaZqeemahFacade.getCountriesList());
        result.put("roles", getRoles());
        result.put("education", getEducation());
        result.put("premium", getPremium());
        result.put("professionalLicense",getprofessionalLicense());
        result.put("multinationalCompany", getMultinationalCompany());
        result.put("expectedInvestments", getExpectedInvestmentValues());
        result.put("isPreApprovalNumber",getIsPreApprovalNumber());
        return new Gson().toJson(result);
    }
    
    @RequestMapping(path = "/dropdownsQeemah1/rhqRegions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsForRhqRegions(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new Gson().toJson(sagiaZqeemahFacade.getRhqRegionsList());
    }

    @RequestMapping(path = "/dropdownsQeemah1/corporateActivities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsForCorporateActivties(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new Gson().toJson(sagiaZqeemahFacade.getCorporateActivities());
    }

    @RequestMapping(path = "/dropdownsQeemah1/strategicActivities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsForStrategicActivties(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new Gson().toJson(sagiaZqeemahFacade.getStrategicActivities());
    }
    @RequestMapping(path = "/dropdownsQeemah1/managementActivities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsForManagementActivties(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new Gson().toJson(sagiaZqeemahFacade.getManagementActivities());
    }

    @RequestMapping(path = "/dropdownsQeemah1/cities/{region}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCitiesQeemah1(@PathVariable String region, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new Gson().toJson(sagiaZqeemahFacade.getCitiesList(region));
    }

    @RequestMapping(path = "/dropdownsQeemah2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDropdownsQeemah2(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        Map<String, Collection> qeemah2Dropdowns = sagiaZqeemah2Facade.getQeemah2Dropdowns();
        qeemah2Dropdowns.put("roles", getRoles());
        qeemah2Dropdowns.put("expectedInvestments", getExpectedInvestmentValues());
        return new Gson().toJson(qeemah2Dropdowns);
    }

    @RequestMapping(path = "/dropdownsQeemah2/cities/{region}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCitiesQeemah2(@PathVariable String region, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new Gson().toJson(sagiaZqeemah2Facade.getDropdownValues(null, "CT", region));
    }

    @RequestMapping(path = "/countryCode/{country}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCountryCode(@PathVariable String country, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new Gson().toJson(sagiaZqeemahFacade.getTeleCode(country));
    }

    @RequestMapping(path = "/stockMarketQuestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getStockMarketQuestions(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        return new ResponseEntity<Collection<FinancialQuestion>>(sagiaZqeemah2Facade.getFinancialQuestions(), HttpStatus.OK);
    }

    @RequestMapping(value = "/isic/{licenseType}", method = RequestMethod.GET)
    @ResponseBody
    public Map getIsic(@PathVariable String licenseType, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        //return sagiaIsicFacade.getIsicFromMasterData(licenseType);
        return sagiaIsicFacade.getIsic(licenseType);
    }

    private void validateUser(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        if (!isCustomerAuthority(SecurityContextHolder.getContext().getAuthentication())) {
            SecurityContextHolder.getContext().setAuthentication(null);
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath());
        }
    }

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:MethodCyclomaticComplexity","squid:S3776"})
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity apply(HttpServletRequest request, final SagiaApplyReviewForm sagiaApplyReviewForm, BindingResult result) throws IOException {
        if(!isApplyAllowed()) { //sanity check
            throw new IOException("not allowed");
        }
        
        final CustomerModel customerModel = (CustomerModel) userService.getCurrentUser();
        
        
        final Map<String, Object> map = new Gson().fromJson(IOUtils.toString(request.getInputStream(), UTF_8), Map.class);
        Map<String, Object> errorMap = sagiaLicenseApplyValidator.validate(map);
        if(!Boolean.valueOf(map.get("isTermsAndConditionsChecked").toString())) {
            if(errorMap == null) {
                errorMap = new HashMap<>();
            }
            errorMap.put("termsAndConditionsChecked", false);
        }
        if (!errorMap.isEmpty()) {
            return getResponseEntity(errorMap);
        }
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.APPLY_NEW_LICENSE);
        
        String qeemahChannel = (String)map.get("qeemahChannel");
		qeemahChannel = StringUtils.isNotBlank(qeemahChannel) ? qeemahChannel : regularQeemahChannel;
		
		/*

		•	Below are the calls which we will use for data submission for both license type
		sagiaZqeemah2Facade.saveDiffQeemah --> /ZQEEMAH2_EXT_SRV/DiffQeemahSet
		sagiaZqeemahFacade.uploadActivityAttachments --> /ZQEEMAH_SRV/ZBASIC_CONT_INFO_ENT
		sagiaZqeemahFacade.createShareholders --> /ZQEEMAH_SHRHLDR_SRV/ZSHAREHOLDER_INFO_ENT
		Shareholders attachment --> /ZQEEMAH_SHRHLDR_SRV/InfoToFile
							
		sagiaZqeemahFacade.createDelegates --> /ZQEEMAH_SHRHLDR_SRV/DelegateInfoSet
		Delegates attachment --> /ZQEEMAH_SHRHLDR_SRV/DelegateDocumentSet
							
		sagiaZqeemahFacade.saveOrganizationInformation --> /ZQEEMAH_SRV/ZBASIC_ORG_INFO_ENT
		sagiaZqeemahFacade.saveContactPerson --> /ZQEEMAH_SRV/ZBASIC_CONT_INFO_ENT
		sagiaZqeemahFacade.saveBusinessActivities --> /ZQEEMAH_SRV/IsicDetPsSet
		if isEntrepreneur upload file --> /InfoToPoa
		
		•	If Qeemah1(Regular) license creation calls
		qeemahSubmitServiceQ1.saveQeemah  --> /ZQEEMAH_SRV/ZQEEMAH_SUBMIT_ENT
		submitServiceQ1.saveQeemah --> /ZQEEMAH_SRV/SUBMIT_ENT (Input: RefID, Guid)
		
		•	Qeemah2 license creation calls 
		sagiaZqeemah2Facade.saveServiceRequestCreation --> /ZQEEMAH2_EXT_SRV/ServiceReqCreationSet
		sagiaZqeemah2Facade.saveServiceRequestMD --> /ZQEEMAH2_EXT_SRV/ServReqMDSetSet
		
		*/
		
        if (isRegularLicense(qeemahChannel)) {
            sagiaZqeemah2Facade.saveDiffQeemah("QEEMAH 1");
        } else {
            sagiaZqeemah2Facade.saveDiffQeemah("QEEMAH 2");
        }

//        InvsIdPost invsIdPost = loadOnStockMarket(map);

        Collection<ShareholderAttachment> activityAttachments = loadAttachments(map);

        //load business activities
        Map businessActivitiesMap = (Map) map.get("businessActivities");
        String businessType = (String) businessActivitiesMap.get("businessType");
        String licenseType = (String) map.get("licenseTypeName");
        List<BusinessActivity> businessActivities = new Gson().fromJson(new Gson().toJson(businessActivitiesMap.get("selectedActivities")),
                new TypeToken<List<BusinessActivity>>() {}.getType());
        //temporary license Type
        String temporaryTextValue=(String )businessActivitiesMap.get("temporaryLicenseTextBox");
        String  licenseTypeCode=(String) map.get("licenseType");
//        sagiaZqeemah2Facade.saveInvestorAnswers(invsIdPost);

        if(!(activityAttachments.isEmpty() || licenseTypeCode.equals(temporaryLicenseConstant))){
            sagiaZqeemahFacade.uploadActivityAttachments(activityAttachments);
        }

        //create new shareholders
        List<ShareholderInfo> shareholderInfo = getNewShareholders(map);
  
        sagiaZqeemahFacade.createShareholders(shareholderInfo);
        sagiaZqeemahFacade.createDelegates(shareholderInfo);
    
        //save organization info // throw new Exception("");
        OrganizationInformation organizationInformation = new Gson().fromJson(new Gson().toJson(map.get("basicInformationExtended")), OrganizationInformation.class);
	    organizationInformation.setLicenseDuration((String) map.get("licenseYear")); 
        sagiaZqeemahFacade.saveOrganizationInformation(organizationInformation);

        //save contact person
        BasicContactInformation basicContactInformation = new Gson().fromJson(new Gson().toJson(((Map<String, Object>) map.get(QEEMAH_1_DATA)).get("contactPerson")), BasicContactInformation.class);
        sagiaZqeemahFacade.saveContactPerson(basicContactInformation);

        sagiaZqeemahFacade.saveBusinessActivities(businessActivities, licenseType, businessType,temporaryTextValue,licenseTypeCode,null);

        String advanceLicenseNr = null;
        if(map.get(ADVANCE_LICENSE_NR) != null) 
        {
            advanceLicenseNr = map.get(ADVANCE_LICENSE_NR).toString();
        }

        if(map.get("isEntrepreneur") != null)
        {
            Boolean isEntrepreneur = (Boolean)map.get("isEntrepreneur");
	        if(isEntrepreneur)
	        {
	            Collection<ShareholderAttachment> entrepreneurFiles = loadEntrepreneurFiles(map);
	            sagiaZqeemahFacade.uploadEntrepreneurFiles(entrepreneurFiles);
	        }
        }
        
        if(map.get("isMoreThan2Branch") != null)
        {
            Boolean isMoreThan2Branch = (Boolean)map.get("isMoreThan2Branch");
	        if(isMoreThan2Branch)
	        {
	            Collection<ShareholderAttachment> entrepreneurFiles = loadRHQCRFiles(map);
	            sagiaZqeemahFacade.uploadEntrepreneurFiles(entrepreneurFiles);
	        }
        }
        
        if(map.get("isEntityListedInStockMarket") != null)
        {
            Boolean isEntityListedInStockMarket = (Boolean)map.get("isEntityListedInStockMarket");
	        if(isEntityListedInStockMarket)
	        {
	            Collection<ShareholderAttachment> entrepreneurFiles = loadRHQCRFiles(map);
	            sagiaZqeemahFacade.uploadEntrepreneurFiles(entrepreneurFiles);
	        }
        }
        
        if(map.get("isEntityRevenueMoreThanThreshold") != null)
        {
            Boolean isEntityRevenueMoreThanThreshold = (Boolean)map.get("isEntityRevenueMoreThanThreshold");
	        if(isEntityRevenueMoreThanThreshold)
	        {
	            Collection<ShareholderAttachment> entrepreneurFiles = loadRHQCRFiles(map);
	            sagiaZqeemahFacade.uploadEntrepreneurFiles(entrepreneurFiles);
	        }
        }
        
        if(map.get("isEntityAssetMoreThanThreshold") != null)
        {
            Boolean isEntityAssetMoreThanThreshold = (Boolean)map.get("isEntityAssetMoreThanThreshold");
	        if(isEntityAssetMoreThanThreshold)
	        {
	            Collection<ShareholderAttachment> entrepreneurFiles = loadRHQCRFiles(map);
	            sagiaZqeemahFacade.uploadEntrepreneurFiles(entrepreneurFiles);
	        }
        }
        
        if(map.get("isMoreThan6Branch") != null)
        {
            Boolean isMoreThan6Branch = (Boolean)map.get("isMoreThan6Branch");
	        if(isMoreThan6Branch)
	        {
	            Collection<ShareholderAttachment> entrepreneurFiles = loadRHQCRFiles(map);
	            sagiaZqeemahFacade.uploadEntrepreneurFiles(entrepreneurFiles);
	        }
        }
        
        
        if(map.get("isPreApprovalNumber") != null)
        {
            Boolean isPreApprovalNumber = (Boolean)map.get("isPreApprovalNumber");
	        if(isPreApprovalNumber)
	        {
	            Collection<ShareholderAttachment> preApprovalNumberFiles = loadPreApprovalNumberFiles(map);
	            sagiaZqeemahFacade.uploadEntrepreneurFiles(preApprovalNumberFiles);
	        }
        }

        String serviceId = null;
       
/*        if (isRegularLicense(qeemahChannel)) {
        	
            serviceId = sagiaZqeemahFacade.submitLicense(advanceLicenseNr);
        } 
        else
        {
            ServiceRequestCreation serviceRequestCreation = sagiaZqeemah2Facade.saveServiceRequestCreation(createServiceRequestObj(advanceLicenseNr));
            sagiaZqeemah2Facade.saveServiceRequestMD(serviceRequestCreation.getGuid());
            serviceId = "";
            if (serviceRequestCreation.getServiceRequestId() != null) {
                getSessionService().setAttribute(SAGIA_LICENSE_SERVICE_REQUEST_ID, serviceRequestCreation.getServiceRequestId().trim());
                serviceId = serviceRequestCreation.getServiceRequestId().trim();
            }
        }
        sagiaCustomerFacade.setApplicationServiceRequestID(serviceId);
*/        
        return ResponseEntity.status(HttpStatus.OK).body("{\"SAGIA_LICENSE_SERVICE_REQUEST_ID\"" + ":" + "\"" + serviceId + "\" }");
    }

    
	private boolean isRegularLicense(String qeemahChannel) {
		return qeemahChannel.equalsIgnoreCase(regularQeemahChannel);
	}
    
    
    @RequestMapping(path = "/sagiaLicenseTypeRequirement/{splRequirementId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSagiaLicenseTypeRequirement(@PathVariable String splRequirementId, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        
        SagiaLicenseTypeRequirementData sagiaLicenseTypeRequirementData = sagiaLicenseTypeRequirementFacade.getSagiaLicenseTypeRequirementForID(splRequirementId);
        
        return new Gson().toJson(sagiaLicenseTypeRequirementData);
        
    }
    
    
    @RequestMapping(path = "/sagiaLicenseTypeRequirementList/{ids}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSagiaLicenseTypeRequirementList(@PathVariable List<String> ids,  final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        
    	validateUser(request, response);
    	List<SagiaLicenseTypeRequirementData>  sagiaLicenseTypeRequirementDataList = sagiaLicenseTypeRequirementFacade.getSagiaLicenseTypeRequirementForListId(ids);
        return new Gson().toJson(sagiaLicenseTypeRequirementDataList);
        
    }
    
    

    private Collection<ShareholderAttachment> loadEntrepreneurFiles(Map<String, Object> map) {
        Collection<ShareholderAttachment> entrepreneurFiles = new ArrayList<>();
        Map attachmentsData = (Map) map.get("attachments");
        ShareholderAttachment boardResolution = new ShareholderAttachment();

        if(attachmentsData.get("boardResolutionFileName") != null && attachmentsData.get("boardResolutionFile") != null){
            String fileName = attachmentsData.get("boardResolutionFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            boardResolution.setFileName(fileName);
            boardResolution.setFileContent(attachmentsData.get("boardResolutionFile").toString());
            boardResolution.setFileType("ENBO");
            entrepreneurFiles.add(boardResolution);
        }
        ShareholderAttachment letterOfSupport = new ShareholderAttachment();
        if(attachmentsData.get("letterOfSupportFileName") != null && attachmentsData.get("letterOfSupportFile") != null){
            String fileName = attachmentsData.get("letterOfSupportFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            letterOfSupport.setFileName(fileName);
            letterOfSupport.setFileContent(attachmentsData.get("letterOfSupportFile").toString());
            letterOfSupport.setFileType("ENLA");
            entrepreneurFiles.add(letterOfSupport);
        }

        return entrepreneurFiles;
    }
    
    private Collection<ShareholderAttachment> loadRHQCRFiles(Map<String, Object> map) {
        Collection<ShareholderAttachment> branchCRFiles = new ArrayList<>();
        Map attachmentsData = (Map) map.get("attachments");
        ShareholderAttachment mainBranchCR = new ShareholderAttachment();

        if(attachmentsData.get("mainBranchCRFileName") != null && attachmentsData.get("mainBranchCRFile") != null){
            String fileName = attachmentsData.get("mainBranchCRFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            mainBranchCR.setFileName(fileName);
            mainBranchCR.setFileContent(attachmentsData.get("mainBranchCRFile").toString());
            mainBranchCR.setFileType("ENMBCR");
            branchCRFiles.add(mainBranchCR);
        }
        ShareholderAttachment otherBranchCR1 = new ShareholderAttachment();
        if(attachmentsData.get("otherBranchCR1FileName") != null && attachmentsData.get("otherBranchCR1File") != null){
            String fileName = attachmentsData.get("otherBranchCR1FileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            otherBranchCR1.setFileName(fileName);
            otherBranchCR1.setFileContent(attachmentsData.get("otherBranchCR1File").toString());
            otherBranchCR1.setFileType("ENOBCR1");
            branchCRFiles.add(otherBranchCR1);
        }
        
        ShareholderAttachment otherBranchCR2 = new ShareholderAttachment();
        if(attachmentsData.get("otherBranchCR2FileName") != null && attachmentsData.get("otherBranchCR2File") != null){
            String fileName = attachmentsData.get("otherBranchCR2FileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            otherBranchCR2.setFileName(fileName);
            otherBranchCR2.setFileContent(attachmentsData.get("otherBranchCR2File").toString());
            otherBranchCR2.setFileType("ENOBCR2");
            branchCRFiles.add(otherBranchCR2);
        }

        ShareholderAttachment entityListedInStockMarket = new ShareholderAttachment();
        if(attachmentsData.get("entityListedInStockMarketFileName") != null && attachmentsData.get("entityListedInStockMarketFile") != null){
            String fileName = attachmentsData.get("entityListedInStockMarketFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            entityListedInStockMarket.setFileName(fileName);
            entityListedInStockMarket.setFileContent(attachmentsData.get("entityListedInStockMarketFile").toString());
            entityListedInStockMarket.setFileType("ENLSM");
            branchCRFiles.add(entityListedInStockMarket);
        }
        
        ShareholderAttachment entityAsset = new ShareholderAttachment();
        if(attachmentsData.get("entityAssetFileName") != null && attachmentsData.get("entityAssetFile") != null){
            String fileName = attachmentsData.get("entityAssetFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            entityAsset.setFileName(fileName);
            entityAsset.setFileContent(attachmentsData.get("entityAssetFile").toString());
            entityAsset.setFileType("ENAS");
            branchCRFiles.add(entityAsset);
        }
        
        ShareholderAttachment entityRevenue = new ShareholderAttachment();
        if(attachmentsData.get("entityRevenueFileName") != null && attachmentsData.get("entityRevenueFile") != null){
            String fileName = attachmentsData.get("entityRevenueFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            entityRevenue.setFileName(fileName);
            entityRevenue.setFileContent(attachmentsData.get("entityRevenueFile").toString());
            entityRevenue.setFileType("ENRV");
            branchCRFiles.add(entityRevenue);
        }
        
        ShareholderAttachment branchCR1 = new ShareholderAttachment();
        if(attachmentsData.get("branchCR1FileName") != null && attachmentsData.get("branchCR1File") != null){
            String fileName = attachmentsData.get("branchCR1FileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            branchCR1.setFileName(fileName);
            branchCR1.setFileContent(attachmentsData.get("branchCR1File").toString());
            branchCR1.setFileType("ENBCR1");
            branchCRFiles.add(branchCR1);
        }
        
        ShareholderAttachment branchCR2 = new ShareholderAttachment();
        if(attachmentsData.get("branchCR2FileName") != null && attachmentsData.get("branchCR2File") != null){
            String fileName = attachmentsData.get("branchCR2FileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            branchCR2.setFileName(fileName);
            branchCR2.setFileContent(attachmentsData.get("branchCR2File").toString());
            branchCR2.setFileType("ENBCR2");
            branchCRFiles.add(branchCR2);
        }
        ShareholderAttachment branchCR3 = new ShareholderAttachment();
        if(attachmentsData.get("branchCR3FileName") != null && attachmentsData.get("branchCR3File") != null){
            String fileName = attachmentsData.get("branchCR3FileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            branchCR3.setFileName(fileName);
            branchCR3.setFileContent(attachmentsData.get("branchCR3File").toString());
            branchCR3.setFileType("ENBCR3");
            branchCRFiles.add(branchCR3);
        }
        ShareholderAttachment branchCR4 = new ShareholderAttachment();
        if(attachmentsData.get("branchCR4FileName") != null && attachmentsData.get("branchCR4File") != null){
            String fileName = attachmentsData.get("branchCR4FileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            branchCR4.setFileName(fileName);
            branchCR4.setFileContent(attachmentsData.get("branchCR4File").toString());
            branchCR4.setFileType("ENBCR4");
            branchCRFiles.add(branchCR4);
        }       

        return branchCRFiles;
    }
    
    private Collection<ShareholderAttachment> loadPreApprovalNumberFiles(Map<String, Object> map) {
        Collection<ShareholderAttachment> preApprovalNumberFiles = new ArrayList<>();
        Map attachmentsData = (Map) map.get("attachments");
        
        ShareholderAttachment financialStatement = new ShareholderAttachment();
        if(attachmentsData.get("financialStatementFileName") != null && attachmentsData.get("financialStatementFile") != null){
            String fileName = attachmentsData.get("financialStatementFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            financialStatement.setFileName(fileName);
            financialStatement.setFileContent(attachmentsData.get("financialStatementFile").toString());
            financialStatement.setFileType("ENFS");
            preApprovalNumberFiles.add(financialStatement);
        }
        
        ShareholderAttachment iqama = new ShareholderAttachment();
        if(attachmentsData.get("iqamaFileName") != null && attachmentsData.get("iqamaFile") != null){
            String fileName = attachmentsData.get("iqamaFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            iqama.setFileName(fileName);
            iqama.setFileContent(attachmentsData.get("iqamaFile").toString());
            iqama.setFileType("ENIQ");
            preApprovalNumberFiles.add(iqama);
        }
        
        ShareholderAttachment crCertificate = new ShareholderAttachment();
        if(attachmentsData.get("crCertificateFileName") != null && attachmentsData.get("crCertificateFile") != null){
            String fileName = attachmentsData.get("crCertificateFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            crCertificate.setFileName(fileName);
            crCertificate.setFileContent(attachmentsData.get("crCertificateFile").toString());
            crCertificate.setFileType("ENCR");
            preApprovalNumberFiles.add(crCertificate);
        }
        
        ShareholderAttachment gosiCertificate = new ShareholderAttachment();
        if(attachmentsData.get("gosiCertificateFileName") != null && attachmentsData.get("gosiCertificateFile") != null){
            String fileName = attachmentsData.get("gosiCertificateFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            gosiCertificate.setFileName(fileName);
            gosiCertificate.setFileContent(attachmentsData.get("gosiCertificateFile").toString());
            gosiCertificate.setFileType("ENGC");
            preApprovalNumberFiles.add(gosiCertificate);
        }
        
        ShareholderAttachment noObjectionCertificate = new ShareholderAttachment();
        if(attachmentsData.get("noObjectionCertificateFileName") != null && attachmentsData.get("noObjectionCertificateFile") != null){
            String fileName = attachmentsData.get("noObjectionCertificateFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(fileName, "\\");
            }
            noObjectionCertificate.setFileName(fileName);
            noObjectionCertificate.setFileContent(attachmentsData.get("noObjectionCertificateFile").toString());
            noObjectionCertificate.setFileType("ENNC");
            preApprovalNumberFiles.add(noObjectionCertificate);
        }

        return preApprovalNumberFiles;
    }

    private boolean isApplyAllowed() {
        Optional<ApplicationStatusData> applicationStatus = zQeemahService.getApplicationStatus();
        return !applicationStatus.isPresent() ||
                (applicationStatus.get().getStatusDesc() != null
                && !applicationStatus.get().getStatusDesc().isEmpty()
                && applicationStatus.get().getStatusDesc().toUpperCase().contains("REJECTED"));
    }

    private boolean isLicenseRejected() {
        Optional<ApplicationStatusData> applicationStatus = zQeemahService.getApplicationStatus();
        return applicationStatus.isPresent() && applicationStatus.get().getStatusDesc() != null
                && !applicationStatus.get().getStatusDesc().isEmpty()
                && applicationStatus.get().getStatusDesc().toUpperCase().contains("REJECTED");
    }
    
    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    @RequireHardLogIn
    public String get(final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
        model.addAttribute("sagiaApplyReviewForm", new SagiaApplyReviewForm());
        model.addAttribute("temporaryLicenseConstant", temporaryLicenseConstant);
        model.addAttribute("regularQeemahChannel", regularQeemahChannel);
        model.addAttribute("immediateQeemahChannel", immediateQeemahChannel);
        try {
            if (sagiaDashboardWithoutLicenseFacade.hasLicense()) {
                GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "license.apply.alreadyhavelicense.error");
                storeCmsPageInModel(model, getContentPageForLabelOrId(null));
                setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
                return "redirect:/dashboard";
            } else if(!isApplyAllowed()) {
                return "redirect:/dashboard-without-license";
            } else {
                zQeemahService.createApplicantReferenceId();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, e.getMessage());
            storeCmsPageInModel(model, getContentPageForLabelOrId(null));
            setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
            try {
                if (sagiaDashboardWithoutLicenseFacade.hasLicense()) {
                        return "redirect:/dashboard";
                }
            } catch (Exception e2) {
                LOG.error(e2.getMessage(), e2);
            }
            return "redirect:/dashboard-without-license";
        }

        model.addAttribute("basicOrganizationInformation", new BasicOrganizationInformation());
        model.addAttribute("organizationInformationExtended", new OrganizationInformation());
        model.addAttribute("sagiaLicenseApplyForm", new ProfileCompanyData());
        model.addAttribute("MIGS_Session_JS", configurationService.getConfiguration().getString(SagiaConstants.MIGS_SESSION_URL));
        model.addAttribute("licenseTypes", sagiaZqeemahFacade.getLicenseTypes());
        model.addAttribute("activeTab", "entityInformation");
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute("draftExists", sagiaDraftFacade.isJsonDraftExists(SAGIA_LICENSE_APPLY_DRAFT));
        model.addAttribute("controllerUrl", "/my-sagia/license");

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_APPLY_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_APPLY_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }

    @RequestMapping(value = "/entity", method = RequestMethod.POST)
    public String applyEntityInfo(@ModelAttribute("sagiaApplyEntityInfoForm") EntityInformationData sagiaApplyEntityInfoForm,
                                  final BindingResult result,
                                  final Model model, final RedirectAttributes redirectAttributes,
                                  HttpServletRequest request,
                                  @RequestParam(name="entitiesManagedByRhq", required = false) String entitiesManagedByRhq,
                                  @RequestParam(name="brandPresenceInMENARegion", required = false) String brandPresenceInMENARegion,
                                  @RequestParam(name="estimatedOperatingCostForRhq", required = false) String estimatedOperatingCostForRhq,
                                  @RequestParam(name="businessActivities", required = false) String[] businessActivities,
                                  @RequestParam(name="businessActivities", required = false) String singleBusinessActivity,
                                  @RequestParam(required = false) String businessActivitiesCounter, BindingResult bindingResult) throws CMSItemNotFoundException, JsonProcessingException, JSONException {
        List<HashMap<String, String>> businessActivitiesListUnfiltered = new ArrayList<>();
        List<EntitiesManagedByRhq> entityList = null;
        List<BrandPresenceInMENARegion> brandList = null;
        List<EstimatedOperatingCostForRhq> estimatedList = null;
        List<HashMap<String, String>> businessActivitiesList = new ArrayList<>();
        Gson gson = new Gson();
        
        LOG.info("^^^^^ "+sagiaApplyEntityInfoForm.isIsPreApprovalNumber()+" **** "+sagiaApplyEntityInfoForm.getPreApprovalNumber());

        boolean hasIsic = businessActivitiesCounter != null || !StringUtils.isEmpty(singleBusinessActivity) || (businessActivities != null && businessActivities.length > 1);

        if (hasIsic) {
            if (Integer.parseInt(businessActivitiesCounter) > 1) {
                for (String businessActivity : businessActivities) {
//                	businessActivity = XSSFilterUtil.filter(businessActivity);
                    businessActivitiesListUnfiltered.add(gson.fromJson(businessActivity, HashMap.class));
                }
            } else {
                businessActivitiesListUnfiltered.add(gson.fromJson(singleBusinessActivity, HashMap.class));
            }

            for (HashMap<String, String> isicActivityUnfiltered : businessActivitiesListUnfiltered) {
                HashMap<String, String> isicActivityFiltered = new HashMap<>();

                for (HashMap.Entry<String, String> entry : isicActivityUnfiltered.entrySet()) {
                    isicActivityFiltered.put(XSSFilterUtil.filter(entry.getKey()), XSSFilterUtil.filter(entry.getValue()));
                }

                businessActivitiesList.add(isicActivityFiltered);
            }
        } else {
            if (!sagiaApplyEntityInfoForm.getLicenseType().equals("6")) {
                sagiaLicenseApplyFacade.repopulateEntityInfoMediaOnFormError(sagiaApplyEntityInfoForm);
                model.addAttribute("isicRegisterError", true);
                return setUpPageForNewApply(model, redirectAttributes, sagiaApplyEntityInfoForm);
            }
        }

        if(null!=entitiesManagedByRhq)
        {
            Type type = new TypeToken< List<EntitiesManagedByRhq> >(){}.getType();
            entityList = gson.fromJson( entitiesManagedByRhq, type );
            sagiaApplyEntityInfoForm.setListOfEntitiesManagedByRhq(entityList);
        }
        if(null!=brandPresenceInMENARegion)
        {
            Type type = new TypeToken< List<BrandPresenceInMENARegion> >(){}.getType();
            brandList = gson.fromJson( brandPresenceInMENARegion, type );
            sagiaApplyEntityInfoForm.setListOfBrandPresenceInMENARegion(brandList);

        }
        if(null!=estimatedOperatingCostForRhq)
        {
            Type type = new TypeToken< List<EstimatedOperatingCostForRhq> >(){}.getType();
            estimatedList = gson.fromJson( estimatedOperatingCostForRhq, type );
            sagiaApplyEntityInfoForm.setListOfEstimatedOperatingCostForRhq(estimatedList);
        }


        sagiaLicenseApplyFacade.validateMediasFromEntityInfo(sagiaApplyEntityInfoForm, request);
        entityInfoValidator.validate(sagiaApplyEntityInfoForm, bindingResult);

        if (result.hasErrors()) {
            List<String> jsonStringifiedList = new ArrayList<>();

            for (HashMap<String, String> isicActivity : businessActivitiesList) {
                jsonStringifiedList.add(gson.toJson(isicActivity));
            }
            for (EntitiesManagedByRhq entities : entityList) {
                jsonStringifiedList.add(gson.toJson(entities));
            }
            for (BrandPresenceInMENARegion brand : brandList) {
                jsonStringifiedList.add(gson.toJson(brand));
            }
            for (EstimatedOperatingCostForRhq cost : estimatedList) {
                jsonStringifiedList.add(gson.toJson(cost));
            }

            sagiaLicenseApplyFacade.repopulateEntityInfoMediaOnFormError(sagiaApplyEntityInfoForm);

            model.addAttribute("sagiaApplyEntityInfoForm", sagiaApplyEntityInfoForm);
            model.addAttribute("saveStatus", false);
            model.addAttribute("isicActivities", jsonStringifiedList);

            return setUpPageForNewApply(model, redirectAttributes, sagiaApplyEntityInfoForm);
        } else {
            if (hasIsic) {
                sagiaApplyEntityInfoForm.setIsicActivities(sagiaLicenseApplyFacade.populateBusinessActivities(businessActivitiesList));
            }

            sagiaLicenseApplyFacade.saveEntityInformationData(sagiaApplyEntityInfoForm, request);

            return "redirect:"+THIS_CONTROLLER_REDIRECTION_URL+"/shareholders";
        }
    }

    @RequestMapping(value = "/entity", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getNewApply(final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
        EntityInformationData sagiaApplyEntityInfoForm = sagiaLicenseApplyFacade.getEntityInformationData();

        if (sagiaApplyEntityInfoForm == null) {
            sagiaApplyEntityInfoForm = new EntityInformationData();
        }

        return setUpPageForNewApply(model, redirectModel, sagiaApplyEntityInfoForm);
    }

    private String setUpPageForNewApply(final Model model, final RedirectAttributes redirectModel,
                                        EntityInformationData sagiaApplyEntityInfoForm) throws CMSItemNotFoundException {
        model.addAttribute("sagiaApplyReviewForm", new SagiaApplyReviewForm());
        model.addAttribute("temporaryLicenseConstant", temporaryLicenseConstant);
        model.addAttribute("regularQeemahChannel", regularQeemahChannel);
        model.addAttribute("immediateQeemahChannel", immediateQeemahChannel);
        try {
            if (sagiaDashboardWithoutLicenseFacade.hasLicense()) {
                GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "license.apply.alreadyhavelicense.error");
                storeCmsPageInModel(model, getContentPageForLabelOrId(null));
                setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
                return "redirect:/dashboard";
            } else if(!isApplyAllowed()) {
                return "redirect:/dashboard-without-license";
            } else {
            	if(isLicenseRejected())
            	{
            		 
            		//TO populate same license in case if it got rejected from CRM
            		SagiaLicenseModel draft = sagiaLicenseApplyFacade.changeLicneseStatusTODraft();

                    sagiaApplyEntityInfoForm = sagiaLicenseApplyFacade.getEntityInformationData(draft);
                   
            	}
                zQeemahService.createApplicantReferenceId();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, e.getMessage());
            storeCmsPageInModel(model, getContentPageForLabelOrId(null));
            setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
            try {
                if (sagiaDashboardWithoutLicenseFacade.hasLicense()) {
                    return "redirect:/dashboard";
                }
            } catch (Exception e2) {
                LOG.error(e2.getMessage(), e2);
            }
            return "redirect:/dashboard-without-license";
        }

        if (sagiaApplyEntityInfoForm.getIsicActivities() != null && sagiaApplyEntityInfoForm.getIsicActivities().size() > 0) {
            List<String> isicActivities = sagiaLicenseApplyFacade.prepareBusinessActivitiesHashMap(sagiaApplyEntityInfoForm.getIsicActivities());
            model.addAttribute("isicActivities", isicActivities);
        }
        if (!sagiaApplyEntityInfoForm.getListOfEntitiesManagedByRhq().isEmpty())
        {
            List<String> entitiesManagedByRhq = sagiaLicenseApplyFacade.prepareEntitiesManagedByRhqHashMap( sagiaApplyEntityInfoForm.getListOfEntitiesManagedByRhq());
            model.addAttribute("entitiesManagedByRhq", new Gson().toJson(entitiesManagedByRhq));
        }
            if(!sagiaApplyEntityInfoForm.getListOfBrandPresenceInMENARegion().isEmpty())
        {
            List<String> brandPresenceInMENARegion = sagiaLicenseApplyFacade.prepareBrandPresenceInMENARegionHashMap( sagiaApplyEntityInfoForm.getListOfBrandPresenceInMENARegion());
            model.addAttribute("brandPresenceInMENARegion", new Gson().toJson(brandPresenceInMENARegion));
        }
        if(!sagiaApplyEntityInfoForm.getListOfEstimatedOperatingCostForRhq().isEmpty())
        {
            List<String> estimatedOperatingCostForRhq = sagiaLicenseApplyFacade.prepareEstimatedOperatingCostForRhqHashMap( sagiaApplyEntityInfoForm.getListOfEstimatedOperatingCostForRhq());
            model.addAttribute("estimatedOperatingCostForRhq", new Gson().toJson(estimatedOperatingCostForRhq));
        }

        model.addAttribute("basicOrganizationInformation", new BasicOrganizationInformation());
        model.addAttribute("organizationInformationExtended", new OrganizationInformation());
        model.addAttribute("sagiaLicenseApplyForm", new ProfileCompanyData());
        model.addAttribute("sagiaApplyEntityInfoForm", sagiaApplyEntityInfoForm);
        model.addAttribute("MIGS_Session_JS", configurationService.getConfiguration().getString(SagiaConstants.MIGS_SESSION_URL));
        model.addAttribute("licenseTypes", sagiaZqeemahFacade.getLicenseTypes());
        model.addAttribute("activeTab", "entityInformation");
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute("draftExists", sagiaDraftFacade.isJsonDraftExists(SAGIA_LICENSE_APPLY_DRAFT));
        model.addAttribute("controllerUrl", "/my-sagia/license");

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_NEW_APPLY_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_NEW_APPLY_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

        return getViewForPage(model);
    }

    @RequestMapping(value = "/shareholders", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getShareholderApply(final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {

        String validateEntityInfoTab = sagiaLicenseApplyFacade.validateEntityTab();

        if (StringUtils.isNotEmpty(validateEntityInfoTab)) {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, validateEntityInfoTab);
            return "redirect:" + THIS_CONTROLLER_REDIRECTION_URL + "/entity";
        }

        EntityInformationData entityInformationData = sagiaLicenseApplyFacade.getEntityInformationData();
        boolean sagiaLicenseCreated = (entityInformationData != null);

        model.addAttribute("sagiaLicenseCreated", sagiaLicenseCreated);
        model.addAttribute("entityInformationData", entityInformationData);
        model.addAttribute("controllerUrl", "/my-sagia/license");

        List<ShareHoldersData> shareHoldersData = sagiaLicenseApplyFacade.getShareHoldersDataByLicense();
        model.addAttribute("shareHoldersDataList", shareHoldersData);

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_SHAREHOLDER_APPLY_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_SHAREHOLDER_APPLY_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

        return getViewForPage(model);
    }

    @RequestMapping(value = "/existing-shareholder-form", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getExistingShareholderForm(final Model model, final RedirectAttributes redirectModel,
                                             @RequestParam(value = "code", required = false, defaultValue = "") String code) throws CMSItemNotFoundException {
        ExistingShareholderData shareholderData;

        if (!StringUtils.isEmpty(code)) {
            shareholderData = sagiaLicenseApplyFacade.getExistingShareholderByCode(code);
        } else {
            shareholderData = new ExistingShareholderData();
        }

        if (shareholderData != null) {
            model.addAttribute("sagiaApplyExistingShareholderForm", shareholderData);
        } else {
            model.addAttribute("sagiaApplyExistingShareholderForm", new ExistingShareholderData());
        }

        return ControllerConstants.Views.Fragments.License.LicenseApplyExistingShareholderForm;
    }

    @RequestMapping(value = "/save-existing-shareholder", method = RequestMethod.POST)
    @RequireHardLogIn
    public String saveExistingShareholder(final Model model, final RedirectAttributes redirectModel,
                                          @ModelAttribute("sagiaApplyExistingShareholderForm") ExistingShareholderData existingShareholderForm,
                                          BindingResult bindingResult, HttpServletRequest request) throws CMSItemNotFoundException {

        String shareholderType = "existing";
        model.addAttribute("shareholderType", shareholderType);

        sagiaLicenseApplyFacade.validateMediasFromExistingShareHolder(existingShareholderForm, request);
        existingShareHoldersValidator.validate(existingShareholderForm, bindingResult);

        if (bindingResult.hasErrors()) {
            sagiaLicenseApplyFacade.populateExistingShareholderMediaOnFormError(existingShareholderForm);
            model.addAttribute("sagiaApplyExistingShareholderForm", existingShareholderForm);
            model.addAttribute("saveStatus", false);
        } else {
            try {
                existingShareholderForm.setShareHolderType(shareholderType);
                sagiaLicenseApplyFacade.saveShareHolderData(existingShareholderForm, request);
                List<ShareHoldersData> shareHoldersData = sagiaLicenseApplyFacade.getShareHoldersDataByLicense();
                model.addAttribute("shareHoldersDataList", shareHoldersData);
                model.addAttribute("saveStatus", true);
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("saveStatus", false);
            }
        }

        return ControllerConstants.Views.Fragments.License.LicenseApplyRegisteredShareholdersTable;

    }

    @RequestMapping(value = "/person-shareholder-form", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getPersonShareholderForm(final Model model, final RedirectAttributes redirectModel,
                                           @RequestParam(value = "code", required = false, defaultValue = "") String code) throws CMSItemNotFoundException {
        PersonShareholderData shareholderData;
        boolean edit = false;

        if (!StringUtils.isEmpty(code)) {
            shareholderData = sagiaLicenseApplyFacade.getPersonShareholderByCode(code);
            edit = true;
        } else {
            shareholderData = new PersonShareholderData();
        }

        if (shareholderData != null) {
            model.addAttribute("sagiaApplyPersonShareholderForm", shareholderData);
            if (edit) {
                model.addAttribute("editOrError", edit);
            }
        } else {
            model.addAttribute("sagiaApplyPersonShareholderForm", new PersonShareholderData());
        }

        return ControllerConstants.Views.Fragments.License.LicenseApplyPersonShareholderForm;
    }

    @RequestMapping(value = "/save-person-shareholder", method = RequestMethod.POST)
    @RequireHardLogIn
    public String savePersonShareholder(final Model model, final RedirectAttributes redirectModel,
                                        @ModelAttribute("sagiaApplyPersonShareholderForm") PersonShareholderData personShareholderForm,
                                        BindingResult bindingResult, HttpServletRequest request) throws CMSItemNotFoundException {
        String shareholderType = "person";
        model.addAttribute("shareholderType", shareholderType);
        
        
        sagiaLicenseApplyFacade.validateMediasFromPersonShareHolder(personShareholderForm, request);
        
        
        personShareHoldersValidator.validate(personShareholderForm, bindingResult);

        if (bindingResult.hasErrors()) {
            sagiaLicenseApplyFacade.populatePersonShareholderMediaOnFormError(personShareholderForm);
            model.addAttribute("sagiaApplyPersonShareholderForm", personShareholderForm);
            model.addAttribute("saveStatus", false);
        } else {
            try {
                personShareholderForm.setShareHolderType(shareholderType);
                sagiaLicenseApplyFacade.saveShareHolderData(personShareholderForm, request);
                List<ShareHoldersData> shareHoldersData = sagiaLicenseApplyFacade.getShareHoldersDataByLicense();
                model.addAttribute("shareHoldersDataList", shareHoldersData);
                model.addAttribute("saveStatus", true);
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("saveStatus", false);
            }
        }

        return ControllerConstants.Views.Fragments.License.LicenseApplyRegisteredShareholdersTable;
    }

    @RequestMapping(value = "/organization-shareholder-form", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getOrganizationShareholderForm(final Model model, final RedirectAttributes redirectModel,
                                           @RequestParam(value = "code", required = false, defaultValue = "") String code) throws CMSItemNotFoundException {
        OrganizationShareholderData shareholderData;
        boolean edit = false;

        if (!StringUtils.isEmpty(code)) {
            shareholderData = sagiaLicenseApplyFacade.getOrganizationShareholderByCode(code);
            edit = true;
        } else {
            shareholderData = new OrganizationShareholderData();
        }

        if (shareholderData != null) {
            model.addAttribute("sagiaApplyOrganizationShareholderForm", shareholderData);
            if (edit) {
                model.addAttribute("editOrError", edit);
            }
        } else {
            model.addAttribute("sagiaApplyOrganizationShareholderForm", new OrganizationShareholderData());
        }

        return ControllerConstants.Views.Fragments.License.LicenseApplyOrganizationShareholderForm;
    }

    @RequestMapping(value = "/save-organization-shareholder", method = RequestMethod.POST)
    @RequireHardLogIn
    public String saveOrganizationShareholder(final Model model, final RedirectAttributes redirectModel,
                                              @ModelAttribute("sagiaApplyOrganizationShareholderForm") OrganizationShareholderData organizationShareholderForm,
                                              BindingResult bindingResult, HttpServletRequest request) throws CMSItemNotFoundException {
        String shareholderType = "organization";
        model.addAttribute("shareholderType", shareholderType);

        sagiaLicenseApplyFacade.validateMediasFromOrganizationShareHolder(organizationShareholderForm, request);

        organizationShareHoldersValidator.validate(organizationShareholderForm, bindingResult);

        if (bindingResult.hasErrors()) {
            sagiaLicenseApplyFacade.populateOrganizationShareholderMediaOnFormError(organizationShareholderForm);
            model.addAttribute("sagiaApplyOrganizationShareholderForm", organizationShareholderForm);
            model.addAttribute("saveStatus", false);
        } else {
            try {
                organizationShareholderForm.setShareHolderType(shareholderType);
                sagiaLicenseApplyFacade.saveShareHolderData(organizationShareholderForm, request);
                List<ShareHoldersData> shareHoldersData = sagiaLicenseApplyFacade.getShareHoldersDataByLicense();
                model.addAttribute("shareHoldersDataList", shareHoldersData);
                model.addAttribute("saveStatus", true);
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("saveStatus", false);
            }
        }

        return ControllerConstants.Views.Fragments.License.LicenseApplyRegisteredShareholdersTable;
    }

    @RequestMapping(value = "/remove-shareholder", method = RequestMethod.GET)
    @RequireHardLogIn
    public String removeShareholder(final Model model, final RedirectAttributes redirectModel,
                                    @RequestParam(value = "code") String code) throws CMSItemNotFoundException {
        try{
            sagiaLicenseApplyFacade.removeShareholder(code);
            List<ShareHoldersData> shareHoldersData = sagiaLicenseApplyFacade.getShareHoldersDataByLicense();
            model.addAttribute("shareHoldersDataList", shareHoldersData);
            model.addAttribute("saveStatus", true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ControllerConstants.Views.Fragments.License.LicenseApplyRegisteredShareholdersTable;
    }

    @RequestMapping(value = "/contactperson", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getContactPerson(final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {

        String validateEntityInfoTab = sagiaLicenseApplyFacade.validateEntityTab();

        if (StringUtils.isNotEmpty(validateEntityInfoTab)) {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, validateEntityInfoTab);
            return "redirect:" + THIS_CONTROLLER_REDIRECTION_URL + "/entity";
        }
        String validateShareHoldersTab = sagiaLicenseApplyFacade.validateShareHoldersTab();
        if (StringUtils.isNotEmpty(validateShareHoldersTab)) {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, validateShareHoldersTab);
            return "redirect:" + THIS_CONTROLLER_REDIRECTION_URL + "/shareholders";
        }

    	ContactPersonsData contactPersonsData = sagiaLicenseApplyFacade.getContactPersonData();
        if (contactPersonsData == null) {
        	contactPersonsData = new ContactPersonsData();
        	contactPersonsData.setContacts(ORIGIN_CONTACT_OTHER);
        	
        }
        return setupContactPersonPage(model, contactPersonsData);
    }

    @RequestMapping(value = "/contactperson", method = RequestMethod.POST)
    public String saveContactPerson(@ModelAttribute("contactPersonsData") ContactPersonsData contactPersonsData, final BindingResult result,
                                  final Model model, final RedirectAttributes redirectAttributes,
                                  HttpServletRequest request, BindingResult bindingResult) throws CMSItemNotFoundException {


    	  contactPersonValidator.validate(contactPersonsData, bindingResult);
          
          if (result.hasErrors()) {
              model.addAttribute("contactPersonsData", contactPersonsData);
              model.addAttribute("saveStatus", false);
              return setupContactPersonPage(model, contactPersonsData);
          } else {
              sagiaLicenseApplyFacade.saveContactPersonData(contactPersonsData);
              return "redirect:"+THIS_CONTROLLER_REDIRECTION_URL + "/review";
          }
    }

	private String setupContactPersonPage(final Model model, ContactPersonsData contactPersonsData) throws CMSItemNotFoundException {
		model.addAttribute("educations", getEducation());
        model.addAttribute("roles", getRoles());
        model.addAttribute("contacts", getContacts());
        if ( contactPersonsData.getContacts() == null || !getListAvailbleContacts().contains(contactPersonsData.getContacts()) ) {
        	contactPersonsData.setContacts(ORIGIN_CONTACT_OTHER); 
        }
        model.addAttribute("contactPersonsData", contactPersonsData);
        model.addAttribute("controllerUrl", "/my-sagia/license");

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_CONTACTPERSON_APPLY_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_CONTACTPERSON_APPLY_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
	}
    
	private List<String> getListAvailbleContacts(){
		List<String> contacts = new ArrayList<String>();
		// Get list of contacts from License Delegates
        List<ShareHoldersData> shareHoldersDataList = sagiaLicenseApplyFacade.getShareHoldersDataByLicense(); 
        for (ShareHoldersData shareHoldersData : shareHoldersDataList ) {
        	if(shareHoldersData.getDelegateInfo() != null) {
        	    contacts.add(PREFIX_DELEGATE+shareHoldersData.getCode());
        	}
        	if (SHAREHOLDER_TYPE_PERSON.equalsIgnoreCase(shareHoldersData.getShareHolderType())) {
        
            	contacts.add(PREFIX_SHAREHOLDER+shareHoldersData.getCode());
        	}
			
		}
        
        
       return contacts ;
            
	}
	
	
	private List<DropdownValue> getContacts() {
		
		List<DropdownValue> contacts = new ArrayList<>();
		
		
		// Get list of contacts from License Delegates
        List<ShareHoldersData> shareHoldersDataList = sagiaLicenseApplyFacade.getShareHoldersDataByLicense(); 
        for (ShareHoldersData shareHoldersData : shareHoldersDataList ) {
        	if(shareHoldersData.getDelegateInfo() != null && shareHoldersData.getDelegateInfo().getDelegateFullName()!=null && !"".equals(shareHoldersData.getDelegateInfo().getDelegateFullName())) {
        	    String delegateName = shareHoldersData.getDelegateInfo().getDelegateFullName();
        	    //Locale locale = i18NService.getCurrentLocale(); ENG or AR depend on the curent local TODO
        	    contacts.add(createContact(PREFIX_DELEGATE+shareHoldersData.getCode(), delegateName));
        	}
        	if (SHAREHOLDER_TYPE_PERSON.equalsIgnoreCase(shareHoldersData.getShareHolderType())) {
        		//Locale locale = i18NService.getCurrentLocale(); ENG or AR depend on the curent local TODO
        		String shareholderName = ((PersonShareholderData)shareHoldersData).getFullName();
            	contacts.add(createContact(PREFIX_SHAREHOLDER+shareHoldersData.getCode(), shareholderName));
        	}
			
		}
            
        Locale locale = i18NService.getCurrentLocale();
        contacts.add(createContact(ORIGIN_CONTACT_OTHER, messageSource.getMessage("license.apply.contact.other", null, locale)));
     
        return contacts ;
    }
	
    private List<DropdownValue> getRoles() {
        List<DropdownValue> roles = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        roles.add(createRole("AGENT", messageSource.getMessage("licenseApply.role.AGENT", null, locale)));
        roles.add(createRole("EMPLOYEE", messageSource.getMessage("licenseApply.role.EMPLOYEE", null, locale)));
        roles.add(createRole("INVESTOR", messageSource.getMessage("licenseApply.role.INVESTOR", null, locale)));
        roles.add(createRole("GM", messageSource.getMessage("licenseApply.role.GM", null, locale)));
        return roles;
    }

    private DropdownValue createRole(String role, String roleText) {
        DropdownValue result = new DropdownValue();
        result.setRole(role);
        result.setRoleText(roleText);
        return result;
    }
    
    private DropdownValue createContact(String contact, String contactName) {
        DropdownValue result = new DropdownValue();
        result.setContact(contact);
        result.setContactText(contactName);
        return result;
    }

    private class ExpectedInvestment {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        ExpectedInvestment(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    private List<ExpectedInvestment> getExpectedInvestmentValues() {
        List<ExpectedInvestment> expectedInvestments = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        expectedInvestments.add(new ExpectedInvestment("LT1", messageSource.getMessage("license.apply.expectedinvestment.LT1", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("LT2", messageSource.getMessage("license.apply.expectedinvestment.LT2", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("LT3", messageSource.getMessage("license.apply.expectedinvestment.LT3", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("LT4", messageSource.getMessage("license.apply.expectedinvestment.LT4", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("LT5", messageSource.getMessage("license.apply.expectedinvestment.LT5", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("LT6", messageSource.getMessage("license.apply.expectedinvestment.LT6", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("LT7", messageSource.getMessage("license.apply.expectedinvestment.LT7", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("LT8", messageSource.getMessage("license.apply.expectedinvestment.LT8", null, locale)));
        expectedInvestments.add(new ExpectedInvestment("LT9", messageSource.getMessage("license.apply.expectedinvestment.LT9", null, locale)));
        return expectedInvestments;
    }

    private List<DropdownValue> getEducation() {
        List<DropdownValue> education = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        education.add(createEducation("0001", messageSource.getMessage("licenseApply.education.0001", null, locale)));
        education.add(createEducation("0002", messageSource.getMessage("licenseApply.education.0002", null, locale)));
        education.add(createEducation("0003", messageSource.getMessage("licenseApply.education.0003", null, locale)));
        education.add(createEducation("0004", messageSource.getMessage("licenseApply.education.0004", null, locale)));
        education.add(createEducation("0005", messageSource.getMessage("licenseApply.education.0005", null, locale)));
        education.add(createEducation("0006", messageSource.getMessage("licenseApply.education.0006", null, locale)));
        return education;
    }
    
    private List<DropdownValue> getPremium() {
        List<DropdownValue> premiumStatus = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        premiumStatus.add(createPremiumStatus("yes", messageSource.getMessage("licenseApply.shareholder.premiumStatus.yes", null, locale)));
        premiumStatus.add(createPremiumStatus("no", messageSource.getMessage("licenseApply.shareholder.premiumStatus.no", null, locale)));
        return premiumStatus;
    }
    
    private List<DropdownValue> getprofessionalLicense() {
        List<DropdownValue> professionalLicenseStatus = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        professionalLicenseStatus.add(createProfessionalLicenseStatus("yes", messageSource.getMessage("licenseApply.shareholder.professionalLicense.yes", null, locale)));
        professionalLicenseStatus.add(createProfessionalLicenseStatus("no", messageSource.getMessage("licenseApply.shareholder.professionalLicense.no", null, locale)));
        return professionalLicenseStatus;
    }
    
    private DropdownValue createProfessionalLicenseStatus(String status, String description) {
        DropdownValue result = new DropdownValue();
        result.setProfessionalLicenseStatus(status);
        result.setProfessionalLicenseText(description);
        return result;
    }
    
    private List<DropdownValue> getIsPreApprovalNumber() {
        List<DropdownValue> isPreApprovalNumber = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        isPreApprovalNumber.add(createPreApprovalNumberStatus("yes", messageSource.getMessage("licenseApply.shareholder.professionalLicense.yes", null, locale)));
        isPreApprovalNumber.add(createPreApprovalNumberStatus("no", messageSource.getMessage("licenseApply.shareholder.professionalLicense.no", null, locale)));
        return isPreApprovalNumber;
    }
 
    private DropdownValue createPreApprovalNumberStatus(String status, String description) {
        DropdownValue result = new DropdownValue();
        result.setPreApprovalNumberStatus(status);
        result.setPreApprovalNumberText(description);
        return result;
    }
    private DropdownValue createPremiumStatus(String status, String description) {
        DropdownValue result = new DropdownValue();
        result.setPremiumStatus(status);
        result.setPremiumText(description);
        return result;
    }

    private DropdownValue createEducation(String education, String educationText) {
        DropdownValue result = new DropdownValue();
        result.setEducation(education);
        result.setEducationText(educationText);
        return result;
    }

    private List<DropdownValue> getMultinationalCompany() {
        List<DropdownValue> multinationalCompany = new ArrayList<>();
        Locale locale = i18NService.getCurrentLocale();
        multinationalCompany.add(createMultinationalCompany("1", messageSource.getMessage("licenseApply.multinationalCompany.Yes", null, locale)));
        multinationalCompany.add(createMultinationalCompany("2", messageSource.getMessage("licenseApply.multinationalCompany.No", null, locale)));
        return multinationalCompany;
    }

    private DropdownValue createMultinationalCompany(String multinationalCompany, String multinationalCompanyText) {
        DropdownValue result = new DropdownValue();
        result.setMultinationalCompany(multinationalCompany);
        result.setMultinationalCompanyText(multinationalCompanyText);
        return result;
    }

    @RequestMapping(value = "/{shareholderEntityNumber}", method = RequestMethod.GET)
//    @RequireHardLogIn
    @ResponseBody
    public String checkExistingShareholder(@PathVariable("shareholderEntityNumber") String shareholderEntityNumber) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(
                sagiaZqeemahFacade.validateExistingShareholder(shareholderEntityNumber));
    }
    
    
    @RequestMapping(value = "/verifyMofa/{mofaNumber}", method = RequestMethod.GET)
    @ResponseBody
    public String checkMofaVerification(@PathVariable("mofaNumber") String mofaNumber) throws JsonProcessingException {
      return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(
              sagiaZqeemahFacade.validateMofaNumber(mofaNumber));
    }

    private List<ShareholderInfo> getNewShareholders(Map map) {
        Map qeemah1Map = (Map) map.get(QEEMAH_1_DATA);
        List<ShareholderInfo> shareholders = new ArrayList<>();
        if (MapUtils.isNotEmpty(qeemah1Map)) {
            List<Map> qeemah1NewShareholders = (List) qeemah1Map.get(NEW_SHAREHOLDERS);
            List<Map> qeemah1ExistingShareholders = (List) qeemah1Map.get(EXISTING_SHAREHOLDERS);
            fillShareholdersList(qeemah1NewShareholders, shareholders, false);
            fillShareholdersList(qeemah1ExistingShareholders, shareholders, true);
        }
        return shareholders;
    }

    private void fillShareholdersList(List<Map> qeemah1NewShareholders, List<ShareholderInfo> shareholders, boolean existingShareholders) {
        if (CollectionUtils.isEmpty(qeemah1NewShareholders)) {
            return;
        }
        int index = 0;
        for (Map newShareholder : qeemah1NewShareholders) {
            ShareholderInfo shareholderInfo = new Gson().fromJson(new Gson().toJson(newShareholder), ShareholderInfo.class);
            Collection<ShareholderAttachment> shareholderAttachments = new ArrayList<>();
            Collection<DelegateAttachment> delegateAttachments = new ArrayList<>();
            if (existingShareholders) {
                shareholderInfo.setType(SHAREHOLDER_TYPE_EXISTING);
                if (newShareholder.get(ENTITY_NUMBER) != null) {
                    shareholderInfo.setEntityNo(String.valueOf(newShareholder.get(ENTITY_NUMBER)));
                }
                if (newShareholder.get(NAME) != null) {
                    shareholderInfo.setFullNameEnglish(String.valueOf(newShareholder.get(NAME)));
                }
                shareholderInfo.setHasDelegateInfo("false");// there will no delegate info incase of existing shareholder
                updateExistingShareholder((LinkedTreeMap) newShareholder, shareholderAttachments);
            }

            if (SHAREHOLDER_TYPE_PERSON.equals(shareholderInfo.getType())) {
                index++;
                updatePersonShareholder(index, (LinkedTreeMap) newShareholder, shareholderAttachments);
            }
            if (SHAREHOLDER_TYPE_ORGANIZATION.equals(shareholderInfo.getType())) {
                index++;
                updateOrganizationShareholder(index, (LinkedTreeMap) newShareholder, shareholderAttachments);
            }
            
            if(!existingShareholders && shareholderInfo.getHasDelegateInfo().equalsIgnoreCase("true") && shareholderInfo.getSelfDelegate().equalsIgnoreCase("false") && shareholderInfo.getDelegate()!= null)
            {
	            	shareholderInfo.getDelegate().setEntityNo(String.format("%03d", index));
		            updateDelegate( index, (LinkedTreeMap) newShareholder,delegateAttachments) ;            
		            shareholderInfo.getDelegate().setDelegateAttachments(delegateAttachments);
            }
            shareholderInfo.setAttachments(shareholderAttachments);
            shareholders.add(shareholderInfo);
        }
    }

    private void updateOrganizationShareholder(int index , LinkedTreeMap newShareholder, Collection<ShareholderAttachment> shareholderAttachments) {
        String entityNumber = String.format("%03d", index);
        ShareholderAttachment registrationFile = new ShareholderAttachment();
        if(newShareholder.get("companyRegistrationFileName") != null){
            String fileName = newShareholder.get("companyRegistrationFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(newShareholder.get("companyRegistrationFileName").toString(), "\\");
            }
            registrationFile.setFileName(fileName);
        }
        registrationFile.setFileMimeType(newShareholder.get("companyRegistrationFileMimeType").toString());
        registrationFile.setFileContent(newShareholder.get("companyRegistrationFile").toString());
        registrationFile.setEntityNumber(entityNumber);
        registrationFile.setFileType("COMM");
        shareholderAttachments.add(registrationFile);

        ShareholderAttachment financialStatementFile = new ShareholderAttachment();

        if(newShareholder.get("companyFinancialStatementFileName") != null) {
            String fileName = newShareholder.get("companyFinancialStatementFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(newShareholder.get("companyFinancialStatementFileName").toString(), "\\");
            }
            financialStatementFile.setFileName(fileName);
        }
        financialStatementFile.setFileMimeType(newShareholder.get("companyFinancialStatementFileMimeType").toString());
        financialStatementFile.setFileContent(newShareholder.get("companyFinancialStatementFile").toString());
        financialStatementFile.setEntityNumber(entityNumber);
        financialStatementFile.setFileType("BAL");
        shareholderAttachments.add(financialStatementFile);
        
        ShareholderAttachment companyMemoAssociationFile = new ShareholderAttachment();

        if(newShareholder.get("companyMemoAssociationFileName") != null  && checkOrgShareholderAttach(newShareholder.get("companyCountry").toString())) {
            String fileName = newShareholder.get("companyMemoAssociationFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(newShareholder.get("companyMemoAssociationFileName").toString(), "\\");
            }
            companyMemoAssociationFile.setFileName(fileName);
        
            companyMemoAssociationFile.setFileMimeType(newShareholder.get("companyMemoAssociationFileMimeType").toString());
            companyMemoAssociationFile.setFileContent(newShareholder.get("companyMemoAssociationFile").toString());
            companyMemoAssociationFile.setEntityNumber(entityNumber);
            companyMemoAssociationFile.setFileType("CMOA");
        shareholderAttachments.add(companyMemoAssociationFile);
        }
    }
	
	private boolean checkOrgShareholderAttach(String companyCountry) {
    	 List<SagiaCountryData> shareHolderCheck = sagiaCountryFacade.getShareHolderCheckCountries();	      
	       if(StringUtils.isNotEmpty(companyCountry) && shareHolderCheck.size() > 0){
	       	for(SagiaCountryData shareHolderCheckCountry : shareHolderCheck) {
	       		if(companyCountry.equalsIgnoreCase(shareHolderCheckCountry.getCode())) {
	       				return true;
	       		}
	           }
	       }
	       return false;
    }

    private void updatePersonShareholder(int index, LinkedTreeMap newShareholder, Collection<ShareholderAttachment> shareholderAttachments) {
        String entityNumber = String.format("%03d", index);
        String passportFileName = ObjectUtils.getValueOrNullFrom(newShareholder, "passportFileName");
        String otherFileName = ObjectUtils.getValueOrNullFrom(newShareholder, "otherFileName");
        String personMemoAssociationFileName = ObjectUtils.getValueOrNullFrom(newShareholder, "personMemoAssociationFileName");
        if (passportFileName != null) {
            ShareholderAttachment passportFile = new ShareholderAttachment();
            if(passportFileName.contains("\\")){
                passportFileName = StringUtils.substringAfterLast(passportFileName, "\\");
            }
            passportFile.setFileName(passportFileName);

            passportFile.setFileMimeType(ObjectUtils.getValueOrNullFrom(newShareholder, "passportFileMimeType"));
            passportFile.setFileContent(ObjectUtils.getValueOrNullFrom(newShareholder, "passportFile"));
            passportFile.setEntityNumber(entityNumber);
            passportFile.setFileType("PASS");
            shareholderAttachments.add(passportFile);
        }

        if (otherFileName != null) {
            ShareholderAttachment otherFile = new ShareholderAttachment();
            if(otherFileName.contains("\\")){
                otherFileName = StringUtils.substringAfterLast(otherFileName, "\\");
            }
            otherFile.setFileName(otherFileName);
            otherFile.setFileMimeType(ObjectUtils.getValueOrNullFrom(newShareholder, "otherFileMimeType"));
            otherFile.setFileContent(ObjectUtils.getValueOrNullFrom(newShareholder, "otherFile"));
            otherFile.setEntityNumber(entityNumber);
            otherFile.setFileType("OTHR");
            shareholderAttachments.add(otherFile);
        }
                
    }
    
    private void updateDelegate(int index, LinkedTreeMap newShareholder, Collection<DelegateAttachment> delegateAttachments) {
        String entityNumber = String.format("%03d", index);
        LinkedTreeMap delegate = (LinkedTreeMap) newShareholder.get("delegate");
        if(delegate != null)
        {
	        String authorizationLetterFileName = ObjectUtils.getValueOrNullFrom(delegate, "authorizationLetterFileName");
	        String idCopyFileName = ObjectUtils.getValueOrNullFrom(delegate, "idCopyFileName");
	        if (StringUtils.isNotBlank(authorizationLetterFileName)) {
	        	DelegateAttachment authorizationLetterFile = new DelegateAttachment();
	            if(authorizationLetterFileName.contains("\\")){
	            	authorizationLetterFileName = StringUtils.substringAfterLast(authorizationLetterFileName, "\\");
	            }
	            authorizationLetterFile.setFileName(authorizationLetterFileName);
	
	            authorizationLetterFile.setFileMimeType(ObjectUtils.getValueOrNullFrom(delegate, "authorizationLetterFileMimeType"));
	            authorizationLetterFile.setFileContent(ObjectUtils.getValueOrNullFrom(delegate, "authorizationLetterFile"));
	            authorizationLetterFile.setEntityNumber(entityNumber);
	            authorizationLetterFile.setFileType("AUTH");
	            delegateAttachments.add(authorizationLetterFile);
	        }
	
	        if (StringUtils.isNotBlank(idCopyFileName)) {
	        	DelegateAttachment idCopyFile = new DelegateAttachment();
	            if(idCopyFileName.contains("\\")){
	            	idCopyFileName = StringUtils.substringAfterLast(idCopyFileName, "\\");
	            }
	            idCopyFile.setFileName(idCopyFileName);
	            idCopyFile.setFileMimeType(ObjectUtils.getValueOrNullFrom(delegate, "idCopyFileMimeType"));
	            idCopyFile.setFileContent(ObjectUtils.getValueOrNullFrom(delegate, "idCopyFile"));
	            idCopyFile.setEntityNumber(entityNumber);
	            idCopyFile.setFileType("ID");
	            delegateAttachments.add(idCopyFile);
	        }
        }    
    }

    private void updateExistingShareholder(LinkedTreeMap newShareholder, Collection<ShareholderAttachment> shareholderAttachments) {
        ShareholderAttachment commercialRegistrationFile = new ShareholderAttachment();
        if(newShareholder.get("commercialRegistrationFileName") != null) {
            String fileName = newShareholder.get("commercialRegistrationFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(newShareholder.get("commercialRegistrationFileName").toString(), "\\");
            }
            commercialRegistrationFile.setFileName(fileName);
        }
        commercialRegistrationFile.setFileContent(newShareholder.get("commercialRegistrationFile").toString());
        commercialRegistrationFile.setEntityNumber(newShareholder.get(ENTITY_NUMBER).toString());
        commercialRegistrationFile.setFileType("E3");
        commercialRegistrationFile.setFileMimeType(newShareholder.get("commercialRegistrationFileMimeType").toString());
        shareholderAttachments.add(commercialRegistrationFile);

        ShareholderAttachment financialStatementFile = new ShareholderAttachment();
        if(newShareholder.get("lastBudgetFileName") != null) {
            String fileName = newShareholder.get("lastBudgetFileName").toString();
            if(fileName.contains("\\")){
                fileName = StringUtils.substringAfterLast(newShareholder.get("lastBudgetFileName").toString(), "\\");
            }
            financialStatementFile.setFileName(fileName);
        }
        financialStatementFile.setFileContent(newShareholder.get("lastBudgetFile").toString());
        financialStatementFile.setEntityNumber(newShareholder.get(ENTITY_NUMBER).toString());
        financialStatementFile.setFileType("E5");
        financialStatementFile.setFileMimeType(newShareholder.get("lastBudgetFileMimeType").toString());
        shareholderAttachments.add(financialStatementFile);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public String getFiltersForProducts(@RequestBody Map<String, String> filtersList, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        validateUser(request, response);
        String top = String.valueOf(BATCH_SIZE_FOR_PRODUCTS_SEARCH);

        String skip;
        if (filtersList.get(BATCHNO) != null && Integer.parseInt(filtersList.get(BATCHNO)) > 0) {
            skip = String.valueOf(((Integer.parseInt(filtersList.get(BATCHNO)) - 1) * BATCH_SIZE_FOR_PRODUCTS_SEARCH) - 1);
        } else {
            skip = "0";
        }
        Collection<ProductData> productsList;

        if ((filtersList.get(USERINPUT)) != null && !filtersList.get(USERINPUT).isEmpty() && !" ".equals(filtersList.get(USERINPUT))) {
            String userInput = filtersList.get(USERINPUT).startsWith(" ") ? filtersList.get(USERINPUT).substring(1) : filtersList.get(USERINPUT);
            if (StringUtils.isNumeric(userInput)) {
                productsList = sagiaLicenseAmendmentFacade.getAmendProductsListWithId(userInput, skip, top);
            } else {
                productsList = sagiaLicenseAmendmentFacade.getAmendProductsListWithDescription(userInput, skip, top);
            }
        } else {
            productsList = sagiaLicenseAmendmentFacade.getAmendProductsList(skip, top);
        }

        if (CollectionUtils.isNotEmpty(productsList)) {
            return new Gson().toJson(productsList);
        }
        return "[]";
    }

    private FininvisidPost getFinancialQuestions(Map map) {
        List<FinancialPost> answers = new ArrayList<>();
        Map questionsData = (Map) map.get("technicalAndFinancialCriteriaQuestions");
        Iterator iterator = questionsData.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            Map answerData = (Map) item.getValue();
            FinancialPost answer = new FinancialPost();

            answer.setQuestionId(item.getKey().toString());
            answer.setQuestionText(answerData.get("description").toString());
            answer.setAnswer(answerData.get("answerKey").toString());
            answer.setAnswerkey(answerData.get("answerValue").toString());
            answers.add(answer);
        }
        FininvisidPost fininvisidPost = new FininvisidPost();
        fininvisidPost.setFinancialPostData(answers);
        return fininvisidPost;
    }

    private Collection<Product> loadProductsQ1(Map map) {
        Collection<Product> products = new ArrayList<>();
        List<Map> productsList = (List<Map>) ((Map) map.get("entityInformation")).get("products");
        if (CollectionUtils.isNotEmpty(productsList)) {
            for (Map item : productsList) {
                Product product = new Product();
                product.setProductCode(item.get("id").toString());
                product.setDescription(item.get("description").toString());
                product.setQuantity(item.get("quantity").toString());
                product.setUom(item.get("unit").toString());
                product.setUomText(item.get("unitDescription").toString());
                products.add(product);
            }
        }
        return products;
    }

    private Collection<com.sap.ibso.eservices.facades.data.zqeemah2.Product> loadProductsQ2(Map map) {
        Collection<com.sap.ibso.eservices.facades.data.zqeemah2.Product> products = new ArrayList<>();
        List<Map> productsList = (List<Map>) ((Map) map.get("entityInformation")).get("products");
        if (CollectionUtils.isNotEmpty(productsList)) {
            for (Map item : productsList) {
                com.sap.ibso.eservices.facades.data.zqeemah2.Product product = new com.sap.ibso.eservices.facades.data.zqeemah2.Product();
                product.setProductId(item.get("id").toString());
                product.setDescription(item.get("description").toString());
                product.setQuantity(item.get("quantity").toString());
                product.setUnitOfMeasurement(item.get("unit").toString());
                product.setUnitOfMeasurementText(item.get("unitDescription").toString());
                products.add(product);
            }
        }
        return products;
    }

    private Collection<ShareholderAttachment> loadAttachments(Map map) {
        List<Map> activityAttachments = (List<Map>) map.get("activityAttachments");
        Collection<ShareholderAttachment> attachments = new ArrayList<>();
        if (!activityAttachments.isEmpty()) {
            int counter = 0;
            for (Map item : activityAttachments) {
                ShareholderAttachment attachment = new ShareholderAttachment();
                attachment.setFileType("PERM" + counter);
                attachment.setFileContent(item.get("content").toString());
                attachment.setFileName(item.get("fileName").toString().replace("C:\\fakepath\\", ""));
                attachment.setFileMimeType(item.get("fileMimeType").toString());
                attachments.add(attachment);
                counter++;
            }
        }
        return attachments;
    }

    @RequestMapping(value = "/save-on-stock", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity saveOnStock(HttpServletRequest request) throws IOException {
        final Map<String, Object> map = new Gson().fromJson(URLDecoder.decode(IOUtils.toString(request.getInputStream(), UTF_8), UTF_8), Map.class);
        InvsIdPost invsIdPost = loadOnStockMarket(map);
        return new ResponseEntity(invsIdPost, HttpStatus.OK);
    }

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:MethodCyclomaticComplexity"})
    private InvsIdPost loadOnStockMarket(Map map) {
        Map data = (Map) map.get("onStockMarket");
        Map businessActivities = (Map) map.get("businessActivities");
        InvsIdPost invsIdPost = new InvsIdPost();
        Collection<GeneralQuestionPost> generalQuestionPosts = new ArrayList<>();
        GeneralQuestionPost licenseType = new GeneralQuestionPost();
        licenseType.setQuestionId("Q10");
        licenseType.setQuestionText("LICENSE TYPE");
        licenseType.setAnswer(map.get("licenseTypeName").toString());
        licenseType.setAnswerKey(map.get("licenseType").toString());
        generalQuestionPosts.add(licenseType);

        GeneralQuestionPost isListed = new GeneralQuestionPost();
        isListed.setQuestionId("Q30");
        isListed.setQuestionText("IS YOUR COMPANY REGISTERED IN ANY NATIONAL LEVL STOCK MARKET?");
        isListed.setAnswer("Yes");
        isListed.setAnswerKey("01");
        generalQuestionPosts.add(isListed);

        GeneralQuestionPost isicSection = new GeneralQuestionPost();
        isicSection.setQuestionId("IQ1");
        isicSection.setQuestionText("ISIC Section");
        if (businessActivities.get("selectedSectionName") != null) {
            isicSection.setAnswer(businessActivities.get("selectedSectionName").toString());
        }
        if (businessActivities.get("selectedSection") != null) {
            isicSection.setAnswerKey(businessActivities.get("selectedSection").toString());
        }
        generalQuestionPosts.add(isicSection);

        List<Map> isicDivisions = (List<Map>) businessActivities.get("selectedDivisions");
        if (CollectionUtils.isNotEmpty(isicDivisions)) {
            Map selectedDivision = isicDivisions.get(0);
            GeneralQuestionPost isicDivision = new GeneralQuestionPost();
            isicDivision.setQuestionId("IQ2");
            isicDivision.setQuestionText("ISIC Division");
            if (selectedDivision.get("divisionIdName") != null) {
                isicDivision.setAnswer(selectedDivision.get("divisionIdName").toString());
            }
            if (selectedDivision.get("divisionId") != null) {
                isicDivision.setAnswerKey(selectedDivision.get("divisionId").toString().replace(".0", ""));
            }
            generalQuestionPosts.add(isicDivision);
        }

        List<Map> isicActivities = (List<Map>) businessActivities.get("selectedActivities");
        if(!isicActivities.isEmpty()){
            Map selectedActivity = isicActivities.get(0);
            GeneralQuestionPost isicActivity = new GeneralQuestionPost();
            isicActivity.setQuestionId("IQ3");
            isicActivity.setQuestionText("ISIC Activity");

            GeneralQuestionPost isicActivityDescription = new GeneralQuestionPost();
            isicActivityDescription.setQuestionId("IQ5");
            isicActivityDescription.setQuestionText("Activity Description");

            if (selectedActivity.get("activityIdName") != null) {
                isicActivity.setAnswer(selectedActivity.get("activityIdName").toString());
                isicActivityDescription.setAnswer(selectedActivity.get("activityIdName").toString());
            }
            if (selectedActivity.get("activityId") != null) {
                isicActivity.setAnswerKey(selectedActivity.get("activityId").toString().replace(".0", ""));
                isicActivityDescription.setAnswerKey(selectedActivity.get("activityId").toString().replace(".0", ""));
            }
            generalQuestionPosts.add(isicActivity);
            generalQuestionPosts.add(isicActivityDescription);
        }

        if (data != null && !data.isEmpty()) {
            GeneralQuestionPost listingCountry = new GeneralQuestionPost();
            listingCountry.setQuestionId("Q40");
            listingCountry.setQuestionText("SELECT COUNTRY");
            listingCountry.setAnswer(data.get("countryName").toString());
            listingCountry.setAnswerKey(data.get("country").toString());
            generalQuestionPosts.add(listingCountry);

            GeneralQuestionPost isinCode = new GeneralQuestionPost();
            isinCode.setQuestionId("Q50");
            isinCode.setQuestionText("ENTER ISIN CODE");
            isinCode.setAnswer(data.get("isinCode").toString());
            generalQuestionPosts.add(isinCode);
        }

        invsIdPost.setGeneralQuestionPosts(generalQuestionPosts);
        return invsIdPost;
    }
    
    /**
     * Ajax call to load an investor information based on the CR Number.
     * The response is used to pre-fill the form. In case of an invalid number an error will be displayed.
     *
     * @param crNumber crNumber
     * @return
     */
    @RequestMapping(path = {"/professional-license/{crNumber}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getNationalInvestorHeaderSet(@PathVariable("crNumber") String crNumber) {
        return new Gson().toJson(sagiaNationalInvestorFacade.getNationalInvestorHeaderSet(crNumber));
    }
    

    /**
     * retrieves the countries list and provide it in JSON format for frontend
     *
     * @return
     */
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    @ResponseBody
    public String getCountries() {
        return new Gson().toJson(sagiaCountryFacade.getCountriesList());
    }

	private ResponseEntity getResponseEntity(Map<String, Object> errorMap) {
        return new ResponseEntity(SagiaContextFormErrorsConverter.fromValidation(errorMap), HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(path = "/nic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getNicUserData(@RequestParam(value = "idType", defaultValue = "false") final int idType, @RequestParam(value = "idNumber", defaultValue = "false") final String idNumber, @RequestParam(value = "dob", defaultValue = "false") final String dob, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        
    	try
    	{
	        NICUserData nicUserData = sagiaZqeemahFacade.getNICUserdata(idType, idNumber, dob);
	        
	        if(nicUserData != null)
	        {
	        	return new Gson().toJson(nicUserData);
	        }
    	}
    	catch(Exception e)
    	{
    		//catch all exception
    	}
        return "";
    }
    
    /**
     * retrieves the countries list and provide it in JSON format for frontend
     *
     * @return
     */
    @RequestMapping(value = "/shareHolderCountryCheck", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getShareHolderCheckCountry() {    	
        return new Gson().toJson(sagiaCountryFacade.getShareHolderCheckCountries());
    }
    
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getReview(final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {


    	if(!isApplyAllowed()) {
            return "redirect:/dashboard-without-license";
        } 
    	
    	String validateEntityInfoTab = sagiaLicenseApplyFacade.validateEntityTab();

        if (StringUtils.isNotEmpty(validateEntityInfoTab)) {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, validateEntityInfoTab);
            return "redirect:" + THIS_CONTROLLER_REDIRECTION_URL + "/entity";
        }

        String validateShareHoldersTab = sagiaLicenseApplyFacade.validateShareHoldersTab();
        if (StringUtils.isNotEmpty(validateShareHoldersTab)) {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, validateShareHoldersTab);
            return "redirect:" + THIS_CONTROLLER_REDIRECTION_URL + "/shareholders";
        }

        String validateContactPersonTab = sagiaLicenseApplyFacade.validateContactPersonTab();
        if (StringUtils.isNotEmpty(validateContactPersonTab)) {
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, validateContactPersonTab);
            return "redirect:" + THIS_CONTROLLER_REDIRECTION_URL + "/contactperson";
        }

        model.addAttribute("activeTab", "review");
        model.addAttribute("sagiaApplyReviewForm", new SagiaApplyReviewForm());
        model.addAttribute("entityInformation", sagiaLicenseApplyFacade.getEntityInformationData());
        model.addAttribute("shareholdersDataByLicense", sagiaLicenseApplyFacade.getShareHoldersDataByLicense());
        model.addAttribute("contactPerson", sagiaLicenseApplyFacade.getContactPersonData());
        model.addAttribute("selectedISICDetails", sagiaLicenseApplyFacade.getSelectedISICDetails());
        model.addAttribute("controllerUrl", "/my-sagia/license");
        model.addAttribute("temporaryLicenseConstant", temporaryLicenseConstant);
        model.addAttribute("regularQeemahChannel", regularQeemahChannel);
        model.addAttribute("immediateQeemahChannel", immediateQeemahChannel);
    	
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_REVIEW_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_REVIEW_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }
    
    @SuppressWarnings({ "squid:MethodCyclomaticComplexity","squid:S3776"})
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    @RequireHardLogIn
    @ResponseBody
    public ResponseEntity review(HttpServletRequest request, final SagiaApplyReviewForm sagiaApplyReviewForm, BindingResult result) throws IOException {

        if(!isApplyAllowed()) { //sanity check
            throw new IOException("not allowed");
        }
        final Map<String, Object> map = new Gson().fromJson(IOUtils.toString(request.getInputStream(), UTF_8), Map.class);
        Map<String, Object> errorMap = new HashMap<>();
        // sagiaLicenseApplyValidator.validate(map);
        if(!Boolean.valueOf(map.get("isTermsAndConditionsChecked").toString())) {
            errorMap.put("termsAndConditionsChecked", false);
        }
        if (!errorMap.isEmpty()) {
            return getResponseEntity(errorMap);
        }
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.APPLY_NEW_LICENSE);
        
		
        ServiceRequestCreation serviceRequestCreation = sagiaODataFacade.saveODataServiceRequestCreation(null);
        //sagiaZqeemah2Facade.saveServiceRequestMD(serviceRequestCreation.getGuid(),null);
        if (serviceRequestCreation.getObjectid() != null) 
        {
        	SagiaLicenseModel sagiaLicenseModel = sagiaCustomerFacade.setApplicationServiceRequestID(serviceRequestCreation);
            try
            {
            	submitLiceseData(sagiaLicenseModel);
            }
            catch(Exception e)
            {
            	//
            }
            String serviceId = serviceRequestCreation.getObjectid().trim();
			getSessionService().setAttribute(SAGIA_LICENSE_SERVICE_REQUEST_ID, serviceId);
			return ResponseEntity.status(HttpStatus.OK).body("{\"SAGIA_LICENSE_SERVICE_REQUEST_ID\"" + ":" + "\"" + serviceId + "\" }");
        }
        else
        {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    //TODO: Need to move this to facade
	private void submitLiceseData(SagiaLicenseModel sagiaLicenseModel) {
		SagiaPublishLicenseEvent sagiaPublishLicenseEvent = new SagiaPublishLicenseEvent();
		sagiaPublishLicenseEvent.setSite(baseSiteService.getCurrentBaseSite());
		sagiaPublishLicenseEvent.setSagiaLicenseModel(sagiaLicenseModel);
		eventService.publishEvent(sagiaPublishLicenseEvent);
	}    
    
    private boolean isRegularLicense(SagiaLicenseModel sagiaLicenseModel) {
		 
		 boolean isAllSame = true;
		 
		 List<IsicMasterModel> listIsicMaster = sagiaLicenseModel.getEntityInformation().getIsicActivities();
		 String firstVal = listIsicMaster.get(0).getQeemahChannel();
		 for (IsicMasterModel isicMasterModel : listIsicMaster) {
			if(!isicMasterModel.getQeemahChannel().equals(firstVal)) {
				isAllSame = false;
			}
		}
		if (isAllSame) {
			if(regularQeemahChannel.equals(firstVal))  return true;
			else return false;
		}else 
		return true ;
	 }
    
    /**
     * Ajax call to load an contact information based on the shareholder id or delegate.
     * The response is used to pre-fill the form. 
     *
     * @param crNumber crNumber
     * @return
     */
    @RequestMapping(path = {"/contact-details/{contact}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getContactDetails(@PathVariable("contact") String contact) {
    	  	
    	if (contact.startsWith(PREFIX_SHAREHOLDER)) {
    		String sharholderCode = contact.split("-")[1];
    		return new Gson().toJson(sagiaLicenseApplyFacade.getPersonShareholderByCode(sharholderCode));
    	}else {
    		String sharholderCode = contact.split("-")[1];
    		ShareHolderModel shareHolderModel = licenseApplyService.getShareHolder(sharholderCode);
    		if (shareHolderModel instanceof  PersonShareholderModel ) {
    		  return new Gson().toJson(sagiaLicenseApplyFacade.getPersonShareholderByCode(sharholderCode).getDelegateInfo());
    		}else {
    		  return new Gson().toJson(sagiaLicenseApplyFacade.getOrganizationShareholderByCode(sharholderCode).getDelegateInfo());
    		}
    	}
        
    }
}
