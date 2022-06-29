package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.ForeignInvestmentModel;
import com.sap.ibso.eservices.facades.data.ForeignInvestmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ForeignInvestmentPopulator implements Populator<ForeignInvestmentModel, ForeignInvestmentData> {
    @Override
    public void populate(ForeignInvestmentModel source, ForeignInvestmentData target) throws ConversionException {
        target.setIndex(source.getIndex());
        target.setUid(source.getUid());
        target.setValue1(source.getValue1());
        target.setValue2(source.getValue2());
        target.setValue3(source.getValue3());
        target.setLabel1(source.getLabel1());
        target.setLabel2(source.getLabel2());
        target.setLabel3(source.getLabel3());
    }
}
