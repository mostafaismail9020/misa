
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.PrivateCitiesModel;
import com.sap.ibso.eservices.facades.data.PrivateCitiesData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class PrivateCitiesPopulator implements Populator<PrivateCitiesModel, PrivateCitiesData> {

	@Override
	public void populate(final PrivateCitiesModel source, final PrivateCitiesData target) throws ConversionException {
		target.setName(source.getName());
		target.setEstablishmentYear(source.getEstablishmentYear());
		target.setTotalArea(source.getTotalArea());
		target.setDevelopedArea(source.getDevelopedArea());
		target.setNoOfContracts(source.getNoOfContracts());
		target.setDisplayName(source.getDisplayName());
	}

}
