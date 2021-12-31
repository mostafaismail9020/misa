package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaCityModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaCountryService;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import com.sap.ibso.eservices.facades.sagia.SagiaCountryFacade;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * DefaultSagiaCountryFacade
 */
public class DefaultSagiaCountryFacade implements SagiaCountryFacade {

    private SagiaCountryService sagiaCountryService;
    private Converter<SagiaCountryModel,SagiaCountryData> sagiaCountryConverter;
    private Populator sagiaCountryPopulator;

    /**
     * @return
     */
    public SagiaCountryService getSagiaCountryService() {
        return sagiaCountryService;
    }

    /**
     * @return
     */
    public Converter<SagiaCountryModel, SagiaCountryData> getSagiaCountryConverter() {
        return sagiaCountryConverter;
    }

    /**
     * @param sagiaCountryService
     */
    public void setSagiaCountryService(SagiaCountryService sagiaCountryService) {
        this.sagiaCountryService = sagiaCountryService;
    }

    /**
     * @param sagiaCountryConverter
     */
    public void setSagiaCountryConverter(Converter<SagiaCountryModel, SagiaCountryData> sagiaCountryConverter) {
        this.sagiaCountryConverter = sagiaCountryConverter;
    }

    /**
     * retrieves countries List from DB
     * @return
     */
    @Override
    public List<SagiaCountryData> getCountriesList() {
        final List<SagiaCountryModel> countries = getSagiaCountryService().getCountries();
        final List<SagiaCountryData> countriesData = new ArrayList<>();
        for (SagiaCountryModel country : countries) {
            if(!country.getBlacklisted()){
                SagiaCountryData countryData = getSagiaCountryConverter().convert(country);
                countriesData.add(countryData);
            }
        }
        return countriesData;
    }

    @Override
    public List<DropdownValue> getAllCountries() {
        final List<SagiaCountryModel> countries = getSagiaCountryService().getCountries();

        List<DropdownValue> countryList = new ArrayList<>();

        for (SagiaCountryModel country : countries) {
            DropdownValue data = new DropdownValue();
            getSagiaCountryPopulator().populate(country, data);
            countryList.add(data);
        }

        return countryList;
    }

    /**
     * retrieves countries List from DB
     * @return
     */
    @Override
    public List<SagiaCountryData> getShareHolderCheckCountries() {
        final List<SagiaCountryModel> countries = getSagiaCountryService().getShareHolderCheckCountries();
        final List<SagiaCountryData> countriesData = new ArrayList<>();
        for (SagiaCountryModel country : countries) {
                SagiaCountryData countryData = getSagiaCountryConverter().convert(country);
                countriesData.add(countryData);
        }        
        return countriesData;
    }

    public Populator getSagiaCountryPopulator() {
        return sagiaCountryPopulator;
    }

    public void setSagiaCountryPopulator(Populator sagiaCountryPopulator) {
        this.sagiaCountryPopulator = sagiaCountryPopulator;
    }

}
