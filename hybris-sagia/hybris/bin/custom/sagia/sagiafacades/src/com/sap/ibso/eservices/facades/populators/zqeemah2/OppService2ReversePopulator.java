package com.sap.ibso.eservices.facades.populators.zqeemah2;


import com.sap.ibso.eservices.facades.data.zqeemah2.OppService2;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.OppService2Data;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class OppService2ReversePopulator implements Populator<OppService2, OppService2Data> {
    @Override
    public void populate(OppService2 oppService2, OppService2Data oppService2Data) throws ConversionException {
        oppService2Data.setOppid(oppService2.getOppId());
        oppService2Data.setZreturn(oppService2.getReturnProperty());
        oppService2Data.setRefid(oppService2.getRefId());
        oppService2Data.setGuid(oppService2.getGuid());
    }
}
