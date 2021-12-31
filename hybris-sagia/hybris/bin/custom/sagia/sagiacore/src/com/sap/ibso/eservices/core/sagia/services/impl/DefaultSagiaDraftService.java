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
package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaDraftFileModel;
import com.sap.ibso.eservices.core.model.SagiaDraftModel;
import com.sap.ibso.eservices.core.model.SagiaDraftParameterModel;
import com.sap.ibso.eservices.core.model.SagiaJsonDraftModel;
import com.sap.ibso.eservices.core.model.SagiaJsonUtilityModel;
import com.sap.ibso.eservices.core.model.ServiceTypeCategoryModel;
import com.sap.ibso.eservices.core.model.ServiceTypeModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaDraftDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaJsonDraftDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaJsonUtilityDAO;
import com.sap.ibso.eservices.core.sagia.exception.SagiaNotUniqueException;
import com.sap.ibso.eservices.core.sagia.services.SagiaDraftService;
import com.sap.ibso.eservices.core.sagia.services.SagiaServiceTypeCategoryService;
import com.sap.ibso.eservices.core.sagia.services.SagiaServiceTypeService;
import com.sap.ibso.eservices.sagiaservices.data.DraftData;
import com.sap.ibso.eservices.sagiaservices.data.DraftJsonData;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.core.model.order.AbstractDraftModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.fest.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Default implementation of DraftService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaDraftService implements SagiaDraftService
{

	private static final Logger LOG = Logger.getLogger(DefaultSagiaDraftService.class);
	private static final String ERROR_MORE_THAT_ONE_DRAFT_FOR_PAIR_USER_FORMID          = "Error: more that one draft for pair user-formid";
	private static final String ERROR_MORE_THAT_ONE_UTILITY_DRAFT_FOR_PAIR_USER_FORMID  = "Error: more that one utility draft for pair user-formid";

	private transient SagiaDraftDAO                   sagiaDraftDAO;
	private transient SagiaJsonDraftDAO               sagiaJsonDraftDAO;
	private transient SagiaJsonUtilityDAO             sagiaJsonUtilityDAO;
	private           ModelService                    modelService;
	private           MediaService                    mediaService;
	private           UserService                     userService;
	private           SagiaServiceTypeCategoryService sagiaServiceTypeCategoryService;
	private           SagiaServiceTypeService         sagiaServiceTypeService;


	@Override
	public void save(final DraftData draftData, final String serviceId)
	{
		final UserModel currentUser = userService.getCurrentUser();
		final String userId = currentUser.getPk().getLong().toString();
		List<SagiaDraftModel> drafts = sagiaDraftDAO.getDraft(userId, serviceId);

		if (drafts.size() > 1) {
			throw new SagiaNotUniqueException(ERROR_MORE_THAT_ONE_DRAFT_FOR_PAIR_USER_FORMID);
		}

		final SagiaDraftModel draftModel;
		if (CollectionUtils.isEmpty(drafts)) {
			draftModel = modelService.create(SagiaDraftModel.class);
			final Long maxTemporaryCode = sagiaDraftDAO.getMaxTemporaryCode();
			draftModel.setTemporaryId(maxTemporaryCode + 1);
		}
		else {
			draftModel = drafts.get(0);
		}

		final List<SagiaDraftParameterModel> parameterModels = new ArrayList<>();
		if (!CollectionUtils.isEmpty(draftModel.getParameters()))
		{
			draftModel.getParameters().forEach(parameterModel ->
					modelService.remove(parameterModel)
			);
		}
		draftData.getParameters().forEach(parameter -> {
			final SagiaDraftParameterModel parameterModel = modelService.create(SagiaDraftParameterModel.class);
			parameterModel.setName(parameter.getName());
			parameterModel.setType(parameter.getType());
			parameterModel.setValue(parameter.getValue());

			if (!Strings.isEmpty(parameter.getFileName())) {
				parameterModel.setFileName(parameter.getFileName());
			}
			parameterModels.add(parameterModel);
		});

		final List<SagiaDraftFileModel> draftFileModels = getDraftFileModels(draftData);

		if (!CollectionUtils.isEmpty(draftData.getDraftFiles()) && !CollectionUtils.isEmpty(draftModel.getDraftFiles())) {
			final List<SagiaDraftFileModel> existingFileModels = draftModel.getDraftFiles();
			existingFileModels.forEach(savedDraftFile -> {
				if (draftData.getDraftFiles().contains(savedDraftFile.getFileCode())) {
					draftFileModels.add(savedDraftFile);
				}
				else {
					final CatalogUnawareMediaModel media = (CatalogUnawareMediaModel) mediaService.getMedia(savedDraftFile.getFileCode());
					modelService.remove(savedDraftFile);
					modelService.remove(media);
				}
			});
		}

		draftModel.setFormId(serviceId);
		draftModel.setUserId(userId);
		draftModel.setParameters(parameterModels);
		draftModel.setDraftFiles(draftFileModels);
		draftModel.setCreationDate(new Date());
		draftModel.setUrl(draftData.getUrl());


		fillCategories(serviceId, draftModel);
		modelService.save(draftModel);
	}

	private List<SagiaDraftFileModel> getDraftFileModels(DraftData draftData) {
		final List<SagiaDraftFileModel> draftFileModels = new ArrayList<>();
		if (CollectionUtils.isEmpty(draftData.getFiles())) {
			return draftFileModels;
		}
		for (int i = 0; i < draftData.getFiles().size(); i++) {
			final MultipartFile uploadedFile = draftData.getFiles().get(i);
			try {
				final SagiaDraftFileModel draftFileModel = modelService.create(SagiaDraftFileModel.class);
				final String fileCode = UUID.randomUUID().toString().replace("-", "");
				final CatalogUnawareMediaModel model = modelService.create(CatalogUnawareMediaModel.class);
				model.setCode(fileCode);
				model.setRealFileName(uploadedFile.getOriginalFilename());
				model.setMime(uploadedFile.getContentType());

				modelService.save(model);
				mediaService.setDataForMedia(model, uploadedFile.getBytes());

				draftFileModel.setFileCode(fileCode);
				if (!CollectionUtils.isEmpty(draftData.getAttachmentsInputNames())) {
					draftFileModel.setAttachmentInputName(draftData.getAttachmentsInputNames().get(i));
				}
				if (!CollectionUtils.isEmpty(draftData.getAttachmentsNames())) {
					draftFileModel.setAttachmentName(draftData.getAttachmentsNames().get(i));
				}

				draftFileModels.add(draftFileModel);
			} catch (IOException ex) {
				LOG.error("Cant upload draft file");
				LOG.error(ex.getMessage(), ex);
			}
		}

		return draftFileModels;
	}


	@Override
	public SagiaDraftModel getDraft(final String formId)
	{
		final UserModel currentUser = userService.getCurrentUser();
		List<SagiaDraftModel> drafts = sagiaDraftDAO.getDraft(currentUser.getPk().getLong().toString(), formId);

		if (drafts.size() > 1) {
			throw new SagiaNotUniqueException(ERROR_MORE_THAT_ONE_DRAFT_FOR_PAIR_USER_FORMID);
		}

		if (drafts.isEmpty()) {
			return null;
		}
		else {
			return drafts.get(0);
		}
	}

	@Override
	public List<SagiaDraftModel> getDrafts()
	{
		final UserModel currentUser = userService.getCurrentUser();
		return sagiaDraftDAO.getDrafts(currentUser.getPk().getLong().toString());
	}

	@Override
	public List<SagiaDraftModel> getDrafts(final Date date)
	{
		return sagiaDraftDAO.getDrafts(date);
	}

	@Override
	public boolean isDraftExists(final String formId)
	{
		final UserModel currentUser = userService.getCurrentUser();
		List<SagiaDraftModel> drafts = sagiaDraftDAO.getDraft(currentUser.getPk().getLong().toString(), formId);

		if (drafts.size() > 1) {
			throw new SagiaNotUniqueException(ERROR_MORE_THAT_ONE_DRAFT_FOR_PAIR_USER_FORMID);
		}

		return !drafts.isEmpty();
	}

	@Override
	public void removeDraft(final String formId)
	{
		final UserModel currentUser = userService.getCurrentUser();
		List<SagiaDraftModel> drafts = sagiaDraftDAO.getDraft(currentUser.getPk().getLong().toString(), formId);

		if (CollectionUtils.isNotEmpty(drafts)) {
			modelService.removeAll(drafts);
		}
	}

	@Override
	public void saveJsonDraft(final DraftJsonData draftJsonData)
	{
		final UserModel currentUser = userService.getCurrentUser();
		final String userId = currentUser.getPk().getLong().toString();
		List<SagiaJsonDraftModel> drafts = sagiaJsonDraftDAO.getJsonDraft(userId, draftJsonData.getServiceId());

		if (drafts.size() > 1) {
			throw new SagiaNotUniqueException(ERROR_MORE_THAT_ONE_DRAFT_FOR_PAIR_USER_FORMID);
		}

		final SagiaJsonDraftModel draftJsonModel;
		if (drafts.isEmpty()) {
			draftJsonModel = modelService.create(SagiaJsonDraftModel.class);
			draftJsonModel.setServiceId(draftJsonData.getServiceId());
			draftJsonModel.setUserId(userId);

			final Long maxTemporaryCode = sagiaDraftDAO.getMaxTemporaryCode();
			draftJsonModel.setTemporaryId(maxTemporaryCode + 1);
		}
		else {
			draftJsonModel = drafts.get(0);
		}

		final String fileCode = UUID.randomUUID().toString().replace("-", "");

		draftJsonModel.setJsonMediaId(fileCode);
		draftJsonModel.setCreationDate(new Date());
		draftJsonModel.setUrl(draftJsonData.getUrl());
		final CatalogUnawareMediaModel model = modelService.create(CatalogUnawareMediaModel.class);
		model.setCode(fileCode);
		model.setRealFileName("draftdata_" + userId + "_" + fileCode +".txt");
		model.setMime("text/plain");

		fillCategories(draftJsonData.getServiceId(), draftJsonModel);
		modelService.save(model);
		mediaService.setDataForMedia(model, draftJsonData.getJson().getBytes(StandardCharsets.UTF_8));

		modelService.save(draftJsonModel);
	}

	@Override
	public void saveUtilityDraft(final DraftJsonData draftJsonData)
	{
		final UserModel currentUser = userService.getCurrentUser();
		final String userId = currentUser.getPk().getLong().toString();
		List<SagiaJsonUtilityModel> drafts = sagiaJsonUtilityDAO.getJsonUtility(userId, draftJsonData.getServiceId());

		if (drafts.size() > 1) {
			throw new SagiaNotUniqueException(ERROR_MORE_THAT_ONE_DRAFT_FOR_PAIR_USER_FORMID);
		}

		final SagiaJsonUtilityModel jsonUtilityModel;
		if (drafts.isEmpty()) {
			jsonUtilityModel = modelService.create(SagiaJsonUtilityModel.class);
			jsonUtilityModel.setServiceId(draftJsonData.getServiceId());
			jsonUtilityModel.setUserId(userId);
		}
		else {
			jsonUtilityModel = drafts.get(0);
		}

		final String fileCode = UUID.randomUUID().toString().replace("-", "");

		jsonUtilityModel.setJsonMediaId(fileCode);
		jsonUtilityModel.setCreationDate(new Date());
		final CatalogUnawareMediaModel model = modelService.create(CatalogUnawareMediaModel.class);
		model.setCode(fileCode);
		model.setRealFileName("draftdata_" + userId + "_" + fileCode +".txt");
		model.setMime("text/plain");

		modelService.save(model);
		mediaService.setDataForMedia(model, draftJsonData.getJson().getBytes(StandardCharsets.UTF_8));

		modelService.save(jsonUtilityModel);
	}

	@Override
	public SagiaJsonDraftModel getJsonDraft(final String serviceId)
	{
		final UserModel currentUser = userService.getCurrentUser();
		List<SagiaJsonDraftModel> drafts = sagiaJsonDraftDAO.getJsonDraft(currentUser.getPk().getLong().toString(), serviceId);

		if (drafts.size() > 1) {
			throw new SagiaNotUniqueException(ERROR_MORE_THAT_ONE_DRAFT_FOR_PAIR_USER_FORMID);
		}

		if (drafts.isEmpty()) {
			return null;
		}
		else {
			return drafts.get(0);
		}
	}

	@Override
	public SagiaJsonUtilityModel getJsonUtilityDraft(final String serviceId)
	{
		final UserModel currentUser = userService.getCurrentUser();
		List<SagiaJsonUtilityModel> drafts = sagiaJsonUtilityDAO.getJsonUtility(currentUser.getPk().getLong().toString(), serviceId);

		if (drafts.size() > 1) {
			throw new SagiaNotUniqueException(ERROR_MORE_THAT_ONE_UTILITY_DRAFT_FOR_PAIR_USER_FORMID);
		}

		if (drafts.isEmpty()) {
			return null;
		}
		else {
			return drafts.get(0);
		}
	}

	@Override
	public List<SagiaJsonDraftModel> getJsonDrafts()
	{
		final UserModel currentUser = userService.getCurrentUser();
		return sagiaJsonDraftDAO.getJsonDrafts(currentUser.getPk().getLong().toString());
	}

	@Override
	public List<SagiaJsonDraftModel> getJsonDrafts(final Date date)
	{
		return sagiaJsonDraftDAO.getJsonDrafts(date);
	}

	@Override
	public boolean isJsonDraftExists(final String serviceId)
	{
		final UserModel currentUser = userService.getCurrentUser();
		List<SagiaJsonDraftModel> drafts = sagiaJsonDraftDAO.getJsonDraft(currentUser.getPk().getLong().toString(), serviceId);

		if (drafts.size() > 1) {
			throw new SagiaNotUniqueException(ERROR_MORE_THAT_ONE_DRAFT_FOR_PAIR_USER_FORMID);
		}

		return !drafts.isEmpty();
	}

	@Override
	public void removeJsonDraft(final String serviceId)
	{
		final UserModel currentUser = userService.getCurrentUser();
		List<SagiaJsonDraftModel> drafts = sagiaJsonDraftDAO.getJsonDraft(currentUser.getPk().getLong().toString(), serviceId);

		if (!drafts.isEmpty())		{
			modelService.removeAll(drafts);
		}
	}

	private void fillCategories(final String serviceId, final AbstractDraftModel draftModel)
	{
		if (Strings.isEmpty(serviceId)) {
			return;
		}

		final Map<String, ServiceTypeCategoryModel> services = sagiaServiceTypeCategoryService.getServiceTypeCategoriesByCodes();
		if (services.containsKey(serviceId)) {
			final ServiceTypeCategoryModel service = services.get(serviceId);
			draftModel.setServiceCategory(service);
		}
		else {
			final Map<String, ServiceTypeModel> serviceTypes = sagiaServiceTypeService.getServiceTypesByCodes();
			if (serviceTypes.containsKey(serviceId)) {
				final ServiceTypeModel serviceType = serviceTypes.get(serviceId);
				draftModel.setServiceType(serviceType);
			}
		}
	}

	public SagiaDraftDAO getSagiaDraftDAO()
	{
		return sagiaDraftDAO;
	}

	public void setSagiaDraftDAO(final SagiaDraftDAO sagiaDraftDAO)
	{
		this.sagiaDraftDAO = sagiaDraftDAO;
	}

	public SagiaJsonDraftDAO getSagiaJsonDraftDAO()
	{
		return sagiaJsonDraftDAO;
	}

	public void setSagiaJsonDraftDAO(final SagiaJsonDraftDAO sagiaJsonDraftDAO)
	{
		this.sagiaJsonDraftDAO = sagiaJsonDraftDAO;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public MediaService getMediaService()
	{
		return mediaService;
	}

	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public SagiaServiceTypeCategoryService getSagiaServiceTypeCategoryService()	{
		return sagiaServiceTypeCategoryService;
	}

	public void setSagiaServiceTypeCategoryService(final SagiaServiceTypeCategoryService sagiaServiceTypeCategoryService)	{
		this.sagiaServiceTypeCategoryService = sagiaServiceTypeCategoryService;
	}

	public SagiaJsonUtilityDAO getSagiaJsonUtilityDAO()	{
		return sagiaJsonUtilityDAO;
	}

	public void setSagiaJsonUtilityDAO(final SagiaJsonUtilityDAO sagiaJsonUtilityDAO)	{
		this.sagiaJsonUtilityDAO = sagiaJsonUtilityDAO;
	}

	public SagiaServiceTypeService getSagiaServiceTypeService()	{
		return sagiaServiceTypeService;
	}

	public void setSagiaServiceTypeService(final SagiaServiceTypeService sagiaServiceTypeService)	{
		this.sagiaServiceTypeService = sagiaServiceTypeService;
	}
}
