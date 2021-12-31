package com.sap.ibso.eservices.facades.populators;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class MediaPopulator implements Populator<MediaModel, MediaData> {
    @Override
    public void populate(MediaModel source, MediaData target) throws ConversionException {
        target.setUrl(source.getURL());
        target.setDownloadUrl(source.getDownloadURL());
        target.setCode(source.getCode());
        if (source.getRealFileName() != null) {
            target.setFileName(source.getRealFileName());
        }
    }
}
