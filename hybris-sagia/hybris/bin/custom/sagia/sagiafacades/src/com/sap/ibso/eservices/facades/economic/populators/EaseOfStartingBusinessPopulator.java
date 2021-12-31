
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.EaseOfStartingBusinessModel;
import com.sap.ibso.eservices.facades.data.SAInternationalIndicesData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class EaseOfStartingBusinessPopulator
		implements Populator<EaseOfStartingBusinessModel, SAInternationalIndicesData> {

	@Override
	public void populate(final EaseOfStartingBusinessModel source, final SAInternationalIndicesData target)
			throws ConversionException {
		target.setFrequency(source.getFrequency());
		target.setRankValue(source.getRankValue());
		target.setScoreValue(source.getScoreValue());
		target.setYear(source.getYear());
	}

}
