
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.EGovernmentDevelopmentIndexModel;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.sap.ibso.eservices.facades.data.SAInternationalIndicesData;

public class EGovernmentDevelopmentIndexPopulator
		implements Populator<EGovernmentDevelopmentIndexModel, SAInternationalIndicesData> {

	@Override
	public void populate(final EGovernmentDevelopmentIndexModel source, final SAInternationalIndicesData target)
			throws ConversionException {
		target.setFrequency(source.getFrequency());
		target.setRankValue(source.getRankValue());
		target.setScoreValue(source.getScoreValue());
		target.setYear(source.getYear());
	}

}