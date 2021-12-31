package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.NIPRegionSet;
import com.sap.ibso.eservices.sagiaservices.data.nip.NIPRegionSetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipRegionPopulator implements Populator<NIPRegionSetData, NIPRegionSet> {
    @Override
    public void populate(NIPRegionSetData nipLegalStatusSetData, NIPRegionSet nipLeagalStatusSet) throws ConversionException {
        nipLeagalStatusSet.setLanguage(nipLegalStatusSetData.getLANGUAGE());
        nipLeagalStatusSet.setCode(nipLegalStatusSetData.getREGION());
        nipLeagalStatusSet.setName(nipLegalStatusSetData.getREGION_TEXT());
        nipLeagalStatusSet.setCountryCode(nipLegalStatusSetData.getCOUNTRY());
        nipLeagalStatusSet.setSuccess(nipLegalStatusSetData.getSUCCESS());
        nipLeagalStatusSet.setError(nipLegalStatusSetData.getERROR());
    }
}
