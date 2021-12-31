package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.MonetarySectorModel;
import com.sap.ibso.eservices.facades.data.MonetarySectorData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class MonetarySectorPopulator implements Populator<MonetarySectorModel, MonetarySectorData>{

	@Override
	public void populate(MonetarySectorModel source, MonetarySectorData target) throws ConversionException {
		target.setLabel(source.getLabel());
		target.setName(source.getName());
		target.setValue(source.getValue());
		target.setYear(source.getYear());
		target.setDisplayName(source.getDisplayName());
		
	}

}
