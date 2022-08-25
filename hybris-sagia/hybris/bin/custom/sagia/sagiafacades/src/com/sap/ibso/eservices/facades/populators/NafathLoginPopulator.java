package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.NafathLoginModel;
import com.sap.ibso.eservices.facades.data.NafathLoginData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class NafathLoginPopulator implements Populator<NafathLoginModel, NafathLoginData> {
    @Override
    public void populate(NafathLoginModel source, NafathLoginData target) throws ConversionException {
        target.setNationalId(source.getNationalId());
        target.setTransactionId(source.getTransactionId());
        target.setRandom(source.getRandom());
        target.setStatus(source.getStatus());
    }
}
