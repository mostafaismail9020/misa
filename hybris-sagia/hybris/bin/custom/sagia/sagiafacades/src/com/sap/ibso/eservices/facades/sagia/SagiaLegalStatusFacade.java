package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;

import java.util.List;

/**
 * SagiaLegalStatusFacade
 */
public interface SagiaLegalStatusFacade {

    List<DropdownValue> getAllLegalStatus();

    DropdownValue getLegalStatusForCode(String code);
}



