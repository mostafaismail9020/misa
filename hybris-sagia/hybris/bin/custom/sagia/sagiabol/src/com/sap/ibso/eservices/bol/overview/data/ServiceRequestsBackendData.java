package com.sap.ibso.eservices.bol.overview.data;

import de.hybris.platform.sap.core.common.message.Message;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Provides a data transfer object for a list of service requests from SAP backend system.
 */
public class ServiceRequestsBackendData implements Serializable
{
    private List<ServiceRequestBackendData> serviceRequests;
    private List<Message> messages;

    /**
     * Gets the service requests.
     *
     * @return the service requests
     */
    public List<ServiceRequestBackendData> getServiceRequests()
    {
        return serviceRequests != null && !serviceRequests.isEmpty() ? serviceRequests : Collections.emptyList();
    }

    /**
     * Sets the service requests.
     *
     * @param serviceRequests the service requests
     */
    public void setServiceRequests(List<ServiceRequestBackendData> serviceRequests)
    {
        this.serviceRequests = serviceRequests;
    }

    /**
     * Gets backend messages. If there are no backend message available then an empty list is returned.
     *
     * @return the messages
     */
    public List<Message> getMessages()
    {
        return messages != null && !messages.isEmpty() ? messages : Collections.emptyList();
    }

    /**
     * Sets backend messages.
     *
     * @param messages the messages
     */
    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }
}
