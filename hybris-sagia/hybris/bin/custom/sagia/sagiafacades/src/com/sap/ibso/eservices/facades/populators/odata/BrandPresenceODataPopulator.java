package com.sap.ibso.eservices.facades.populators.odata;


import com.sap.ibso.eservices.core.model.BrandPresenceModel;
import com.sap.ibso.eservices.sagiaservices.data.odata.BrandPresenceInMENARegion;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


public class BrandPresenceODataPopulator implements Populator<BrandPresenceModel, BrandPresenceInMENARegion> {
	@Override
	public void populate(BrandPresenceModel source, BrandPresenceInMENARegion target) throws ConversionException {
		target.setBrandName(source.getBrandName());
		target.setCountry(source.getCountry());
		target.setIndustry(source.getIndustry());
		target.setCompanyOwningBrandInMENA(source.getCompanyOwningBrandInMENA());
		target.setRhqActivityProvided(source.getRhqActivityProvided());
	}
}
