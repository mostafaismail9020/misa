package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.AmanahData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtDropdown;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class AmanahPopulator implements Populator<GovtDropdown,AmanahData>
{
    @Override
    public void populate(GovtDropdown govtDropdown, AmanahData amanahData) throws ConversionException
    {
        amanahData.setName(govtDropdown.getAmanah());
        amanahData.setChamber(govtDropdown.getChamber());
        amanahData.setChamberId(govtDropdown.getChamberId());
        amanahData.setKeyFromCrm(govtDropdown.getKey());
        amanahData.setKey(amanahData.getName().replaceAll("\\s+",""));
    }
}

