package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.CommercialRegisterModel;
import com.sap.ibso.eservices.facades.data.CommercialRegisterData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CommercialRegisterPopulator implements Populator<CommercialRegisterModel, CommercialRegisterData> {
    @Override
    public void populate(CommercialRegisterModel source, CommercialRegisterData target) throws ConversionException {
        target.setUid(source.getUid());
        target.setYear(source.getYear());
        target.setValue(source.getValue());
        target.setPercentage(source.getPercentage());
    }
}
