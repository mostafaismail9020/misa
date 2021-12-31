package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.GrowthrateWagesModel;
import com.sap.ibso.eservices.facades.data.GrowthrateWagesData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class GrowthrateWagesPopulator implements Populator<GrowthrateWagesModel, GrowthrateWagesData>{

	@Override
	public void populate(GrowthrateWagesModel source, GrowthrateWagesData target) throws ConversionException {
		target.setYear(source.getYear());
		target.setMales(source.getMales());
		target.setFemales(source.getFemales());
		target.setTotal(source.getTotal());
		
		
	}

}
