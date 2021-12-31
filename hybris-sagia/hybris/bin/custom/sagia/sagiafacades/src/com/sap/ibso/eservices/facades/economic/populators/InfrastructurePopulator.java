
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.InfrastructureModel;
import com.sap.ibso.eservices.facades.data.InfrastructureData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class InfrastructurePopulator implements Populator<InfrastructureModel, InfrastructureData> {

	@Override
	public void populate(final InfrastructureModel source, final InfrastructureData target) throws ConversionException {
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
