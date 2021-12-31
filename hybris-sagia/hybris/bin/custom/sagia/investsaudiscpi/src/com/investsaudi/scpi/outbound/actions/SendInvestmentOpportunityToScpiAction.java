package com.investsaudi.scpi.outbound.actions;

import com.investsaudi.model.scpi.outbound.process.ScpiOutInvestmentOpportunityProcessModel;
import com.investsaudi.scpi.outbound.services.MISAScpiOutboundServiceImpl;

import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.model.CsTicketModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

/**
 * The type Send investment opportunity to scpi action.
 */
public class SendInvestmentOpportunityToScpiAction extends AbstractAction<ScpiOutInvestmentOpportunityProcessModel> {
    private static final Logger LOG = LoggerFactory.getLogger(SendInvestmentOpportunityToScpiAction.class);

	/**
	 * The Misa scpi default outbound service.
	 */
	@Resource(name = "misaScpiDefaultOutboundService")
    private MISAScpiOutboundServiceImpl misaScpiDefaultOutboundService;

	@Resource
    private ModelService modelService;

    @Override
    public Set<String> getTransitions() {
        return AbstractAction.createTransitions("OK", "NOK");
    }

    @Override
    public String execute(ScpiOutInvestmentOpportunityProcessModel scpiOutInvestmentOpportunityProcessModel)
            throws RetryLaterException {
        try {
            CsTicketModel csTicketModel = scpiOutInvestmentOpportunityProcessModel.getCsTicket();
            LOG.info(String.format("Sending the %s to SCPI for upload.", csTicketModel.getTicketID()));
            csTicketModel.setState(CsTicketState.UPLOADED);
            modelService.save(csTicketModel);
            misaScpiDefaultOutboundService.sendOpportunity(csTicketModel);
            return ("OK");
        } catch (Exception e) {
            LOG.error("Error while sending InvestmentOpportunity: "
                    + scpiOutInvestmentOpportunityProcessModel.getCsTicket().getTicketID() + " to SCPI ", e);
            return ("NOK");
        }
    }
}
