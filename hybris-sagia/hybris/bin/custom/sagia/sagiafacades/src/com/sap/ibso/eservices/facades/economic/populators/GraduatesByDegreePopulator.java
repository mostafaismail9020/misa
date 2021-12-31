package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.GraduatesByDegreeModel;
import com.sap.ibso.eservices.facades.data.GraduatesByDegreeData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class GraduatesByDegreePopulator implements Populator<GraduatesByDegreeModel, GraduatesByDegreeData>{

	@Override
	public void populate(GraduatesByDegreeModel source, GraduatesByDegreeData target) throws ConversionException {
		target.setPhd(source.getPhd());
		target.setFellowship(source.getFellowship());
		target.setMaster(source.getMaster());
		target.setDiploma(source.getDiploma());
		target.setBachelor(source.getBachelor());
		target.setIntermediate(source.getIntermediate());
	}

}
