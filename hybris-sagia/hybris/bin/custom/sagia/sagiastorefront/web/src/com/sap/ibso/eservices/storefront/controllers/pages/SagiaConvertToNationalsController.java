
package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ConvToNationalsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GetTextData;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.sagiaservices.services.license.*;
import com.sap.ibso.eservices.sagiaservices.utils.CollectionUtils;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/convertToNationals")
public class SagiaConvertToNationalsController extends SagiaAbstractPageController {

	@Autowired
	private ConvertToNationalsService convertToNationalsService;
	@Autowired
	private CustomizationListService customizationListService;

	@Resource(name = "sagiaConvertToNationalsValidator")
	private Validator sagiaConvertToNationalsValidator;

	@Autowired
	private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

	@Autowired
	private UserService userService;

	/**
	 * read  Convert to Nationals by srID
	 * read Info data for Convert to Nationals screen
	 * @return details for a specific Convert to Nationals entity
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{srID}/details")
	@RequireHardLogIn
	public ResponseEntity<DetailedConvToNationalsData> getConvertToNationalDetails(@PathVariable("srID") String srID,
			HttpServletRequest request, Model model) {
		ConvToNationalsData convToNationalData = convertToNationalsService.getConvertToNationalBy(srID);
		Collection<CustomizingGetData> serviceDetails = customizationListService.readConvToNationalsInfo();
		DetailedConvToNationalsData expandedConvNationals = ConvToNationalsConverter.createDetailedConvNationalsData(convToNationalData, serviceDetails);
		
		return new ResponseEntity<>(expandedConvNationals, HttpStatus.OK);
	}
	
	/**
	 * get the most recent Convert to nationals
	 */
	@RequestMapping(method = RequestMethod.GET, path = "latest")
	@RequireHardLogIn
	public ResponseEntity<ConvToNationalsData> getLatestConvertToNationals(HttpServletRequest request, Model model) {
		ConvToNationalsData latestConvToNationals = CollectionUtils
				.getFirstOrNullFrom(convertToNationalsService.getConvertToNationalHistory());
		return new ResponseEntity<>(latestConvToNationals, HttpStatus.OK);
	}

	/**
	 * read messages list for a single Convert to Nationals
	 * Messages can not be created by the user, they are read-only
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{srID}/messages")
	@RequireHardLogIn
	public ResponseEntity<List<GetTextData>> getConvertToNationalMessages(@PathVariable("srID") String srID,
			HttpServletRequest request, Model model) {
		ConvToNationalsData convToNationalData = convertToNationalsService.getConvertToNationalBy(srID);
		List<GetTextData> messages = convToNationalData.getConvNatToTextNav();
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}

	/**
	 * create a new Convert to Nationals request
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	@RequireHardLogIn
	public ResponseEntity submit(final Model model, @ModelAttribute("convToNationalsFormData") final ConvToNationalsFormData convToNationalsFormData,
					   final BindingResult bindingResult) {
		getSagiaConvertToNationalsValidator().validate(convToNationalsFormData,bindingResult);

		if(bindingResult.hasErrors()){
			return new ResponseEntity(new Gson().toJson(bindingResult.getAllErrors()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Collection<CustomizingGetData> supportedAttachments = customizationListService.readConvToNationalsSupportingAttachments();
		convertToNationalsService.create(convToNationalsFormData, supportedAttachments);
		sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel)userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * if a Convert to Nationals has been rejected, the user can resubmit it
	 * update the entity with new uploaded files
	 * @return
	 */
	@RequestMapping(value = "/{srId}/{srGuid}/resubmit", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	@ResponseStatus(value = HttpStatus.OK)
	@RequireHardLogIn
	public void resubmit(@PathVariable("srId") String srId, @PathVariable("srGuid") String srGuid,
						final Model model, @ModelAttribute("convToNationalsResubmitFormData") 
						final ConvToNationalsResubmitFormData convToNationalsResubmitFormData) {
		
		ConvToNationalsData currentTarget = convertToNationalsService.getConvertToNationalBy( srId);
		List<ContentHDRData> previouslyAttachedFiles = currentTarget.getConvNatToContentNav();
		List<ConvertToNationalsAttachmentFile> newlyUploadedFiles = convToNationalsResubmitFormData
							.getFiles()
							.stream()
							.filter(newFile -> newFile.getMultiPartFile().getSize() > 0)
							.collect(Collectors.toList());
		convToNationalsResubmitFormData.setFiles(newlyUploadedFiles);
		convToNationalsResubmitFormData.setSrGuid(srGuid);
		convertToNationalsService.update(convToNationalsResubmitFormData, previouslyAttachedFiles);
		sagiaTermsAndConditionsFacade.acceptTermsAndConditions((CustomerModel)userService.getCurrentUser(),TermsAndConditionsAcceptanceEventEnum.LICENSE_SERVICES);
	}

	public Validator getSagiaConvertToNationalsValidator() {
		return sagiaConvertToNationalsValidator;
	}

	public void setSagiaConvertToNationalsValidator(Validator sagiaConvertToNationalsValidator) {
		this.sagiaConvertToNationalsValidator = sagiaConvertToNationalsValidator;
	}
}
