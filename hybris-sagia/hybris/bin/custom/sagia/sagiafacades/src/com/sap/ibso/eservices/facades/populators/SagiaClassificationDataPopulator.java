package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaClassificationModel;
import com.sap.ibso.eservices.facades.data.SagiaClassificationData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaClassificationDataPopulator implements Populator<SagiaClassificationModel, SagiaClassificationData> {
    /**
     * Populate from SagiaClassificationModel to SagiaClassificationData.
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException exception
     */

    @Override
    public void populate(SagiaClassificationModel source, SagiaClassificationData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setValue(source.getValue());
    }
}
