package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.reopenfacility.ReopenFacility;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityDetailsData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ReopenFacilityPopulator implements Populator<ReopenFacilityDetailsData, ReopenFacility> {

    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(ReopenFacilityDetailsData reopenFacilityData, ReopenFacility reopenFacility) throws ConversionException {
        reopenFacility.setSrId(reopenFacilityData.getSrID());
        reopenFacility.setBpId(reopenFacilityData.getBPID());
        reopenFacility.setVisitDate(reopenFacilityData.getVisitDate());
        reopenFacility.setVisitDateData(sagiaFormatProvider.getLocalizedDateData(reopenFacilityData.getVisitDate()));
        reopenFacility.setText(reopenFacilityData.getText());
        reopenFacility.setTdid(reopenFacilityData.getTdid());
        reopenFacility.setDocumentType(reopenFacilityData.getDocType());
        reopenFacility.setVisitType(reopenFacilityData.getVisitType());
        reopenFacility.setSource(reopenFacilityData.getSource());
    }


    /**
     * @param sagiaFormatProvider sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
