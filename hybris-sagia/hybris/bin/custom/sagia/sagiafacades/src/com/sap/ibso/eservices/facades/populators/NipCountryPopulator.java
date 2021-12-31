package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.NIPCountrySet;
import com.sap.ibso.eservices.sagiaservices.data.nip.NIPCountrySetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipCountryPopulator implements Populator<NIPCountrySetData, NIPCountrySet> {
    @Override
    public void populate(NIPCountrySetData nipCountrySetData, NIPCountrySet nipCountry) throws ConversionException {
        nipCountry.setLanguage(nipCountrySetData.getLANGUAGE());
        nipCountry.setCode(nipCountrySetData.getCOUNTRY());
        nipCountry.setName(nipCountrySetData.getCOUNTRY_TEXT());
        nipCountry.setNationalityCategory(nipCountrySetData.getNATIONALITY_CAT());
        nipCountry.setNationalityText(nipCountrySetData.getNATIONALITY_TEXT());
        nipCountry.setMobileCode(nipCountrySetData.getMOBILE_CODE());
        nipCountry.setSuccess(nipCountrySetData.getSUCCESS());
        nipCountry.setError(nipCountrySetData.getERROR());
    }
}
