package com.sap.ibso.eservices.facades.populators.odata;


import com.sap.ibso.eservices.core.model.OperatingCostForRhqModel;
import com.sap.ibso.eservices.sagiaservices.data.odata.EstimatedOperatingCostForRhq;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


public class EstimatedOperatingCostODataPopulator implements Populator<OperatingCostForRhqModel, EstimatedOperatingCostForRhq> {

	@Override
	public void populate(OperatingCostForRhqModel source, EstimatedOperatingCostForRhq target) throws ConversionException {
		target.setItem(source.getItem());
		target.setUnitCost(source.getUnitCost());
		target.setNoOfUnits(source.getNoOfUnits());
		target.setCostFrequency(source.getCostFrequency());
		target.setYear2022(source.getYear2022());
		target.setYear2023(source.getYear2023());
		target.setYear2024(source.getYear2024());
	}
}
