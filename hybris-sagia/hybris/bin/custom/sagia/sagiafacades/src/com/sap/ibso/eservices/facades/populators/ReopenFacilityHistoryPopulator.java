package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.reopenfacility.ReopenFacilityHistory;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityHistoryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ReopenFacilityHistoryPopulator implements Populator<ReopenFacilityHistoryData, ReopenFacilityHistory>{
    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(ReopenFacilityHistoryData reopenFacilityHistoryData, ReopenFacilityHistory reopenFacilityHistory) throws ConversionException {
        reopenFacilityHistory.setBpId(reopenFacilityHistoryData.getBpid());
		reopenFacilityHistory.setObjectId(reopenFacilityHistoryData.getObjectId());
		reopenFacilityHistory.setCreatedDate(reopenFacilityHistoryData.getCrtDate());
		reopenFacilityHistory.setCreatedDateData(sagiaFormatProvider.getLocalizedDateData(reopenFacilityHistoryData.getCrtDate()));
		reopenFacilityHistory.setStatusKey(reopenFacilityHistoryData.getStatusKey());
		reopenFacilityHistory.setStatusText(reopenFacilityHistoryData.getStatusText());
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
