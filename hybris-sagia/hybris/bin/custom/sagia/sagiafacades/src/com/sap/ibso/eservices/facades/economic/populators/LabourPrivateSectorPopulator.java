package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.LabourPrivateSectorModel;
import com.sap.ibso.eservices.facades.data.LabourPrivateSectorData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class LabourPrivateSectorPopulator implements Populator<LabourPrivateSectorModel, LabourPrivateSectorData>{

	@Override
	public void populate(LabourPrivateSectorModel source, LabourPrivateSectorData target) throws ConversionException {
		target.setYear(source.getYear());
		target.setSaudi(source.getSaudi());
		target.setNonSaudi(source.getNonSaudi());
		target.setTotalLabour(source.getTotalLabour());
		target.setSaudiPercentagelabour(source.getSaudiPercentagelabour());
		target.setNonSaudiPercentageLabour(source.getNonSaudiPercentageLabour());
		
	}

}
