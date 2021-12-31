package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.NIPLeagalStatusSet;
import com.sap.ibso.eservices.sagiaservices.data.nip.NIPLegalStatusSetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipLegalStatusPopulator implements Populator<NIPLegalStatusSetData, NIPLeagalStatusSet> {
    @Override
    public void populate(NIPLegalStatusSetData nipLegalStatusSetData, NIPLeagalStatusSet nipLegalStatusSet) throws ConversionException {
        nipLegalStatusSet.setLanguage(nipLegalStatusSetData.getLANGUAGE());
        nipLegalStatusSet.setCode(nipLegalStatusSetData.getLEGAL_STATUS());
        nipLegalStatusSet.setName(nipLegalStatusSetData.getLEGAL_STATUS_TEXT());
        nipLegalStatusSet.setSuccess(nipLegalStatusSetData.getSUCCESS());
        nipLegalStatusSet.setError(nipLegalStatusSetData.getERROR());
    }
}
