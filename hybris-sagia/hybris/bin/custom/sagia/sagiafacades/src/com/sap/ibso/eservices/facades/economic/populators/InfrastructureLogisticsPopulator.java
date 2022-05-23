package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.InfrastructureLogisticsModel;
import com.sap.ibso.eservices.facades.data.InfrastructureLogisticsData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class InfrastructureLogisticsPopulator implements Populator<InfrastructureLogisticsModel, InfrastructureLogisticsData> {
    @Override
    public void populate(InfrastructureLogisticsModel source, InfrastructureLogisticsData target) throws ConversionException {
        target.setDisplayName(source.getDisplayName());
        target.setIndex(source.getIndex());
        target.setLabel1(source.getLabel1());
        target.setLabel2(source.getLabel2());
        target.setLabel3(source.getLabel3());
        target.setLabel4(source.getLabel4());
        target.setLabel5(source.getLabel5());
        target.setValue1(source.getValue1());
        target.setValue2(source.getValue2());
        target.setValue3(source.getValue3());
        target.setValue4(source.getValue4());
        target.setValue5(source.getValue5());
        target.setUid(source.getUid());
    }
}
