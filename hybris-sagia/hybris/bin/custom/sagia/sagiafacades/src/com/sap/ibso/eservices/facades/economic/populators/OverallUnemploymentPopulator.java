package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.OverallUnemploymentModel;
import com.sap.ibso.eservices.facades.data.OverallUnemploymentData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class OverallUnemploymentPopulator implements Populator<OverallUnemploymentModel, OverallUnemploymentData>{

	@Override
	public void populate(OverallUnemploymentModel source, OverallUnemploymentData target) throws ConversionException {
		target.setYear(source.getYear());
		target.setMales(source.getMales());
		target.setFemales(source.getFemales());
		target.setTotal(source.getTotal());
		
	}

}
