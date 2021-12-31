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

import com.sap.ibso.eservices.core.model.SagiaUploadFilesDataModel;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.request.UploadFilesRemoveRequest;
import com.sap.ibso.eservices.storefront.response.UploadFileResponse;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;
import org.fest.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.controllers.pages
 * @link http://sap.com/
 * @copyright 2018 SAP
 */

@Controller
@RequestMapping("/file")
public class SagiaUploadFileController extends SagiaAbstractPageController
{

	private static final Logger LOG = Logger.getLogger(SagiaUploadFileController.class);

	@Resource(name = "mediaService")
	private MediaService mediaService;

	@Resource(name = "modelService")
	private ModelService modelService;

	@Resource(name = "sagiaCustomerFacade")
	private SagiaCustomerFacade sagiaCustomerFacade;

	@RequestMapping(value = "/send-data", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	@ResponseBody
	@RequireHardLogIn
	public UploadFileResponse sendData(
					@RequestParam(value = "file") MultipartFile file,
					@RequestParam(value = "code") String code,
					MultipartHttpServletRequest request) {

		if (file == null || file.isEmpty()) {
			request.setAttribute("message",
			                     "Please select a file to upload");
			return new UploadFileResponse(code, 1);
		}

		try {

			final String fileCode = UUID.randomUUID().toString().replace("-", "");
			final CatalogUnawareMediaModel model = modelService.create(CatalogUnawareMediaModel.class);
			model.setCode(fileCode);
			model.setRealFileName(file.getOriginalFilename());
			model.setMime(file.getContentType());

			modelService.save(model);
			mediaService.setDataForMedia(model, file.getBytes());

			/*For job execution - clean up this files after 3 days*/
			final SagiaUploadFilesDataModel uploadFilesDataModel = modelService.create(SagiaUploadFilesDataModel.class);
			uploadFilesDataModel.setCustomerId(sagiaCustomerFacade.getCurrentCustomer().getCustomerId());
			uploadFilesDataModel.setFileCode(fileCode);
			uploadFilesDataModel.setUploadTime(new Date());
			modelService.save(uploadFilesDataModel);

			return new UploadFileResponse(code, fileCode, file.getOriginalFilename(), 0);

		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
			return new UploadFileResponse(code, 1);
		}
	}

	@RequestMapping(value = "/remove-data", method = RequestMethod.DELETE)
	@ResponseBody
	@RequireHardLogIn
	public void removeData(@RequestBody UploadFilesRemoveRequest requestData)
	{
		if (!Strings.isEmpty(requestData.getCode())) {

			final CatalogUnawareMediaModel media = (CatalogUnawareMediaModel) mediaService.getMedia(requestData.getCode());
			modelService.remove(media);
		}

		if (!CollectionUtils.isEmpty(requestData.getCodes())) {
			requestData.getCodes().forEach(code -> {
				final CatalogUnawareMediaModel media = (CatalogUnawareMediaModel) mediaService.getMedia(requestData.getCode());
				modelService.remove(media);
			});
		}
	}

	public MediaService getMediaService()
	{
		return mediaService;
	}

	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}
}
