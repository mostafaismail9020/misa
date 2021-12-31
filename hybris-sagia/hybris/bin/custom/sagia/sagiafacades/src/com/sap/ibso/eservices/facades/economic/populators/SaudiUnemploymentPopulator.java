package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.SaudiUnemploymentModel;
import com.sap.ibso.eservices.facades.data.SaudiUnemploymentData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SaudiUnemploymentPopulator implements Populator<SaudiUnemploymentModel, SaudiUnemploymentData>{

	@Override
	public void populate(SaudiUnemploymentModel source, SaudiUnemploymentData target) throws ConversionException {
		target.setYear(source.getYear());
		target.setMales(source.getMales());
		target.setFemales(source.getFemales());
		target.setTotal(source.getTotal());
		
	}

}
