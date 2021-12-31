
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.CapitalOfCommercialRegisterModel;
import com.sap.ibso.eservices.facades.data.CapitalOfCommercialRegisterData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CapitalOfCommercialRegisterPopulator
		implements Populator<CapitalOfCommercialRegisterModel, CapitalOfCommercialRegisterData> {

	@Override
	public void populate(final CapitalOfCommercialRegisterModel source, final CapitalOfCommercialRegisterData target)
			throws ConversionException {
		target.setYear(source.getYear());
		target.setCapitalOfCommercialRegisters(source.getCapitalOfCommercialRegisters());
		target.setGrowthRate(source.getGrowthRate());
	}

}
