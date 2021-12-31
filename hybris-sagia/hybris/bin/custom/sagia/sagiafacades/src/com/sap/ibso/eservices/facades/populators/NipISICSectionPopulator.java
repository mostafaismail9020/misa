package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.NipISICSectionSet;
import com.sap.ibso.eservices.sagiaservices.data.nip.NIPISICSectionSetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NipISICSectionPopulator implements Populator<NIPISICSectionSetData, NipISICSectionSet> {
    @Override
    public void populate(NIPISICSectionSetData nipisicSectionSetData, NipISICSectionSet nipISICSectionSet) throws ConversionException {
        nipISICSectionSet.setLanguage(nipisicSectionSetData.getLANGUAGE());
		nipISICSectionSet.setCode(nipisicSectionSetData.getISIC_SECTION());
		nipISICSectionSet.setName(nipisicSectionSetData.getISIC_SECTION_TEXT());
		nipISICSectionSet.setSuccess(nipisicSectionSetData.getSUCCESS());
		nipISICSectionSet.setError(nipisicSectionSetData.getERROR());
    }
}
