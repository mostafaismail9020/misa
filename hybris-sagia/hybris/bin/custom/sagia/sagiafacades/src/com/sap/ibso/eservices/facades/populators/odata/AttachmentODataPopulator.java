package com.sap.ibso.eservices.facades.populators.odata;


import javax.annotation.Resource;

import com.sap.ibso.eservices.sagiaservices.data.odata.ShareholderAttachmentData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.media.MediaService;

public class AttachmentODataPopulator implements Populator<MediaModel, ShareholderAttachmentData> {

	@Resource
	private MediaService mediaService;
    
	@Override
	public void populate(MediaModel source, ShareholderAttachmentData target) throws ConversionException {
		
		target.setFileMtype(source.getMime());
		target.setFileName(source.getRealFileName());
		target.setContent(mediaService.getStreamFromMedia(source));
	
	}

}
