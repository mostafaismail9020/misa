
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.EmploymentModel;
import com.sap.ibso.eservices.facades.data.EmploymentData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class EmploymentPopulator implements Populator<EmploymentModel, EmploymentData> {

	@Override
	public void populate(final EmploymentModel source, final EmploymentData target) throws ConversionException {
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
