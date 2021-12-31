package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.model.SagiaTermsAndConditionsModel;

import java.util.List;

public interface SagiaTermsAndConditionsDAO {

    /**
     * @return - All terms and conditions stored in DB
     */
    List<SagiaTermsAndConditionsModel> getAll();

    /**
     * Retrieves the most recent saved terms and conditions.
     * Period : now - seconds
     *
     * @param seconds - Period of seconds
     * @param event   - Acceptance event
     * @return - List of recently saved terms and conditions
     */
    List<SagiaTermsAndConditionsModel> getRecentlySaved(int seconds, TermsAndConditionsAcceptanceEventEnum event);

    /**
     * Get the active terms and conditions for an event
     *
     * @param event - The event for which T&C are retrieved
     * @return - Terms and Conditions Model
     */
    SagiaTermsAndConditionsModel getActive(TermsAndConditionsAcceptanceEventEnum event);

}
