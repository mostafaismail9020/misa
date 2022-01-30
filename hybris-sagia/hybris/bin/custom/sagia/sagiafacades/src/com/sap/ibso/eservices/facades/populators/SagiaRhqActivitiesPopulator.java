package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.RhqActivitiesModel;
import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import de.hybris.platform.converters.Populator;


public class SagiaRhqActivitiesPopulator implements Populator<RhqActivitiesModel, DropdownValue> {

    @Override
    public void populate(RhqActivitiesModel source, DropdownValue dropdownValue)  {
       dropdownValue.setId(source.getId());
        dropdownValue.setDetails(source.getDetails());
    }

}
