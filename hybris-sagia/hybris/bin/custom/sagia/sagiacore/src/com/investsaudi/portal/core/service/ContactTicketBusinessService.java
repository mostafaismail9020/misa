package com.investsaudi.portal.core.service;

import com.investsaudi.model.InvestorVisaTicketDetailsEmailProcessModel;
import com.investsaudi.model.MizaTicketDetailsEmailProcessModel;
import com.investsaudi.model.OpportunityUserEmailProcessModel;
import com.investsaudi.model.StrategicInvestorTicketDetailsEmailProcessModel;
import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.investsaudiportal.model.scpi.outbound.process.ScpiOutLeadTicketProcessModel;
import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.model.ScpiOutCsCustomerEventProcessModel;
import com.sap.ibso.eservices.core.model.ScpiOutServiceRequestProcessModel;
import com.sap.ibso.eservices.core.model.ScpiOutTicketAttachmentProcessModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.comments.services.CommentService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.processengine.BusinessProcessService;
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
import de.hybris.platform.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

// added by c4p\mpop - lead management - start
// added by c4p\mpop - lead management - end


public class ContactTicketBusinessService extends DefaultTicketBusinessService {

    private static final Logger LOG = LoggerFactory.getLogger(ContactTicketBusinessService.class);

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

    @Resource(name = "sagiaUserService")
    private SagiaUserService sagiaUserService;

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
        boolean isMizaContactUsFlow=false;
        if(null!=sessionService.getAttribute("isMizaContactUsFlow"))
        {
            isMizaContactUsFlow=true;
        }
        boolean isStrategicContactUsFlow=false;
        if(null!=sessionService.getAttribute("isStrategicContactUsFlow"))
        {
            isStrategicContactUsFlow=true;
        }
        boolean isInvestorVisaContactUsFlow=false;
        if(null!=sessionService.getAttribute("isInvestorVisaContactUsFlow"))
        {
            isInvestorVisaContactUsFlow=true;
        }
        if (ticketParameter instanceof ContactTicketParameter) {
            ContactTicketParameter contactTicketParameter = (ContactTicketParameter) ticketParameter;
            if(null!=sessionService.getAttribute("partnerSystem"))
            {
                contactTicketParameter.setPartnerSystem(sessionService.getAttribute("partnerSystem"));
            }
            if(isMizaContactUsFlow)
            {
                contactTicketParameter.setPartnerSystem("MIZA");
            }
            if(isStrategicContactUsFlow)
            {
                contactTicketParameter.setPartnerSystem("STRATEGIC-INVESTOR");
            }
            if(isInvestorVisaContactUsFlow)
            {
                contactTicketParameter.setPartnerSystem("INVESTOR-VISA");
            }
            CsTicketModel ticket = contactTicketParameterConverter.convert(contactTicketParameter);
            CsCustomerEventModel creationEvent = ticketEventStrategy.createCreationEventForTicket(ticket,
                ticketParameter.getReason(), ticketParameter.getInterventionType(), ticketParameter.getCreationNotes());

            CsTicketModel csTicket = createTicketInternal(ticket, creationEvent);
            getModelService().save(csTicket);

            // added by c4p\mpop - lead management - start
            if(!(isMizaContactUsFlow || isStrategicContactUsFlow || isInvestorVisaContactUsFlow)) {
                if (Config.getBoolean("leadticket.scpi.interface.enable", true)) {
                    final ScpiOutLeadTicketProcessModel
                            scpiOutLeadTicketProcessModel =
                            (ScpiOutLeadTicketProcessModel) businessProcessService
                                    .createProcess("scpiOutLeadTicketProcess-" + csTicket.getTicketID()
                                            + "-" + System.currentTimeMillis(), "scpiOutLeadTicketProcess");
                    scpiOutLeadTicketProcessModel.setCsTicket(csTicket);
                    getModelService().save(scpiOutLeadTicketProcessModel);
                    businessProcessService.startProcess(scpiOutLeadTicketProcessModel);
                    sessionService.setAttribute("partnerSystem", null);
                }
            }
            // added by c4p\mpop - lead management - end

            return csTicket;
        }

