package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaCityModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;
import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaCityService;
import com.sap.ibso.eservices.core.sagia.services.SagiaRegionService;
import com.sap.ibso.eservices.facades.data.SagiaCityData;
import com.sap.ibso.eservices.facades.data.SagiaRegionData;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import com.sap.ibso.eservices.facades.data.zqeemah.LicenseType;
import com.sap.ibso.eservices.facades.sagia.SagiaCityFacade;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultSagiaCityFacade
 */
public class DefaultSagiaCityFacade implements SagiaCityFacade {

    private SagiaCityService sagiaCityService;
    private Populator sagiaCityPopulator;


    @Override
    public List<DropdownValue> getAllCities() {
        final List<SagiaCityModel> cities = getSagiaCityService().getAllCities();

        List<DropdownValue> citiesList = new ArrayList<>();

        for (SagiaCityModel city : cities) {
            DropdownValue data = new DropdownValue();
            getSagiaCityPopulator().populate(city, data);
            citiesList.add(data);
        }

        return citiesList;
    }

    @Override
    public DropdownValue getCityForCode(String code) {

        SagiaCityModel cityFound = getSagiaCityService().getCityForCode(code);
        if(cityFound != null){
           DropdownValue city = new DropdownValue();
           getSagiaCityPopulator().populate(cityFound, city);
           return city;
        }
        return null;
    }

    @Override
    public List<DropdownValue> getAllCitiesForRegionCode(String code) {
        final List<SagiaCityModel> cities = getSagiaCityService().getAllCitiesForRegionCode(code);

        List<DropdownValue> citiesList = new ArrayList<>();

        for (SagiaCityModel city : cities) {
            DropdownValue data = new DropdownValue();
            getSagiaCityPopulator().populate(city, data);
            citiesList.add(data);
        }

        return citiesList;
    }

    public Populator getSagiaCityPopulator() {
        return sagiaCityPopulator;
    }

    public void setSagiaCityPopulator(Populator sagiaCityPopulator) {
        this.sagiaCityPopulator = sagiaCityPopulator;
    }

    public SagiaCityService getSagiaCityService() {
        return sagiaCityService;
    }

    public void setSagiaCityService(SagiaCityService sagiaCityService) {
        this.sagiaCityService = sagiaCityService;
    }
}
