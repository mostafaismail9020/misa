package com.investsaudi.portal.facades.contact.populator;

import com.investsaudi.portal.core.model.ContactTicketPurposeModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerticketingfacades.data.ContactTicketSubjectData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;

@Component
public class ContactTicketSubjectPopulator implements Populator<ContactTicketPurposeModel, ContactTicketSubjectData> {

    @Override
    public void populate(final ContactTicketPurposeModel contactTicketPurposeModel,
                         final ContactTicketSubjectData contactTicketSubjectData) throws ConversionException {
        contactTicketSubjectData.setCode(contactTicketPurposeModel.getCode());
        contactTicketSubjectData.setLabel(contactTicketPurposeModel.getLabel());
    }
}
