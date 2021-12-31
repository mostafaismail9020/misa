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

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.sap.ibso.eservices.core.model.CleanUpMediaCronJobModel;
import com.sap.ibso.eservices.core.model.SagiaUploadFilesDataModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaUploadFilesService;

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
public class CleanUpMediaFilesJob extends AbstractJobPerformable<CleanUpMediaCronJobModel>
{
	private static final Logger LOG = Logger.getLogger(CleanUpMediaFilesJob.class);

	private SagiaUploadFilesService sagiaUploadFilesService;
	private MediaService            mediaService;
	private ModelService            modelService;

	@Override
	public PerformResult perform(final CleanUpMediaCronJobModel cronJobModel)
	{
		LOG.info("Start CleanUpMediaFilesJob");

		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		final Date date = cal.getTime();

		final List<SagiaUploadFilesDataModel> uploadedFilesRecords = sagiaUploadFilesService.getUploadedFilesData(date);

		if (!CollectionUtils.isEmpty(uploadedFilesRecords)) {
			uploadedFilesRecords.forEach(record -> {
				try {
					final CatalogUnawareMediaModel media = (CatalogUnawareMediaModel) mediaService.getMedia(record.getFileCode());
					modelService.remove(media);
					LOG.info("Media with code: " + record.getFileCode() + " successfully deleted");
				}
				catch (UnknownIdentifierException | AmbiguousIdentifierException ex)
				{
					LOG.error("Error performing job CleanUpMediaFilesJob with deleting media for code: " + record.getFileCode());
					LOG.error(ex);
				}
			});

			modelService.removeAll(uploadedFilesRecords);
		}

		LOG.info("Finish CleanUpMediaFilesJob");
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public SagiaUploadFilesService getSagiaUploadFilesService()
	{
		return sagiaUploadFilesService;
	}

	public void setSagiaUploadFilesService(final SagiaUploadFilesService sagiaUploadFilesService)
	{
		this.sagiaUploadFilesService = sagiaUploadFilesService;
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
