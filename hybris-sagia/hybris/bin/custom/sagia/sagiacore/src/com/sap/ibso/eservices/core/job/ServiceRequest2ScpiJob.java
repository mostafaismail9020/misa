package com.sap.ibso.eservices.core.job;


import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.investsaudiportal.scpi.outbound.services.ScpiOutboundServiceImpl;
import com.sap.ibso.eservices.core.sagia.services.SagiaTicketService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import javax.annotation.Resource;

public class ServiceRequest2ScpiJob extends AbstractJobPerformable<CronJobModel> {
    @Resource
    private SagiaTicketService sagiaTicketService;
    @Resource(name = "defaultScpiOutboundService")
    ScpiOutboundServiceImpl scpiOutboundService;
    @Override
    public PerformResult perform(CronJobModel cronJobModel){
        PerformResult performResult = null;
        for(ServiceRequestModel serviceRequest : sagiaTicketService.getScpiServiceRequest()){
            scpiOutboundService.sendServiceRequest(serviceRequest);
        }
        performResult = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        return performResult;
    }
}
