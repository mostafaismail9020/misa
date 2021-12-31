
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.NumberOfCommercialRegisterModel;
import com.sap.ibso.eservices.facades.data.NumberOfCommercialRegisterData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class NumberOfCommercialRegisterPopulator
		implements Populator<NumberOfCommercialRegisterModel, NumberOfCommercialRegisterData> {

	@Override
	public void populate(final NumberOfCommercialRegisterModel source, final NumberOfCommercialRegisterData target)
			throws ConversionException {
		target.setYear(source.getYear());
		target.setNoOfCommercialRegisters(source.getNoOfCommercialRegisters());
		target.setGrowthRate(source.getGrowthRate());
	}

}
