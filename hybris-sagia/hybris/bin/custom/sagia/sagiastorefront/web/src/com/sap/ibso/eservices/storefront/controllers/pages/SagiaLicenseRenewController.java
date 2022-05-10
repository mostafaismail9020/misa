package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.account.RemovePopupALRResponse;
import com.sap.ibso.eservices.facades.data.license.amendment.LicenseAmendment;
import com.sap.ibso.eservices.facades.form.LicenseRenewalForm;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovernmentDocumentsCheck;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.SagiaTemporaryBiddingLicenseJsonResponse;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.apache.commons.io.IOUtils.toByteArray;
import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping("/my-sagia/license")
public class SagiaLicenseRenewController extends SagiaAbstractPageController {
    private static final String SAGIA_LICENSE_RENEW_CMS_PAGE = "license-renew";
    private static final String SAGIA_LICENSE_RENEW_EDIT_CMS_PAGE = "license-renew-edit";
    private static final String SAGIA_LICENSE_RENEW_SERVICE_ID = "ZSR4";

    private static final String ENTITY_NAME = "ServiceReqHDRs";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "sagiaRenewLicenseValidator")
    private Validator sagiaRenewLicenseValidator;

    @Resource(name = "sagiaRenewMyLicenceFacade")
    private SagiaRenewMyLicenseFacade sagiaRenewMyLicenseFacade;

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "sagiaDraftFacade")
    private SagiaDraftFacade sagiaDraftFacade;

    @Resource(name = "sagiaAccountFacade")
    private SagiaAccountFacade sagiaAccountFacade;

    @Resource(name ="sagiaTermsAndConditionsFacade")
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "ZUI5SagiaFacade")
    private ZUI5SagiaFacade zui5SagiaFacade;

    @Resource(name = "i18NService")
    private I18NService i18NService;
	
	@Resource(name = "sagiaSearchService")
	private SagiaSearchService searchService;

    @RequestMapping(value = {"/renew/edit","/renew/reapply/{id}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String renewEdit(final Model model, final HttpServletRequest request, @PathVariable("id") Optional<String> id) throws CMSItemNotFoundException {
        LicenseRenewalForm licenseRenewalForm;
        Integer maxDuration = 0 ;
        Integer licenseFeePerYr = 0 ;
        Integer subsOutstandingFee = 0 ;
        if(id.isPresent() && Strings.isNotEmpty(id.get())){
            ServiceRequestHDR serviceRequest = sagiaRenewMyLicenseFacade.getServiceRequestHDR(id.get());
            licenseRenewalForm = getLicenseRenewalForm(serviceRequest);
            model.addAttribute("reapply", true);
            model.addAttribute("reapplyId", id.get());
            model.addAttribute("resubmittedDocuments", serviceRequest.getAttachedDocuments());
            model.addAttribute("resubmittedImages", serviceRequest.getAttachedImages());
            model.addAttribute("licExDateData", serviceRequest.getLicenseExpirationDateDate());
          	maxDuration = sagiaAccountFacade.getRenewalMaxDuration(serviceRequest.getMaxLicenseDuration()); 
        	licenseFeePerYr = sagiaAccountFacade.getRenewalLicenseFeePerYr(serviceRequest.getLicenseFeePerYr());
        	subsOutstandingFee = sagiaAccountFacade.getSubsOutstandingFee(serviceRequest.getSubscriptionOutstandingFee());
   
        }
        else{
        	ServiceRequestHDR serviceRequest = sagiaRenewMyLicenseFacade.getServiceRequestHDR();
        	maxDuration = sagiaAccountFacade.getRenewalMaxDuration(serviceRequest.getMaxLicenseDuration());
        	licenseFeePerYr = sagiaAccountFacade.getRenewalLicenseFeePerYr(serviceRequest.getLicenseFeePerYr());
        	subsOutstandingFee = sagiaAccountFacade.getSubsOutstandingFee(serviceRequest.getSubscriptionOutstandingFee());
            licenseRenewalForm = getLicenseRenewalForm(serviceRequest);
            model.addAttribute("licExDateData", serviceRequest.getLicenseExpirationDateDate());
            model.addAttribute("reapply", false);
            model.addAttribute("attachments", sagiaRenewMyLicenseFacade.getLicenseRenewalSupportingAttachments());
        }
		SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SAGIA_LICENSE_RENEW_SERVICE_ID);
        model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());
        model.addAttribute("licenseRenewalForm", licenseRenewalForm);
        /*Draft attributes*/
        model.addAttribute("draftExists", sagiaDraftFacade.isDraftExists(SAGIA_LICENSE_RENEW_SERVICE_ID));
        model.addAttribute("serviceId", SAGIA_LICENSE_RENEW_SERVICE_ID);
        model.addAttribute("srID", request.getAttribute("srId"));
        model.addAttribute("countries", sagiaRenewMyLicenseFacade.getCountries(i18NService.getCurrentLocale().getLanguage().substring(0, 1).toUpperCase()));
       	
      	model.addAttribute("durations", getLicenseDurations(i18NService.getCurrentLocale(), maxDuration, licenseFeePerYr, subsOutstandingFee));
      
      	
      	
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_RENEW_EDIT_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_RENEW_EDIT_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }

    @RequestMapping(value = "/renew/removePopupALR", method = RequestMethod.GET)
    @ResponseBody
    @RequireHardLogIn
    public RemovePopupALRResponse removePopupALR() {
        return sagiaRenewMyLicenseFacade.removePopupALR();
    }

    @RequestMapping(value = "/renew/checkGovernmentDocuments", method = RequestMethod.GET)
    @ResponseBody
    @RequireHardLogIn
    public GovernmentDocumentsCheck checkGovernmentDocuments() {
        return sagiaRenewMyLicenseFacade.checkGovernmentDocuments();
    }

    @RequestMapping(value = "/renew/check", method = RequestMethod.GET)
    @ResponseBody
    @RequireHardLogIn
    public ResponseEntity checkLicenseRenewalAvailability() {
        sagiaRenewMyLicenseFacade.checkLicenseRenewalAvailability();
        return new ResponseEntity(HttpStatus.OK);
    }

    private LicenseRenewalForm getLicenseRenewalForm(ServiceRequestHDR serviceRequest) {
        LicenseRenewalForm licenseRenewalForm = new LicenseRenewalForm();
        licenseRenewalForm.setStreet(serviceRequest.getAddress().getStreet());
        licenseRenewalForm.setHouseNo(serviceRequest.getAddress().getHouseNo());
        licenseRenewalForm.setCountry(serviceRequest.getAddress().getCountry());
        licenseRenewalForm.setCity(serviceRequest.getAddress().getCity());
        licenseRenewalForm.setZipCode(serviceRequest.getAddress().getZipCode());
        licenseRenewalForm.setAdditNo(serviceRequest.getAddress().getAdditionalNotes());
        licenseRenewalForm.setBuildingNo(serviceRequest.getAddress().getBuilding());
        licenseRenewalForm.setDuration(serviceRequest.getLicenseDuration());
        
        
        return licenseRenewalForm;
    }

    @RequestMapping(value = {"/renew", "/renew-redirect","renew/display/{srId}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String renew(final Model model, final HttpServletRequest request, @PathVariable(name = "srId", required = false) String srId) throws CMSItemNotFoundException
    {
        addRenewalFlags(model);
        
        Collection<ServiceRequestHDR> serviceRequestHDRs = sagiaRenewMyLicenseFacade.getServicesRequestHDR();
        Collection<ListItem> serviceInfo = sagiaRenewMyLicenseFacade.getServiceInfo("ZSR4");

        if (serviceRequestHDRs != null && !serviceRequestHDRs.isEmpty())
        {
            ServiceRequestHDR selectedServiceRequest;
            // Consider the service request ID if present, otherwise select the first element in the list
            if (srId != null)
            {
                // Filter the service requests for the service request ID and select it
                Optional<ServiceRequestHDR> serviceRequestHDR = serviceRequestHDRs.stream().filter(serviceRequest -> srId.equals(serviceRequest.getSrID())).findFirst();
                if (serviceRequestHDR.isPresent())
                {
                    selectedServiceRequest = sagiaRenewMyLicenseFacade.getServiceRequestHDR(srId);
                    model.addAttribute("fromServiceRequestOverview",true);
                }
                else
                {
                    // Auto-fix: no service request found for service request ID, therefore select the first one of the collection
                    selectedServiceRequest = sagiaRenewMyLicenseFacade.getServiceRequestHDR(serviceRequestHDRs.stream().findFirst().get().getSrID());
                }
            }
            else
            {
                // There is no service request ID, therefore select the first one of the collection
                selectedServiceRequest = sagiaRenewMyLicenseFacade.getServiceRequestHDR(serviceRequestHDRs.stream().findFirst().get().getSrID());
            }

            model.addAttribute("license", selectedServiceRequest);
        }
        
        ServiceRequestHDR emptyServiceRequest = sagiaRenewMyLicenseFacade.getServiceRequestHDR("");
        model.addAttribute("isAllowed", emptyServiceRequest.getIsAllowed());
        model.addAttribute("disclaimer", emptyServiceRequest.getMsgToInvestor());

        model.addAttribute("licenseRenew", serviceRequestHDRs);
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute("serviceInformation", serviceInfo.stream().findFirst().orElse(null));
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_RENEW_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_RENEW_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

        model.addAttribute("fromRenewSubmitPage", false);
        if (request.getRequestURI().contains("renew-redirect")) {
            model.addAttribute("fromRenewSubmitPage", true);
        }
        SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SAGIA_LICENSE_RENEW_SERVICE_ID);
        model.addAttribute("sagiaService", sagiaService);
        model.addAttribute("serviceDescription", getServiceDescription(request.getServletPath()));
        return getViewForPage(model);
    }

    @RequestMapping(value = "/image/{objectId}/{documentId}", produces = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE})
    @RequireHardLogIn
    @ResponseBody
    public byte[] getImage(@PathVariable("objectId") String objectId, @PathVariable("documentId") String documentId) throws IOException {
        return toByteArray(sagiaRenewMyLicenseFacade.getFile(objectId, documentId));
    }

    @RequestMapping(value = "/renew/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequireHardLogIn
    public String renewId(@PathVariable("id") String id, HttpServletRequest request) throws  IOException {
        ServiceRequestHDR serviceRequest = sagiaRenewMyLicenseFacade.getServiceRequestHDR(id);
        request.getSession().setAttribute("srId", serviceRequest.getSrID());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        return gson.toJson(serviceRequest);
    }

    @RequestMapping(value = {"/renew/edit","/renew/reapply/{id}"}, method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public ResponseEntity submit(final Model model, @ModelAttribute("licenseRenewalForm") final LicenseRenewalForm licenseRenewalForm, final BindingResult result, @PathVariable("id") Optional<String> id) {
        if(id.isPresent() && Strings.isNotEmpty(id.get())){
            ServiceRequestHDR serviceRequestHDR = sagiaRenewMyLicenseFacade.getServiceRequestHDR(id.get());
            licenseRenewalForm.setObjectId(id.get());
            licenseRenewalForm.setPreviouslyAttachedDocuments(serviceRequestHDR.getAttachedDocuments());
            licenseRenewalForm.setPreviouslyAttachedImages(serviceRequestHDR.getAttachedImages());
            licenseRenewalForm.setSrGuid(serviceRequestHDR.getSrGuid());
        }
        else{
            Collection<ListItem> attachmentList = sagiaRenewMyLicenseFacade.getLicenseRenewalSupportingAttachments();
            long uploadedFilesCount = licenseRenewalForm.getMultipartFile().stream().filter( x -> x.getSize() > 0).count();

            if(uploadedFilesCount != attachmentList.size()){
                throw new SagiaCRMException(getMessageSource().getMessage("renewlicense.support.document.required", null, getI18nService().getCurrentLocale()));
            }
        }
        SagiaTemporaryBiddingLicenseJsonResponse response = new SagiaTemporaryBiddingLicenseJsonResponse();
        sagiaRenewLicenseValidator.validate(licenseRenewalForm, result);
        if(licenseRenewalForm.getImg() == null || licenseRenewalForm.getImg().isEmpty()) {
            //throw new SagiaCRMException(getMessageSource().getMessage("renewlicense.support.image.required", null, getI18nService().getCurrentLocale()));
        }
        if(licenseRenewalForm.getDuration() == null || licenseRenewalForm.getDuration().isEmpty()) {
            throw new SagiaCRMException(getMessageSource().getMessage("renewlicense.support.duration.required", null, getI18nService().getCurrentLocale()));
        }

        if (result.hasErrors()) {
            return new ResponseEntity<>(new Gson().toJson(result.getAllErrors()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sagiaRenewMyLicenseFacade.renewServiceRequest(licenseRenewalForm);
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(), TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
        ServiceRequestHDR newCreatedEntity = sagiaRenewMyLicenseFacade.getLatestServiceRequestHDR();
        response.setValid(true);
        response.setNewEntityCreatedId(newCreatedEntity.getSrID());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/renew/instant/{period}", method = RequestMethod.POST)
    @ResponseBody
    @RequireHardLogIn
    public ResponseEntity instantRenew(@PathVariable("period") String period,@RequestParam(value = "duration", required = false) String duration){
        sagiaRenewMyLicenseFacade.checkLicenseRenewalAvailability();
        ServiceRequestHDR serviceRequestHDR = new ServiceRequestHDR();
        serviceRequestHDR.setReApply(false);
        AddressHDR address = new AddressHDR();
        address.setPeriod(period);
        
        serviceRequestHDR.setAddress(address);

        ContentHDRDocument document = new ContentHDRDocument();
        List<ContentHDRDocument> documentCollection = new ArrayList<>();
        documentCollection.add(document);
        serviceRequestHDR.setAttachedDocuments(documentCollection);
        if(duration!= null) {
          serviceRequestHDR.setLicenseDuration(duration);
        }
        sagiaRenewMyLicenseFacade.renewServiceRequest(serviceRequestHDR);

        SagiaTemporaryBiddingLicenseJsonResponse response = new SagiaTemporaryBiddingLicenseJsonResponse();
        ServiceRequestHDR newCreatedEntity = sagiaRenewMyLicenseFacade.getLatestServiceRequestHDR();
        response.setValid(true);
        response.setNewEntityCreatedId(newCreatedEntity.getSrID());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public void addRenewalFlags(Model model){
    	HomeHDRData homeHDRData = zui5SagiaFacade.getHomeHDRData();
        LicenseRenewalIndicators indicators = sagiaAccountFacade.getRenewalIndicators(homeHDRData);
        model.addAttribute("autoRenewal",indicators.isAutoRenewal());
        model.addAttribute("autoRenewalClearance",indicators.isAutoRenewalForClearance());
        model.addAttribute("governmentDocumentsCheck",indicators.isCheckGovernmentDocuments());
        model.addAttribute("clearanceCheck",indicators.isCheckRenewalForClearance());
        
    	Integer maxDuration = sagiaAccountFacade.getRenewalMaxDuration(homeHDRData.getMaxLicenseDuration());
    	Integer licenseFeePerYr = sagiaAccountFacade.getRenewalLicenseFeePerYr(homeHDRData.getLicenseFeePerYr());
    	Integer subsOutstandingFee = sagiaAccountFacade.getSubsOutstandingFee(homeHDRData.getSubscriptionOutstandingFee());
        model.addAttribute("durations", getLicenseDurations(i18NService.getCurrentLocale(), maxDuration, licenseFeePerYr, subsOutstandingFee));
        model.addAttribute("licExDateData", sagiaAccountFacade.getLicExDateData(homeHDRData));
        
    }
    
    public List<LicenseDuration> getLicenseDurations(Locale language,Integer maxDuration,Integer licenseFeePerYr,Integer subsOutstandingFee) {
        List<LicenseDuration> licenseDurations = new ArrayList<>();
        
        for (int i = 1 ; i <= maxDuration ; i++ ) {
       		LicenseDuration licenseDuration = new LicenseDuration();
        	licenseDuration.setCode(String.valueOf(i));
        	if(licenseFeePerYr > 0) {
        		if(subsOutstandingFee > 0) {
        			licenseDuration.setName(getMessageSource().getMessage("license.entity.year.outstanding."+i, new Object[] {i*licenseFeePerYr, subsOutstandingFee}, getI18nService().getCurrentLocale()));
        		}else {
        			licenseDuration.setName(getMessageSource().getMessage("license.entity.year.renew."+i, new Object[] {i*licenseFeePerYr}, getI18nService().getCurrentLocale()));
        		}
        		
        	}else {
        		licenseDuration.setName(getMessageSource().getMessage("license.entity.year.emptyLicenseFee."+i, null, getI18nService().getCurrentLocale()));
        	}
        	licenseDurations.add(licenseDuration);
        }
        
        return licenseDurations;
    }
}
