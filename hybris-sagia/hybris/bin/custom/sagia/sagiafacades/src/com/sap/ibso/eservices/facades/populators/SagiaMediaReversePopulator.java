package com.sap.ibso.eservices.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SagiaMediaReversePopulator implements Populator<MediaData, MediaModel> {

	@Override
	public void populate(MediaData source, MediaModel target) throws ConversionException {
        target.setURL(source.getUrl());
        //target.setDownloadUrl(source.getDownloadUrl());
        target.setCode(source.getCode());
    }
}
