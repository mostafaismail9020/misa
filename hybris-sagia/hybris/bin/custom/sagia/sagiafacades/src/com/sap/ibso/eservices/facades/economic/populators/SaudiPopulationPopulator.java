package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.SaudiPopulationModel;
import com.sap.ibso.eservices.facades.data.SaudiPopulationData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SaudiPopulationPopulator implements Populator<SaudiPopulationModel, SaudiPopulationData>{

	@Override
	public void populate(SaudiPopulationModel source, SaudiPopulationData target) throws ConversionException {
		target.setNonSaudi(source.getNonSaudi());
		target.setSaudi(source.getSaudi());
		target.setSaudiPercentage(source.getSaudiPercentage());
		target.setNonSaudiPercentage(source.getNonSaudiPercentage());
		target.setYear(source.getYear());
		
	}

}
