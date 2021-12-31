package com.sap.ibso.eservices.facades.sagia;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.exceptions.PasswordMismatchException;

/**
 * SagiaChangingEmailFacade
 */
public interface SagiaChangingEmailFacade {

    /**
     * updates Email
     * @param customerData customerData
     * @param passw passw
     * @throws PasswordMismatchException exception
     */
    void updateEmail(CustomerData customerData, String passw) throws PasswordMismatchException;
}
