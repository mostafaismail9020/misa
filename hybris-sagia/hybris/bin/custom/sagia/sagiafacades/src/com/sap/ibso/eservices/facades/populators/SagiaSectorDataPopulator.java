package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaSectorModel;
import com.sap.ibso.eservices.facades.data.SagiaSectorData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaSectorDataPopulator implements Populator<SagiaSectorModel, SagiaSectorData> {

    @Override
    public void populate(SagiaSectorModel source, SagiaSectorData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setSectorCode(source.getSectorCode());
        target.setSectorName(source.getSectorName());
        target.setSectorDetails(source.getSectorDetails());
    }
}
