package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import de.hybris.platform.core.model.user.CustomerModel;

/**
 * Provides access to  SagiaTermsAndConditionsFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaTermsAndConditionsFacade {

    /**
     * Accept a version of Terms and Conditions by a Customer on an event.
     * @param customer - Customer who accepts T&C
     * @param event - Event on which the T&C were accepted
     */
    void acceptTermsAndConditions(CustomerModel customer, TermsAndConditionsAcceptanceEventEnum event);
}
