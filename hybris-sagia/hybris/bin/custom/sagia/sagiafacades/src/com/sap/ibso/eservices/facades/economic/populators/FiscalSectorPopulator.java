package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.FiscalSectorModel;
import com.sap.ibso.eservices.facades.data.FiscalSectorData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class FiscalSectorPopulator implements Populator<FiscalSectorModel, FiscalSectorData>{

	@Override
	public void populate(FiscalSectorModel source, FiscalSectorData target) throws ConversionException {
		target.setLabel(source.getLabel());
		target.setName(source.getName());
		target.setValue(source.getValue());
		target.setYear(source.getYear());
		target.setDisplayName(source.getDisplayName());
		
	}

}
