package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CompAndEnqHdrToTextNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ComplaintsAndEnquiryHdrsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.ComplaintsAndEnquiryService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.UpdatableComplaintDetails;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaComplaintDetailsValidator;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaComplaintsValidator;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.util.localization.Localization;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/complaints")
public class SagiaComplaintsController extends SagiaAbstractPageController {
	private static final Logger LOG = Logger.getLogger(SagiaComplaintsController.class);
	private static final String SAGIA_COMPLAINTS_HOME_PAGE = "/my-sagia/sagia-profile";
	@Autowired
	private ComplaintsAndEnquiryService complaintsAndEnquiryService;
	@Autowired
	private CustomizationListService customizationListService;
	@Autowired
	private ContentDetailsService contentDetailsService;


	@Resource(name = "sagiaComplaintsValidator")
	SagiaComplaintsValidator sagiaComplaintsValidator;

	@Resource(name = "sagiaComplaintDetailsValidator")
	SagiaComplaintDetailsValidator sagiaComplaintDetailsValidator;

	/**
	 * read a single complaint, by id, with $expand parameter
	 * @param srID id of the complaint
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{srID}")
	@RequireHardLogIn
	public String getComplaintAndEnquiry(@PathVariable("srID") String srID, HttpServletRequest request, Model model)  {
		try {
			ComplaintsAndEnquiryHdrsData complaintAndEnquiry = complaintsAndEnquiryService
					.getComplaintBy(srID);
			model.addAttribute("expandedComplaintFormData", complaintAndEnquiry);
			model.addAttribute("messages", complaintAndEnquiry.getCompAndEnqHdrToTextNav());
			model.addAttribute("uploadedAttachments", complaintAndEnquiry.getCompAndEnqHdrToContentNav());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		
        return "fragments/complaint/expandedComplaint";
	}

	/**
	 * read the list of messages of a specific complaint
	 * @param srID id of the complaint
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{srID}/messages", headers = "Accept=application/json")
	@RequireHardLogIn
	public @ResponseBody List<CompAndEnqHdrToTextNavData> getComplaintAndEnquiryMessages(
			@PathVariable("srID") String srID) {
		ComplaintsAndEnquiryHdrsData complaintAndEnquiry = complaintsAndEnquiryService.getComplaintBy(srID);
		return complaintAndEnquiry.getCompAndEnqHdrToTextNav();
	}

	/***
	 * create a new complaint
	 * @param complaintFormData request payload
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	@RequireHardLogIn
	public String submit(final Model model, @ModelAttribute("complaintFormData") final ComplaintFormData complaintFormData,
			final BindingResult result, final RedirectAttributes redirectModel) throws JSONException {
		sagiaComplaintsValidator.validate(complaintFormData, result);
		if (result.hasErrors()) {
			getSessionService().setAttribute("complaintFormData", complaintFormData);
			redirectModel.addFlashAttribute("org.springframework.validation.BindingResult.complaintFormData", result);
		} else {
			try {
				Collection<CustomizingGetData> supportedAttachments = customizationListService.readComplaintsSupportingAttachments();
				complaintsAndEnquiryService.createComplaint(complaintFormData, supportedAttachments);
				GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, Localization.getLocalizedString("complaint.submitted"));
			} catch (SagiaCRMException e) {
				LOG.info(e.getMessage(), e);
				parseCRMException(redirectModel, e);
			}
		}
		return REDIRECT_PREFIX + SAGIA_COMPLAINTS_HOME_PAGE + "#enquiriesTab";
	}

	private void parseCRMException(RedirectAttributes redirectModel, SagiaCRMException e) throws JSONException {
		if (e.getMessage().indexOf('{') >= 0) {
			String jsonError = new JSONObject(e.getMessage().substring(e.getMessage().indexOf('{'))).getString("error");
			if (StringUtils.isNotEmpty(jsonError)) {
				String jsonMessage = new JSONObject(jsonError).getString("message");
				if (StringUtils.isNotEmpty(jsonMessage)) {
					redirectModel.addFlashAttribute("complaintInProgress", new JSONObject(jsonMessage).getString("value"));
				}
			}
		}
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
	public @ResponseBody ComplaintsAndEnquiryHdrsData updateComplaint(@PathVariable("srID") String srID,
			@RequestBody UpdatableComplaintDetails detailsToUpdate, HttpServletRequest request, final BindingResult result) {

		sagiaComplaintDetailsValidator.validate(detailsToUpdate, result);

		if (result.hasErrors()) {
			return complaintsAndEnquiryService.getComplaintBy(srID);

		} else {
			complaintsAndEnquiryService.updateComplaint(detailsToUpdate, srID);
			return complaintsAndEnquiryService.getComplaintBy(srID);
		}
	}

	@ModelAttribute("complaintFormData")
	public ComplaintFormData getComplaintFormData() {
		return new ComplaintFormData();
	}
}
