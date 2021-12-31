package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaMediaModel;
import com.sap.ibso.eservices.facades.data.SagiaMediaData;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaMediaDataPopulator implements Populator<SagiaMediaModel, SagiaMediaData> {

    private Converter<MediaModel, MediaData> mediaConverter;

    @Override
    public void populate(SagiaMediaModel source, SagiaMediaData target) throws ConversionException {
        target.setName(source.getName());
        List<MediaData> mediaList = new ArrayList<>();
        if(source.getAttachments() != null) {
            for (MediaModel media: source.getAttachments()) {
                mediaList.add((MediaData)this.getMediaConverter().convert(media));
            }
            target.setAttachments(mediaList);
        }
    }

    /**
     * @return
     */
    public Converter<MediaModel, MediaData> getMediaConverter() {
        return mediaConverter;
    }

    /**
     * @param mediaConverter
     */
    public void setMediaConverter(Converter<MediaModel, MediaData> mediaConverter) {
        this.mediaConverter = mediaConverter;
    }
}
