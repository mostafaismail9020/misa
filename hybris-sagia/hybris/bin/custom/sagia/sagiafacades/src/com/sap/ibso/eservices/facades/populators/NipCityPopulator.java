package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.NipCitySet;
import com.sap.ibso.eservices.sagiaservices.data.nip.NIPCitySetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipCityPopulator implements Populator<NIPCitySetData, NipCitySet> {
    @Override
    public void populate(NIPCitySetData nipLegalStatusSetData, NipCitySet nipLeagalStatusSet) throws ConversionException {
        nipLeagalStatusSet.setLanguage(nipLegalStatusSetData.getLANGUAGE());
        nipLeagalStatusSet.setCode(nipLegalStatusSetData.getCITY());
        nipLeagalStatusSet.setName(nipLegalStatusSetData.getCITY_TEXT());
        nipLeagalStatusSet.setCountryCode(nipLegalStatusSetData.getCOUNTRY());
        nipLeagalStatusSet.setRegionCode(nipLegalStatusSetData.getREGION());
        nipLeagalStatusSet.setSuccess(nipLegalStatusSetData.getSUCCESS());
        nipLeagalStatusSet.setError(nipLegalStatusSetData.getERROR());
    }
}
