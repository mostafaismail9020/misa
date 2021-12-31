package com.investsaudi.scpi.outbound.actions;

import com.investsaudi.model.scpi.outbound.process.ScpiOutInvestmentOpportunityProcessModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.model.CsTicketModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * The type Work flow approval validation action.
 */
public class WorkFlowApprovalValidationAction extends AbstractAction<ScpiOutInvestmentOpportunityProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(WorkFlowApprovalValidationAction.class);

    @Override
    public Set<String> getTransitions() {
        return AbstractAction.createTransitions("OK", "NOK");
    }

    @Override
    public String execute(final ScpiOutInvestmentOpportunityProcessModel scpiOutInvestmentOpportunityProcessModel) throws Exception {
        String status = "OK";
        try {
            CsTicketModel csTicketModel = scpiOutInvestmentOpportunityProcessModel.getCsTicket();

            if (csTicketModel.getState().equals(CsTicketState.WOAGAPPROVED)) {
                status = "OK";   //Sumbit in case of approval required flag is false.
            } else if (csTicketModel.getState().equals(CsTicketState.WOAGREJECTED)) {
                status = "NOK"; //Close BP in case of rejection.
            } else if (csTicketModel.getState().equals(CsTicketState.WOAGPENDINGAPPROVAL)) {
                status = "WAIT"; //wait in case of status is pending approval.
            }
        } catch (Exception e) {
            LOG.error("Error in validation ", e);
            status = "NOK";
        }
        return status;
    }

    /**
     * Update ticket status.
     *
     * @param csTicket      the cs ticket
     * @param csTicketState the cs ticket state
     */
    public void updateTicketStatus(final CsTicketModel csTicket, final CsTicketState csTicketState) {
        csTicket.setState(csTicketState);
        getModelService().save(csTicket);
    }
}
