package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.SaudiArabiaInternationalIndicesModel;
import com.sap.ibso.eservices.facades.data.SAInternationalIndicesData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SAInternationalIndicesPopulator implements Populator<SaudiArabiaInternationalIndicesModel, SAInternationalIndicesData> {
    @Override
    public void populate(SaudiArabiaInternationalIndicesModel source, SAInternationalIndicesData target) throws ConversionException {
        target.setFrequency(source.getFrequency());
        target.setRankValue(source.getRankValue());
        target.setScoreValue(source.getScoreValue());
        target.setYear(source.getYear());
        target.setUid(source.getUid());
    }
}
