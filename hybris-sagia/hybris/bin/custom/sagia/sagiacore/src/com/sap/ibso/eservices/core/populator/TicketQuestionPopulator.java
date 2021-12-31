package com.sap.ibso.eservices.core.populator;

import com.sap.ibso.eservices.core.model.TicketQuestionModel;
import de.hybris.platform.customerticketingfacades.data.TicketQuestionData;
import de.hybris.platform.converters.Populator;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class TicketQuestionPopulator  implements Populator<TicketQuestionModel,TicketQuestionData> {

	@Override
	public void populate(TicketQuestionModel source, TicketQuestionData target) throws ConversionException {
		
		
		target.setId(source.getCode());
		target.setIdKey(source.getCode());
		target.setIsMandatory(source.isIsMandatory());
		target.setLabel(source.getLabel());
		target.setPlaceholder(source.getPlaceholder());
		target.setPlaceholderkey(source.getPlaceholderkey());
		
	}

}
