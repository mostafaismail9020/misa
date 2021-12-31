package com.sap.ibso.eservices.core.populator;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerticketingfacades.data.TicketConfigurationData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class TicketConfigurationPopulator  implements Populator<TicketConfigurationModel,TicketConfigurationData> {

	@Override
	public void populate(TicketConfigurationModel source, TicketConfigurationData target) throws ConversionException {
		
		
		target.setId(source.getCode());
		target.setName(source.getName());
		
	}

}
