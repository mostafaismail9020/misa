package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.RealSectorModel;
import com.sap.ibso.eservices.facades.data.RealSectorData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class RealSectorPopulator implements Populator<RealSectorModel, RealSectorData> {

	@Override
	public void populate(RealSectorModel source, RealSectorData target) throws ConversionException {
		target.setName(source.getName());
		target.setValue(source.getValue());
		target.setDisplayName(source.getDisplayName());
		
	}

}
