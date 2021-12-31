package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import com.investsaudi.portal.core.service.utils.PaginationUtils;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.util.Config;
import com.sap.ibso.eservices.sagiaservices.services.complaints.ContactUsService;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GeneralInquirySet;

import com.sap.ibso.eservices.sagiaservices.services.impl.GeneralInquirySetService;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;

import com.sap.ibso.eservices.facades.sagia.SagiaComplaintFacade;

import web.src.com.sap.ibso.eservices.storefront.forms.ContactUsForm;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ContactUsFormData;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.ibso.eservices.storefront.controllers.pages.portal.DefaultPageController;


@Controller
@RequestMapping(value = "/about")
public class AboutSaudiPageController extends DefaultPageController {
   
    private static final Logger LOG = Logger.getLogger(AboutSaudiPageController.class);
    
    private static final String ABOUT_SAUDI_PAGE = "/aboutSaudi";
    
    private static final String ABOUT_CONTACTUS_PAGE = "/contactUs";
    
    @Autowired
	private ContactUsService contactUsService;
    
    @Autowired
	private GeneralInquirySetService generalInquirySetService;
    
    @Resource(name = "sagiaComplaintFacade")
    private SagiaComplaintFacade sagiaComplaintFacade;
    
    @RequestMapping(value = "/aboutSaudi", method = {RequestMethod.GET})
    public String aboutSaudiPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException {

    	ContentPageModel contentPageModel = getContentPageForLabelOrId(ABOUT_SAUDI_PAGE);
    	storeCmsPageInModel(model, contentPageModel);
    	storeContentPageTitleInModel(model, contentPageModel.getTitle());
        return getViewForPage(model);        
    }
    
    @RequestMapping(value = "/contactUs", method = {RequestMethod.GET})
    public String contactUsPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException {

    	LOG.info("Hit Successfully GET Contact Controller");
    	model.addAttribute("complaintFormData", sagiaComplaintFacade.getComplaintFormData()	);
    	ContentPageModel contentPageModel = getContentPageForLabelOrId(ABOUT_CONTACTUS_PAGE);
    	storeCmsPageInModel(model, contentPageModel);
    	storeContentPageTitleInModel(model, contentPageModel.getTitle());
        return getViewForPage(model);        
    }
    
	
	@RequestMapping(value = "/contactUs",method = RequestMethod.POST,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.TEXT_PLAIN_VALUE})
	public String submitForm(final Model model, @ModelAttribute("contactUsForm") final ContactUsFormData contactUsFormData, final BindingResult bindingResult,
			final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
			
	{
		LOG.info("Hit Successfully POST Contact Controller");
		
		if(Objects.nonNull(contactUsFormData)) {
			LOG.debug("First Name "+contactUsFormData.getFirstName());
			LOG.debug("Last Name "+contactUsFormData.getLastName());
			LOG.debug("email  "+contactUsFormData.getEmail());
			LOG.debug("phone Number "+contactUsFormData.getPhoneNumber());
			LOG.debug(" message  "+contactUsFormData.getMessage());
			LOG.debug(" selectedEnquiryType  "+contactUsFormData.getSelectedEnquiryType());
			LOG.debug(" selectedCategoryOne  "+contactUsFormData.getSelectedCategoryOne());
			if(Objects.nonNull(contactUsFormData.getContactfile())) {
				LOG.info("Uploaded file name "+contactUsFormData.getContactfile().getOriginalFilename());
			}
		}
		try {
			GeneralInquirySet payload = contactUsService.createContactUsTicket(contactUsFormData);
			generalInquirySetService.readContactUsSupportingAttachment(payload.getOBJECT_ID(),contactUsFormData);
			LOG.info("Object_ID Created from CRM : "+payload.getOBJECT_ID());
		} catch (SagiaCRMException e) {
			LOG.info(e.getMessage(), e);
			//parseCRMException(redirectModel, e);
		}
		
		 ContentPageModel contentPageModel = getContentPageForLabelOrId(ABOUT_CONTACTUS_PAGE);
	       
	     storeCmsPageInModel(model, contentPageModel);
	     return getViewForPage(model);
	}
	
	
    
}

