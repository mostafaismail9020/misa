package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.ExternalSectorModel;
import com.sap.ibso.eservices.facades.data.ExternalSectorData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ExternalSectorPopulator implements Populator<ExternalSectorModel, ExternalSectorData>{

	@Override
	public void populate(ExternalSectorModel source, ExternalSectorData target) throws ConversionException {
		target.setLabel(source.getLabel());
		target.setName(source.getName());
		target.setValue(source.getValue());
		target.setYear(source.getYear());
		target.setDisplayName(source.getDisplayName());
	}

}
