package com.sap.ibso.eservices.core.populator;

import java.util.List;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.model.TicketSectorModel;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerticketingfacades.data.TicketConfigurationData;
import de.hybris.platform.customerticketingfacades.data.TicketSectorData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class TicketSectorPopulator  implements Populator<TicketSectorModel,TicketSectorData> {

	 @Resource(name = "ticketConfigurationConverter")
     private Converter<TicketConfigurationModel, TicketConfigurationData> ticketConfigurationConverter;
	
	@Override
	public void populate(TicketSectorModel source, TicketSectorData target) throws ConversionException {
		
		
		target.setId(source.getCode());
		target.setName(source.getName());
		final List<TicketConfigurationData> listConfigurationData = Converters.convertAll(source.getSubSectors(), ticketConfigurationConverter);
		target.setSubSecotrs(listConfigurationData);
	}


}
