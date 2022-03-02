package com.sap.ibso.eservices.core.job;


import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudiportal.scpi.outbound.services.ScpiOutboundServiceImpl;
import com.sap.ibso.eservices.core.sagia.services.SagiaTicketService;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import javax.annotation.Resource;

public class ContactTicket2ScpiJob extends AbstractJobPerformable<CronJobModel> {
    @Resource
    private SagiaTicketService sagiaTicketService;
    @Resource(name = "defaultScpiOutboundService")
    ScpiOutboundServiceImpl scpiOutboundService;
    @Override
    public PerformResult perform(CronJobModel cronJobModel){
        PerformResult performResult = null;
        for(ContactTicketModel contactTicket : sagiaTicketService.getScpiTickets()){
            scpiOutboundService.sendLeadTicket(contactTicket);
        }
        return performResult;
    }
}
