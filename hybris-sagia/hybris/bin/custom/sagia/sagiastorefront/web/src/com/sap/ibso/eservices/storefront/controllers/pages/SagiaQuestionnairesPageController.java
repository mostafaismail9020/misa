/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.SagiaSurveyFacade;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyResultData;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.controllers.pages
 * @link http://sap.com/
 * @copyright 2018 SAP
 */

@Controller
@RequestMapping("/my-sagia/questionnaires")
public class SagiaQuestionnairesPageController extends SagiaAbstractPageController {
	private static final String SINGLE_QUESTIONNAIRE_CMS_PAGE = "single-questionnaire";

	@Resource(name = "sagiaSurveyFacade")
	private SagiaSurveyFacade sagiaSurveyFacade;

	@RequestMapping(value = "/{surveyId}/{surveyVersion}", method = RequestMethod.GET)
	@RequireHardLogIn
	@LicenseRequired
	public String readZui5Surveys(@PathVariable String surveyId, @PathVariable String surveyVersion, final Model model)
			throws CMSItemNotFoundException {
		model.addAttribute("surveyData", sagiaSurveyFacade.getZui5SurveyBy(surveyId, surveyVersion));
		model.addAttribute("surveyVersion", surveyVersion);
		storeCmsPageInModel(model, getContentPageForLabelOrId(SINGLE_QUESTIONNAIRE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SINGLE_QUESTIONNAIRE_CMS_PAGE));
		return getViewForPage(model);
	}

	@RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
	@RequireHardLogIn
	@LicenseRequired
	public String readSurveysFromNotificationService(@PathVariable String transactionId, final Model model)
			throws CMSItemNotFoundException {
		model.addAttribute("surveyData", sagiaSurveyFacade.getNotificationSurveyBy(transactionId));
		storeCmsPageInModel(model, getContentPageForLabelOrId(SINGLE_QUESTIONNAIRE_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SINGLE_QUESTIONNAIRE_CMS_PAGE));
		return getViewForPage(model);
	}

	@RequestMapping(value = "/send-survey-data", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@RequireHardLogIn
	public void sendSurveyData(@RequestBody SurveyResultData data) throws IOException {
		sagiaSurveyFacade.validateSurveyResults(data);
		sagiaSurveyFacade.sendSurveyResults(data);
	}
}