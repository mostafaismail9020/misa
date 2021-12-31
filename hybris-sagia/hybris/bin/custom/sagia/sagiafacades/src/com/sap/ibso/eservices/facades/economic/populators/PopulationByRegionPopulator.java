package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.PopulationByRegionModel;
import com.sap.ibso.eservices.facades.data.PopulationByRegionData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class PopulationByRegionPopulator implements Populator<PopulationByRegionModel, PopulationByRegionData>{

	@Override
	public void populate(PopulationByRegionModel source, PopulationByRegionData target) throws ConversionException {
		target.setRiyadhValue(source.getRiyadhValue());
		target.setRiyadhPercentage(source.getRiyadhPercentage());
		target.setYear(source.getYear());
		target.setMakkahValue(source.getMakkahValue());
		target.setMakkahPercentage(source.getMakkahPercentage());
		target.setAlmadinahValue(source.getAlmadinahValue());
		target.setAlmadinaPercentage(source.getAlmadinaPercentage());
		target.setAlqassimValue(source.getAlqassimValue());
		target.setAlqassimPercentage(source.getAlqassimPercentage());
		target.setEasternValue(source.getEasternValue());
		target.setEasternPercentage(source.getEasternPercentage());
		target.setAsirValue(source.getAsirValue());
		target.setAsirPercentage(source.getAsirPercentage());
		target.setTabukValue(source.getTabukValue());
		target.setTabukPercentage(source.getTabukPercentage());
		target.setHailValue(source.getHailValue());
		target.setHailPercentage(source.getHailPercentage());
		target.setNorthernValue(source.getNorthernValue());
		target.setNorthernPercentage(source.getNorthernPercentage());
		target.setJazanValue(source.getJazanValue());
		target.setJazanPercentage(source.getJazanPercentage());
		target.setNajranValue(source.getNajranValue());
		target.setNajranPercentage(source.getNajranPercentage());
		target.setAlbahaValue(source.getAlbahaValue());
		target.setAlbahaPercentage(source.getAlbahaPercentage());
		target.setAljawfValue(source.getAljawfValue());
		target.setAljawfPercentage(source.getAljawfPercentage());
		target.setTotalPopulation(source.getTotalPopulation());
		
	}

}
