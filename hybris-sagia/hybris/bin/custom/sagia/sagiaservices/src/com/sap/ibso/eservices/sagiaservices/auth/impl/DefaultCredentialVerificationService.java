package com.sap.ibso.eservices.sagiaservices.auth.impl;

import com.sap.ibso.eservices.sagiaservices.auth.CredentialVerificationService;
import com.sap.ibso.eservices.sagiaservices.auth.impl.odata.CredentialVerificationODataService;
import de.hybris.platform.sap.core.common.util.GenericFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Implements credential verification against an external system based on the current base store configuration.
 */
public class DefaultCredentialVerificationService implements CredentialVerificationService
{
    private GenericFactory genericFactory;

    @Override
    public boolean isValid(String userName, String password)
    {
        CredentialVerificationODataService oDataService = genericFactory.getBean("credentialVerificationODataService");
        return oDataService.isValid(userName, password);
    }

    /**
     * Sets a generic factory to retrieve Spring beans of scope "prototype".
     *
     * @param genericFactory the generic factory
     */
    @Required
    public void setGenericFactory(GenericFactory genericFactory)
    {
        this.genericFactory = genericFactory;
    }
}
