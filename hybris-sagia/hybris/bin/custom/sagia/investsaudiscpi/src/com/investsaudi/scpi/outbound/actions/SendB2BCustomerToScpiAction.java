package com.investsaudi.scpi.outbound.actions;

import com.investsaudi.model.scpi.outbound.process.ScpiOutB2BCustomerProcessModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.sap.sapcpicustomerexchangeb2b.outbound.services.impl.SapCpiB2BCustomerOutboundService;
import de.hybris.platform.task.RetryLaterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;

/**
 * The type Send b 2 b customer to scpi action.
 */
public class SendB2BCustomerToScpiAction extends AbstractAction<ScpiOutB2BCustomerProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(SendB2BCustomerToScpiAction.class);

    /**
     * The Sap cpi b 2 b customer outbound service.
     */
    @Resource(name = "sapCpiB2BCustomerOutboundService")
    SapCpiB2BCustomerOutboundService sapCpiB2BCustomerOutboundService;

    @Override
    public Set<String> getTransitions() {
        return AbstractAction.createTransitions("OK", "NOK");
    }

    @Override
    public String execute(ScpiOutB2BCustomerProcessModel scpiOutB2BCustomerProcessModel)
            throws RetryLaterException {
        try {
            B2BCustomerModel b2bCustomerModel = scpiOutB2BCustomerProcessModel.getB2bCustomer();
            if (Objects.isNull(b2bCustomerModel.getDefaultB2BUnit())) {
                LOG.error("B2B customer [{}] cannot be replicated to SCPI because it is missing the default B2B unit!", b2bCustomerModel.getUid());
                return ("NOK");
            }
            sapCpiB2BCustomerOutboundService.prepareAndSend(b2bCustomerModel, "en");
            return ("OK");
        } catch (Exception e) {
            LOG.error("Error while sending b2bCustomer: " + scpiOutB2BCustomerProcessModel.getB2bCustomer().getUid() + " to SCPI ", e);
            return ("NOK");
        }
    }
}
