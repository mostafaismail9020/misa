package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.WasselCheckData;
import com.sap.ibso.eservices.sagiaservices.data.SagiaGovDocWasselCheck;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class WasselCheckPopulator implements Populator<SagiaGovDocWasselCheck,WasselCheckData> {
    @Override
    public void populate(SagiaGovDocWasselCheck sagiaGovDocWasselCheck, WasselCheckData wasselCheckData) throws ConversionException {
        wasselCheckData.setCrNumber(sagiaGovDocWasselCheck.getCRNUMBER());
        wasselCheckData.setMessage(sagiaGovDocWasselCheck.getMESSAGE());
        wasselCheckData.setSuccess(sagiaGovDocWasselCheck.getSUCCESS());
    }
}
