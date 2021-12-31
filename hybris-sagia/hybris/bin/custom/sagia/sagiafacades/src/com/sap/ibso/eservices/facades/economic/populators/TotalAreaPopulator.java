
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.TotalAreaModel;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.sap.ibso.eservices.facades.data.TotalAreaData;

public class TotalAreaPopulator implements Populator<TotalAreaModel, TotalAreaData> {

	@Override
	public void populate(final TotalAreaModel source, final TotalAreaData target) throws ConversionException {
		target.setName(source.getName());
		target.setValue1(source.getValue1());
		target.setValue2(source.getValue2());
		target.setValue3(source.getValue3());
		target.setLabel1(source.getLabel1());
		target.setLabel2(source.getLabel2());
		target.setLabel3(source.getLabel3());
		target.setDisplayName(source.getDisplayName());
	}

}
