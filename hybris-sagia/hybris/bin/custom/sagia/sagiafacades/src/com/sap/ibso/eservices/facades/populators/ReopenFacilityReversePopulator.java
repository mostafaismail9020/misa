package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.reopenfacility.ReopenFacility;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityDetailsData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ReopenFacilityReversePopulator implements Populator<ReopenFacility, ReopenFacilityDetailsData> {

    @Override
    public void populate(ReopenFacility reopenFacility, ReopenFacilityDetailsData reopenFacilityData) throws ConversionException {
        reopenFacilityData.setSrID(reopenFacility.getSrId());
        reopenFacilityData.setBPID(reopenFacility.getBpId());
        reopenFacilityData.setVisitDate(reopenFacility.getVisitDate());
        reopenFacilityData.setText(reopenFacility.getText());
        reopenFacilityData.setTdid(reopenFacility.getTdid());
        reopenFacilityData.setDocType(reopenFacility.getDocumentType());
        reopenFacilityData.setVisitType(reopenFacility.getVisitType());
        reopenFacilityData.setSource(reopenFacility.getSource());
    }

}
