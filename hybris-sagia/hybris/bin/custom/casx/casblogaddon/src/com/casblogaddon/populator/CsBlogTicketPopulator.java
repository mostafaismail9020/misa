package com.casblogaddon.populator;

import com.casblogaddon.model.CsBlogTicketModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.ticketsystem.data.CsTicketParameter;

public class CsBlogTicketPopulator<SOURCE extends CsTicketParameter, TARGET extends CsBlogTicketModel> implements Populator<SOURCE, TARGET> {

    public CsBlogTicketPopulator() {
    }

    public void populate(SOURCE source, TARGET target) throws ConversionException {
        target.setHeadline(source.getHeadline());
        target.setOrder(source.getAssociatedTo());
        target.setPriority(source.getPriority());
        target.setCategory(source.getCategory());
        target.setAssignedAgent(source.getAssignedAgent());
        target.setAssignedGroup(source.getAssignedGroup());
        target.setCustomer(source.getCustomer());

		if(source.getBlogPostComponent() != null){
            target.setBlogComment(source.getBlogComment());
            target.setBlogPostComponent(source.getBlogPostComponent());
		}

    }
}
