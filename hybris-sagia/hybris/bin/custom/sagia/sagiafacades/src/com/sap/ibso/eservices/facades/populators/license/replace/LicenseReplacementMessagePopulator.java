package com.sap.ibso.eservices.facades.populators.license.replace;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementMessages;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentToTextNavData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class LicenseReplacementMessagePopulator implements Populator<LicenseReplaceMentToTextNavData, LicenseReplacementMessages> {
    private SagiaFormatProvider sagiaFormatProvider;
    @Override
    public void populate(LicenseReplaceMentToTextNavData licenseReplaceMentToTextNavData, LicenseReplacementMessages licenseReplacementMessage) {
        licenseReplacementMessage.setSrID(licenseReplaceMentToTextNavData.getSrID());
        licenseReplacementMessage.setSrGuid(licenseReplaceMentToTextNavData.getSrID());
        licenseReplacementMessage.setCommentBy(licenseReplaceMentToTextNavData.getCommentBy());
        licenseReplacementMessage.setComments(licenseReplaceMentToTextNavData.getComments());
        if (licenseReplaceMentToTextNavData.getCommentDate()!= null) {
            licenseReplacementMessage.setCommentDate(sagiaFormatProvider.getLocalizedDateTimeData(licenseReplaceMentToTextNavData.getCommentDate()));
        }
        licenseReplacementMessage.setStage(licenseReplaceMentToTextNavData.getStage());
        licenseReplacementMessage.setCommentTime(licenseReplaceMentToTextNavData.getCommentTime());
        licenseReplacementMessage.setTdID(licenseReplaceMentToTextNavData.getTdID());
        licenseReplacementMessage.setZone(licenseReplaceMentToTextNavData.getZone());
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
