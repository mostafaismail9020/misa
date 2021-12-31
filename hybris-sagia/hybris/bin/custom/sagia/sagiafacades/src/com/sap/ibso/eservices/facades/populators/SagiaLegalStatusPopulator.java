package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaLegalStatusModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import com.sap.ibso.eservices.facades.data.zqeemah.LicenseType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


public class SagiaLegalStatusPopulator implements Populator<SagiaLegalStatusModel, DropdownValue> {

    @Override
    public void populate(SagiaLegalStatusModel source, DropdownValue dropdownValue)  {
        dropdownValue.setLegalStatus(source.getCode());
        dropdownValue.setLegalStatusText(source.getName());
    }

}
