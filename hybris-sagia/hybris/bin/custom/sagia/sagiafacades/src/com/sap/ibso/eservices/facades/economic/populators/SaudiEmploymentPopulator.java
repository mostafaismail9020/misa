package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.SaudiEmploymentModel;
import com.sap.ibso.eservices.facades.data.SaudiEmploymentData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SaudiEmploymentPopulator implements Populator<SaudiEmploymentModel, SaudiEmploymentData>{

	@Override
	public void populate(SaudiEmploymentModel source, SaudiEmploymentData target) throws ConversionException {
		target.setYear(source.getYear());
		target.setMales(source.getMales());
		target.setFemales(source.getFemales());
		target.setTotal(source.getTotal());
		
	}

}
