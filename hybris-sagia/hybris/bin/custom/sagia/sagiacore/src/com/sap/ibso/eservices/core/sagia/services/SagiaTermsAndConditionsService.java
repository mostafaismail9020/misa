package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.model.SagiaTermsAndConditionsModel;

import java.util.List;

/**
 * Sagia Terms And Conditions Service.
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaTermsAndConditionsService {

    /**
     * @return - All terms and conditions stored in DB
     */
    List<SagiaTermsAndConditionsModel> getAll();
    /**
     * Retrieves the most recent saved terms and conditions.
     * Period : now - seconds
     * @param seconds - Period of seconds
     * @param event - Acceptance event
     * @return - List of recently saved terms and conditions
     */
    List<SagiaTermsAndConditionsModel> getRecentlySaved(int seconds,TermsAndConditionsAcceptanceEventEnum event);

    /**
     * Get the active terms and conditions for an event
     * @param event - The event for which T&C are retrieved
     * @return - Terms and Conditions Model
     */
    SagiaTermsAndConditionsModel getActive(TermsAndConditionsAcceptanceEventEnum event);

    /**
     * Method that is called from {@link com.sap.ibso.eservices.core.event.SagiaAfterSaveListener}.
     * It creates a new version of a 'Terms and Conditions' entity while keeping hisotry.
     * It removes all the entities associated with that event that were saved less than 15 min ago.
     * This duration period can be changed from
     * {@link com.sap.ibso.eservices.core.sagia.services.impl.DefaultTermsAndConditionsService#DELETION_PERIOD_SECONDS}.
     * @param content - The HTML content of the Terms And Conditions page
     * @param event - The event for which T&C are updated (ex. REGISTRATION, SPECIAL_SERVICES etc)
     */
    void handleTermsAndConditionsUpdate(String content,TermsAndConditionsAcceptanceEventEnum event);
}
