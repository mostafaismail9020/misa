package com.investsaudi.portal.core.service;

import com.investsaudi.model.OpportunityUserEmailProcessModel;
import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.model.ScpiOutCsCustomerEventProcessModel;
import com.sap.ibso.eservices.core.model.ScpiOutServiceRequestProcessModel;
import com.sap.ibso.eservices.core.model.ScpiOutTicketAttachmentProcessModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.comments.services.CommentService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.ticket.events.model.CsCustomerEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketService;
import de.hybris.platform.ticket.service.impl.DefaultTicketBusinessService;
import de.hybris.platform.ticket.strategies.TicketEventStrategy;
import de.hybris.platform.ticketsystem.data.ContactTicketParameter;
import de.hybris.platform.ticketsystem.data.CsTicketParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

// added by c4p\mpop - lead management - start
import com.investsaudiportal.model.scpi.outbound.process.ScpiOutLeadTicketProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.util.Config;
// added by c4p\mpop - lead management - end


public class ContactTicketBusinessService extends DefaultTicketBusinessService {

    private static final Logger log = LoggerFactory.getLogger(ContactTicketBusinessService.class);

    private static final String IS_NEW_CUSTOMER = "isNewCustomer";
    
    @Resource(name = "businessProcessService")
    private BusinessProcessService businessProcessService;

    @Resource(name = "contactTicketParameterConverter")
    private Converter<ContactTicketParameter, ContactTicketModel> contactTicketParameterConverter;

    @Resource(name = "contactTicketEventStrategy")
    private TicketEventStrategy ticketEventStrategy;

    @Resource(name = "commentService")
    private CommentService commentService;
    
    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource
    private CMSSiteService cmsSiteService;
    
    @Resource
    private BaseStoreService baseStoreService;
    
    @Resource
    private CommonI18NService commonI18NService;
    
    @Resource
    private EventService eventService;
    
    @Resource
    private TicketService ticketService;
    
	@Resource
    private BaseSiteService baseSiteService;


    @Override
    public CsTicketModel createTicket(CsTicketParameter ticketParameter) {

        if (ticketParameter instanceof ContactTicketParameter) {
            ContactTicketParameter contactTicketParameter = (ContactTicketParameter) ticketParameter;
            if(null!=sessionService.getAttribute("partnerSystem"))
            {
                contactTicketParameter.setPartnerSystem(sessionService.getAttribute("partnerSystem"));
            }
            CsTicketModel ticket = contactTicketParameterConverter.convert(contactTicketParameter);
            CsCustomerEventModel creationEvent = ticketEventStrategy.createCreationEventForTicket(ticket,
                ticketParameter.getReason(), ticketParameter.getInterventionType(), ticketParameter.getCreationNotes());

            CsTicketModel csTicket = createTicketInternal(ticket, creationEvent);
            getModelService().save(csTicket);

            // added by c4p\mpop - lead management - start
            if (Config.getBoolean("leadticket.scpi.interface.enable", true)) {
                final ScpiOutLeadTicketProcessModel
                        scpiOutLeadTicketProcessModel =
                        (ScpiOutLeadTicketProcessModel) businessProcessService
                                .createProcess("scpiOutLeadTicketProcess-" + csTicket.getTicketID()
                                        + "-" + System.currentTimeMillis(), "scpiOutLeadTicketProcess");
                scpiOutLeadTicketProcessModel.setCsTicket(csTicket);
                getModelService().save(scpiOutLeadTicketProcessModel);
                businessProcessService.startProcess(scpiOutLeadTicketProcessModel);
                sessionService.setAttribute("partnerSystem",null);
            }
            // added by c4p\mpop - lead management - end

            return csTicket;
        }

        log.warn("This service can only create ticket of type ContactTicketParameter, parameter type: [{}]",
            ticketParameter
                .getClass().getName());
        return null;
    }

