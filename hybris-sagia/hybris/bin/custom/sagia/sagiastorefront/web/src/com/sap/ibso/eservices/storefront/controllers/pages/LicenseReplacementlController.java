package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.replace.LicenseReplaceMents;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaRuntimeException;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.licensereplacement.*;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/my-sagia/license/replace")
public class LicenseReplacementlController extends SagiaAbstractPageController {

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;
    @Resource(name = "sagiaLicenseReplacementFacade")
    private SagiaLicenseReplacementFacade sagiaLicenseReplacementFacade;

    @Resource(name = "licenseReplacementFormDataValidator")
    private Validator licenseReplacementFormDataValidator;

    @Resource(name = "sagiaLicenseFacade")
    private SagiaLicenseFacade licenseFacade;

    @Resource(name = "ZUI5SagiaFacade")
    private ZUI5SagiaFacade zui5SagiaFacade;


    @Autowired
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private SagiaDraftFacade sagiaDraftFacade;

    private static final String SAGIA_LICENSEREPL_CMS_PAGE = "license-replace";
    private static final String SAGIA_LICENSE_NEW_REPLACE_CMS_PAGE = "license-new-replace";
    private static final String SAGIA_LICENSE_RESUBMIT_REPLACE_CMS_PAGE = "license-resubmit-replace";
    private static final String ENTITY_NAME = "LicenseReplaceMents";
    private static final String LICENSE_REPLACEMENT_SERVICE_ID = "ZSR8";
    private static final Logger LOG = Logger.getLogger(LicenseReplacementlController.class);

