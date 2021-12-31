package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaCityModel;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import de.hybris.platform.converters.Populator;


public class SagiaCityPopulator implements Populator<SagiaCityModel, DropdownValue> {

    @Override
    public void populate(SagiaCityModel source, DropdownValue dropdownValue)  {
        dropdownValue.setCity(source.getCode());
        dropdownValue.setCityText(source.getName());
    }

}
