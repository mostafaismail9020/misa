package com.investsaudiportal.scpi.outbound.actions;

import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.investsaudiportal.scpi.outbound.services.ScpiOutboundServiceImpl;
import com.sap.ibso.eservices.core.model.ScpiOutServiceRequestProcessModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.task.RetryLaterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

public class SendServiceRequestToScpiAction extends AbstractAction<ScpiOutServiceRequestProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(SendServiceRequestToScpiAction.class);

    @Resource(name = "defaultScpiOutboundService")
    ScpiOutboundServiceImpl scpiOutboundService;

    @Override
    public Set<String> getTransitions() {
        return AbstractAction.createTransitions("OK", "NOK");
    }

    @Override
    public String execute(ScpiOutServiceRequestProcessModel scpiOutServiceRequestEventProcessModel) throws RetryLaterException, Exception {

        try {
             ServiceRequestModel serviceRequest = scpiOutServiceRequestEventProcessModel.getServiceRequest();
            scpiOutboundService.sendServiceRequest(serviceRequest);
            return("OK");
        }
        catch (Exception e) {
          LOG.error("Error while sending customerEvent: " + scpiOutServiceRequestEventProcessModel.getServiceRequest().getId()+" to SCPI ",e);
            return("NOK");
        }

    }

}
