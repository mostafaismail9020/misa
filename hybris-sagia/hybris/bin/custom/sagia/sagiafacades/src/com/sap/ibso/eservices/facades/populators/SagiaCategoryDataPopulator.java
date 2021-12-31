package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaCategoryDataPopulator implements Populator<SagiaCategoryModel,SagiaCategoryData> {

    private Converter<MediaModel, MediaData> mediaConverter;

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

    /**
     * Populate data from model object to data object.
     * @param source the source object
     * @param target the target to fill
     */
    @Override
    public void populate(SagiaCategoryModel source, SagiaCategoryData target)  {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setCategoryUrl(source.getCategoryUrl());
        if(source.getIcon() != null){
            target.setIcon(getMediaConverter().convert(source.getIcon()));
        }
        if(source.getMenuIcon() != null){
            target.setMenuIcon(getMediaConverter().convert(source.getMenuIcon()));
        }
        if(source.getLabel() != null){
            target.setLabel(source.getLabel());
        }
    }
}
