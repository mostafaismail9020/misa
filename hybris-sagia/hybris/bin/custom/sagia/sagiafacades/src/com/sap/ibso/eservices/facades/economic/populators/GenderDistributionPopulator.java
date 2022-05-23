package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.GenderDistributionModel;
import com.sap.ibso.eservices.facades.data.GenderDistributionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class GenderDistributionPopulator implements Populator<GenderDistributionModel, GenderDistributionData> {
    @Override
    public void populate(GenderDistributionModel source, GenderDistributionData target) throws ConversionException {
        target.setFemales(source.getFemales());
        target.setMales(source.getMales());
        target.setUid(source.getUid());
        target.setTotal(source.getTotal());
        target.setYear(source.getYear());
    }
}
