
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.HousingFacilitiesModel;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.sap.ibso.eservices.facades.data.HousingFacilitiesData;

public class HousingFacilitiesPopulator implements Populator<HousingFacilitiesModel, HousingFacilitiesData> {

	@Override
	public void populate(final HousingFacilitiesModel source, final HousingFacilitiesData target)
			throws ConversionException {
		target.setName(source.getName());
		target.setValue1(source.getValue1());
		target.setValue2(source.getValue2());
		target.setValue3(source.getValue3());
		target.setValue4(source.getValue4());
		target.setValue5(source.getValue5());
		target.setLabel1(source.getLabel1());
		target.setLabel2(source.getLabel2());
		target.setLabel3(source.getLabel3());
		target.setLabel4(source.getLabel4());
		target.setLabel5(source.getLabel5());
		target.setDisplayName(source.getDisplayName());
	}

}
