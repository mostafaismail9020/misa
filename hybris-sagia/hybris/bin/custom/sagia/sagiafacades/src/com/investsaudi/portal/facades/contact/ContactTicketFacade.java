package com.investsaudi.portal.facades.contact;

import com.investsaudi.portal.core.model.ContactTicketPurposeModel;
import com.investsaudi.portal.core.model.MizaServiceTypeForFormModel;
import com.investsaudi.portal.core.model.StrategicInvestorServiceTypeModel;
import com.investsaudi.portal.core.service.ContactTicketBusinessService;
import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.sagia.services.impl.DefaultSagiaCountryService;
import com.sap.ibso.eservices.core.sagia.services.impl.DefaultSagiaSectorService;
import com.sap.ibso.eservices.core.sagia.services.impl.DefaultSagiaUserService;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerticketingfacades.customerticket.DefaultCustomerTicketingFacade;
import de.hybris.platform.customerticketingfacades.data.ContactTicketData;
import de.hybris.platform.customerticketingfacades.data.ContactTicketSubjectData;
import de.hybris.platform.customerticketingfacades.data.MizaServiceTypeForFormData;
import de.hybris.platform.customerticketingfacades.data.StrategicInvestorServiceTypeData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.enums.CsTicketCategory;
import de.hybris.platform.ticket.enums.CsTicketPriority;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticketsystem.data.ContactTicketParameter;
import de.hybris.platform.ticketsystem.data.CsTicketParameter;
import de.hybris.platform.util.Config;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class ContactTicketFacade extends DefaultCustomerTicketingFacade {
    private static final Logger LOG = Logger.getLogger(ContactTicketFacade.class);
    private static final String IS_NEW_CUSTOMER = "isNewCustomer";
    @Autowired
    @Resource(name = "contactTicketSubjectConverter")
    private Converter<ContactTicketPurposeModel, ContactTicketSubjectData> contactTicketSubjectConverter;

    @Autowired
    @Resource(name = "mizaServiceTypeConverter")
    private Converter<MizaServiceTypeForFormModel, MizaServiceTypeForFormData> mizaServiceTypeConverter;

    @Resource(name = "strategicServiceTypeConverter")
    private Converter<StrategicInvestorServiceTypeModel, StrategicInvestorServiceTypeData> strategicServiceTypeConverter;

    @Autowired
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Autowired
    private ContactTicketBusinessService contactTicketBusinessService;

    @Autowired
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;
    @Resource(name = "userService")
    private DefaultSagiaUserService userService;
    @Resource(name = "sagiaCountryService")
    private DefaultSagiaCountryService sagiaCountryService;
    @Resource(name = "sagiaSectorService")
    private DefaultSagiaSectorService sagiaSectorService;
    @Resource(name = "modelService")
    private ModelService modelService;
    @Resource(name = "sessionService")
    private SessionService sessionService;

    String initialPassword=generateRandomPassword();
    boolean newCustomer=false;
    public CsTicketModel saveTicket(ContactTicketData contactTicketData) {

        CsTicketParameter csTicketParameter = createCsTicketParameter(contactTicketData);
        CsTicketModel ticket = contactTicketBusinessService.createTicket(csTicketParameter);
		CustomerModel customer = (CustomerModel)csTicketParameter.getCustomer();
        if(null!=contactTicketData.getJobTitle() || (null==contactTicketData.getJobTitle() && newCustomer==true)) {
            contactTicketBusinessService.sendOpportunityUserDetails(ticket.getTicketID(), csTicketParameter.getCustomer(), initialPassword,
                    SagiaCoreConstants.ORIGINSYSTEM.equalsIgnoreCase(customer.getSystemOrigin()));
            newCustomer=false;
        }
        return ticket;
    }

    public CsTicketModel saveMizaTicket(ContactTicketData contactTicketData) {

        CsTicketParameter csTicketParameter = createCsTicketParameter(contactTicketData);
        CsTicketModel ticket = contactTicketBusinessService.createTicket(csTicketParameter);
        CustomerModel customer = (CustomerModel)csTicketParameter.getCustomer();
        contactTicketBusinessService.sendMizaTicketDetails(ticket.getTicketID());
        return ticket;
    }
    public CsTicketModel saveStrategicInvestorTicket(ContactTicketData contactTicketData) {

        CsTicketParameter csTicketParameter = createCsTicketParameter(contactTicketData);
        CsTicketModel ticket = contactTicketBusinessService.createTicket(csTicketParameter);
        CustomerModel customer = (CustomerModel)csTicketParameter.getCustomer();
        contactTicketBusinessService.sendStrategicInvestorTicketDetails(ticket.getTicketID());
        return ticket;
    }
    public CsTicketModel saveInvestorVisaTicket(ContactTicketData contactTicketData) {

        CsTicketParameter csTicketParameter = createCsTicketParameter(contactTicketData);
        CsTicketModel ticket = contactTicketBusinessService.createTicket(csTicketParameter);
        CustomerModel customer = (CustomerModel)csTicketParameter.getCustomer();
        contactTicketBusinessService.sendInvestorVisaTicketDetails(ticket.getTicketID());
        return ticket;
    }

    protected CsTicketParameter createCsTicketParameter(final ContactTicketData ticketData) {
        final ContactTicketParameter ticketParameter = new ContactTicketParameter();
        ticketParameter.setPriority(getEnumerationService().getEnumerationValue(CsTicketPriority._TYPECODE, getTicketPriority()));
        ticketParameter.setReason(getEnumerationService().getEnumerationValue(CsEventReason._TYPECODE, getTicketReason()));
        ticketParameter.setAssociatedTo(getTicketService().getAssociatedObject(ticketData.getAssociatedTo(), null, null));
        ticketParameter.setAssignedGroup(getDefaultCsAgentManagerGroup());
        ticketParameter.setCategory(CsTicketCategory.ENQUIRY);
        ticketParameter.setHeadline(ticketData.getContactSubject());
        ticketParameter.setInterventionType(CsInterventionType.TICKETMESSAGE);
        ticketParameter.setCreationNotes(StringUtils.isNotBlank(ticketData.getMessage()) ? ticketData.getMessage() : "Interested in business opportunity");
        UserModel user=getUserService().getCurrentUser();
        if(null!=sessionService.getAttribute("partnerSystem"))
        {
            ticketParameter.setCategoryCode(ticketData.getSectorCode());
        }
        else {
            ticketParameter.setCategoryCode(ticketData.getCategoryCode());
        }
        if(userService.isAnonymousUser(user));
        {
			user= CheckIfUserExistElseCreateUser(ticketData);
        }
        if(null!=sessionService.getAttribute("isMizaContactUsFlow"))
        {
            ticketParameter.setCustomer(userService.getCustomerByEmail("miza@misa.gov.sa"));
        }
        else if(null!=sessionService.getAttribute("isStrategicContactUsFlow"))
        {
            ticketParameter.setCustomer(userService.getCustomerByEmail("Strategic_Investors@misa.gov.sa"));
        }
        else if(null!=sessionService.getAttribute("isInvestorVisaContactUsFlow"))
        {
            ticketParameter.setCustomer(userService.getCustomerByEmail("investorvisa@misa.gov.sa"));
        }
        else
        {
            ticketParameter.setCustomer(user);
        }

        ticketParameter.setAttachments(ticketData.getAttachments());

        ticketParameter.setName(ticketData.getName());
        ticketParameter.setCompany(ticketData.getCompany());
        ticketParameter.setJobTitle(ticketData.getJobTitle());
        ticketParameter.setEmail(ticketData.getEmail().toLowerCase());
        ticketParameter.setMessage(StringUtils.isNotBlank(ticketData.getMessage()) ? ticketData.getMessage() : "Interested in business opportunity");
        ticketParameter.setMobile(ticketData.getCountryCode()+ticketData.getMobile());
        ticketParameter.setContactSubject(ticketData.getContactSubject());
        ticketParameter.setOpportunity(ticketData.getOpportunity());
        ticketParameter.setProductCode(ticketData.getProductCode());
        ticketParameter.setCommerceUserID(ticketParameter.getCustomer().getUid());
        ticketParameter.setC4CAccountID(((CustomerModel) ticketParameter.getCustomer()).getC4cAccountID());

        return ticketParameter;
    }

    private UserModel CheckIfUserExistElseCreateUser(ContactTicketData ticketData) {
        boolean isStaticPageFlow = false;
        if(null!=sessionService.getAttribute("isMizaContactUsFlow") || null!=sessionService.getAttribute("isStrategicContactUsFlow") || null!=sessionService.getAttribute("isInvestorVisaContactUsFlow"))
        {
            isStaticPageFlow=true;
        }

        UserModel userForEmail = userService.getCustomerByEmail(ticketData.getEmail().toLowerCase());
        if (null==userForEmail && !isStaticPageFlow ) {
            try {
                CustomerModel customer=modelService.create(CustomerModel.class);
                customer.setCompany(ticketData.getCompany());
                customer.setUserNameEmail(ticketData.getEmail().toLowerCase());
                customer.setUid(ticketData.getEmail().toLowerCase());
                customer.setName(ticketData.getName());
                customer.setMobileNumber(ticketData.getMobile());
                customer.setCountry(sagiaCountryService.getCountryForPhonePrefix(ticketData.getCountryCode().substring(1)));
                if(null!=sessionService.getAttribute("partnerSystem"))
                {
                    customer.setSector(sagiaSectorService.getSectorForSectorCode(ticketData.getSectorCode()));
                }
                else {
                    customer.setSector(sagiaSectorService.getSectorForSectorCode(ticketData.getCategoryCode()));
                }

                customer.setMobileCountryCode(ticketData.getCountryCode().substring(1));
                customer.setPassword(initialPassword);
                sessionService.setAttribute(IS_NEW_CUSTOMER,true);
                modelService.save(customer);
                newCustomer=true;
                return customer;
            }
            catch (Exception e)
            {
                LOG.error("Error Occured while creating new user for the Opportunity Ticket "+e);
            }

        }
        sessionService.setAttribute(IS_NEW_CUSTOMER,false);
        return userForEmail;
    }

    private String generateRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(Config.getInt("default.password.length", 8));
    }

    public List<ContactTicketSubjectData> getContactTicketSubjects() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery("Select {pk} FROM {ContactTicketPurpose}");
        var contactSubjects = flexibleSearchService.<ContactTicketPurposeModel>search(query).getResult();
        return contactTicketSubjectConverter.convertAll(contactSubjects);
    }

    public List<MizaServiceTypeForFormData> getMizaServiceTypes() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery("Select {pk} FROM {MizaServiceTypeForForm}");
        var contactSubjects = flexibleSearchService.<MizaServiceTypeForFormModel>search(query).getResult();
        return mizaServiceTypeConverter.convertAll(contactSubjects);
    }

    public List<StrategicInvestorServiceTypeData> getStrategicServiceTypes() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery("Select {pk} FROM {StrategicInvestorServiceType}");
        var contactSubjects = flexibleSearchService.<StrategicInvestorServiceTypeModel>search(query).getResult();
        return strategicServiceTypeConverter.convertAll(contactSubjects);
    }
}
