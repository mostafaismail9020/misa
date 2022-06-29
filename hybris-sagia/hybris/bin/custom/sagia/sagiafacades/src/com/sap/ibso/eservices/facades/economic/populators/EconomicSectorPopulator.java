package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.EconomicsSectorModel;
import com.sap.ibso.eservices.facades.data.EconomicSectorData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class EconomicSectorPopulator implements Populator<EconomicsSectorModel, EconomicSectorData> {

    @Override
    public void populate(EconomicsSectorModel source, EconomicSectorData target) throws ConversionException {
               target.setDisplayName(source.getDisplayName());
               target.setIndex(source.getIndex());
               target.setUid(source.getUid());
               target.setValue(source.getValue());
               target.setYear(source.getYear());
               target.setLabel(source.getLabel());
    }

}
