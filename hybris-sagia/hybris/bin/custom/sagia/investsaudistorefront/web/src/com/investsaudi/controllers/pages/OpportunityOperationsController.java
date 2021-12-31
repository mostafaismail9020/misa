package com.investsaudi.controllers.pages;

import com.investsaudi.forms.BDSupportTicketForm;
import com.sap.ibso.eservices.core.sagia.services.SagiaNotificationService;
import com.sap.ibso.eservices.facade.customerticket.SagiaCustomerTicketingFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.customerticketingfacades.TicketFacade;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.service.TicketException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * The type Opportunity operations controller.
 */
@Controller
@RequestMapping("/opportunity")
public class OpportunityOperationsController {
    private static final Logger LOG = Logger.getLogger(OpportunityOperationsController.class);
    /**
     * The constant FORWARD_PREFIX.
     */
    public static final String FORWARD_PREFIX = "forward:/my-account";
    /**
     * The constant OPERATION_FAILURE_MESSAGE_KEY.
     */
    public static final String OPERATION_FAILURE_MESSAGE_KEY = "opportunity.operation.failure.message";

    public static final String INVALID_REJECTION_MESSAGE = "opportunity.reject.invalidrejectionmessage";

    @Resource
    private SagiaCustomerTicketingFacade sagiaTicketingFacade;
    @Resource
    private SagiaNotificationService sagiaNotificationService;

    @Resource(name = "defaultTicketFacade")
    private TicketFacade ticketFacade;

    /**
     * Approve opportunity string.
     *
     * @param model      the model
     * @param supportTicketForm the ticket form
     * @return the string
     */
    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    @RequireHardLogIn
    public String approveOpportunity(final Model model, final BDSupportTicketForm supportTicketForm) {
        return processOpportunityOperation(model, supportTicketForm.getId(), CsTicketState.WOAGAPPROVED,
                String.format("Ticket with ticket id - %s is: %s", supportTicketForm.getId(), CsTicketState.WOAGAPPROVED.getCode()));
    }

    /**
     * Reject opportunity string.
     *
     * @param model      the model
     * @param supportTicketForm the ticket form
     * @return the string
     */
    @RequestMapping(value = "/reject", method = RequestMethod.GET)
    @RequireHardLogIn
    public String rejectOpportunity(final Model model, final BDSupportTicketForm supportTicketForm) {
        if(StringUtils.isEmpty(supportTicketForm.getRejectionReason())) {
            GlobalMessages.addErrorMessage(model, INVALID_REJECTION_MESSAGE);
            return String.format(FORWARD_PREFIX+"/support-ticket/{ticketId:%s}", supportTicketForm.getId());
        }
        return processOpportunityOperation(model, supportTicketForm.getId(), CsTicketState.WOAGREJECTED,
                supportTicketForm.getRejectionReason());
    }

    private String processOpportunityOperation(Model model, String ticketId, CsTicketState csTicketState, String message) {
        if(StringUtils.isEmpty(ticketId)) {
            GlobalMessages.addErrorMessage(model, OPERATION_FAILURE_MESSAGE_KEY);
            return String.format(FORWARD_PREFIX+"/support-ticket/{ticketId:%s}", ticketId);
        }

        try {
            sagiaTicketingFacade.setTicketState(ticketId, csTicketState, message);
            if(CsTicketState.WOAGREJECTED.equals(csTicketState) || CsTicketState.WOAGAPPROVED.equals(csTicketState)){
                sagiaNotificationService.sendOpportunityUpdateNotificationToUser(ticketId);
            }
            return FORWARD_PREFIX+"/support-tickets";
        } catch (TicketException | IllegalAccessException e) {
            GlobalMessages.addErrorMessage(model, OPERATION_FAILURE_MESSAGE_KEY);
            LOG.error(e.getMessage(), e);
        }
        return String.format(FORWARD_PREFIX+"/support-ticket/{ticketId:%s}", ticketId);
    }
}
