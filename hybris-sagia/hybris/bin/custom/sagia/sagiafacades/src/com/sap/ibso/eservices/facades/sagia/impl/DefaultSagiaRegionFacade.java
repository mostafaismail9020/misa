package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.core.model.SagiaRhqRegionModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaRegionService;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import com.sap.ibso.eservices.facades.sagia.SagiaRegionFacade;
import de.hybris.platform.converters.Populator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


/**
 * DefaultSagiaRegionFacade
 */
public class DefaultSagiaRegionFacade implements SagiaRegionFacade {

    private SagiaRegionService sagiaRegionService;
    private Populator sagiaRegionPopulator;
    @Resource
    private Populator sagiaRhqRegionPopulator;
    

    public void setSagiaRegionService(SagiaRegionService sagiaRegionService) {
        this.sagiaRegionService = sagiaRegionService;
    }

    public SagiaRegionService getSagiaRegionService() {
        return sagiaRegionService;
    }

    @Override
    public List<DropdownValue> getAllRegions() {
        final List<SagiaRegionModel> regions = getSagiaRegionService().getAllRegions();

        List<DropdownValue> regionsList = new ArrayList<>();
        for (SagiaRegionModel region : regions) {

            DropdownValue dropdownValue = new DropdownValue();
            sagiaRegionPopulator.populate(region, dropdownValue);
            regionsList.add(dropdownValue);
        }

        return regionsList;
    }

    @Override
    public List<DropdownValue> getRhqRegionsList() {
        final List<SagiaRhqRegionModel> regions = getSagiaRegionService().getRhqRegionsList();

        List<DropdownValue> regionsList = new ArrayList<>();
        for (SagiaRhqRegionModel region : regions) {

            DropdownValue dropdownValue = new DropdownValue();
            sagiaRhqRegionPopulator.populate(region, dropdownValue);
            regionsList.add(dropdownValue);
        }

        return regionsList;
    }
    
    @Override
    public DropdownValue getRegionForCode(String code) {
        SagiaRegionModel regionModel = getSagiaRegionService().getRegionForCode(code);
        if(regionModel != null){
            DropdownValue dropdownValue = new DropdownValue();
            getSagiaRegionPopulator().populate(regionModel, dropdownValue);
            return dropdownValue;
        }
        return null;
    }

    @Override
    public List<DropdownValue> getAllRegionsForCountryCode(String code) {
        final List<SagiaRegionModel> regions = getSagiaRegionService().getAllRegionsForCountryCode(code);

        List<DropdownValue> regionsList = new ArrayList<>();
        for (SagiaRegionModel region : regions) {

            DropdownValue dropdownValue = new DropdownValue();
            sagiaRegionPopulator.populate(region, dropdownValue);
            regionsList.add(dropdownValue);
        }

        return regionsList;
    }


    public Populator getSagiaRegionPopulator() {
        return sagiaRegionPopulator;
    }

    public void setSagiaRegionPopulator(Populator sagiaRegionPopulator) {
        this.sagiaRegionPopulator = sagiaRegionPopulator;
    }
}
