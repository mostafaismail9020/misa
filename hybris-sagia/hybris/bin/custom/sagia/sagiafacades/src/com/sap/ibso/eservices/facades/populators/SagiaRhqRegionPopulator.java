package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaRhqRegionModel;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;

import de.hybris.platform.converters.Populator;


public class SagiaRhqRegionPopulator implements Populator<SagiaRhqRegionModel, DropdownValue> {

    @Override
    public void populate(SagiaRhqRegionModel source, DropdownValue dropdownValue)  {
        dropdownValue.setRegion(source.getCode());
        dropdownValue.setRegionText(source.getName());
    }

}
