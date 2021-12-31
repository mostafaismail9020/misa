
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.CreditRatingModel;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.sap.ibso.eservices.facades.data.CreditRatingData;

public class CreditRatingPopulator implements Populator<CreditRatingModel, CreditRatingData> {

	@Override
	public void populate(final CreditRatingModel source, final CreditRatingData target) throws ConversionException {
		target.setSpLabel(source.getSpLabel());
		target.setMoodysLabel(source.getMoodysLabel());
		target.setFitchLabel(source.getFitchLabel());
		target.setSpValue(source.getSpValue());
		target.setMoodysValue(source.getMoodysValue());
		target.setFitchValue(source.getFitchValue());
		target.setName(source.getName());
		target.setDisplayName(source.getDisplayName());
		target.setCalenderValue(source.getCalenderValue());
		target.setCurrenyValue(source.getCurrencyValue());
	}

}
