package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicense;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseAttachment;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseCountry;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTemporaryBiddingLicenseFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseData;
import com.sap.ibso.eservices.storefront.controllers.SagiaConstants;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.SagiaTemporaryBiddingLicenseCountryFormElement;
import com.sap.ibso.eservices.storefront.forms.SagiaTemporaryBiddingLicenseForm;
import com.sap.ibso.eservices.storefront.forms.SagiaTemporaryBiddingLicenseJsonResponse;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/my-sagia/license")
public class SagiaLicenseBiddingController extends SagiaAbstractPageController {
    private static final String SAGIA_LICENSE_BIDDING_CMS_PAGE = "license-bidding";
    private static final String SAGIA_LICENSE_BIDDING_FORM = "temporaryBiddingLicenseFormId";
    private static final String SAGIA_LICENSE_BIDDING_ID = "ZTBC";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "sagiaTemporaryBiddingLicenseFacade")
    private SagiaTemporaryBiddingLicenseFacade sagiaTemporaryBiddingLicenseFacade;

    @Resource(name = "sagiaTemporaryBiddingLicenseValidator")
    private Validator temporaryBiddingLicenseValidator;

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "mediaService")
    private MediaService mediaService;

    @Autowired
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private SagiaDraftFacade sagiaDraftFacade;

    @Resource(name = "i18NService")
    private I18NService i18NService;
	
	@Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @RequestMapping(value = "/bidding", method = RequestMethod.GET)
    @RequireHardLogIn
    public String bidding(final Model model) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_BIDDING_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_BIDDING_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        model.addAttribute("controllerUrl", "/my-sagia/license");
        model.addAttribute("sagiaTemporaryBiddingLicenseForm", new SagiaTemporaryBiddingLicenseForm());
        model.addAttribute("countries", mapCountries(sagiaTemporaryBiddingLicenseFacade.getCountries(i18NService.getCurrentLocale().getLanguage().substring(0, 1).toUpperCase())));
        model.addAttribute("governmentEntities", sagiaTemporaryBiddingLicenseFacade.getGovernmentEntities(i18NService.getCurrentLocale().getLanguage().substring(0, 1).toUpperCase()));
		model.addAttribute("maxUploadSize", configurationService.getConfiguration().getString("import.tbc.max.file.upload.size.mb"));

        boolean draftExists = sagiaDraftFacade.isDraftExists(SAGIA_LICENSE_BIDDING_ID);
        model.addAttribute("draftExists", draftExists);
        model.addAttribute("serviceId", SAGIA_LICENSE_BIDDING_ID);
        return getViewForPage(model);
    }

    private List<SagiaTemporaryBiddingLicenseCountryFormElement> mapCountries(List<TemporaryBiddingLicenseCountry> countries) {
        return countries.stream().map(country -> {
            SagiaTemporaryBiddingLicenseCountryFormElement countryFormElement = new SagiaTemporaryBiddingLicenseCountryFormElement();
            countryFormElement.setName(country.getName());
            countryFormElement.setCode(country.getCode() + " - " + country.getName());
            return countryFormElement;
        }).collect(Collectors.toList());
    }

    @RequestMapping(value = "/bidding-code/{countryCode}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String biddingCode(final Model model, @PathVariable String countryCode) {
        return sagiaTemporaryBiddingLicenseFacade.getCountryPrefix(countryCode);
    }

    @RequestMapping(value = "/bidding-create", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequireHardLogIn
    public SagiaTemporaryBiddingLicenseJsonResponse biddingCreate(
            final Model model,
            @ModelAttribute("sagiaTemporaryBiddingLicenseForm") final SagiaTemporaryBiddingLicenseForm sagiaTemporaryBiddingLicenseForm,
            final BindingResult result) throws IOException {
        SagiaTemporaryBiddingLicenseJsonResponse response = new SagiaTemporaryBiddingLicenseJsonResponse();
        temporaryBiddingLicenseValidator.validate(sagiaTemporaryBiddingLicenseForm, result);
        if (result.hasFieldErrors()) {
            Map<String, String> errorsMap = new HashMap<>();
            result.getFieldErrors().forEach(e -> {
                String message = messageSource.getMessage(e, getI18nService().getCurrentLocale());
                errorsMap.put(e.getField(), message);
            });
            response.setErrors(errorsMap);
            response.setValid(false);
            return response;
        }

        TemporaryBiddingLicenseData newEntityCreated = sagiaTemporaryBiddingLicenseFacade
                .createTemporaryBiddingLicense(mapTemporaryBiddingLicense(sagiaTemporaryBiddingLicenseForm));
        sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel)userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
        sagiaDraftFacade.removeDraft(SAGIA_LICENSE_BIDDING_FORM);

        response.setValid(true);
        response.setNewEntityCreatedId(newEntityCreated.getId());
        return response;
    }

    private TemporaryBiddingLicense mapTemporaryBiddingLicense(SagiaTemporaryBiddingLicenseForm formObject) throws IOException {
        TemporaryBiddingLicense temporaryBiddingLicense = new TemporaryBiddingLicense();
        temporaryBiddingLicense.setCompanyNameInArabic(formObject.getCompanyNameInArabic());
        temporaryBiddingLicense.setCompanyNameInEnglish(formObject.getCompanyNameInEnglish());
        temporaryBiddingLicense.setProjectNameInArabic(formObject.getProjectNameInArabic());
        temporaryBiddingLicense.setProjectNameInEnglish(formObject.getProjectNameInEnglish());
        temporaryBiddingLicense.setCapital(formObject.getCapital());
        temporaryBiddingLicense.setGovernmentEntity(formObject.getGovernmentEntity());
        temporaryBiddingLicense.setCountry(formObject.getCountry());
        temporaryBiddingLicense.setCity(formObject.getCity());
        temporaryBiddingLicense.setPostalCode(formObject.getPostalCode());
        temporaryBiddingLicense.setPoBox(formObject.getPoBox());
        temporaryBiddingLicense.setTelephone(formObject.getTelephone());
        temporaryBiddingLicense.setMobile(formObject.getMobile());
        temporaryBiddingLicense.setEmail(formObject.getEmail());

        List<TemporaryBiddingLicenseAttachment> attachments = new ArrayList<>();
        List<MultipartFile> formFiles = formObject.getTemporaryBiddingLicenseMultipartFiles();
        final List<String> draftFiles = formObject.getDraftFileData();
        List<String> attachmentsNames = SagiaConstants.TemporaryBiddingLicenseConstants.getAttachmentsNames();
        for (int i = 0; i < attachmentsNames.size(); i++) {
            if (indexExists(i, formFiles) || indexExists(i, draftFiles)) {
                TemporaryBiddingLicenseAttachment attachment = new TemporaryBiddingLicenseAttachment();
                attachment.setFilename(attachmentsNames.get(i));
                attachment.setMimeType(SagiaConstants.TemporaryBiddingLicenseConstants.MIME_TYPE);

                final String content;
                if (formFiles != null && formFiles.get(i) != null ){
                    content = new String(Base64.getEncoder().encode(formFiles.get(i).getBytes()));
                    attachment.setContent(content);
                    attachments.add(attachment);
                }
                else if (draftFiles != null && draftFiles.get(i) != null){
                    final String fileCode = draftFiles.get(i);
                    final CatalogUnawareMediaModel uploadedFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                    content = new String(Base64.getEncoder().encode(mediaService.getDataFromMedia(uploadedFile)));
                    attachment.setContent(content);
                    attachments.add(attachment);
                }
            }
        }
        temporaryBiddingLicense.setAttachments(attachments);

        return temporaryBiddingLicense;
    }

    private boolean indexExists(int index, List list) {
        return list != null && index < list.size();
    }

    @ModelAttribute("sagiaTemporaryBiddingLicenseForm")
    public SagiaTemporaryBiddingLicenseForm getTemporaryBiddingLicenseForm() {
        return new SagiaTemporaryBiddingLicenseForm();
    }
}
