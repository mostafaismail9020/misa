package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ConvToNationalsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GetTextData;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.sagiaservices.services.license.ConvToNationalsFormData;
import com.sap.ibso.eservices.sagiaservices.services.license.ConvertToNationalsService;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.sap.ibso.eservices.core.sagia.services.SagiaSearchService;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/my-sagia/license/convert")
public class SagiaLicenseConvertController extends SagiaAbstractPageController {
    private static final String SAGIA_LICENSE_CONVERT_CMS_PAGE = "license-convert";
    private static final String SAGIA_LICENSE_NEW_CONVERT_TO_NATIONALS_CMS_PAGE = "license-new-convert";
    private static final String SAGIA_CREATE_TO_NATIONALS_FORM = "formNewConvertToNationals";
    private static final String SAGIA_CREATE_TO_NATIONALS_SERVICE_ID = "ZSR7";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;
	
	@Resource(name = "sagiaSearchService")
	private SagiaSearchService searchService;

    @Autowired
    private ConvertToNationalsService convertToNationalsService;

    @Autowired
    private CustomizationListService customizationListService;

    @Autowired
    private SagiaDraftFacade sagiaDraftFacade;

    /**
     * read Convert to Nationals history list
     * read the latest Convert to Nationals entry from the list, and its expanded data
     * read Convert to Nationals service info
     */
    @RequestMapping(value = {"", "/display/{srId}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String convertToNationals(
            final HttpServletRequest request, @RequestParam(value = "requestFeedback", required = false) Boolean requestFeedback,
            @PathVariable(name = "srId", required = false) String srId, final Model model) throws CMSItemNotFoundException
    {
        Collection<ConvToNationalsData> convToNationalHistory = convertToNationalsService.getConvertToNationalHistory();

        if (CollectionUtils.isNotEmpty(convToNationalHistory))
        {
            ConvToNationalsData selectedServiceRequest;
            // Consider the service request ID if present, otherwise select the first element in the collection
            if (srId != null)
            {
                // Filter the convert to national service requests for the service request ID and select it
                Optional<ConvToNationalsData> convertToNationRequest = convToNationalHistory.stream().filter(serviceRequest -> srId.equals(serviceRequest.getSrID())).findFirst();
                if (convertToNationRequest.isPresent())
                {
                    selectedServiceRequest = convertToNationalsService.getConvertToNationalBy(srId);
                    populateAttachedFilesAndMessages(selectedServiceRequest, model);
                    model.addAttribute("fromServiceRequestOverview",true);
                }
                else
                {
                    // Auto-fix: no convert to national service request found for service request ID, therefore select the first one of the collection
                    selectedServiceRequest = convertToNationalsService.getConvertToNationalBy(convToNationalHistory.stream().findFirst().get().getSrID());
                    populateAttachedFilesAndMessages(selectedServiceRequest, model);
                }
            }
            else
            {
                // There is no service request ID, therefore select the first one of the collection
                selectedServiceRequest = convertToNationalsService.getConvertToNationalBy(convToNationalHistory.stream().findFirst().get().getSrID());
                populateAttachedFilesAndMessages(selectedServiceRequest, model);
            }

            model.addAttribute("latestConvToNationals", selectedServiceRequest);
        }
        else
        {
            model.addAttribute("attachedFiles", Collections.emptyList());
            model.addAttribute("messages", Collections.emptyList());
        }
        
        ConvToNationalsData emptyServiceRequest = convertToNationalsService.getConvertToNationalBy("");
        model.addAttribute("isAllowed", emptyServiceRequest.getIsAllowed());
        model.addAttribute("isInstant", emptyServiceRequest.getIsInstant());
        model.addAttribute("disclaimer", emptyServiceRequest.getMsgToInvestor());

        model.addAttribute("processingTime", averageProcessingTimeFacade.getAverageProcessingTimeData(convertToNationalsService.getEntitySetName()));
        model.addAttribute("convertToNationals_list", convToNationalHistory);

        Collection<CustomizingGetData> serviceDetails = customizationListService.readConvToNationalsInfo();
        model.addAttribute("serviceDetailInfo", serviceDetails.stream().findFirst().orElse(null));

        Collection<CustomizingGetData> attachmentsFiles = customizationListService.readConvToNationalsSupportingAttachments();
        model.addAttribute("attachmentsFiles", attachmentsFiles);

        if (BooleanUtils.isTrue(requestFeedback)) {
            model.addAttribute("requestFeedback", "true");
        }
        model.addAttribute("serviceDescription", getServiceDescription(request.getServletPath()));
        
        SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SAGIA_CREATE_TO_NATIONALS_SERVICE_ID);
        model.addAttribute("sagiaService", sagiaService);
        
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_CONVERT_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_CONVERT_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }

    /**
     * Reads attached files and message for a convert-to-national service request and populates the data into the model.
     *
     * @param serviceRequest the convert-to-national service request
     * @param model the model
     */
    private void populateAttachedFilesAndMessages(ConvToNationalsData serviceRequest, Model model)
    {
        List<ContentHDRData> attachedFiles = serviceRequest.getConvNatToContentNav();
        model.addAttribute("attachedFiles", attachedFiles);

        List<GetTextData> messagesList = serviceRequest.getConvNatToTextNav();
        model.addAttribute("messages", messagesList);
    }

    /**
     * @return page for creating a new Convert to Nationals request
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String newConvertToNationals(final Model model) throws CMSItemNotFoundException {
    	
    	ConvToNationalsData emptyServiceRequest = convertToNationalsService.getConvertToNationalBy("");
        Collection<CustomizingGetData> attachments ;
        
        if(emptyServiceRequest.getIsInstant()) {
        	attachments = Collections.emptyList();
        }else {
        	attachments = customizationListService.readConvToNationalsSupportingAttachments();
        }
                
        model.addAttribute("attachments", attachments);        
        model.addAttribute("isAllowed", emptyServiceRequest.getIsAllowed());
        model.addAttribute("isInstant", emptyServiceRequest.getIsInstant());
        model.addAttribute("disclaimer", emptyServiceRequest.getMsgToInvestor());

        boolean draftExists = sagiaDraftFacade.isDraftExists(SAGIA_CREATE_TO_NATIONALS_FORM);
        model.addAttribute("draftExists", draftExists);
        model.addAttribute("serviceId", SAGIA_CREATE_TO_NATIONALS_SERVICE_ID);
	
		SagiaServiceModel sagiaService = searchService.getSagiaServiceByCode(SAGIA_CREATE_TO_NATIONALS_SERVICE_ID);
		model.addAttribute("sagiaService", sagiaService);
        model.addAttribute("maxUploadSize", sagiaService.getMaxFileUploadSize());
        model.addAttribute("convertToNationalsFormData", new ConvToNationalsFormData());
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
       
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_NEW_CONVERT_TO_NATIONALS_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_NEW_CONVERT_TO_NATIONALS_CMS_PAGE));
        return getViewForPage(model);
    }
}
