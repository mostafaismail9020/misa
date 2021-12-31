package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.core.model.SagiaConfigurationModel;
import com.sap.ibso.eservices.sagiaservices.data.SagiaConfigurationData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaConfigurationDataPopulator implements Populator<SagiaConfigurationModel, SagiaConfigurationData> {
    @Override
    public void populate(SagiaConfigurationModel source, SagiaConfigurationData target) throws ConversionException {
        target.setKey(source.getKey());
        target.setValue(source.getValue());
    }
}
