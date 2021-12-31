package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.LegalStatus;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.LegalStatusData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class LegalStatusPopulator implements Populator<LegalStatusData, LegalStatus> {
    @Override
    public void populate(LegalStatusData legalStatusData, LegalStatus legalStatus) throws ConversionException {
        legalStatus.setLegalStatus(legalStatusData.getLegalkey());
        legalStatus.setLegalStatusText(legalStatusData.getLegalvalue());
    }
}
