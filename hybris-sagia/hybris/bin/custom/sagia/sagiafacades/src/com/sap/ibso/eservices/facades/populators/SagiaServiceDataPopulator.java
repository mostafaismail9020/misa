package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.facades.data.SagiaServiceData;
import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaServiceDataPopulator implements Populator<SagiaServiceModel,SagiaServiceData> {

    private Converter<SagiaServiceModel, SagiaServiceData> sagiaServiceConverter;
    private Converter<SagiaCategoryModel, SagiaCategoryData> sagiaCategoryConverter;

    /**
     * @return
     */
    public Converter<SagiaServiceModel, SagiaServiceData> getSagiaServiceConverter() {
        return sagiaServiceConverter;
    }

    /**
     * @param sagiaServiceConverter sagiaServiceConverter
     */
    public void setSagiaServiceConverter(Converter<SagiaServiceModel, SagiaServiceData> sagiaServiceConverter) {
        this.sagiaServiceConverter = sagiaServiceConverter;
    }

    /**
     * @param sagiaCategoryConverter sagiaCategoryConverter
     */
    public void setSagiaCategoryConverter(Converter<SagiaCategoryModel, SagiaCategoryData> sagiaCategoryConverter) {
        this.sagiaCategoryConverter = sagiaCategoryConverter;
    }

    @Override
    public void populate(SagiaServiceModel source, SagiaServiceData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setUrl(source.getUrl());
        target.setCategory(sagiaCategoryConverter.convert(source.getCategory()));

    }
}
