package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaLegalStatusModel;
import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import de.hybris.platform.converters.Populator;


public class SagiaRegionPopulator implements Populator<SagiaRegionModel, DropdownValue> {

    @Override
    public void populate(SagiaRegionModel source, DropdownValue dropdownValue)  {
        dropdownValue.setRegion(source.getCode());
        dropdownValue.setRegionText(source.getName());
    }

}
