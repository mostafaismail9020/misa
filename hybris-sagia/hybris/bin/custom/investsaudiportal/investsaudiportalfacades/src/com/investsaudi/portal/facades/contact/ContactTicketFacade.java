package com.investsaudi.portal.facades.contact;

import javax.annotation.Resource;
import java.util.List;

import com.investsaudi.portal.core.model.ContactTicketSubjectModel;
import com.investsaudi.portal.core.model.MizaServiceTypeForFormModel;
import com.investsaudi.portal.core.model.StrategicInvestorServiceTypeModel;
import com.investsaudi.portal.core.service.ContactTicketBusinessService;
import de.hybris.platform.customerticketingfacades.customerticket.DefaultCustomerTicketingFacade;
import de.hybris.platform.customerticketingfacades.data.ContactTicketData;
import de.hybris.platform.customerticketingfacades.data.ContactTicketSubjectData;
import de.hybris.platform.customerticketingfacades.data.MizaServiceTypeForFormData;
import de.hybris.platform.customerticketingfacades.data.StrategicInvestorServiceTypeData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.enums.CsTicketCategory;
import de.hybris.platform.ticket.enums.CsTicketPriority;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticketsystem.data.ContactTicketParameter;
import de.hybris.platform.ticketsystem.data.CsTicketParameter;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactTicketFacade extends DefaultCustomerTicketingFacade {

    @Autowired
    @Resource(name = "contactTicketSubjectConverter")
    private Converter<ContactTicketSubjectModel, ContactTicketSubjectData> contactTicketSubjectConverter;
    @Autowired
    @Resource(name = "mizaServiceTypeConverter")
    private Converter<MizaServiceTypeForFormModel, MizaServiceTypeForFormData> mizaServiceTypeConverter;
    @Resource(name = "strategicServiceTypeConverter")
    private Converter<StrategicInvestorServiceTypeModel, StrategicInvestorServiceTypeData> strategicServiceTypeConverter;

    @Autowired
    private ContactTicketBusinessService contactTicketBusinessService;

    @Autowired
    @Resource(name = "flexibleSearchService")
    private FlexibleSearchService flexibleSearchService;

    public void saveTicket(ContactTicketData contactTicketData) {
        CsTicketParameter csTicketParameter = createCsTicketParameter(contactTicketData);
        CsTicketModel ticket = contactTicketBusinessService.createTicket(csTicketParameter);
    }

    protected CsTicketParameter createCsTicketParameter(final ContactTicketData ticketData) {
        final ContactTicketParameter ticketParameter = new ContactTicketParameter();
        ticketParameter
            .setPriority(getEnumerationService().getEnumerationValue(CsTicketPriority._TYPECODE, getTicketPriority()));
        ticketParameter
            .setReason(getEnumerationService().getEnumerationValue(CsEventReason._TYPECODE, getTicketReason()));
        ticketParameter
            .setAssociatedTo(getTicketService().getAssociatedObject(ticketData.getAssociatedTo(), null, null));
        ticketParameter.setAssignedGroup(getDefaultCsAgentManagerGroup());
        ticketParameter.setCategory(CsTicketCategory.ENQUIRY);
        ticketParameter.setHeadline(ticketData.getContactSubject());
        ticketParameter.setInterventionType(CsInterventionType.TICKETMESSAGE);
        ticketParameter.setCreationNotes(ticketData.getMessage());
        ticketParameter.setCustomer(getUserService().getCurrentUser());
        ticketParameter.setAttachments(ticketData.getAttachments());

        ticketParameter.setName(ticketData.getName());
        ticketParameter.setCategoryCode(ticketData.getCategoryCode());
        ticketParameter.setCompany(ticketData.getCompany());
        ticketParameter.setJobTitle(ticketData.getJobTitle());
        ticketParameter.setEmail(ticketData.getEmail());
        ticketParameter.setMessage(ticketData.getMessage());
        ticketParameter.setMobile(ticketData.getMobile());
        ticketParameter.setContactSubject(ticketData.getContactSubject());
        ticketParameter.setOpportunity(ticketData.getOpportunity());        

        return ticketParameter;
    }

    public List<ContactTicketSubjectData> getContactTicketSubjects() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery("Select {pk} FROM {ContactTicketSubject}");
        var contactSubjects = flexibleSearchService.<ContactTicketSubjectModel>search(query).getResult();
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
