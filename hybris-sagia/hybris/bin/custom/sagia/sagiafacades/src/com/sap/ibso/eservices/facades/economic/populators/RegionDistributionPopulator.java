package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.RegionDistributionModel;
import com.sap.ibso.eservices.facades.data.RegionDistributionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class RegionDistributionPopulator implements Populator<RegionDistributionModel, RegionDistributionData> {
    @Override
    public void populate(RegionDistributionModel source, RegionDistributionData target) throws ConversionException {
        target.setUid(source.getUid());
        target.setYear(source.getYear());
        target.setAlbahaValue(source.getAlbahaValue());
        target.setAlbahaPercentage(source.getAlbahaPercentage());
        target.setAljawfValue(source.getAljawfValue());
        target.setAljawfPercentage(source.getAljawfPercentage());
        target.setAlmadinahValue(source.getAlmadinahValue());
        target.setAlmadinaPercentage(source.getAlmadinaPercentage());
        target.setAlqassimValue(source.getAlqassimValue());
        target.setAlqassimPercentage(source.getAlqassimPercentage());
        target.setAlbahaValue(source.getAlbahaValue());
        target.setAlbahaPercentage(source.getAlbahaPercentage());
        target.setAsirValue(source.getAsirValue());
        target.setAsirPercentage(source.getAsirPercentage());
        target.setEasternValue(source.getEasternValue());
        target.setEasternPercentage(source.getEasternPercentage());
        target.setRiyadhValue(source.getRiyadhValue());
        target.setRiyadhPercentage(source.getRiyadhPercentage());
        target.setHailValue(source.getHailValue());
        target.setHailPercentage(source.getHailPercentage());
        target.setMakkahValue(source.getMakkahValue());
        target.setMakkahPercentage(source.getMakkahPercentage());
        target.setTabukValue(source.getTabukValue());
        target.setTabukPercentage(source.getTabukPercentage());
        target.setNorthernValue(source.getNorthernValue());
        target.setNorthernPercentage(source.getNorthernPercentage());
        target.setJazanValue(source.getJazanValue());
        target.setJazanPercentage(source.getJazanPercentage());
        target.setNajranValue(source.getNajranValue());
        target.setNajranPercentage(source.getNajranPercentage());
        target.setTotalPopulation(source.getTotalPopulation());
    }
}
