package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah.Telecode;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.TelecodeData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class TelecodePopulator implements Populator<TelecodeData, Telecode> {

    @Override
    public void populate(TelecodeData telecodeData, Telecode telecode) throws ConversionException {
        telecode.setCountryCode(telecodeData.getCountKey());
        telecode.setLvStatus(telecodeData.getLvStatus());
        telecode.setPrefix(telecodeData.getTelNo());
    }
}
