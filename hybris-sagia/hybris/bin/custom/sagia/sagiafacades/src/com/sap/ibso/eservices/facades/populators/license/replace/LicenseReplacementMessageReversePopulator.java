package com.sap.ibso.eservices.facades.populators.license.replace;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementMessages;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentToTextNavData;
import de.hybris.platform.converters.Populator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *
 */
public class LicenseReplacementMessageReversePopulator implements Populator<LicenseReplacementMessages, LicenseReplaceMentToTextNavData> {
    private static final String ZONE_UTC = "UTC";
    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(LicenseReplacementMessages source, LicenseReplaceMentToTextNavData target) {
        target.setSrID(source.getSrID());
        target.setSrGuid(source.getSrID());
        target.setCommentBy(source.getCommentBy());
        target.setComments(source.getComments());
        LocalDateTime dateTime = Instant.ofEpochMilli(source.getCommentDate().getMillis()).atZone(ZoneId.of(ZONE_UTC)).toLocalDateTime();
        target.setCommentDate(dateTime);
        target.setStage(source.getStage());
        target.setCommentTime(source.getCommentTime());
        target.setTdID(source.getTdID());
        target.setZone(source.getZone());
    }

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
