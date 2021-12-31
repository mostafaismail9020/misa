package com.investsaudi.portal.facades.contact.populator;

import com.investsaudi.portal.core.model.ContactTicketSubjectModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerticketingfacades.data.ContactTicketSubjectData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;

@Component
public class ContactTicketSubjectPopulator implements Populator<ContactTicketSubjectModel, ContactTicketSubjectData> {

    @Override
    public void populate(final ContactTicketSubjectModel contactTicketSubjectModel,
                         final ContactTicketSubjectData contactTicketSubjectData) throws ConversionException {
        contactTicketSubjectData.setCode(contactTicketSubjectModel.getCode());
        contactTicketSubjectData.setLabel(contactTicketSubjectModel.getLabel());
    }
}