        LOG.warn("This service can only create ticket of type ContactTicketParameter, parameter type: [{}]",
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
			LOG.info("IS_NEW_CUSTOMER sessionService= " + sessionService.getAttribute(IS_NEW_CUSTOMER));
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

    public void sendMizaTicketDetails(String id) {
        ContactTicketModel mizaTicket = sagiaUserService.getContactTicketForTicketId(id);
        if (null != mizaTicket) {
            final MizaTicketDetailsEmailProcessModel mizaTicketDetailEmailProcess =
                    (MizaTicketDetailsEmailProcessModel) businessProcessService.createProcess("mizaTicketEmailProcess-" + id + "-"
                            + System.currentTimeMillis(), "mizaTicketEmailProcess");

            mizaTicketDetailEmailProcess.setOpportunityId(id);
            mizaTicketDetailEmailProcess.setMizaTicketUserName(mizaTicket.getName());
            mizaTicketDetailEmailProcess.setMizaTicketUserCompanyName(mizaTicket.getCompany());
            mizaTicketDetailEmailProcess.setMizaTicketUserPhoneNumber(mizaTicket.getMobile());
            mizaTicketDetailEmailProcess.setMizaTicketUserPosition(mizaTicket.getJobTitle());
            mizaTicketDetailEmailProcess.setMizaTicketUserEmail(mizaTicket.getEmail());
            mizaTicketDetailEmailProcess.setMizaTicketUserService(mizaTicket.getHeadline());
            mizaTicketDetailEmailProcess.setMizaTicketUserEnquiry(mizaTicket.getMessage());

            mizaTicketDetailEmailProcess.setSite(cmsSiteService.getCurrentSite());
            mizaTicketDetailEmailProcess.setStore(baseStoreService.getCurrentBaseStore());
            mizaTicketDetailEmailProcess.setLanguage(commonI18NService.getCurrentLanguage());
            mizaTicketDetailEmailProcess.setCurrency(commonI18NService.getCurrentCurrency());

            getModelService().save(mizaTicketDetailEmailProcess);
            businessProcessService.startProcess(mizaTicketDetailEmailProcess);
        }
        else
        {
            LOG.error("No Contact Ticket Found with Ticket ID "+id);
        }
    }
    public void sendStrategicInvestorTicketDetails(String id) {
        ContactTicketModel strategicTicket = sagiaUserService.getContactTicketForTicketId(id);
        if (null != strategicTicket) {
            final StrategicInvestorTicketDetailsEmailProcessModel strategicInvestorTicketDetailEmailProcess =
                    (StrategicInvestorTicketDetailsEmailProcessModel) businessProcessService.createProcess("strategicInvestorTicketEmailProcess-" + id + "-"
                            + System.currentTimeMillis(), "strategicInvestorTicketEmailProcess");

            strategicInvestorTicketDetailEmailProcess.setOpportunityId(id);
            strategicInvestorTicketDetailEmailProcess.setStrategicInvestorTicketUserName(strategicTicket.getName());
            strategicInvestorTicketDetailEmailProcess.setStrategicInvestorTicketUserCompanyName(strategicTicket.getCompany());
            strategicInvestorTicketDetailEmailProcess.setStrategicInvestorTicketUserPhoneNumber(strategicTicket.getMobile());
            strategicInvestorTicketDetailEmailProcess.setStrategicInvestorTicketUserPosition(strategicTicket.getJobTitle());
            strategicInvestorTicketDetailEmailProcess.setStrategicInvestorTicketUserEmail(strategicTicket.getEmail());
            strategicInvestorTicketDetailEmailProcess.setStrategicInvestorTicketUserService(strategicTicket.getHeadline());
            strategicInvestorTicketDetailEmailProcess.setStrategicInvestorTicketUserEnquiry(strategicTicket.getMessage());

            strategicInvestorTicketDetailEmailProcess.setSite(cmsSiteService.getCurrentSite());
            strategicInvestorTicketDetailEmailProcess.setStore(baseStoreService.getCurrentBaseStore());
            strategicInvestorTicketDetailEmailProcess.setLanguage(commonI18NService.getCurrentLanguage());
            strategicInvestorTicketDetailEmailProcess.setCurrency(commonI18NService.getCurrentCurrency());

            getModelService().save(strategicInvestorTicketDetailEmailProcess);
            businessProcessService.startProcess(strategicInvestorTicketDetailEmailProcess);
        }
        else
        {
            LOG.error("No Contact Ticket Found with Ticket ID "+id);
        }
    }
    public void sendInvestorVisaTicketDetails(String id) {
        ContactTicketModel strategicTicket = sagiaUserService.getContactTicketForTicketId(id);
        if (null != strategicTicket) {
            final InvestorVisaTicketDetailsEmailProcessModel investorVisaTicketDetailEmailProcess =
                    (InvestorVisaTicketDetailsEmailProcessModel) businessProcessService.createProcess("investorVisaTicketEmailProcess-" + id + "-"
                            + System.currentTimeMillis(), "investorVisaTicketEmailProcess");

            investorVisaTicketDetailEmailProcess.setOpportunityId(id);
            investorVisaTicketDetailEmailProcess.setInvestorVisaTicketUserName(strategicTicket.getName());
            investorVisaTicketDetailEmailProcess.setInvestorVisaTicketUserPhoneNumber(strategicTicket.getMobile());
            investorVisaTicketDetailEmailProcess.setInvestorVisaTicketUserEmail(strategicTicket.getEmail());
            investorVisaTicketDetailEmailProcess.setInvestorVisaTicketUserEnquiry(strategicTicket.getMessage());

            investorVisaTicketDetailEmailProcess.setSite(cmsSiteService.getCurrentSite());
            investorVisaTicketDetailEmailProcess.setStore(baseStoreService.getCurrentBaseStore());
            investorVisaTicketDetailEmailProcess.setLanguage(commonI18NService.getCurrentLanguage());
            investorVisaTicketDetailEmailProcess.setCurrency(commonI18NService.getCurrentCurrency());

            getModelService().save(investorVisaTicketDetailEmailProcess);
            businessProcessService.startProcess(investorVisaTicketDetailEmailProcess);
        }
        else
        {
            LOG.error("No Contact Ticket Found with Ticket ID "+id);
        }
    }
}
