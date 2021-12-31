package com.sap.ibso.eservices.bol;

import de.hybris.platform.sap.core.common.util.GenericFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Provides access to remote function call based backend services.
 */
public abstract class BackendAwareService
{
    private GenericFactory genericFactory;
    private final String backendServiceBeanName;

    /**
     * Creates an backend aware service instance.
     *
     * @param backendServiceBeanName the backend service bean name
     */
    public BackendAwareService(String backendServiceBeanName)
    {
        this.backendServiceBeanName = backendServiceBeanName;
    }

    @Required
    public void setGenericFactory(GenericFactory genericFactory)
    {
        this.genericFactory = genericFactory;
    }

    /**
     * Returns the backend service instance for its sub-class.
     *
     * @param <T> the type of the backend service
     * @return the backend service
     */
    protected <T> T getBackendService()
    {
        return genericFactory.getBean(backendServiceBeanName);
    }
}
