package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.NipISICDivisionSet;
import com.sap.ibso.eservices.sagiaservices.data.nip.NIPISICDivisionSetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipISICDivisionPopulator implements Populator<NIPISICDivisionSetData, NipISICDivisionSet> {
    @Override
    public void populate(NIPISICDivisionSetData nipisicDivisionSetData, NipISICDivisionSet nipISICDivisionSet) throws ConversionException {
        nipISICDivisionSet.setLanguage(nipisicDivisionSetData.getLANGUAGE());
        nipISICDivisionSet.setSectionCode(nipisicDivisionSetData.getISIC_SECTION());
        nipISICDivisionSet.setCode(nipisicDivisionSetData.getISIC_DIVISION());
        nipISICDivisionSet.setName(nipisicDivisionSetData.getISIC_DIVISION_TEXT());
        nipISICDivisionSet.setSuccess(nipisicDivisionSetData.getSUCCESS());
        nipISICDivisionSet.setError(nipisicDivisionSetData.getERROR());
    }
}