    /**
     * read license replacements history list
     * read the latest license replacement entry from the list, and its expanded data
     * read the selected license replacement entry (e.g. selected in service requests overview component) from the list and its expanded data
     * read license replacements service info
     */
    @RequestMapping(value = {"", "/display/{srId}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getLicenseReplacements(final Model model, @PathVariable(name = "srId", required=false) String srId) throws CMSItemNotFoundException
    {
        Collection<LicenseReplaceMents> licenseReplacements = sagiaLicenseReplacementFacade.getLicenseReplacementList();

        if (licenseReplacements != null && !licenseReplacements.isEmpty())
        {
            LicenseReplaceMents licenseForm;
            // Consider the service request ID if present, otherwise select the first element in the list
            if (srId != null)
            {
                // Filter the license replacements for the service request ID and select replacement
                Optional<LicenseReplaceMents> licenseReplacement = licenseReplacements.stream().filter(replacement -> srId.equals(replacement.getSrID())).findFirst();
                if (licenseReplacement.isPresent())
                {
                    licenseForm = sagiaLicenseReplacementFacade.getLicenseReplacement(srId);
                    model.addAttribute("fromServiceRequestOverview",true);
                }
                else
                {
                    // Auto-fix: no license replacement found for service request ID, therefore select the first one of the collection
                    licenseForm = sagiaLicenseReplacementFacade.getLicenseReplacement(licenseReplacements.stream().findFirst().get().getSrID());
                }
            }
            else
            {
                // There is no service request ID, therefore select the first one of the collection
                licenseForm = sagiaLicenseReplacementFacade.getLicenseReplacement(licenseReplacements.stream().findFirst().get().getSrID());
            }

            model.addAttribute("licensereplacementFormData", licenseForm);
            model.addAttribute("messages", licenseForm.getMessages());
            model.addAttribute("attachedFiles", licenseForm.getUploadedAttachments());
        }
        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(ENTITY_NAME));
        model.addAttribute(ENTITY_NAME, licenseReplacements);
        Collection<CustomizingGetData> licensereplacementinfos = sagiaLicenseReplacementFacade.getLicenseReplacementInfo();
        model.addAttribute("licenseReplaceMentinfo", licensereplacementinfos.stream().findFirst().orElse(null));
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSEREPL_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSEREPL_CMS_PAGE));
        model.addAttribute(ENTITY_STATUS, getSessionService().getCurrentSession().getAttribute(ENTITY_STATUS));
        return getViewForPage(model);
    }
    /**
     * @return page for creating a new License Replacement request
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String newLicenseReplacement(final Model model) throws CMSItemNotFoundException {
        model.addAttribute("licenseReplacementFormData",new LicenseReplacementFormData());
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_NEW_REPLACE_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_NEW_REPLACE_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

        boolean draftExists = sagiaDraftFacade.isDraftExists(LICENSE_REPLACEMENT_SERVICE_ID);
        model.addAttribute("draftExists", draftExists);
        model.addAttribute("serviceId", LICENSE_REPLACEMENT_SERVICE_ID);

        return getViewForPage(model);
    }

    @RequestMapping(value = "/resubmit", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String resubmitLicenseReplacement(final Model model) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_RESUBMIT_REPLACE_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_RESUBMIT_REPLACE_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        boolean draftExists = sagiaDraftFacade.isDraftExists("licenseReplacementResubmitFormData");
        model.addAttribute("draftExists", draftExists);
        return getViewForPage(model);
    }


    @RequestMapping(value = "/{srId}/{srGuid}/resubmit", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @ResponseStatus(value = HttpStatus.OK)
    @RequireHardLogIn
    public void resubmit(@PathVariable("srId") String srId, @PathVariable("srGuid") String srGuid,
                         final Model model, @ModelAttribute("licenseReplacementResubmitFormData")
                         final LicenseReplacementResubmitFormData licenseReplacementResubmitFormData) {

        LicenseReplaceMentData currentTarget = sagiaLicenseReplacementFacade.getLicenseReplacementData(srId);
        List<ContentHDRData> previouslyAttachedFiles = currentTarget.getLicenseReplaceMentToContentNav();

        List<LicenseReplacementAttachmentFile> newlyUploadedFiles = licenseReplacementResubmitFormData
                .getFiles()
                .stream()
                .filter(newFile -> newFile.getMultiPartFile().getSize() > 0)
                .collect(Collectors.toList());
        licenseReplacementResubmitFormData.setFiles(newlyUploadedFiles);
        licenseReplacementResubmitFormData.setSrGuid(srGuid);
        sagiaLicenseReplacementFacade.update(licenseReplacementResubmitFormData, previouslyAttachedFiles);
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel)userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
    }

    /**
     * read License Replacement by ID
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    public ResponseEntity<DetailedLicenseReplacementData> getLicenseReplacement(@PathVariable("id") String id, final Model model) throws CMSItemNotFoundException {
        if (!model.containsAttribute(ENTITY_NAME)) {
            Collection<LicenseReplaceMents> licensereplacements = sagiaLicenseReplacementFacade.getLicenseReplacementList();
            model.addAttribute(ENTITY_NAME, licensereplacements);
        }
        LicenseReplaceMents licensereplacement = sagiaLicenseReplacementFacade.getLicenseReplacement(id);

        Collection<CustomizingGetData> licensereplacementinfos = sagiaLicenseReplacementFacade.getLicenseReplacementInfo();

        DetailedLicenseReplacementData expandedLicenseReplacement = LicenseReplacementConverter.createDetailedLicenseReplacementData(licensereplacement,licensereplacementinfos);
        return new ResponseEntity<>(expandedLicenseReplacement, HttpStatus.OK);
    }

    /**
     * get attachments by objectsId, documentID
     * @param objectId
     * @param documentId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/attachment/{objectId}/{documentID}")
    @RequireHardLogIn
    public ResponseEntity<InputStreamResource> getAttachments(@PathVariable("objectId") String objectId,
                                                              @PathVariable("documentID") String documentId, HttpServletRequest request, Model model) {
        try {
            InputStream complaintAndEnquiry = sagiaLicenseReplacementFacade.getContentDetailsService().readAttachmentBy(objectId, documentId);
            InputStreamResource attachmentInputStreamResource = new InputStreamResource(complaintAndEnquiry);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            return new ResponseEntity<>(attachmentInputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("stacktrace",e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /***
     * create licenseReplaceMent
     * @param model
     * @param licenseReplacementFormData
     * @param result
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    @RequireHardLogIn
    public ResponseEntity submit(final Model model,
                                 @ModelAttribute("licenseReplacementFormData") final LicenseReplacementFormData licenseReplacementFormData,
                                 final BindingResult result) {

        if (licenseFacade.hasInvalidLicense(zui5SagiaFacade.getHomeHDRData())) {
            throw new SagiaRuntimeException("License Replacement is not allowed. Entity status is invalid!");
        }

        try {
            getLicenseReplacementFormDataValidator().validate(licenseReplacementFormData, result);

            if (result.hasErrors()) {
                return new ResponseEntity<>(new Gson().toJson(result.getAllErrors()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Collection<CustomizingGetData> supportedAttachments = sagiaLicenseReplacementFacade
                    .getCustomizationListService().readLicenseReplacementSupportingAttachments();

            sagiaLicenseReplacementFacade.createLicenseReplacement(licenseReplacementFormData, supportedAttachments);
            sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel) userService.getCurrentUser(),
                    TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (SagiaCRMException ex) {
            LOG.error("Displaying CRM error message to the user. ",ex);
            try {
                return new ResponseEntity(parseException(ex), HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (JSONException e) {
                LOG.error(e.getMessage(), e);
                return new ResponseEntity<>(new Gson().toJson(result.getAllErrors()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new Gson().toJson(result.getAllErrors()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Validator getLicenseReplacementFormDataValidator() {
        return licenseReplacementFormDataValidator;
    }

    public void setLicenseReplacementFormDataValidator(Validator licenseReplacementFormDataValidator) {
        this.licenseReplacementFormDataValidator = licenseReplacementFormDataValidator;
    }

    private String parseException(SagiaCRMException ex) throws JSONException {
        if (StringUtils.isEmpty(ex.getMessage())) {
            return null;
        }

        String jsonError = new JSONObject(ex.getMessage().substring(ex.getMessage().indexOf("{"))).getString("error");
        if (StringUtils.isEmpty(jsonError)) {
            return null;
        }

        String jsonMessage = new JSONObject(jsonError).getString("message");
        if (StringUtils.isEmpty(jsonMessage)) {
            String jsonErrorMessage = new JSONObject(jsonMessage).getString("value");
            return jsonErrorMessage;
        }
        String jsonValue = new JSONObject(jsonMessage).getString("value");
        return jsonValue;
    }
}
