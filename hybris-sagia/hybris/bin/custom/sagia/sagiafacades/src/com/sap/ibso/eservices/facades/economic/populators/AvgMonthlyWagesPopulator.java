package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.AvgMonthlyWagesModel;
import com.sap.ibso.eservices.facades.data.AvgMonthlyWagesData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class AvgMonthlyWagesPopulator implements Populator<AvgMonthlyWagesModel, AvgMonthlyWagesData>{

	@Override
	public void populate(AvgMonthlyWagesModel source, AvgMonthlyWagesData target) throws ConversionException {
		target.setYear(source.getYear());
		target.setMales(source.getMales());
		target.setFemales(source.getFemales());
		target.setTotal(source.getTotal());
		
		
	}

}
