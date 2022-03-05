package com.sap.ibso.eservices.core.job;


import com.investsaudiportal.scpi.outbound.services.ScpiOutboundServiceImpl;
import com.sap.ibso.eservices.core.sagia.services.SagiaTicketService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

public class CustomerEvent2ScpiJob extends AbstractJobPerformable<CronJobModel> {
    @Resource
    private SagiaTicketService sagiaTicketService;
    @Resource(name = "defaultScpiOutboundService")
    ScpiOutboundServiceImpl scpiOutboundService;

    private final static Logger LOG = org.apache.log4j.Logger.getLogger(ContactTicket2ScpiJob.class);
    @Override
    public PerformResult perform(CronJobModel cronJobModel){
        PerformResult performResult = null;
        try {
            if(null != sagiaTicketService.getScpiCustomerEvents()) {
                for (CsCustomerEventModel event : sagiaTicketService.getScpiCustomerEvents()) {
                    scpiOutboundService.sendCsCustomerEvent(event);
                }
                performResult = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
            }
            else{
                performResult = new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
            }
        }
        catch(Exception e){
            LOG.error("Something went wrong while sending item to SCPI : " + e.getMessage());
            e.printStackTrace();
            performResult = new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
        }
        return performResult;
    }
}
