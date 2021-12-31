package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.DiffQeemah;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.DiffQeemahData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DiffQeemahReversePopulator implements Populator<DiffQeemah, DiffQeemahData> {
    @Override
    public void populate(DiffQeemah diffQeemah, DiffQeemahData diffQeemahData) throws ConversionException {
        diffQeemahData.setRefid(diffQeemah.getRefId());
        diffQeemahData.setQeemah(diffQeemah.getQeemah());
    }
}
