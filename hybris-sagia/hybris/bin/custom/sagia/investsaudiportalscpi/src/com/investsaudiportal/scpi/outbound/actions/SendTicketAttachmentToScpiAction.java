package com.investsaudiportal.scpi.outbound.actions;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudiportal.scpi.outbound.services.ScpiOutboundServiceImpl;
import com.sap.ibso.eservices.core.model.ScpiOutTicketAttachmentProcessModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.task.RetryLaterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

public class SendTicketAttachmentToScpiAction extends AbstractAction<ScpiOutTicketAttachmentProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(ScpiOutTicketAttachmentProcessModel.class);

    @Resource(name = "defaultScpiOutboundService")
    ScpiOutboundServiceImpl scpiOutboundService;

    @Override
    public Set<String> getTransitions() {
        return AbstractAction.createTransitions("OK", "NOK");
    }

    @Override
    public String execute(ScpiOutTicketAttachmentProcessModel scpiOutTicketAttachmentProcessModel) throws RetryLaterException, Exception {

        try {
             ContactTicketModel contactTicket = scpiOutTicketAttachmentProcessModel.getContactTicket();
            scpiOutboundService.sendTicketAttachment(contactTicket);
            return("OK");
        }
        catch (Exception e) {
          LOG.error("Error while sending serviceRequest: " + scpiOutTicketAttachmentProcessModel.getContactTicket().getTicketID()+" to SCPI ",e);
            return("NOK");
        }

    }

}
