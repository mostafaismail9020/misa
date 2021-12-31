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
package com.sap.ibso.eservices.facades.populators.draft;

import com.sap.ibso.eservices.core.model.SagiaJsonUtilityModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.draft.DraftInfo;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.media.MediaService;

import java.nio.charset.StandardCharsets;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.populators.draft
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DraftJsonUtilitiesPopulator implements Populator<SagiaJsonUtilityModel, DraftInfo>
{
	private MediaService        mediaService;
	private SagiaFormatProvider sagiaFormatProvider;

	@Override
	public void populate(final SagiaJsonUtilityModel source, final DraftInfo target) {
		target.setServiceId(source.getServiceId());
		target.setCreationDate(sagiaFormatProvider.getLocalizedDateData(source.getCreationDate()));

		final CatalogUnawareMediaModel media = (CatalogUnawareMediaModel) mediaService.getMedia(source.getJsonMediaId());
		final byte[] byteData = mediaService.getDataFromMedia(media);
		final String data = new String(byteData, StandardCharsets.UTF_8);
		target.setData(data);
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(final MediaService mediaService) {
		this.mediaService = mediaService;
	}

	public SagiaFormatProvider getSagiaFormatProvider() {
		return sagiaFormatProvider;
	}

	public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
		this.sagiaFormatProvider = sagiaFormatProvider;
	}
}