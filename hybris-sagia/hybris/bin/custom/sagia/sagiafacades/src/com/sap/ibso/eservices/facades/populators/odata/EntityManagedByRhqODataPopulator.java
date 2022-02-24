package com.sap.ibso.eservices.facades.populators.odata;


import com.sap.ibso.eservices.core.model.EntitiesManagedByRhqModel;
import com.sap.ibso.eservices.sagiaservices.data.odata.EntitiesManagedByRhq;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


public class EntityManagedByRhqODataPopulator implements Populator<EntitiesManagedByRhqModel, EntitiesManagedByRhq> {
	@Override
	public void populate(EntitiesManagedByRhqModel source, EntitiesManagedByRhq target) throws ConversionException {
		target.setCompanyName(source.getCompanyName());
		target.setCountry(source.getCountry());
		target.setBusinessRelationshipType(source.getBusinessRelationshipType());
		target.setIndustry(source.getIndustry());
		target.setOperations(source.getOperations());
		target.setRhqActivityProvided(source.getRhqActivityProvided());
	}
	

}
