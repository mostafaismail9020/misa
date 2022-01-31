package com.investsaudiportal.scpi.outbound.actions;

import com.investsaudiportal.scpi.outbound.services.ScpiOutboundServiceImpl;

import com.sap.ibso.eservices.core.model.ScpiOutCsCustomerEventProcessModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.task.RetryLaterException;

import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

public class SendCsCustomerEventToScpiAction extends AbstractAction<ScpiOutCsCustomerEventProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(SendCsCustomerEventToScpiAction.class);

    @Resource(name = "defaultScpiOutboundService")
    ScpiOutboundServiceImpl scpiOutboundService;

    @Override
    public Set<String> getTransitions() {
        return AbstractAction.createTransitions("OK", "NOK");
    }

    @Override
    public String execute(ScpiOutCsCustomerEventProcessModel scpiOutCsCustomerEventProcessModel) throws RetryLaterException, Exception {

        try {
             CsCustomerEventModel customerEvent = scpiOutCsCustomerEventProcessModel.getCsCustomerEvent();
            scpiOutboundService.sendCsCustomerEvent(customerEvent);
            return("OK");
        }
        catch (Exception e) {
          LOG.error("Error while sending customerEvent: " + scpiOutCsCustomerEventProcessModel.getCsCustomerEvent().getCode()+" to SCPI ",e);
            return("NOK");
        }

    }

}
