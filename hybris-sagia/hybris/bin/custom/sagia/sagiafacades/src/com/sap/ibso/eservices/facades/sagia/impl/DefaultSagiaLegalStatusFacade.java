package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaLegalStatusModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaLegalStatusService;
import com.sap.ibso.eservices.facades.data.SagiaLegalStatusData;
import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import com.sap.ibso.eservices.facades.data.zqeemah.LicenseType;
import com.sap.ibso.eservices.facades.sagia.SagiaLegalStatusFacade;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultSagiaLegalStatusFacade
 */
public class DefaultSagiaLegalStatusFacade implements SagiaLegalStatusFacade {

    private SagiaLegalStatusService sagiaLegalStatusService;
    private Populator sagiaLegalStatusPopulator;


    @Override
    public List<DropdownValue> getAllLegalStatus() {
        final List<SagiaLegalStatusModel> legalStatus = getSagiaLegalStatusService().getAllLegalStatus();

        List<DropdownValue> legalStatusList = new ArrayList<>();
        for (SagiaLegalStatusModel sagiaLegalStatusModel : legalStatus) {

            DropdownValue dropdownValue = new DropdownValue();
            sagiaLegalStatusPopulator.populate(sagiaLegalStatusModel, dropdownValue);
            legalStatusList.add(dropdownValue);
        }

        return legalStatusList;
    }

    @Override
    public DropdownValue getLegalStatusForCode(String code) {
        SagiaLegalStatusModel legalStatusModel = getSagiaLegalStatusService().getLegalStatusForCode(code);
        if(legalStatusModel != null){

            DropdownValue dropdownValue = new DropdownValue();
            sagiaLegalStatusPopulator.populate(legalStatusModel, dropdownValue);

           return dropdownValue;
        }
        return null;
    }

    public Populator getSagiaLegalStatusPopulator() {
        return sagiaLegalStatusPopulator;
    }

    public void setSagiaLegalStatusPopulator(Populator sagiaLegalStatusPopulator) {
        this.sagiaLegalStatusPopulator = sagiaLegalStatusPopulator;
    }

    public void setSagiaLegalStatusService(SagiaLegalStatusService sagiaLegalStatusService) {
        this.sagiaLegalStatusService = sagiaLegalStatusService;
    }

    public SagiaLegalStatusService getSagiaLegalStatusService() {
        return sagiaLegalStatusService;
    }

}
