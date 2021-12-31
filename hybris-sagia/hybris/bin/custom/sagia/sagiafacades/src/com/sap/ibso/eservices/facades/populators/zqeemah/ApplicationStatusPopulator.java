package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah.ApplicationStatus;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ApplicationStatusPopulator implements Populator<ApplicationStatusData, ApplicationStatus> {

    @Override
    public void populate(ApplicationStatusData applicationStatusData, ApplicationStatus applicationStatus) throws ConversionException {
        applicationStatus.setEntityId(applicationStatusData.getEntityId());
        applicationStatus.setInvestorId(applicationStatusData.getInvId());
        applicationStatus.setLvReturn(applicationStatusData.getLvReturn());
        applicationStatus.setLeadGuid(applicationStatusData.getLeadGuid());
        applicationStatus.setLeadId(applicationStatusData.getLeadId());
        applicationStatus.setLvDate(applicationStatusData.getLvDate());
        applicationStatus.setStatus(applicationStatusData.getStatus());
        applicationStatus.setStatusDescription(applicationStatusData.getStatusDesc());
        applicationStatus.setQeemah(applicationStatusData.getQeemah());
    }
}
