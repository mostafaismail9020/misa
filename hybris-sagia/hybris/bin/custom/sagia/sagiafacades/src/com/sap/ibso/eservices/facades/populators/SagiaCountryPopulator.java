package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import de.hybris.platform.converters.Populator;


public class SagiaCountryPopulator implements Populator<SagiaCountryModel, DropdownValue> {

    @Override
    public void populate(SagiaCountryModel source, DropdownValue dropdownValue)  {
        dropdownValue.setCountry(source.getCode());
        dropdownValue.setCountryText(source.getName());
        dropdownValue.setNationality(source.getCode());
        dropdownValue.setNationalityText(source.getName());
    }

}
