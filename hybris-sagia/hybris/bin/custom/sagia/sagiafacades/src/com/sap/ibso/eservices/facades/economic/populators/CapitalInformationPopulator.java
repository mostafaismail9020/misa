
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.CapitalInformationModel;
import com.sap.ibso.eservices.facades.data.CapitalInformationData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CapitalInformationPopulator
		implements Populator<CapitalInformationModel, CapitalInformationData> {

	@Override
	public void populate(final CapitalInformationModel source, final CapitalInformationData target)
			throws ConversionException {
		target.setYear(source.getYear());
		target.setGrossFixed(source.getGrossFixed());
		target.setGrowthrateOfGrossFixed(source.getGrowthrateOfGrossFixed());
	}

}
