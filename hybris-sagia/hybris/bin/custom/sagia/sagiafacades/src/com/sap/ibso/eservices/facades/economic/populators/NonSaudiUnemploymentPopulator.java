package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.NonSaudiUnemploymentModel;
import com.sap.ibso.eservices.facades.data.NonSaudiUnemploymentData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class NonSaudiUnemploymentPopulator implements Populator<NonSaudiUnemploymentModel, NonSaudiUnemploymentData>{

	@Override
	public void populate(NonSaudiUnemploymentModel source, NonSaudiUnemploymentData target) throws ConversionException {
		target.setYear(source.getYear());
		target.setMales(source.getMales());
		target.setFemales(source.getFemales());
		target.setTotal(source.getTotal());
		
	}

}
