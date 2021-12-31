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
package com.sap.ibso.eservices.core.sagia.job;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.apache.commons.collections.CollectionUtils;

import com.sap.ibso.eservices.core.model.CleanUpDraftCronJobModel;
import com.sap.ibso.eservices.core.model.SagiaDraftModel;
import com.sap.ibso.eservices.core.model.SagiaJsonDraftModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaDraftService;

import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.job
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class CleanUpDraftJob extends AbstractJobPerformable<CleanUpDraftCronJobModel>
{
	private SagiaDraftService sagiaDraftService;
	private MediaService mediaService;
	private ModelService modelService;//NOSONAR

	private static final Logger LOG = Logger.getLogger(CleanUpDraftJob.class);

	@Override
	public PerformResult perform(final CleanUpDraftCronJobModel cleanUpDraftCronJob)
	{
		LOG.info("Start CleanUpDraftJob");

		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -3);
		final Date date = cal.getTime();
		final List<SagiaDraftModel> drafts = sagiaDraftService.getDrafts(date);

		if (!CollectionUtils.isEmpty(drafts)) {
			drafts.forEach(draft -> {
				if (!CollectionUtils.isEmpty(draft.getDraftFiles())) {
					draft.getDraftFiles().forEach(draftFile -> {
						final String draftFileMediaCode = draftFile.getFileCode();
						removeDraft(draftFileMediaCode);
					});
				}
			});
			modelService.removeAll(drafts);
		}

		final List<SagiaJsonDraftModel> jsonDraftModels = sagiaDraftService.getJsonDrafts();
		if (!CollectionUtils.isEmpty(jsonDraftModels)) {
			jsonDraftModels.forEach(jsonDraft -> {
				final String mediaId = jsonDraft.getJsonMediaId();
				removeDraft(mediaId);
			});
			modelService.removeAll(jsonDraftModels);
		}

		LOG.info("Finish CleanUpDraftJob");
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	private void removeDraft(String mediaId) {
		try {
			final CatalogUnawareMediaModel media = (CatalogUnawareMediaModel) mediaService.getMedia(mediaId);
			modelService.remove(media);
			LOG.info("Media of draft with code: " + mediaId + " successfully deleted");
		}
		catch (UnknownIdentifierException | AmbiguousIdentifierException ex)
		{
			LOG.error("Error performing job CleanUpDraftJob with deleting media for code: " + mediaId);
			LOG.error(ex);
		}
	}

	public SagiaDraftService getSagiaDraftService()
	{
		return sagiaDraftService;
	}

	public void setSagiaDraftService(final SagiaDraftService sagiaDraftService)
	{
		this.sagiaDraftService = sagiaDraftService;
	}

	public MediaService getMediaService()
	{
		return mediaService;
	}

	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
