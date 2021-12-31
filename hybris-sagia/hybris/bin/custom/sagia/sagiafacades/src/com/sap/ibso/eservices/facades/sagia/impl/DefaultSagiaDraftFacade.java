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
package com.sap.ibso.eservices.facades.sagia.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import com.sap.ibso.eservices.core.model.SagiaDraftModel;
import com.sap.ibso.eservices.core.model.SagiaJsonDraftModel;
import com.sap.ibso.eservices.core.model.SagiaJsonUtilityModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaDraftService;
import com.sap.ibso.eservices.core.sagia.services.SagiaServiceTypeCategoryService;
import com.sap.ibso.eservices.facades.data.draft.DraftInfo;
import com.sap.ibso.eservices.facades.populators.draft.DraftJsonInfoPopulator;
import com.sap.ibso.eservices.facades.populators.draft.DraftInfoPopulator;
import com.sap.ibso.eservices.facades.populators.draft.DraftJsonUtilitiesPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.sagiaservices.data.DraftData;
import com.sap.ibso.eservices.sagiaservices.data.DraftJsonData;

/**
 * DefaultSagiaDraftFacade
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.sagia.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaDraftFacade implements SagiaDraftFacade
{
	private SagiaDraftService               sagiaDraftService;
	private DraftInfoPopulator              draftInfoPopulator;
	private DraftJsonInfoPopulator          draftJsonInfoPopulator;
	private DraftJsonUtilitiesPopulator     draftJsonUtilitiesPopulator;
	private SagiaServiceTypeCategoryService sagiaServiceTypeCategoryService;

	@Override
	public void save(final DraftData draftData, final String serviceId)
	{
		sagiaDraftService.save(draftData, serviceId);
	}

	@Override
	public void saveJson(final DraftJsonData draftJsonData)
	{
		sagiaDraftService.saveJsonDraft(draftJsonData);
	}

	@Override
	public DraftInfo getDraft(final String formId)
	{
		final SagiaDraftModel draftModel = sagiaDraftService.getDraft(formId);
		final DraftInfo result = new DraftInfo();
		if (draftModel != null)
		{
			draftInfoPopulator.populate(draftModel, result);
			return result;
		}

		return null;
	}

	@Override
	public void saveUtilityDraft(final DraftJsonData draftJsonData)
	{
		sagiaDraftService.saveUtilityDraft(draftJsonData);
	}

	@Override
	public DraftInfo getDraftJson(final String serviceId)
	{
		final SagiaJsonDraftModel draftModel = sagiaDraftService.getJsonDraft(serviceId);
		if (draftModel != null) {
			final DraftInfo result  = new DraftInfo();
			draftJsonInfoPopulator.populate(draftModel, result);
			return result;
		}

		return null;
	}

	@Override
	public DraftInfo getJsonUtilityDraft(final String serviceId)
	{
		final SagiaJsonUtilityModel draftModel = sagiaDraftService.getJsonUtilityDraft(serviceId);
		if (draftModel != null) {
			final DraftInfo result  = new DraftInfo();
			draftJsonUtilitiesPopulator.populate(draftModel, result);
			return result;
		}

		return null;
	}

	@Override
	public List<DraftInfo> getDrafts()
	{
		final List<SagiaDraftModel> drafts = sagiaDraftService.getDrafts();
		final List<DraftInfo> result = new ArrayList<>();
		if (!CollectionUtils.isEmpty(drafts)) {
			drafts.forEach(draft -> {
				if (Strings.isNotEmpty(draft.getFormId())) {
					final DraftInfo draftInfo = new DraftInfo();
					draftInfoPopulator.populate(draft, draftInfo);
					result.add(draftInfo);
				}
			});
		}

		final List<SagiaJsonDraftModel> jsonDrafts = sagiaDraftService.getJsonDrafts();
		if (!CollectionUtils.isEmpty(jsonDrafts)) {
			jsonDrafts.forEach(draft -> {
				if (Strings.isNotEmpty(draft.getServiceId())) {
					final DraftInfo draftInfo = new DraftInfo();
					draftJsonInfoPopulator.populate(draft, draftInfo);
					result.add(draftInfo);
				}
			});
		}

		return result;
	}

	@Override
	public boolean isJsonDraftExists(final String serviceId)
	{
		return sagiaDraftService.isJsonDraftExists(serviceId);
	}

	@Override
	public void removeJsonDraft(final String serviceId)
	{
		sagiaDraftService.removeJsonDraft(serviceId);
	}

	@Override
	public boolean isDraftExists(final String formId)
	{
		return sagiaDraftService.isDraftExists(formId);
	}

	@Override
	public void removeDraft(final String formId)
	{
		sagiaDraftService.removeDraft(formId);
	}

	public SagiaDraftService getSagiaDraftService()
	{
		return sagiaDraftService;
	}

	public void setSagiaDraftService(final SagiaDraftService sagiaDraftService)
	{
		this.sagiaDraftService = sagiaDraftService;
	}

	public DraftInfoPopulator getDraftInfoPopulator()
	{
		return draftInfoPopulator;
	}

	public void setDraftInfoPopulator(final DraftInfoPopulator draftInfoPopulator)
	{
		this.draftInfoPopulator = draftInfoPopulator;
	}

	public DraftJsonInfoPopulator getDraftJsonInfoPopulator()
	{
		return draftJsonInfoPopulator;
	}

	public void setDraftJsonInfoPopulator(final DraftJsonInfoPopulator draftJsonInfoPopulator)
	{
		this.draftJsonInfoPopulator = draftJsonInfoPopulator;
	}

	public SagiaServiceTypeCategoryService getSagiaServiceTypeCategoryService()	{
		return sagiaServiceTypeCategoryService;
	}

	public void setSagiaServiceTypeCategoryService(final SagiaServiceTypeCategoryService sagiaServiceTypeCategoryService)	{
		this.sagiaServiceTypeCategoryService = sagiaServiceTypeCategoryService;
	}

	public DraftJsonUtilitiesPopulator getDraftJsonUtilitiesPopulator()	{
		return draftJsonUtilitiesPopulator;
	}

	public void setDraftJsonUtilitiesPopulator(final DraftJsonUtilitiesPopulator draftJsonUtilitiesPopulator) 	{
		this.draftJsonUtilitiesPopulator = draftJsonUtilitiesPopulator;
	}
}