    public void sendOpportunityUserDetails(String id, UserModel customer, String initialPassword,boolean isMigratedCustomer) {
        final OpportunityUserEmailProcessModel opportunityUserEmailProcessModel = 
        		(OpportunityUserEmailProcessModel) businessProcessService.createProcess("opportunityUserEmailProcess-" + id+ "-" 
        				+ System.currentTimeMillis(), "opportunityUserEmailProcess");
        
        opportunityUserEmailProcessModel.setCustomer((CustomerModel) customer);
        opportunityUserEmailProcessModel.setOpportunityId(id);
        opportunityUserEmailProcessModel.setInitialPassword(initialPassword);
        
        if (isMigratedCustomer) {
        	opportunityUserEmailProcessModel.setIsNewCustomer(true);
        	opportunityUserEmailProcessModel.setSite(baseSiteService.getBaseSiteForUID(SagiaCoreConstants.SITE));
        	opportunityUserEmailProcessModel.setStore(baseStoreService.getBaseStoreForUid(SagiaCoreConstants.SITE));
        }
		else {
			log.info("IS_NEW_CUSTOMER sessionService= " + sessionService.getAttribute(IS_NEW_CUSTOMER));
			if (null != sessionService.getAttribute(IS_NEW_CUSTOMER))
			{
				opportunityUserEmailProcessModel.setIsNewCustomer(sessionService.getAttribute(IS_NEW_CUSTOMER));
			}
			else {
				opportunityUserEmailProcessModel.setIsNewCustomer(true);
			}
			opportunityUserEmailProcessModel.setSite(cmsSiteService.getCurrentSite());
			opportunityUserEmailProcessModel.setStore(baseStoreService.getCurrentBaseStore());
		}
        
        opportunityUserEmailProcessModel.setLanguage(commonI18NService.getCurrentLanguage());
        opportunityUserEmailProcessModel.setCurrency(commonI18NService.getCurrentCurrency());

        getModelService().save(opportunityUserEmailProcessModel);
        businessProcessService.startProcess(opportunityUserEmailProcessModel);
    }
	
	public void customerEvent2SCPI(CsCustomerEventModel customerEvent){
        if (Config.getBoolean("customerEvent.scpi.interface.enable", true)) {
            final ScpiOutCsCustomerEventProcessModel scpiOutCsCustomerEventProcessModel =
                    (ScpiOutCsCustomerEventProcessModel) businessProcessService
                    .createProcess("scpiOutCsCustomerEventProcess-" + customerEvent.getCode()
                            + "-" + System.currentTimeMillis(), "scpiOutCsCustomerEventProcess");
            scpiOutCsCustomerEventProcessModel.setCsCustomerEvent(customerEvent);
            getModelService().save(scpiOutCsCustomerEventProcessModel);
            businessProcessService.startProcess(scpiOutCsCustomerEventProcessModel);
            sessionService.setAttribute("partnerSystem",null);
        }
    }
	
	public void servicerequest2SCPI(ServiceRequestModel serviceRequest){
        if (Config.getBoolean("serviceRequest.scpi.interface.enable", true)) {
            final ScpiOutServiceRequestProcessModel scpiOutServiceRequestProcessModel =
                    (ScpiOutServiceRequestProcessModel) businessProcessService
                            .createProcess("scpiOutServiceRequestProcess-" + serviceRequest.getId()
                                    + "-" + System.currentTimeMillis(), "scpiOutServiceRequestProcess");
            scpiOutServiceRequestProcessModel.setServiceRequest(serviceRequest);
            getModelService().save(scpiOutServiceRequestProcessModel);
            businessProcessService.startProcess(scpiOutServiceRequestProcessModel);
            sessionService.setAttribute("partnerSystem",null);
        }
    }
	
	  public void ticketAttachment2SCPI(ContactTicketModel contactTicket){
        if (Config.getBoolean("ticketAttachment.scpi.interface.enable", true)) {
            final ScpiOutTicketAttachmentProcessModel scpiOutTicketAttachmentProcessModel =
                    (ScpiOutTicketAttachmentProcessModel) businessProcessService
                            .createProcess("scpiOutTicketAttachmentProcess-" + contactTicket.getTicketID()
                                    + "-" + System.currentTimeMillis(), "scpiOutTicketAttachmentProcess");
            scpiOutTicketAttachmentProcessModel.setContactTicket(contactTicket);
            getModelService().save(scpiOutTicketAttachmentProcessModel);
            businessProcessService.startProcess(scpiOutTicketAttachmentProcessModel);
            sessionService.setAttribute("partnerSystem",null);
        }
    }
}
