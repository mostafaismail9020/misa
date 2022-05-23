package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.PopulationDistributionModel;
import com.sap.ibso.eservices.facades.data.PopulationDistributionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class PopulationDistributionPopulator implements Populator<PopulationDistributionModel, PopulationDistributionData> {
    @Override
    public void populate(PopulationDistributionModel source, PopulationDistributionData target) throws ConversionException {
        target.setUid(source.getUid());
        target.setYear(source.getYear());
        target.setTotalPopulation(source.getTotalPopulation());
        target.setNonSaudi(source.getNonSaudi());
        target.setNonSaudiPercentage(source.getNonSaudiPercentage());
        target.setSaudi(source.getSaudi());
        target.setSaudiPercentage(source.getSaudiPercentage());
    }
}
