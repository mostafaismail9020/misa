package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.NonSaudiEmploymentModel;
import com.sap.ibso.eservices.facades.data.NonSaudiEmploymentData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class NonSaudiEmploymentPopulator implements Populator<NonSaudiEmploymentModel, NonSaudiEmploymentData>{

	@Override
	public void populate(NonSaudiEmploymentModel source, NonSaudiEmploymentData target) throws ConversionException {
		target.setYear(source.getYear());
		target.setMales(source.getMales());
		target.setFemales(source.getFemales());
		target.setTotal(source.getTotal());
		
	}

}
