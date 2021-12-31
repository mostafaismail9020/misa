/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.storefront.controllers.pages;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sap.ibso.eservices.facades.data.draft.DraftInfo;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.sagiaservices.data.DraftData;
import com.sap.ibso.eservices.sagiaservices.data.DraftParameter;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.sap.ibso.eservices.sagiaservices.data.DraftJsonData;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.controllers.pages
 * @link http://sap.com/
 * @copyright 2018 SAP
 */

@Controller
@RequestMapping("/draft")
@RequireHardLogIn
@ResponseBody
public class SagiaDraftController {

	private static final String PARAMETERS = "parameters";
	@Autowired
	private SagiaDraftFacade sagiaDraftFacade;

	@RequestMapping(method = RequestMethod.GET)
	public DraftInfo getDraftData(
					@RequestParam(required = false, value = "formId") String formId) throws IOException{
		return sagiaDraftFacade.getDraft(formId);
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public DraftInfo getDraftJsonData(
					@RequestParam(required = false, value = "serviceId") String serviceId) throws IOException{
		return sagiaDraftFacade.getDraftJson(serviceId);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public void saveDraftMultipartData(
					@RequestParam(value = "files", required = false) List<MultipartFile> files,
					@RequestParam(value = "id") String id,
					@RequestParam(value = "paramsCount", defaultValue = "0", required = false) Integer paramsCount,
					@RequestParam(value = "attachments", required = false) List<String> attachments,
					@RequestParam(value = "attachmentsNames", required = false) List<String> attachmentsNames,
					@RequestParam(value = "draftFiles", required = false) List<String> draftFiles,
					@RequestParam(value = "url", required = false) String url,
					MultipartHttpServletRequest request, HttpServletResponse response
	) {

		DraftData draftData = new DraftData();
		draftData.setUrl(url);

		List<DraftParameter> parameters = new ArrayList<>();
		for (int i = 0; i < paramsCount; i++)
		{
			DraftParameter parameter = new DraftParameter();
			parameter.setName(request.getParameter(PARAMETERS + "[" + i + "][name]"));
			parameter.setValue(request.getParameter(PARAMETERS + "[" + i + "][value]"));
			parameter.setType(request.getParameter(PARAMETERS + "[" + i + "][type]"));

			if (request.getParameter(PARAMETERS + "[" + i + "][fileName]") != null) {
				parameter.setFileName(request.getParameter(PARAMETERS + "[" + i + "][fileName]"));
			}

			parameters.add(parameter);
		}
		draftData.setParameters(parameters);
		draftData.setFiles(files);
		draftData.setDraftFiles(draftFiles);
		draftData.setAttachmentsNames(attachments);
		draftData.setAttachmentsInputNames(attachmentsNames);

		sagiaDraftFacade.save(draftData, id);
	}

	@RequestMapping(value = "/save-json", method = RequestMethod.POST)
	public void saveDraftJson(@RequestBody DraftJsonData draftJson) {
		sagiaDraftFacade.saveJson(draftJson);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public void deleteDraft(@RequestParam String serviceType) {
		sagiaDraftFacade.removeDraft(serviceType);
	}

	@RequestMapping(value = "/remove-json", method = RequestMethod.DELETE)
	public void deleteJsonDraft(@RequestParam String serviceType) {
		sagiaDraftFacade.removeJsonDraft(serviceType);
	}

}
