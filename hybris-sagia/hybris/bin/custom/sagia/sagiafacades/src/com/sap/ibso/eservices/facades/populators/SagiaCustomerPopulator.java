package com.sap.ibso.eservices.facades.populators;

import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaCustomerPopulator implements Populator<UserModel, CustomerData> {

    private Converter<MediaModel, ImageData> mediaImageConverter;

    /**
     * @return
     */
    public Converter<MediaModel, ImageData> getMediaImageConverter() {
        return mediaImageConverter;
    }

    /**
     * @param mediaImageConverter
     */
    public void setMediaImageConverter(Converter<MediaModel, ImageData> mediaImageConverter) {
        this.mediaImageConverter = mediaImageConverter;
    }

    @Override
    public void populate(UserModel userModel, CustomerData customerData) throws ConversionException {
        customerData.setProfilePicture(getMediaImageConverter().convert(userModel.getProfilePicture()));
    }
}
