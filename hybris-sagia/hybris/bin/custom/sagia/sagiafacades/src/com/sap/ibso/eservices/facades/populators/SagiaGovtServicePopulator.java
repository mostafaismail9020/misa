package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;
import com.sap.ibso.eservices.facades.data.services.SagiaServiceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaGovtServicePopulator implements Populator<SagiaServiceModel, SagiaServiceData> {

    private SagiaCategoryDataPopulator sagiaCategoryDataPopulator;

    /**
     * Populate data from model object to data object.
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(SagiaServiceModel source, SagiaServiceData target) throws ConversionException {
        SagiaCategoryData sagiaCategoryData = new SagiaCategoryData();
        sagiaCategoryDataPopulator.populate(source.getCategory(), sagiaCategoryData);
        target.setCategory(sagiaCategoryData);

        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
        target.setName(source.getName());
        target.setUrl(source.getUrl());
    }

    /**
     * @param sagiaCategoryDataPopulator
     */
    public void setSagiaCategoryDataPopulator(SagiaCategoryDataPopulator sagiaCategoryDataPopulator) {
        this.sagiaCategoryDataPopulator = sagiaCategoryDataPopulator;
    }
}
