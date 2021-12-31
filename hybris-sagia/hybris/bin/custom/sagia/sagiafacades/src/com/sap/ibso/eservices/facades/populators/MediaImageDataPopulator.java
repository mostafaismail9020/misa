package com.sap.ibso.eservices.facades.populators;

import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class MediaImageDataPopulator implements Populator<MediaModel, ImageData> {
    @Override
    public void populate(MediaModel source, ImageData target) throws ConversionException {
        if(source != null){
            target.setUrl(source.getURL());
        }
    }
}
