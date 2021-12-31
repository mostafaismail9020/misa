package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeRequirementModel;
import com.sap.ibso.eservices.facades.data.SagiaLicenseTypeRequirementData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class SagiaLicenseTypeRequirementPopulator implements Populator<SagiaLicenseTypeRequirementModel, SagiaLicenseTypeRequirementData> {

	@Override
	public void populate(SagiaLicenseTypeRequirementModel source, SagiaLicenseTypeRequirementData target)
			throws ConversionException {
		target.setContent(source.getContent());
        target.setId(source.getSplRequirementId());
		
	}

}
